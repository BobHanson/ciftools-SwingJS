package org.rcsb.cif.binary;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import org.rcsb.cif.binary.codec.Classifier;
import org.rcsb.cif.binary.data.ByteArray;
import org.rcsb.cif.binary.data.EncodedDataFactory;
import org.rcsb.cif.binary.data.Float64Array;
import org.rcsb.cif.binary.data.Int32Array;
import org.rcsb.cif.binary.data.Uint8Array;
import org.rcsb.cif.binary.encoding.ByteArrayEncoding;
import org.rcsb.cif.binary.encoding.FixedPointEncoding;
import org.rcsb.cif.binary.encoding.RunLengthEncoding;
import org.rcsb.cif.binary.encoding.StringArrayEncoding;
import org.rcsb.cif.io.CifOptions;
import org.rcsb.cif.io.EncodingStrategyHint;
import org.rcsb.cif.io.Platform;
import org.rcsb.cif.model.Column;
import org.rcsb.cif.model.FloatColumn;
import org.rcsb.cif.model.IntColumn;
import org.rcsb.cif.model.StrColumn;
import org.rcsb.cif.model.ValueKind;

public class BinaryEncoder {

	private CifOptions options;

	public BinaryEncoder(CifOptions options) {
		this.options = options;
	}

	public Map<String, Object> encodeColumn(String categoryName, Column cifColumn) {
        if (cifColumn instanceof FloatColumn) {
            FloatColumn floatCol = (FloatColumn) cifColumn;
            double[] array = floatCol.getBinaryData() != null ? floatCol.getBinaryData() : floatCol.values().toArray();
            ByteArray byteArray = encode(categoryName, cifColumn.getColumnName(), EncodedDataFactory.float64Array(array));
            return encodeColumn(cifColumn, byteArray);
        } else if (cifColumn instanceof IntColumn) {
            IntColumn intCol = (IntColumn) cifColumn;
            int[] array = intCol.getBinaryData() != null ? intCol.getBinaryData() : intCol.values().toArray();
            ByteArray byteArray = encode(categoryName, cifColumn.getColumnName(), EncodedDataFactory.int32Array(array));
            return encodeColumn(cifColumn, byteArray);
        } else {
            StrColumn strCol = (StrColumn) cifColumn;
            String[] array = strCol.getBinaryData() != null ? strCol.getBinaryData() : strCol.values().toArray(String[]::new);
            ByteArray byteArray = EncodedDataFactory.stringArray(array).encode(new StringArrayEncoding());
            return encodeColumn(cifColumn, byteArray);
        }
    }
	
    private ByteArray encode(String categoryName, String columnName, Float64Array column) {
        Optional<EncodingStrategyHint> optional = options.getEncodingStrategyHint(categoryName, columnName);

        // if no hint given, auto-classify column
        EncodingStrategyHint hint = optional.orElseGet(column::classify);
        // if no encoding given, auto-classify encoding
        String encoding = hint.getEncoding() != null ? hint.getEncoding() : Classifier.classify(column).getEncoding();
        // if multiplier/precision not given, auto-classify only precision
        EncodingStrategyHint precisionClassification = Classifier.classifyPrecision(column);
        if ("byte".equals(precisionClassification.getEncoding())) {
            return column.encode(new ByteArrayEncoding(column.getType()));
        }
        int multiplier = getMultiplier(hint.getPrecision() != null ? hint.getPrecision() : precisionClassification.getPrecision());

        Int32Array fixedPoint = column.encode(new FixedPointEncoding(multiplier));
        return Classifier.encode(fixedPoint, encoding);
    }

    private static int getMultiplier(int mantissaDigits) {
        int m = 1;
        for (int i = 0; i < mantissaDigits; i++) {
            m *= 10;
        }
        return m;
    }

    private ByteArray encode(String categoryName, String columnName, Int32Array column) {
        Optional<String> optional = options.getEncodingStrategyHint(categoryName, columnName).map(EncodingStrategyHint::getEncoding);

        // if no hint given, auto-classify column
        String encoding = optional.orElseGet(() -> Classifier.classify(column).getEncoding());

        return Classifier.encode(column, encoding);
    }


    @SuppressWarnings("unchecked")
	private Map<String, Object> encodeColumn(Column cifField, ByteArray byteArray) {
        String name = cifField.getColumnName();

        // handle ValueKind and if needed create mask
        int[] maskArray = new int[cifField.getRowCount()];
        Uint8Array mask = EncodedDataFactory.uint8Array(maskArray);
        boolean allPresent = true;

        for (int row = 0; row < maskArray.length; row++) {
            ValueKind kind = cifField.getValueKind(row);

            if (kind != ValueKind.PRESENT) {
                maskArray[row] = (byte) kind.ordinal();
                allPresent = false;
            } else {
                maskArray[row] = (byte) ValueKind.PRESENT.ordinal();
            }
        }

        // default encoding
		Map<String, Object> encodedMap = (Map<String, Object>) Platform.getMap();
        encodedMap.put("encoding", byteArray.getEncoding().stream().map(this::wrap).toArray(Object[]::new));
        encodedMap.put("data", byteArray.getData());

        // encode mask
        Map<String, Object> maskData = (Map<String, Object>) Platform.getMap();
        if (!allPresent) {
            ByteArray maskRLE = mask.encode(new RunLengthEncoding()).encode(new ByteArrayEncoding());

            if (maskRLE.getData().length < mask.getData().length) {
                RunLengthEncoding rle = (RunLengthEncoding) maskRLE.getEncoding().get(0);

                Map<String, Object> encoding1 = (Map<String, Object>) Platform.getMap();
                encoding1.put("kind", "RunLength");
                encoding1.put("srcType", rle.getSrcType());
                encoding1.put("srcSize", rle.getSrcSize());

                Map<String, Object> encoding2 = (Map<String, Object>) Platform.getMap();
                encoding2.put("kind", "ByteArray");
                encoding2.put("type", 3);

                maskData.put("encoding", new Object[] { encoding1, encoding2 });
                maskData.put("data", maskRLE.getData());
            } else {
                ByteArray encodedMask = mask.encode(new ByteArrayEncoding(4));
                Map<String, Object> encoding = (Map<String, Object>) Platform.getMap();
                encoding.put("kind", "ByteArray");
                encoding.put("type", 4);
                maskData.put("encoding", new Object[] { encoding });
                maskData.put("data", encodedMask.getData());
            }
        }

        // create map
        Map<String, Object> map = (Map<String, Object>) Platform.getMap();
        map.put("name", name);
        map.put("data", encodedMap);
        map.put("mask", maskData);

        return map;
    }

    @SuppressWarnings("unchecked")
	private Map<String, Object> wrap(Object object) {
        try {
            Map<String, Object> out = (Map<String, Object>) Platform.getMap();
            for (Field field : object.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object content = field.get(object);
                if (content instanceof Map) {
                    content = wrap(content);
                } else if (content instanceof List) {
                    content = ((List<?>) content).stream()
                            .map(this::wrap)
                            .toArray(Object[]::new);
                } else if (isObjectArray(content)) {
                    content = Stream.of((Object[]) content)
                            .map(this::wrap)
                            .toArray(Object[]::new);
                }
                out.put(field.getName(), content);
            }
            return out;
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException("Could not convert Encoding to Map representation", e);
        }
    }

    private boolean isObjectArray(Object content) {
        if (content == null) {
            return false;
        } else if (!content.getClass().isArray()) {
            return false;
        } else {
            return !(content instanceof int[] || content instanceof double[] || content instanceof byte[] || content instanceof char[]);
        }
    }

}

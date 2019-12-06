package org.rcsb.cif.binary.codec;

import org.rcsb.cif.binary.data.EncodedData;
import org.rcsb.cif.binary.data.EncodedDataFactory;
import org.rcsb.cif.binary.encoding.*;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A library of codec implementations and provider to codec-specific constants such as encoder name, version, and
 * minimal version of the data to decode.
 */
public class Codec {
    public static final String CODEC_NAME = "ciftools-java";
    public static final String VERSION = "0.3.0";
    public static final String MIN_VERSION = "0.3";

    public static final ByteArrayCodec BYTE_ARRAY_CODEC = new ByteArrayCodec();
    public static final DeltaCodec DELTA_CODEC = new DeltaCodec();
    public static final FixedPointCodec FIXED_POINT_CODEC = new FixedPointCodec();
    public static final IntegerPackingCodec INTEGER_PACKING_CODEC = new IntegerPackingCodec();
    public static final IntervalQuantizationCodec INTERVAL_QUANTIZATION_CODEC = new IntervalQuantizationCodec();
    public static final RunLengthCodec RUN_LENGTH_CODEC = new RunLengthCodec();
    public static final StringArrayCodec STRING_ARRAY_CODEC = new StringArrayCodec();

    public static final MessagePackCodec MESSAGE_PACK_CODEC = new MessagePackCodec();

    /**
     * Decode an instance of {@link EncodedData} by traversing its encoding chain until the original data is restored.
     * @param encodedData the data to decode
     * @return the decoded data
     */
    @SuppressWarnings("unchecked")
    public static EncodedData decode(EncodedData encodedData) {
        EncodedData current = encodedData;

        while (current.hasNextDecodingStep()) {
            // pop the last element of this encoding chain, do so until chain is completely resolved
            Encoding encoding = (Encoding) current.getEncoding().removeLast();
            current = encoding.decode(current);
        }

        return current;
    }

    /**
     * Decode an instance of {@link EncodedData} by traversing its encoding chain until the original data is restored.
     * @param encodedData the map of data to decode
     * @return the decoded data
     */
    @SuppressWarnings("unchecked")
    public static Object decode(Map<String, Object> encodedData) {
        EncodedData current = EncodedDataFactory.byteArray((byte[]) encodedData.get("data"));
        LinkedList<Encoding> encodings = getListReversed((Object[]) encodedData.get("encoding"));
//        Object[] encodingMaps = (Object[]) encodedData.get("encoding");
//        LinkedList<Encoding> encodings = Stream.of(encodingMaps)
//                .map(Map.class::cast)
//                .map(Codec::wrap)
//                .collect(Collectors.toCollection(LinkedList::new));
//        Collections.reverse(encodings);

        for (Encoding encoding : encodings) {
            current = encoding.decode(current);
        }

        return current.getData();
    }

    /**
     * Convert a map representation of an encoding to a Java object.
     * @param encoding map representation of encoding
     * @return the concrete Encoding instance
     */
    private static Encoding wrap(Map encoding) {
//        String kind = getStringFromBytes(encoding.get("kind"));
    	byte[] kind = (byte[])encoding.get("kind");
        switch (kind[0]) {
            case 'B'://"ByteArray":
                return new ByteArrayEncoding(encoding);
            case 'F'://"FixedPoint":
                return new FixedPointEncoding(encoding);
            case 'R'://"RunLength":
                return new RunLengthEncoding(encoding);
            case 'D'://"Delta":
                return new DeltaEncoding(encoding);
            case 'I'://"IntervalQuantization":
                return (kind[8] == 'Q' ? new IntervalQuantizationEncoding(encoding) 
                		://"IntegerPacking":
                			new IntegerPackingEncoding(encoding));
            case 'S'://"StringArray":
                LinkedList<Encoding> outputEncoding = getList((Object[])encoding.get("dataEncoding"));
                LinkedList<Encoding> offsetEncoding = getList((Object[])encoding.get("offsetEncoding"));
                return new StringArrayEncoding(encoding, outputEncoding, offsetEncoding);
            default:
                throw new IllegalArgumentException("Unsupported Encoding kind: " + getStringFromBytes(kind));
        }
    }

    @SuppressWarnings("unchecked")
	private static LinkedList<Encoding> getList(Object[] a) {
		LinkedList<Encoding> list = new LinkedList<>();
		for (int i = 0, n = a.length; i < n; i++)
			list.add(wrap((Map<String, Object>) a[i]));
		return list;
	}

    @SuppressWarnings("unchecked")
	private static LinkedList<Encoding> getListReversed(Object[] a) {
		LinkedList<Encoding> list = new LinkedList<>();
		for (int i = a.length; --i >= 0;)
			list.add(wrap((Map<String, Object>) a[i]));
		return list;
	}

	public static String getStringFromBytes(byte[] bytes) {
		try {
			return new String(bytes, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}
    
}

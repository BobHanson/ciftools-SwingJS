package org.rcsb.cif.model;

import org.rcsb.cif.binary.codec.Codec;
import org.rcsb.cif.schema.Schema;

import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.rcsb.cif.schema.Schema.filter;
import static org.rcsb.cif.schema.Schema.toPackageName;

public abstract class BaseColumn implements Column {
    private final String name;
    final int rowCount;

    protected final boolean isText;
    private final String[] textData;

    private final boolean hasMask;
    protected final int[] mask;
    private final boolean defined;

    BaseColumn(String name) {
        this.name = name;
        this.rowCount = 0;

        this.isText = false;
        this.textData = new String[0];

        this.hasMask = false;
        this.mask = null;
        this.defined = false;
    }

    BaseColumn(String name, int rowCount, String[] data) {
        this.name = name;
        this.rowCount = rowCount;

        this.isText = true;
        this.textData = data;

        this.hasMask = false;
        this.mask = null;
        this.defined = true;
    }

    public static Column create(String catName, String fieldName, String data, int startToken, int endToken) {
        return create(catName, fieldName, data, new int[] { startToken }, new int[] { endToken });
    }

    /**
     * The creation method for a column based on binary (still encoded) data.
     * @param catName the category to retrieve this class from
     * @param fieldName the column name to create
     * @param data the raw string data to parse
     * @param startToken the collection of start indices which will be used to extract data
     * @param endToken the collection of end indices which will be used to extract data
     * @return the text column, ready to parse particular rows
     */
    @SuppressWarnings("unchecked")
    public static Column create(String catName, String fieldName, String data, int[] startToken, int[] endToken) {
        int rowCount = startToken.length;
        String[] textData = IntStream.range(0, rowCount)
                .mapToObj(i -> data.substring(startToken[i], endToken[i]))
                .toArray(String[]::new);
        if (filter(catName, fieldName)) {
            try {
                Class<? extends BaseColumn> column = (Class<? extends BaseColumn>) Class.forName(Schema.BASE_PACKAGE
                        + "." + toPackageName(catName) + "." + Schema.toClassName(fieldName));
                return column.getConstructor(String.class, int.class, String[].class)
                        .newInstance(fieldName, rowCount, textData);
            } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        } else {
            // cannot rely on schema, cross fingers that there is enough information to infer data type correctly
            if (isIntData(textData)) {
                return new IntColumn(fieldName, rowCount, textData);
            } else if (isFloatData(textData)) {
                return new FloatColumn(fieldName, rowCount, textData);
            } else {
                return new StrColumn(fieldName, rowCount, textData);
            }
        }
    }

    private static boolean isIntData(String[] textData) {
        return isNumberData(textData, BaseColumn::parsableAsInt);
    }

    private static boolean isNumberData(String[] textData, Predicate<String> predicate) {
        int undefinedCount = (int) Stream.of(textData)
                .filter(datum -> datum.isEmpty() || ".".equals(datum) || "?".equals(datum))
                .count();
        if (undefinedCount == textData.length) {
            return false;
        }

        return Stream.of(textData)
                .filter(datum -> !datum.isEmpty() && !".".equals(datum) && !"?".equals(datum))
                .allMatch(predicate);
    }

    private static boolean parsableAsInt(String datum) {
        try {
            Integer.parseInt(datum);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isFloatData(String[] textData) {
        return isNumberData(textData, BaseColumn::parsableAsFloat);
    }

    private static boolean parsableAsFloat(String datum) {
        try {
            Double.parseDouble(datum);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    String getTextData(int row) {
        return honorValueKind(textData[row]);
    }

    String honorValueKind(String value) {
        return (".".equals(value) || "?".equals(value)) ? "" : value;
    }

    @Override
    public String getStringData(int row) {
        String val = isText ? getTextData(row) : getBinaryStringData(row);
        try {
            return format(Double.parseDouble(val));
        } catch (NumberFormatException e) {
            // no number, return plain string
            return val;
        }
    }

    /**
     * Some columns (i.e. CartnX, CartnY, CartnZ, and Occupancy demand for more fine-grained over the values the report.
     * @param val the double value
     * @return the formatted String value
     */
    protected String format(double val) {
        return FLOAT_6.format(val);
    }

    protected abstract String getBinaryStringData(int row);

    BaseColumn(String name, int rowCount, int[] mask) {
        this.name = name;
        this.rowCount = rowCount;

        this.isText = false;
        this.textData = null;

        this.hasMask = mask != null && mask.length > 0;
        this.mask = mask;
        this.defined = true;
    }

    /**
     * The creation method for absent binary columns;
     * @param catName the category to retrieve this class from
     * @param fieldName the column name to create
     * @return an empty instance of this column
     */
    @SuppressWarnings("unchecked")
    public static Column create(String catName, String fieldName) {
        if (filter(catName, fieldName)) {
            try {
                Class<? extends BaseColumn> column = (Class<? extends BaseColumn>) Class.forName(Schema.BASE_PACKAGE
                        + "." + toPackageName(catName) + "." + Schema.toClassName(fieldName));
                return column.getConstructor(String.class)
                        .newInstance(fieldName);
            } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        } else {
            return new StrColumn(fieldName);
        }
    }

    /**
     * The creation method for a column based on binary (still encoded) data.
     * @param catName the category to retrieve this class from
     * @param fieldName the column name to create
     * @param encodedColumn a map encompassing all information needed to create this column
     * @return the decoded column
     */
    @SuppressWarnings("unchecked")
    public static Column create(String catName, String fieldName, Map<String, Object> encodedColumn) {
        Object binaryData = Codec.decode((Map<String, Object>) encodedColumn.get("data"));
        boolean isIntArray = binaryData instanceof int[];
        boolean isDoubleArray = binaryData instanceof double[];
        int rowCount = isIntArray ? ((int[]) binaryData).length : isDoubleArray ? ((double[]) binaryData).length : ((String[]) binaryData).length;
        boolean hasMask = encodedColumn.containsKey("mask") && encodedColumn.get("mask") != null && !((Map) encodedColumn.get("mask")).isEmpty();
        int[] mask = hasMask ? (int[]) Codec.decode((Map<String, Object>) encodedColumn.get("mask")) : null;


        if (filter(catName, fieldName)) {
            try {
                    Class<? extends BaseColumn> column = (Class<? extends BaseColumn>) Class.forName(Schema.BASE_PACKAGE
                            + "." + toPackageName(catName) + "." + Schema.toClassName(fieldName));
                    return column.getConstructor(String.class, int.class, Object.class, int[].class)
                            .newInstance(fieldName, rowCount, binaryData, mask);
            } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        } else {
            if (isIntArray) {
                return new IntColumn(fieldName, rowCount, binaryData, mask);
            } else if (isDoubleArray) {
                return new FloatColumn(fieldName, rowCount, binaryData, mask);
            } else {
                return new StrColumn(fieldName, rowCount, binaryData, mask);
            }
        }
    }

    @Override
    public String getColumnName() {
        return name;
    }

    @Override
    public int getRowCount() {
        return rowCount;
    }

    @Override
    public ValueKind getValueKind(int row) {
        if (isText) {
            String value = textData[row];
            if (value.isEmpty() || ".".equals(value)) {
                return ValueKind.NOT_PRESENT;
            } else if ("?".equals(value)) {
                return ValueKind.UNKNOWN;
            } else {
                return ValueKind.PRESENT;
            }
        }

        if (!hasMask) {
            return ValueKind.PRESENT;
        }
        return ValueKind.values()[mask[row]];
    }

    protected static final DecimalFormat FLOAT_2 = new DecimalFormat("0.00");
    protected static final DecimalFormat FLOAT_3 = new DecimalFormat("0.000");
    private static final DecimalFormat FLOAT_6 = new DecimalFormat("0.######");

    @Override
    public boolean isDefined() {
        return defined;
    }
}

package org.rcsb.cif.model;

import org.rcsb.cif.parser.BinaryDecoder;
import org.rcsb.cif.parser.ParsingException;

import java.util.Map;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BinaryCifField implements CifField {
    private final int[] mask;
    private final int[] intData;
    private final double[] doubleData;
    private final String[] stringData;
    private final int rowCount;
    private final boolean isNumeric;
    private final DataType dataType;
    private final boolean hasMask;

    public enum DataType {
        INT,
        DOUBLE,
        STRING
    }

    // TODO throwing constructors are not that nice
    @SuppressWarnings("unchecked")
    public BinaryCifField(Map<String, Object> encodedColumn) throws ParsingException {
        this.hasMask = encodedColumn.containsKey("mask") && encodedColumn.get("mask") != null;
        this.mask = hasMask ? (int[]) BinaryDecoder.decode((Map<String, Object>) encodedColumn.get("mask")) : null;
        Object data = BinaryDecoder.decode((Map<String, Object>) encodedColumn.get("data"));

        // decide data type and store in a type-safe way
        if (data instanceof int[]) {
            this.intData = (int[]) data;
            this.doubleData = null;
            this.stringData = null;
            this.dataType = DataType.INT;
            this.rowCount = intData.length;
        } else if (data instanceof double[]) {
            this.intData = null;
            this.doubleData = (double[]) data;
            this.stringData = null;
            this.dataType = DataType.DOUBLE;
            this.rowCount = doubleData.length;
        } else if (data instanceof String[]) {
            this.intData = null;
            this.doubleData = null;
            this.stringData = (String[]) data;
            this.dataType = DataType.STRING;
            this.rowCount = stringData.length;
        } else {
            throw new ParsingException("novel data type: " + data.getClass().getSimpleName());
        }
        this.isNumeric = dataType == DataType.INT || dataType == DataType.DOUBLE;
    }

    public boolean isNumeric() {
        return isNumeric;
    }

    public DataType getDataType() {
        return dataType;
    }

    @Override
    public boolean isDefined() {
        return true;
    }

    @Override
    public int getRowCount() {
        return rowCount;
    }

    @Override
    public String getString(int row) {
        if (dataType == DataType.STRING) {
            return !hasMask || mask[row] == ValueKind.PRESENT.ordinal() ? stringData[row] : "";
        }
        return !hasMask || mask[row] == ValueKind.PRESENT.ordinal() ?
                String.valueOf(dataType == DataType.INT ? intData[row] : doubleData[row]) : "";
    }

    @Override
    public int getInt(int row) {
        if (isNumeric) {
            return dataType == DataType.INT ? intData[row] : (int) doubleData[row];
        }
        return Integer.parseInt(stringData[row]);
    }

    @Override
    public double getDouble(int row) {
        if (isNumeric) {
            return dataType == DataType.INT ? intData[row] : doubleData[row];
        }
        return Double.parseDouble(stringData[row]);
    }

    @Override
    public ValueKind getValueKind(int row) {
        if (hasMask) {
            return ValueKind.PRESENT;
        }
        return ValueKind.values()[mask[row]];
    }

    @Override
    public boolean areEqualByValue(int rowA, int rowB) {
        switch (dataType) {
            case INT:
                return intData[rowA] == intData[rowB];
            case DOUBLE:
                // TODO address rounding errors?
                return doubleData[rowA] == doubleData[rowB];
            case STRING:
                return stringData[rowA].equals(stringData[rowB]);
            default:
                throw new RuntimeException("Unrecognized data type.");
        }
    }

    @Override
    public Stream<String> strings() {
        return IntStream.range(0, rowCount)
                .mapToObj(this::getString);
    }

    @Override
    public IntStream ints() {
        if (isNumeric) {
            return dataType == DataType.INT ? IntStream.of(intData) :
                    DoubleStream.of(doubleData).mapToInt(d -> (int) d);
        }
        return Stream.of(stringData)
                .mapToInt(Integer::parseInt);
    }

    @Override
    public DoubleStream doubles() {
        if (isNumeric) {
            return dataType == DataType.INT ? IntStream.of(intData).mapToDouble(i -> i) :
                    DoubleStream.of(doubleData);
        }
        return Stream.of(stringData)
                .mapToDouble(Double::parseDouble);
    }
}

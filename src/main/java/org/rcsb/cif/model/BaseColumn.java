package org.rcsb.cif.model;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public abstract class BaseColumn implements Column {

	protected int type = Column.COLUMN_TYPE_STRING;
	
	protected final String name;
    protected final int rowCount;

    final boolean isText;
    private final String textData;
    private final int[] startToken;
    private final int[] endToken;

    protected final boolean hasMask;
    protected final int[] mask;
    private final boolean defined;

    BaseColumn(String name) {
        this.name = name;
        this.rowCount = 0;

        this.isText = false;
        this.textData = null;
        this.startToken = null;
        this.endToken = null;

        this.hasMask = false;
        this.mask = null;
        this.defined = false;
    }

    BaseColumn(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        this.name = name;
        this.rowCount = rowCount;

        this.isText = true;
        this.textData = data;
        this.startToken = startToken;
        this.endToken = endToken;

        this.hasMask = false;
        this.mask = null;
        this.defined = true;
    }

    BaseColumn(String name, int rowCount, int[] mask) {
        this.name = name;
        this.rowCount = rowCount;

        this.isText = false;
        this.textData = null;
        this.startToken = null;
        this.endToken = null;

        this.hasMask = mask != null && mask.length > 0;
        this.mask = mask;
        this.defined = true;
    }

    @Override
    public int getType() {
    	return type;
    }
    
    String getTextData(int row) {
        return honorValueKind(textData.substring(startToken[row], endToken[row]));
    }

    String honorValueKind(String value) {
        return (".".equals(value) || "?".equals(value)) ? "" : value;
    }

    @Override
    public String getStringData(int row) {
        // if this is a FloatColumn, ensure formatting
        if (type == Column.COLUMN_TYPE_FLOAT) {
            return ((FloatColumn) this).format(row);
        } else {
            return isText ? getTextData(row) : getBinaryStringData(row);
        }
    }

    protected abstract String getBinaryStringData(int row);

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
            String value = textData.substring(startToken[row], endToken[row]);
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

    @Override
    public Stream<ValueKind> valueKinds() {
        return IntStream.range(0, rowCount)
                .mapToObj(this::getValueKind);
    }

    @Override
    public boolean isDefined() {
        return defined;
    }

    abstract public Object getUnmaskedData();

	protected String getRawTextData(int i) {
		return textData.substring(startToken[i], endToken[i]);
	}

}

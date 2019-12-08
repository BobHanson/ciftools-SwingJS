package org.rcsb.cif.model;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Type-safe column which contains <code>float</code> values as entries.
 */
public class FloatColumn extends BaseColumn {
    private final double[] binaryData;

    public FloatColumn(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
        type = Column.COLUMN_TYPE_FLOAT;
        this.binaryData = null;
    }

    public FloatColumn(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, mask);
        type = Column.COLUMN_TYPE_FLOAT;
        double[] tmpData;
        if (data instanceof double[]) {
            tmpData = (double[]) data;
        } else {
        	// binaryCIF writing may have misassigned the data type
        	tmpData = new double[rowCount];
        	if (data instanceof int[]) {
        		for (int i = rowCount; --i >= 0;)
        			tmpData[i] = ((int[]) data)[i];
        	} else {
        		for (int i = rowCount; --i >= 0;)
        			tmpData[i] = (mask == null || mask[i] == Column.PRESENT ? Double.parseDouble(((String[]) data)[i]) : 0);
        	}
        }
        binaryData = tmpData;
//        binaryData = (
//        		data instanceof double[] ? (double[]) data
//        		: data instanceof String[] ? Stream.of((String[]) data).mapToDouble(this::parseFloat).toArray()
//        		: IntStream.of((int[]) data).mapToDouble(i -> i).toArray());
    }

    public FloatColumn(String name) {
        super(name);
        type = Column.COLUMN_TYPE_FLOAT;
        this.binaryData = new double[0];
    }

    /**
     * Retrieve the double value at the given row index.
     * @param row the index to retrieve
     * @return the value - 0 if not present according to {@link #getValueKind(int)}
     */
    public double get(int row) {
        return isText ? parseFloat(getTextData(row)) : binaryData[row];
    }

    private double parseFloat(String text) {
        if (text.isEmpty() || ".".equals(text) || "?".equals(text)) {
            return 0;
        }
        return Double.parseDouble(text);
    }

    /**
     * Type-safe {@link Stream} of all elements.
     * @return a {@link DoubleStream} of values
     */
    public DoubleStream values() {
        return IntStream.range(0, rowCount)
                .mapToDouble(this::get);
    }

    @Override
    protected String getBinaryStringData(int row) {
        return String.valueOf(binaryData[row]);
    }

    /**
     * For internal use. Returns the decoded binary data which this column wraps. Returns null for text columns.
     * @return the <code>double[]</code> data
     */
    public double[] getBinaryData() {
        return binaryData;
    }
    
    
    private double[] unmasked;

    /**
     * Uses Double.MIN_VALUE for not present and Double.MAX_VALUE for unknown
     */
	@Override
	public Object getUnmaskedData() {
		if (!isText && !hasMask)
			return binaryData;
		if (unmasked != null)
			return unmasked;
		int n = rowCount;
		double[] a = new double[n];
		if (isText) {
			for (int i = rowCount; --i >= 0;) {
				String val = getRawTextData(i);
				switch (val) {
				case ".": // not present
					a[i] = Double.MIN_VALUE;
					break;
				case "":  // could be a problem
				case "?": // unknown
					a[i] = Double.MAX_VALUE;
					break;
				default:
					a[i] = Double.parseDouble(val);
				}
			}
		} else {
			for (int i = n; --i >= 0;) {
				switch (mask[i]) {
				case Column.PRESENT:
					a[i] = binaryData[i];
					break;
				case Column.NOT_PRESENT:
					a[i] = Double.MIN_VALUE;
					break;
				case Column.UNKNOWN:
					a[i] = Double.MAX_VALUE;
					break;
				}
			}
		}
		return unmasked = a;
	}

	public String toString() {
		return Arrays.toString((double[])getUnmaskedData());
	}

    private static final DecimalFormat FLOAT_2 = new DecimalFormat("0.00");
    private static final DecimalFormat FLOAT_3 = new DecimalFormat("0.000");
    private static final DecimalFormat FLOAT_6 = new DecimalFormat("0.######");

    /**
     * Some columns (i.e. CartnX, CartnY, CartnZ, and Occupancy demand for more fine-grained over the values they report.
     * @param val the double value
     * @return the formatted String value
     */
    protected String format(int row) {
    	double val = ((FloatColumn) this).get(row);
    	switch (name.toLowerCase()) {
    	case "cartn_x":
    	case "cartn_y":
    	case "cartn_z":
           return FLOAT_3.format(val);
    	case "occupancy":	
            return FLOAT_2.format(val);
        default:
            return FLOAT_6.format(val);
    	}
    }


}

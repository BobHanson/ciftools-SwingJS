package org.rcsb.cif.model;

import java.util.Arrays;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Type-safe {@link Column} which contains <code>int</code> values as entries.
 */
public class IntColumn extends BaseColumn {
    private final int[] binaryData;

    public IntColumn(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
        type = Column.COLUMN_TYPE_INT;
        this.binaryData = null;
    }

    public IntColumn(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, mask);
        int[] tmpData;
        type = Column.COLUMN_TYPE_INT;
        if (data instanceof int[]) {
            tmpData = (int[]) data;
        } else {
        	// binaryCIF writing may have misassigned the data type
        	tmpData = new int[rowCount];
        	if (data instanceof String[]) {
        		
        		for (int i = rowCount; --i >= 0;)
        			tmpData[i] = (hasMask && mask[i] != Column.PRESENT ? 0 : Integer.parseInt(((String[]) data)[i]));
        	} else {
        		for (int i = rowCount; --i >= 0;)
        			tmpData[i] = (int) ((double[]) data)[i];
        	}
        }
        	// note that casting with (String[]) fails in Java but not in JavaScript      
//        try {
//            tmpData = (int[]) data;
//        } catch (ClassCastException e) {
//            tmpData = data instanceof String[] ? Stream.of((String[]) data).mapToInt(this::parseInt).toArray() :
//                    DoubleStream.of((double[]) data).mapToInt(d -> (int) d).toArray();
//        }
        this.binaryData = tmpData;
    }

    public IntColumn(String name) {
        super(name);
        type = Column.COLUMN_TYPE_INT;
        this.binaryData = new int[0];
    }

    /**
     * Retrieve the <code>int</code> value at the given row index.
     * @param row the index to retrieve
     * @return the value - 0 if not present according to {@link #getValueKind(int)}
     */
    public int get(int row) {
        return isText ? parseInt(getTextData(row)) : binaryData[row];
    }

    private int parseInt(String text) {
        if (text.isEmpty() || ".".equals(text) || "?".equals(text)) {
            return 0;
        }
        return Integer.parseInt(text);
    }

    /**
     * Type-safe {@link Stream} of all elements.
     * @return a {@link IntStream} of values
     */
    public IntStream values() {
        return IntStream.range(0, rowCount)
                .map(this::get);
    }

    @Override
    protected String getBinaryStringData(int row) {
        return String.valueOf(binaryData[row]);
    }
    
    /**
     * For internal use. Returns the decoded binary data which this column wraps. Returns <code>null</code> for text
     * columns.
     * @return the <code>int[]</code> data
     */
    public int[] getBinaryData() {
        return binaryData;
    }
    
    private int[] unmasked;
    
    /**
     * Uses Integer.MIN_VALUE for not present and Integer.MAX_VALUE for unknown
     */
	@Override
	public Object getUnmaskedData() {
		if (!isText && !hasMask)
			return binaryData;
		if (unmasked != null)
			return unmasked;
		int[] a = new int[rowCount];
		if (isText) {
			for (int i = rowCount; --i >= 0;) {
				String val = getRawTextData(i);
				switch (val) {
				case ".":
					a[i] = Integer.MIN_VALUE;
					break;
				case "":
				case "?":
					a[i] = Integer.MAX_VALUE;
					break;
				default:
					a[i] = Integer.parseInt(val);
				}
			}
		} else {
			for (int i = rowCount; --i >= 0;) {
				switch (mask[i]) {
				case Column.PRESENT:
					a[i] = binaryData[i];
					break;
				case Column.NOT_PRESENT: // .
					a[i] = Integer.MIN_VALUE;
					break;
				case Column.UNKNOWN:     // ?
					a[i] = Integer.MAX_VALUE;
					break;
				}
			}
		}
		return unmasked = a;
	}


	public String toString() {
		return Arrays.toString((int[])getUnmaskedData());
	}


}

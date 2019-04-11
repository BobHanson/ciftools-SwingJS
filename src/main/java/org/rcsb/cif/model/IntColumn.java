package org.rcsb.cif.model;

import java.util.Map;
import java.util.stream.IntStream;

public class IntColumn extends BaseCifColumn {
    public IntColumn(String data, int startToken, int endToken, String name) {
        super(data, startToken, endToken, name);
    }

    public IntColumn(String data, int[] startToken, int[] endToken, String name) {
        super(data, startToken, endToken, name);
    }

    public IntColumn(Map<String, Object> encodedColumn) {
        super(encodedColumn);
    }

    @Override
    public Integer get(int row) {
        return isText ? getTextIntData(row) : getBinaryIntData(row);
    }

    @Override
    public IntStream values() {
        return intValues();
    }
}

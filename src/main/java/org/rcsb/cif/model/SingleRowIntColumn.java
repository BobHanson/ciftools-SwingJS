package org.rcsb.cif.model;

public class SingleRowIntColumn extends IntColumn implements SingleRowCifColumn {
    public SingleRowIntColumn(String name, int rowCount, String[] data) {
        super(name, rowCount, data);
    }

    public SingleRowIntColumn(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public int get() {
        return get(0);
    }

    public ValueKind getValueKind() {
        return getValueKind(0);
    }
}

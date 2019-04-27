package org.rcsb.cif.model.diffrnrefln;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class ScanRate extends SingleRowFloatColumn {
    public ScanRate(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public ScanRate(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public ScanRate(String name) {
        super(name);
    }
}

package org.rcsb.cif.model.emmap;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class CellA extends SingleRowFloatColumn {
    public CellA(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public CellA(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public CellA(String name) {
        super(name);
    }
}

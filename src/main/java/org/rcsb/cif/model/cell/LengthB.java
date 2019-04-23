package org.rcsb.cif.model.cell;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.schema.generator.SchemaGenerator")
public class LengthB extends SingleRowFloatColumn {
    public LengthB(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public LengthB(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public LengthB(String name) {
        super(name);
    }
}

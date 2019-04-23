package org.rcsb.cif.model.structconn;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.schema.generator.SchemaGenerator")
public class PdbxValueOrder extends EnumColumn {
    public PdbxValueOrder(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public PdbxValueOrder(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public PdbxValueOrder(String name) {
        super(name);
    }
}

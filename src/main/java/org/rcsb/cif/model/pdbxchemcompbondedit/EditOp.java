package org.rcsb.cif.model.pdbxchemcompbondedit;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class EditOp extends StrColumn {
    public EditOp(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public EditOp(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public EditOp(String name) {
        super(name);
    }
}

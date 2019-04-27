package org.rcsb.cif.model.pdbxconstruct;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class Organisation extends SingleRowStrColumn {
    public Organisation(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public Organisation(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public Organisation(String name) {
        super(name);
    }
}

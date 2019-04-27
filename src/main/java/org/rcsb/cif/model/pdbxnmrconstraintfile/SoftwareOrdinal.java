package org.rcsb.cif.model.pdbxnmrconstraintfile;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class SoftwareOrdinal extends IntColumn {
    public SoftwareOrdinal(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public SoftwareOrdinal(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public SoftwareOrdinal(String name) {
        super(name);
    }
}

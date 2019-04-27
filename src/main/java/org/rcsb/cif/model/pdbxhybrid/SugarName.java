package org.rcsb.cif.model.pdbxhybrid;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class SugarName extends StrColumn {
    public SugarName(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public SugarName(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public SugarName(String name) {
        super(name);
    }
}

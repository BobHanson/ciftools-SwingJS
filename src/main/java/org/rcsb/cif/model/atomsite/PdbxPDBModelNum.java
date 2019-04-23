package org.rcsb.cif.model.atomsite;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.schema.generator.SchemaGenerator")
public class PdbxPDBModelNum extends IntColumn {
    public PdbxPDBModelNum(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public PdbxPDBModelNum(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public PdbxPDBModelNum(String name) {
        super(name);
    }
}

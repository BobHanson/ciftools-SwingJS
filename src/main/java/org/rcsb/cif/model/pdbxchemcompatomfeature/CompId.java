package org.rcsb.cif.model.pdbxchemcompatomfeature;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class CompId extends StrColumn {
    public CompId(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public CompId(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public CompId(String name) {
        super(name);
    }
}

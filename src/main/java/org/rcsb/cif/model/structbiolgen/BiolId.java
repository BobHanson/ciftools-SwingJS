package org.rcsb.cif.model.structbiolgen;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class BiolId extends StrColumn {
    public BiolId(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public BiolId(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public BiolId(String name) {
        super(name);
    }
}

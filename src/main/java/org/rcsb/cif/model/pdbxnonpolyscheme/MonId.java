package org.rcsb.cif.model.pdbxnonpolyscheme;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.schema.generator.SchemaGenerator")
public class MonId extends StrColumn {
    public MonId(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public MonId(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public MonId(String name) {
        super(name);
    }
}

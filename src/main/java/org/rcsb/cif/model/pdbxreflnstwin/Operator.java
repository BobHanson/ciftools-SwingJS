package org.rcsb.cif.model.pdbxreflnstwin;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class Operator extends StrColumn {
    public Operator(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public Operator(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public Operator(String name) {
        super(name);
    }
}

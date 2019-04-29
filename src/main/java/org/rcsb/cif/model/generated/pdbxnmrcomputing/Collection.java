package org.rcsb.cif.model.generated.pdbxnmrcomputing;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class Collection extends SingleRowStrColumn {
    public Collection(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public Collection(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public Collection(String name) {
        super(name);
    }
}
package org.rcsb.cif.model.diffrnorientrefln;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class IndexK extends SingleRowIntColumn {
    public IndexK(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public IndexK(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public IndexK(String name) {
        super(name);
    }
}

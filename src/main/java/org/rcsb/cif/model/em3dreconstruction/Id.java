package org.rcsb.cif.model.em3dreconstruction;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class Id extends SingleRowStrColumn {
    public Id(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public Id(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public Id(String name) {
        super(name);
    }
}

package org.rcsb.cif.model.atomtype;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class RadiusBond extends FloatColumn {
    public RadiusBond(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public RadiusBond(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public RadiusBond(String name) {
        super(name);
    }
}

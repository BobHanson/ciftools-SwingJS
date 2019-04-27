package org.rcsb.cif.model.exptlcrystalgrow;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class PressureEsd extends SingleRowFloatColumn {
    public PressureEsd(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public PressureEsd(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public PressureEsd(String name) {
        super(name);
    }
}

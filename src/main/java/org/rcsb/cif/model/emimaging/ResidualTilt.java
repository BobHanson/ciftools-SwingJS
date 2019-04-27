package org.rcsb.cif.model.emimaging;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class ResidualTilt extends SingleRowFloatColumn {
    public ResidualTilt(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public ResidualTilt(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public ResidualTilt(String name) {
        super(name);
    }
}

package org.rcsb.cif.model.reflnsclass;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class RFactorGt extends FloatColumn {
    public RFactorGt(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public RFactorGt(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public RFactorGt(String name) {
        super(name);
    }
}

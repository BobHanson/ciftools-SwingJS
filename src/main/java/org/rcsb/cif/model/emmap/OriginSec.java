package org.rcsb.cif.model.emmap;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class OriginSec extends SingleRowIntColumn {
    public OriginSec(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public OriginSec(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public OriginSec(String name) {
        super(name);
    }
}

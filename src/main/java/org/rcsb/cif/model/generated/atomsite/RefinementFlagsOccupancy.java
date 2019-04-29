package org.rcsb.cif.model.generated.atomsite;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class RefinementFlagsOccupancy extends StrColumn {
    public RefinementFlagsOccupancy(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public RefinementFlagsOccupancy(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public RefinementFlagsOccupancy(String name) {
        super(name);
    }
}
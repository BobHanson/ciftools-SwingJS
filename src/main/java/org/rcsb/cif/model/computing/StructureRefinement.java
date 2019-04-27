package org.rcsb.cif.model.computing;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class StructureRefinement extends SingleRowStrColumn {
    public StructureRefinement(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public StructureRefinement(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public StructureRefinement(String name) {
        super(name);
    }
}

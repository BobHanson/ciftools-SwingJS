package org.rcsb.cif.model.entitypoly;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.schema.generator.SchemaGenerator")
public class PdbxTargetIdentifier extends StrColumn {
    public PdbxTargetIdentifier(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public PdbxTargetIdentifier(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public PdbxTargetIdentifier(String name) {
        super(name);
    }
}

package org.rcsb.cif.model.emassembly;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class MolWtTheo extends SingleRowFloatColumn {
    public MolWtTheo(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public MolWtTheo(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public MolWtTheo(String name) {
        super(name);
    }
}

package org.rcsb.cif.model.generated.computing;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class PdbxDataReductionDs extends SingleRowStrColumn {
    public PdbxDataReductionDs(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public PdbxDataReductionDs(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public PdbxDataReductionDs(String name) {
        super(name);
    }
}

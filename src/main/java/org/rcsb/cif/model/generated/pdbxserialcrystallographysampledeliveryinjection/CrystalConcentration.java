package org.rcsb.cif.model.generated.pdbxserialcrystallographysampledeliveryinjection;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class CrystalConcentration extends SingleRowFloatColumn {
    public CrystalConcentration(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public CrystalConcentration(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public CrystalConcentration(String name) {
        super(name);
    }
}

package org.rcsb.cif.model.pdbxsolnscatter;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class SourceBeamlineInstrument extends StrColumn {
    public SourceBeamlineInstrument(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public SourceBeamlineInstrument(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public SourceBeamlineInstrument(String name) {
        super(name);
    }
}

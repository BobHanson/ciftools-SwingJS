package org.rcsb.cif.model.journal;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class CodenCambridge extends SingleRowStrColumn {
    public CodenCambridge(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public CodenCambridge(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public CodenCambridge(String name) {
        super(name);
    }
}

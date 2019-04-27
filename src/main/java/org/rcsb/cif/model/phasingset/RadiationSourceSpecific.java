package org.rcsb.cif.model.phasingset;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class RadiationSourceSpecific extends SingleRowStrColumn {
    public RadiationSourceSpecific(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public RadiationSourceSpecific(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public RadiationSourceSpecific(String name) {
        super(name);
    }
}

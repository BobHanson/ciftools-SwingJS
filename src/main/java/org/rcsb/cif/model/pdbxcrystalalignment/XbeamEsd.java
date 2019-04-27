package org.rcsb.cif.model.pdbxcrystalalignment;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class XbeamEsd extends FloatColumn {
    public XbeamEsd(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public XbeamEsd(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public XbeamEsd(String name) {
        super(name);
    }
}

package org.rcsb.cif.model.emdepui;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class SameTitleAsPdb extends SingleRowStrColumn {
    public SameTitleAsPdb(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public SameTitleAsPdb(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public SameTitleAsPdb(String name) {
        super(name);
    }
}

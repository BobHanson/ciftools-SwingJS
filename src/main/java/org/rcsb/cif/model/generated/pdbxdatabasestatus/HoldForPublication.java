package org.rcsb.cif.model.generated.pdbxdatabasestatus;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class HoldForPublication extends SingleRowStrColumn {
    public HoldForPublication(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public HoldForPublication(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public HoldForPublication(String name) {
        super(name);
    }
}

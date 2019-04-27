package org.rcsb.cif.model.audit;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class UpdateRecord extends SingleRowStrColumn {
    public UpdateRecord(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public UpdateRecord(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public UpdateRecord(String name) {
        super(name);
    }
}

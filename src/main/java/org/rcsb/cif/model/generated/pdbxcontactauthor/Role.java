package org.rcsb.cif.model.generated.pdbxcontactauthor;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class Role extends SingleRowStrColumn {
    public Role(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public Role(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public Role(String name) {
        super(name);
    }
}
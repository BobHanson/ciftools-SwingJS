package org.rcsb.cif.model.pdbxdepositgroupindex;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class AuthFileName extends StrColumn {
    public AuthFileName(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public AuthFileName(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public AuthFileName(String name) {
        super(name);
    }
}

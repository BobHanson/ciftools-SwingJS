package org.rcsb.cif.model.database;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class CodeCSD extends StrColumn {
    public CodeCSD(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public CodeCSD(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public CodeCSD(String name) {
        super(name);
    }
}

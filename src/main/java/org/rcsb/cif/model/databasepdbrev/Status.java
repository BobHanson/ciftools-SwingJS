package org.rcsb.cif.model.databasepdbrev;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class Status extends StrColumn {
    public Status(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public Status(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public Status(String name) {
        super(name);
    }
}

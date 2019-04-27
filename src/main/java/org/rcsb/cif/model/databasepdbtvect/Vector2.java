package org.rcsb.cif.model.databasepdbtvect;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class Vector2 extends FloatColumn {
    public Vector2(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public Vector2(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public Vector2(String name) {
        super(name);
    }
}

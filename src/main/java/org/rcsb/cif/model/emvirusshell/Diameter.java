package org.rcsb.cif.model.emvirusshell;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class Diameter extends FloatColumn {
    public Diameter(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public Diameter(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public Diameter(String name) {
        super(name);
    }
}

package org.rcsb.cif.model.generated.software;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class Hardware extends StrColumn {
    public Hardware(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public Hardware(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public Hardware(String name) {
        super(name);
    }
}

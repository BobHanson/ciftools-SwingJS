package org.rcsb.cif.model.emimaging;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class Mode extends SingleRowStrColumn {
    public Mode(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public Mode(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public Mode(String name) {
        super(name);
    }
}

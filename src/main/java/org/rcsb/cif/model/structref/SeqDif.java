package org.rcsb.cif.model.structref;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class SeqDif extends StrColumn {
    public SeqDif(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public SeqDif(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public SeqDif(String name) {
        super(name);
    }
}

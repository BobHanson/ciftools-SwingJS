package org.rcsb.cif.model.pdbxnmrspectralpeaklist;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class Label extends StrColumn {
    public Label(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public Label(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public Label(String name) {
        super(name);
    }
}

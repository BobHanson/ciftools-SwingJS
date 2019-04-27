package org.rcsb.cif.model.emadmin;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class ReplaceExistingEntryFlag extends SingleRowStrColumn {
    public ReplaceExistingEntryFlag(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public ReplaceExistingEntryFlag(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public ReplaceExistingEntryFlag(String name) {
        super(name);
    }
}

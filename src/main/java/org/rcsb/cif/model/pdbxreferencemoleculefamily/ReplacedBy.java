package org.rcsb.cif.model.pdbxreferencemoleculefamily;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class ReplacedBy extends SingleRowStrColumn {
    public ReplacedBy(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public ReplacedBy(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public ReplacedBy(String name) {
        super(name);
    }
}

package org.rcsb.cif.model.structconf;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.schema.Schema")
public class PdbxEndPDBInsCode extends StrColumn {
    public PdbxEndPDBInsCode(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public PdbxEndPDBInsCode(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public PdbxEndPDBInsCode(String name) {
        super(name);
    }
}

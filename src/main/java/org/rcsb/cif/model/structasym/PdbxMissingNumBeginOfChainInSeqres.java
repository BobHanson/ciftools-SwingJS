package org.rcsb.cif.model.structasym;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class PdbxMissingNumBeginOfChainInSeqres extends IntColumn {
    public PdbxMissingNumBeginOfChainInSeqres(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public PdbxMissingNumBeginOfChainInSeqres(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public PdbxMissingNumBeginOfChainInSeqres(String name) {
        super(name);
    }
}

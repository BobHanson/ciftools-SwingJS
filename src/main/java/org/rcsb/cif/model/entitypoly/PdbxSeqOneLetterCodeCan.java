package org.rcsb.cif.model.entitypoly;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.schema.generator.SchemaGenerator")
public class PdbxSeqOneLetterCodeCan extends StrColumn {
    public PdbxSeqOneLetterCodeCan(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public PdbxSeqOneLetterCodeCan(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public PdbxSeqOneLetterCodeCan(String name) {
        super(name);
    }
}

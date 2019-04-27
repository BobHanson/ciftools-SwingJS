package org.rcsb.cif.model.pdbxconnecttype;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class NdbTokenType extends StrColumn {
    public NdbTokenType(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public NdbTokenType(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public NdbTokenType(String name) {
        super(name);
    }
}

package org.rcsb.cif.model.pdbxreferencelinkedentity;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class LinkToCompId extends StrColumn {
    public LinkToCompId(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public LinkToCompId(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public LinkToCompId(String name) {
        super(name);
    }
}

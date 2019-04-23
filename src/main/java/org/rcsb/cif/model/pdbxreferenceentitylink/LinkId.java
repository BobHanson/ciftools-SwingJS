package org.rcsb.cif.model.pdbxreferenceentitylink;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.schema.generator.SchemaGenerator")
public class LinkId extends IntColumn {
    public LinkId(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public LinkId(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public LinkId(String name) {
        super(name);
    }
}

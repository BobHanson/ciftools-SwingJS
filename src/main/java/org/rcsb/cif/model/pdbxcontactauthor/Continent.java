package org.rcsb.cif.model.pdbxcontactauthor;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class Continent extends SingleRowStrColumn {
    public Continent(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public Continent(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public Continent(String name) {
        super(name);
    }
}

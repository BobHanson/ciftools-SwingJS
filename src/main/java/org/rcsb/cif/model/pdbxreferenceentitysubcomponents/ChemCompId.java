package org.rcsb.cif.model.pdbxreferenceentitysubcomponents;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class ChemCompId extends StrColumn {
    public ChemCompId(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public ChemCompId(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public ChemCompId(String name) {
        super(name);
    }
}

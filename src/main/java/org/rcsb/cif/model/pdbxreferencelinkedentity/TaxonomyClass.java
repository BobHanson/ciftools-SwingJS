package org.rcsb.cif.model.pdbxreferencelinkedentity;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class TaxonomyClass extends StrColumn {
    public TaxonomyClass(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public TaxonomyClass(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public TaxonomyClass(String name) {
        super(name);
    }
}

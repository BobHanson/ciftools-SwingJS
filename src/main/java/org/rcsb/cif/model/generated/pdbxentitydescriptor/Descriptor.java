package org.rcsb.cif.model.generated.pdbxentitydescriptor;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class Descriptor extends StrColumn {
    public Descriptor(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public Descriptor(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public Descriptor(String name) {
        super(name);
    }
}

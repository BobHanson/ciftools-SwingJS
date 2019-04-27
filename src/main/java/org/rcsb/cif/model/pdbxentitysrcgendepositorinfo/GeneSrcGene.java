package org.rcsb.cif.model.pdbxentitysrcgendepositorinfo;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class GeneSrcGene extends StrColumn {
    public GeneSrcGene(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public GeneSrcGene(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public GeneSrcGene(String name) {
        super(name);
    }
}

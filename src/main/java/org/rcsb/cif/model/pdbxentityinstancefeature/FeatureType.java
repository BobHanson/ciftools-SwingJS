package org.rcsb.cif.model.pdbxentityinstancefeature;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class FeatureType extends SingleRowStrColumn {
    public FeatureType(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public FeatureType(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public FeatureType(String name) {
        super(name);
    }
}

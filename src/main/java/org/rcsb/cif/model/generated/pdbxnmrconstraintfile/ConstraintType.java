package org.rcsb.cif.model.generated.pdbxnmrconstraintfile;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class ConstraintType extends StrColumn {
    public ConstraintType(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public ConstraintType(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public ConstraintType(String name) {
        super(name);
    }
}

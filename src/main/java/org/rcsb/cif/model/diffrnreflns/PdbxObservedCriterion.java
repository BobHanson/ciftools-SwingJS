package org.rcsb.cif.model.diffrnreflns;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class PdbxObservedCriterion extends FloatColumn {
    public PdbxObservedCriterion(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public PdbxObservedCriterion(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public PdbxObservedCriterion(String name) {
        super(name);
    }
}

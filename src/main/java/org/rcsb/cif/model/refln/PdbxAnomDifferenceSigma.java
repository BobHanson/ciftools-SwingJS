package org.rcsb.cif.model.refln;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class PdbxAnomDifferenceSigma extends FloatColumn {
    public PdbxAnomDifferenceSigma(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public PdbxAnomDifferenceSigma(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public PdbxAnomDifferenceSigma(String name) {
        super(name);
    }
}

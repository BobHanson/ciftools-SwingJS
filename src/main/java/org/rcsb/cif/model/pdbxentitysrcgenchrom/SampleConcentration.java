package org.rcsb.cif.model.pdbxentitysrcgenchrom;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class SampleConcentration extends FloatColumn {
    public SampleConcentration(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public SampleConcentration(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public SampleConcentration(String name) {
        super(name);
    }
}

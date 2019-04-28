package org.rcsb.cif.model.generated.pdbxentrydetails;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class NonpolymerDetails extends SingleRowStrColumn {
    public NonpolymerDetails(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public NonpolymerDetails(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public NonpolymerDetails(String name) {
        super(name);
    }
}

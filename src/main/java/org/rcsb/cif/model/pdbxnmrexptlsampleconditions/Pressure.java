package org.rcsb.cif.model.pdbxnmrexptlsampleconditions;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class Pressure extends StrColumn {
    public Pressure(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public Pressure(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public Pressure(String name) {
        super(name);
    }
}

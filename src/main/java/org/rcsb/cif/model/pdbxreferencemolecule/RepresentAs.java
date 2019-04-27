package org.rcsb.cif.model.pdbxreferencemolecule;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class RepresentAs extends StrColumn {
    public RepresentAs(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public RepresentAs(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public RepresentAs(String name) {
        super(name);
    }
}

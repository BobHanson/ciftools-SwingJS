package org.rcsb.cif.model.exptlcrystalgrowcomp;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class Conc extends StrColumn {
    public Conc(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public Conc(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public Conc(String name) {
        super(name);
    }
}

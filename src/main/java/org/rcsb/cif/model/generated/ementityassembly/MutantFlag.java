package org.rcsb.cif.model.generated.ementityassembly;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class MutantFlag extends SingleRowStrColumn {
    public MutantFlag(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public MutantFlag(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public MutantFlag(String name) {
        super(name);
    }
}

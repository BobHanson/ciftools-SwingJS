package org.rcsb.cif.model.generated.pdbxstructassemblydepositorinfo;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class MatrixFlag extends StrColumn {
    public MatrixFlag(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public MatrixFlag(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public MatrixFlag(String name) {
        super(name);
    }
}
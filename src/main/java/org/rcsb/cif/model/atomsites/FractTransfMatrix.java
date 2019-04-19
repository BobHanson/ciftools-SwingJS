package org.rcsb.cif.model.atomsites;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.schema.Schema")
public class FractTransfMatrix extends MatrixColumn {
    public FractTransfMatrix(String name, int rowCount, String[] data) {
        super(name, rowCount, data);
    }

    public FractTransfMatrix(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public FractTransfMatrix(String name) {
        super(name);
    }
}
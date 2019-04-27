package org.rcsb.cif.model.pdbxrefineauxfile;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class FileType extends SingleRowStrColumn {
    public FileType(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public FileType(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public FileType(String name) {
        super(name);
    }
}

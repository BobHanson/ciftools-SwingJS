package org.rcsb.cif.model.structasym;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.schema.Schema")
public class Id extends StrColumn {
    public Id(String name, int rowCount, String[] data) {
        super(name, rowCount, data);
    }

    public Id(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public Id(String name) {
        super(name);
    }
}
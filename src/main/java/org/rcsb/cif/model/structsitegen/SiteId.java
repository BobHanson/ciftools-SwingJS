package org.rcsb.cif.model.structsitegen;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.schema.Schema")
public class SiteId extends StrColumn {
    public SiteId(String name, int rowCount, String[] data) {
        super(name, rowCount, data);
    }

    public SiteId(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public SiteId(String name) {
        super(name);
    }
}

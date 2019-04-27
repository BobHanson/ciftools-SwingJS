package org.rcsb.cif.model.journal;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class PageLast extends SingleRowStrColumn {
    public PageLast(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public PageLast(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public PageLast(String name) {
        super(name);
    }
}

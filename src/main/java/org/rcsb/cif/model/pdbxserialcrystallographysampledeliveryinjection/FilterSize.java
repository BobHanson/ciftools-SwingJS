package org.rcsb.cif.model.pdbxserialcrystallographysampledeliveryinjection;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class FilterSize extends SingleRowFloatColumn {
    public FilterSize(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public FilterSize(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public FilterSize(String name) {
        super(name);
    }
}

package org.rcsb.cif.model.emmap;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class StatisticsStd extends SingleRowFloatColumn {
    public StatisticsStd(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public StatisticsStd(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public StatisticsStd(String name) {
        super(name);
    }
}

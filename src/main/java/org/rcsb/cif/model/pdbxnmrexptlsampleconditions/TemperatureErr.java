package org.rcsb.cif.model.pdbxnmrexptlsampleconditions;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class TemperatureErr extends FloatColumn {
    public TemperatureErr(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public TemperatureErr(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public TemperatureErr(String name) {
        super(name);
    }
}

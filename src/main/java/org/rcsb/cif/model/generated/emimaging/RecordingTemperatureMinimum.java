package org.rcsb.cif.model.generated.emimaging;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class RecordingTemperatureMinimum extends SingleRowFloatColumn {
    public RecordingTemperatureMinimum(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public RecordingTemperatureMinimum(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public RecordingTemperatureMinimum(String name) {
        super(name);
    }
}

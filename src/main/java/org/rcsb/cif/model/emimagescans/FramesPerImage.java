package org.rcsb.cif.model.emimagescans;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class FramesPerImage extends SingleRowIntColumn {
    public FramesPerImage(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public FramesPerImage(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public FramesPerImage(String name) {
        super(name);
    }
}

package org.rcsb.cif.model.generated.pdbxdepositionmessageinfo;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class ContentValue extends StrColumn {
    public ContentValue(String name, int rowCount, String data, int[] startToken, int[] endToken) {
        super(name, rowCount, data, startToken, endToken);
    }

    public ContentValue(String name, int rowCount, Object data, int[] mask) {
        super(name, rowCount, data, mask);
    }

    public ContentValue(String name) {
        super(name);
    }
}
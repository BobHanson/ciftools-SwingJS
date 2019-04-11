package org.rcsb.cif.model.generated.atomsite;

import org.rcsb.cif.model.*;

import java.util.Map;

public class AuthAtomId extends StrColumn {
    public AuthAtomId(String data, int startToken, int endToken, String name) {
        super(data, startToken, endToken, name);
    }

    public AuthAtomId(String data, int[] startToken, int[] endToken, String name) {
        super(data, startToken, endToken, name);
    }

    public AuthAtomId(Map<String, Object> encodedColumn) {
        super(encodedColumn);
    }
}

package org.rcsb.cif.binary.old.codec.encoding;

import org.rcsb.cif.binary.old.codec.CodecParameters;

public class IntegerPackingParameters implements CodecParameters {
    private final int byteCount;
    private final boolean unsigned;
    private final int srcSize;

    public IntegerPackingParameters(int byteCount, boolean unsigned, int srcSize) {
        this.byteCount = byteCount;
        this.unsigned = unsigned;
        this.srcSize = srcSize;
    }

    public int getByteCount() {
        return byteCount;
    }

    public boolean isUnsigned() {
        return unsigned;
    }

    public int getSrcSize() {
        return srcSize;
    }

    @Override
    public String getKind() {
        return "IntegerPackingCodec";
    }
}

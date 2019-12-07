package org.rcsb.cif.api;

import java.util.List;

public class BinaryFile implements CifFile {
    private final List<CifBlock> blocks;
    private final String version;
    private final String encoder;

    public BinaryFile(List<CifBlock> blocks, String version, String encoder) {
        this.blocks = blocks;
        this.version = version;
        this.encoder = encoder;
    }

    @Override
    public List<CifBlock> getBlocks() {
        return blocks;
    }

    /**
     * Reports the version of this {@link CifFile}.
     * @return the version <code>String</code>
     */
    public String getVersion() {
        return version;
    }

    /**
     * Reports the encoder which created this {@link CifFile}.
     * @return the encoder <code>String</code>
     */
    public String getEncoder() {
        return encoder;
    }
}

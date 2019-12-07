package org.rcsb.cif.generic;

import java.util.List;

import org.rcsb.cif.model.CifFile;

public class BinaryFileGeneric implements CifFileGeneric {

    private final List<CifBlockGeneric> blocks;
    private final String version;
    private final String encoder;

    public BinaryFileGeneric(List<CifBlockGeneric> blocks, String version, String encoder) {
        this.blocks = blocks;
        this.version = version;
        this.encoder = encoder;
    }
    
	@Override
	public List<CifBlockGeneric> getBlocks() {
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

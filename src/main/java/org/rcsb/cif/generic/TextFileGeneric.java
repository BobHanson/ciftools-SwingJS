package org.rcsb.cif.generic;

import java.util.List;

public class TextFileGeneric implements CifFileGeneric {
    private final List<CifBlockGeneric> blocks;

    @SuppressWarnings("unchecked")
	public TextFileGeneric(List<?> blocks) {
        this.blocks = (List<CifBlockGeneric>) blocks;
    }

    @Override
    public List<CifBlockGeneric> getBlocks() {
        return blocks;
    }
}

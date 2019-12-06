package org.rcsb.cif.generic;

import java.util.List;

public class TextFileGeneric implements CifFileGeneric {
    private final List<BlockGeneric> blocks;

    @SuppressWarnings("unchecked")
	public TextFileGeneric(List<?> blocks) {
        this.blocks = (List<BlockGeneric>) blocks;
    }

    @Override
    public List<BlockGeneric> getBlocks() {
        return blocks;
    }
}

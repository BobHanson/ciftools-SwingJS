package org.rcsb.cif.generic;

import java.util.List;

public class TextFile implements CifFile {
    private final List<CifBlock> blocks;

    @SuppressWarnings("unchecked")
	public TextFile(List<?> blocks) {
        this.blocks = (List<CifBlock>) blocks;
    }

    @Override
    public List<CifBlock> getBlocks() {
        return blocks;
    }
}

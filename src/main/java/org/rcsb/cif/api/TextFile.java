package org.rcsb.cif.api;

import java.util.List;

public class TextFile implements CifFile {
    private final List<CifBlock> blocks;

    public TextFile(List<CifBlock> blocks) {
        this.blocks = blocks;
    }

    @Override
    public List<CifBlock> getBlocks() {
        return blocks;
    }
}

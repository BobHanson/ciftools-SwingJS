package org.rcsb.cif.model;

import org.rcsb.cif.model.generated.CifBlock;

import java.util.List;

public interface CifFile {
    List<CifBlock> getBlocks();

    default CifBlock getFirstBlock() {
        return getBlocks().get(0);
    }
}

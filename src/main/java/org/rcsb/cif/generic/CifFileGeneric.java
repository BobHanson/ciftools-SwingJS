package org.rcsb.cif.generic;

import java.util.List;
import java.util.stream.Stream;

public interface CifFileGeneric {

	List<CifBlockGeneric> getBlocks();

    /**
     * Convenience method to access the first block.
     * @return the first block of this file
     */
    default CifBlockGeneric getFirstBlock() {
        return getBlocks().get(0);
    }

    /**
     * Convenience method to access all blocks as Stream.
     * @return a Stream of all blocks
     */
    default Stream<CifBlockGeneric> blocks() {
        return getBlocks().stream();
    }

}

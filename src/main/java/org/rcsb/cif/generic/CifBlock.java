package org.rcsb.cif.generic;

import java.util.List;

import org.rcsb.cif.model.Block;

public interface CifBlock extends Block {

	List<CifBlock> getSaveFrames();

}

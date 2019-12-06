package org.rcsb.cif.generic;

import java.util.List;

import org.rcsb.cif.model.BaseCategory;
import org.rcsb.cif.model.Block;

public interface BlockGeneric extends Block {

	List<BlockGeneric> getSaveFrames();

}

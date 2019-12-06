package org.rcsb.cif.text;

import java.util.List;
import java.util.Map;

import org.rcsb.cif.model.Block;
import org.rcsb.cif.model.Category;
import org.rcsb.cif.model.Column;

public interface TextReader {

	Block createBlock(Map<String, Category> categories, String header);

	void addBlock(Block block, List<?> dataBlocks, List<?> saveFrames);
	
	Category createCategory(String categoryName, Map<String, Column> columns);

}

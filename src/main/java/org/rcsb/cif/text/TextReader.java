package org.rcsb.cif.text;

import java.util.List;
import java.util.Map;

import org.rcsb.cif.model.Block;
import org.rcsb.cif.model.Category;
import org.rcsb.cif.model.Column;

/**
 * Return methods from TokenizerState to the reader.
 * 
 * @author hansonr
 *
 */
public interface TextReader {

	public Block createBlock(Map<String, Category> categories, String header);

	public void addBlock(Block block, List<?> dataBlocks, List<?> saveFrames);
	
	public Category createCategory(String categoryName, Map<String, Column> columns);

}

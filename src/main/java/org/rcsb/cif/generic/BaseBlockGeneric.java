package org.rcsb.cif.generic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.rcsb.cif.model.BaseCategory;
import org.rcsb.cif.model.Block;
import org.rcsb.cif.model.Category;
import org.rcsb.cif.model.ModelFactory;

/**
 * A simpler interface that does not require generation
 * 
 * @author hansonr
 *
 */
public class BaseBlockGeneric implements BlockGeneric {
	
    protected final Map<String, Category> categories;
    private final List<BlockGeneric> saveFrames;
    protected final String header;

    public BaseBlockGeneric(Map<String, Category> categories, String header) {
    	this(categories, header, new ArrayList<Block>());
	}

    @SuppressWarnings({ "unchecked" })
	public BaseBlockGeneric(Map<String, Category> categories, String header, List<?> saveFrames) {
        this.categories = categories;
        this.saveFrames =  (List<BlockGeneric>) saveFrames;
        this.header = header;
    }

	@Override
    public BaseCategory getCategory(String name) {
        // try to return category, if unknown and not present, return empty category
        return (BaseCategory) categories.computeIfAbsent(name, ModelFactory::createEmptyCategory);
    }

    public List<BlockGeneric> getSaveFrames() {
        return saveFrames;
    }

	@Override
	public String getBlockHeader() {
		return header;
	}

	@Override
	public List<String> getCategoryNames() {
		// TODO Auto-generated method stub
		return null;
	}


}

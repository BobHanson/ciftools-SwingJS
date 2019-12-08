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
public class BaseBlock implements CifBlock {
	
    protected final Map<String, Category> categories;
    private final List<CifBlock> saveFrames;
    protected final String header;

    public BaseBlock(Map<String, Category> categories, String header) {
    	this(categories, header, new ArrayList<Block>());
	}

    @SuppressWarnings({ "unchecked" })
	public BaseBlock(Map<String, Category> categories, String header, List<?> saveFrames) {
        this.categories = categories;
        this.saveFrames =  (List<CifBlock>) saveFrames;
        this.header = header;
    }

	@Override
    public BaseCategory getCategory(String name) {
        // try to return category, if unknown and not present, return empty category
		Category c = categories.get(name);
		if (c == null) {
			categories.put(name, c = ModelFactory.createEmptyCategory(name));
		}
        return (BaseCategory) c;
    }

    public List<CifBlock> getSaveFrames() {
        return saveFrames;
    }

	@Override
	public String getBlockHeader() {
		return header;
	}

	@Override
	public List<String> getCategoryNames() {
		return new ArrayList<String>(categories.keySet());
	}


}

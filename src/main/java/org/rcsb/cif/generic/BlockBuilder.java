package org.rcsb.cif.generic;

import org.rcsb.cif.model.BaseCategory;
import org.rcsb.cif.model.Builder;
import org.rcsb.cif.model.Category;
import org.rcsb.cif.model.ModelFactory;

/**
 * Builds a block in a {@link org.rcsb.cif.api.CifFile}, in most cases there is 1 block which contains all categories
 * of the file.
 */
public class BlockBuilder extends Builder.BlockBuilder {
    protected final CifBuilder parent;

    public BlockBuilder(String blockName, CifBuilder parent) {
    	super(blockName);
        this.parent = parent;
	}

	/**
     * Process all stored information and release a {@link CifBlock} instance. Use {@link BlockBuilder#leaveBlock()} if you
     * want to create a {@link CifFile} instance. Otherwise the block is aware of this parent (and vice versa).
     * @return the created Block
     */

    public CifBlock build() {
        return new BaseBlock(categories, "unknown");
    }

    /**
     * Dive into a category captured by this builder. This will create a generic category, for categories in the CIF
     * schema use the named enter functions.
     * @param categoryName the name of this category
     * @return the CategoryBuilder
     */
    public CategoryBuilder enterCategory(String categoryName) {
        return new CategoryBuilder(categoryName, this);
    }

    /**
     * Package-private function to process the information of children.
     * @param categoryBuilder the child builder to incorporate
     * @return this BlockBuilder instance
     */
    BlockBuilder digest(Category category) {
        categories.put(category.getCategoryName().toLowerCase(), category);
        return this;
    }

    /**
     * Process all stored information and return to the initial CifBuilder instance.
     * @return the parent builder
     */
    public CifBuilder leaveBlock() {
        if (parent == null) {
            throw new IllegalStateException("cannot leave block with undefined parent file");
        }
        return parent.digest(this);
    }
}

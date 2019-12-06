package org.rcsb.cif.model;

import java.util.List;
import java.util.stream.Stream;

public interface Block {
	
    /**
     * The names of all {@link Category} instances which will not return an empty {@link Category} when queried.
     * @return collection of all {@link Category} names
     */
    List<String> getCategoryNames();

    /**
     * The header of this {@link CifBlock}.
     * @return <code>String</code> of the header
     */
    String getBlockHeader();

    /**
     * Retrieve a particular {@link Category} by name.
     * @param name the category name
     * @return the corresponding {@link Category}, if none exists a instance of {@link BaseCategory} is returned as
     * proxy
     */
    Category getCategory(String name);

    /**
     * Convenience method to access all present {@link Category} names.
     * @return a stream of all registered categories
     */
    default Stream<String> categoryNames() {
        return getCategoryNames().stream();
    }

    /**
     * Traverses all present categories.
     * @return a Stream of all {@link Category} instances
     */
    default Stream<Category> categories() {
        return categoryNames().map(this::getCategory);
    }

}

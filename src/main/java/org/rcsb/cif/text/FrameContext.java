package org.rcsb.cif.text;

import java.util.Map;

import org.rcsb.cif.generic.Platform;
import org.rcsb.cif.model.Category;

class FrameContext {
    private final Map<String, Category> categories;

    @SuppressWarnings("unchecked")
	FrameContext() {
        this.categories = (Map<String, Category>) Platform.getMap();
    }

    Map<String, Category> getCategories() {
        return categories;
    }
}

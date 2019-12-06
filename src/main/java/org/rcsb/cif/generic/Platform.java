package org.rcsb.cif.generic;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.rcsb.cif.model.BaseCategory;
import org.rcsb.cif.model.Block;

public interface Platform {

	static Map<String, ?> getMap() {
    	/**
    	 * @j2sNative
    	 * 
    	 * Clazz.load("java.util.JSHashMap");
    	 *   return new java.util.JSHashMap();
    	 * 
    	 */
    	{
    		 return new LinkedHashMap<>();
    	}
	}

}

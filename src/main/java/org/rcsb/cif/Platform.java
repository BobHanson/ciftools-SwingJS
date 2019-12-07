package org.rcsb.cif;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.rcsb.cif.model.BaseCategory;
import org.rcsb.cif.model.Block;

/**
 * This class allows for variations that depend upon the platform. Here we use a
 * fast well-ordered JavaScript HashMap in place of java.util.LinkedHashMap,
 * which is considerably slower.
 * 
 * Note that JavaScript's Object may or may not be a true hash map, and it is
 * only available for String keys.
 * 
 * @author hansonr
 *
 */
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

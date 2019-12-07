package org.rcsb.cif.generic;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.rcsb.cif.CifOptions;
import org.rcsb.cif.Platform;
import org.rcsb.cif.binary.BinaryEncoder;
import org.rcsb.cif.binary.codec.Codec;
import org.rcsb.cif.model.Category;
import org.rcsb.cif.model.ModelFactory;

public class BinaryCifWriter {
    private final CifOptions options;

    public BinaryCifWriter(CifOptions options) {
        this.options = options;
    }

    public byte[] write(CifFile cifFile) {
        Map<String, Object> file = encodeFile(cifFile);
        return Codec.MESSAGE_PACK_CODEC.encode(file);
    }

	private Map<String, Object> encodeFile(CifFile cifFile) {
		// naming: uses cifEntity for original model and entity for the map
		// representation ready for MessagePack
		Map<String, Object> file = new LinkedHashMap<>();
		file.put("encoder", options.getEncoder());
		file.put("version", Codec.VERSION);
		Map[] blocks = new Map[cifFile.getBlocks().size()];
		int blockCount = 0;
		file.put("dataBlocks", blocks);

		BinaryEncoder encoder = new BinaryEncoder(options);
		for (CifBlock cifBlock : cifFile.getBlocks()) {
			@SuppressWarnings("unchecked")
			Map<String, Object> block = blocks[blockCount++] = (Map<String, Object>) Platform.getMap();
			String blockHeader = cifBlock.getBlockHeader();
			String header = (blockHeader != null ? blockHeader.replaceAll("[ \n\t]", "").toUpperCase() : "UNKNOWN");
			block.put("header", header);
			List<Category> categories = new ArrayList<Category>();
			for (String categoryName : cifBlock.getCategoryNames()) {
				if (!options.filterCategory(categoryName)) {
					continue;
				}
				Category cifCategory = cifBlock.getCategory(categoryName);
				int rowCount = cifCategory.getRowCount();
				if (rowCount == 0) {
					continue;
				}
				ModelFactory.ensureModelPropertiesLoaded(categoryName);
				List<Map<String, Object>> columns = new ArrayList<>();
				Map<String, Object> category = (Map<String, Object>) Platform.getMap();
				category.put("name", "_" + categoryName);
				category.put("rowCount", rowCount);
				for (String columnName : cifCategory.getColumnNamesEncoded()) {
					if (options.filterColumn(categoryName, columnName)) {
						columns.add(encoder.encodeColumn(categoryName, cifCategory.getColumn(columnName)));
					}
				}
				category.put("columns", columns.toArray());
				categories.add(cifCategory);
			}
			block.put("categories", categories.toArray());
		}
		return file;
	}

}

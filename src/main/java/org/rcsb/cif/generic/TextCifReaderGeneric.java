package org.rcsb.cif.generic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.rcsb.cif.CifOptions;
import org.rcsb.cif.ParsingException;
import org.rcsb.cif.model.BaseCategory;
import org.rcsb.cif.model.Block;
import org.rcsb.cif.model.Category;
import org.rcsb.cif.model.Column;
import org.rcsb.cif.text.TextReader;
import org.rcsb.cif.text.TokenizerState;

public class TextCifReaderGeneric implements TextReader {
	private final CifOptions options;

	public TextCifReaderGeneric(CifOptions options) {
		this.options = options;
	}

	public TextFileGeneric read(InputStream inputStream) throws ParsingException, IOException {
		String content = new BufferedReader(new InputStreamReader(inputStream)).lines()
				.collect(Collectors.joining("\n"));
		inputStream.close();
		return readText(content);
	}

	public TextFileGeneric readText(String data) throws ParsingException {
		if (data.isEmpty()) {
			throw new ParsingException("Cannot parse empty file.");
		}
		List<?> dataBlocks = new ArrayList<>();
		new TokenizerState((TextReader) this, data).getDataBlocks(dataBlocks);
		return new TextFileGeneric(dataBlocks);
	}

	@Override
	public Block createBlock(Map<String, Category> categories, String header) {
		return new BaseBlockGeneric(categories, header);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void addBlock(Block block, List<?> dataBlocks, List<?> saveFrames) {
		((List<BlockGeneric>)dataBlocks).add((BlockGeneric) block);
		((BaseBlockGeneric)block).getSaveFrames().addAll((Collection<? extends BlockGeneric>) saveFrames);
	}
	
    @Override
	public Category createCategory(String name, Map<String, Column> columns) {
		return new BaseCategory(name, columns);
	}
    

}

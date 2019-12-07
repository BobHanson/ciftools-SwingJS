package org.rcsb.cif.api;

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
import org.rcsb.cif.model.Block;
import org.rcsb.cif.model.Category;
import org.rcsb.cif.model.Column;
import org.rcsb.cif.model.ModelFactory;
import org.rcsb.cif.text.TextReader;
import org.rcsb.cif.text.TokenizerState;

public class TextCifReader implements TextReader {
	private final CifOptions options;

	public TextCifReader(CifOptions options) {
		this.options = options;
	}

	public TextFile read(InputStream inputStream) throws ParsingException, IOException {
		String content = new BufferedReader(new InputStreamReader(inputStream)).lines()
				.collect(Collectors.joining("\n"));
		inputStream.close();
		return readText(content);
	}
	
	@SuppressWarnings("unchecked")
	public TextFile readText(String data) throws ParsingException {
		if (data.isEmpty()) {
			throw new ParsingException("Cannot parse empty file.");
		}
		List<?> dataBlocks = new ArrayList<>();
		return new TextFile((List<CifBlock>) new TokenizerState(this, data).getDataBlocks(dataBlocks));
	}
	
	@Override
	public Block createBlock(Map<String, Category> categories, String name) {
		return new BaseBlock(categories, name);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addBlock(Block block, List<?> dataBlocks, List<?> saveFrames) {
		((List<CifBlock>)dataBlocks).add((CifBlock) block);
		((BaseBlock)block).getSaveFrames().addAll((Collection<? extends CifBlock>) saveFrames);
	}

	@Override
	public Category createCategory(String name, Map<String, Column> columns) {
		return ModelFactory.createCategoryText(name, columns);
	}
    
	

}

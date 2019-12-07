package org.rcsb.cif.api;

import org.rcsb.cif.CifOptions;
import org.rcsb.cif.model.Category;
import org.rcsb.cif.model.Column;
import org.rcsb.cif.model.FloatColumn;
import org.rcsb.cif.model.IntColumn;
import org.rcsb.cif.model.ModelFactory;
import org.rcsb.cif.model.ValueKind;
import org.rcsb.cif.text.TextWriter;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TextCifWriter extends TextWriter {

    public TextCifWriter(CifOptions options) {
    	super(options);
    }

    public byte[] write(CifFile cifFile) {
    	return writeBlocks(cifFile.getBlocks());
    }

}

package org.rcsb.cif.generic;

import org.rcsb.cif.io.CifOptions;
import org.rcsb.cif.text.TextWriter;

public class TextCifWriter extends TextWriter {

    public TextCifWriter(CifOptions options) {
    	super(options);
    }

    public byte[] write(CifFile cifFile) {
    	return writeBlocks(cifFile.getBlocks());
    }

}

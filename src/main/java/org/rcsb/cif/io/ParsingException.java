package org.rcsb.cif.io;

/**
 * Indicates that Cif parsing failed.
 */
@SuppressWarnings("serial")
public class ParsingException extends RuntimeException {
    public ParsingException(String message) {
        super(message);
    }

    public ParsingException(String message, int line) {
        super(message + " [" + line + "]");
    }

    public ParsingException(String message, Exception e) {
        super(message, e);
    }
}

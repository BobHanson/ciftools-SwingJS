package org.rcsb.cif.text;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.rcsb.cif.ParsingException;
import org.rcsb.cif.generic.Platform;
import org.rcsb.cif.model.Block;
import org.rcsb.cif.model.Column;
import org.rcsb.cif.model.ModelFactory;

public class TokenizerState {
    private final String data;
    private final int length;

    private int position;
    private boolean isEscaped;

    private int lineNumber;
    private CifTokenType tokenType;
    private int tokenStart;
    private int tokenEnd;

    private TextReader reader;
    
    public TokenizerState(TextReader reader, String data) {
    	this.reader = reader;
        this.data = data;
        this.position = 0;
        this.length = data.length();
        this.tokenStart = 0;
        this.tokenEnd = 0;
        this.tokenType = CifTokenType.END;
        this.lineNumber = 1;
        this.isEscaped = false;
    }

    CifTokenType getTokenType() {
        return tokenType;
    }

    int getLineNumber() {
        return lineNumber;
    }

    int getTokenStart() {
        return tokenStart;
    }

    int getTokenEnd() {
        return tokenEnd;
    }

    String getData() {
        return data;
    }

    /**
     * Eat everything until a whitespace/newline occurs.
     */
    private void eatValue() {
        while (position < length) {
            switch (data.charAt(position)) {
                case '\t': case '\n': case '\r': case ' ':
                    tokenEnd = position;
                    return;
                default:
                    position++;
                    break;
            }
        }
        tokenEnd = position;
    }

    /**
     * Eats an escaped value. Handles the "degenerate" cases as well.
     * "Degenerate" cases:
     *  - 'xx'x' => xx'x
     *  - 'xxxNEWLINE => 'xxx
     * @param esc escaping char
     */
    private void eatEscaped(int esc) {
        position++;
        while (position < length) {
            int c = data.charAt(position);

            if (c == esc) {
                // check for end of file
                if (position + 1 >= length) {
                    tokenStart++;
                    tokenEnd = position;
                    isEscaped = true;
                    position++;
                    return;

                }

                int next = data.charAt(position + 1);
                if (next == '\t' || next == '\n' || next == '\r' || next == ' ') {
                    // get rid of the quotes
                    tokenStart++;
                    tokenEnd = position;
                    isEscaped = true;
                    position++;
                    return;
                }

                position++;
            } else {
                // handle 'xxxNEWLINE => 'xxx
                if (c == '\r' || c == '\n') {
                    tokenEnd = position;
                    return;
                }
                position++;
            }
        }

        tokenEnd = position;
    }

    /**
     * Eats a multiline token of the form NL;....NL;
     */
    private void eatMultiline() {
        int prev = ';';
        int pos = position + 1;

        while (pos < length) {
            int c = data.charAt(pos);
            if (c == ';' && (prev == '\n' || prev == '\r')) {
                position = pos + 1;
                // get rid of the ;
                tokenStart++;

                // remove trailing newlines
                pos--;
                c = data.charAt(pos);
                while (c == '\n' || c == '\r') {
                    pos--;
                    c = data.charAt(pos);
                }
                tokenEnd = pos + 1;

                isEscaped = true;
                return;
            } else {
                // handle line numbers
                if (c == '\r') {
                    lineNumber++;
                } else if (c == '\n' && prev != '\r') {
                    lineNumber++;
                }

                prev = c;
                pos++;
            }
        }

        position = pos;
    }

    /**
     * Skips until \n or \r occurs -- therefore the newlines get handled by the "skipWhitespace" function.
     */
    private void skipCommentLine() {
        while (position < length) {
            int c = data.charAt(position);
            if (c == '\r' || c == '\n') {
                return;
            }
            position++;
        }
    }

    /**
     * Skips all whitespaces - space, tab, \r, \n. Handles incrementing the line number.
     * @return the last character read
     */
    private int skipWhitespace() {
        int prev = '\n';
        while (position < length) {
            int c = data.charAt(position);
            switch (c) {
                case '\t': case ' ':
                    prev = c;
                    position++;
                    break;
                case '\n':
                    // handle \r\n
                    if (prev != '\r') {
                        lineNumber++;
                    }
                    prev = c;
                    position++;
                    break;
                case '\r':
                    prev = c;
                    position++;
                    lineNumber++;
                    break;
                default:
                    return prev;
            }
        }
        return prev;
    }

    private boolean isData() {
        // here we already assume the 5th char is _ and that the length >= 5
        return "data".equalsIgnoreCase(data.substring(tokenStart, tokenStart + 4));
    }

    private boolean isSave() {
        // here we already assume the 5th char is _ and that the length >= 5
        return "save".equalsIgnoreCase(data.substring(tokenStart, tokenStart + 4));
    }

    private boolean isLoop() {
        // here we already assume the 5th char is _ and that the length >= 5
        return "loop".equalsIgnoreCase(data.substring(tokenStart, tokenStart + 4));
    }

    /**
     * Checks if the current token shares the namespace with string at <start,end).
     */
    private boolean isNamespace(int start, int end) {
        int i;
        int nsLen = end - start;
        int offset = tokenStart - start;
        int tokenLen = tokenEnd - tokenStart;

        if (tokenLen < nsLen) {
            return false;
        }

        for (i = start; i < end; i++) {
            if (data.charAt(i) != data.charAt(i + offset)) {
                return false;
            }
        }

        if (nsLen == tokenLen) {
            return true;
        }
        return data.charAt(i + offset) == '.';
    }

    /**
     * Returns the index of '.' in the current token. If no '.' is present, returns currentTokenEnd.
     */
    private int getNamespaceEnd() {
        return data.substring(tokenStart, tokenEnd).indexOf(".") + tokenStart;
    }

    /**
     * Get the namespace string. endIndex is obtained by the getNamespaceEnd() function.
     */
    private String getNamespace(int endIndex) {
        return data.substring(tokenStart, endIndex);
    }

    private String getTokenString() {
        return data.substring(tokenStart, tokenEnd);
    }

    /**
     * Move to the next token.
     */
    private void moveNext() {
        int prev = skipWhitespace();

        // end of file reached
        if (position >= length) {
            tokenType = CifTokenType.END;
            return;
        }

        tokenStart = position;
        tokenEnd = position;
        isEscaped = false;
        int c = data.charAt(position);
        switch (c) {
            case '#':
                skipCommentLine();
                tokenType = CifTokenType.COMMENT;
                break;
            case '"': case '\'':
                eatEscaped(c);
                tokenType = CifTokenType.VALUE;
                break;
            case ';': // possible multiline value
                // multiline value must start at the beginning of the line
                if (prev == '\n' || prev == '\r') {
                    eatMultiline();
                } else {
                    eatValue();
                }
                tokenType = CifTokenType.VALUE;
                break;
            default:
                eatValue();
                // escaped is always Value
                if (isEscaped) {
                    tokenType = CifTokenType.VALUE;
                    // _ always means column name
                } else if (data.charAt(tokenStart) == '_') {
                    tokenType = CifTokenType.COLUMN_NAME;
                    // 5th char needs to be _ for data_ or loop_
                } else if (tokenEnd - tokenStart >= 5 && data.charAt(tokenStart + 4) == '_') {
                    if (isData()) {
                        tokenType = CifTokenType.DATA;
                    } else if (isSave()) {
                        tokenType = CifTokenType.SAVE;
                    } else if (isLoop()) {
                        tokenType = CifTokenType.LOOP;
                    } else {
                        tokenType = CifTokenType.VALUE;
                    }
                    // all other tests failed, we are at Value token.
                } else {
                    tokenType = CifTokenType.VALUE;
                }
        }
    }

	/**
	 * Move to the next non-comment token.
	 */
	private void moveNextNoComment() {
		do {
			moveNext();
		} while (tokenType == CifTokenType.COMMENT);
	}

    /**
     * Reads a category containing a single row.
     * @param ctx the context values will be assigned to
     * @throws ParsingException throws when file is malformed
     */
	private void handleSingle(FrameContext ctx) throws ParsingException {

		final int nsStart = tokenStart;
		final int nsEnd = getNamespaceEnd();
		final String name = getNamespace(nsEnd);
		@SuppressWarnings("unchecked")
		final Map<String, Column> fields = (Map<String, Column>) Platform.getMap();
		String categoryName = name.substring(1).toLowerCase();
		ModelFactory.ensureModelPropertiesLoaded(categoryName);
		while (tokenType == CifTokenType.COLUMN_NAME && isNamespace(nsStart, nsEnd)) {
			String columnName = getTokenString().substring(name.length() + 1).toLowerCase();
			moveNextNoComment();
			if (tokenType != CifTokenType.VALUE) {
				throw new ParsingException("Expected value.", lineNumber);
			}
			Column cifColumn = ModelFactory.createColumnText(categoryName, columnName, data, tokenStart, tokenEnd);
			fields.put(columnName, cifColumn);
			moveNextNoComment();
		}
		ctx.getCategories().put(categoryName, reader.createCategory(categoryName, fields));
	}

    /**
     * Reads a loop.
     * @param ctx the context values will be assigned to
     */
    private void handleLoop(FrameContext ctx) {
		final int loopLine = lineNumber;

		moveNextNoComment();
		String name = getNamespace(getNamespaceEnd());

		// performance 1.2: resizing of token lists is pronounced - provide initial
		// guess to avoid excessive resizing
		int columnCountEstimate = 32;
		int rowCountEstimate = "_atom_site".equals(name) ? data.length() / 100 : 32;
		final List<String> columnNamesEncoded = new ArrayList<>(columnCountEstimate);
		final List<String> columnNamesLC = new ArrayList<>(columnCountEstimate);
		final List<List<Integer>> start = new ArrayList<>(columnCountEstimate);
		final List<List<Integer>> end = new ArrayList<>(columnCountEstimate);
		
		String categoryName = name.substring(1).toLowerCase();
		ModelFactory.ensureModelPropertiesLoaded(categoryName);
		while (tokenType == CifTokenType.COLUMN_NAME) {
			String colName = getTokenString().substring(name.length() + 1);
			columnNamesEncoded.add(colName);
			columnNamesLC.add(colName.toLowerCase());
			moveNextNoComment();
			start.add(new ArrayList<>(rowCountEstimate));
			end.add(new ArrayList<>(rowCountEstimate));
		}
		int colCount = start.size();
		int iToken = 0;
		while (tokenType == CifTokenType.VALUE) {
			int i = iToken++ % columnNamesLC.size();
			start.get(i).add(tokenStart);
			end.get(i).add(tokenEnd);
			moveNextNoComment();
		}
		if (colCount % columnNamesLC.size() != 0) {
			throw new ParsingException("The number of values for loop starting at line " + loopLine
					+ " is not a multiple of the number of columns.");
		}
		@SuppressWarnings("unchecked")
		Map<String, Column> columns = (Map<String, Column>) Platform.getMap();
		int nRows =  (colCount == 0 ? 0 : start.get(0).size());
		for (int i = 0; i < colCount; i++) {
			Column cifColumn = ModelFactory.createColumnText(categoryName, columnNamesEncoded.get(i), data,
					toArray(start.get(i), new int[nRows]), toArray(end.get(i), new int[nRows]));
			columns.put(columnNamesLC.get(i), cifColumn);
		}
		ctx.getCategories().put(categoryName, reader.createCategory(categoryName, columns));
	}

	private int[] toArray(List<Integer> list, int[] a) {
		for (int i = a.length; --i >= 0;)
			a[i] = list.get(i).intValue();
		return a;
	}

	public List<?> getDataBlocks(List<?> dataBlocks) {	
		FrameContext blockCtx = new FrameContext();
		String blockHeader = "";
		boolean inSaveFrame = false;

		// the next three initial values are never used in valid files
		List<Block> saveFrames = new ArrayList<>();
		FrameContext saveCtx = new FrameContext();
		Block saveFrame = reader.createBlock(saveCtx.getCategories(), "");

		moveNextNoComment();
		while (getTokenType() != CifTokenType.END) {
			CifTokenType token = getTokenType();

			// data block
			if (token == CifTokenType.DATA) {
				if (inSaveFrame) {
					throw new ParsingException("Unexpected data block inside a save frame.", getLineNumber());
				}
				if (blockCtx.getCategories().size() > 0) {
					Block block = reader.createBlock(blockCtx.getCategories(), blockHeader);
					reader.addBlock(block, dataBlocks, saveFrames);
				}
				blockHeader = getData().substring(getTokenStart() + 5, getTokenEnd());
				blockCtx = new FrameContext();
				saveFrames.clear();
				moveNextNoComment();
				// save frame
			} else if (getTokenType() == CifTokenType.SAVE) {
				final String saveHeader = getData().substring(getTokenStart() + 5,
						getTokenEnd());
				if (saveHeader.isEmpty()) {
					if (saveCtx.getCategories().size() > 0) {
						saveFrames.add(saveFrame);
					}
					inSaveFrame = false;
				} else {
					if (inSaveFrame) {
						throw new ParsingException("Save frames cannot be nested.", getLineNumber());
					}
					inSaveFrame = true;
					final String safeHeader = getData().substring(getTokenStart() + 5,
							getTokenEnd());
					saveCtx = new FrameContext();
					saveFrame = reader.createBlock(saveCtx.getCategories(), safeHeader);
				}
				moveNextNoComment();
				// loop
			} else if (token == CifTokenType.LOOP) {
				handleLoop(inSaveFrame ? saveCtx : blockCtx);
				// single row
			} else if (token == CifTokenType.COLUMN_NAME) {
				handleSingle(inSaveFrame ? saveCtx : blockCtx);
				// out of options
			} else {
				throw new ParsingException("Unexpected token (" + token + "). Expected data_, loop_, or data name.",
						getLineNumber());
			}
		}

		// check if the latest save frame was terminated
		if (inSaveFrame) {
			throw new ParsingException("Unfinished save frame (" + saveFrame.getBlockHeader() + ").",
					getLineNumber());
		}

		if (blockCtx.getCategories().size() > 0 || saveFrames.size() > 0) {
			Block block = reader.createBlock(blockCtx.getCategories(), blockHeader);
			reader.addBlock(block, dataBlocks, saveFrames);
		}
		return dataBlocks;
	}
}

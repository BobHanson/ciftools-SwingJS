package org.rcsb.cif.text;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.rcsb.cif.CifOptions;
import org.rcsb.cif.model.Block;
import org.rcsb.cif.model.Category;
import org.rcsb.cif.model.Column;
import org.rcsb.cif.model.IntColumn;
import org.rcsb.cif.model.ModelFactory;

public class TextWriter {
    private final CifOptions options;

    public TextWriter(CifOptions options) {
        this.options = options;
    }

    public byte[] writeBlocks(List<?> blocks) {
        StringBuilder output = new StringBuilder();
        for (Object block : blocks) {
        	writeBlock((Block) block, output);
        }
        output.append("\n");
        try {
			return output.toString().getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	private void writeBlock(Block block, StringBuilder output) {
        String blockHeader = block.getBlockHeader();
        String header = blockHeader != null ? blockHeader.replaceAll("[ \n\t]", "").toUpperCase() : "UNKNOWN";
        output.append("data_")
                .append(header)
                .append("\n#\n");

        for (String categoryName : block.getCategoryNames()) {
        	//System.out.println("textwriter adding category " + categoryName);
            if (!options.filterCategory(categoryName)) {
                continue;
            }
            Category cifCategory = block.getCategory(categoryName);
            int rowCount = cifCategory.getRowCount();
            if (rowCount == 0) {
                continue;
            }
    		ModelFactory.ensureModelPropertiesLoaded(categoryName);
            List<Column> columns = new ArrayList<>();
            for (String name : cifCategory.getColumnNamesEncoded()) {
            	if (options.filterColumn(categoryName, name)) {
            		columns.add(cifCategory.getColumn(name));
            	}
            }
            // problem here with original transpiled lambda method $$
            if (columns.isEmpty()) {
                continue;
            }

            if (rowCount == 1) {
                writeCifSingleRecord(output, cifCategory, columns);
            } else {
                writeCifLoop(output, cifCategory, columns);
            }
        }
	}

	private void writeCifSingleRecord(StringBuilder output, Category cifCategory, List<Column> columns) {
        int width = columns.stream()
                .map(Column::getColumnName)
                .mapToInt(String::length)
                .max()
                .orElseThrow(() -> new NoSuchElementException("not able to determine column width"))
                + 6 + cifCategory.getCategoryName().length();

        for (Column cifField : columns) {
            writePadRight(output, "_" + cifCategory.getCategoryName() + "." + cifField.getColumnName(), width);

            for (int row = 0; row < cifField.getRowCount(); row++) {
                boolean multiline = writeValue(output, cifField, row);
                if (!multiline) {
                    output.append("\n");
                }
            }
        }
        output.append("#\n");
    }

    private void writeCifLoop(StringBuilder output, Category cifCategory, List<Column> columns) {
        output.append("loop_")
                .append("\n");
        for (Column cifField : columns) {
        	// this will initialize the column
        	Column c = cifCategory.getColumn(cifField.getColumnName());
            output.append("_")
                    .append(cifCategory.getCategoryName())
                    .append(".")
                    .append(c.getColumnName())
                    .append("\n");
        }

        for (int row = 0, n = cifCategory.getRowCount(); row < n; row++) {
            boolean multiline = false;
            for (Column cifField : columns) {
                multiline = writeValue(output, cifField, row);
            }
            if (!multiline) {
                output.append("\n");
            }
        }
        output.append("#\n");
    }

	private boolean writeValue(StringBuilder output, Column cifField, int row) {
		switch (cifField.getValueKind(row)) {
		case PRESENT:
		default:
			switch (cifField.getType()) {
			case Column.COLUMN_TYPE_INT:
				output.append(((IntColumn) cifField).get(row)).append(" ");
				return false;
			case Column.COLUMN_TYPE_FLOAT:
				output.append(cifField.getStringData(row)).append(" ");
				return false;
			default:
			case Column.COLUMN_TYPE_STRING:
				return writeChecked(output, cifField.getStringData(row));
			}
		case NOT_PRESENT:
	        output.append(". ");
	        return false;
		case UNKNOWN:
	        output.append("? ");
	        return false;
		}
	}

	private boolean writeChecked(StringBuilder output, String val) {
		if (val.isEmpty()) {
			output.append(". ");
			return false;
		}
		if (val.contains("\n"))
			return writeMultiline(output, val);
		boolean escape = false;
		char escChar = '\'';
		out: for (int i = 0, n = val.length(); i < n; i++) {
			switch (val.charAt(i)) {
			case '\t':
			case ' ':
				escape = true;
				continue;
			case '"':
				if (val.indexOf('\'', i + 1) > 0)
					return writeMultiline(output, val);
				escape = true;
				break out;
			case '\'':
				if (val.indexOf('"', i + 1) > 0)
					return writeMultiline(output, val);
				escChar = '"';
				escape = true;
				break out;
			}
		}
		if (!escape) {
			switch (val.charAt(0)) {
			case '#':
			case '$':
			case ';':
			case '[':
			case ']':
				escape = true;
				break;
			}
		}
		if (escape) {
			output.append(escChar).append(val).append(escChar).append(' ');
		} else {
			output.append(val).append(' ');
		}
		return false;
	}

    private static boolean writeMultiline(StringBuilder output, String val) {
        output.append("\n;")
                .append(val)
                .append("\n;\n");
        return true;
    }

	private boolean writePadRight(StringBuilder output, String val, int width) {
		if (val != null && !val.isEmpty()) {
			output.append(val);
			width -= val.length();
		}
		whitespace(output, width);
		return false;
	}

    private static final List<String> PADDING_SPACES = IntStream.range(0, 80)
        .mapToObj(TextWriter::whitespaceString)
        .collect(Collectors.toList());

    private static String whitespaceString(int width) {
        return IntStream.range(0, width).mapToObj(i -> " ").collect(Collectors.joining());
    }

    private static String getPaddingSpaces(int width) {
    	if (width < PADDING_SPACES.size())
    		return PADDING_SPACES.get(width);
    	String pad = null;
    	for (int i = PADDING_SPACES.size(); i <= width; i++)
    		PADDING_SPACES.set(i, pad = whitespaceString(i));
    	return pad;
    }

    private void whitespace(StringBuilder output, int width) {
        if (width > 0) {
            output.append(getPaddingSpaces(width));
        }
    }
}

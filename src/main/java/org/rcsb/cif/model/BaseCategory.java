package org.rcsb.cif.model;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.rcsb.cif.Platform;
import org.rcsb.cif.binary.codec.Codec;

public class BaseCategory implements Category {
    private final String name;
    private final int rowCount;
    private final List<String> columnNamesEncoded;
    private final List<String> columnNamesLC;

    protected final boolean isText;
    protected final Map<String, Column> textFields;

    protected Object[] encodedColumns;
    private final Map<String, Column> decodedColumns;
    private final boolean defined;

    public BaseCategory(String name) {
        this.name = name;
        this.rowCount = 0;
        this.columnNamesEncoded = Collections.emptyList();
        this.columnNamesLC = Collections.emptyList();

        this.isText = false;
        this.textFields = Collections.emptyMap();

        this.encodedColumns = new Object[0];
        this.decodedColumns = Collections.emptyMap();
        this.defined = false;
    }

    public BaseCategory(String name, Map<String, Column> textColumns) {
        this.name = name;
        
// still not functional in SwingJS
//        this.rowCount = textColumns.values()
//                .stream()
//                .findFirst()
//                .map(Column::getRowCount)
//                .orElse(0);
        this.columnNamesEncoded = new ArrayList<>(textColumns.keySet());
        this.columnNamesLC = new ArrayList<>(textColumns.keySet());

        this.rowCount = (this.columnNamesLC.size() == 0 ? 0 : textColumns.get(this.columnNamesLC.get(0)).getRowCount());

        this.isText = true;
        this.textFields = textColumns;

        this.encodedColumns = null;
        this.decodedColumns = null;
        this.defined = true;
    }

    @SuppressWarnings("unchecked")
    public BaseCategory(String name, int rowCount, Object[] encodedColumns) {
        this.name = name;
        this.rowCount = rowCount;

        this.isText = false;
        this.encodedColumns = encodedColumns;
        this.decodedColumns = (Map<String, Column>) Platform.getMap();
        try {
            this.columnNamesEncoded = new ArrayList<String>();
            this.columnNamesLC = new ArrayList<String>();
            for (int i = 0, n = encodedColumns.length; i < n; i++) {
            	String s = Codec.getStringFromBytes((byte[]) ((Map<String, Object>) encodedColumns[i]).get("name"));
            	this.columnNamesEncoded.add(s);
            	this.columnNamesLC.add(s.toLowerCase());
            }
        } catch(Exception e) {
            throw new RuntimeException(e);
        }

        this.textFields = null;
        this.defined = true;
    }

    @Override
    public String getCategoryName() {
        return name;
    }

    @Override
    public int getRowCount() {
        return rowCount;
    }

    @Override
    public Column getColumn(String name) {
        return isText ? getTextColumn(name) : getBinaryColumn(name.toLowerCase());
    }

    private Column getTextColumn(String name) {
    	Column c = textFields.get(name.toLowerCase());
    	return (c == null ? textFields.computeIfAbsent(name, n -> ModelFactory.createEmptyColumn(this.name, n)) : c);
    }

    protected Column getBinaryColumn(String name) {
        Map<String, Object> col = find(name);
        // cache decoded fields to reuse them if applicable
        if (col == null) {
            return ModelFactory.createEmptyColumn(this.name, name);
        }
        if (decodedColumns.containsKey(name)) {
            return decodedColumns.get(name);
        }
        Column decodedColumn = ModelFactory.createColumnBinary(this.name, name, col);
        decodedColumns.put(name, decodedColumn);
        return decodedColumn;
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> find(String name) {
    	for (int i = columnNamesEncoded.size(); --i >= 0;)
    		if (columnNamesEncoded.get(i).equalsIgnoreCase(name))
    			return (Map<String, Object>) encodedColumns[i];
    	return null;
//        return Stream.of(encodedColumns)
//                .map(m -> (Map<String, Object>) m)
//                .filter(m -> name.equalsIgnoreCase((String)m.get("name")))
//                .findFirst();
    }

    @Override
    public List<String> getColumnNamesEncoded() {
        return columnNamesEncoded;
    }

    @Override
    public List<String> getColumnNames() {
        return columnNamesLC;
    }

    @Override
    public boolean isDefined() {
        return defined;
    }

	@Override
	public double[][] fillFloat(String... colNames) {
		if (colNames.length == 0)
			return new double[0][];
		try {
			double[][] a = new double[colNames.length][rowCount];
			for (int i = colNames.length; --i >= 0;) {
				BaseColumn col = (BaseColumn) getColumn(colNames[i]);
				Object data = col.getUnmaskedData();
				switch (col.type) {
				case Column.COLUMN_TYPE_STRING:
					continue;
				case Column.COLUMN_TYPE_FLOAT:
					a[i] = (double[]) data;
					break;
				case Column.COLUMN_TYPE_INT:
					for (int j = rowCount; --j >= 0;) {
						a[i][j] = ((int[]) data)[j];
					}
					break;
				}
			}
			return a;
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public FloatColumn getFloatColumn(String name) {
		Column col = getColumn(name);
		if (col == null)
			throw new NullPointerException("no column with name " + name);
		if (!(col instanceof FloatColumn))
			throw new ClassCastException("type of column " + name + " is " + col.getClass().getSimpleName());
		return (FloatColumn) col;
	}

	@Override
	public IntColumn getIntColumn(String name) {
		Column col = getColumn(name);
		if (col == null)
			throw new NullPointerException("no column with name " + name);
		if (!(col instanceof IntColumn))
			throw new ClassCastException("type of column " + name + " is " + col.getClass().getSimpleName());
		return (IntColumn) col;
	}

	@Override
	public StrColumn getStrColumn(String name) {
		Column col = getColumn(name);
		if (col == null)
			throw new NullPointerException("no column with name " + name);
		if (!(col instanceof StrColumn))
			throw new ClassCastException("type of column " + name + " is " + col.getClass().getSimpleName());
		return (StrColumn) col;
	}

}

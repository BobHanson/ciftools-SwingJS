package org.rcsb.cif.model;

import org.rcsb.cif.schema.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.rcsb.cif.schema.Schema.*;

public class BaseCategory implements Category {
    private static final Logger logger = LoggerFactory.getLogger(BaseColumn.class);
    private final String name;
    private final int rowCount;
    private final List<String> columnNames;

    protected final boolean isText;
    protected final Map<String, Column> textFields;

    private final Object[] encodedColumns;
    private final Map<String, Column> decodedColumns;
    private final boolean defined;

    public BaseCategory(String name) {
        this.name = name;
        this.rowCount = 0;
        this.columnNames = Collections.emptyList();

        this.isText = false;
        this.textFields = Collections.emptyMap();

        this.encodedColumns = new Object[0];
        this.decodedColumns = Collections.emptyMap();
        this.defined = false;
    }

    public BaseCategory(String name, Map<String, Column> textColumns) {
        this.name = name;
        this.rowCount = textColumns.values()
                .stream()
                .findFirst()
                .map(Column::getRowCount)
                .orElse(0);
        this.columnNames = new ArrayList<>(textColumns.keySet());

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
        this.decodedColumns = new LinkedHashMap<>();
        try {
            this.columnNames = Stream.of(encodedColumns)
                    .map(map -> ((Map<String, Object>) map).get("name"))
                    .map(String.class::cast)
                    .collect(Collectors.toList());
        } catch(Exception e) {
            throw new RuntimeException(e);
        }

        this.textFields = null;
        this.defined = true;
    }

    public BaseCategory(String name, int rowCount, Map<String, Object> columns) {
        this.name = name;
        this.rowCount = rowCount;

        this.isText = false;
        this.encodedColumns = null;
        this.decodedColumns = columns.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        BaseColumn::create,
                        (u, v) -> {
                            throw new IllegalStateException("Duplicate key " + u);
                        },
                        LinkedHashMap::new));
        this.columnNames = new ArrayList<>(columns.keySet());

        this.textFields = null;
        this.defined = true;
    }

    @SuppressWarnings("unchecked")
    public static Category create(String catName, Map<String, Column> fields) {
        // well, it's come to this
        // 1. look if category name is in list of considered fields, otherwise don't even bother
        if (filter(catName)) {
            try {
                // 2. if so, try to obtain instance
                Class<? extends BaseCategory> category = (Class<? extends BaseCategory>) Class.forName(Schema.BASE_PACKAGE
                        + "." + toPackageName(catName) + "." + toClassName(catName));
                return category.getConstructor(String.class, Map.class).newInstance(catName, fields);
            } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                // though recoverable, any exception points to a flaw in the data model, implementation or schema
                throw new RuntimeException(e);
            }
        } else {
            // 3. if not a known category, roll with base implementation
            return new BaseCategory(catName, fields);
        }
    }

    @SuppressWarnings("unchecked")
    public static Category create(String name, int rowCount, Object[] encodedFields) {
        if (filter(name)) {
            try {
                Class<? extends BaseCategory> category = (Class<? extends BaseCategory>) Class.forName(Schema.BASE_PACKAGE
                        + "." + toPackageName(name) + "." + toClassName(name));
                return category.getConstructor(String.class, int.class, Object[].class).newInstance(name, rowCount, encodedFields);
            } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        } else {
            return new BaseCategory(name, rowCount, encodedFields);
        }
    }

    /**
     * This methods must be named getCategoryName() to avoid collision with categories actually containing columns named
     * 'name'.
     * @return the name of this category
     */
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
        return isText ? getTextColumn(name) : getBinaryColumn(name);
    }

    private Column getTextColumn(String name) {
        return textFields.computeIfAbsent(name, n -> BaseColumn.create(this.name, n));
    }

    protected Column getBinaryColumn(String name) {
        Optional<Map<String, Object>> optional = find(name);
        // cache decoded fields to reuse them if applicable
        if (!optional.isPresent()) {
            return BaseColumn.create(this.name, name);
        }
        if (decodedColumns.containsKey(name)) {
            return decodedColumns.get(name);
        }
        logger.debug("decoding binary column: {}.{}", this.name, name);
        Column decodedColumn = BaseColumn.create(this.name, name, optional.get());
        decodedColumns.put(name, decodedColumn);
        return decodedColumn;
    }

    @SuppressWarnings("unchecked")
    private Optional<Map<String, Object>> find(String name) {
        return Stream.of(encodedColumns)
                .map(m -> (Map<String, Object>) m)
                .filter(m -> name.equals(m.get("name")))
                .findFirst();
    }

    @Override
    public List<String> getColumnNames() {
        return columnNames;
    }

    @Override
    public boolean isDefined() {
        return defined;
    }
}

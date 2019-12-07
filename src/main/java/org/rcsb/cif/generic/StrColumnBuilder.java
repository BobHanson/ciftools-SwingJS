package org.rcsb.cif.generic;

import org.rcsb.cif.model.Builder;
import org.rcsb.cif.model.StrColumn;
import org.rcsb.cif.model.ValueKind;

import java.util.ArrayList;
import java.util.List;

/**
 * Builds a String column, data type cannot change, all subsequent values must be Strings.
 * @param <P> the type of the parent builder
 */
public class StrColumnBuilder<P extends CategoryBuilder> extends Builder.ColumnBuilder implements ColumnBuilder<P> {
	private P parent;

    public StrColumnBuilder(String categoryName, String columnName, P parent) {
        super(categoryName, columnName, new ArrayList<String>());
	    this.parent = parent;
	    if (parent != null) {
	        // make parent aware of its child, so that when parent category is closed, all child information can be collected
	        this.parent.registerChild(this);
	    }
    }

    @SuppressWarnings("unchecked")
	List<String> getValues() {
        return values;
    }

    @Override
    public StrColumnBuilder<P> markNextNotPresent() {
		markNext(".", ValueKind.NOT_PRESENT);
        return this;
    }

    @Override
    public StrColumnBuilder<P> markNextUnknown() {
		markNext("?", ValueKind.UNKNOWN);
        return this;
    }

    @Override
    public StrColumn build() {
        return (StrColumn) CategoryBuilder.createColumnText(getCategoryName(), getColumnName(), values, mask, StrColumn.class);
    }

    public P leaveColumn() {
        if (parent == null) {
            throw new IllegalStateException("cannot leave column with undefined parent category");
        }
        return parent.digest(this);
    }

    /**
     * Add an arbitrary number of String values to this column.
     * @param value 0...n String values
     * @return this builder instance
     */
    public StrColumnBuilder<P> add(String... value) {
        for (String s : value) {
            if (".".equals(s)) {
                markNextNotPresent();
            } else if ("?".equals(s)) {
                markNextUnknown();
            } else {
                values.add(s);
                mask.add(ValueKind.PRESENT);
            }
        }
        return this;
    }
}

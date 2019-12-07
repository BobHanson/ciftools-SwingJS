package org.rcsb.cif.api.builder;

import org.rcsb.cif.model.Builder;
import org.rcsb.cif.model.IntColumn;
import org.rcsb.cif.model.ValueKind;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Builds a int column, data type cannot change, all subsequent values must be ints.
 * @param <P> the type of the parent builder
 */
public class IntColumnBuilder<P extends CategoryBuilder> extends Builder.ColumnBuilder implements ColumnBuilder<P> {


	final P parent;


	/**
	 * Create a new ColumnBuilder instance.
	 * @param categoryName the parent category name
	 * @param columnName this column name
	 * @param parent the parent instance - can be <code>null</code>, but {@link ColumnBuilder#leaveColumn()} will throw
	 *               an exception if invoked
	 */
    public IntColumnBuilder(String categoryName, String columnName, P parent) {
        super(categoryName, columnName, new ArrayList<Integer>());
	    this.parent = parent;
	    if (parent != null) {
	        // make parent aware of its child, so that when parent category is closed, all child information can be collected
	        this.parent.registerChild(this);
	    }
    }

    @SuppressWarnings("unchecked")
	List<Integer> getValues() {
        return values;
    }

    @Override
    public IntColumnBuilder<P> markNextNotPresent() {
		markNext(Integer.valueOf(0), ValueKind.NOT_PRESENT);
        return this;
    }

    @Override
    public IntColumnBuilder<P> markNextUnknown() {
		markNext(Integer.valueOf(0), ValueKind.UNKNOWN);
        return this;
    }

    @Override
    public IntColumn build() {
        return (IntColumn) CategoryBuilder.createColumnText(getCategoryName(), getColumnName(), values, mask, IntColumn.class);
    }

    public P leaveColumn() {
        if (parent == null) {
            throw new IllegalStateException("cannot leave column with undefined parent category");
        }
        return parent.digest(this);
    }

    /**
     * Add an arbitrary number of int values to this column.
     * @param value 0...n int values
     * @return this builder instance
     */
    @SuppressWarnings("unchecked")
	public IntColumnBuilder<P> add(int... value) {
        IntStream.of(value).forEach(values::add);
        IntStream.range(0, value.length).mapToObj(i -> ValueKind.PRESENT).forEach(mask::add);
        return this;
    }
}

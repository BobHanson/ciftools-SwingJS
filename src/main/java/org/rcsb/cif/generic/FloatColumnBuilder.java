package org.rcsb.cif.generic;

import org.rcsb.cif.model.Builder;
import org.rcsb.cif.model.FloatColumn;
import org.rcsb.cif.model.ValueKind;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

/**
 * Builds a float column, data type cannot change, all subsequent values must be floats.
 * @param <P> the type of the parent builder
 */
public class FloatColumnBuilder<P extends CategoryBuilder> extends Builder.ColumnBuilder implements ColumnBuilder<P> {
	private P parent;

    public FloatColumnBuilder(String categoryName, String columnName, P parent) {
        super(categoryName, columnName, new ArrayList<Double>());
	    this.parent = parent;
	    if (parent != null) {
	        // make parent aware of its child, so that when parent category is closed, all child information can be collected
	        this.parent.registerChild(this);
	    }
    }

    @SuppressWarnings("unchecked")
	List<Double> getValues() {
        return (List<Double>) values;
    }

    @Override
    public FloatColumnBuilder<P> markNextNotPresent() {
    	markNext(Double.valueOf(0), ValueKind.NOT_PRESENT);
        return this;
    }

	@Override
    public FloatColumnBuilder<P> markNextUnknown() {
		markNext(Double.valueOf(0), ValueKind.UNKNOWN);
        return this;
    }

    @Override
    public FloatColumn build() {
        return (FloatColumn) Builder.CategoryBuilder.createColumnText(getCategoryName(), getColumnName(), values, mask, FloatColumn.class);
    }

    public P leaveColumn() {
        if (parent == null) {
            throw new IllegalStateException("cannot leave column with undefined parent category");
        }
        return parent.digest(this);
    }

    /**
     * Add an arbitrary number of float values to this column.
     * @param value 0...n float values
     * @return this builder instance
     */
    @SuppressWarnings("unchecked")
	public FloatColumnBuilder<P> add(double... value) {
        DoubleStream.of(value).forEach(values::add);
        IntStream.range(0, value.length).mapToObj(i -> ValueKind.PRESENT).forEach(mask::add);
        return this;
    }
}

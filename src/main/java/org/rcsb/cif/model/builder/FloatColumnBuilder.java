package org.rcsb.cif.model.builder;

import org.rcsb.cif.model.Column;
import org.rcsb.cif.model.ValueKind;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.DoubleStream;

public class FloatColumnBuilder extends AbstractColumnBuilder {
    private final List<Double> values;

    public FloatColumnBuilder(String categoryName, String columnName, CategoryBuilder parent) {
        super(categoryName, columnName, parent);
        this.values = new ArrayList<>();
    }

    public List<Double> getValues() {
        return values;
    }


    @Override
    public FloatColumnBuilder markNextNotPresent() {
        values.add(0.0);
        mask.add(ValueKind.NOT_PRESENT);
        return this;
    }

    @Override
    public FloatColumnBuilder markNextUnknown() {
        values.add(0.0);
        mask.add(ValueKind.UNKNOWN);
        return this;
    }

    @Override
    public Column build() {
        return AbstractCategoryBuilder.createColumnText(getCategoryName(), getColumnName(), values, mask);
    }

    @Override
    public CategoryBuilder leaveColumn() {
        if (parent == null) {
            throw new IllegalStateException("cannot leave column with undefined parent category");
        }
        return parent.digest(this);
    }

    public FloatColumnBuilder add(double... value) {
        DoubleStream.of(value).forEach(values::add);
        return this;
    }
}

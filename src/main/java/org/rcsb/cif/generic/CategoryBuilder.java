package org.rcsb.cif.generic;

import org.rcsb.cif.model.BaseCategory;
import org.rcsb.cif.model.Builder;
import org.rcsb.cif.model.Category;
import org.rcsb.cif.model.FloatColumn;
import org.rcsb.cif.model.IntColumn;
import org.rcsb.cif.model.StrColumn;

/**
 * Builds a category in a {@link org.rcsb.cif.api.CifBlock}.
 */
public class CategoryBuilder extends Builder.CategoryBuilder {

	protected final BlockBuilder parent;

    /**
     * Create a CategoryBuilder instance.
     * @param categoryName the name of this category
     * @param parent the parent builder - can be <code>null</code>, but {@link CategoryBuilder#leaveCategory()} will
     *               throw an exception if invoked
     */
    public CategoryBuilder(String categoryName, BlockBuilder parent) {
    	super(categoryName);
    	this.parent = parent;
    }
    
    /**
     * Enter an arbitrary IntColumn.
     * @param columnName the column name
     * @return an IntColumnBuilder
     */
    public IntColumnBuilder enterIntColumn(String columnName) {
        return new IntColumnBuilder<>(getCategoryName(), columnName, this);
    }

    /**
     * Enter an arbitrary FloatColumn.
     * @param columnName the column name
     * @return an FloatColumnBuilder
     */
    public FloatColumnBuilder enterFloatColumn(String columnName) {
        return new FloatColumnBuilder<>(getCategoryName(), columnName, this);
    }

    /**
     * Enter an arbitrary StrColumn.
     * @param columnName the column name
     * @return an StrColumnBuilder
     */
    public StrColumnBuilder enterStrColumn(String columnName) {
        return new StrColumnBuilder<>(getCategoryName(), columnName, this);
    }

    /**
     * Process all stored information and release a {@link Category} instance. Use
     * {@link CategoryBuilder#leaveCategory()} if you want to create a {@link CifFile} instance. Otherwise the block is
     * aware of this parent (and vice versa).
     * @return the created Category
     */
    public Category build() {
        return new BaseCategory(categoryName, columns);
    }

    /**
     * Process all stored information and return to the BlockBuilder instance.
     * @return the parent builder
     */
    public BlockBuilder leaveCategory() {
        if (parent == null) {
            throw new IllegalStateException("cannot leave category with undefined parent block");
        }

        // if children are not digested yet (what a sentence), do so
        pendingDigests.stream()
                .filter(child -> !finishedDigests.contains(child))
                .forEach(child -> {
                    if (child instanceof IntColumnBuilder<?>) {
                        digest((IntColumnBuilder<?>) child);
                    } else if (child instanceof FloatColumnBuilder<?>) {
                        digest((FloatColumnBuilder<?>) child);
                    } else {
                        digest((StrColumnBuilder<?>) child);
                    }
                });

        return parent.digest(build());
    }


    /**
     * Package-private function to process the information of children.
     * @param intColumnBuilder the child builder to incorporate
     * @param <P> the type of the parent builder (this class)
     * @return the type-safe builder instance which was used to enter this column
     */
    @SuppressWarnings("unchecked")
    <P extends CategoryBuilder> P digest(IntColumnBuilder<P> intColumnBuilder) {
        columns.put(intColumnBuilder.getColumnName(),
                createColumnText(categoryName,
                        intColumnBuilder.getColumnName(),
                        intColumnBuilder.getValues(),
                        intColumnBuilder.getMask(),
                        IntColumn.class));
        finishedDigests.add(intColumnBuilder);
        return (P) this;
    }

    /**
     * Package-private function to process the information of children.
     * @param floatColumnBuilder the child builder to incorporate
     * @param <P> the type of the parent builder (this class)
     * @return the type-safe builder instance which was used to enter this column
     */
    @SuppressWarnings("unchecked")
    <P extends CategoryBuilder> P digest(FloatColumnBuilder<P> floatColumnBuilder) {
        columns.put(floatColumnBuilder.getColumnName(),
                createColumnText(categoryName,
                        floatColumnBuilder.getColumnName(),
                        floatColumnBuilder.getValues(),
                        floatColumnBuilder.getMask(),
                        FloatColumn.class));
        finishedDigests.add(floatColumnBuilder);
        return (P) this;
    }

    /**
     * Package-private function to process the information of children.
     * @param strColumnBuilder the child builder to incorporate
     * @param <P> the type of the parent builder (this class)
     * @return the type-safe builder instance which was used to enter this column
     */
    @SuppressWarnings("unchecked")
    <P extends CategoryBuilder> P digest(StrColumnBuilder<P> strColumnBuilder) {
        columns.put(strColumnBuilder.getColumnName(),
                createColumnText(categoryName,
                        strColumnBuilder.getColumnName(),
                        strColumnBuilder.getValues(),
                        strColumnBuilder.getMask(),
                        StrColumn.class));
        finishedDigests.add(strColumnBuilder);
        return (P) this;
    }

}

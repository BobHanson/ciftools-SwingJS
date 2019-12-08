package org.rcsb.cif.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.rcsb.cif.io.Platform;

public abstract class Builder {

	public static class ColumnBuilder {

		protected final String categoryName;
		protected final String columnName;
		protected final List<ValueKind> mask;
        public final List values;


		public ColumnBuilder(String categoryName, String columnName, List<?> values) {
			this.categoryName = categoryName;
			this.columnName = columnName;
			this.mask = new ArrayList<>();
	        this.values = values;
		}

		/**
		 * The name of the parent category.
		 * 
		 * @return a String
		 */
		protected String getCategoryName() {
			return categoryName;
		}

		/**
		 * The name of this column.
		 * 
		 * @return a String
		 */
		public String getColumnName() {
			return columnName;
		}

		/**
		 * The mask of this column.
		 * 
		 * @return a list of ValueKind values
		 */
		public List<ValueKind> getMask() {
			return mask;
		}

	    @SuppressWarnings("unchecked")
		protected void markNext(Object val, ValueKind kind) {
	        values.add(val);
	        mask.add(kind); 		
		}

	}

	public static class BlockBuilder {

		protected final String blockName;
		protected final Map<String, Category> categories;

		/**
		 * Create a BlockBuilder instance.
		 * 
		 * @param blockName the name of this block
		 * @param parent    the parent builder - can be <code>null</code>, but
		 *                  {@link BlockBuilder#leaveBlock()} will throw an exception if
		 *                  invoked
		 */
		public BlockBuilder(String blockName) {
			this.blockName = blockName;
			this.categories = (Map<String, Category>) Platform.getMap();
		}

		/**
		 * The name of this block.
		 * 
		 * @return a String
		 */
		public String getBlockName() {
			return blockName;
		}

		/**
		 * Associated categories.
		 * 
		 * @return the category map
		 */
		public Map<String, Category> getCategories() {
			return categories;
		}

		/**
		 * Add an arbitrary category and make this builder aware of it.
		 * 
		 * @param category the category to add
		 * @return this BlockBuilder instance
		 */
		public BlockBuilder addCategory(Category category) {
			categories.put(category.getCategoryName(), category);
			return this;
		}

	}

	public static abstract class CategoryBuilder {

		protected final String categoryName;
		protected final Map<String, Column> columns;
		protected final List pendingDigests;
		protected final List finishedDigests;

		public CategoryBuilder(String categoryName) {
			this.categoryName = categoryName;
			this.columns = (Map<String, Column>) Platform.getMap();
			this.pendingDigests = new ArrayList<>();
			this.finishedDigests = new ArrayList<>();
		}

		/**
		 * The name of this category.
		 * 
		 * @return a String
		 */
		public String getCategoryName() {
			return categoryName;
		}

		/**
		 * Associated columns.
		 * 
		 * @return the column map
		 */
		public Map<String, Column> getColumns() {
			return columns;
		}

		/**
		 * Add an arbitrary column and make this builder aware of it.
		 * 
		 * @param column the column to add
		 * @return this CategoryBuilder instance
		 */
		public Builder.CategoryBuilder addColumn(Column column) {
			columns.put(column.getColumnName(), column);
			return this;
		}

		/**
		 * Convenience function to create column instances.
		 * 
		 * @param categoryName the category name
		 * @param columnName   the column name
		 * @param values       a list of int, double, or String values
		 * @param mask         a list of equal size, specifying ValueKinds
		 * @param hint         the class the column to create resembles
		 * @param              <C> the class the column to create resembles
		 * @return the create Column
		 */
		public static Column createColumnText(String categoryName, String columnName, List<?> values, List<ValueKind> mask,
				Class<? extends Column> hint) {
			int length = values.size();
			int[] startToken = new int[length];
			int[] endToken = new int[length];
			StringBuilder builder = new StringBuilder();

			for (int i = 0; i < length; i++) {
				startToken[i] = builder.length();
				String value = String.valueOf(values.get(i));
				if (mask.get(i) == ValueKind.NOT_PRESENT) {
					value = ".";
				} else if (mask.get(i) == ValueKind.UNKNOWN) {
					value = "?";
				}
				builder.append(value);
				endToken[i] = builder.length();
			}
			return ModelFactory.createColumnText(categoryName, columnName, builder.toString(), startToken, endToken,
					hint);
		}

		/**
		 * This allows to automatically digest child column builders upon leaving this
		 * category.
		 * 
		 * @param childColumnBuilder the child to register
		 */
		public void registerChild(ColumnBuilder childColumnBuilder) {
			pendingDigests.add(childColumnBuilder);
		}

	}

}

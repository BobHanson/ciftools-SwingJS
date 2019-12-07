package org.rcsb.cif.api.builder;

import org.rcsb.cif.model.Column;
import org.rcsb.cif.model.ValueKind;

import java.util.ArrayList;
import java.util.List;

/**
 * Shared functionality of column building instances.
 * @param <P> type of the parent builder, so that upon leaving the concrete instance can be provided (rather than a
 *           generic category builder)
 */
public interface ColumnBuilder<P extends CategoryBuilder> {

    /**
     * Values in CifFile instances can be present, not present (.), or unknown (?). Stores an empty value in the value
     * list and marks this row as not present.
     * @return this builder instance
     */
    public abstract ColumnBuilder<P> markNextNotPresent();

    /**
     * Values in CifFile instances can be present, not present (.), or unknown (?). Stores an empty value in the value
     * list and marks this row as unknown.
     * @return this builder instance
     */
    public abstract ColumnBuilder<P> markNextUnknown();

    /**
     * Build this instance, ignoring parent information.
     * @return the Column
     */
    public abstract Column build();
    
    /**
     * Leave this column and return to the parent builder.
     * @return the parent builder
     */
    public abstract P leaveColumn();
}

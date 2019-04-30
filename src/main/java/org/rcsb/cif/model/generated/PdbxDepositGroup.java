package org.rcsb.cif.model.generated;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;
import java.util.Map;

/**
 * Data items in the pdbx_deposit_group category provide identifiers
 * and related information for groups of entries deposited in a collection.
 */
@Generated("org.rcsb.cif.generator.SchemaGenerator")
public class PdbxDepositGroup extends BaseCategory {
    public PdbxDepositGroup(String name, Map<String, Column> columns) {
        super(name, columns);
    }

    public PdbxDepositGroup(String name, int rowCount, Object[] encodedColumns) {
        super(name, rowCount, encodedColumns);
    }

    public PdbxDepositGroup(String name) {
        super(name);
    }

    /**
     * A unique identifier for a group of entries deposited as a collection.
     * @return SingleRowStrColumn
     */
    public SingleRowStrColumn getGroupId() {
        return (SingleRowStrColumn) (isText ? textFields.computeIfAbsent("group_id", SingleRowStrColumn::new) :
                getBinaryColumn("group_id"));
    }

    /**
     * A title to describe the group of entries deposited in the collection.
     * @return SingleRowStrColumn
     */
    public SingleRowStrColumn getGroupTitle() {
        return (SingleRowStrColumn) (isText ? textFields.computeIfAbsent("group_title", SingleRowStrColumn::new) :
                getBinaryColumn("group_title"));
    }

    /**
     * A description of the contents of entries in the collection.
     * @return SingleRowStrColumn
     */
    public SingleRowStrColumn getGroupDescription() {
        return (SingleRowStrColumn) (isText ? textFields.computeIfAbsent("group_description", SingleRowStrColumn::new) :
                getBinaryColumn("group_description"));
    }

    /**
     * Text to describe a grouping of entries in multiple collections
     * @return SingleRowStrColumn
     */
    public SingleRowStrColumn getGroupType() {
        return (SingleRowStrColumn) (isText ? textFields.computeIfAbsent("group_type", SingleRowStrColumn::new) :
                getBinaryColumn("group_type"));
    }
}

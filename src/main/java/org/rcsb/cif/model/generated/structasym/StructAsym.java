package org.rcsb.cif.model.generated.structasym;

import org.rcsb.cif.model.BaseCifCategory;
import org.rcsb.cif.model.CifColumn;

import java.util.Map;

public class StructAsym extends BaseCifCategory {
    public StructAsym(String name, Map<String, CifColumn> columns) {
        super(name, columns);
    }

    public StructAsym(String name, int rowCount, Object[] encodedColumns) {
        super(name, rowCount, encodedColumns);
    }

    /**
     * A description of special aspects of this portion of the contents
     * of the asymmetric unit.
     * @return Details
     */
    public Details getDetails() {
        return (Details) (isText ? getTextColumn("details") : getBinaryColumn("details", "Details"));
    }

    /**
     * This data item is a pointer to _entity.id in the ENTITY category.
     * @return EntityId
     */
    public EntityId getEntityId() {
        return (EntityId) (isText ? getTextColumn("entity_id") : getBinaryColumn("entity_id", "EntityId"));
    }

    /**
     * The value of _struct_asym.id must uniquely identify a record in
     * the STRUCT_ASYM list.
     * 
     * Note that this item need not be a number; it can be any unique
     * identifier.
     * @return Id
     */
    public Id getId() {
        return (Id) (isText ? getTextColumn("id") : getBinaryColumn("id", "Id"));
    }

    /**
     * This data item indicates whether the structural elements are modified.
     * @return PdbxModified
     */
    public PdbxModified getPdbxModified() {
        return (PdbxModified) (isText ? getTextColumn("pdbx_modified") : getBinaryColumn("pdbx_modified", "PdbxModified"));
    }

    /**
     * A flag indicating that this entity was originally labeled
     * with a blank PDB chain id.
     * @return PdbxBlankPDBChainidFlag
     */
    public PdbxBlankPDBChainidFlag getPdbxBlankPDBChainidFlag() {
        return (PdbxBlankPDBChainidFlag) (isText ? getTextColumn("pdbx_blank_PDB_chainid_flag") : getBinaryColumn("pdbx_blank_PDB_chainid_flag", "PdbxBlankPDBChainidFlag"));
    }
}

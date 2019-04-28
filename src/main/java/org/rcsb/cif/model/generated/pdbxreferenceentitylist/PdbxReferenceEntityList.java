package org.rcsb.cif.model.generated.pdbxreferenceentitylist;

import org.rcsb.cif.model.BaseCategory;
import org.rcsb.cif.model.Column;

import javax.annotation.Generated;
import java.util.Map;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class PdbxReferenceEntityList extends BaseCategory {
    public PdbxReferenceEntityList(String name, Map<String, Column> columns) {
        super(name, columns);
    }

    public PdbxReferenceEntityList(String name, int rowCount, Object[] encodedColumns) {
        super(name, rowCount, encodedColumns);
    }

    public PdbxReferenceEntityList(String name) {
        super(name);
    }

    /**
     * The value of _pdbx_reference_entity_list.prd_id is a reference
     * _pdbx_reference_molecule.prd_id in the PDBX_REFERENCE_MOLECULE category.
     * @return PrdId
     */
    public PrdId getPrdId() {
        return (PrdId) (isText ? textFields.computeIfAbsent("prd_id",
                PrdId::new) : getBinaryColumn("prd_id"));
    }

    /**
     * The value of _pdbx_reference_entity_list.ref_entity_id is a unique identifier
     * the a constituent entity within this reference molecule.
     * @return RefEntityId
     */
    public RefEntityId getRefEntityId() {
        return (RefEntityId) (isText ? textFields.computeIfAbsent("ref_entity_id",
                RefEntityId::new) : getBinaryColumn("ref_entity_id"));
    }

    /**
     * Defines the polymer characteristic of the entity.
     * @return Type
     */
    public Type getType() {
        return (Type) (isText ? textFields.computeIfAbsent("type",
                Type::new) : getBinaryColumn("type"));
    }

    /**
     * Additional details about this entity.
     * @return Details
     */
    public Details getDetails() {
        return (Details) (isText ? textFields.computeIfAbsent("details",
                Details::new) : getBinaryColumn("details"));
    }

    /**
     * The component number of this entity within the molecule.
     * @return ComponentId
     */
    public ComponentId getComponentId() {
        return (ComponentId) (isText ? textFields.computeIfAbsent("component_id",
                ComponentId::new) : getBinaryColumn("component_id"));
    }
}

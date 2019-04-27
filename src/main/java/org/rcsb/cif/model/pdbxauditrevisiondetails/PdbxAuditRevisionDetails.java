package org.rcsb.cif.model.pdbxauditrevisiondetails;

import org.rcsb.cif.model.BaseCategory;
import org.rcsb.cif.model.Column;

import javax.annotation.Generated;
import java.util.Map;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class PdbxAuditRevisionDetails extends BaseCategory {
    public PdbxAuditRevisionDetails(String name, Map<String, Column> columns) {
        super(name, columns);
    }

    public PdbxAuditRevisionDetails(String name, int rowCount, Object[] encodedColumns) {
        super(name, rowCount, encodedColumns);
    }

    public PdbxAuditRevisionDetails(String name) {
        super(name);
    }

    /**
     * A unique identifier for the pdbx_audit_revision_details record.
     * @return Ordinal
     */
    public Ordinal getOrdinal() {
        return (Ordinal) (isText ? textFields.computeIfAbsent("ordinal",
                Ordinal::new) : getBinaryColumn("ordinal"));
    }

    /**
     * A pointer to  _pdbx_audit_revision_history.ordinal
     * @return RevisionOrdinal
     */
    public RevisionOrdinal getRevisionOrdinal() {
        return (RevisionOrdinal) (isText ? textFields.computeIfAbsent("revision_ordinal",
                RevisionOrdinal::new) : getBinaryColumn("revision_ordinal"));
    }

    /**
     * The type of file that the pdbx_audit_revision_history record refers to.
     * @return DataContentType
     */
    public DataContentType getDataContentType() {
        return (DataContentType) (isText ? textFields.computeIfAbsent("data_content_type",
                DataContentType::new) : getBinaryColumn("data_content_type"));
    }

    /**
     * The provider of the revision.
     * @return Provider
     */
    public Provider getProvider() {
        return (Provider) (isText ? textFields.computeIfAbsent("provider",
                Provider::new) : getBinaryColumn("provider"));
    }

    /**
     * A type classification of the revision
     * @return Type
     */
    public Type getType() {
        return (Type) (isText ? textFields.computeIfAbsent("type",
                Type::new) : getBinaryColumn("type"));
    }

    /**
     * Additional details describing the revision.
     * @return Description
     */
    public Description getDescription() {
        return (Description) (isText ? textFields.computeIfAbsent("description",
                Description::new) : getBinaryColumn("description"));
    }
}

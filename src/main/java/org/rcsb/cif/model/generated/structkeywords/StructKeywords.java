package org.rcsb.cif.model.generated.structkeywords;

import org.rcsb.cif.model.BaseCategory;
import org.rcsb.cif.model.Column;

import javax.annotation.Generated;
import java.util.Map;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class StructKeywords extends BaseCategory {
    public StructKeywords(String name, Map<String, Column> columns) {
        super(name, columns);
    }

    public StructKeywords(String name, int rowCount, Object[] encodedColumns) {
        super(name, rowCount, encodedColumns);
    }

    public StructKeywords(String name) {
        super(name);
    }

    /**
     * This data item is a pointer to _entry.id in the ENTRY category.
     * @return EntryId
     */
    public EntryId getEntryId() {
        return (EntryId) (isText ? textFields.computeIfAbsent("entry_id",
                EntryId::new) : getBinaryColumn("entry_id"));
    }

    /**
     * Keywords describing this structure.
     * @return Text
     */
    public Text getText() {
        return (Text) (isText ? textFields.computeIfAbsent("text",
                Text::new) : getBinaryColumn("text"));
    }

    /**
     * Terms characterizing the macromolecular structure.
     * @return PdbxKeywords
     */
    public PdbxKeywords getPdbxKeywords() {
        return (PdbxKeywords) (isText ? textFields.computeIfAbsent("pdbx_keywords",
                PdbxKeywords::new) : getBinaryColumn("pdbx_keywords"));
    }

    /**
     * Keywords describing this structure.  This is constructed by the
     * PROGRAM for the PDB KEYWRD record.
     * @return PdbxDetails
     */
    public PdbxDetails getPdbxDetails() {
        return (PdbxDetails) (isText ? textFields.computeIfAbsent("pdbx_details",
                PdbxDetails::new) : getBinaryColumn("pdbx_details"));
    }
}

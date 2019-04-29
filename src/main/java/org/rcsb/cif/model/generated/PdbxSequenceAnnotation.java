package org.rcsb.cif.model.generated;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;
import java.util.Map;

/**
 * PDBX_SEQUENCE_ANNOTATION holds internal details about molecular sequences
 * described in the context of PDB chains.
 */
@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class PdbxSequenceAnnotation extends BaseCategory {
    public PdbxSequenceAnnotation(String name, Map<String, Column> columns) {
        super(name, columns);
    }

    public PdbxSequenceAnnotation(String name, int rowCount, Object[] encodedColumns) {
        super(name, rowCount, encodedColumns);
    }

    public PdbxSequenceAnnotation(String name) {
        super(name);
    }

    /**
     * PDB chain ID.
     * @return SingleRowStrColumn
     */
    public SingleRowStrColumn getPdbChainId() {
        return (SingleRowStrColumn) (isText ? textFields.computeIfAbsent("pdb_chain_id", SingleRowStrColumn::new) :
                getBinaryColumn("pdb_chain_id"));
    }

    /**
     * NCBI TaxID
     * @return SingleRowStrColumn
     */
    public SingleRowStrColumn getNcbiTaxid() {
        return (SingleRowStrColumn) (isText ? textFields.computeIfAbsent("ncbi_taxid", SingleRowStrColumn::new) :
                getBinaryColumn("ncbi_taxid"));
    }
}

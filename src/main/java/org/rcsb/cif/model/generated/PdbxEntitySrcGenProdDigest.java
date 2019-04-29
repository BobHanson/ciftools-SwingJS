package org.rcsb.cif.model.generated;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;
import java.util.Map;

/**
 * This category contains details for the DIGEST steps used in
 * the overall protein production process. The digestion is assumed
 * to be applied to the result of the previous production step, or the
 * gene source if this is the first production step.
 */
@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class PdbxEntitySrcGenProdDigest extends BaseCategory {
    public PdbxEntitySrcGenProdDigest(String name, Map<String, Column> columns) {
        super(name, columns);
    }

    public PdbxEntitySrcGenProdDigest(String name, int rowCount, Object[] encodedColumns) {
        super(name, rowCount, encodedColumns);
    }

    public PdbxEntitySrcGenProdDigest(String name) {
        super(name);
    }

    /**
     * The value of _pdbx_entity_src_gen_prod_digest.entry_id uniquely identifies
     * a sample consisting of one or more proteins whose structure is
     * to be determined. This is a pointer to _entry.id. This item may
     * be a site dependent bar code.
     * @return SingleRowStrColumn
     */
    public SingleRowStrColumn getEntryId() {
        return (SingleRowStrColumn) (isText ? textFields.computeIfAbsent("entry_id", SingleRowStrColumn::new) :
                getBinaryColumn("entry_id"));
    }

    /**
     * The value of _pdbx_entity_src_gen_prod_digest.entity_id uniquely identifies
     * each protein contained in the project target protein complex whose
     * structure is to be determined. This data item is a pointer to _entity.id
     * in the ENTITY category. This item may be a site dependent bar code.
     * @return SingleRowStrColumn
     */
    public SingleRowStrColumn getEntityId() {
        return (SingleRowStrColumn) (isText ? textFields.computeIfAbsent("entity_id", SingleRowStrColumn::new) :
                getBinaryColumn("entity_id"));
    }

    /**
     * This item is the unique identifier for this digestion step.
     * @return SingleRowIntColumn
     */
    public SingleRowIntColumn getStepId() {
        return (SingleRowIntColumn) (isText ? textFields.computeIfAbsent("step_id", SingleRowIntColumn::new) :
                getBinaryColumn("step_id"));
    }

    /**
     * This item unique identifier for the next production step. This allows
     * a workflow to have multiple entry points leading to a single product.
     * @return SingleRowIntColumn
     */
    public SingleRowIntColumn getNextStepId() {
        return (SingleRowIntColumn) (isText ? textFields.computeIfAbsent("next_step_id", SingleRowIntColumn::new) :
                getBinaryColumn("next_step_id"));
    }

    /**
     * This item is a pointer to pdbx_construct.id in the
     * PDBX_CONSTRUCT category. The referenced nucleic acid sequence
     * is that of the digest product
     * @return SingleRowStrColumn
     */
    public SingleRowStrColumn getEndConstructId() {
        return (SingleRowStrColumn) (isText ? textFields.computeIfAbsent("end_construct_id", SingleRowStrColumn::new) :
                getBinaryColumn("end_construct_id"));
    }

    /**
     * This data item is a pointer to pdbx_robot_system.id
     * in the PDBX_ROBOT_SYSTEM category.
     * @return SingleRowStrColumn
     */
    public SingleRowStrColumn getRobotId() {
        return (SingleRowStrColumn) (isText ? textFields.computeIfAbsent("robot_id", SingleRowStrColumn::new) :
                getBinaryColumn("robot_id"));
    }

    /**
     * The date of this production step.
     * @return SingleRowStrColumn
     */
    public SingleRowStrColumn getDate() {
        return (SingleRowStrColumn) (isText ? textFields.computeIfAbsent("date", SingleRowStrColumn::new) :
                getBinaryColumn("date"));
    }

    /**
     * The first enzyme used in the restriction digestion. The sites at
     * which this cuts can be derived from the sequence.
     * @return SingleRowStrColumn
     */
    public SingleRowStrColumn getRestrictionEnzyme1() {
        return (SingleRowStrColumn) (isText ? textFields.computeIfAbsent("restriction_enzyme_1", SingleRowStrColumn::new) :
                getBinaryColumn("restriction_enzyme_1"));
    }

    /**
     * The second enzyme used in the restriction digestion. The sites at
     * which this cuts can be derived from the sequence.
     * @return SingleRowStrColumn
     */
    public SingleRowStrColumn getRestrictionEnzyme2() {
        return (SingleRowStrColumn) (isText ? textFields.computeIfAbsent("restriction_enzyme_2", SingleRowStrColumn::new) :
                getBinaryColumn("restriction_enzyme_2"));
    }

    /**
     * String value containing details of any purification of the
     * product of the digestion.
     * @return SingleRowStrColumn
     */
    public SingleRowStrColumn getPurificationDetails() {
        return (SingleRowStrColumn) (isText ? textFields.computeIfAbsent("purification_details", SingleRowStrColumn::new) :
                getBinaryColumn("purification_details"));
    }

    /**
     * Summary of the details of restriction digestion any purification of the
     * product of the digestion.
     * @return SingleRowStrColumn
     */
    public SingleRowStrColumn getSummary() {
        return (SingleRowStrColumn) (isText ? textFields.computeIfAbsent("summary", SingleRowStrColumn::new) :
                getBinaryColumn("summary"));
    }
}

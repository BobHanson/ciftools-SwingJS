package org.rcsb.cif.model.pdbxentitysrcgenprodother;

import org.rcsb.cif.model.BaseCategory;
import org.rcsb.cif.model.Column;

import javax.annotation.Generated;
import java.util.Map;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class PdbxEntitySrcGenProdOther extends BaseCategory {
    public PdbxEntitySrcGenProdOther(String name, Map<String, Column> columns) {
        super(name, columns);
    }

    public PdbxEntitySrcGenProdOther(String name, int rowCount, Object[] encodedColumns) {
        super(name, rowCount, encodedColumns);
    }

    public PdbxEntitySrcGenProdOther(String name) {
        super(name);
    }

    /**
     * The value of _pdbx_entity_src_gen_prod_other.entry_id uniquely identifies
     * a sample consisting of one or more proteins whose structure is
     * to be determined. This is a pointer to _entry.id. This item may
     * be a site dependent bar code.
     * @return EntryId
     */
    public EntryId getEntryId() {
        return (EntryId) (isText ? textFields.computeIfAbsent("entry_id",
                EntryId::new) : getBinaryColumn("entry_id"));
    }

    /**
     * The value of _pdbx_entity_src_gen_prod_other.entity_id uniquely identifies
     * each protein contained in the project target protein complex whose
     * structure is to be determined. This data item is a pointer to _entity.id
     * in the ENTITY category. This item may be a site dependent bar code.
     * @return EntityId
     */
    public EntityId getEntityId() {
        return (EntityId) (isText ? textFields.computeIfAbsent("entity_id",
                EntityId::new) : getBinaryColumn("entity_id"));
    }

    /**
     * This item is the unique identifier for this process step.
     * @return StepId
     */
    public StepId getStepId() {
        return (StepId) (isText ? textFields.computeIfAbsent("step_id",
                StepId::new) : getBinaryColumn("step_id"));
    }

    /**
     * This item unique identifier for the next production step.
     * This allows a workflow to have multiple entry points leading
     * to a single product.
     * @return NextStepId
     */
    public NextStepId getNextStepId() {
        return (NextStepId) (isText ? textFields.computeIfAbsent("next_step_id",
                NextStepId::new) : getBinaryColumn("next_step_id"));
    }

    /**
     * This item is a pointer to pdbx_construct.id in the PDBX_CONSTRUCT
     * category. The referenced nucleic acid sequence is that of the
     * product of the process step.
     * @return EndConstructId
     */
    public EndConstructId getEndConstructId() {
        return (EndConstructId) (isText ? textFields.computeIfAbsent("end_construct_id",
                EndConstructId::new) : getBinaryColumn("end_construct_id"));
    }

    /**
     * This data item is a pointer to pdbx_robot_system.id in the
     * PDBX_ROBOT_SYSTEM category. The referenced robot is the
     * robot responsible for the process step
     * @return RobotId
     */
    public RobotId getRobotId() {
        return (RobotId) (isText ? textFields.computeIfAbsent("robot_id",
                RobotId::new) : getBinaryColumn("robot_id"));
    }

    /**
     * The date of this process step.
     * @return Date
     */
    public Date getDate() {
        return (Date) (isText ? textFields.computeIfAbsent("date",
                Date::new) : getBinaryColumn("date"));
    }

    /**
     * Name of this process step.
     * @return ProcessName
     */
    public ProcessName getProcessName() {
        return (ProcessName) (isText ? textFields.computeIfAbsent("process_name",
                ProcessName::new) : getBinaryColumn("process_name"));
    }

    /**
     * Additional details of this process step.
     * @return Details
     */
    public Details getDetails() {
        return (Details) (isText ? textFields.computeIfAbsent("details",
                Details::new) : getBinaryColumn("details"));
    }
}

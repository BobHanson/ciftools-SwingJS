package org.rcsb.cif.model.pdbxvalbond;

import org.rcsb.cif.model.BaseCategory;
import org.rcsb.cif.model.Column;

import javax.annotation.Generated;
import java.util.Map;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class PdbxValBond extends BaseCategory {
    public PdbxValBond(String name, Map<String, Column> columns) {
        super(name, columns);
    }

    public PdbxValBond(String name, int rowCount, Object[] encodedColumns) {
        super(name, rowCount, encodedColumns);
    }

    public PdbxValBond(String name) {
        super(name);
    }

    /**
     * The value of _pdbx_val_bond.id must uniquely identify
     * each item in the PDBX_VAL_BOND list.
     * This is an integer serial number.
     * @return Id
     */
    public Id getId() {
        return (Id) (isText ? textFields.computeIfAbsent("id",
                Id::new) : getBinaryColumn("id"));
    }

    /**
     * The model number for the given bond
     * @return ModelId
     */
    public ModelId getModelId() {
        return (ModelId) (isText ? textFields.computeIfAbsent("model_id",
                ModelId::new) : getBinaryColumn("model_id"));
    }

    /**
     * identifier of the first of the two atom sites that
     * define the covalent bond.
     * This data item is a pointer to _atom_site.auth_asym_id in the
     * ATOM_SITE category.
     * @return AuthAsymId1
     */
    public AuthAsymId1 getAuthAsymId1() {
        return (AuthAsymId1) (isText ? textFields.computeIfAbsent("auth_asym_id_1",
                AuthAsymId1::new) : getBinaryColumn("auth_asym_id_1"));
    }

    /**
     * identifier of the first of the two atom sites that
     * define the covalent bond.
     * This data item is a pointer to _atom_site.auth_atom_id in the
     * ATOM_SITE category.
     * @return AuthAtomId1
     */
    public AuthAtomId1 getAuthAtomId1() {
        return (AuthAtomId1) (isText ? textFields.computeIfAbsent("auth_atom_id_1",
                AuthAtomId1::new) : getBinaryColumn("auth_atom_id_1"));
    }

    /**
     * identifier of the first of the two atom sites that
     * define the covalent bond.
     * This data item is a pointer to _atom_site.auth_comp_id in the
     * ATOM_SITE category.
     * @return AuthCompId1
     */
    public AuthCompId1 getAuthCompId1() {
        return (AuthCompId1) (isText ? textFields.computeIfAbsent("auth_comp_id_1",
                AuthCompId1::new) : getBinaryColumn("auth_comp_id_1"));
    }

    /**
     * identifier of the first of the two atom sites that
     * define the covalent bond.
     * This data item is a pointer to _atom_site.auth_seq_id in the
     * ATOM_SITE category.
     * @return AuthSeqId1
     */
    public AuthSeqId1 getAuthSeqId1() {
        return (AuthSeqId1) (isText ? textFields.computeIfAbsent("auth_seq_id_1",
                AuthSeqId1::new) : getBinaryColumn("auth_seq_id_1"));
    }

    /**
     * identifier of the second of the two atom sites
     * that define the covalent bond.
     * This data item is a pointer to _atom_site.auth_atom_id in the
     * ATOM_SITE category.
     * @return AuthAtomId2
     */
    public AuthAtomId2 getAuthAtomId2() {
        return (AuthAtomId2) (isText ? textFields.computeIfAbsent("auth_atom_id_2",
                AuthAtomId2::new) : getBinaryColumn("auth_atom_id_2"));
    }

    /**
     * identifier of the second of the two atom sites
     * that define the covalent bond.
     * This data item is a pointer to _atom_site.auth_asym_id in the
     * ATOM_SITE category.
     * @return AuthAsymId2
     */
    public AuthAsymId2 getAuthAsymId2() {
        return (AuthAsymId2) (isText ? textFields.computeIfAbsent("auth_asym_id_2",
                AuthAsymId2::new) : getBinaryColumn("auth_asym_id_2"));
    }

    /**
     * identifier of the second of the two atom sites
     * that define the covalent bond.
     * This data item is a pointer to _atom_site.auth_comp_id in the
     * ATOM_SITE category.
     * @return AuthCompId2
     */
    public AuthCompId2 getAuthCompId2() {
        return (AuthCompId2) (isText ? textFields.computeIfAbsent("auth_comp_id_2",
                AuthCompId2::new) : getBinaryColumn("auth_comp_id_2"));
    }

    /**
     * identifier of the second of the two atom sites
     * that define the covalent bond.
     * This data item is a pointer to _atom_site.auth_seq_id in the
     * ATOM_SITE category.
     * @return AuthSeqId2
     */
    public AuthSeqId2 getAuthSeqId2() {
        return (AuthSeqId2) (isText ? textFields.computeIfAbsent("auth_seq_id_2",
                AuthSeqId2::new) : getBinaryColumn("auth_seq_id_2"));
    }

    /**
     * Optional identifier of the first of the two atom sites that
     * define the covalent bond.
     * @return AuthPDBInsertId1
     */
    public AuthPDBInsertId1 getAuthPDBInsertId1() {
        return (AuthPDBInsertId1) (isText ? textFields.computeIfAbsent("auth_PDB_insert_id_1",
                AuthPDBInsertId1::new) : getBinaryColumn("auth_PDB_insert_id_1"));
    }

    /**
     * Optional identifier of the second of the two atom sites that
     * define the covalent bond.
     * @return AuthPDBInsertId2
     */
    public AuthPDBInsertId2 getAuthPDBInsertId2() {
        return (AuthPDBInsertId2) (isText ? textFields.computeIfAbsent("auth_PDB_insert_id_2",
                AuthPDBInsertId2::new) : getBinaryColumn("auth_PDB_insert_id_2"));
    }

    /**
     * A component of the identifier for partner 1 of the bond
     * connection.
     * @return LabelAltId1
     */
    public LabelAltId1 getLabelAltId1() {
        return (LabelAltId1) (isText ? textFields.computeIfAbsent("label_alt_id_1",
                LabelAltId1::new) : getBinaryColumn("label_alt_id_1"));
    }

    /**
     * A component of the identifier for partner 1 of the bond
     * connection.
     * 
     * This data item is a pointer to _atom_site.label_asym_id in the
     * ATOM_SITE category.
     * @return LabelAsymId1
     */
    public LabelAsymId1 getLabelAsymId1() {
        return (LabelAsymId1) (isText ? textFields.computeIfAbsent("label_asym_id_1",
                LabelAsymId1::new) : getBinaryColumn("label_asym_id_1"));
    }

    /**
     * A component of the identifier for partner 1 of the bond
     * connection.
     * 
     * This data item is a pointer to _chem_comp_atom.atom_id in the
     * CHEM_COMP_ATOM category.
     * @return LabelAtomId1
     */
    public LabelAtomId1 getLabelAtomId1() {
        return (LabelAtomId1) (isText ? textFields.computeIfAbsent("label_atom_id_1",
                LabelAtomId1::new) : getBinaryColumn("label_atom_id_1"));
    }

    /**
     * A component of the identifier for partner 1 of the bond
     * connection.
     * 
     * This data item is a pointer to _atom_site.label_comp_id in the
     * ATOM_SITE category.
     * @return LabelCompId1
     */
    public LabelCompId1 getLabelCompId1() {
        return (LabelCompId1) (isText ? textFields.computeIfAbsent("label_comp_id_1",
                LabelCompId1::new) : getBinaryColumn("label_comp_id_1"));
    }

    /**
     * A component of the identifier for partner 1 of the bond
     * connection.
     * 
     * This data item is a pointer to _atom_site.label_seq_id in the
     * ATOM_SITE category.
     * @return LabelSeqId1
     */
    public LabelSeqId1 getLabelSeqId1() {
        return (LabelSeqId1) (isText ? textFields.computeIfAbsent("label_seq_id_1",
                LabelSeqId1::new) : getBinaryColumn("label_seq_id_1"));
    }

    /**
     * A component of the identifier for partner 2 of the bond
     * connection.
     * @return LabelAltId2
     */
    public LabelAltId2 getLabelAltId2() {
        return (LabelAltId2) (isText ? textFields.computeIfAbsent("label_alt_id_2",
                LabelAltId2::new) : getBinaryColumn("label_alt_id_2"));
    }

    /**
     * A component of the identifier for partner 2 of the bond
     * connection.
     * 
     * This data item is a pointer to _atom_site.label_asym_id in the
     * ATOM_SITE category.
     * @return LabelAsymId2
     */
    public LabelAsymId2 getLabelAsymId2() {
        return (LabelAsymId2) (isText ? textFields.computeIfAbsent("label_asym_id_2",
                LabelAsymId2::new) : getBinaryColumn("label_asym_id_2"));
    }

    /**
     * A component of the identifier for partner 2 of the bond
     * connection.
     * 
     * This data item is a pointer to _chem_comp_atom.atom_id in the
     * CHEM_COMP_ATOM category.
     * @return LabelAtomId2
     */
    public LabelAtomId2 getLabelAtomId2() {
        return (LabelAtomId2) (isText ? textFields.computeIfAbsent("label_atom_id_2",
                LabelAtomId2::new) : getBinaryColumn("label_atom_id_2"));
    }

    /**
     * A component of the identifier for partner 2 of the bond
     * connection.
     * 
     * This data item is a pointer to _atom_site.label_comp_id in the
     * ATOM_SITE category.
     * @return LabelCompId2
     */
    public LabelCompId2 getLabelCompId2() {
        return (LabelCompId2) (isText ? textFields.computeIfAbsent("label_comp_id_2",
                LabelCompId2::new) : getBinaryColumn("label_comp_id_2"));
    }

    /**
     * A component of the identifier for partner 2 of the bond
     * connection.
     * 
     * This data item is a pointer to _atom_site.label_seq_id in the
     * ATOM_SITE category.
     * @return LabelSeqId2
     */
    public LabelSeqId2 getLabelSeqId2() {
        return (LabelSeqId2) (isText ? textFields.computeIfAbsent("label_seq_id_2",
                LabelSeqId2::new) : getBinaryColumn("label_seq_id_2"));
    }

    /**
     * The value of the bond distance deviating by more than
     * 6*RMSD from the standard dictionary value.
     * @return Bond
     */
    public Bond getBond() {
        return (Bond) (isText ? textFields.computeIfAbsent("bond",
                Bond::new) : getBinaryColumn("bond"));
    }

    /**
     * The value of the deviation from ideal
     * for the defined covalent bond for the two atoms defined.
     * @return BondDeviation
     */
    public BondDeviation getBondDeviation() {
        return (BondDeviation) (isText ? textFields.computeIfAbsent("bond_deviation",
                BondDeviation::new) : getBinaryColumn("bond_deviation"));
    }
}

package org.rcsb.cif.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.rcsb.cif.model.Category;
import org.rcsb.cif.model.ModelFactory;

public class BaseBlock implements CifBlock {
    private final Map<String, Category> categories;
    private final List<CifBlock> saveFrames;
    private final String header;

    public BaseBlock(Map<String, Category> categories, String header, List<CifBlock> saveFrames) {
        this.categories = categories;
        this.saveFrames = saveFrames;
        this.header = header;
    }

    public BaseBlock(Map<String, Category> categories, String header) {
        this(categories, header, new ArrayList<>());
    }

    @Override
    public String getBlockHeader() {
        return header;
    }

    @Override
    public Category getCategory(String name) {
        // try to return category, if unknown and not present, return empty category
        return categories.computeIfAbsent(name, ModelFactory::createEmptyCategory);
    }

    @Override
    public List<String> getCategoryNames() {
        return new ArrayList<>(categories.keySet());
    }

    @Override
    public List<CifBlock> getSaveFrames() {
        return saveFrames;
    }

    public org.rcsb.cif.api.generated.AtomSite getAtomSite() {
        return (org.rcsb.cif.api.generated.AtomSite) categories.computeIfAbsent("atom_site",
                org.rcsb.cif.api.generated.AtomSite::new).get();
    }

    public org.rcsb.cif.api.generated.AtomSiteAnisotrop getAtomSiteAnisotrop() {
        return (org.rcsb.cif.api.generated.AtomSiteAnisotrop) categories.computeIfAbsent("atom_site_anisotrop",
                org.rcsb.cif.api.generated.AtomSiteAnisotrop::new).get();
    }

    public org.rcsb.cif.api.generated.AtomSites getAtomSites() {
        return (org.rcsb.cif.api.generated.AtomSites) categories.computeIfAbsent("atom_sites",
                org.rcsb.cif.api.generated.AtomSites::new).get();
    }

    public org.rcsb.cif.api.generated.AtomSitesAlt getAtomSitesAlt() {
        return (org.rcsb.cif.api.generated.AtomSitesAlt) categories.computeIfAbsent("atom_sites_alt",
                org.rcsb.cif.api.generated.AtomSitesAlt::new).get();
    }

    public org.rcsb.cif.api.generated.AtomSitesAltEns getAtomSitesAltEns() {
        return (org.rcsb.cif.api.generated.AtomSitesAltEns) categories.computeIfAbsent("atom_sites_alt_ens",
                org.rcsb.cif.api.generated.AtomSitesAltEns::new).get();
    }

    public org.rcsb.cif.api.generated.AtomSitesAltGen getAtomSitesAltGen() {
        return (org.rcsb.cif.api.generated.AtomSitesAltGen) categories.computeIfAbsent("atom_sites_alt_gen",
                org.rcsb.cif.api.generated.AtomSitesAltGen::new).get();
    }

    public org.rcsb.cif.api.generated.AtomSitesFootnote getAtomSitesFootnote() {
        return (org.rcsb.cif.api.generated.AtomSitesFootnote) categories.computeIfAbsent("atom_sites_footnote",
                org.rcsb.cif.api.generated.AtomSitesFootnote::new).get();
    }

    public org.rcsb.cif.api.generated.AtomType getAtomType() {
        return (org.rcsb.cif.api.generated.AtomType) categories.computeIfAbsent("atom_type",
                org.rcsb.cif.api.generated.AtomType::new).get();
    }

    public org.rcsb.cif.api.generated.Audit getAudit() {
        return (org.rcsb.cif.api.generated.Audit) categories.computeIfAbsent("audit",
                org.rcsb.cif.api.generated.Audit::new).get();
    }

    public org.rcsb.cif.api.generated.AuditAuthor getAuditAuthor() {
        return (org.rcsb.cif.api.generated.AuditAuthor) categories.computeIfAbsent("audit_author",
                org.rcsb.cif.api.generated.AuditAuthor::new).get();
    }

    public org.rcsb.cif.api.generated.AuditConform getAuditConform() {
        return (org.rcsb.cif.api.generated.AuditConform) categories.computeIfAbsent("audit_conform",
                org.rcsb.cif.api.generated.AuditConform::new).get();
    }

    public org.rcsb.cif.api.generated.AuditContactAuthor getAuditContactAuthor() {
        return (org.rcsb.cif.api.generated.AuditContactAuthor) categories.computeIfAbsent("audit_contact_author",
                org.rcsb.cif.api.generated.AuditContactAuthor::new).get();
    }

    public org.rcsb.cif.api.generated.Cell getCell() {
        return (org.rcsb.cif.api.generated.Cell) categories.computeIfAbsent("cell",
                org.rcsb.cif.api.generated.Cell::new).get();
    }

    public org.rcsb.cif.api.generated.CellMeasurement getCellMeasurement() {
        return (org.rcsb.cif.api.generated.CellMeasurement) categories.computeIfAbsent("cell_measurement",
                org.rcsb.cif.api.generated.CellMeasurement::new).get();
    }

    public org.rcsb.cif.api.generated.CellMeasurementRefln getCellMeasurementRefln() {
        return (org.rcsb.cif.api.generated.CellMeasurementRefln) categories.computeIfAbsent("cell_measurement_refln",
                org.rcsb.cif.api.generated.CellMeasurementRefln::new).get();
    }

    public org.rcsb.cif.api.generated.ChemComp getChemComp() {
        return (org.rcsb.cif.api.generated.ChemComp) categories.computeIfAbsent("chem_comp",
                org.rcsb.cif.api.generated.ChemComp::new).get();
    }

    public org.rcsb.cif.api.generated.ChemCompAngle getChemCompAngle() {
        return (org.rcsb.cif.api.generated.ChemCompAngle) categories.computeIfAbsent("chem_comp_angle",
                org.rcsb.cif.api.generated.ChemCompAngle::new).get();
    }

    public org.rcsb.cif.api.generated.ChemCompAtom getChemCompAtom() {
        return (org.rcsb.cif.api.generated.ChemCompAtom) categories.computeIfAbsent("chem_comp_atom",
                org.rcsb.cif.api.generated.ChemCompAtom::new).get();
    }

    public org.rcsb.cif.api.generated.ChemCompBond getChemCompBond() {
        return (org.rcsb.cif.api.generated.ChemCompBond) categories.computeIfAbsent("chem_comp_bond",
                org.rcsb.cif.api.generated.ChemCompBond::new).get();
    }

    public org.rcsb.cif.api.generated.ChemCompChir getChemCompChir() {
        return (org.rcsb.cif.api.generated.ChemCompChir) categories.computeIfAbsent("chem_comp_chir",
                org.rcsb.cif.api.generated.ChemCompChir::new).get();
    }

    public org.rcsb.cif.api.generated.ChemCompChirAtom getChemCompChirAtom() {
        return (org.rcsb.cif.api.generated.ChemCompChirAtom) categories.computeIfAbsent("chem_comp_chir_atom",
                org.rcsb.cif.api.generated.ChemCompChirAtom::new).get();
    }

    public org.rcsb.cif.api.generated.ChemCompLink getChemCompLink() {
        return (org.rcsb.cif.api.generated.ChemCompLink) categories.computeIfAbsent("chem_comp_link",
                org.rcsb.cif.api.generated.ChemCompLink::new).get();
    }

    public org.rcsb.cif.api.generated.ChemCompPlane getChemCompPlane() {
        return (org.rcsb.cif.api.generated.ChemCompPlane) categories.computeIfAbsent("chem_comp_plane",
                org.rcsb.cif.api.generated.ChemCompPlane::new).get();
    }

    public org.rcsb.cif.api.generated.ChemCompPlaneAtom getChemCompPlaneAtom() {
        return (org.rcsb.cif.api.generated.ChemCompPlaneAtom) categories.computeIfAbsent("chem_comp_plane_atom",
                org.rcsb.cif.api.generated.ChemCompPlaneAtom::new).get();
    }

    public org.rcsb.cif.api.generated.ChemCompTor getChemCompTor() {
        return (org.rcsb.cif.api.generated.ChemCompTor) categories.computeIfAbsent("chem_comp_tor",
                org.rcsb.cif.api.generated.ChemCompTor::new).get();
    }

    public org.rcsb.cif.api.generated.ChemCompTorValue getChemCompTorValue() {
        return (org.rcsb.cif.api.generated.ChemCompTorValue) categories.computeIfAbsent("chem_comp_tor_value",
                org.rcsb.cif.api.generated.ChemCompTorValue::new).get();
    }

    public org.rcsb.cif.api.generated.ChemLink getChemLink() {
        return (org.rcsb.cif.api.generated.ChemLink) categories.computeIfAbsent("chem_link",
                org.rcsb.cif.api.generated.ChemLink::new).get();
    }

    public org.rcsb.cif.api.generated.ChemLinkAngle getChemLinkAngle() {
        return (org.rcsb.cif.api.generated.ChemLinkAngle) categories.computeIfAbsent("chem_link_angle",
                org.rcsb.cif.api.generated.ChemLinkAngle::new).get();
    }

    public org.rcsb.cif.api.generated.ChemLinkBond getChemLinkBond() {
        return (org.rcsb.cif.api.generated.ChemLinkBond) categories.computeIfAbsent("chem_link_bond",
                org.rcsb.cif.api.generated.ChemLinkBond::new).get();
    }

    public org.rcsb.cif.api.generated.ChemLinkChir getChemLinkChir() {
        return (org.rcsb.cif.api.generated.ChemLinkChir) categories.computeIfAbsent("chem_link_chir",
                org.rcsb.cif.api.generated.ChemLinkChir::new).get();
    }

    public org.rcsb.cif.api.generated.ChemLinkChirAtom getChemLinkChirAtom() {
        return (org.rcsb.cif.api.generated.ChemLinkChirAtom) categories.computeIfAbsent("chem_link_chir_atom",
                org.rcsb.cif.api.generated.ChemLinkChirAtom::new).get();
    }

    public org.rcsb.cif.api.generated.ChemLinkPlane getChemLinkPlane() {
        return (org.rcsb.cif.api.generated.ChemLinkPlane) categories.computeIfAbsent("chem_link_plane",
                org.rcsb.cif.api.generated.ChemLinkPlane::new).get();
    }

    public org.rcsb.cif.api.generated.ChemLinkPlaneAtom getChemLinkPlaneAtom() {
        return (org.rcsb.cif.api.generated.ChemLinkPlaneAtom) categories.computeIfAbsent("chem_link_plane_atom",
                org.rcsb.cif.api.generated.ChemLinkPlaneAtom::new).get();
    }

    public org.rcsb.cif.api.generated.ChemLinkTor getChemLinkTor() {
        return (org.rcsb.cif.api.generated.ChemLinkTor) categories.computeIfAbsent("chem_link_tor",
                org.rcsb.cif.api.generated.ChemLinkTor::new).get();
    }

    public org.rcsb.cif.api.generated.ChemLinkTorValue getChemLinkTorValue() {
        return (org.rcsb.cif.api.generated.ChemLinkTorValue) categories.computeIfAbsent("chem_link_tor_value",
                org.rcsb.cif.api.generated.ChemLinkTorValue::new).get();
    }

    public org.rcsb.cif.api.generated.Chemical getChemical() {
        return (org.rcsb.cif.api.generated.Chemical) categories.computeIfAbsent("chemical",
                org.rcsb.cif.api.generated.Chemical::new).get();
    }

    public org.rcsb.cif.api.generated.ChemicalConnAtom getChemicalConnAtom() {
        return (org.rcsb.cif.api.generated.ChemicalConnAtom) categories.computeIfAbsent("chemical_conn_atom",
                org.rcsb.cif.api.generated.ChemicalConnAtom::new).get();
    }

    public org.rcsb.cif.api.generated.ChemicalConnBond getChemicalConnBond() {
        return (org.rcsb.cif.api.generated.ChemicalConnBond) categories.computeIfAbsent("chemical_conn_bond",
                org.rcsb.cif.api.generated.ChemicalConnBond::new).get();
    }

    public org.rcsb.cif.api.generated.ChemicalFormula getChemicalFormula() {
        return (org.rcsb.cif.api.generated.ChemicalFormula) categories.computeIfAbsent("chemical_formula",
                org.rcsb.cif.api.generated.ChemicalFormula::new).get();
    }

    public org.rcsb.cif.api.generated.Citation getCitation() {
        return (org.rcsb.cif.api.generated.Citation) categories.computeIfAbsent("citation",
                org.rcsb.cif.api.generated.Citation::new).get();
    }

    public org.rcsb.cif.api.generated.CitationAuthor getCitationAuthor() {
        return (org.rcsb.cif.api.generated.CitationAuthor) categories.computeIfAbsent("citation_author",
                org.rcsb.cif.api.generated.CitationAuthor::new).get();
    }

    public org.rcsb.cif.api.generated.CitationEditor getCitationEditor() {
        return (org.rcsb.cif.api.generated.CitationEditor) categories.computeIfAbsent("citation_editor",
                org.rcsb.cif.api.generated.CitationEditor::new).get();
    }

    public org.rcsb.cif.api.generated.Computing getComputing() {
        return (org.rcsb.cif.api.generated.Computing) categories.computeIfAbsent("computing",
                org.rcsb.cif.api.generated.Computing::new).get();
    }

    public org.rcsb.cif.api.generated.Database getDatabase() {
        return (org.rcsb.cif.api.generated.Database) categories.computeIfAbsent("database",
                org.rcsb.cif.api.generated.Database::new).get();
    }

    public org.rcsb.cif.api.generated.Database2 getDatabase2() {
        return (org.rcsb.cif.api.generated.Database2) categories.computeIfAbsent("database_2",
                org.rcsb.cif.api.generated.Database2::new).get();
    }

    public org.rcsb.cif.api.generated.DatabasePDBCaveat getDatabasePDBCaveat() {
        return (org.rcsb.cif.api.generated.DatabasePDBCaveat) categories.computeIfAbsent("database_PDB_caveat",
                org.rcsb.cif.api.generated.DatabasePDBCaveat::new).get();
    }

    public org.rcsb.cif.api.generated.DatabasePDBMatrix getDatabasePDBMatrix() {
        return (org.rcsb.cif.api.generated.DatabasePDBMatrix) categories.computeIfAbsent("database_PDB_matrix",
                org.rcsb.cif.api.generated.DatabasePDBMatrix::new).get();
    }

    public org.rcsb.cif.api.generated.DatabasePDBRemark getDatabasePDBRemark() {
        return (org.rcsb.cif.api.generated.DatabasePDBRemark) categories.computeIfAbsent("database_PDB_remark",
                org.rcsb.cif.api.generated.DatabasePDBRemark::new).get();
    }

    public org.rcsb.cif.api.generated.DatabasePDBRev getDatabasePDBRev() {
        return (org.rcsb.cif.api.generated.DatabasePDBRev) categories.computeIfAbsent("database_PDB_rev",
                org.rcsb.cif.api.generated.DatabasePDBRev::new).get();
    }

    public org.rcsb.cif.api.generated.DatabasePDBRevRecord getDatabasePDBRevRecord() {
        return (org.rcsb.cif.api.generated.DatabasePDBRevRecord) categories.computeIfAbsent("database_PDB_rev_record",
                org.rcsb.cif.api.generated.DatabasePDBRevRecord::new).get();
    }

    public org.rcsb.cif.api.generated.DatabasePDBTvect getDatabasePDBTvect() {
        return (org.rcsb.cif.api.generated.DatabasePDBTvect) categories.computeIfAbsent("database_PDB_tvect",
                org.rcsb.cif.api.generated.DatabasePDBTvect::new).get();
    }

    public org.rcsb.cif.api.generated.Diffrn getDiffrn() {
        return (org.rcsb.cif.api.generated.Diffrn) categories.computeIfAbsent("diffrn",
                org.rcsb.cif.api.generated.Diffrn::new).get();
    }

    public org.rcsb.cif.api.generated.DiffrnAttenuator getDiffrnAttenuator() {
        return (org.rcsb.cif.api.generated.DiffrnAttenuator) categories.computeIfAbsent("diffrn_attenuator",
                org.rcsb.cif.api.generated.DiffrnAttenuator::new).get();
    }

    public org.rcsb.cif.api.generated.DiffrnDetector getDiffrnDetector() {
        return (org.rcsb.cif.api.generated.DiffrnDetector) categories.computeIfAbsent("diffrn_detector",
                org.rcsb.cif.api.generated.DiffrnDetector::new).get();
    }

    public org.rcsb.cif.api.generated.DiffrnMeasurement getDiffrnMeasurement() {
        return (org.rcsb.cif.api.generated.DiffrnMeasurement) categories.computeIfAbsent("diffrn_measurement",
                org.rcsb.cif.api.generated.DiffrnMeasurement::new).get();
    }

    public org.rcsb.cif.api.generated.DiffrnOrientMatrix getDiffrnOrientMatrix() {
        return (org.rcsb.cif.api.generated.DiffrnOrientMatrix) categories.computeIfAbsent("diffrn_orient_matrix",
                org.rcsb.cif.api.generated.DiffrnOrientMatrix::new).get();
    }

    public org.rcsb.cif.api.generated.DiffrnOrientRefln getDiffrnOrientRefln() {
        return (org.rcsb.cif.api.generated.DiffrnOrientRefln) categories.computeIfAbsent("diffrn_orient_refln",
                org.rcsb.cif.api.generated.DiffrnOrientRefln::new).get();
    }

    public org.rcsb.cif.api.generated.DiffrnRadiation getDiffrnRadiation() {
        return (org.rcsb.cif.api.generated.DiffrnRadiation) categories.computeIfAbsent("diffrn_radiation",
                org.rcsb.cif.api.generated.DiffrnRadiation::new).get();
    }

    public org.rcsb.cif.api.generated.DiffrnRadiationWavelength getDiffrnRadiationWavelength() {
        return (org.rcsb.cif.api.generated.DiffrnRadiationWavelength) categories.computeIfAbsent("diffrn_radiation_wavelength",
                org.rcsb.cif.api.generated.DiffrnRadiationWavelength::new).get();
    }

    public org.rcsb.cif.api.generated.DiffrnRefln getDiffrnRefln() {
        return (org.rcsb.cif.api.generated.DiffrnRefln) categories.computeIfAbsent("diffrn_refln",
                org.rcsb.cif.api.generated.DiffrnRefln::new).get();
    }

    public org.rcsb.cif.api.generated.DiffrnReflns getDiffrnReflns() {
        return (org.rcsb.cif.api.generated.DiffrnReflns) categories.computeIfAbsent("diffrn_reflns",
                org.rcsb.cif.api.generated.DiffrnReflns::new).get();
    }

    public org.rcsb.cif.api.generated.DiffrnScaleGroup getDiffrnScaleGroup() {
        return (org.rcsb.cif.api.generated.DiffrnScaleGroup) categories.computeIfAbsent("diffrn_scale_group",
                org.rcsb.cif.api.generated.DiffrnScaleGroup::new).get();
    }

    public org.rcsb.cif.api.generated.DiffrnSource getDiffrnSource() {
        return (org.rcsb.cif.api.generated.DiffrnSource) categories.computeIfAbsent("diffrn_source",
                org.rcsb.cif.api.generated.DiffrnSource::new).get();
    }

    public org.rcsb.cif.api.generated.DiffrnStandardRefln getDiffrnStandardRefln() {
        return (org.rcsb.cif.api.generated.DiffrnStandardRefln) categories.computeIfAbsent("diffrn_standard_refln",
                org.rcsb.cif.api.generated.DiffrnStandardRefln::new).get();
    }

    public org.rcsb.cif.api.generated.DiffrnStandards getDiffrnStandards() {
        return (org.rcsb.cif.api.generated.DiffrnStandards) categories.computeIfAbsent("diffrn_standards",
                org.rcsb.cif.api.generated.DiffrnStandards::new).get();
    }

    public org.rcsb.cif.api.generated.Entity getEntity() {
        return (org.rcsb.cif.api.generated.Entity) categories.computeIfAbsent("entity",
                org.rcsb.cif.api.generated.Entity::new).get();
    }

    public org.rcsb.cif.api.generated.EntityKeywords getEntityKeywords() {
        return (org.rcsb.cif.api.generated.EntityKeywords) categories.computeIfAbsent("entity_keywords",
                org.rcsb.cif.api.generated.EntityKeywords::new).get();
    }

    public org.rcsb.cif.api.generated.EntityLink getEntityLink() {
        return (org.rcsb.cif.api.generated.EntityLink) categories.computeIfAbsent("entity_link",
                org.rcsb.cif.api.generated.EntityLink::new).get();
    }

    public org.rcsb.cif.api.generated.EntityNameCom getEntityNameCom() {
        return (org.rcsb.cif.api.generated.EntityNameCom) categories.computeIfAbsent("entity_name_com",
                org.rcsb.cif.api.generated.EntityNameCom::new).get();
    }

    public org.rcsb.cif.api.generated.EntityNameSys getEntityNameSys() {
        return (org.rcsb.cif.api.generated.EntityNameSys) categories.computeIfAbsent("entity_name_sys",
                org.rcsb.cif.api.generated.EntityNameSys::new).get();
    }

    public org.rcsb.cif.api.generated.EntityPoly getEntityPoly() {
        return (org.rcsb.cif.api.generated.EntityPoly) categories.computeIfAbsent("entity_poly",
                org.rcsb.cif.api.generated.EntityPoly::new).get();
    }

    public org.rcsb.cif.api.generated.EntityPolySeq getEntityPolySeq() {
        return (org.rcsb.cif.api.generated.EntityPolySeq) categories.computeIfAbsent("entity_poly_seq",
                org.rcsb.cif.api.generated.EntityPolySeq::new).get();
    }

    public org.rcsb.cif.api.generated.Entry getEntry() {
        return (org.rcsb.cif.api.generated.Entry) categories.computeIfAbsent("entry",
                org.rcsb.cif.api.generated.Entry::new).get();
    }

    public org.rcsb.cif.api.generated.EntryLink getEntryLink() {
        return (org.rcsb.cif.api.generated.EntryLink) categories.computeIfAbsent("entry_link",
                org.rcsb.cif.api.generated.EntryLink::new).get();
    }

    public org.rcsb.cif.api.generated.Exptl getExptl() {
        return (org.rcsb.cif.api.generated.Exptl) categories.computeIfAbsent("exptl",
                org.rcsb.cif.api.generated.Exptl::new).get();
    }

    public org.rcsb.cif.api.generated.ExptlCrystal getExptlCrystal() {
        return (org.rcsb.cif.api.generated.ExptlCrystal) categories.computeIfAbsent("exptl_crystal",
                org.rcsb.cif.api.generated.ExptlCrystal::new).get();
    }

    public org.rcsb.cif.api.generated.ExptlCrystalFace getExptlCrystalFace() {
        return (org.rcsb.cif.api.generated.ExptlCrystalFace) categories.computeIfAbsent("exptl_crystal_face",
                org.rcsb.cif.api.generated.ExptlCrystalFace::new).get();
    }

    public org.rcsb.cif.api.generated.ExptlCrystalGrow getExptlCrystalGrow() {
        return (org.rcsb.cif.api.generated.ExptlCrystalGrow) categories.computeIfAbsent("exptl_crystal_grow",
                org.rcsb.cif.api.generated.ExptlCrystalGrow::new).get();
    }

    public org.rcsb.cif.api.generated.ExptlCrystalGrowComp getExptlCrystalGrowComp() {
        return (org.rcsb.cif.api.generated.ExptlCrystalGrowComp) categories.computeIfAbsent("exptl_crystal_grow_comp",
                org.rcsb.cif.api.generated.ExptlCrystalGrowComp::new).get();
    }

    public org.rcsb.cif.api.generated.Geom getGeom() {
        return (org.rcsb.cif.api.generated.Geom) categories.computeIfAbsent("geom",
                org.rcsb.cif.api.generated.Geom::new).get();
    }

    public org.rcsb.cif.api.generated.GeomAngle getGeomAngle() {
        return (org.rcsb.cif.api.generated.GeomAngle) categories.computeIfAbsent("geom_angle",
                org.rcsb.cif.api.generated.GeomAngle::new).get();
    }

    public org.rcsb.cif.api.generated.GeomBond getGeomBond() {
        return (org.rcsb.cif.api.generated.GeomBond) categories.computeIfAbsent("geom_bond",
                org.rcsb.cif.api.generated.GeomBond::new).get();
    }

    public org.rcsb.cif.api.generated.GeomContact getGeomContact() {
        return (org.rcsb.cif.api.generated.GeomContact) categories.computeIfAbsent("geom_contact",
                org.rcsb.cif.api.generated.GeomContact::new).get();
    }

    public org.rcsb.cif.api.generated.GeomHbond getGeomHbond() {
        return (org.rcsb.cif.api.generated.GeomHbond) categories.computeIfAbsent("geom_hbond",
                org.rcsb.cif.api.generated.GeomHbond::new).get();
    }

    public org.rcsb.cif.api.generated.GeomTorsion getGeomTorsion() {
        return (org.rcsb.cif.api.generated.GeomTorsion) categories.computeIfAbsent("geom_torsion",
                org.rcsb.cif.api.generated.GeomTorsion::new).get();
    }

    public org.rcsb.cif.api.generated.Journal getJournal() {
        return (org.rcsb.cif.api.generated.Journal) categories.computeIfAbsent("journal",
                org.rcsb.cif.api.generated.Journal::new).get();
    }

    public org.rcsb.cif.api.generated.JournalIndex getJournalIndex() {
        return (org.rcsb.cif.api.generated.JournalIndex) categories.computeIfAbsent("journal_index",
                org.rcsb.cif.api.generated.JournalIndex::new).get();
    }

    public org.rcsb.cif.api.generated.Phasing getPhasing() {
        return (org.rcsb.cif.api.generated.Phasing) categories.computeIfAbsent("phasing",
                org.rcsb.cif.api.generated.Phasing::new).get();
    }

    public org.rcsb.cif.api.generated.PhasingAveraging getPhasingAveraging() {
        return (org.rcsb.cif.api.generated.PhasingAveraging) categories.computeIfAbsent("phasing_averaging",
                org.rcsb.cif.api.generated.PhasingAveraging::new).get();
    }

    public org.rcsb.cif.api.generated.PhasingIsomorphous getPhasingIsomorphous() {
        return (org.rcsb.cif.api.generated.PhasingIsomorphous) categories.computeIfAbsent("phasing_isomorphous",
                org.rcsb.cif.api.generated.PhasingIsomorphous::new).get();
    }

    public org.rcsb.cif.api.generated.PhasingMAD getPhasingMAD() {
        return (org.rcsb.cif.api.generated.PhasingMAD) categories.computeIfAbsent("phasing_MAD",
                org.rcsb.cif.api.generated.PhasingMAD::new).get();
    }

    public org.rcsb.cif.api.generated.PhasingMADClust getPhasingMADClust() {
        return (org.rcsb.cif.api.generated.PhasingMADClust) categories.computeIfAbsent("phasing_MAD_clust",
                org.rcsb.cif.api.generated.PhasingMADClust::new).get();
    }

    public org.rcsb.cif.api.generated.PhasingMADExpt getPhasingMADExpt() {
        return (org.rcsb.cif.api.generated.PhasingMADExpt) categories.computeIfAbsent("phasing_MAD_expt",
                org.rcsb.cif.api.generated.PhasingMADExpt::new).get();
    }

    public org.rcsb.cif.api.generated.PhasingMADRatio getPhasingMADRatio() {
        return (org.rcsb.cif.api.generated.PhasingMADRatio) categories.computeIfAbsent("phasing_MAD_ratio",
                org.rcsb.cif.api.generated.PhasingMADRatio::new).get();
    }

    public org.rcsb.cif.api.generated.PhasingMADSet getPhasingMADSet() {
        return (org.rcsb.cif.api.generated.PhasingMADSet) categories.computeIfAbsent("phasing_MAD_set",
                org.rcsb.cif.api.generated.PhasingMADSet::new).get();
    }

    public org.rcsb.cif.api.generated.PhasingMIR getPhasingMIR() {
        return (org.rcsb.cif.api.generated.PhasingMIR) categories.computeIfAbsent("phasing_MIR",
                org.rcsb.cif.api.generated.PhasingMIR::new).get();
    }

    public org.rcsb.cif.api.generated.PhasingMIRDer getPhasingMIRDer() {
        return (org.rcsb.cif.api.generated.PhasingMIRDer) categories.computeIfAbsent("phasing_MIR_der",
                org.rcsb.cif.api.generated.PhasingMIRDer::new).get();
    }

    public org.rcsb.cif.api.generated.PhasingMIRDerRefln getPhasingMIRDerRefln() {
        return (org.rcsb.cif.api.generated.PhasingMIRDerRefln) categories.computeIfAbsent("phasing_MIR_der_refln",
                org.rcsb.cif.api.generated.PhasingMIRDerRefln::new).get();
    }

    public org.rcsb.cif.api.generated.PhasingMIRDerShell getPhasingMIRDerShell() {
        return (org.rcsb.cif.api.generated.PhasingMIRDerShell) categories.computeIfAbsent("phasing_MIR_der_shell",
                org.rcsb.cif.api.generated.PhasingMIRDerShell::new).get();
    }

    public org.rcsb.cif.api.generated.PhasingMIRDerSite getPhasingMIRDerSite() {
        return (org.rcsb.cif.api.generated.PhasingMIRDerSite) categories.computeIfAbsent("phasing_MIR_der_site",
                org.rcsb.cif.api.generated.PhasingMIRDerSite::new).get();
    }

    public org.rcsb.cif.api.generated.PhasingMIRShell getPhasingMIRShell() {
        return (org.rcsb.cif.api.generated.PhasingMIRShell) categories.computeIfAbsent("phasing_MIR_shell",
                org.rcsb.cif.api.generated.PhasingMIRShell::new).get();
    }

    public org.rcsb.cif.api.generated.PhasingSet getPhasingSet() {
        return (org.rcsb.cif.api.generated.PhasingSet) categories.computeIfAbsent("phasing_set",
                org.rcsb.cif.api.generated.PhasingSet::new).get();
    }

    public org.rcsb.cif.api.generated.PhasingSetRefln getPhasingSetRefln() {
        return (org.rcsb.cif.api.generated.PhasingSetRefln) categories.computeIfAbsent("phasing_set_refln",
                org.rcsb.cif.api.generated.PhasingSetRefln::new).get();
    }

    public org.rcsb.cif.api.generated.Publ getPubl() {
        return (org.rcsb.cif.api.generated.Publ) categories.computeIfAbsent("publ",
                org.rcsb.cif.api.generated.Publ::new).get();
    }

    public org.rcsb.cif.api.generated.PublAuthor getPublAuthor() {
        return (org.rcsb.cif.api.generated.PublAuthor) categories.computeIfAbsent("publ_author",
                org.rcsb.cif.api.generated.PublAuthor::new).get();
    }

    public org.rcsb.cif.api.generated.PublBody getPublBody() {
        return (org.rcsb.cif.api.generated.PublBody) categories.computeIfAbsent("publ_body",
                org.rcsb.cif.api.generated.PublBody::new).get();
    }

    public org.rcsb.cif.api.generated.PublManuscriptIncl getPublManuscriptIncl() {
        return (org.rcsb.cif.api.generated.PublManuscriptIncl) categories.computeIfAbsent("publ_manuscript_incl",
                org.rcsb.cif.api.generated.PublManuscriptIncl::new).get();
    }

    public org.rcsb.cif.api.generated.Refine getRefine() {
        return (org.rcsb.cif.api.generated.Refine) categories.computeIfAbsent("refine",
                org.rcsb.cif.api.generated.Refine::new).get();
    }

    public org.rcsb.cif.api.generated.RefineAnalyze getRefineAnalyze() {
        return (org.rcsb.cif.api.generated.RefineAnalyze) categories.computeIfAbsent("refine_analyze",
                org.rcsb.cif.api.generated.RefineAnalyze::new).get();
    }

    public org.rcsb.cif.api.generated.RefineBIso getRefineBIso() {
        return (org.rcsb.cif.api.generated.RefineBIso) categories.computeIfAbsent("refine_B_iso",
                org.rcsb.cif.api.generated.RefineBIso::new).get();
    }

    public org.rcsb.cif.api.generated.RefineFunctMinimized getRefineFunctMinimized() {
        return (org.rcsb.cif.api.generated.RefineFunctMinimized) categories.computeIfAbsent("refine_funct_minimized",
                org.rcsb.cif.api.generated.RefineFunctMinimized::new).get();
    }

    public org.rcsb.cif.api.generated.RefineHist getRefineHist() {
        return (org.rcsb.cif.api.generated.RefineHist) categories.computeIfAbsent("refine_hist",
                org.rcsb.cif.api.generated.RefineHist::new).get();
    }

    public org.rcsb.cif.api.generated.RefineLsRestr getRefineLsRestr() {
        return (org.rcsb.cif.api.generated.RefineLsRestr) categories.computeIfAbsent("refine_ls_restr",
                org.rcsb.cif.api.generated.RefineLsRestr::new).get();
    }

    public org.rcsb.cif.api.generated.RefineLsRestrNcs getRefineLsRestrNcs() {
        return (org.rcsb.cif.api.generated.RefineLsRestrNcs) categories.computeIfAbsent("refine_ls_restr_ncs",
                org.rcsb.cif.api.generated.RefineLsRestrNcs::new).get();
    }

    public org.rcsb.cif.api.generated.RefineLsRestrType getRefineLsRestrType() {
        return (org.rcsb.cif.api.generated.RefineLsRestrType) categories.computeIfAbsent("refine_ls_restr_type",
                org.rcsb.cif.api.generated.RefineLsRestrType::new).get();
    }

    public org.rcsb.cif.api.generated.RefineLsShell getRefineLsShell() {
        return (org.rcsb.cif.api.generated.RefineLsShell) categories.computeIfAbsent("refine_ls_shell",
                org.rcsb.cif.api.generated.RefineLsShell::new).get();
    }

    public org.rcsb.cif.api.generated.RefineOccupancy getRefineOccupancy() {
        return (org.rcsb.cif.api.generated.RefineOccupancy) categories.computeIfAbsent("refine_occupancy",
                org.rcsb.cif.api.generated.RefineOccupancy::new).get();
    }

    public org.rcsb.cif.api.generated.Refln getRefln() {
        return (org.rcsb.cif.api.generated.Refln) categories.computeIfAbsent("refln",
                org.rcsb.cif.api.generated.Refln::new).get();
    }

    public org.rcsb.cif.api.generated.ReflnSysAbs getReflnSysAbs() {
        return (org.rcsb.cif.api.generated.ReflnSysAbs) categories.computeIfAbsent("refln_sys_abs",
                org.rcsb.cif.api.generated.ReflnSysAbs::new).get();
    }

    public org.rcsb.cif.api.generated.Reflns getReflns() {
        return (org.rcsb.cif.api.generated.Reflns) categories.computeIfAbsent("reflns",
                org.rcsb.cif.api.generated.Reflns::new).get();
    }

    public org.rcsb.cif.api.generated.ReflnsScale getReflnsScale() {
        return (org.rcsb.cif.api.generated.ReflnsScale) categories.computeIfAbsent("reflns_scale",
                org.rcsb.cif.api.generated.ReflnsScale::new).get();
    }

    public org.rcsb.cif.api.generated.ReflnsShell getReflnsShell() {
        return (org.rcsb.cif.api.generated.ReflnsShell) categories.computeIfAbsent("reflns_shell",
                org.rcsb.cif.api.generated.ReflnsShell::new).get();
    }

    public org.rcsb.cif.api.generated.Software getSoftware() {
        return (org.rcsb.cif.api.generated.Software) categories.computeIfAbsent("software",
                org.rcsb.cif.api.generated.Software::new).get();
    }

    public org.rcsb.cif.api.generated.Struct getStruct() {
        return (org.rcsb.cif.api.generated.Struct) categories.computeIfAbsent("struct",
                org.rcsb.cif.api.generated.Struct::new).get();
    }

    public org.rcsb.cif.api.generated.StructAsym getStructAsym() {
        return (org.rcsb.cif.api.generated.StructAsym) categories.computeIfAbsent("struct_asym",
                org.rcsb.cif.api.generated.StructAsym::new).get();
    }

    public org.rcsb.cif.api.generated.StructBiol getStructBiol() {
        return (org.rcsb.cif.api.generated.StructBiol) categories.computeIfAbsent("struct_biol",
                org.rcsb.cif.api.generated.StructBiol::new).get();
    }

    public org.rcsb.cif.api.generated.StructBiolGen getStructBiolGen() {
        return (org.rcsb.cif.api.generated.StructBiolGen) categories.computeIfAbsent("struct_biol_gen",
                org.rcsb.cif.api.generated.StructBiolGen::new).get();
    }

    public org.rcsb.cif.api.generated.StructBiolKeywords getStructBiolKeywords() {
        return (org.rcsb.cif.api.generated.StructBiolKeywords) categories.computeIfAbsent("struct_biol_keywords",
                org.rcsb.cif.api.generated.StructBiolKeywords::new).get();
    }

    public org.rcsb.cif.api.generated.StructBiolView getStructBiolView() {
        return (org.rcsb.cif.api.generated.StructBiolView) categories.computeIfAbsent("struct_biol_view",
                org.rcsb.cif.api.generated.StructBiolView::new).get();
    }

    public org.rcsb.cif.api.generated.StructConf getStructConf() {
        return (org.rcsb.cif.api.generated.StructConf) categories.computeIfAbsent("struct_conf",
                org.rcsb.cif.api.generated.StructConf::new).get();
    }

    public org.rcsb.cif.api.generated.StructConfType getStructConfType() {
        return (org.rcsb.cif.api.generated.StructConfType) categories.computeIfAbsent("struct_conf_type",
                org.rcsb.cif.api.generated.StructConfType::new).get();
    }

    public org.rcsb.cif.api.generated.StructConn getStructConn() {
        return (org.rcsb.cif.api.generated.StructConn) categories.computeIfAbsent("struct_conn",
                org.rcsb.cif.api.generated.StructConn::new).get();
    }

    public org.rcsb.cif.api.generated.StructConnType getStructConnType() {
        return (org.rcsb.cif.api.generated.StructConnType) categories.computeIfAbsent("struct_conn_type",
                org.rcsb.cif.api.generated.StructConnType::new).get();
    }

    public org.rcsb.cif.api.generated.StructKeywords getStructKeywords() {
        return (org.rcsb.cif.api.generated.StructKeywords) categories.computeIfAbsent("struct_keywords",
                org.rcsb.cif.api.generated.StructKeywords::new).get();
    }

    public org.rcsb.cif.api.generated.StructMonDetails getStructMonDetails() {
        return (org.rcsb.cif.api.generated.StructMonDetails) categories.computeIfAbsent("struct_mon_details",
                org.rcsb.cif.api.generated.StructMonDetails::new).get();
    }

    public org.rcsb.cif.api.generated.StructMonNucl getStructMonNucl() {
        return (org.rcsb.cif.api.generated.StructMonNucl) categories.computeIfAbsent("struct_mon_nucl",
                org.rcsb.cif.api.generated.StructMonNucl::new).get();
    }

    public org.rcsb.cif.api.generated.StructMonProt getStructMonProt() {
        return (org.rcsb.cif.api.generated.StructMonProt) categories.computeIfAbsent("struct_mon_prot",
                org.rcsb.cif.api.generated.StructMonProt::new).get();
    }

    public org.rcsb.cif.api.generated.StructMonProtCis getStructMonProtCis() {
        return (org.rcsb.cif.api.generated.StructMonProtCis) categories.computeIfAbsent("struct_mon_prot_cis",
                org.rcsb.cif.api.generated.StructMonProtCis::new).get();
    }

    public org.rcsb.cif.api.generated.StructNcsDom getStructNcsDom() {
        return (org.rcsb.cif.api.generated.StructNcsDom) categories.computeIfAbsent("struct_ncs_dom",
                org.rcsb.cif.api.generated.StructNcsDom::new).get();
    }

    public org.rcsb.cif.api.generated.StructNcsDomLim getStructNcsDomLim() {
        return (org.rcsb.cif.api.generated.StructNcsDomLim) categories.computeIfAbsent("struct_ncs_dom_lim",
                org.rcsb.cif.api.generated.StructNcsDomLim::new).get();
    }

    public org.rcsb.cif.api.generated.StructNcsEns getStructNcsEns() {
        return (org.rcsb.cif.api.generated.StructNcsEns) categories.computeIfAbsent("struct_ncs_ens",
                org.rcsb.cif.api.generated.StructNcsEns::new).get();
    }

    public org.rcsb.cif.api.generated.StructNcsEnsGen getStructNcsEnsGen() {
        return (org.rcsb.cif.api.generated.StructNcsEnsGen) categories.computeIfAbsent("struct_ncs_ens_gen",
                org.rcsb.cif.api.generated.StructNcsEnsGen::new).get();
    }

    public org.rcsb.cif.api.generated.StructNcsOper getStructNcsOper() {
        return (org.rcsb.cif.api.generated.StructNcsOper) categories.computeIfAbsent("struct_ncs_oper",
                org.rcsb.cif.api.generated.StructNcsOper::new).get();
    }

    public org.rcsb.cif.api.generated.StructRef getStructRef() {
        return (org.rcsb.cif.api.generated.StructRef) categories.computeIfAbsent("struct_ref",
                org.rcsb.cif.api.generated.StructRef::new).get();
    }

    public org.rcsb.cif.api.generated.StructRefSeq getStructRefSeq() {
        return (org.rcsb.cif.api.generated.StructRefSeq) categories.computeIfAbsent("struct_ref_seq",
                org.rcsb.cif.api.generated.StructRefSeq::new).get();
    }

    public org.rcsb.cif.api.generated.StructRefSeqDif getStructRefSeqDif() {
        return (org.rcsb.cif.api.generated.StructRefSeqDif) categories.computeIfAbsent("struct_ref_seq_dif",
                org.rcsb.cif.api.generated.StructRefSeqDif::new).get();
    }

    public org.rcsb.cif.api.generated.StructSheet getStructSheet() {
        return (org.rcsb.cif.api.generated.StructSheet) categories.computeIfAbsent("struct_sheet",
                org.rcsb.cif.api.generated.StructSheet::new).get();
    }

    public org.rcsb.cif.api.generated.StructSheetHbond getStructSheetHbond() {
        return (org.rcsb.cif.api.generated.StructSheetHbond) categories.computeIfAbsent("struct_sheet_hbond",
                org.rcsb.cif.api.generated.StructSheetHbond::new).get();
    }

    public org.rcsb.cif.api.generated.StructSheetOrder getStructSheetOrder() {
        return (org.rcsb.cif.api.generated.StructSheetOrder) categories.computeIfAbsent("struct_sheet_order",
                org.rcsb.cif.api.generated.StructSheetOrder::new).get();
    }

    public org.rcsb.cif.api.generated.StructSheetRange getStructSheetRange() {
        return (org.rcsb.cif.api.generated.StructSheetRange) categories.computeIfAbsent("struct_sheet_range",
                org.rcsb.cif.api.generated.StructSheetRange::new).get();
    }

    public org.rcsb.cif.api.generated.StructSheetTopology getStructSheetTopology() {
        return (org.rcsb.cif.api.generated.StructSheetTopology) categories.computeIfAbsent("struct_sheet_topology",
                org.rcsb.cif.api.generated.StructSheetTopology::new).get();
    }

    public org.rcsb.cif.api.generated.StructSite getStructSite() {
        return (org.rcsb.cif.api.generated.StructSite) categories.computeIfAbsent("struct_site",
                org.rcsb.cif.api.generated.StructSite::new).get();
    }

    public org.rcsb.cif.api.generated.StructSiteGen getStructSiteGen() {
        return (org.rcsb.cif.api.generated.StructSiteGen) categories.computeIfAbsent("struct_site_gen",
                org.rcsb.cif.api.generated.StructSiteGen::new).get();
    }

    public org.rcsb.cif.api.generated.StructSiteKeywords getStructSiteKeywords() {
        return (org.rcsb.cif.api.generated.StructSiteKeywords) categories.computeIfAbsent("struct_site_keywords",
                org.rcsb.cif.api.generated.StructSiteKeywords::new).get();
    }

    public org.rcsb.cif.api.generated.StructSiteView getStructSiteView() {
        return (org.rcsb.cif.api.generated.StructSiteView) categories.computeIfAbsent("struct_site_view",
                org.rcsb.cif.api.generated.StructSiteView::new).get();
    }

    public org.rcsb.cif.api.generated.Symmetry getSymmetry() {
        return (org.rcsb.cif.api.generated.Symmetry) categories.computeIfAbsent("symmetry",
                org.rcsb.cif.api.generated.Symmetry::new).get();
    }

    public org.rcsb.cif.api.generated.SymmetryEquiv getSymmetryEquiv() {
        return (org.rcsb.cif.api.generated.SymmetryEquiv) categories.computeIfAbsent("symmetry_equiv",
                org.rcsb.cif.api.generated.SymmetryEquiv::new).get();
    }

    public org.rcsb.cif.api.generated.AuditLink getAuditLink() {
        return (org.rcsb.cif.api.generated.AuditLink) categories.computeIfAbsent("audit_link",
                org.rcsb.cif.api.generated.AuditLink::new).get();
    }

    public org.rcsb.cif.api.generated.DiffrnReflnsClass getDiffrnReflnsClass() {
        return (org.rcsb.cif.api.generated.DiffrnReflnsClass) categories.computeIfAbsent("diffrn_reflns_class",
                org.rcsb.cif.api.generated.DiffrnReflnsClass::new).get();
    }

    public org.rcsb.cif.api.generated.RefineLsClass getRefineLsClass() {
        return (org.rcsb.cif.api.generated.RefineLsClass) categories.computeIfAbsent("refine_ls_class",
                org.rcsb.cif.api.generated.RefineLsClass::new).get();
    }

    public org.rcsb.cif.api.generated.ReflnsClass getReflnsClass() {
        return (org.rcsb.cif.api.generated.ReflnsClass) categories.computeIfAbsent("reflns_class",
                org.rcsb.cif.api.generated.ReflnsClass::new).get();
    }

    public org.rcsb.cif.api.generated.SpaceGroup getSpaceGroup() {
        return (org.rcsb.cif.api.generated.SpaceGroup) categories.computeIfAbsent("space_group",
                org.rcsb.cif.api.generated.SpaceGroup::new).get();
    }

    public org.rcsb.cif.api.generated.SpaceGroupSymop getSpaceGroupSymop() {
        return (org.rcsb.cif.api.generated.SpaceGroupSymop) categories.computeIfAbsent("space_group_symop",
                org.rcsb.cif.api.generated.SpaceGroupSymop::new).get();
    }

    public org.rcsb.cif.api.generated.ValenceParam getValenceParam() {
        return (org.rcsb.cif.api.generated.ValenceParam) categories.computeIfAbsent("valence_param",
                org.rcsb.cif.api.generated.ValenceParam::new).get();
    }

    public org.rcsb.cif.api.generated.ValenceRef getValenceRef() {
        return (org.rcsb.cif.api.generated.ValenceRef) categories.computeIfAbsent("valence_ref",
                org.rcsb.cif.api.generated.ValenceRef::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxAudit getPdbxAudit() {
        return (org.rcsb.cif.api.generated.PdbxAudit) categories.computeIfAbsent("pdbx_audit",
                org.rcsb.cif.api.generated.PdbxAudit::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxVersion getPdbxVersion() {
        return (org.rcsb.cif.api.generated.PdbxVersion) categories.computeIfAbsent("pdbx_version",
                org.rcsb.cif.api.generated.PdbxVersion::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxAuditAuthor getPdbxAuditAuthor() {
        return (org.rcsb.cif.api.generated.PdbxAuditAuthor) categories.computeIfAbsent("pdbx_audit_author",
                org.rcsb.cif.api.generated.PdbxAuditAuthor::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxDatabaseMessage getPdbxDatabaseMessage() {
        return (org.rcsb.cif.api.generated.PdbxDatabaseMessage) categories.computeIfAbsent("pdbx_database_message",
                org.rcsb.cif.api.generated.PdbxDatabaseMessage::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxDatabasePDBObsSpr getPdbxDatabasePDBObsSpr() {
        return (org.rcsb.cif.api.generated.PdbxDatabasePDBObsSpr) categories.computeIfAbsent("pdbx_database_PDB_obs_spr",
                org.rcsb.cif.api.generated.PdbxDatabasePDBObsSpr::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxDatabaseProc getPdbxDatabaseProc() {
        return (org.rcsb.cif.api.generated.PdbxDatabaseProc) categories.computeIfAbsent("pdbx_database_proc",
                org.rcsb.cif.api.generated.PdbxDatabaseProc::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxDatabaseRemark getPdbxDatabaseRemark() {
        return (org.rcsb.cif.api.generated.PdbxDatabaseRemark) categories.computeIfAbsent("pdbx_database_remark",
                org.rcsb.cif.api.generated.PdbxDatabaseRemark::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxDatabaseStatus getPdbxDatabaseStatus() {
        return (org.rcsb.cif.api.generated.PdbxDatabaseStatus) categories.computeIfAbsent("pdbx_database_status",
                org.rcsb.cif.api.generated.PdbxDatabaseStatus::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxEntityName getPdbxEntityName() {
        return (org.rcsb.cif.api.generated.PdbxEntityName) categories.computeIfAbsent("pdbx_entity_name",
                org.rcsb.cif.api.generated.PdbxEntityName::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxPrereleaseSeq getPdbxPrereleaseSeq() {
        return (org.rcsb.cif.api.generated.PdbxPrereleaseSeq) categories.computeIfAbsent("pdbx_prerelease_seq",
                org.rcsb.cif.api.generated.PdbxPrereleaseSeq::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxPolySeqScheme getPdbxPolySeqScheme() {
        return (org.rcsb.cif.api.generated.PdbxPolySeqScheme) categories.computeIfAbsent("pdbx_poly_seq_scheme",
                org.rcsb.cif.api.generated.PdbxPolySeqScheme::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxNonpolyScheme getPdbxNonpolyScheme() {
        return (org.rcsb.cif.api.generated.PdbxNonpolyScheme) categories.computeIfAbsent("pdbx_nonpoly_scheme",
                org.rcsb.cif.api.generated.PdbxNonpolyScheme::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxRefine getPdbxRefine() {
        return (org.rcsb.cif.api.generated.PdbxRefine) categories.computeIfAbsent("pdbx_refine",
                org.rcsb.cif.api.generated.PdbxRefine::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxStructSheetHbond getPdbxStructSheetHbond() {
        return (org.rcsb.cif.api.generated.PdbxStructSheetHbond) categories.computeIfAbsent("pdbx_struct_sheet_hbond",
                org.rcsb.cif.api.generated.PdbxStructSheetHbond::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxXplorFile getPdbxXplorFile() {
        return (org.rcsb.cif.api.generated.PdbxXplorFile) categories.computeIfAbsent("pdbx_xplor_file",
                org.rcsb.cif.api.generated.PdbxXplorFile::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxRefineAuxFile getPdbxRefineAuxFile() {
        return (org.rcsb.cif.api.generated.PdbxRefineAuxFile) categories.computeIfAbsent("pdbx_refine_aux_file",
                org.rcsb.cif.api.generated.PdbxRefineAuxFile::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxDatabaseRelated getPdbxDatabaseRelated() {
        return (org.rcsb.cif.api.generated.PdbxDatabaseRelated) categories.computeIfAbsent("pdbx_database_related",
                org.rcsb.cif.api.generated.PdbxDatabaseRelated::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxEntityAssembly getPdbxEntityAssembly() {
        return (org.rcsb.cif.api.generated.PdbxEntityAssembly) categories.computeIfAbsent("pdbx_entity_assembly",
                org.rcsb.cif.api.generated.PdbxEntityAssembly::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxExptlCrystalGrowComp getPdbxExptlCrystalGrowComp() {
        return (org.rcsb.cif.api.generated.PdbxExptlCrystalGrowComp) categories.computeIfAbsent("pdbx_exptl_crystal_grow_comp",
                org.rcsb.cif.api.generated.PdbxExptlCrystalGrowComp::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxExptlCrystalGrowSol getPdbxExptlCrystalGrowSol() {
        return (org.rcsb.cif.api.generated.PdbxExptlCrystalGrowSol) categories.computeIfAbsent("pdbx_exptl_crystal_grow_sol",
                org.rcsb.cif.api.generated.PdbxExptlCrystalGrowSol::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxExptlCrystalCryoTreatment getPdbxExptlCrystalCryoTreatment() {
        return (org.rcsb.cif.api.generated.PdbxExptlCrystalCryoTreatment) categories.computeIfAbsent("pdbx_exptl_crystal_cryo_treatment",
                org.rcsb.cif.api.generated.PdbxExptlCrystalCryoTreatment::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxRefineTls getPdbxRefineTls() {
        return (org.rcsb.cif.api.generated.PdbxRefineTls) categories.computeIfAbsent("pdbx_refine_tls",
                org.rcsb.cif.api.generated.PdbxRefineTls::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxRefineTlsGroup getPdbxRefineTlsGroup() {
        return (org.rcsb.cif.api.generated.PdbxRefineTlsGroup) categories.computeIfAbsent("pdbx_refine_tls_group",
                org.rcsb.cif.api.generated.PdbxRefineTlsGroup::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxContactAuthor getPdbxContactAuthor() {
        return (org.rcsb.cif.api.generated.PdbxContactAuthor) categories.computeIfAbsent("pdbx_contact_author",
                org.rcsb.cif.api.generated.PdbxContactAuthor::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxSGProject getPdbxSGProject() {
        return (org.rcsb.cif.api.generated.PdbxSGProject) categories.computeIfAbsent("pdbx_SG_project",
                org.rcsb.cif.api.generated.PdbxSGProject::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxAtomSiteAnisoTls getPdbxAtomSiteAnisoTls() {
        return (org.rcsb.cif.api.generated.PdbxAtomSiteAnisoTls) categories.computeIfAbsent("pdbx_atom_site_aniso_tls",
                org.rcsb.cif.api.generated.PdbxAtomSiteAnisoTls::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxNmrDetails getPdbxNmrDetails() {
        return (org.rcsb.cif.api.generated.PdbxNmrDetails) categories.computeIfAbsent("pdbx_nmr_details",
                org.rcsb.cif.api.generated.PdbxNmrDetails::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxNmrSampleDetails getPdbxNmrSampleDetails() {
        return (org.rcsb.cif.api.generated.PdbxNmrSampleDetails) categories.computeIfAbsent("pdbx_nmr_sample_details",
                org.rcsb.cif.api.generated.PdbxNmrSampleDetails::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxNmrExptlSample getPdbxNmrExptlSample() {
        return (org.rcsb.cif.api.generated.PdbxNmrExptlSample) categories.computeIfAbsent("pdbx_nmr_exptl_sample",
                org.rcsb.cif.api.generated.PdbxNmrExptlSample::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxNmrExptlSampleConditions getPdbxNmrExptlSampleConditions() {
        return (org.rcsb.cif.api.generated.PdbxNmrExptlSampleConditions) categories.computeIfAbsent("pdbx_nmr_exptl_sample_conditions",
                org.rcsb.cif.api.generated.PdbxNmrExptlSampleConditions::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxNmrSpectrometer getPdbxNmrSpectrometer() {
        return (org.rcsb.cif.api.generated.PdbxNmrSpectrometer) categories.computeIfAbsent("pdbx_nmr_spectrometer",
                org.rcsb.cif.api.generated.PdbxNmrSpectrometer::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxNmrExptl getPdbxNmrExptl() {
        return (org.rcsb.cif.api.generated.PdbxNmrExptl) categories.computeIfAbsent("pdbx_nmr_exptl",
                org.rcsb.cif.api.generated.PdbxNmrExptl::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxNmrSoftware getPdbxNmrSoftware() {
        return (org.rcsb.cif.api.generated.PdbxNmrSoftware) categories.computeIfAbsent("pdbx_nmr_software",
                org.rcsb.cif.api.generated.PdbxNmrSoftware::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxNmrConstraints getPdbxNmrConstraints() {
        return (org.rcsb.cif.api.generated.PdbxNmrConstraints) categories.computeIfAbsent("pdbx_nmr_constraints",
                org.rcsb.cif.api.generated.PdbxNmrConstraints::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxNmrEnsemble getPdbxNmrEnsemble() {
        return (org.rcsb.cif.api.generated.PdbxNmrEnsemble) categories.computeIfAbsent("pdbx_nmr_ensemble",
                org.rcsb.cif.api.generated.PdbxNmrEnsemble::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxNmrEnsembleRms getPdbxNmrEnsembleRms() {
        return (org.rcsb.cif.api.generated.PdbxNmrEnsembleRms) categories.computeIfAbsent("pdbx_nmr_ensemble_rms",
                org.rcsb.cif.api.generated.PdbxNmrEnsembleRms::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxNmrRepresentative getPdbxNmrRepresentative() {
        return (org.rcsb.cif.api.generated.PdbxNmrRepresentative) categories.computeIfAbsent("pdbx_nmr_representative",
                org.rcsb.cif.api.generated.PdbxNmrRepresentative::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxNmrRefine getPdbxNmrRefine() {
        return (org.rcsb.cif.api.generated.PdbxNmrRefine) categories.computeIfAbsent("pdbx_nmr_refine",
                org.rcsb.cif.api.generated.PdbxNmrRefine::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxNmrForceConstants getPdbxNmrForceConstants() {
        return (org.rcsb.cif.api.generated.PdbxNmrForceConstants) categories.computeIfAbsent("pdbx_nmr_force_constants",
                org.rcsb.cif.api.generated.PdbxNmrForceConstants::new).get();
    }

    public org.rcsb.cif.api.generated.NdbStructConfNa getNdbStructConfNa() {
        return (org.rcsb.cif.api.generated.NdbStructConfNa) categories.computeIfAbsent("ndb_struct_conf_na",
                org.rcsb.cif.api.generated.NdbStructConfNa::new).get();
    }

    public org.rcsb.cif.api.generated.NdbStructFeatureNa getNdbStructFeatureNa() {
        return (org.rcsb.cif.api.generated.NdbStructFeatureNa) categories.computeIfAbsent("ndb_struct_feature_na",
                org.rcsb.cif.api.generated.NdbStructFeatureNa::new).get();
    }

    public org.rcsb.cif.api.generated.NdbStructNaBasePair getNdbStructNaBasePair() {
        return (org.rcsb.cif.api.generated.NdbStructNaBasePair) categories.computeIfAbsent("ndb_struct_na_base_pair",
                org.rcsb.cif.api.generated.NdbStructNaBasePair::new).get();
    }

    public org.rcsb.cif.api.generated.NdbStructNaBasePairStep getNdbStructNaBasePairStep() {
        return (org.rcsb.cif.api.generated.NdbStructNaBasePairStep) categories.computeIfAbsent("ndb_struct_na_base_pair_step",
                org.rcsb.cif.api.generated.NdbStructNaBasePairStep::new).get();
    }

    public org.rcsb.cif.api.generated.NdbOriginalNdbCoordinates getNdbOriginalNdbCoordinates() {
        return (org.rcsb.cif.api.generated.NdbOriginalNdbCoordinates) categories.computeIfAbsent("ndb_original_ndb_coordinates",
                org.rcsb.cif.api.generated.NdbOriginalNdbCoordinates::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxEntityNonpoly getPdbxEntityNonpoly() {
        return (org.rcsb.cif.api.generated.PdbxEntityNonpoly) categories.computeIfAbsent("pdbx_entity_nonpoly",
                org.rcsb.cif.api.generated.PdbxEntityNonpoly::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxPhasingDm getPdbxPhasingDm() {
        return (org.rcsb.cif.api.generated.PdbxPhasingDm) categories.computeIfAbsent("pdbx_phasing_dm",
                org.rcsb.cif.api.generated.PdbxPhasingDm::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxPhasingDmShell getPdbxPhasingDmShell() {
        return (org.rcsb.cif.api.generated.PdbxPhasingDmShell) categories.computeIfAbsent("pdbx_phasing_dm_shell",
                org.rcsb.cif.api.generated.PdbxPhasingDmShell::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxPhasingMADShell getPdbxPhasingMADShell() {
        return (org.rcsb.cif.api.generated.PdbxPhasingMADShell) categories.computeIfAbsent("pdbx_phasing_MAD_shell",
                org.rcsb.cif.api.generated.PdbxPhasingMADShell::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxPhasingMADSet getPdbxPhasingMADSet() {
        return (org.rcsb.cif.api.generated.PdbxPhasingMADSet) categories.computeIfAbsent("pdbx_phasing_MAD_set",
                org.rcsb.cif.api.generated.PdbxPhasingMADSet::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxPhasingMADSetShell getPdbxPhasingMADSetShell() {
        return (org.rcsb.cif.api.generated.PdbxPhasingMADSetShell) categories.computeIfAbsent("pdbx_phasing_MAD_set_shell",
                org.rcsb.cif.api.generated.PdbxPhasingMADSetShell::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxPhasingMADSetSite getPdbxPhasingMADSetSite() {
        return (org.rcsb.cif.api.generated.PdbxPhasingMADSetSite) categories.computeIfAbsent("pdbx_phasing_MAD_set_site",
                org.rcsb.cif.api.generated.PdbxPhasingMADSetSite::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxPhasingMR getPdbxPhasingMR() {
        return (org.rcsb.cif.api.generated.PdbxPhasingMR) categories.computeIfAbsent("pdbx_phasing_MR",
                org.rcsb.cif.api.generated.PdbxPhasingMR::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxRefineComponent getPdbxRefineComponent() {
        return (org.rcsb.cif.api.generated.PdbxRefineComponent) categories.computeIfAbsent("pdbx_refine_component",
                org.rcsb.cif.api.generated.PdbxRefineComponent::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxEntityProdProtocol getPdbxEntityProdProtocol() {
        return (org.rcsb.cif.api.generated.PdbxEntityProdProtocol) categories.computeIfAbsent("pdbx_entity_prod_protocol",
                org.rcsb.cif.api.generated.PdbxEntityProdProtocol::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxEntitySrcGenProdOther getPdbxEntitySrcGenProdOther() {
        return (org.rcsb.cif.api.generated.PdbxEntitySrcGenProdOther) categories.computeIfAbsent("pdbx_entity_src_gen_prod_other",
                org.rcsb.cif.api.generated.PdbxEntitySrcGenProdOther::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxEntitySrcGenProdOtherParameter getPdbxEntitySrcGenProdOtherParameter() {
        return (org.rcsb.cif.api.generated.PdbxEntitySrcGenProdOtherParameter) categories.computeIfAbsent("pdbx_entity_src_gen_prod_other_parameter",
                org.rcsb.cif.api.generated.PdbxEntitySrcGenProdOtherParameter::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxEntitySrcGenProdPcr getPdbxEntitySrcGenProdPcr() {
        return (org.rcsb.cif.api.generated.PdbxEntitySrcGenProdPcr) categories.computeIfAbsent("pdbx_entity_src_gen_prod_pcr",
                org.rcsb.cif.api.generated.PdbxEntitySrcGenProdPcr::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxEntitySrcGenProdDigest getPdbxEntitySrcGenProdDigest() {
        return (org.rcsb.cif.api.generated.PdbxEntitySrcGenProdDigest) categories.computeIfAbsent("pdbx_entity_src_gen_prod_digest",
                org.rcsb.cif.api.generated.PdbxEntitySrcGenProdDigest::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxEntitySrcGenClone getPdbxEntitySrcGenClone() {
        return (org.rcsb.cif.api.generated.PdbxEntitySrcGenClone) categories.computeIfAbsent("pdbx_entity_src_gen_clone",
                org.rcsb.cif.api.generated.PdbxEntitySrcGenClone::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxEntitySrcGenCloneLigation getPdbxEntitySrcGenCloneLigation() {
        return (org.rcsb.cif.api.generated.PdbxEntitySrcGenCloneLigation) categories.computeIfAbsent("pdbx_entity_src_gen_clone_ligation",
                org.rcsb.cif.api.generated.PdbxEntitySrcGenCloneLigation::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxEntitySrcGenCloneRecombination getPdbxEntitySrcGenCloneRecombination() {
        return (org.rcsb.cif.api.generated.PdbxEntitySrcGenCloneRecombination) categories.computeIfAbsent("pdbx_entity_src_gen_clone_recombination",
                org.rcsb.cif.api.generated.PdbxEntitySrcGenCloneRecombination::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxEntitySrcGenExpress getPdbxEntitySrcGenExpress() {
        return (org.rcsb.cif.api.generated.PdbxEntitySrcGenExpress) categories.computeIfAbsent("pdbx_entity_src_gen_express",
                org.rcsb.cif.api.generated.PdbxEntitySrcGenExpress::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxEntitySrcGenExpressTimepoint getPdbxEntitySrcGenExpressTimepoint() {
        return (org.rcsb.cif.api.generated.PdbxEntitySrcGenExpressTimepoint) categories.computeIfAbsent("pdbx_entity_src_gen_express_timepoint",
                org.rcsb.cif.api.generated.PdbxEntitySrcGenExpressTimepoint::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxEntitySrcGenLysis getPdbxEntitySrcGenLysis() {
        return (org.rcsb.cif.api.generated.PdbxEntitySrcGenLysis) categories.computeIfAbsent("pdbx_entity_src_gen_lysis",
                org.rcsb.cif.api.generated.PdbxEntitySrcGenLysis::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxEntitySrcGenRefold getPdbxEntitySrcGenRefold() {
        return (org.rcsb.cif.api.generated.PdbxEntitySrcGenRefold) categories.computeIfAbsent("pdbx_entity_src_gen_refold",
                org.rcsb.cif.api.generated.PdbxEntitySrcGenRefold::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxEntitySrcGenProteolysis getPdbxEntitySrcGenProteolysis() {
        return (org.rcsb.cif.api.generated.PdbxEntitySrcGenProteolysis) categories.computeIfAbsent("pdbx_entity_src_gen_proteolysis",
                org.rcsb.cif.api.generated.PdbxEntitySrcGenProteolysis::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxEntitySrcGenChrom getPdbxEntitySrcGenChrom() {
        return (org.rcsb.cif.api.generated.PdbxEntitySrcGenChrom) categories.computeIfAbsent("pdbx_entity_src_gen_chrom",
                org.rcsb.cif.api.generated.PdbxEntitySrcGenChrom::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxEntitySrcGenFract getPdbxEntitySrcGenFract() {
        return (org.rcsb.cif.api.generated.PdbxEntitySrcGenFract) categories.computeIfAbsent("pdbx_entity_src_gen_fract",
                org.rcsb.cif.api.generated.PdbxEntitySrcGenFract::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxEntitySrcGenPure getPdbxEntitySrcGenPure() {
        return (org.rcsb.cif.api.generated.PdbxEntitySrcGenPure) categories.computeIfAbsent("pdbx_entity_src_gen_pure",
                org.rcsb.cif.api.generated.PdbxEntitySrcGenPure::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxEntitySrcGenCharacter getPdbxEntitySrcGenCharacter() {
        return (org.rcsb.cif.api.generated.PdbxEntitySrcGenCharacter) categories.computeIfAbsent("pdbx_entity_src_gen_character",
                org.rcsb.cif.api.generated.PdbxEntitySrcGenCharacter::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxConstruct getPdbxConstruct() {
        return (org.rcsb.cif.api.generated.PdbxConstruct) categories.computeIfAbsent("pdbx_construct",
                org.rcsb.cif.api.generated.PdbxConstruct::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxConstructFeature getPdbxConstructFeature() {
        return (org.rcsb.cif.api.generated.PdbxConstructFeature) categories.computeIfAbsent("pdbx_construct_feature",
                org.rcsb.cif.api.generated.PdbxConstructFeature::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxRobotSystem getPdbxRobotSystem() {
        return (org.rcsb.cif.api.generated.PdbxRobotSystem) categories.computeIfAbsent("pdbx_robot_system",
                org.rcsb.cif.api.generated.PdbxRobotSystem::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxBuffer getPdbxBuffer() {
        return (org.rcsb.cif.api.generated.PdbxBuffer) categories.computeIfAbsent("pdbx_buffer",
                org.rcsb.cif.api.generated.PdbxBuffer::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxBufferComponents getPdbxBufferComponents() {
        return (org.rcsb.cif.api.generated.PdbxBufferComponents) categories.computeIfAbsent("pdbx_buffer_components",
                org.rcsb.cif.api.generated.PdbxBufferComponents::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxDomain getPdbxDomain() {
        return (org.rcsb.cif.api.generated.PdbxDomain) categories.computeIfAbsent("pdbx_domain",
                org.rcsb.cif.api.generated.PdbxDomain::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxDomainRange getPdbxDomainRange() {
        return (org.rcsb.cif.api.generated.PdbxDomainRange) categories.computeIfAbsent("pdbx_domain_range",
                org.rcsb.cif.api.generated.PdbxDomainRange::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxSequenceRange getPdbxSequenceRange() {
        return (org.rcsb.cif.api.generated.PdbxSequenceRange) categories.computeIfAbsent("pdbx_sequence_range",
                org.rcsb.cif.api.generated.PdbxSequenceRange::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxFeatureEntry getPdbxFeatureEntry() {
        return (org.rcsb.cif.api.generated.PdbxFeatureEntry) categories.computeIfAbsent("pdbx_feature_entry",
                org.rcsb.cif.api.generated.PdbxFeatureEntry::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxFeatureDomain getPdbxFeatureDomain() {
        return (org.rcsb.cif.api.generated.PdbxFeatureDomain) categories.computeIfAbsent("pdbx_feature_domain",
                org.rcsb.cif.api.generated.PdbxFeatureDomain::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxFeatureSequenceRange getPdbxFeatureSequenceRange() {
        return (org.rcsb.cif.api.generated.PdbxFeatureSequenceRange) categories.computeIfAbsent("pdbx_feature_sequence_range",
                org.rcsb.cif.api.generated.PdbxFeatureSequenceRange::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxFeatureAssembly getPdbxFeatureAssembly() {
        return (org.rcsb.cif.api.generated.PdbxFeatureAssembly) categories.computeIfAbsent("pdbx_feature_assembly",
                org.rcsb.cif.api.generated.PdbxFeatureAssembly::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxFeatureMonomer getPdbxFeatureMonomer() {
        return (org.rcsb.cif.api.generated.PdbxFeatureMonomer) categories.computeIfAbsent("pdbx_feature_monomer",
                org.rcsb.cif.api.generated.PdbxFeatureMonomer::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxExptlPd getPdbxExptlPd() {
        return (org.rcsb.cif.api.generated.PdbxExptlPd) categories.computeIfAbsent("pdbx_exptl_pd",
                org.rcsb.cif.api.generated.PdbxExptlPd::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxReflnsTwin getPdbxReflnsTwin() {
        return (org.rcsb.cif.api.generated.PdbxReflnsTwin) categories.computeIfAbsent("pdbx_reflns_twin",
                org.rcsb.cif.api.generated.PdbxReflnsTwin::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxStructInfo getPdbxStructInfo() {
        return (org.rcsb.cif.api.generated.PdbxStructInfo) categories.computeIfAbsent("pdbx_struct_info",
                org.rcsb.cif.api.generated.PdbxStructInfo::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxReRefinement getPdbxReRefinement() {
        return (org.rcsb.cif.api.generated.PdbxReRefinement) categories.computeIfAbsent("pdbx_re_refinement",
                org.rcsb.cif.api.generated.PdbxReRefinement::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxStructAssemblyProp getPdbxStructAssemblyProp() {
        return (org.rcsb.cif.api.generated.PdbxStructAssemblyProp) categories.computeIfAbsent("pdbx_struct_assembly_prop",
                org.rcsb.cif.api.generated.PdbxStructAssemblyProp::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxStructRefSeqFeature getPdbxStructRefSeqFeature() {
        return (org.rcsb.cif.api.generated.PdbxStructRefSeqFeature) categories.computeIfAbsent("pdbx_struct_ref_seq_feature",
                org.rcsb.cif.api.generated.PdbxStructRefSeqFeature::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxStructRefSeqFeatureProp getPdbxStructRefSeqFeatureProp() {
        return (org.rcsb.cif.api.generated.PdbxStructRefSeqFeatureProp) categories.computeIfAbsent("pdbx_struct_ref_seq_feature_prop",
                org.rcsb.cif.api.generated.PdbxStructRefSeqFeatureProp::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxStructChemCompDiagnostics getPdbxStructChemCompDiagnostics() {
        return (org.rcsb.cif.api.generated.PdbxStructChemCompDiagnostics) categories.computeIfAbsent("pdbx_struct_chem_comp_diagnostics",
                org.rcsb.cif.api.generated.PdbxStructChemCompDiagnostics::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxChemCompFeature getPdbxChemCompFeature() {
        return (org.rcsb.cif.api.generated.PdbxChemCompFeature) categories.computeIfAbsent("pdbx_chem_comp_feature",
                org.rcsb.cif.api.generated.PdbxChemCompFeature::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxCoordinateModel getPdbxCoordinateModel() {
        return (org.rcsb.cif.api.generated.PdbxCoordinateModel) categories.computeIfAbsent("pdbx_coordinate_model",
                org.rcsb.cif.api.generated.PdbxCoordinateModel::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxStructChemCompFeature getPdbxStructChemCompFeature() {
        return (org.rcsb.cif.api.generated.PdbxStructChemCompFeature) categories.computeIfAbsent("pdbx_struct_chem_comp_feature",
                org.rcsb.cif.api.generated.PdbxStructChemCompFeature::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxDiffrnReflnsShell getPdbxDiffrnReflnsShell() {
        return (org.rcsb.cif.api.generated.PdbxDiffrnReflnsShell) categories.computeIfAbsent("pdbx_diffrn_reflns_shell",
                org.rcsb.cif.api.generated.PdbxDiffrnReflnsShell::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxBondDistanceLimits getPdbxBondDistanceLimits() {
        return (org.rcsb.cif.api.generated.PdbxBondDistanceLimits) categories.computeIfAbsent("pdbx_bond_distance_limits",
                org.rcsb.cif.api.generated.PdbxBondDistanceLimits::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxSolnScatter getPdbxSolnScatter() {
        return (org.rcsb.cif.api.generated.PdbxSolnScatter) categories.computeIfAbsent("pdbx_soln_scatter",
                org.rcsb.cif.api.generated.PdbxSolnScatter::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxSolnScatterModel getPdbxSolnScatterModel() {
        return (org.rcsb.cif.api.generated.PdbxSolnScatterModel) categories.computeIfAbsent("pdbx_soln_scatter_model",
                org.rcsb.cif.api.generated.PdbxSolnScatterModel::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxChemCompDescriptor getPdbxChemCompDescriptor() {
        return (org.rcsb.cif.api.generated.PdbxChemCompDescriptor) categories.computeIfAbsent("pdbx_chem_comp_descriptor",
                org.rcsb.cif.api.generated.PdbxChemCompDescriptor::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxChemCompIdentifier getPdbxChemCompIdentifier() {
        return (org.rcsb.cif.api.generated.PdbxChemCompIdentifier) categories.computeIfAbsent("pdbx_chem_comp_identifier",
                org.rcsb.cif.api.generated.PdbxChemCompIdentifier::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxChemCompImport getPdbxChemCompImport() {
        return (org.rcsb.cif.api.generated.PdbxChemCompImport) categories.computeIfAbsent("pdbx_chem_comp_import",
                org.rcsb.cif.api.generated.PdbxChemCompImport::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxChemCompAtomEdit getPdbxChemCompAtomEdit() {
        return (org.rcsb.cif.api.generated.PdbxChemCompAtomEdit) categories.computeIfAbsent("pdbx_chem_comp_atom_edit",
                org.rcsb.cif.api.generated.PdbxChemCompAtomEdit::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxChemCompBondEdit getPdbxChemCompBondEdit() {
        return (org.rcsb.cif.api.generated.PdbxChemCompBondEdit) categories.computeIfAbsent("pdbx_chem_comp_bond_edit",
                org.rcsb.cif.api.generated.PdbxChemCompBondEdit::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxChemCompAudit getPdbxChemCompAudit() {
        return (org.rcsb.cif.api.generated.PdbxChemCompAudit) categories.computeIfAbsent("pdbx_chem_comp_audit",
                org.rcsb.cif.api.generated.PdbxChemCompAudit::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxValidateCloseContact getPdbxValidateCloseContact() {
        return (org.rcsb.cif.api.generated.PdbxValidateCloseContact) categories.computeIfAbsent("pdbx_validate_close_contact",
                org.rcsb.cif.api.generated.PdbxValidateCloseContact::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxValidateSymmContact getPdbxValidateSymmContact() {
        return (org.rcsb.cif.api.generated.PdbxValidateSymmContact) categories.computeIfAbsent("pdbx_validate_symm_contact",
                org.rcsb.cif.api.generated.PdbxValidateSymmContact::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxValidateRmsdBond getPdbxValidateRmsdBond() {
        return (org.rcsb.cif.api.generated.PdbxValidateRmsdBond) categories.computeIfAbsent("pdbx_validate_rmsd_bond",
                org.rcsb.cif.api.generated.PdbxValidateRmsdBond::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxValidateRmsdAngle getPdbxValidateRmsdAngle() {
        return (org.rcsb.cif.api.generated.PdbxValidateRmsdAngle) categories.computeIfAbsent("pdbx_validate_rmsd_angle",
                org.rcsb.cif.api.generated.PdbxValidateRmsdAngle::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxValidateTorsion getPdbxValidateTorsion() {
        return (org.rcsb.cif.api.generated.PdbxValidateTorsion) categories.computeIfAbsent("pdbx_validate_torsion",
                org.rcsb.cif.api.generated.PdbxValidateTorsion::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxValidatePeptideOmega getPdbxValidatePeptideOmega() {
        return (org.rcsb.cif.api.generated.PdbxValidatePeptideOmega) categories.computeIfAbsent("pdbx_validate_peptide_omega",
                org.rcsb.cif.api.generated.PdbxValidatePeptideOmega::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxValidateChiral getPdbxValidateChiral() {
        return (org.rcsb.cif.api.generated.PdbxValidateChiral) categories.computeIfAbsent("pdbx_validate_chiral",
                org.rcsb.cif.api.generated.PdbxValidateChiral::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxValidatePlanes getPdbxValidatePlanes() {
        return (org.rcsb.cif.api.generated.PdbxValidatePlanes) categories.computeIfAbsent("pdbx_validate_planes",
                org.rcsb.cif.api.generated.PdbxValidatePlanes::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxValidatePlanesAtom getPdbxValidatePlanesAtom() {
        return (org.rcsb.cif.api.generated.PdbxValidatePlanesAtom) categories.computeIfAbsent("pdbx_validate_planes_atom",
                org.rcsb.cif.api.generated.PdbxValidatePlanesAtom::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxValidateMainChainPlane getPdbxValidateMainChainPlane() {
        return (org.rcsb.cif.api.generated.PdbxValidateMainChainPlane) categories.computeIfAbsent("pdbx_validate_main_chain_plane",
                org.rcsb.cif.api.generated.PdbxValidateMainChainPlane::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxStructConnAngle getPdbxStructConnAngle() {
        return (org.rcsb.cif.api.generated.PdbxStructConnAngle) categories.computeIfAbsent("pdbx_struct_conn_angle",
                org.rcsb.cif.api.generated.PdbxStructConnAngle::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxUnobsOrZeroOccResidues getPdbxUnobsOrZeroOccResidues() {
        return (org.rcsb.cif.api.generated.PdbxUnobsOrZeroOccResidues) categories.computeIfAbsent("pdbx_unobs_or_zero_occ_residues",
                org.rcsb.cif.api.generated.PdbxUnobsOrZeroOccResidues::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxUnobsOrZeroOccAtoms getPdbxUnobsOrZeroOccAtoms() {
        return (org.rcsb.cif.api.generated.PdbxUnobsOrZeroOccAtoms) categories.computeIfAbsent("pdbx_unobs_or_zero_occ_atoms",
                org.rcsb.cif.api.generated.PdbxUnobsOrZeroOccAtoms::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxEntryDetails getPdbxEntryDetails() {
        return (org.rcsb.cif.api.generated.PdbxEntryDetails) categories.computeIfAbsent("pdbx_entry_details",
                org.rcsb.cif.api.generated.PdbxEntryDetails::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxStructModResidue getPdbxStructModResidue() {
        return (org.rcsb.cif.api.generated.PdbxStructModResidue) categories.computeIfAbsent("pdbx_struct_mod_residue",
                org.rcsb.cif.api.generated.PdbxStructModResidue::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxStructRefSeqInsertion getPdbxStructRefSeqInsertion() {
        return (org.rcsb.cif.api.generated.PdbxStructRefSeqInsertion) categories.computeIfAbsent("pdbx_struct_ref_seq_insertion",
                org.rcsb.cif.api.generated.PdbxStructRefSeqInsertion::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxStructRefSeqDeletion getPdbxStructRefSeqDeletion() {
        return (org.rcsb.cif.api.generated.PdbxStructRefSeqDeletion) categories.computeIfAbsent("pdbx_struct_ref_seq_deletion",
                org.rcsb.cif.api.generated.PdbxStructRefSeqDeletion::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxRemediationAtomSiteMapping getPdbxRemediationAtomSiteMapping() {
        return (org.rcsb.cif.api.generated.PdbxRemediationAtomSiteMapping) categories.computeIfAbsent("pdbx_remediation_atom_site_mapping",
                org.rcsb.cif.api.generated.PdbxRemediationAtomSiteMapping::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxValidatePolymerLinkage getPdbxValidatePolymerLinkage() {
        return (org.rcsb.cif.api.generated.PdbxValidatePolymerLinkage) categories.computeIfAbsent("pdbx_validate_polymer_linkage",
                org.rcsb.cif.api.generated.PdbxValidatePolymerLinkage::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxHelicalSymmetry getPdbxHelicalSymmetry() {
        return (org.rcsb.cif.api.generated.PdbxHelicalSymmetry) categories.computeIfAbsent("pdbx_helical_symmetry",
                org.rcsb.cif.api.generated.PdbxHelicalSymmetry::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxPointSymmetry getPdbxPointSymmetry() {
        return (org.rcsb.cif.api.generated.PdbxPointSymmetry) categories.computeIfAbsent("pdbx_point_symmetry",
                org.rcsb.cif.api.generated.PdbxPointSymmetry::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxStructEntityInst getPdbxStructEntityInst() {
        return (org.rcsb.cif.api.generated.PdbxStructEntityInst) categories.computeIfAbsent("pdbx_struct_entity_inst",
                org.rcsb.cif.api.generated.PdbxStructEntityInst::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxStructOperList getPdbxStructOperList() {
        return (org.rcsb.cif.api.generated.PdbxStructOperList) categories.computeIfAbsent("pdbx_struct_oper_list",
                org.rcsb.cif.api.generated.PdbxStructOperList::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxStructAssembly getPdbxStructAssembly() {
        return (org.rcsb.cif.api.generated.PdbxStructAssembly) categories.computeIfAbsent("pdbx_struct_assembly",
                org.rcsb.cif.api.generated.PdbxStructAssembly::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxStructAssemblyGen getPdbxStructAssemblyGen() {
        return (org.rcsb.cif.api.generated.PdbxStructAssemblyGen) categories.computeIfAbsent("pdbx_struct_assembly_gen",
                org.rcsb.cif.api.generated.PdbxStructAssemblyGen::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxStructAsymGen getPdbxStructAsymGen() {
        return (org.rcsb.cif.api.generated.PdbxStructAsymGen) categories.computeIfAbsent("pdbx_struct_asym_gen",
                org.rcsb.cif.api.generated.PdbxStructAsymGen::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxStructMsymGen getPdbxStructMsymGen() {
        return (org.rcsb.cif.api.generated.PdbxStructMsymGen) categories.computeIfAbsent("pdbx_struct_msym_gen",
                org.rcsb.cif.api.generated.PdbxStructMsymGen::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxStructLegacyOperList getPdbxStructLegacyOperList() {
        return (org.rcsb.cif.api.generated.PdbxStructLegacyOperList) categories.computeIfAbsent("pdbx_struct_legacy_oper_list",
                org.rcsb.cif.api.generated.PdbxStructLegacyOperList::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxChemCompAtomFeature getPdbxChemCompAtomFeature() {
        return (org.rcsb.cif.api.generated.PdbxChemCompAtomFeature) categories.computeIfAbsent("pdbx_chem_comp_atom_feature",
                org.rcsb.cif.api.generated.PdbxChemCompAtomFeature::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxReferenceMoleculeFamily getPdbxReferenceMoleculeFamily() {
        return (org.rcsb.cif.api.generated.PdbxReferenceMoleculeFamily) categories.computeIfAbsent("pdbx_reference_molecule_family",
                org.rcsb.cif.api.generated.PdbxReferenceMoleculeFamily::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxReferenceMoleculeList getPdbxReferenceMoleculeList() {
        return (org.rcsb.cif.api.generated.PdbxReferenceMoleculeList) categories.computeIfAbsent("pdbx_reference_molecule_list",
                org.rcsb.cif.api.generated.PdbxReferenceMoleculeList::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxReferenceMolecule getPdbxReferenceMolecule() {
        return (org.rcsb.cif.api.generated.PdbxReferenceMolecule) categories.computeIfAbsent("pdbx_reference_molecule",
                org.rcsb.cif.api.generated.PdbxReferenceMolecule::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxReferenceEntityList getPdbxReferenceEntityList() {
        return (org.rcsb.cif.api.generated.PdbxReferenceEntityList) categories.computeIfAbsent("pdbx_reference_entity_list",
                org.rcsb.cif.api.generated.PdbxReferenceEntityList::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxReferenceEntityNonpoly getPdbxReferenceEntityNonpoly() {
        return (org.rcsb.cif.api.generated.PdbxReferenceEntityNonpoly) categories.computeIfAbsent("pdbx_reference_entity_nonpoly",
                org.rcsb.cif.api.generated.PdbxReferenceEntityNonpoly::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxReferenceEntityLink getPdbxReferenceEntityLink() {
        return (org.rcsb.cif.api.generated.PdbxReferenceEntityLink) categories.computeIfAbsent("pdbx_reference_entity_link",
                org.rcsb.cif.api.generated.PdbxReferenceEntityLink::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxReferenceEntityPolyLink getPdbxReferenceEntityPolyLink() {
        return (org.rcsb.cif.api.generated.PdbxReferenceEntityPolyLink) categories.computeIfAbsent("pdbx_reference_entity_poly_link",
                org.rcsb.cif.api.generated.PdbxReferenceEntityPolyLink::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxReferenceEntityPoly getPdbxReferenceEntityPoly() {
        return (org.rcsb.cif.api.generated.PdbxReferenceEntityPoly) categories.computeIfAbsent("pdbx_reference_entity_poly",
                org.rcsb.cif.api.generated.PdbxReferenceEntityPoly::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxReferenceEntityPolySeq getPdbxReferenceEntityPolySeq() {
        return (org.rcsb.cif.api.generated.PdbxReferenceEntityPolySeq) categories.computeIfAbsent("pdbx_reference_entity_poly_seq",
                org.rcsb.cif.api.generated.PdbxReferenceEntityPolySeq::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxReferenceEntitySequence getPdbxReferenceEntitySequence() {
        return (org.rcsb.cif.api.generated.PdbxReferenceEntitySequence) categories.computeIfAbsent("pdbx_reference_entity_sequence",
                org.rcsb.cif.api.generated.PdbxReferenceEntitySequence::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxReferenceEntitySrcNat getPdbxReferenceEntitySrcNat() {
        return (org.rcsb.cif.api.generated.PdbxReferenceEntitySrcNat) categories.computeIfAbsent("pdbx_reference_entity_src_nat",
                org.rcsb.cif.api.generated.PdbxReferenceEntitySrcNat::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxReferenceMoleculeDetails getPdbxReferenceMoleculeDetails() {
        return (org.rcsb.cif.api.generated.PdbxReferenceMoleculeDetails) categories.computeIfAbsent("pdbx_reference_molecule_details",
                org.rcsb.cif.api.generated.PdbxReferenceMoleculeDetails::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxReferenceMoleculeSynonyms getPdbxReferenceMoleculeSynonyms() {
        return (org.rcsb.cif.api.generated.PdbxReferenceMoleculeSynonyms) categories.computeIfAbsent("pdbx_reference_molecule_synonyms",
                org.rcsb.cif.api.generated.PdbxReferenceMoleculeSynonyms::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxReferenceEntitySubcomponents getPdbxReferenceEntitySubcomponents() {
        return (org.rcsb.cif.api.generated.PdbxReferenceEntitySubcomponents) categories.computeIfAbsent("pdbx_reference_entity_subcomponents",
                org.rcsb.cif.api.generated.PdbxReferenceEntitySubcomponents::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxReferenceMoleculeAnnotation getPdbxReferenceMoleculeAnnotation() {
        return (org.rcsb.cif.api.generated.PdbxReferenceMoleculeAnnotation) categories.computeIfAbsent("pdbx_reference_molecule_annotation",
                org.rcsb.cif.api.generated.PdbxReferenceMoleculeAnnotation::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxReferenceMoleculeFeatures getPdbxReferenceMoleculeFeatures() {
        return (org.rcsb.cif.api.generated.PdbxReferenceMoleculeFeatures) categories.computeIfAbsent("pdbx_reference_molecule_features",
                org.rcsb.cif.api.generated.PdbxReferenceMoleculeFeatures::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxReferenceMoleculeRelatedStructures getPdbxReferenceMoleculeRelatedStructures() {
        return (org.rcsb.cif.api.generated.PdbxReferenceMoleculeRelatedStructures) categories.computeIfAbsent("pdbx_reference_molecule_related_structures",
                org.rcsb.cif.api.generated.PdbxReferenceMoleculeRelatedStructures::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxStructGroupList getPdbxStructGroupList() {
        return (org.rcsb.cif.api.generated.PdbxStructGroupList) categories.computeIfAbsent("pdbx_struct_group_list",
                org.rcsb.cif.api.generated.PdbxStructGroupList::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxStructGroupComponents getPdbxStructGroupComponents() {
        return (org.rcsb.cif.api.generated.PdbxStructGroupComponents) categories.computeIfAbsent("pdbx_struct_group_components",
                org.rcsb.cif.api.generated.PdbxStructGroupComponents::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxStructGroupComponentRange getPdbxStructGroupComponentRange() {
        return (org.rcsb.cif.api.generated.PdbxStructGroupComponentRange) categories.computeIfAbsent("pdbx_struct_group_component_range",
                org.rcsb.cif.api.generated.PdbxStructGroupComponentRange::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxPrdAudit getPdbxPrdAudit() {
        return (org.rcsb.cif.api.generated.PdbxPrdAudit) categories.computeIfAbsent("pdbx_prd_audit",
                org.rcsb.cif.api.generated.PdbxPrdAudit::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxFamilyPrdAudit getPdbxFamilyPrdAudit() {
        return (org.rcsb.cif.api.generated.PdbxFamilyPrdAudit) categories.computeIfAbsent("pdbx_family_prd_audit",
                org.rcsb.cif.api.generated.PdbxFamilyPrdAudit::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxMolecule getPdbxMolecule() {
        return (org.rcsb.cif.api.generated.PdbxMolecule) categories.computeIfAbsent("pdbx_molecule",
                org.rcsb.cif.api.generated.PdbxMolecule::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxMoleculeFeatures getPdbxMoleculeFeatures() {
        return (org.rcsb.cif.api.generated.PdbxMoleculeFeatures) categories.computeIfAbsent("pdbx_molecule_features",
                org.rcsb.cif.api.generated.PdbxMoleculeFeatures::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxFamilyGroupIndex getPdbxFamilyGroupIndex() {
        return (org.rcsb.cif.api.generated.PdbxFamilyGroupIndex) categories.computeIfAbsent("pdbx_family_group_index",
                org.rcsb.cif.api.generated.PdbxFamilyGroupIndex::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxDistantSolventAtoms getPdbxDistantSolventAtoms() {
        return (org.rcsb.cif.api.generated.PdbxDistantSolventAtoms) categories.computeIfAbsent("pdbx_distant_solvent_atoms",
                org.rcsb.cif.api.generated.PdbxDistantSolventAtoms::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxStructSpecialSymmetry getPdbxStructSpecialSymmetry() {
        return (org.rcsb.cif.api.generated.PdbxStructSpecialSymmetry) categories.computeIfAbsent("pdbx_struct_special_symmetry",
                org.rcsb.cif.api.generated.PdbxStructSpecialSymmetry::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxReferencePublicationList getPdbxReferencePublicationList() {
        return (org.rcsb.cif.api.generated.PdbxReferencePublicationList) categories.computeIfAbsent("pdbx_reference_publication_list",
                org.rcsb.cif.api.generated.PdbxReferencePublicationList::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxNmrAssignedChemShiftList getPdbxNmrAssignedChemShiftList() {
        return (org.rcsb.cif.api.generated.PdbxNmrAssignedChemShiftList) categories.computeIfAbsent("pdbx_nmr_assigned_chem_shift_list",
                org.rcsb.cif.api.generated.PdbxNmrAssignedChemShiftList::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxNmrChemShiftExperiment getPdbxNmrChemShiftExperiment() {
        return (org.rcsb.cif.api.generated.PdbxNmrChemShiftExperiment) categories.computeIfAbsent("pdbx_nmr_chem_shift_experiment",
                org.rcsb.cif.api.generated.PdbxNmrChemShiftExperiment::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxNmrChemShiftRef getPdbxNmrChemShiftRef() {
        return (org.rcsb.cif.api.generated.PdbxNmrChemShiftRef) categories.computeIfAbsent("pdbx_nmr_chem_shift_ref",
                org.rcsb.cif.api.generated.PdbxNmrChemShiftRef::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxNmrChemShiftReference getPdbxNmrChemShiftReference() {
        return (org.rcsb.cif.api.generated.PdbxNmrChemShiftReference) categories.computeIfAbsent("pdbx_nmr_chem_shift_reference",
                org.rcsb.cif.api.generated.PdbxNmrChemShiftReference::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxNmrChemShiftSoftware getPdbxNmrChemShiftSoftware() {
        return (org.rcsb.cif.api.generated.PdbxNmrChemShiftSoftware) categories.computeIfAbsent("pdbx_nmr_chem_shift_software",
                org.rcsb.cif.api.generated.PdbxNmrChemShiftSoftware::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxNmrConstraintFile getPdbxNmrConstraintFile() {
        return (org.rcsb.cif.api.generated.PdbxNmrConstraintFile) categories.computeIfAbsent("pdbx_nmr_constraint_file",
                org.rcsb.cif.api.generated.PdbxNmrConstraintFile::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxNmrSoftwareTask getPdbxNmrSoftwareTask() {
        return (org.rcsb.cif.api.generated.PdbxNmrSoftwareTask) categories.computeIfAbsent("pdbx_nmr_software_task",
                org.rcsb.cif.api.generated.PdbxNmrSoftwareTask::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxNmrSpectralDim getPdbxNmrSpectralDim() {
        return (org.rcsb.cif.api.generated.PdbxNmrSpectralDim) categories.computeIfAbsent("pdbx_nmr_spectral_dim",
                org.rcsb.cif.api.generated.PdbxNmrSpectralDim::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxNmrSpectralPeakList getPdbxNmrSpectralPeakList() {
        return (org.rcsb.cif.api.generated.PdbxNmrSpectralPeakList) categories.computeIfAbsent("pdbx_nmr_spectral_peak_list",
                org.rcsb.cif.api.generated.PdbxNmrSpectralPeakList::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxNmrSpectralPeakSoftware getPdbxNmrSpectralPeakSoftware() {
        return (org.rcsb.cif.api.generated.PdbxNmrSpectralPeakSoftware) categories.computeIfAbsent("pdbx_nmr_spectral_peak_software",
                org.rcsb.cif.api.generated.PdbxNmrSpectralPeakSoftware::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxNmrSystematicChemShiftOffset getPdbxNmrSystematicChemShiftOffset() {
        return (org.rcsb.cif.api.generated.PdbxNmrSystematicChemShiftOffset) categories.computeIfAbsent("pdbx_nmr_systematic_chem_shift_offset",
                org.rcsb.cif.api.generated.PdbxNmrSystematicChemShiftOffset::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxNmrUpload getPdbxNmrUpload() {
        return (org.rcsb.cif.api.generated.PdbxNmrUpload) categories.computeIfAbsent("pdbx_nmr_upload",
                org.rcsb.cif.api.generated.PdbxNmrUpload::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxAuditSupport getPdbxAuditSupport() {
        return (org.rcsb.cif.api.generated.PdbxAuditSupport) categories.computeIfAbsent("pdbx_audit_support",
                org.rcsb.cif.api.generated.PdbxAuditSupport::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxChemCompSubcomponentStructConn getPdbxChemCompSubcomponentStructConn() {
        return (org.rcsb.cif.api.generated.PdbxChemCompSubcomponentStructConn) categories.computeIfAbsent("pdbx_chem_comp_subcomponent_struct_conn",
                org.rcsb.cif.api.generated.PdbxChemCompSubcomponentStructConn::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxChemCompSubcomponentEntityList getPdbxChemCompSubcomponentEntityList() {
        return (org.rcsb.cif.api.generated.PdbxChemCompSubcomponentEntityList) categories.computeIfAbsent("pdbx_chem_comp_subcomponent_entity_list",
                org.rcsb.cif.api.generated.PdbxChemCompSubcomponentEntityList::new).get();
    }

    public org.rcsb.cif.api.generated.EntitySrcNat getEntitySrcNat() {
        return (org.rcsb.cif.api.generated.EntitySrcNat) categories.computeIfAbsent("entity_src_nat",
                org.rcsb.cif.api.generated.EntitySrcNat::new).get();
    }

    public org.rcsb.cif.api.generated.EntitySrcGen getEntitySrcGen() {
        return (org.rcsb.cif.api.generated.EntitySrcGen) categories.computeIfAbsent("entity_src_gen",
                org.rcsb.cif.api.generated.EntitySrcGen::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxEntitySrcSyn getPdbxEntitySrcSyn() {
        return (org.rcsb.cif.api.generated.PdbxEntitySrcSyn) categories.computeIfAbsent("pdbx_entity_src_syn",
                org.rcsb.cif.api.generated.PdbxEntitySrcSyn::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxEntityPolyCompLinkList getPdbxEntityPolyCompLinkList() {
        return (org.rcsb.cif.api.generated.PdbxEntityPolyCompLinkList) categories.computeIfAbsent("pdbx_entity_poly_comp_link_list",
                org.rcsb.cif.api.generated.PdbxEntityPolyCompLinkList::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxLinkedEntity getPdbxLinkedEntity() {
        return (org.rcsb.cif.api.generated.PdbxLinkedEntity) categories.computeIfAbsent("pdbx_linked_entity",
                org.rcsb.cif.api.generated.PdbxLinkedEntity::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxLinkedEntityInstanceList getPdbxLinkedEntityInstanceList() {
        return (org.rcsb.cif.api.generated.PdbxLinkedEntityInstanceList) categories.computeIfAbsent("pdbx_linked_entity_instance_list",
                org.rcsb.cif.api.generated.PdbxLinkedEntityInstanceList::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxLinkedEntityList getPdbxLinkedEntityList() {
        return (org.rcsb.cif.api.generated.PdbxLinkedEntityList) categories.computeIfAbsent("pdbx_linked_entity_list",
                org.rcsb.cif.api.generated.PdbxLinkedEntityList::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxLinkedEntityLinkList getPdbxLinkedEntityLinkList() {
        return (org.rcsb.cif.api.generated.PdbxLinkedEntityLinkList) categories.computeIfAbsent("pdbx_linked_entity_link_list",
                org.rcsb.cif.api.generated.PdbxLinkedEntityLinkList::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxEntityDescriptor getPdbxEntityDescriptor() {
        return (org.rcsb.cif.api.generated.PdbxEntityDescriptor) categories.computeIfAbsent("pdbx_entity_descriptor",
                org.rcsb.cif.api.generated.PdbxEntityDescriptor::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxReferenceLinkedEntity getPdbxReferenceLinkedEntity() {
        return (org.rcsb.cif.api.generated.PdbxReferenceLinkedEntity) categories.computeIfAbsent("pdbx_reference_linked_entity",
                org.rcsb.cif.api.generated.PdbxReferenceLinkedEntity::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxReferenceLinkedEntityCompList getPdbxReferenceLinkedEntityCompList() {
        return (org.rcsb.cif.api.generated.PdbxReferenceLinkedEntityCompList) categories.computeIfAbsent("pdbx_reference_linked_entity_comp_list",
                org.rcsb.cif.api.generated.PdbxReferenceLinkedEntityCompList::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxReferenceLinkedEntityCompLink getPdbxReferenceLinkedEntityCompLink() {
        return (org.rcsb.cif.api.generated.PdbxReferenceLinkedEntityCompLink) categories.computeIfAbsent("pdbx_reference_linked_entity_comp_link",
                org.rcsb.cif.api.generated.PdbxReferenceLinkedEntityCompLink::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxReferenceLinkedEntityLink getPdbxReferenceLinkedEntityLink() {
        return (org.rcsb.cif.api.generated.PdbxReferenceLinkedEntityLink) categories.computeIfAbsent("pdbx_reference_linked_entity_link",
                org.rcsb.cif.api.generated.PdbxReferenceLinkedEntityLink::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxRelatedExpDataSet getPdbxRelatedExpDataSet() {
        return (org.rcsb.cif.api.generated.PdbxRelatedExpDataSet) categories.computeIfAbsent("pdbx_related_exp_data_set",
                org.rcsb.cif.api.generated.PdbxRelatedExpDataSet::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxDatabaseStatusHistory getPdbxDatabaseStatusHistory() {
        return (org.rcsb.cif.api.generated.PdbxDatabaseStatusHistory) categories.computeIfAbsent("pdbx_database_status_history",
                org.rcsb.cif.api.generated.PdbxDatabaseStatusHistory::new).get();
    }

    public org.rcsb.cif.api.generated.EmAssembly getEmAssembly() {
        return (org.rcsb.cif.api.generated.EmAssembly) categories.computeIfAbsent("em_assembly",
                org.rcsb.cif.api.generated.EmAssembly::new).get();
    }

    public org.rcsb.cif.api.generated.EmEntityAssembly getEmEntityAssembly() {
        return (org.rcsb.cif.api.generated.EmEntityAssembly) categories.computeIfAbsent("em_entity_assembly",
                org.rcsb.cif.api.generated.EmEntityAssembly::new).get();
    }

    public org.rcsb.cif.api.generated.EmVirusEntity getEmVirusEntity() {
        return (org.rcsb.cif.api.generated.EmVirusEntity) categories.computeIfAbsent("em_virus_entity",
                org.rcsb.cif.api.generated.EmVirusEntity::new).get();
    }

    public org.rcsb.cif.api.generated.EmSamplePreparation getEmSamplePreparation() {
        return (org.rcsb.cif.api.generated.EmSamplePreparation) categories.computeIfAbsent("em_sample_preparation",
                org.rcsb.cif.api.generated.EmSamplePreparation::new).get();
    }

    public org.rcsb.cif.api.generated.EmSampleSupport getEmSampleSupport() {
        return (org.rcsb.cif.api.generated.EmSampleSupport) categories.computeIfAbsent("em_sample_support",
                org.rcsb.cif.api.generated.EmSampleSupport::new).get();
    }

    public org.rcsb.cif.api.generated.EmBuffer getEmBuffer() {
        return (org.rcsb.cif.api.generated.EmBuffer) categories.computeIfAbsent("em_buffer",
                org.rcsb.cif.api.generated.EmBuffer::new).get();
    }

    public org.rcsb.cif.api.generated.EmVitrification getEmVitrification() {
        return (org.rcsb.cif.api.generated.EmVitrification) categories.computeIfAbsent("em_vitrification",
                org.rcsb.cif.api.generated.EmVitrification::new).get();
    }

    public org.rcsb.cif.api.generated.EmImaging getEmImaging() {
        return (org.rcsb.cif.api.generated.EmImaging) categories.computeIfAbsent("em_imaging",
                org.rcsb.cif.api.generated.EmImaging::new).get();
    }

    public org.rcsb.cif.api.generated.EmDetector getEmDetector() {
        return (org.rcsb.cif.api.generated.EmDetector) categories.computeIfAbsent("em_detector",
                org.rcsb.cif.api.generated.EmDetector::new).get();
    }

    public org.rcsb.cif.api.generated.EmImageScans getEmImageScans() {
        return (org.rcsb.cif.api.generated.EmImageScans) categories.computeIfAbsent("em_image_scans",
                org.rcsb.cif.api.generated.EmImageScans::new).get();
    }

    public org.rcsb.cif.api.generated.Em2dProjectionSelection getEm2dProjectionSelection() {
        return (org.rcsb.cif.api.generated.Em2dProjectionSelection) categories.computeIfAbsent("em_2d_projection_selection",
                org.rcsb.cif.api.generated.Em2dProjectionSelection::new).get();
    }

    public org.rcsb.cif.api.generated.Em3dReconstruction getEm3dReconstruction() {
        return (org.rcsb.cif.api.generated.Em3dReconstruction) categories.computeIfAbsent("em_3d_reconstruction",
                org.rcsb.cif.api.generated.Em3dReconstruction::new).get();
    }

    public org.rcsb.cif.api.generated.Em3dFitting getEm3dFitting() {
        return (org.rcsb.cif.api.generated.Em3dFitting) categories.computeIfAbsent("em_3d_fitting",
                org.rcsb.cif.api.generated.Em3dFitting::new).get();
    }

    public org.rcsb.cif.api.generated.Em3dFittingList getEm3dFittingList() {
        return (org.rcsb.cif.api.generated.Em3dFittingList) categories.computeIfAbsent("em_3d_fitting_list",
                org.rcsb.cif.api.generated.Em3dFittingList::new).get();
    }

    public org.rcsb.cif.api.generated.EmHelicalEntity getEmHelicalEntity() {
        return (org.rcsb.cif.api.generated.EmHelicalEntity) categories.computeIfAbsent("em_helical_entity",
                org.rcsb.cif.api.generated.EmHelicalEntity::new).get();
    }

    public org.rcsb.cif.api.generated.EmExperiment getEmExperiment() {
        return (org.rcsb.cif.api.generated.EmExperiment) categories.computeIfAbsent("em_experiment",
                org.rcsb.cif.api.generated.EmExperiment::new).get();
    }

    public org.rcsb.cif.api.generated.EmSingleParticleEntity getEmSingleParticleEntity() {
        return (org.rcsb.cif.api.generated.EmSingleParticleEntity) categories.computeIfAbsent("em_single_particle_entity",
                org.rcsb.cif.api.generated.EmSingleParticleEntity::new).get();
    }

    public org.rcsb.cif.api.generated.EmAdmin getEmAdmin() {
        return (org.rcsb.cif.api.generated.EmAdmin) categories.computeIfAbsent("em_admin",
                org.rcsb.cif.api.generated.EmAdmin::new).get();
    }

    public org.rcsb.cif.api.generated.EmAuthorList getEmAuthorList() {
        return (org.rcsb.cif.api.generated.EmAuthorList) categories.computeIfAbsent("em_author_list",
                org.rcsb.cif.api.generated.EmAuthorList::new).get();
    }

    public org.rcsb.cif.api.generated.EmDbReference getEmDbReference() {
        return (org.rcsb.cif.api.generated.EmDbReference) categories.computeIfAbsent("em_db_reference",
                org.rcsb.cif.api.generated.EmDbReference::new).get();
    }

    public org.rcsb.cif.api.generated.EmDbReferenceAuxiliary getEmDbReferenceAuxiliary() {
        return (org.rcsb.cif.api.generated.EmDbReferenceAuxiliary) categories.computeIfAbsent("em_db_reference_auxiliary",
                org.rcsb.cif.api.generated.EmDbReferenceAuxiliary::new).get();
    }

    public org.rcsb.cif.api.generated.EmDepui getEmDepui() {
        return (org.rcsb.cif.api.generated.EmDepui) categories.computeIfAbsent("em_depui",
                org.rcsb.cif.api.generated.EmDepui::new).get();
    }

    public org.rcsb.cif.api.generated.EmObsolete getEmObsolete() {
        return (org.rcsb.cif.api.generated.EmObsolete) categories.computeIfAbsent("em_obsolete",
                org.rcsb.cif.api.generated.EmObsolete::new).get();
    }

    public org.rcsb.cif.api.generated.EmSupersede getEmSupersede() {
        return (org.rcsb.cif.api.generated.EmSupersede) categories.computeIfAbsent("em_supersede",
                org.rcsb.cif.api.generated.EmSupersede::new).get();
    }

    public org.rcsb.cif.api.generated.EmEntityAssemblyMolwt getEmEntityAssemblyMolwt() {
        return (org.rcsb.cif.api.generated.EmEntityAssemblyMolwt) categories.computeIfAbsent("em_entity_assembly_molwt",
                org.rcsb.cif.api.generated.EmEntityAssemblyMolwt::new).get();
    }

    public org.rcsb.cif.api.generated.EmEntityAssemblyNaturalsource getEmEntityAssemblyNaturalsource() {
        return (org.rcsb.cif.api.generated.EmEntityAssemblyNaturalsource) categories.computeIfAbsent("em_entity_assembly_naturalsource",
                org.rcsb.cif.api.generated.EmEntityAssemblyNaturalsource::new).get();
    }

    public org.rcsb.cif.api.generated.EmEntityAssemblyRecombinant getEmEntityAssemblyRecombinant() {
        return (org.rcsb.cif.api.generated.EmEntityAssemblyRecombinant) categories.computeIfAbsent("em_entity_assembly_recombinant",
                org.rcsb.cif.api.generated.EmEntityAssemblyRecombinant::new).get();
    }

    public org.rcsb.cif.api.generated.EmVirusNaturalHost getEmVirusNaturalHost() {
        return (org.rcsb.cif.api.generated.EmVirusNaturalHost) categories.computeIfAbsent("em_virus_natural_host",
                org.rcsb.cif.api.generated.EmVirusNaturalHost::new).get();
    }

    public org.rcsb.cif.api.generated.EmVirusShell getEmVirusShell() {
        return (org.rcsb.cif.api.generated.EmVirusShell) categories.computeIfAbsent("em_virus_shell",
                org.rcsb.cif.api.generated.EmVirusShell::new).get();
    }

    public org.rcsb.cif.api.generated.EmSpecimen getEmSpecimen() {
        return (org.rcsb.cif.api.generated.EmSpecimen) categories.computeIfAbsent("em_specimen",
                org.rcsb.cif.api.generated.EmSpecimen::new).get();
    }

    public org.rcsb.cif.api.generated.EmEmbedding getEmEmbedding() {
        return (org.rcsb.cif.api.generated.EmEmbedding) categories.computeIfAbsent("em_embedding",
                org.rcsb.cif.api.generated.EmEmbedding::new).get();
    }

    public org.rcsb.cif.api.generated.EmFiducialMarkers getEmFiducialMarkers() {
        return (org.rcsb.cif.api.generated.EmFiducialMarkers) categories.computeIfAbsent("em_fiducial_markers",
                org.rcsb.cif.api.generated.EmFiducialMarkers::new).get();
    }

    public org.rcsb.cif.api.generated.EmFocusedIonBeam getEmFocusedIonBeam() {
        return (org.rcsb.cif.api.generated.EmFocusedIonBeam) categories.computeIfAbsent("em_focused_ion_beam",
                org.rcsb.cif.api.generated.EmFocusedIonBeam::new).get();
    }

    public org.rcsb.cif.api.generated.EmGridPretreatment getEmGridPretreatment() {
        return (org.rcsb.cif.api.generated.EmGridPretreatment) categories.computeIfAbsent("em_grid_pretreatment",
                org.rcsb.cif.api.generated.EmGridPretreatment::new).get();
    }

    public org.rcsb.cif.api.generated.EmUltramicrotomy getEmUltramicrotomy() {
        return (org.rcsb.cif.api.generated.EmUltramicrotomy) categories.computeIfAbsent("em_ultramicrotomy",
                org.rcsb.cif.api.generated.EmUltramicrotomy::new).get();
    }

    public org.rcsb.cif.api.generated.EmHighPressureFreezing getEmHighPressureFreezing() {
        return (org.rcsb.cif.api.generated.EmHighPressureFreezing) categories.computeIfAbsent("em_high_pressure_freezing",
                org.rcsb.cif.api.generated.EmHighPressureFreezing::new).get();
    }

    public org.rcsb.cif.api.generated.EmShadowing getEmShadowing() {
        return (org.rcsb.cif.api.generated.EmShadowing) categories.computeIfAbsent("em_shadowing",
                org.rcsb.cif.api.generated.EmShadowing::new).get();
    }

    public org.rcsb.cif.api.generated.EmTomographySpecimen getEmTomographySpecimen() {
        return (org.rcsb.cif.api.generated.EmTomographySpecimen) categories.computeIfAbsent("em_tomography_specimen",
                org.rcsb.cif.api.generated.EmTomographySpecimen::new).get();
    }

    public org.rcsb.cif.api.generated.EmCrystalFormation getEmCrystalFormation() {
        return (org.rcsb.cif.api.generated.EmCrystalFormation) categories.computeIfAbsent("em_crystal_formation",
                org.rcsb.cif.api.generated.EmCrystalFormation::new).get();
    }

    public org.rcsb.cif.api.generated.EmStaining getEmStaining() {
        return (org.rcsb.cif.api.generated.EmStaining) categories.computeIfAbsent("em_staining",
                org.rcsb.cif.api.generated.EmStaining::new).get();
    }

    public org.rcsb.cif.api.generated.EmSupportFilm getEmSupportFilm() {
        return (org.rcsb.cif.api.generated.EmSupportFilm) categories.computeIfAbsent("em_support_film",
                org.rcsb.cif.api.generated.EmSupportFilm::new).get();
    }

    public org.rcsb.cif.api.generated.EmBufferComponent getEmBufferComponent() {
        return (org.rcsb.cif.api.generated.EmBufferComponent) categories.computeIfAbsent("em_buffer_component",
                org.rcsb.cif.api.generated.EmBufferComponent::new).get();
    }

    public org.rcsb.cif.api.generated.EmDiffraction getEmDiffraction() {
        return (org.rcsb.cif.api.generated.EmDiffraction) categories.computeIfAbsent("em_diffraction",
                org.rcsb.cif.api.generated.EmDiffraction::new).get();
    }

    public org.rcsb.cif.api.generated.EmDiffractionShell getEmDiffractionShell() {
        return (org.rcsb.cif.api.generated.EmDiffractionShell) categories.computeIfAbsent("em_diffraction_shell",
                org.rcsb.cif.api.generated.EmDiffractionShell::new).get();
    }

    public org.rcsb.cif.api.generated.EmDiffractionStats getEmDiffractionStats() {
        return (org.rcsb.cif.api.generated.EmDiffractionStats) categories.computeIfAbsent("em_diffraction_stats",
                org.rcsb.cif.api.generated.EmDiffractionStats::new).get();
    }

    public org.rcsb.cif.api.generated.EmTomography getEmTomography() {
        return (org.rcsb.cif.api.generated.EmTomography) categories.computeIfAbsent("em_tomography",
                org.rcsb.cif.api.generated.EmTomography::new).get();
    }

    public org.rcsb.cif.api.generated.EmImageRecording getEmImageRecording() {
        return (org.rcsb.cif.api.generated.EmImageRecording) categories.computeIfAbsent("em_image_recording",
                org.rcsb.cif.api.generated.EmImageRecording::new).get();
    }

    public org.rcsb.cif.api.generated.EmImagingOptics getEmImagingOptics() {
        return (org.rcsb.cif.api.generated.EmImagingOptics) categories.computeIfAbsent("em_imaging_optics",
                org.rcsb.cif.api.generated.EmImagingOptics::new).get();
    }

    public org.rcsb.cif.api.generated.EmFinalClassification getEmFinalClassification() {
        return (org.rcsb.cif.api.generated.EmFinalClassification) categories.computeIfAbsent("em_final_classification",
                org.rcsb.cif.api.generated.EmFinalClassification::new).get();
    }

    public org.rcsb.cif.api.generated.EmStartModel getEmStartModel() {
        return (org.rcsb.cif.api.generated.EmStartModel) categories.computeIfAbsent("em_start_model",
                org.rcsb.cif.api.generated.EmStartModel::new).get();
    }

    public org.rcsb.cif.api.generated.EmSoftware getEmSoftware() {
        return (org.rcsb.cif.api.generated.EmSoftware) categories.computeIfAbsent("em_software",
                org.rcsb.cif.api.generated.EmSoftware::new).get();
    }

    public org.rcsb.cif.api.generated.EmEulerAngleAssignment getEmEulerAngleAssignment() {
        return (org.rcsb.cif.api.generated.EmEulerAngleAssignment) categories.computeIfAbsent("em_euler_angle_assignment",
                org.rcsb.cif.api.generated.EmEulerAngleAssignment::new).get();
    }

    public org.rcsb.cif.api.generated.EmCtfCorrection getEmCtfCorrection() {
        return (org.rcsb.cif.api.generated.EmCtfCorrection) categories.computeIfAbsent("em_ctf_correction",
                org.rcsb.cif.api.generated.EmCtfCorrection::new).get();
    }

    public org.rcsb.cif.api.generated.EmVolumeSelection getEmVolumeSelection() {
        return (org.rcsb.cif.api.generated.EmVolumeSelection) categories.computeIfAbsent("em_volume_selection",
                org.rcsb.cif.api.generated.EmVolumeSelection::new).get();
    }

    public org.rcsb.cif.api.generated.Em3dCrystalEntity getEm3dCrystalEntity() {
        return (org.rcsb.cif.api.generated.Em3dCrystalEntity) categories.computeIfAbsent("em_3d_crystal_entity",
                org.rcsb.cif.api.generated.Em3dCrystalEntity::new).get();
    }

    public org.rcsb.cif.api.generated.Em2dCrystalEntity getEm2dCrystalEntity() {
        return (org.rcsb.cif.api.generated.Em2dCrystalEntity) categories.computeIfAbsent("em_2d_crystal_entity",
                org.rcsb.cif.api.generated.Em2dCrystalEntity::new).get();
    }

    public org.rcsb.cif.api.generated.EmImageProcessing getEmImageProcessing() {
        return (org.rcsb.cif.api.generated.EmImageProcessing) categories.computeIfAbsent("em_image_processing",
                org.rcsb.cif.api.generated.EmImageProcessing::new).get();
    }

    public org.rcsb.cif.api.generated.EmParticleSelection getEmParticleSelection() {
        return (org.rcsb.cif.api.generated.EmParticleSelection) categories.computeIfAbsent("em_particle_selection",
                org.rcsb.cif.api.generated.EmParticleSelection::new).get();
    }

    public org.rcsb.cif.api.generated.EmMap getEmMap() {
        return (org.rcsb.cif.api.generated.EmMap) categories.computeIfAbsent("em_map",
                org.rcsb.cif.api.generated.EmMap::new).get();
    }

    public org.rcsb.cif.api.generated.EmFscCurve getEmFscCurve() {
        return (org.rcsb.cif.api.generated.EmFscCurve) categories.computeIfAbsent("em_fsc_curve",
                org.rcsb.cif.api.generated.EmFscCurve::new).get();
    }

    public org.rcsb.cif.api.generated.EmInterpretFigure getEmInterpretFigure() {
        return (org.rcsb.cif.api.generated.EmInterpretFigure) categories.computeIfAbsent("em_interpret_figure",
                org.rcsb.cif.api.generated.EmInterpretFigure::new).get();
    }

    public org.rcsb.cif.api.generated.EmLayerLines getEmLayerLines() {
        return (org.rcsb.cif.api.generated.EmLayerLines) categories.computeIfAbsent("em_layer_lines",
                org.rcsb.cif.api.generated.EmLayerLines::new).get();
    }

    public org.rcsb.cif.api.generated.EmStructureFactors getEmStructureFactors() {
        return (org.rcsb.cif.api.generated.EmStructureFactors) categories.computeIfAbsent("em_structure_factors",
                org.rcsb.cif.api.generated.EmStructureFactors::new).get();
    }

    public org.rcsb.cif.api.generated.EmDepositorInfo getEmDepositorInfo() {
        return (org.rcsb.cif.api.generated.EmDepositorInfo) categories.computeIfAbsent("em_depositor_info",
                org.rcsb.cif.api.generated.EmDepositorInfo::new).get();
    }

    public org.rcsb.cif.api.generated.EmMapDepositorInfo getEmMapDepositorInfo() {
        return (org.rcsb.cif.api.generated.EmMapDepositorInfo) categories.computeIfAbsent("em_map_depositor_info",
                org.rcsb.cif.api.generated.EmMapDepositorInfo::new).get();
    }

    public org.rcsb.cif.api.generated.EmMaskDepositorInfo getEmMaskDepositorInfo() {
        return (org.rcsb.cif.api.generated.EmMaskDepositorInfo) categories.computeIfAbsent("em_mask_depositor_info",
                org.rcsb.cif.api.generated.EmMaskDepositorInfo::new).get();
    }

    public org.rcsb.cif.api.generated.EmFigureDepositorInfo getEmFigureDepositorInfo() {
        return (org.rcsb.cif.api.generated.EmFigureDepositorInfo) categories.computeIfAbsent("em_figure_depositor_info",
                org.rcsb.cif.api.generated.EmFigureDepositorInfo::new).get();
    }

    public org.rcsb.cif.api.generated.EmLayerLinesDepositorInfo getEmLayerLinesDepositorInfo() {
        return (org.rcsb.cif.api.generated.EmLayerLinesDepositorInfo) categories.computeIfAbsent("em_layer_lines_depositor_info",
                org.rcsb.cif.api.generated.EmLayerLinesDepositorInfo::new).get();
    }

    public org.rcsb.cif.api.generated.EmStructureFactorsDepositorInfo getEmStructureFactorsDepositorInfo() {
        return (org.rcsb.cif.api.generated.EmStructureFactorsDepositorInfo) categories.computeIfAbsent("em_structure_factors_depositor_info",
                org.rcsb.cif.api.generated.EmStructureFactorsDepositorInfo::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxSeqMapDepositorInfo getPdbxSeqMapDepositorInfo() {
        return (org.rcsb.cif.api.generated.PdbxSeqMapDepositorInfo) categories.computeIfAbsent("pdbx_seq_map_depositor_info",
                org.rcsb.cif.api.generated.PdbxSeqMapDepositorInfo::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxChemCompDepositorInfo getPdbxChemCompDepositorInfo() {
        return (org.rcsb.cif.api.generated.PdbxChemCompDepositorInfo) categories.computeIfAbsent("pdbx_chem_comp_depositor_info",
                org.rcsb.cif.api.generated.PdbxChemCompDepositorInfo::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxStructRefSeqDepositorInfo getPdbxStructRefSeqDepositorInfo() {
        return (org.rcsb.cif.api.generated.PdbxStructRefSeqDepositorInfo) categories.computeIfAbsent("pdbx_struct_ref_seq_depositor_info",
                org.rcsb.cif.api.generated.PdbxStructRefSeqDepositorInfo::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxStructRefSeqDifDepositorInfo getPdbxStructRefSeqDifDepositorInfo() {
        return (org.rcsb.cif.api.generated.PdbxStructRefSeqDifDepositorInfo) categories.computeIfAbsent("pdbx_struct_ref_seq_dif_depositor_info",
                org.rcsb.cif.api.generated.PdbxStructRefSeqDifDepositorInfo::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxStructAssemblyPropDepositorInfo getPdbxStructAssemblyPropDepositorInfo() {
        return (org.rcsb.cif.api.generated.PdbxStructAssemblyPropDepositorInfo) categories.computeIfAbsent("pdbx_struct_assembly_prop_depositor_info",
                org.rcsb.cif.api.generated.PdbxStructAssemblyPropDepositorInfo::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxStructAssemblyDepositorInfo getPdbxStructAssemblyDepositorInfo() {
        return (org.rcsb.cif.api.generated.PdbxStructAssemblyDepositorInfo) categories.computeIfAbsent("pdbx_struct_assembly_depositor_info",
                org.rcsb.cif.api.generated.PdbxStructAssemblyDepositorInfo::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxStructAssemblyGenDepositorInfo getPdbxStructAssemblyGenDepositorInfo() {
        return (org.rcsb.cif.api.generated.PdbxStructAssemblyGenDepositorInfo) categories.computeIfAbsent("pdbx_struct_assembly_gen_depositor_info",
                org.rcsb.cif.api.generated.PdbxStructAssemblyGenDepositorInfo::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxStructOperListDepositorInfo getPdbxStructOperListDepositorInfo() {
        return (org.rcsb.cif.api.generated.PdbxStructOperListDepositorInfo) categories.computeIfAbsent("pdbx_struct_oper_list_depositor_info",
                org.rcsb.cif.api.generated.PdbxStructOperListDepositorInfo::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxPointSymmetryDepositorInfo getPdbxPointSymmetryDepositorInfo() {
        return (org.rcsb.cif.api.generated.PdbxPointSymmetryDepositorInfo) categories.computeIfAbsent("pdbx_point_symmetry_depositor_info",
                org.rcsb.cif.api.generated.PdbxPointSymmetryDepositorInfo::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxHelicalSymmetryDepositorInfo getPdbxHelicalSymmetryDepositorInfo() {
        return (org.rcsb.cif.api.generated.PdbxHelicalSymmetryDepositorInfo) categories.computeIfAbsent("pdbx_helical_symmetry_depositor_info",
                org.rcsb.cif.api.generated.PdbxHelicalSymmetryDepositorInfo::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxStructAssemblyAuthEvidenceDepositorInfo getPdbxStructAssemblyAuthEvidenceDepositorInfo() {
        return (org.rcsb.cif.api.generated.PdbxStructAssemblyAuthEvidenceDepositorInfo) categories.computeIfAbsent("pdbx_struct_assembly_auth_evidence_depositor_info",
                org.rcsb.cif.api.generated.PdbxStructAssemblyAuthEvidenceDepositorInfo::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxSolventAtomSiteMapping getPdbxSolventAtomSiteMapping() {
        return (org.rcsb.cif.api.generated.PdbxSolventAtomSiteMapping) categories.computeIfAbsent("pdbx_solvent_atom_site_mapping",
                org.rcsb.cif.api.generated.PdbxSolventAtomSiteMapping::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxMoleculeFeaturesDepositorInfo getPdbxMoleculeFeaturesDepositorInfo() {
        return (org.rcsb.cif.api.generated.PdbxMoleculeFeaturesDepositorInfo) categories.computeIfAbsent("pdbx_molecule_features_depositor_info",
                org.rcsb.cif.api.generated.PdbxMoleculeFeaturesDepositorInfo::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxChemCompInstanceDepositorInfo getPdbxChemCompInstanceDepositorInfo() {
        return (org.rcsb.cif.api.generated.PdbxChemCompInstanceDepositorInfo) categories.computeIfAbsent("pdbx_chem_comp_instance_depositor_info",
                org.rcsb.cif.api.generated.PdbxChemCompInstanceDepositorInfo::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxDepuiStatusFlags getPdbxDepuiStatusFlags() {
        return (org.rcsb.cif.api.generated.PdbxDepuiStatusFlags) categories.computeIfAbsent("pdbx_depui_status_flags",
                org.rcsb.cif.api.generated.PdbxDepuiStatusFlags::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxDepuiUpload getPdbxDepuiUpload() {
        return (org.rcsb.cif.api.generated.PdbxDepuiUpload) categories.computeIfAbsent("pdbx_depui_upload",
                org.rcsb.cif.api.generated.PdbxDepuiUpload::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxDepuiValidationStatusFlags getPdbxDepuiValidationStatusFlags() {
        return (org.rcsb.cif.api.generated.PdbxDepuiValidationStatusFlags) categories.computeIfAbsent("pdbx_depui_validation_status_flags",
                org.rcsb.cif.api.generated.PdbxDepuiValidationStatusFlags::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxChemCompUploadDepositorInfo getPdbxChemCompUploadDepositorInfo() {
        return (org.rcsb.cif.api.generated.PdbxChemCompUploadDepositorInfo) categories.computeIfAbsent("pdbx_chem_comp_upload_depositor_info",
                org.rcsb.cif.api.generated.PdbxChemCompUploadDepositorInfo::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxDepuiEntityStatusFlags getPdbxDepuiEntityStatusFlags() {
        return (org.rcsb.cif.api.generated.PdbxDepuiEntityStatusFlags) categories.computeIfAbsent("pdbx_depui_entity_status_flags",
                org.rcsb.cif.api.generated.PdbxDepuiEntityStatusFlags::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxDepuiEntityFeatures getPdbxDepuiEntityFeatures() {
        return (org.rcsb.cif.api.generated.PdbxDepuiEntityFeatures) categories.computeIfAbsent("pdbx_depui_entity_features",
                org.rcsb.cif.api.generated.PdbxDepuiEntityFeatures::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxDepositionMessageInfo getPdbxDepositionMessageInfo() {
        return (org.rcsb.cif.api.generated.PdbxDepositionMessageInfo) categories.computeIfAbsent("pdbx_deposition_message_info",
                org.rcsb.cif.api.generated.PdbxDepositionMessageInfo::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxDepositionMessageFileReference getPdbxDepositionMessageFileReference() {
        return (org.rcsb.cif.api.generated.PdbxDepositionMessageFileReference) categories.computeIfAbsent("pdbx_deposition_message_file_reference",
                org.rcsb.cif.api.generated.PdbxDepositionMessageFileReference::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxDepuiEntryDetails getPdbxDepuiEntryDetails() {
        return (org.rcsb.cif.api.generated.PdbxDepuiEntryDetails) categories.computeIfAbsent("pdbx_depui_entry_details",
                org.rcsb.cif.api.generated.PdbxDepuiEntryDetails::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxDataProcessingStatus getPdbxDataProcessingStatus() {
        return (org.rcsb.cif.api.generated.PdbxDataProcessingStatus) categories.computeIfAbsent("pdbx_data_processing_status",
                org.rcsb.cif.api.generated.PdbxDataProcessingStatus::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxEntityInstanceFeature getPdbxEntityInstanceFeature() {
        return (org.rcsb.cif.api.generated.PdbxEntityInstanceFeature) categories.computeIfAbsent("pdbx_entity_instance_feature",
                org.rcsb.cif.api.generated.PdbxEntityInstanceFeature::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxEntitySrcGenDepositorInfo getPdbxEntitySrcGenDepositorInfo() {
        return (org.rcsb.cif.api.generated.PdbxEntitySrcGenDepositorInfo) categories.computeIfAbsent("pdbx_entity_src_gen_depositor_info",
                org.rcsb.cif.api.generated.PdbxEntitySrcGenDepositorInfo::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxChemCompModel getPdbxChemCompModel() {
        return (org.rcsb.cif.api.generated.PdbxChemCompModel) categories.computeIfAbsent("pdbx_chem_comp_model",
                org.rcsb.cif.api.generated.PdbxChemCompModel::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxChemCompModelAtom getPdbxChemCompModelAtom() {
        return (org.rcsb.cif.api.generated.PdbxChemCompModelAtom) categories.computeIfAbsent("pdbx_chem_comp_model_atom",
                org.rcsb.cif.api.generated.PdbxChemCompModelAtom::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxChemCompModelBond getPdbxChemCompModelBond() {
        return (org.rcsb.cif.api.generated.PdbxChemCompModelBond) categories.computeIfAbsent("pdbx_chem_comp_model_bond",
                org.rcsb.cif.api.generated.PdbxChemCompModelBond::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxChemCompModelFeature getPdbxChemCompModelFeature() {
        return (org.rcsb.cif.api.generated.PdbxChemCompModelFeature) categories.computeIfAbsent("pdbx_chem_comp_model_feature",
                org.rcsb.cif.api.generated.PdbxChemCompModelFeature::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxChemCompModelDescriptor getPdbxChemCompModelDescriptor() {
        return (org.rcsb.cif.api.generated.PdbxChemCompModelDescriptor) categories.computeIfAbsent("pdbx_chem_comp_model_descriptor",
                org.rcsb.cif.api.generated.PdbxChemCompModelDescriptor::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxChemCompModelAudit getPdbxChemCompModelAudit() {
        return (org.rcsb.cif.api.generated.PdbxChemCompModelAudit) categories.computeIfAbsent("pdbx_chem_comp_model_audit",
                org.rcsb.cif.api.generated.PdbxChemCompModelAudit::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxChemCompModelReference getPdbxChemCompModelReference() {
        return (org.rcsb.cif.api.generated.PdbxChemCompModelReference) categories.computeIfAbsent("pdbx_chem_comp_model_reference",
                org.rcsb.cif.api.generated.PdbxChemCompModelReference::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxViewCategoryGroup getPdbxViewCategoryGroup() {
        return (org.rcsb.cif.api.generated.PdbxViewCategoryGroup) categories.computeIfAbsent("pdbx_view_category_group",
                org.rcsb.cif.api.generated.PdbxViewCategoryGroup::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxViewCategory getPdbxViewCategory() {
        return (org.rcsb.cif.api.generated.PdbxViewCategory) categories.computeIfAbsent("pdbx_view_category",
                org.rcsb.cif.api.generated.PdbxViewCategory::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxViewItem getPdbxViewItem() {
        return (org.rcsb.cif.api.generated.PdbxViewItem) categories.computeIfAbsent("pdbx_view_item",
                org.rcsb.cif.api.generated.PdbxViewItem::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxCoord getPdbxCoord() {
        return (org.rcsb.cif.api.generated.PdbxCoord) categories.computeIfAbsent("pdbx_coord",
                org.rcsb.cif.api.generated.PdbxCoord::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxConnect getPdbxConnect() {
        return (org.rcsb.cif.api.generated.PdbxConnect) categories.computeIfAbsent("pdbx_connect",
                org.rcsb.cif.api.generated.PdbxConnect::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxConnectType getPdbxConnectType() {
        return (org.rcsb.cif.api.generated.PdbxConnectType) categories.computeIfAbsent("pdbx_connect_type",
                org.rcsb.cif.api.generated.PdbxConnectType::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxConnectModification getPdbxConnectModification() {
        return (org.rcsb.cif.api.generated.PdbxConnectModification) categories.computeIfAbsent("pdbx_connect_modification",
                org.rcsb.cif.api.generated.PdbxConnectModification::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxConnectAtom getPdbxConnectAtom() {
        return (org.rcsb.cif.api.generated.PdbxConnectAtom) categories.computeIfAbsent("pdbx_connect_atom",
                org.rcsb.cif.api.generated.PdbxConnectAtom::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxDatabasePDBMaster getPdbxDatabasePDBMaster() {
        return (org.rcsb.cif.api.generated.PdbxDatabasePDBMaster) categories.computeIfAbsent("pdbx_database_PDB_master",
                org.rcsb.cif.api.generated.PdbxDatabasePDBMaster::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxDatabasePdbOmit getPdbxDatabasePdbOmit() {
        return (org.rcsb.cif.api.generated.PdbxDatabasePdbOmit) categories.computeIfAbsent("pdbx_database_pdb_omit",
                org.rcsb.cif.api.generated.PdbxDatabasePdbOmit::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxDbref getPdbxDbref() {
        return (org.rcsb.cif.api.generated.PdbxDbref) categories.computeIfAbsent("pdbx_dbref",
                org.rcsb.cif.api.generated.PdbxDbref::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxDrugInfo getPdbxDrugInfo() {
        return (org.rcsb.cif.api.generated.PdbxDrugInfo) categories.computeIfAbsent("pdbx_drug_info",
                org.rcsb.cif.api.generated.PdbxDrugInfo::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxInhibitorInfo getPdbxInhibitorInfo() {
        return (org.rcsb.cif.api.generated.PdbxInhibitorInfo) categories.computeIfAbsent("pdbx_inhibitor_info",
                org.rcsb.cif.api.generated.PdbxInhibitorInfo::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxIonInfo getPdbxIonInfo() {
        return (org.rcsb.cif.api.generated.PdbxIonInfo) categories.computeIfAbsent("pdbx_ion_info",
                org.rcsb.cif.api.generated.PdbxIonInfo::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxHybrid getPdbxHybrid() {
        return (org.rcsb.cif.api.generated.PdbxHybrid) categories.computeIfAbsent("pdbx_hybrid",
                org.rcsb.cif.api.generated.PdbxHybrid::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxNaStrandInfo getPdbxNaStrandInfo() {
        return (org.rcsb.cif.api.generated.PdbxNaStrandInfo) categories.computeIfAbsent("pdbx_na_strand_info",
                org.rcsb.cif.api.generated.PdbxNaStrandInfo::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxNonstandardList getPdbxNonstandardList() {
        return (org.rcsb.cif.api.generated.PdbxNonstandardList) categories.computeIfAbsent("pdbx_nonstandard_list",
                org.rcsb.cif.api.generated.PdbxNonstandardList::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxPdbCompnd getPdbxPdbCompnd() {
        return (org.rcsb.cif.api.generated.PdbxPdbCompnd) categories.computeIfAbsent("pdbx_pdb_compnd",
                org.rcsb.cif.api.generated.PdbxPdbCompnd::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxPdbSource getPdbxPdbSource() {
        return (org.rcsb.cif.api.generated.PdbxPdbSource) categories.computeIfAbsent("pdbx_pdb_source",
                org.rcsb.cif.api.generated.PdbxPdbSource::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxProteinInfo getPdbxProteinInfo() {
        return (org.rcsb.cif.api.generated.PdbxProteinInfo) categories.computeIfAbsent("pdbx_protein_info",
                org.rcsb.cif.api.generated.PdbxProteinInfo::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxSolventInfo getPdbxSolventInfo() {
        return (org.rcsb.cif.api.generated.PdbxSolventInfo) categories.computeIfAbsent("pdbx_solvent_info",
                org.rcsb.cif.api.generated.PdbxSolventInfo::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxSource getPdbxSource() {
        return (org.rcsb.cif.api.generated.PdbxSource) categories.computeIfAbsent("pdbx_source",
                org.rcsb.cif.api.generated.PdbxSource::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxStructBiolFunc getPdbxStructBiolFunc() {
        return (org.rcsb.cif.api.generated.PdbxStructBiolFunc) categories.computeIfAbsent("pdbx_struct_biol_func",
                org.rcsb.cif.api.generated.PdbxStructBiolFunc::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxStructPackGen getPdbxStructPackGen() {
        return (org.rcsb.cif.api.generated.PdbxStructPackGen) categories.computeIfAbsent("pdbx_struct_pack_gen",
                org.rcsb.cif.api.generated.PdbxStructPackGen::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxTrnaInfo getPdbxTrnaInfo() {
        return (org.rcsb.cif.api.generated.PdbxTrnaInfo) categories.computeIfAbsent("pdbx_trna_info",
                org.rcsb.cif.api.generated.PdbxTrnaInfo::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxUnpair getPdbxUnpair() {
        return (org.rcsb.cif.api.generated.PdbxUnpair) categories.computeIfAbsent("pdbx_unpair",
                org.rcsb.cif.api.generated.PdbxUnpair::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxRefineLsRestrNcs getPdbxRefineLsRestrNcs() {
        return (org.rcsb.cif.api.generated.PdbxRefineLsRestrNcs) categories.computeIfAbsent("pdbx_refine_ls_restr_ncs",
                org.rcsb.cif.api.generated.PdbxRefineLsRestrNcs::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxStructNcsVirusGen getPdbxStructNcsVirusGen() {
        return (org.rcsb.cif.api.generated.PdbxStructNcsVirusGen) categories.computeIfAbsent("pdbx_struct_ncs_virus_gen",
                org.rcsb.cif.api.generated.PdbxStructNcsVirusGen::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxSequenceAnnotation getPdbxSequenceAnnotation() {
        return (org.rcsb.cif.api.generated.PdbxSequenceAnnotation) categories.computeIfAbsent("pdbx_sequence_annotation",
                org.rcsb.cif.api.generated.PdbxSequenceAnnotation::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxPostProcessDetails getPdbxPostProcessDetails() {
        return (org.rcsb.cif.api.generated.PdbxPostProcessDetails) categories.computeIfAbsent("pdbx_post_process_details",
                org.rcsb.cif.api.generated.PdbxPostProcessDetails::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxPostProcessStatus getPdbxPostProcessStatus() {
        return (org.rcsb.cif.api.generated.PdbxPostProcessStatus) categories.computeIfAbsent("pdbx_post_process_status",
                org.rcsb.cif.api.generated.PdbxPostProcessStatus::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxStructLink getPdbxStructLink() {
        return (org.rcsb.cif.api.generated.PdbxStructLink) categories.computeIfAbsent("pdbx_struct_link",
                org.rcsb.cif.api.generated.PdbxStructLink::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxMissingResidueList getPdbxMissingResidueList() {
        return (org.rcsb.cif.api.generated.PdbxMissingResidueList) categories.computeIfAbsent("pdbx_missing_residue_list",
                org.rcsb.cif.api.generated.PdbxMissingResidueList::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxDataProcessingCell getPdbxDataProcessingCell() {
        return (org.rcsb.cif.api.generated.PdbxDataProcessingCell) categories.computeIfAbsent("pdbx_data_processing_cell",
                org.rcsb.cif.api.generated.PdbxDataProcessingCell::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxDataProcessingReflns getPdbxDataProcessingReflns() {
        return (org.rcsb.cif.api.generated.PdbxDataProcessingReflns) categories.computeIfAbsent("pdbx_data_processing_reflns",
                org.rcsb.cif.api.generated.PdbxDataProcessingReflns::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxDataProcessingDetector getPdbxDataProcessingDetector() {
        return (org.rcsb.cif.api.generated.PdbxDataProcessingDetector) categories.computeIfAbsent("pdbx_data_processing_detector",
                org.rcsb.cif.api.generated.PdbxDataProcessingDetector::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxChemCompNonstandard getPdbxChemCompNonstandard() {
        return (org.rcsb.cif.api.generated.PdbxChemCompNonstandard) categories.computeIfAbsent("pdbx_chem_comp_nonstandard",
                org.rcsb.cif.api.generated.PdbxChemCompNonstandard::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxEntityPolyProteinClass getPdbxEntityPolyProteinClass() {
        return (org.rcsb.cif.api.generated.PdbxEntityPolyProteinClass) categories.computeIfAbsent("pdbx_entity_poly_protein_class",
                org.rcsb.cif.api.generated.PdbxEntityPolyProteinClass::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxEntityNameTaxonomyTree getPdbxEntityNameTaxonomyTree() {
        return (org.rcsb.cif.api.generated.PdbxEntityNameTaxonomyTree) categories.computeIfAbsent("pdbx_entity_name_taxonomy_tree",
                org.rcsb.cif.api.generated.PdbxEntityNameTaxonomyTree::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxEntityNameTaxonomy getPdbxEntityNameTaxonomy() {
        return (org.rcsb.cif.api.generated.PdbxEntityNameTaxonomy) categories.computeIfAbsent("pdbx_entity_name_taxonomy",
                org.rcsb.cif.api.generated.PdbxEntityNameTaxonomy::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxEntityNameInstance getPdbxEntityNameInstance() {
        return (org.rcsb.cif.api.generated.PdbxEntityNameInstance) categories.computeIfAbsent("pdbx_entity_name_instance",
                org.rcsb.cif.api.generated.PdbxEntityNameInstance::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxTableinfo getPdbxTableinfo() {
        return (org.rcsb.cif.api.generated.PdbxTableinfo) categories.computeIfAbsent("pdbx_tableinfo",
                org.rcsb.cif.api.generated.PdbxTableinfo::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxColumninfo getPdbxColumninfo() {
        return (org.rcsb.cif.api.generated.PdbxColumninfo) categories.computeIfAbsent("pdbx_columninfo",
                org.rcsb.cif.api.generated.PdbxColumninfo::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxValAngle getPdbxValAngle() {
        return (org.rcsb.cif.api.generated.PdbxValAngle) categories.computeIfAbsent("pdbx_val_angle",
                org.rcsb.cif.api.generated.PdbxValAngle::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxValBond getPdbxValBond() {
        return (org.rcsb.cif.api.generated.PdbxValBond) categories.computeIfAbsent("pdbx_val_bond",
                org.rcsb.cif.api.generated.PdbxValBond::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxValContact getPdbxValContact() {
        return (org.rcsb.cif.api.generated.PdbxValContact) categories.computeIfAbsent("pdbx_val_contact",
                org.rcsb.cif.api.generated.PdbxValContact::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxValSymContact getPdbxValSymContact() {
        return (org.rcsb.cif.api.generated.PdbxValSymContact) categories.computeIfAbsent("pdbx_val_sym_contact",
                org.rcsb.cif.api.generated.PdbxValSymContact::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxRmchOutlier getPdbxRmchOutlier() {
        return (org.rcsb.cif.api.generated.PdbxRmchOutlier) categories.computeIfAbsent("pdbx_rmch_outlier",
                org.rcsb.cif.api.generated.PdbxRmchOutlier::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxMissingAtomPoly getPdbxMissingAtomPoly() {
        return (org.rcsb.cif.api.generated.PdbxMissingAtomPoly) categories.computeIfAbsent("pdbx_missing_atom_poly",
                org.rcsb.cif.api.generated.PdbxMissingAtomPoly::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxMissingAtomNonpoly getPdbxMissingAtomNonpoly() {
        return (org.rcsb.cif.api.generated.PdbxMissingAtomNonpoly) categories.computeIfAbsent("pdbx_missing_atom_nonpoly",
                org.rcsb.cif.api.generated.PdbxMissingAtomNonpoly::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxValChiral getPdbxValChiral() {
        return (org.rcsb.cif.api.generated.PdbxValChiral) categories.computeIfAbsent("pdbx_val_chiral",
                org.rcsb.cif.api.generated.PdbxValChiral::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxAtlas getPdbxAtlas() {
        return (org.rcsb.cif.api.generated.PdbxAtlas) categories.computeIfAbsent("pdbx_atlas",
                org.rcsb.cif.api.generated.PdbxAtlas::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxSummaryFlags getPdbxSummaryFlags() {
        return (org.rcsb.cif.api.generated.PdbxSummaryFlags) categories.computeIfAbsent("pdbx_summary_flags",
                org.rcsb.cif.api.generated.PdbxSummaryFlags::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxEntityFuncBindMode getPdbxEntityFuncBindMode() {
        return (org.rcsb.cif.api.generated.PdbxEntityFuncBindMode) categories.computeIfAbsent("pdbx_entity_func_bind_mode",
                org.rcsb.cif.api.generated.PdbxEntityFuncBindMode::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxEntityFuncEnzyme getPdbxEntityFuncEnzyme() {
        return (org.rcsb.cif.api.generated.PdbxEntityFuncEnzyme) categories.computeIfAbsent("pdbx_entity_func_enzyme",
                org.rcsb.cif.api.generated.PdbxEntityFuncEnzyme::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxEntityFuncRegulatory getPdbxEntityFuncRegulatory() {
        return (org.rcsb.cif.api.generated.PdbxEntityFuncRegulatory) categories.computeIfAbsent("pdbx_entity_func_regulatory",
                org.rcsb.cif.api.generated.PdbxEntityFuncRegulatory::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxEntityFuncStructural getPdbxEntityFuncStructural() {
        return (org.rcsb.cif.api.generated.PdbxEntityFuncStructural) categories.computeIfAbsent("pdbx_entity_func_structural",
                org.rcsb.cif.api.generated.PdbxEntityFuncStructural::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxEntityFuncOther getPdbxEntityFuncOther() {
        return (org.rcsb.cif.api.generated.PdbxEntityFuncOther) categories.computeIfAbsent("pdbx_entity_func_other",
                org.rcsb.cif.api.generated.PdbxEntityFuncOther::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxEntityPolyDomain getPdbxEntityPolyDomain() {
        return (org.rcsb.cif.api.generated.PdbxEntityPolyDomain) categories.computeIfAbsent("pdbx_entity_poly_domain",
                org.rcsb.cif.api.generated.PdbxEntityPolyDomain::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxNaStructKeywds getPdbxNaStructKeywds() {
        return (org.rcsb.cif.api.generated.PdbxNaStructKeywds) categories.computeIfAbsent("pdbx_na_struct_keywds",
                org.rcsb.cif.api.generated.PdbxNaStructKeywds::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxEntityPolyNaType getPdbxEntityPolyNaType() {
        return (org.rcsb.cif.api.generated.PdbxEntityPolyNaType) categories.computeIfAbsent("pdbx_entity_poly_na_type",
                org.rcsb.cif.api.generated.PdbxEntityPolyNaType::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxEntityPolyNaNonstandard getPdbxEntityPolyNaNonstandard() {
        return (org.rcsb.cif.api.generated.PdbxEntityPolyNaNonstandard) categories.computeIfAbsent("pdbx_entity_poly_na_nonstandard",
                org.rcsb.cif.api.generated.PdbxEntityPolyNaNonstandard::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxVirtualAngle getPdbxVirtualAngle() {
        return (org.rcsb.cif.api.generated.PdbxVirtualAngle) categories.computeIfAbsent("pdbx_virtual_angle",
                org.rcsb.cif.api.generated.PdbxVirtualAngle::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxVirtualBond getPdbxVirtualBond() {
        return (org.rcsb.cif.api.generated.PdbxVirtualBond) categories.computeIfAbsent("pdbx_virtual_bond",
                org.rcsb.cif.api.generated.PdbxVirtualBond::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxVirtualTorsion getPdbxVirtualTorsion() {
        return (org.rcsb.cif.api.generated.PdbxVirtualTorsion) categories.computeIfAbsent("pdbx_virtual_torsion",
                org.rcsb.cif.api.generated.PdbxVirtualTorsion::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxSequencePattern getPdbxSequencePattern() {
        return (org.rcsb.cif.api.generated.PdbxSequencePattern) categories.computeIfAbsent("pdbx_sequence_pattern",
                org.rcsb.cif.api.generated.PdbxSequencePattern::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxStereochemistry getPdbxStereochemistry() {
        return (org.rcsb.cif.api.generated.PdbxStereochemistry) categories.computeIfAbsent("pdbx_stereochemistry",
                org.rcsb.cif.api.generated.PdbxStereochemistry::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxRmsDevsCovalent getPdbxRmsDevsCovalent() {
        return (org.rcsb.cif.api.generated.PdbxRmsDevsCovalent) categories.computeIfAbsent("pdbx_rms_devs_covalent",
                org.rcsb.cif.api.generated.PdbxRmsDevsCovalent::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxRmsDevsCovByMonomer getPdbxRmsDevsCovByMonomer() {
        return (org.rcsb.cif.api.generated.PdbxRmsDevsCovByMonomer) categories.computeIfAbsent("pdbx_rms_devs_cov_by_monomer",
                org.rcsb.cif.api.generated.PdbxRmsDevsCovByMonomer::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxSugarPhosphateGeometry getPdbxSugarPhosphateGeometry() {
        return (org.rcsb.cif.api.generated.PdbxSugarPhosphateGeometry) categories.computeIfAbsent("pdbx_sugar_phosphate_geometry",
                org.rcsb.cif.api.generated.PdbxSugarPhosphateGeometry::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxNmrComputing getPdbxNmrComputing() {
        return (org.rcsb.cif.api.generated.PdbxNmrComputing) categories.computeIfAbsent("pdbx_nmr_computing",
                org.rcsb.cif.api.generated.PdbxNmrComputing::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxAuditConformExtension getPdbxAuditConformExtension() {
        return (org.rcsb.cif.api.generated.PdbxAuditConformExtension) categories.computeIfAbsent("pdbx_audit_conform_extension",
                org.rcsb.cif.api.generated.PdbxAuditConformExtension::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxDccMapman getPdbxDccMapman() {
        return (org.rcsb.cif.api.generated.PdbxDccMapman) categories.computeIfAbsent("pdbx_dcc_mapman",
                org.rcsb.cif.api.generated.PdbxDccMapman::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxDccRsccMapman getPdbxDccRsccMapman() {
        return (org.rcsb.cif.api.generated.PdbxDccRsccMapman) categories.computeIfAbsent("pdbx_dcc_rscc_mapman",
                org.rcsb.cif.api.generated.PdbxDccRsccMapman::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxDccRsccMapmanOverall getPdbxDccRsccMapmanOverall() {
        return (org.rcsb.cif.api.generated.PdbxDccRsccMapmanOverall) categories.computeIfAbsent("pdbx_dcc_rscc_mapman_overall",
                org.rcsb.cif.api.generated.PdbxDccRsccMapmanOverall::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxDccDensity getPdbxDccDensity() {
        return (org.rcsb.cif.api.generated.PdbxDccDensity) categories.computeIfAbsent("pdbx_dcc_density",
                org.rcsb.cif.api.generated.PdbxDccDensity::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxDccGeometry getPdbxDccGeometry() {
        return (org.rcsb.cif.api.generated.PdbxDccGeometry) categories.computeIfAbsent("pdbx_dcc_geometry",
                org.rcsb.cif.api.generated.PdbxDccGeometry::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxDccDensityCorr getPdbxDccDensityCorr() {
        return (org.rcsb.cif.api.generated.PdbxDccDensityCorr) categories.computeIfAbsent("pdbx_dcc_density_corr",
                org.rcsb.cif.api.generated.PdbxDccDensityCorr::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxDccMap getPdbxDccMap() {
        return (org.rcsb.cif.api.generated.PdbxDccMap) categories.computeIfAbsent("pdbx_dcc_map",
                org.rcsb.cif.api.generated.PdbxDccMap::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxDepositGroup getPdbxDepositGroup() {
        return (org.rcsb.cif.api.generated.PdbxDepositGroup) categories.computeIfAbsent("pdbx_deposit_group",
                org.rcsb.cif.api.generated.PdbxDepositGroup::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxDepositGroupIndex getPdbxDepositGroupIndex() {
        return (org.rcsb.cif.api.generated.PdbxDepositGroupIndex) categories.computeIfAbsent("pdbx_deposit_group_index",
                org.rcsb.cif.api.generated.PdbxDepositGroupIndex::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxStructAssemblyAuthEvidence getPdbxStructAssemblyAuthEvidence() {
        return (org.rcsb.cif.api.generated.PdbxStructAssemblyAuthEvidence) categories.computeIfAbsent("pdbx_struct_assembly_auth_evidence",
                org.rcsb.cif.api.generated.PdbxStructAssemblyAuthEvidence::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxStructAssemblyAuthClassification getPdbxStructAssemblyAuthClassification() {
        return (org.rcsb.cif.api.generated.PdbxStructAssemblyAuthClassification) categories.computeIfAbsent("pdbx_struct_assembly_auth_classification",
                org.rcsb.cif.api.generated.PdbxStructAssemblyAuthClassification::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxCrystalAlignment getPdbxCrystalAlignment() {
        return (org.rcsb.cif.api.generated.PdbxCrystalAlignment) categories.computeIfAbsent("pdbx_crystal_alignment",
                org.rcsb.cif.api.generated.PdbxCrystalAlignment::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxAuditRevisionHistory getPdbxAuditRevisionHistory() {
        return (org.rcsb.cif.api.generated.PdbxAuditRevisionHistory) categories.computeIfAbsent("pdbx_audit_revision_history",
                org.rcsb.cif.api.generated.PdbxAuditRevisionHistory::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxAuditRevisionGroup getPdbxAuditRevisionGroup() {
        return (org.rcsb.cif.api.generated.PdbxAuditRevisionGroup) categories.computeIfAbsent("pdbx_audit_revision_group",
                org.rcsb.cif.api.generated.PdbxAuditRevisionGroup::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxAuditRevisionCategory getPdbxAuditRevisionCategory() {
        return (org.rcsb.cif.api.generated.PdbxAuditRevisionCategory) categories.computeIfAbsent("pdbx_audit_revision_category",
                org.rcsb.cif.api.generated.PdbxAuditRevisionCategory::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxAuditRevisionDetails getPdbxAuditRevisionDetails() {
        return (org.rcsb.cif.api.generated.PdbxAuditRevisionDetails) categories.computeIfAbsent("pdbx_audit_revision_details",
                org.rcsb.cif.api.generated.PdbxAuditRevisionDetails::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxAuditRevisionItem getPdbxAuditRevisionItem() {
        return (org.rcsb.cif.api.generated.PdbxAuditRevisionItem) categories.computeIfAbsent("pdbx_audit_revision_item",
                org.rcsb.cif.api.generated.PdbxAuditRevisionItem::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxSupportingExpDataSet getPdbxSupportingExpDataSet() {
        return (org.rcsb.cif.api.generated.PdbxSupportingExpDataSet) categories.computeIfAbsent("pdbx_supporting_exp_data_set",
                org.rcsb.cif.api.generated.PdbxSupportingExpDataSet::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxSerialCrystallographyMeasurement getPdbxSerialCrystallographyMeasurement() {
        return (org.rcsb.cif.api.generated.PdbxSerialCrystallographyMeasurement) categories.computeIfAbsent("pdbx_serial_crystallography_measurement",
                org.rcsb.cif.api.generated.PdbxSerialCrystallographyMeasurement::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxSerialCrystallographySampleDelivery getPdbxSerialCrystallographySampleDelivery() {
        return (org.rcsb.cif.api.generated.PdbxSerialCrystallographySampleDelivery) categories.computeIfAbsent("pdbx_serial_crystallography_sample_delivery",
                org.rcsb.cif.api.generated.PdbxSerialCrystallographySampleDelivery::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxSerialCrystallographySampleDeliveryInjection getPdbxSerialCrystallographySampleDeliveryInjection() {
        return (org.rcsb.cif.api.generated.PdbxSerialCrystallographySampleDeliveryInjection) categories.computeIfAbsent("pdbx_serial_crystallography_sample_delivery_injection",
                org.rcsb.cif.api.generated.PdbxSerialCrystallographySampleDeliveryInjection::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxSerialCrystallographySampleDeliveryFixedTarget getPdbxSerialCrystallographySampleDeliveryFixedTarget() {
        return (org.rcsb.cif.api.generated.PdbxSerialCrystallographySampleDeliveryFixedTarget) categories.computeIfAbsent("pdbx_serial_crystallography_sample_delivery_fixed_target",
                org.rcsb.cif.api.generated.PdbxSerialCrystallographySampleDeliveryFixedTarget::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxSerialCrystallographyDataReduction getPdbxSerialCrystallographyDataReduction() {
        return (org.rcsb.cif.api.generated.PdbxSerialCrystallographyDataReduction) categories.computeIfAbsent("pdbx_serial_crystallography_data_reduction",
                org.rcsb.cif.api.generated.PdbxSerialCrystallographyDataReduction::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxChemCompSynonyms getPdbxChemCompSynonyms() {
        return (org.rcsb.cif.api.generated.PdbxChemCompSynonyms) categories.computeIfAbsent("pdbx_chem_comp_synonyms",
                org.rcsb.cif.api.generated.PdbxChemCompSynonyms::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxChemCompRelated getPdbxChemCompRelated() {
        return (org.rcsb.cif.api.generated.PdbxChemCompRelated) categories.computeIfAbsent("pdbx_chem_comp_related",
                org.rcsb.cif.api.generated.PdbxChemCompRelated::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxChemCompAtomRelated getPdbxChemCompAtomRelated() {
        return (org.rcsb.cif.api.generated.PdbxChemCompAtomRelated) categories.computeIfAbsent("pdbx_chem_comp_atom_related",
                org.rcsb.cif.api.generated.PdbxChemCompAtomRelated::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxEntityBranchList getPdbxEntityBranchList() {
        return (org.rcsb.cif.api.generated.PdbxEntityBranchList) categories.computeIfAbsent("pdbx_entity_branch_list",
                org.rcsb.cif.api.generated.PdbxEntityBranchList::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxEntityBranchLink getPdbxEntityBranchLink() {
        return (org.rcsb.cif.api.generated.PdbxEntityBranchLink) categories.computeIfAbsent("pdbx_entity_branch_link",
                org.rcsb.cif.api.generated.PdbxEntityBranchLink::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxEntityBranch getPdbxEntityBranch() {
        return (org.rcsb.cif.api.generated.PdbxEntityBranch) categories.computeIfAbsent("pdbx_entity_branch",
                org.rcsb.cif.api.generated.PdbxEntityBranch::new).get();
    }

    public org.rcsb.cif.api.generated.PdbxBranchScheme getPdbxBranchScheme() {
        return (org.rcsb.cif.api.generated.PdbxBranchScheme) categories.computeIfAbsent("pdbx_branch_scheme",
                org.rcsb.cif.api.generated.PdbxBranchScheme::new).get();
    }

    public org.rcsb.cif.api.generated.IhmStartingModelDetails getIhmStartingModelDetails() {
        return (org.rcsb.cif.api.generated.IhmStartingModelDetails) categories.computeIfAbsent("ihm_starting_model_details",
                org.rcsb.cif.api.generated.IhmStartingModelDetails::new).get();
    }

    public org.rcsb.cif.api.generated.IhmStartingComparativeModels getIhmStartingComparativeModels() {
        return (org.rcsb.cif.api.generated.IhmStartingComparativeModels) categories.computeIfAbsent("ihm_starting_comparative_models",
                org.rcsb.cif.api.generated.IhmStartingComparativeModels::new).get();
    }

    public org.rcsb.cif.api.generated.IhmStartingComputationalModels getIhmStartingComputationalModels() {
        return (org.rcsb.cif.api.generated.IhmStartingComputationalModels) categories.computeIfAbsent("ihm_starting_computational_models",
                org.rcsb.cif.api.generated.IhmStartingComputationalModels::new).get();
    }

    public org.rcsb.cif.api.generated.IhmStartingModelSeqDif getIhmStartingModelSeqDif() {
        return (org.rcsb.cif.api.generated.IhmStartingModelSeqDif) categories.computeIfAbsent("ihm_starting_model_seq_dif",
                org.rcsb.cif.api.generated.IhmStartingModelSeqDif::new).get();
    }

    public org.rcsb.cif.api.generated.IhmModelRepresentation getIhmModelRepresentation() {
        return (org.rcsb.cif.api.generated.IhmModelRepresentation) categories.computeIfAbsent("ihm_model_representation",
                org.rcsb.cif.api.generated.IhmModelRepresentation::new).get();
    }

    public org.rcsb.cif.api.generated.IhmStructAssembly getIhmStructAssembly() {
        return (org.rcsb.cif.api.generated.IhmStructAssembly) categories.computeIfAbsent("ihm_struct_assembly",
                org.rcsb.cif.api.generated.IhmStructAssembly::new).get();
    }

    public org.rcsb.cif.api.generated.IhmStructAssemblyDetails getIhmStructAssemblyDetails() {
        return (org.rcsb.cif.api.generated.IhmStructAssemblyDetails) categories.computeIfAbsent("ihm_struct_assembly_details",
                org.rcsb.cif.api.generated.IhmStructAssemblyDetails::new).get();
    }

    public org.rcsb.cif.api.generated.IhmStructAssemblyClassList getIhmStructAssemblyClassList() {
        return (org.rcsb.cif.api.generated.IhmStructAssemblyClassList) categories.computeIfAbsent("ihm_struct_assembly_class_list",
                org.rcsb.cif.api.generated.IhmStructAssemblyClassList::new).get();
    }

    public org.rcsb.cif.api.generated.IhmStructAssemblyClass getIhmStructAssemblyClass() {
        return (org.rcsb.cif.api.generated.IhmStructAssemblyClass) categories.computeIfAbsent("ihm_struct_assembly_class",
                org.rcsb.cif.api.generated.IhmStructAssemblyClass::new).get();
    }

    public org.rcsb.cif.api.generated.IhmModelingProtocol getIhmModelingProtocol() {
        return (org.rcsb.cif.api.generated.IhmModelingProtocol) categories.computeIfAbsent("ihm_modeling_protocol",
                org.rcsb.cif.api.generated.IhmModelingProtocol::new).get();
    }

    public org.rcsb.cif.api.generated.IhmMultiStateModeling getIhmMultiStateModeling() {
        return (org.rcsb.cif.api.generated.IhmMultiStateModeling) categories.computeIfAbsent("ihm_multi_state_modeling",
                org.rcsb.cif.api.generated.IhmMultiStateModeling::new).get();
    }

    public org.rcsb.cif.api.generated.IhmOrderedEnsemble getIhmOrderedEnsemble() {
        return (org.rcsb.cif.api.generated.IhmOrderedEnsemble) categories.computeIfAbsent("ihm_ordered_ensemble",
                org.rcsb.cif.api.generated.IhmOrderedEnsemble::new).get();
    }

    public org.rcsb.cif.api.generated.IhmModelingPostProcess getIhmModelingPostProcess() {
        return (org.rcsb.cif.api.generated.IhmModelingPostProcess) categories.computeIfAbsent("ihm_modeling_post_process",
                org.rcsb.cif.api.generated.IhmModelingPostProcess::new).get();
    }

    public org.rcsb.cif.api.generated.IhmEnsembleInfo getIhmEnsembleInfo() {
        return (org.rcsb.cif.api.generated.IhmEnsembleInfo) categories.computeIfAbsent("ihm_ensemble_info",
                org.rcsb.cif.api.generated.IhmEnsembleInfo::new).get();
    }

    public org.rcsb.cif.api.generated.IhmModelList getIhmModelList() {
        return (org.rcsb.cif.api.generated.IhmModelList) categories.computeIfAbsent("ihm_model_list",
                org.rcsb.cif.api.generated.IhmModelList::new).get();
    }

    public org.rcsb.cif.api.generated.IhmModelRepresentative getIhmModelRepresentative() {
        return (org.rcsb.cif.api.generated.IhmModelRepresentative) categories.computeIfAbsent("ihm_model_representative",
                org.rcsb.cif.api.generated.IhmModelRepresentative::new).get();
    }

    public org.rcsb.cif.api.generated.IhmDatasetList getIhmDatasetList() {
        return (org.rcsb.cif.api.generated.IhmDatasetList) categories.computeIfAbsent("ihm_dataset_list",
                org.rcsb.cif.api.generated.IhmDatasetList::new).get();
    }

    public org.rcsb.cif.api.generated.IhmDatasetGroup getIhmDatasetGroup() {
        return (org.rcsb.cif.api.generated.IhmDatasetGroup) categories.computeIfAbsent("ihm_dataset_group",
                org.rcsb.cif.api.generated.IhmDatasetGroup::new).get();
    }

    public org.rcsb.cif.api.generated.IhmRelatedDatasets getIhmRelatedDatasets() {
        return (org.rcsb.cif.api.generated.IhmRelatedDatasets) categories.computeIfAbsent("ihm_related_datasets",
                org.rcsb.cif.api.generated.IhmRelatedDatasets::new).get();
    }

    public org.rcsb.cif.api.generated.IhmDatasetRelatedDbReference getIhmDatasetRelatedDbReference() {
        return (org.rcsb.cif.api.generated.IhmDatasetRelatedDbReference) categories.computeIfAbsent("ihm_dataset_related_db_reference",
                org.rcsb.cif.api.generated.IhmDatasetRelatedDbReference::new).get();
    }

    public org.rcsb.cif.api.generated.IhmExternalReferenceInfo getIhmExternalReferenceInfo() {
        return (org.rcsb.cif.api.generated.IhmExternalReferenceInfo) categories.computeIfAbsent("ihm_external_reference_info",
                org.rcsb.cif.api.generated.IhmExternalReferenceInfo::new).get();
    }

    public org.rcsb.cif.api.generated.IhmExternalFiles getIhmExternalFiles() {
        return (org.rcsb.cif.api.generated.IhmExternalFiles) categories.computeIfAbsent("ihm_external_files",
                org.rcsb.cif.api.generated.IhmExternalFiles::new).get();
    }

    public org.rcsb.cif.api.generated.IhmDatasetExternalReference getIhmDatasetExternalReference() {
        return (org.rcsb.cif.api.generated.IhmDatasetExternalReference) categories.computeIfAbsent("ihm_dataset_external_reference",
                org.rcsb.cif.api.generated.IhmDatasetExternalReference::new).get();
    }

    public org.rcsb.cif.api.generated.IhmLocalizationDensityFiles getIhmLocalizationDensityFiles() {
        return (org.rcsb.cif.api.generated.IhmLocalizationDensityFiles) categories.computeIfAbsent("ihm_localization_density_files",
                org.rcsb.cif.api.generated.IhmLocalizationDensityFiles::new).get();
    }

    public org.rcsb.cif.api.generated.IhmPredictedContactRestraint getIhmPredictedContactRestraint() {
        return (org.rcsb.cif.api.generated.IhmPredictedContactRestraint) categories.computeIfAbsent("ihm_predicted_contact_restraint",
                org.rcsb.cif.api.generated.IhmPredictedContactRestraint::new).get();
    }

    public org.rcsb.cif.api.generated.IhmHydroxylRadicalFpRestraint getIhmHydroxylRadicalFpRestraint() {
        return (org.rcsb.cif.api.generated.IhmHydroxylRadicalFpRestraint) categories.computeIfAbsent("ihm_hydroxyl_radical_fp_restraint",
                org.rcsb.cif.api.generated.IhmHydroxylRadicalFpRestraint::new).get();
    }

    public org.rcsb.cif.api.generated.IhmCrossLinkList getIhmCrossLinkList() {
        return (org.rcsb.cif.api.generated.IhmCrossLinkList) categories.computeIfAbsent("ihm_cross_link_list",
                org.rcsb.cif.api.generated.IhmCrossLinkList::new).get();
    }

    public org.rcsb.cif.api.generated.IhmCrossLinkRestraint getIhmCrossLinkRestraint() {
        return (org.rcsb.cif.api.generated.IhmCrossLinkRestraint) categories.computeIfAbsent("ihm_cross_link_restraint",
                org.rcsb.cif.api.generated.IhmCrossLinkRestraint::new).get();
    }

    public org.rcsb.cif.api.generated.IhmCrossLinkResult getIhmCrossLinkResult() {
        return (org.rcsb.cif.api.generated.IhmCrossLinkResult) categories.computeIfAbsent("ihm_cross_link_result",
                org.rcsb.cif.api.generated.IhmCrossLinkResult::new).get();
    }

    public org.rcsb.cif.api.generated.IhmCrossLinkResultParameters getIhmCrossLinkResultParameters() {
        return (org.rcsb.cif.api.generated.IhmCrossLinkResultParameters) categories.computeIfAbsent("ihm_cross_link_result_parameters",
                org.rcsb.cif.api.generated.IhmCrossLinkResultParameters::new).get();
    }

    public org.rcsb.cif.api.generated.Ihm2demClassAverageRestraint getIhm2demClassAverageRestraint() {
        return (org.rcsb.cif.api.generated.Ihm2demClassAverageRestraint) categories.computeIfAbsent("ihm_2dem_class_average_restraint",
                org.rcsb.cif.api.generated.Ihm2demClassAverageRestraint::new).get();
    }

    public org.rcsb.cif.api.generated.Ihm2demClassAverageFitting getIhm2demClassAverageFitting() {
        return (org.rcsb.cif.api.generated.Ihm2demClassAverageFitting) categories.computeIfAbsent("ihm_2dem_class_average_fitting",
                org.rcsb.cif.api.generated.Ihm2demClassAverageFitting::new).get();
    }

    public org.rcsb.cif.api.generated.Ihm3demRestraint getIhm3demRestraint() {
        return (org.rcsb.cif.api.generated.Ihm3demRestraint) categories.computeIfAbsent("ihm_3dem_restraint",
                org.rcsb.cif.api.generated.Ihm3demRestraint::new).get();
    }

    public org.rcsb.cif.api.generated.IhmSasRestraint getIhmSasRestraint() {
        return (org.rcsb.cif.api.generated.IhmSasRestraint) categories.computeIfAbsent("ihm_sas_restraint",
                org.rcsb.cif.api.generated.IhmSasRestraint::new).get();
    }

    public org.rcsb.cif.api.generated.IhmStartingModelCoord getIhmStartingModelCoord() {
        return (org.rcsb.cif.api.generated.IhmStartingModelCoord) categories.computeIfAbsent("ihm_starting_model_coord",
                org.rcsb.cif.api.generated.IhmStartingModelCoord::new).get();
    }

    public org.rcsb.cif.api.generated.IhmSphereObjSite getIhmSphereObjSite() {
        return (org.rcsb.cif.api.generated.IhmSphereObjSite) categories.computeIfAbsent("ihm_sphere_obj_site",
                org.rcsb.cif.api.generated.IhmSphereObjSite::new).get();
    }

    public org.rcsb.cif.api.generated.IhmGaussianObjSite getIhmGaussianObjSite() {
        return (org.rcsb.cif.api.generated.IhmGaussianObjSite) categories.computeIfAbsent("ihm_gaussian_obj_site",
                org.rcsb.cif.api.generated.IhmGaussianObjSite::new).get();
    }

    public org.rcsb.cif.api.generated.IhmGaussianObjEnsemble getIhmGaussianObjEnsemble() {
        return (org.rcsb.cif.api.generated.IhmGaussianObjEnsemble) categories.computeIfAbsent("ihm_gaussian_obj_ensemble",
                org.rcsb.cif.api.generated.IhmGaussianObjEnsemble::new).get();
    }

    public org.rcsb.cif.api.generated.IhmResiduesNotModeled getIhmResiduesNotModeled() {
        return (org.rcsb.cif.api.generated.IhmResiduesNotModeled) categories.computeIfAbsent("ihm_residues_not_modeled",
                org.rcsb.cif.api.generated.IhmResiduesNotModeled::new).get();
    }

    public org.rcsb.cif.api.generated.IhmFeatureList getIhmFeatureList() {
        return (org.rcsb.cif.api.generated.IhmFeatureList) categories.computeIfAbsent("ihm_feature_list",
                org.rcsb.cif.api.generated.IhmFeatureList::new).get();
    }

    public org.rcsb.cif.api.generated.IhmPseudoSiteFeature getIhmPseudoSiteFeature() {
        return (org.rcsb.cif.api.generated.IhmPseudoSiteFeature) categories.computeIfAbsent("ihm_pseudo_site_feature",
                org.rcsb.cif.api.generated.IhmPseudoSiteFeature::new).get();
    }

    public org.rcsb.cif.api.generated.IhmPolyAtomFeature getIhmPolyAtomFeature() {
        return (org.rcsb.cif.api.generated.IhmPolyAtomFeature) categories.computeIfAbsent("ihm_poly_atom_feature",
                org.rcsb.cif.api.generated.IhmPolyAtomFeature::new).get();
    }

    public org.rcsb.cif.api.generated.IhmPolyResidueFeature getIhmPolyResidueFeature() {
        return (org.rcsb.cif.api.generated.IhmPolyResidueFeature) categories.computeIfAbsent("ihm_poly_residue_feature",
                org.rcsb.cif.api.generated.IhmPolyResidueFeature::new).get();
    }

    public org.rcsb.cif.api.generated.IhmNonPolyFeature getIhmNonPolyFeature() {
        return (org.rcsb.cif.api.generated.IhmNonPolyFeature) categories.computeIfAbsent("ihm_non_poly_feature",
                org.rcsb.cif.api.generated.IhmNonPolyFeature::new).get();
    }

    public org.rcsb.cif.api.generated.IhmInterfaceResidueFeature getIhmInterfaceResidueFeature() {
        return (org.rcsb.cif.api.generated.IhmInterfaceResidueFeature) categories.computeIfAbsent("ihm_interface_residue_feature",
                org.rcsb.cif.api.generated.IhmInterfaceResidueFeature::new).get();
    }

    public org.rcsb.cif.api.generated.IhmDerivedDistanceRestraint getIhmDerivedDistanceRestraint() {
        return (org.rcsb.cif.api.generated.IhmDerivedDistanceRestraint) categories.computeIfAbsent("ihm_derived_distance_restraint",
                org.rcsb.cif.api.generated.IhmDerivedDistanceRestraint::new).get();
    }

    public org.rcsb.cif.api.generated.IhmGeometricObjectList getIhmGeometricObjectList() {
        return (org.rcsb.cif.api.generated.IhmGeometricObjectList) categories.computeIfAbsent("ihm_geometric_object_list",
                org.rcsb.cif.api.generated.IhmGeometricObjectList::new).get();
    }

    public org.rcsb.cif.api.generated.IhmGeometricObjectCenter getIhmGeometricObjectCenter() {
        return (org.rcsb.cif.api.generated.IhmGeometricObjectCenter) categories.computeIfAbsent("ihm_geometric_object_center",
                org.rcsb.cif.api.generated.IhmGeometricObjectCenter::new).get();
    }

    public org.rcsb.cif.api.generated.IhmGeometricObjectTransformation getIhmGeometricObjectTransformation() {
        return (org.rcsb.cif.api.generated.IhmGeometricObjectTransformation) categories.computeIfAbsent("ihm_geometric_object_transformation",
                org.rcsb.cif.api.generated.IhmGeometricObjectTransformation::new).get();
    }

    public org.rcsb.cif.api.generated.IhmGeometricObjectSphere getIhmGeometricObjectSphere() {
        return (org.rcsb.cif.api.generated.IhmGeometricObjectSphere) categories.computeIfAbsent("ihm_geometric_object_sphere",
                org.rcsb.cif.api.generated.IhmGeometricObjectSphere::new).get();
    }

    public org.rcsb.cif.api.generated.IhmGeometricObjectTorus getIhmGeometricObjectTorus() {
        return (org.rcsb.cif.api.generated.IhmGeometricObjectTorus) categories.computeIfAbsent("ihm_geometric_object_torus",
                org.rcsb.cif.api.generated.IhmGeometricObjectTorus::new).get();
    }

    public org.rcsb.cif.api.generated.IhmGeometricObjectHalfTorus getIhmGeometricObjectHalfTorus() {
        return (org.rcsb.cif.api.generated.IhmGeometricObjectHalfTorus) categories.computeIfAbsent("ihm_geometric_object_half_torus",
                org.rcsb.cif.api.generated.IhmGeometricObjectHalfTorus::new).get();
    }

    public org.rcsb.cif.api.generated.IhmGeometricObjectAxis getIhmGeometricObjectAxis() {
        return (org.rcsb.cif.api.generated.IhmGeometricObjectAxis) categories.computeIfAbsent("ihm_geometric_object_axis",
                org.rcsb.cif.api.generated.IhmGeometricObjectAxis::new).get();
    }

    public org.rcsb.cif.api.generated.IhmGeometricObjectPlane getIhmGeometricObjectPlane() {
        return (org.rcsb.cif.api.generated.IhmGeometricObjectPlane) categories.computeIfAbsent("ihm_geometric_object_plane",
                org.rcsb.cif.api.generated.IhmGeometricObjectPlane::new).get();
    }

    public org.rcsb.cif.api.generated.IhmGeometricObjectDistanceRestraint getIhmGeometricObjectDistanceRestraint() {
        return (org.rcsb.cif.api.generated.IhmGeometricObjectDistanceRestraint) categories.computeIfAbsent("ihm_geometric_object_distance_restraint",
                org.rcsb.cif.api.generated.IhmGeometricObjectDistanceRestraint::new).get();
    }
}

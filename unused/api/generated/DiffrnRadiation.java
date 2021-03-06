package org.rcsb.cif.api.generated;

import org.rcsb.cif.model.*;

import javax.annotation.Generated;
import java.util.Map;

/**
 * Data items in the DIFFRN_RADIATION category describe
 * the radiation used in measuring the diffraction intensities,
 * its collimation and monochromatization before the sample.
 * 
 * Post-sample treatment of the beam is described by data
 * items in the DIFFRN_DETECTOR category.
 */
@Generated("org.rcsb.cif.generator.SchemaGenerator")
public class DiffrnRadiation extends BaseCategory {
    public DiffrnRadiation(String name, Map<String, Column> columns) {
        super(name, columns);
    }

    public DiffrnRadiation(String name, int rowCount, Object[] encodedColumns) {
        super(name, rowCount, encodedColumns);
    }

    public DiffrnRadiation(String name) {
        super(name);
    }

    /**
     * The collimation or focusing applied to the radiation.
     * @return StrColumn
     */
    public StrColumn getCollimation() {
        return (StrColumn) (isText ? textFields.computeIfAbsent("collimation", StrColumn::new) :
                getBinaryColumn("collimation"));
    }

    /**
     * This data item is a pointer to _diffrn.id in the DIFFRN
     * category.
     * @return StrColumn
     */
    public StrColumn getDiffrnId() {
        return (StrColumn) (isText ? textFields.computeIfAbsent("diffrn_id", StrColumn::new) :
                getBinaryColumn("diffrn_id"));
    }

    /**
     * Absorption edge in angstroms of the radiation filter used.
     * @return FloatColumn
     */
    public FloatColumn getFilterEdge() {
        return (FloatColumn) (isText ? textFields.computeIfAbsent("filter_edge", FloatColumn::new) :
                getBinaryColumn("filter_edge"));
    }

    /**
     * Half-width in millimetres of the incident beam in the
     * direction perpendicular to the diffraction plane.
     * @return FloatColumn
     */
    public FloatColumn getInhomogeneity() {
        return (FloatColumn) (isText ? textFields.computeIfAbsent("inhomogeneity", FloatColumn::new) :
                getBinaryColumn("inhomogeneity"));
    }

    /**
     * The method used to obtain monochromatic radiation. If a mono-
     * chromator crystal is used, the material and the indices of the
     * Bragg reflection are specified.
     * @return StrColumn
     */
    public StrColumn getMonochromator() {
        return (StrColumn) (isText ? textFields.computeIfAbsent("monochromator", StrColumn::new) :
                getBinaryColumn("monochromator"));
    }

    /**
     * The angle in degrees, as viewed from the specimen, between the
     * perpendicular component of the polarization and the diffraction
     * plane. See _diffrn_radiation.polarisn_ratio.
     * @return FloatColumn
     */
    public FloatColumn getPolarisnNorm() {
        return (FloatColumn) (isText ? textFields.computeIfAbsent("polarisn_norm", FloatColumn::new) :
                getBinaryColumn("polarisn_norm"));
    }

    /**
     * Polarization ratio of the diffraction beam incident on the
     * crystal. This is the ratio of the perpendicularly polarized
     * to the parallel-polarized component of the radiation. The
     * perpendicular component forms an angle of
     * _diffrn_radiation.polarisn_norm to the normal to the
     * diffraction plane of the sample (i.e. the plane containing
     * the incident and reflected beams).
     * @return FloatColumn
     */
    public FloatColumn getPolarisnRatio() {
        return (FloatColumn) (isText ? textFields.computeIfAbsent("polarisn_ratio", FloatColumn::new) :
                getBinaryColumn("polarisn_ratio"));
    }

    /**
     * The nature of the radiation used (i.e. the name of the
     * subatomic particle or the region of the electromagnetic
     * spectrum). It is strongly recommended that this information
     * is given, so that the probe radiation can be simply determined.
     * @return StrColumn
     */
    public StrColumn getProbe() {
        return (StrColumn) (isText ? textFields.computeIfAbsent("probe", StrColumn::new) :
                getBinaryColumn("probe"));
    }

    /**
     * The nature of the radiation. This is typically a description
     * of the X-ray wavelength in Siegbahn notation.
     * @return StrColumn
     */
    public StrColumn getType() {
        return (StrColumn) (isText ? textFields.computeIfAbsent("type", StrColumn::new) :
                getBinaryColumn("type"));
    }

    /**
     * The IUPAC symbol for the X-ray wavelength for the probe
     * radiation.
     * @return StrColumn
     */
    public StrColumn getXraySymbol() {
        return (StrColumn) (isText ? textFields.computeIfAbsent("xray_symbol", StrColumn::new) :
                getBinaryColumn("xray_symbol"));
    }

    /**
     * This data item is a pointer to _diffrn_radiation_wavelength.id
     * in the DIFFRN_RADIATION_WAVELENGTH category.
     * @return StrColumn
     */
    public StrColumn getWavelengthId() {
        return (StrColumn) (isText ? textFields.computeIfAbsent("wavelength_id", StrColumn::new) :
                getBinaryColumn("wavelength_id"));
    }

    /**
     * Monochromatic or Laue.
     * @return StrColumn
     */
    public StrColumn getPdbxMonochromaticOrLaueML() {
        return (StrColumn) (isText ? textFields.computeIfAbsent("pdbx_monochromatic_or_laue_m_l", StrColumn::new) :
                getBinaryColumn("pdbx_monochromatic_or_laue_m_l"));
    }

    /**
     * Comma separated list of wavelengths or wavelength range.
     * @return StrColumn
     */
    public StrColumn getPdbxWavelengthList() {
        return (StrColumn) (isText ? textFields.computeIfAbsent("pdbx_wavelength_list", StrColumn::new) :
                getBinaryColumn("pdbx_wavelength_list"));
    }

    /**
     * Wavelength of radiation.
     * @return StrColumn
     */
    public StrColumn getPdbxWavelength() {
        return (StrColumn) (isText ? textFields.computeIfAbsent("pdbx_wavelength", StrColumn::new) :
                getBinaryColumn("pdbx_wavelength"));
    }

    /**
     * SINGLE WAVELENGTH, LAUE, or MAD.
     * @return StrColumn
     */
    public StrColumn getPdbxDiffrnProtocol() {
        return (StrColumn) (isText ? textFields.computeIfAbsent("pdbx_diffrn_protocol", StrColumn::new) :
                getBinaryColumn("pdbx_diffrn_protocol"));
    }

    /**
     * Indicates the method used to obtain monochromatic radiation.
     * _diffrn_radiation.monochromator describes the primary beam
     * monochromator (pre-specimen monochromation).
     * _diffrn_radiation.pdbx_analyzer specifies the
     * post-diffraction analyser (post-specimen) monochromation.
     * Note that monochromators may have either 'parallel' or
     * 'antiparallel' orientation. It is assumed that the
     * geometry is parallel unless specified otherwise.
     * In a parallel geometry, the position of the monochromator
     * allows the incident beam and the final post-specimen
     * and post-monochromator beam to be as close to parallel
     * as possible. In a parallel geometry, the diffracting
     * planes in the specimen and monochromator will be parallel
     * when 2*theta(monochromator) is equal to 2*theta (specimen).
     * For further discussion see R. Jenkins and R. Snyder,
     * Introduction to X-ray Powder Diffraction, Wiley (1996),
     * pp. 164-5.
     * @return StrColumn
     */
    public StrColumn getPdbxAnalyzer() {
        return (StrColumn) (isText ? textFields.computeIfAbsent("pdbx_analyzer", StrColumn::new) :
                getBinaryColumn("pdbx_analyzer"));
    }

    /**
     * The radiation scattering type for this diffraction data set.
     * @return StrColumn
     */
    public StrColumn getPdbxScatteringType() {
        return (StrColumn) (isText ? textFields.computeIfAbsent("pdbx_scattering_type", StrColumn::new) :
                getBinaryColumn("pdbx_scattering_type"));
    }
}

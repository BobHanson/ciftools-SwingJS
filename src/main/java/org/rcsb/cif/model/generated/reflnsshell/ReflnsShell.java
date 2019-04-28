package org.rcsb.cif.model.generated.reflnsshell;

import org.rcsb.cif.model.BaseCategory;
import org.rcsb.cif.model.Column;

import javax.annotation.Generated;
import java.util.Map;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class ReflnsShell extends BaseCategory {
    public ReflnsShell(String name, Map<String, Column> columns) {
        super(name, columns);
    }

    public ReflnsShell(String name, int rowCount, Object[] encodedColumns) {
        super(name, rowCount, encodedColumns);
    }

    public ReflnsShell(String name) {
        super(name);
    }

    /**
     * The smallest value in angstroms for the interplanar spacings
     * for the reflections in this shell. This is called the highest
     * resolution.
     * @return DResHigh
     */
    public DResHigh getDResHigh() {
        return (DResHigh) (isText ? textFields.computeIfAbsent("d_res_high",
                DResHigh::new) : getBinaryColumn("d_res_high"));
    }

    /**
     * The highest value in angstroms for the interplanar spacings
     * for the reflections in this shell. This is called the lowest
     * resolution.
     * @return DResLow
     */
    public DResLow getDResLow() {
        return (DResLow) (isText ? textFields.computeIfAbsent("d_res_low",
                DResLow::new) : getBinaryColumn("d_res_low"));
    }

    /**
     * The ratio of the mean of the intensities of all reflections
     * in this shell to the mean of the standard uncertainties of the
     * intensities of all reflections in this shell.
     * @return MeanIOverSigIAll
     */
    public MeanIOverSigIAll getMeanIOverSigIAll() {
        return (MeanIOverSigIAll) (isText ? textFields.computeIfAbsent("meanI_over_sigI_all",
                MeanIOverSigIAll::new) : getBinaryColumn("meanI_over_sigI_all"));
    }

    /**
     * The ratio of the mean of the intensities of the reflections
     * classified as 'observed' (see _reflns.observed_criterion) in
     * this shell to the mean of the standard uncertainties of the
     * intensities of the 'observed' reflections in this
     * shell.
     * @return MeanIOverSigIObs
     */
    public MeanIOverSigIObs getMeanIOverSigIObs() {
        return (MeanIOverSigIObs) (isText ? textFields.computeIfAbsent("meanI_over_sigI_obs",
                MeanIOverSigIObs::new) : getBinaryColumn("meanI_over_sigI_obs"));
    }

    /**
     * The total number of reflections measured for this
     * shell.
     * @return NumberMeasuredAll
     */
    public NumberMeasuredAll getNumberMeasuredAll() {
        return (NumberMeasuredAll) (isText ? textFields.computeIfAbsent("number_measured_all",
                NumberMeasuredAll::new) : getBinaryColumn("number_measured_all"));
    }

    /**
     * The number of reflections classified as 'observed'
     * (see _reflns.observed_criterion) for this
     * shell.
     * @return NumberMeasuredObs
     */
    public NumberMeasuredObs getNumberMeasuredObs() {
        return (NumberMeasuredObs) (isText ? textFields.computeIfAbsent("number_measured_obs",
                NumberMeasuredObs::new) : getBinaryColumn("number_measured_obs"));
    }

    /**
     * The number of unique reflections it is possible to measure in
     * this shell.
     * @return NumberPossible
     */
    public NumberPossible getNumberPossible() {
        return (NumberPossible) (isText ? textFields.computeIfAbsent("number_possible",
                NumberPossible::new) : getBinaryColumn("number_possible"));
    }

    /**
     * The total number of measured reflections which are symmetry-
     * unique after merging for this shell.
     * @return NumberUniqueAll
     */
    public NumberUniqueAll getNumberUniqueAll() {
        return (NumberUniqueAll) (isText ? textFields.computeIfAbsent("number_unique_all",
                NumberUniqueAll::new) : getBinaryColumn("number_unique_all"));
    }

    /**
     * The total number of measured reflections classified as 'observed'
     * (see _reflns.observed_criterion) which are symmetry-unique
     * after merging for this shell.
     * @return NumberUniqueObs
     */
    public NumberUniqueObs getNumberUniqueObs() {
        return (NumberUniqueObs) (isText ? textFields.computeIfAbsent("number_unique_obs",
                NumberUniqueObs::new) : getBinaryColumn("number_unique_obs"));
    }

    /**
     * The percentage of geometrically possible reflections represented
     * by all reflections measured for this shell.
     * @return PercentPossibleAll
     */
    public PercentPossibleAll getPercentPossibleAll() {
        return (PercentPossibleAll) (isText ? textFields.computeIfAbsent("percent_possible_all",
                PercentPossibleAll::new) : getBinaryColumn("percent_possible_all"));
    }

    /**
     * The percentage of geometrically possible reflections represented
     * by reflections classified as 'observed' (see
     * _reflns.observed_criterion) for this shell.
     * @return PercentPossibleObs
     */
    public PercentPossibleObs getPercentPossibleObs() {
        return (PercentPossibleObs) (isText ? textFields.computeIfAbsent("percent_possible_obs",
                PercentPossibleObs::new) : getBinaryColumn("percent_possible_obs"));
    }

    /**
     * Residual factor Rmerge for all reflections that satisfy the
     * resolution limits established by _reflns_shell.d_res_high and
     * _reflns_shell.d_res_low.
     * 
     * sum~i~(sum~j~|F~j~ - <F>|)
     * Rmerge(F) = --------------------------
     * sum~i~(sum~j~<F>)
     * 
     * F~j~ = the amplitude of the jth observation of reflection i
     * <F>  = the mean of the amplitudes of all observations of
     * reflection i
     * 
     * sum~i~ is taken over all reflections
     * sum~j~ is taken over all observations of each reflection
     * @return RmergeFAll
     */
    public RmergeFAll getRmergeFAll() {
        return (RmergeFAll) (isText ? textFields.computeIfAbsent("Rmerge_F_all",
                RmergeFAll::new) : getBinaryColumn("Rmerge_F_all"));
    }

    /**
     * Residual factor Rmerge for reflections that satisfy the
     * resolution limits established by _reflns_shell.d_res_high and
     * _reflns_shell.d_res_low and the observation criterion
     * established by _reflns.observed_criterion.
     * 
     * sum~i~(sum~j~|F~j~ - <F>|)
     * Rmerge(F) = --------------------------
     * sum~i~(sum~j~<F>)
     * 
     * F~j~ = the amplitude of the jth observation of reflection i
     * <F>  = the mean of the amplitudes of all observations of
     * reflection i
     * 
     * sum~i~ is taken over all reflections
     * sum~j~ is taken over all observations of each reflection
     * @return RmergeFObs
     */
    public RmergeFObs getRmergeFObs() {
        return (RmergeFObs) (isText ? textFields.computeIfAbsent("Rmerge_F_obs",
                RmergeFObs::new) : getBinaryColumn("Rmerge_F_obs"));
    }

    /**
     * The value of Rmerge(I) for all reflections in a given shell.
     * 
     * sum~i~(sum~j~|I~j~ - <I>|)
     * Rmerge(I) = --------------------------
     * sum~i~(sum~j~<I>)
     * 
     * I~j~ = the intensity of the jth observation of reflection i
     * <I>  = the mean of the intensities of all observations of
     * reflection i
     * 
     * sum~i~ is taken over all reflections
     * sum~j~ is taken over all observations of each reflection
     * @return RmergeIAll
     */
    public RmergeIAll getRmergeIAll() {
        return (RmergeIAll) (isText ? textFields.computeIfAbsent("Rmerge_I_all",
                RmergeIAll::new) : getBinaryColumn("Rmerge_I_all"));
    }

    /**
     * The value of Rmerge(I) for reflections classified as 'observed'
     * (see _reflns.observed_criterion) in a given shell.
     * 
     * sum~i~(sum~j~|I~j~ - <I>|)
     * Rmerge(I) = --------------------------
     * sum~i~(sum~j~<I>)
     * 
     * I~j~ = the intensity of the jth observation of reflection i
     * <I>  = the mean of the intensities of all observations of
     * reflection i
     * 
     * sum~i~ is taken over all reflections
     * sum~j~ is taken over all observations of each reflection
     * @return RmergeIObs
     */
    public RmergeIObs getRmergeIObs() {
        return (RmergeIObs) (isText ? textFields.computeIfAbsent("Rmerge_I_obs",
                RmergeIObs::new) : getBinaryColumn("Rmerge_I_obs"));
    }

    /**
     * The ratio of the mean of the intensities of the significantly
     * intense reflections (see _reflns.threshold_expression) in
     * this shell to the mean of the standard uncertainties of the
     * intensities of the significantly intense reflections in this
     * shell.
     * @return MeanIOverSigIGt
     */
    public MeanIOverSigIGt getMeanIOverSigIGt() {
        return (MeanIOverSigIGt) (isText ? textFields.computeIfAbsent("meanI_over_sigI_gt",
                MeanIOverSigIGt::new) : getBinaryColumn("meanI_over_sigI_gt"));
    }

    /**
     * The ratio of the mean of the intensities of all reflections
     * in this shell to the mean of the standard uncertainties of the
     * intensities of all reflections in this shell.
     * @return MeanIOverUIAll
     */
    public MeanIOverUIAll getMeanIOverUIAll() {
        return (MeanIOverUIAll) (isText ? textFields.computeIfAbsent("meanI_over_uI_all",
                MeanIOverUIAll::new) : getBinaryColumn("meanI_over_uI_all"));
    }

    /**
     * The ratio of the mean of the intensities of the significantly
     * intense reflections (see _reflns.threshold_expression) in
     * this shell to the mean of the standard uncertainties of the
     * intensities of the significantly intense reflections in this
     * shell.
     * @return MeanIOverUIGt
     */
    public MeanIOverUIGt getMeanIOverUIGt() {
        return (MeanIOverUIGt) (isText ? textFields.computeIfAbsent("meanI_over_uI_gt",
                MeanIOverUIGt::new) : getBinaryColumn("meanI_over_uI_gt"));
    }

    /**
     * The number of significantly intense reflections
     * (see _reflns.threshold_expression) measured for this
     * shell.
     * @return NumberMeasuredGt
     */
    public NumberMeasuredGt getNumberMeasuredGt() {
        return (NumberMeasuredGt) (isText ? textFields.computeIfAbsent("number_measured_gt",
                NumberMeasuredGt::new) : getBinaryColumn("number_measured_gt"));
    }

    /**
     * The total number of significantly intense reflections
     * (see _reflns.threshold_expression) resulting from merging
     * measured symmetry-equivalent reflections for this resolution
     * shell.
     * @return NumberUniqueGt
     */
    public NumberUniqueGt getNumberUniqueGt() {
        return (NumberUniqueGt) (isText ? textFields.computeIfAbsent("number_unique_gt",
                NumberUniqueGt::new) : getBinaryColumn("number_unique_gt"));
    }

    /**
     * The percentage of geometrically possible reflections
     * represented by significantly intense reflections
     * (see _reflns.threshold_expression) measured for this
     * shell.
     * @return PercentPossibleGt
     */
    public PercentPossibleGt getPercentPossibleGt() {
        return (PercentPossibleGt) (isText ? textFields.computeIfAbsent("percent_possible_gt",
                PercentPossibleGt::new) : getBinaryColumn("percent_possible_gt"));
    }

    /**
     * The value of Rmerge(F) for significantly intense reflections
     * (see _reflns.threshold_expression) in a given shell.
     * 
     * sum~i~ ( sum~j~ | F~j~ - <F> | )
     * Rmerge(F) = --------------------------------
     * sum~i~ ( sum~j~ <F> )
     * 
     * F~j~  = the amplitude of the jth observation of reflection i
     * <F> = the mean of the amplitudes of all observations of
     * reflection i
     * 
     * sum~i~ is taken over all reflections
     * sum~j~ is taken over all observations of each reflection.
     * @return RmergeFGt
     */
    public RmergeFGt getRmergeFGt() {
        return (RmergeFGt) (isText ? textFields.computeIfAbsent("Rmerge_F_gt",
                RmergeFGt::new) : getBinaryColumn("Rmerge_F_gt"));
    }

    /**
     * The value of Rmerge(I) for significantly intense reflections
     * (see _reflns.threshold_expression) in a given shell.
     * 
     * sum~i~ ( sum~j~ | I~j~ - <I> | )
     * Rmerge(I) = --------------------------------
     * sum~i~ ( sum~j~ <I> )
     * 
     * I~j~  = the intensity of the jth observation of reflection i
     * <I> = the mean of the intensities of all observations of
     * reflection i
     * 
     * sum~i~ is taken over all reflections
     * sum~j~ is taken over all observations of each reflection.
     * @return RmergeIGt
     */
    public RmergeIGt getRmergeIGt() {
        return (RmergeIGt) (isText ? textFields.computeIfAbsent("Rmerge_I_gt",
                RmergeIGt::new) : getBinaryColumn("Rmerge_I_gt"));
    }

    /**
     * Redundancy for the current shell.
     * @return PdbxRedundancy
     */
    public PdbxRedundancy getPdbxRedundancy() {
        return (PdbxRedundancy) (isText ? textFields.computeIfAbsent("pdbx_redundancy",
                PdbxRedundancy::new) : getBinaryColumn("pdbx_redundancy"));
    }

    /**
     * R sym value in percent.
     * @return PdbxRsymValue
     */
    public PdbxRsymValue getPdbxRsymValue() {
        return (PdbxRsymValue) (isText ? textFields.computeIfAbsent("pdbx_Rsym_value",
                PdbxRsymValue::new) : getBinaryColumn("pdbx_Rsym_value"));
    }

    /**
     * Chi-squared statistic for this resolution shell.
     * @return PdbxChiSquared
     */
    public PdbxChiSquared getPdbxChiSquared() {
        return (PdbxChiSquared) (isText ? textFields.computeIfAbsent("pdbx_chi_squared",
                PdbxChiSquared::new) : getBinaryColumn("pdbx_chi_squared"));
    }

    /**
     * The mean of the ratio of the intensities to their
     * standard uncertainties of all reflections in the
     * resolution shell.
     * 
     * _reflns_shell.pdbx_netI_over_sigmaI_all =  <I/sigma(I)>
     * @return PdbxNetIOverSigmaIAll
     */
    public PdbxNetIOverSigmaIAll getPdbxNetIOverSigmaIAll() {
        return (PdbxNetIOverSigmaIAll) (isText ? textFields.computeIfAbsent("pdbx_netI_over_sigmaI_all",
                PdbxNetIOverSigmaIAll::new) : getBinaryColumn("pdbx_netI_over_sigmaI_all"));
    }

    /**
     * The mean of the ratio of the intensities to their
     * standard uncertainties of observed reflections
     * (see _reflns.observed_criterion) in the resolution shell.
     * 
     * _reflns_shell.pdbx_netI_over_sigmaI_obs =  <I/sigma(I)>
     * @return PdbxNetIOverSigmaIObs
     */
    public PdbxNetIOverSigmaIObs getPdbxNetIOverSigmaIObs() {
        return (PdbxNetIOverSigmaIObs) (isText ? textFields.computeIfAbsent("pdbx_netI_over_sigmaI_obs",
                PdbxNetIOverSigmaIObs::new) : getBinaryColumn("pdbx_netI_over_sigmaI_obs"));
    }

    /**
     * The redundancy-independent merging R factor value Rrim,
     * also denoted Rmeas, for merging all intensities in a
     * given shell.
     * 
     * sum~i~ [N~i~ /( N~i~ - 1)]1/2^ sum~j~ | I~j~ - <I~i~> |
     * Rrim = --------------------------------------------------------
     * sum~i~ ( sum~j~ I~j~ )
     * 
     * I~j~   = the intensity of the jth observation of reflection i
     * <I~i~> = the mean of the intensities of all observations of
     * reflection i
     * N~i~   = the redundancy (the number of times reflection i
     * has been measured).
     * 
     * sum~i~ is taken over all reflections
     * sum~j~ is taken over all observations of each reflection.
     * 
     * Ref: Diederichs, K. & Karplus, P. A. (1997). Nature Struct.
     * Biol. 4, 269-275.
     * Weiss, M. S. & Hilgenfeld, R. (1997). J. Appl. Cryst.
     * 30, 203-205.
     * Weiss, M. S. (2001). J. Appl. Cryst. 34, 130-135.
     * @return PdbxRrimIAll
     */
    public PdbxRrimIAll getPdbxRrimIAll() {
        return (PdbxRrimIAll) (isText ? textFields.computeIfAbsent("pdbx_Rrim_I_all",
                PdbxRrimIAll::new) : getBinaryColumn("pdbx_Rrim_I_all"));
    }

    /**
     * The precision-indicating merging R factor value Rpim,
     * for merging all intensities in a given shell.
     * 
     * sum~i~ [1/(N~i~ - 1)]1/2^ sum~j~ | I~j~ - <I~i~> |
     * Rpim = --------------------------------------------------
     * sum~i~ ( sum~j~ I~j~ )
     * 
     * I~j~   = the intensity of the jth observation of reflection i
     * <I~i~> = the mean of the intensities of all observations of
     * reflection i
     * N~i~   = the redundancy (the number of times reflection i
     * has been measured).
     * 
     * sum~i~ is taken over all reflections
     * sum~j~ is taken over all observations of each reflection.
     * 
     * Ref: Diederichs, K. & Karplus, P. A. (1997). Nature Struct.
     * Biol. 4, 269-275.
     * Weiss, M. S. & Hilgenfeld, R. (1997). J. Appl. Cryst.
     * 30, 203-205.
     * Weiss, M. S. (2001). J. Appl. Cryst. 34, 130-135.
     * @return PdbxRpimIAll
     */
    public PdbxRpimIAll getPdbxRpimIAll() {
        return (PdbxRpimIAll) (isText ? textFields.computeIfAbsent("pdbx_Rpim_I_all",
                PdbxRpimIAll::new) : getBinaryColumn("pdbx_Rpim_I_all"));
    }

    /**
     * The number of rejected reflections in the resolution
     * shell.  Reflections may be rejected from scaling
     * by setting the observation criterion,
     * _reflns.observed_criterion.
     * @return PdbxRejects
     */
    public PdbxRejects getPdbxRejects() {
        return (PdbxRejects) (isText ? textFields.computeIfAbsent("pdbx_rejects",
                PdbxRejects::new) : getBinaryColumn("pdbx_rejects"));
    }

    /**
     * An ordinal identifier for this resolution shell.
     * @return PdbxOrdinal
     */
    public PdbxOrdinal getPdbxOrdinal() {
        return (PdbxOrdinal) (isText ? textFields.computeIfAbsent("pdbx_ordinal",
                PdbxOrdinal::new) : getBinaryColumn("pdbx_ordinal"));
    }

    /**
     * An identifier for the diffraction data set corresponding to this resolution shell.
     * 
     * Multiple diffraction data sets specified as a comma separated list.
     * @return PdbxDiffrnId
     */
    public PdbxDiffrnId getPdbxDiffrnId() {
        return (PdbxDiffrnId) (isText ? textFields.computeIfAbsent("pdbx_diffrn_id",
                PdbxDiffrnId::new) : getBinaryColumn("pdbx_diffrn_id"));
    }

    /**
     * The Pearson's correlation coefficient expressed as a decimal value
     * between the average intensities from randomly selected
     * half-datasets within the resolution shell.
     * 
     * Ref: Karplus & Diederichs (2012), Science 336, 1030-33
     * @return PdbxCCHalf
     */
    public PdbxCCHalf getPdbxCCHalf() {
        return (PdbxCCHalf) (isText ? textFields.computeIfAbsent("pdbx_CC_half",
                PdbxCCHalf::new) : getBinaryColumn("pdbx_CC_half"));
    }

    /**
     * 
     * R split measures the agreement between the sets of intensities created by merging
     * odd- and even-numbered images from the data within the resolution shell.
     * 
     * Ref: T. A. White, R. A. Kirian, A. V. Martin, A. Aquila, K. Nass,
     * A. Barty and H. N. Chapman (2012), J. Appl. Cryst. 45, 335-341
     * @return PdbxRSplit
     */
    public PdbxRSplit getPdbxRSplit() {
        return (PdbxRSplit) (isText ? textFields.computeIfAbsent("pdbx_R_split",
                PdbxRSplit::new) : getBinaryColumn("pdbx_R_split"));
    }

    /**
     * For this shell, the redundancy in the observed reflections.
     * @return PdbxRedundancyReflnsObs
     */
    public PdbxRedundancyReflnsObs getPdbxRedundancyReflnsObs() {
        return (PdbxRedundancyReflnsObs) (isText ? textFields.computeIfAbsent("pdbx_redundancy_reflns_obs",
                PdbxRedundancyReflnsObs::new) : getBinaryColumn("pdbx_redundancy_reflns_obs"));
    }

    /**
     * This item is a duplicate of _reflns_shell.number_unique_all,
     * but only for the observed Friedel pairs.
     * @return PdbxNumberAnomalous
     */
    public PdbxNumberAnomalous getPdbxNumberAnomalous() {
        return (PdbxNumberAnomalous) (isText ? textFields.computeIfAbsent("pdbx_number_anomalous",
                PdbxNumberAnomalous::new) : getBinaryColumn("pdbx_number_anomalous"));
    }

    /**
     * This item is the duplicate of _reflns_shell.pdbx_Rrim_I_all, but
     * is limited to observed Friedel pairs.
     * @return PdbxRrimIAllAnomalous
     */
    public PdbxRrimIAllAnomalous getPdbxRrimIAllAnomalous() {
        return (PdbxRrimIAllAnomalous) (isText ? textFields.computeIfAbsent("pdbx_Rrim_I_all_anomalous",
                PdbxRrimIAllAnomalous::new) : getBinaryColumn("pdbx_Rrim_I_all_anomalous"));
    }

    /**
     * This item serves the same purpose as
     * _reflns_shell.pdbx_Rpim_I_all, but applies to observed Friedel pairs
     * only.
     * @return PdbxRpimIAllAnomalous
     */
    public PdbxRpimIAllAnomalous getPdbxRpimIAllAnomalous() {
        return (PdbxRpimIAllAnomalous) (isText ? textFields.computeIfAbsent("pdbx_Rpim_I_all_anomalous",
                PdbxRpimIAllAnomalous::new) : getBinaryColumn("pdbx_Rpim_I_all_anomalous"));
    }

    /**
     * This item is the same as _reflns_shell.pdbx_Rmerge_I_all, but
     * applies to observed Friedel pairs only.
     * @return PdbxRmergeIAllAnomalous
     */
    public PdbxRmergeIAllAnomalous getPdbxRmergeIAllAnomalous() {
        return (PdbxRmergeIAllAnomalous) (isText ? textFields.computeIfAbsent("pdbx_Rmerge_I_all_anomalous",
                PdbxRmergeIAllAnomalous::new) : getBinaryColumn("pdbx_Rmerge_I_all_anomalous"));
    }
}

package org.rcsb.cif.model.pdbxfeaturedomain;

import org.rcsb.cif.model.BaseCategory;
import org.rcsb.cif.model.Column;

import javax.annotation.Generated;
import java.util.Map;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class PdbxFeatureDomain extends BaseCategory {
    public PdbxFeatureDomain(String name, Map<String, Column> columns) {
        super(name, columns);
    }

    public PdbxFeatureDomain(String name, int rowCount, Object[] encodedColumns) {
        super(name, rowCount, encodedColumns);
    }

    public PdbxFeatureDomain(String name) {
        super(name);
    }

    /**
     * The value of _pdbx_feature_domain.id uniquely identifies
     * a feature in the PDBX_FEATURE_DOMAIN category.
     * @return Id
     */
    public Id getId() {
        return (Id) (isText ? textFields.computeIfAbsent("id",
                Id::new) : getBinaryColumn("id"));
    }

    /**
     * The value of _pdbx_feature_domain.id references a domain
     * definition in category PDBX_DOMAIN.
     * @return DomainId
     */
    public DomainId getDomainId() {
        return (DomainId) (isText ? textFields.computeIfAbsent("domain_id",
                DomainId::new) : getBinaryColumn("domain_id"));
    }

    /**
     * _pdbx_feature_domain.feature_name identifies a feature
     * by name.
     * @return FeatureName
     */
    public FeatureName getFeatureName() {
        return (FeatureName) (isText ? textFields.computeIfAbsent("feature_name",
                FeatureName::new) : getBinaryColumn("feature_name"));
    }

    /**
     * _pdbx_feature_domain.feature_type identifies the
     * type of feature.
     * @return FeatureType
     */
    public FeatureType getFeatureType() {
        return (FeatureType) (isText ? textFields.computeIfAbsent("feature_type",
                FeatureType::new) : getBinaryColumn("feature_type"));
    }

    /**
     * The value of _pdbx_feature_domain.feature_name.
     * @return Feature
     */
    public Feature getFeature() {
        return (Feature) (isText ? textFields.computeIfAbsent("feature",
                Feature::new) : getBinaryColumn("feature"));
    }

    /**
     * _pdbx_feature_domain.feature_identifier is an
     * additional identifier used to identify or
     * accession this feature.
     * @return FeatureIdentifier
     */
    public FeatureIdentifier getFeatureIdentifier() {
        return (FeatureIdentifier) (isText ? textFields.computeIfAbsent("feature_identifier",
                FeatureIdentifier::new) : getBinaryColumn("feature_identifier"));
    }

    /**
     * _pdbx_feature_domain.feature_assigned_by identifies
     * the individual, organization or program that
     * assigned the feature.
     * @return FeatureAssignedBy
     */
    public FeatureAssignedBy getFeatureAssignedBy() {
        return (FeatureAssignedBy) (isText ? textFields.computeIfAbsent("feature_assigned_by",
                FeatureAssignedBy::new) : getBinaryColumn("feature_assigned_by"));
    }

    /**
     * _pdbx_feature_domain.feature_citation_id is a
     * reference to a citation in the CITATION category.
     * @return FeatureCitationId
     */
    public FeatureCitationId getFeatureCitationId() {
        return (FeatureCitationId) (isText ? textFields.computeIfAbsent("feature_citation_id",
                FeatureCitationId::new) : getBinaryColumn("feature_citation_id"));
    }

    /**
     * _pdbx_feature_domain.feature_software_id is a
     * reference to an application described in the
     * SOFTWARE category.
     * @return FeatureSoftwareId
     */
    public FeatureSoftwareId getFeatureSoftwareId() {
        return (FeatureSoftwareId) (isText ? textFields.computeIfAbsent("feature_software_id",
                FeatureSoftwareId::new) : getBinaryColumn("feature_software_id"));
    }
}

package org.rcsb.cif.model.pdbxstructassemblypropdepositorinfo;

import org.rcsb.cif.model.BaseCategory;
import org.rcsb.cif.model.Column;

import javax.annotation.Generated;
import java.util.Map;

@Generated("org.rcsb.cif.internal.generator.SchemaGenerator")
public class PdbxStructAssemblyPropDepositorInfo extends BaseCategory {
    public PdbxStructAssemblyPropDepositorInfo(String name, Map<String, Column> columns) {
        super(name, columns);
    }

    public PdbxStructAssemblyPropDepositorInfo(String name, int rowCount, Object[] encodedColumns) {
        super(name, rowCount, encodedColumns);
    }

    public PdbxStructAssemblyPropDepositorInfo(String name) {
        super(name);
    }

    /**
     * The identifier for the assembly used in category STRUCT_BIOL.
     * @return BiolId
     */
    public BiolId getBiolId() {
        return (BiolId) (isText ? textFields.computeIfAbsent("biol_id",
                BiolId::new) : getBinaryColumn("biol_id"));
    }

    /**
     * The property type for the assembly.
     * @return Type
     */
    public Type getType() {
        return (Type) (isText ? textFields.computeIfAbsent("type",
                Type::new) : getBinaryColumn("type"));
    }

    /**
     * The value of the assembly property.
     * @return Value
     */
    public Value getValue() {
        return (Value) (isText ? textFields.computeIfAbsent("value",
                Value::new) : getBinaryColumn("value"));
    }

    /**
     * Additional details about this assembly property.
     * @return Details
     */
    public Details getDetails() {
        return (Details) (isText ? textFields.computeIfAbsent("details",
                Details::new) : getBinaryColumn("details"));
    }
}

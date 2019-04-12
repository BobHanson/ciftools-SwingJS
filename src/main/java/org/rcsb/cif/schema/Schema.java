package org.rcsb.cif.schema;

import org.rcsb.cif.CifReader;
import org.rcsb.cif.model.*;
import org.rcsb.cif.model.generated.CifBlock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Creates a type-safe data model using the mmCIF dictionary. Needs the basic data structure already present to
 * bootstrap itself.
 */
public class Schema {
    private static final Path OUTPUT_PATH = Paths.get("/Users/sebastian/model/");
    public static final String BASE_PACKAGE = "org.rcsb.cif.model.generated";
    /**
     * Collection of categories and columns to include in the generated model.
     */
    private static final Map<String, List<String>> filter;

    static {
        filter = new LinkedHashMap<>();

        new BufferedReader(new InputStreamReader(Objects.requireNonNull(Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream("mmcif-field-names.csv"))))
                .lines()
                .filter(line -> !line.isEmpty())
                .map(line -> line.split("\\."))
                .forEach(split -> {
                    List<String> cols = filter.computeIfAbsent(split[0], s -> new ArrayList<>());
                    cols.add(split[1]);
                });
    }

    public static boolean filter(String category) {
        return filter.containsKey(category);
    }

    public static boolean filter(String category, String column) {
        return filter.containsKey(category) && filter.get(category).contains(column);
    }

    public static void main(String[] args) throws IOException {
        new Schema().generate();
    }

    public static String toClassName(String rawName) {
        String name = Pattern.compile("_").splitAsStream(rawName)
                .map(s -> s.substring(0, 1).toUpperCase() + s.substring(1))
                .collect(Collectors.joining(""))
                // remove invalid characters
                .replace("/", "_")
                .replace("-", "_");
        if (name.equals("Class")) {
            return "Clazz";
        }
        return name;
    }

    public static String toPackageName(String rawName) {
        return toClassName(rawName).toLowerCase();
    }

    private void generate() throws IOException {
        getCategoryMetadata();

        buildListOfLinksBetweenCategories();

        getFieldData();

        writeClasses();
    }

    private void writeClasses() throws IOException {
        writeBlock(CifBlock.class.getSimpleName(), schema, OUTPUT_PATH);
    }

    private void writeBlock(String className, Map<String, Table> content, Path path) throws IOException {
        System.out.println(className);

        StringJoiner output = new StringJoiner("\n");
        output.add("package " + BASE_PACKAGE + ";");
        output.add("");

        StringJoiner getters = new StringJoiner("\n");

        for (Map.Entry<String, Table> entry : content.entrySet()) {
            String categoryName = entry.getKey();
            Table category = entry.getValue();

            if (!filter.containsKey(categoryName)) {
                continue;
            }

            String categoryClassName = toClassName(categoryName);

            getters.add("    /**");
            String description = Pattern.compile("\n").splitAsStream(category.getDescription())
                    .map(s -> "     * " + s)
                    .collect(Collectors.joining("\n"));
            getters.add(description);
            getters.add("     * @return " + categoryClassName);
            getters.add("     */");
            getters.add("    public " + BASE_PACKAGE + "." + categoryClassName.toLowerCase() + "." + categoryClassName +
                    " get" + categoryClassName + "() {");
            getters.add("        return (" + BASE_PACKAGE + "." + categoryClassName.toLowerCase() + "." +
                    categoryClassName + ") categories.get(\"" + categoryName + "\");");
            getters.add("    }");
            getters.add("");

            writeCategory(categoryClassName, entry.getValue(), path.resolve(categoryClassName.toLowerCase()), filter.get(categoryName));
        }

        output.add("import org.rcsb.cif.model.BaseCifBlock;");
        output.add("import org.rcsb.cif.model.CifCategory;");
        output.add("");
        output.add("import javax.annotation.Generated;");
        output.add("import java.util.Map;");
        output.add("");
        output.add("@Generated(\"org.rcsb.cif.schema.Schema\")");
        output.add("public class " + className + " extends " + BaseCifBlock.class.getSimpleName() + " {");

        // constructor
        output.add("    public " + className + "(Map<String, CifCategory> categories, String header) {");
        output.add("        super(categories, header, new ArrayList<>());");
        output.add("    }");
        output.add("");

        // getters
        output.add(getters.toString() + "}");
        output.add("");

        Files.write(path.resolve(className + ".java"), output.toString().getBytes());
    }

    private void writeCategory(String className, Table content, Path path, List<String> colFilter) throws IOException {
        System.out.println(" -> " + className);

        if (!Files.exists(path)) {
            Files.createDirectory(path);
        }

        StringJoiner output = new StringJoiner("\n");
        output.add("package " + BASE_PACKAGE + "." + className.toLowerCase() + ";");
        output.add("");
        output.add("import org.rcsb.cif.model.BaseCifCategory;");
        output.add("import org.rcsb.cif.model.CifColumn;");
        output.add("");
        output.add("import javax.annotation.Generated;");
        output.add("import java.util.Map;");
        output.add("");

        output.add("@Generated(\"org.rcsb.cif.schema.Schema\")");
        output.add("public class " + className + " extends " + BaseCifCategory.class.getSimpleName() + " {");

        StringJoiner getters = new StringJoiner("\n");

        for (Map.Entry<String, Object> entry : content.getColumns().entrySet()) {
            String columnName = entry.getKey();
            Col column = (Col) entry.getValue();

            if (!colFilter.contains(columnName)) {
                continue;
            }

            String columnClassName = toClassName(columnName);

            getters.add("    /**");
            String description = Pattern.compile("\n").splitAsStream(column.getDescription())
                    .map(s -> "     * " + s)
                    .collect(Collectors.joining("\n"));
            getters.add(description);
            getters.add("     * @return " + columnClassName);
            getters.add("     */");
            getters.add("    public " + columnClassName + " get" + columnClassName + "() {");
            getters.add("        return (" + columnClassName + ") (isText ? getTextColumn(\"" + columnName +
                    "\") : getBinaryColumn(\"" + columnName + "\", \"" + columnClassName + "\"));");
            getters.add("    }");
            getters.add("");

            writeColumn(columnClassName, column, path);
        }

        // constructor
        output.add("    public " + className + "(String name, Map<String, CifColumn> columns) {");
        output.add("        super(name, columns);");
        output.add("    }");
        output.add("");

        output.add("    public " + className + "(String name, int rowCount, Object[] encodedColumns) {");
        output.add("        super(name, rowCount, encodedColumns);");
        output.add("    }");
        output.add("");

        // getters
        output.add(getters.toString() + "}");
        output.add("");

        Files.write(path.resolve(className + ".java"), output.toString().getBytes());
    }

    private void writeColumn(String className, Col content, Path path) throws IOException {
        System.out.println(" -> -> " + className + " " + getBaseClass(content.getType()));

        StringJoiner output = new StringJoiner("\n");
        output.add("package " + BASE_PACKAGE + "." + path.toFile().getName() + ";");
        output.add("");
        output.add("import " + BASE_PACKAGE.replace(".generated", "") + ".*;");
        output.add("");
        output.add("import javax.annotation.Generated;");
        output.add("import java.util.Map;");
        output.add("");

        output.add("@Generated(\"org.rcsb.cif.schema.Schema\")");
        output.add("public class " + className + " extends " + getBaseClass(content.getType()) + " {");

        output.add("    public " + className + "(String data, int startToken, int endToken, String name) {");
        output.add("        super(data, startToken, endToken, name);");
        output.add("    }");
        output.add("");

        output.add("    public " + className + "(String data, int[] startToken, int[] endToken, String name) {");
        output.add("        super(data, startToken, endToken, name);");
        output.add("    }");
        output.add("");

        output.add("    public " + className + "(Map<String, Object> encodedColumn) {");
        output.add("        super(encodedColumn);");
        output.add("    }");

        if (content.getType().equals("enum")) {
            EnumCol enumCol = (EnumCol) content;
            output.add("");
            output.add("    public enum " + className + "Enum {");
            String enumString = enumCol.getValues()
                    .stream()
                    .map(String::toUpperCase)
                    .map(value -> "        " + value)
                    .collect(Collectors.joining(",\n"));
            output.add(enumString);
            output.add("    }");

            output.add("");

            output.add("    public " + className + "Enum get(int row) {");
            output.add("        return " + className + "Enum.valueOf(getString(row).toUpperCase());");
            output.add("    }");

            output.add("");

            output.add("    public Stream<" + className + "Enum> values() {");
            output.add("        return IntStream.range(0, getRowCount())");
            output.add("                .mapToObj(this::get);");
            output.add("    }");
        }

        output.add("}");
        output.add("");

        Files.write(path.resolve(className + ".java"), output.toString().getBytes());
    }

    private String getBaseClass(String type) {
        Class<?> clazz;
        switch (type) {
            case "coord":
                clazz = CoordColumn.class; break;
            case "enum":
                clazz = EnumColumn.class; break;
            case "float":
                clazz = FloatColumn.class; break;
            case "int":
                clazz = IntColumn.class; break;
            case "list":
                clazz = ListColumn.class; break;
            case "matrix":
                clazz = MatrixColumn.class; break;
            case "str":
                clazz = StrColumn.class; break;
            case "vector":
                clazz = VectorColumn.class; break;
            default:
                throw new IllegalArgumentException("Unknown type " + type);
        }
        return clazz.getSimpleName();
    }

    private Schema() throws IOException {
        this.cifFile = CifReader.parseText(Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream("mmcif_pdbx_v50.dic"));
        this.schema = new LinkedHashMap<>();
        this.categories = new LinkedHashMap<>();
        this.links = new LinkedHashMap<>();
    }

    private static final String RE_MATRIX_FIELD = "\\[[1-3]]\\[[1-3]]";
    private static final String RE_VECTOR_FIELD = "\\[[1-3]]";

    private static final List<String> FORCE_INT_FIELDS = Stream.of(
            "_atom_site.id",
            "_atom_site.auth_seq_id",
            "_pdbx_struct_mod_residue.auth_seq_id",
            "_struct_conf.beg_auth_seq_id",
            "_struct_conf.end_auth_seq_id",
            "_struct_conn.ptnr1_auth_seq_id",
            "_struct_conn.ptnr2_auth_seq_id",
            "_struct_sheet_range.beg_auth_seq_id",
            "_struct_sheet_range.end_auth_seq_id"
    ).collect(Collectors.toList());

    private static final List<String> COMMA_SEPARATED_LIST_FIELDS = Stream.of(
            "_atom_site.pdbx_struct_group_id",
            "_chem_comp.mon_nstd_parent_comp_id",
            "_diffrn_radiation.pdbx_wavelength_list",
            "_diffrn_source.pdbx_wavelength_list",
            "_em_diffraction.tilt_angle_list", // 20,40,50,55
            "_em_entity_assembly.entity_id_list",
            "_entity.pdbx_description", // Endolysin,Beta-2 adrenergic receptor
            "_entity.pdbx_ec",
            "_entity_poly.pdbx_strand_id", // A,B
            "_pdbx_depui_entry_details.experimental_methods",
            "_pdbx_depui_entry_details.requested_accession_types",
            "_pdbx_soln_scatter_model.software_list", // INSIGHT II, HOMOLOGY, DISCOVERY, BIOPOLYMER, DELPHI
            "_pdbx_soln_scatter_model.software_author_list", // MSI
            "_pdbx_soln_scatter_model.entry_fitting_list", // Odd example: "PDB CODE 1HFI, 1HCC, 1HFH, 1VCC"
            "_pdbx_struct_assembly_gen.entity_inst_id",
            "_pdbx_struct_assembly_gen.asym_id_list",
            "_pdbx_struct_assembly_gen.auth_asym_id_list",
            "_pdbx_struct_assembly_gen_depositor_info.asym_id_list",
            "_pdbx_struct_assembly_gen_depositor_info.chain_id_list",
            "_pdbx_struct_group_list.group_enumeration_type",
            "_reflns.pdbx_diffrn_id",
            "_refine.pdbx_diffrn_id",
            "_reflns_shell.pdbx_diffrn_id",
            "_struct_keywords.text"
    ).collect(Collectors.toList());

    private static final List<String> SPACE_SEPARATED_LIST_FIELDS = Stream.of(
            "_chem_comp.pdbx_subcomponent_list", // TSM DPH HIS CHF EMR
            "_pdbx_soln_scatter.data_reduction_software_list", // OTOKO
            "_pdbx_soln_scatter.data_analysis_software_list" // SCTPL5 GNOM
    ).collect(Collectors.toList());

    private static final List<String> SEMICOLON_SEPARATED_LIST_FIELDS = Collections.singletonList(
            "_chem_comp.pdbx_synonyms" // GLYCERIN; PROPANE-1,2,3-TRIOL
    );

    /**
     * Useful when a dictionary extension will add enum values to an existing dictionary.
     * By adding them here, the dictionary extension can be tested before the added enum
     * values are available in the existing dictionary.
     */
    private static final Map<String, List<String>> EXTRA_ENUM_VALUES = new LinkedHashMap<String, List<String>>() {{
        put("_pdbx_chem_comp_identifier.type", Arrays.asList("CONDENSED IUPAC CARB SYMBOL",
                "IUPAC CARB SYMBOL",
                "SNFG CARB SYMBOL"));
    }};

    private final CifFile cifFile;
    private final Map<String, Table> schema;
    private final Map<String, BaseCifBlock> categories;
    private final Map<String, String> links;

    private void getFieldData() {
        categories.forEach((fullName, saveFrame) -> {
            String header = saveFrame.getHeader();
            String categoryName = header.substring(1, header.contains(".") ? header.indexOf(".") : header.length());
            String itemName = header.substring(header.contains(".") ? header.indexOf(".") + 1 : 1);
            Map<String, Object> fields = schema.get(categoryName).getColumns();

            String description = getDescription(saveFrame);
//            System.out.println(description);

            // need to use regex to check for matrix or vector items
            // as sub_category assignment is missing for some entries
            String subCategory = getSubCategory(saveFrame);
            if ("cartesian_coordinate".equals(subCategory) || "fractional_coordinate".equals(subCategory)) {
                fields.put(itemName, new CoordCol(description));
            } else if (FORCE_INT_FIELDS.contains(header)) {
                fields.put(itemName, new IntCol(description));
            } else if ("matrix".equals(subCategory)) {
                fields.put(itemName.replaceAll(RE_MATRIX_FIELD, ""), new MatrixCol(3, 3, description));
            } else if ("vector".equals(subCategory)) {
                fields.put(itemName.replaceAll(RE_VECTOR_FIELD, ""), new VectorCol(3, description));
            } else {
                if (itemName.matches(RE_MATRIX_FIELD)) {
                    fields.put(itemName.replaceAll(RE_MATRIX_FIELD, ""), new MatrixCol(3, 3, description));
                } else if (itemName.matches(RE_VECTOR_FIELD)) {
                    fields.put(itemName.replaceAll(RE_VECTOR_FIELD, ""), new VectorCol(3, description));
                } else {
                    List<String> code = getCode(saveFrame);
                    if (code.size() > 0) {
                        Col fieldType = getFieldType(code.get(0), description, code.subList(1, code.size()));
                        if (fieldType instanceof StrCol) {
                            if (COMMA_SEPARATED_LIST_FIELDS.contains(header)) {
                                fieldType = new ListCol("str", ",", description);
                            } else if (SPACE_SEPARATED_LIST_FIELDS.contains(header)) {
                                fieldType = new ListCol("str", " ", description);
                            } else if (SEMICOLON_SEPARATED_LIST_FIELDS.contains(header)) {
                                fieldType = new ListCol("str", ";", description);
                            }
                        }
                        if (EXTRA_ENUM_VALUES.containsKey(header)) {
                            if (fieldType instanceof EnumCol) {
                                ((EnumCol) fieldType).getValues().addAll(EXTRA_ENUM_VALUES.get(header));
                            }
                        }
                        fields.put(itemName, fieldType);
                    }
                }
            }
        });
    }

    private Col getFieldType(String type, String description, List<String> values) {
        switch (type) {
            case "code":
            case "ucode":
            case "line":
            case "uline":
            case "text":
            case "char":
            case "uchar3":
            case "uchar1":
            case "boolean":
                return values.size() > 0 ? new EnumCol(values, "str", description) : new StrCol(description);
            case "aliasname":
            case "name":
            case "idname":
            case "any":
            case "atcode":
            case "fax":
            case "phone":
            case "email":
            case "code30":
            case "seq-one-letter-code":
            case "author":
            case "orcid_id":
            case "sequence_dep":
            case "pdb_id":
            case "emd_id":
            case "yyyy-mm-dd":
            case "yyyy-mm-dd:hh:mm":
            case "yyyy-mm-dd:hh:mm-flex":
            case "int-range":
            case "float-range":
            case "binary":
            case "operation_expression":
            case "point_symmetry":
            case "4x3_matrix":
            case "3x4_matrices":
            case "point_group":
            case "point_group_helical":
            case "symmetry_operation":
            case "date_dep":
            case "url":
            case "symop":
                return new StrCol(description);
            case "int":
            case "non_negative_int":
            case "positive_int":
                return values.size() > 0 ? new EnumCol(values, "int", description) : new IntCol(description);
            case "float":
                return new FloatCol(description);
            case "ec-type":
            case "ucode-alphanum-csv":
            case "id_list":
                return new ListCol("str", ",", description);
            default:
                return new StrCol(description);
        }
    }

    private List<String> getCode(BaseCifBlock saveFrame) {
        try {
            CifColumn code = getField("item_type", "code", saveFrame);
            return Stream.concat(Stream.of(code.getString(0)), getEnums(saveFrame)).collect(Collectors.toList());
        } catch (NullPointerException e) {
            return Collections.emptyList();
        }
    }

    private Stream<String> getEnums(BaseCifBlock saveFrame) {
        try {
            CifColumn value = getField("item_enumeration", "value", saveFrame);
            return IntStream.range(0, value.getRowCount())
                    .mapToObj(value::getString);
        } catch (NullPointerException e) {
            return Stream.empty();
        }
    }

    private String getSubCategory(BaseCifBlock saveFrame) {
        try {
            CifColumn value = getField("item_sub_category", "id", saveFrame);
            return value.getString(0);
        } catch (NullPointerException e) {
            return "";
        }
    }

    private String getDescription(BaseCifBlock saveFrame) {
        CifColumn value = getField("item_description", "description", saveFrame);
        return Pattern.compile("\n").splitAsStream(value.getString(0))
                .map(String::trim)
                .collect(Collectors.joining("\n"))
                .replaceAll("(\\[[1-3]])+ element", "elements")
                .replaceAll("(\\[[1-3]])+", "");
    }

    private CifColumn getField(String category, String field, BaseCifBlock saveFrame) {
        try {
            CifCategory cat = saveFrame.getCategory(category);
            return cat.getColumn(field);
        } catch (NullPointerException e) {
            String linkName = links.get(saveFrame.getHeader());
            return getField(category, field, categories.get(linkName));
        }
    }

    private void buildListOfLinksBetweenCategories() {
        cifFile.getBlocks()
                .get(0)
                .getSaveFrames()
                .stream()
                .filter(saveFrame -> saveFrame.getHeader().startsWith("_"))
                .forEach(saveFrame -> {
                    categories.put(saveFrame.getHeader(), saveFrame);
                    CifCategory item_linked = saveFrame.getCategory("item_linked");

                    if (item_linked == null) {
                        return;
                    }

                    CifColumn child_name = item_linked.getColumn("child_name");
                    CifColumn parent_name = item_linked.getColumn("parent_name");

                    for (int i = 0; i < item_linked.getRowCount(); i++) {
                        String childName = child_name.getString(i);
                        String parentName = parent_name.getString(i);
                        links.put(childName, parentName);
                    }
                });
    }

    private void getCategoryMetadata() {
        cifFile.getBlocks()
                .get(0)
                .getSaveFrames()
                .stream()
                .filter(saveFrame -> !saveFrame.getHeader().startsWith("_"))
                .forEach(saveFrame -> {
                    Set<String> categoryKeyNames = new HashSet<>();
                    CifColumn cifColumn = saveFrame.getCategory("category_key").getColumn("name");
                    for (int i = 0; i < cifColumn.getRowCount(); i++) {
                        categoryKeyNames.add(cifColumn.getString(i));
                    }

                    String rawDescription = saveFrame.getCategory("category")
                            .getColumn("description")
                            .getString(0);
                    String description = Pattern.compile("\n")
                            .splitAsStream(rawDescription)
                            .map(String::trim)
                            .collect(Collectors.joining("\n"));

                    schema.put(saveFrame.getHeader(), new Table(description, categoryKeyNames, new LinkedHashMap<>()));
                });
    }
}


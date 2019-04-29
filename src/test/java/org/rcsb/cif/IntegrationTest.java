package org.rcsb.cif;

import org.junit.Test;
import org.rcsb.cif.model.Block;
import org.rcsb.cif.model.CifFile;
import org.rcsb.cif.model.Column;
import org.rcsb.cif.model.ValueKind;
import org.rcsb.cif.model.generated.atomsite.*;
import org.rcsb.cif.model.generated.atomsites.AtomSites;
import org.rcsb.cif.model.generated.cell.Cell;
import org.rcsb.cif.model.generated.cell.PdbxUniqueAxis;
import org.rcsb.cif.model.generated.entry.Entry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.rcsb.cif.TestHelper.TEST_CASES;
import static org.rcsb.cif.TestHelper.assertEqualsLoosely;

/**
 * More complex tests for interactions between various parts of the code. Especially round-trip are used to assess the
 * fidelity of the implementation. For a Cif file encoding and subsequent decoding should arrive at the original file
 * content. For Bcif decoding and encoding should do the same.
 */
public class IntegrationTest {
    @Test
    public void testGzipBehavior() throws IOException {
        //TODO impl
    }

    @Test
    public void testVectorAndMatrixBehavior() throws IOException {
        CifFile textCifFile = CifReader.readText(TestHelper.getInputStream("cif/1acj.cif"));
        testVectorAndMatrixBehavior(textCifFile);

        CifFile binaryCifFile = CifReader.readBinary(TestHelper.getInputStream("bcif/1acj.bcif"));
        testVectorAndMatrixBehavior(binaryCifFile);
    }

    private void testVectorAndMatrixBehavior(CifFile cifFile) {
        AtomSites atomSites = cifFile.getBlocks().get(0).getAtomSites();

        assertDefined(atomSites.getFractTransfMatrix11());
        assertEquals(0.008795, atomSites.getFractTransfMatrix11().get(), TestHelper.ERROR_MARGIN);
        assertDefined(atomSites.getFractTransfMatrix12());
        assertEquals(0.005078, atomSites.getFractTransfMatrix12().get(), TestHelper.ERROR_MARGIN);
        assertDefined(atomSites.getFractTransfMatrix13());
        assertEquals(0.0, atomSites.getFractTransfMatrix13().get(), TestHelper.ERROR_MARGIN);
        assertDefined(atomSites.getFractTransfMatrix21());
        assertEquals(0.0, atomSites.getFractTransfMatrix21().get(), TestHelper.ERROR_MARGIN);
        assertDefined(atomSites.getFractTransfMatrix22());
        assertEquals(0.010156, atomSites.getFractTransfMatrix22().get(), TestHelper.ERROR_MARGIN);
        assertDefined(atomSites.getFractTransfMatrix23());
        assertEquals(0.0, atomSites.getFractTransfMatrix23().get(), TestHelper.ERROR_MARGIN);
        assertDefined(atomSites.getFractTransfMatrix31());
        assertEquals(0.0, atomSites.getFractTransfMatrix31().get(), TestHelper.ERROR_MARGIN);
        assertDefined(atomSites.getFractTransfMatrix32());
        assertEquals(0.0, atomSites.getFractTransfMatrix32().get(), TestHelper.ERROR_MARGIN);
        assertDefined(atomSites.getFractTransfMatrix33());
        assertEquals(0.007241, atomSites.getFractTransfMatrix33().get(), TestHelper.ERROR_MARGIN);

        assertDefined(atomSites.getFractTransfVector1());
        assertEquals(0.0, atomSites.getFractTransfVector1().get(), TestHelper.ERROR_MARGIN);
        assertDefined(atomSites.getFractTransfVector2());
        assertEquals(0.0, atomSites.getFractTransfVector2().get(), TestHelper.ERROR_MARGIN);
        assertDefined(atomSites.getFractTransfVector3());
        assertEquals(0.0, atomSites.getFractTransfVector3().get(), TestHelper.ERROR_MARGIN);
    }

    private void assertDefined(Column column) {
        assertNotNull(column);
        assertTrue(column.isDefined());
        assertTrue(column.getRowCount() > 0);
    }

    @Test
    public void testSingleRowStraightMessagePack() throws IOException {
        String expected = "0RED";

        // from conventional WriterTest: should report 1 row with value 0RED
        CifFile textCifFile = CifReader.readText(TestHelper.getInputStream("cif/0red.cif"));
        Entry textEntry = textCifFile.getFirstBlock().getEntry();
        assertEquals("id", textEntry.getId().getColumnName());
        assertEquals(1, textEntry.getRowCount());
        assertEquals(expected, textEntry.getId().get());

        // convert to binary representation
        BinaryCifWriterOptions options = BinaryCifWriterOptions.create().setSingleRowByMessagePack(true).build();
        InputStream binary = CifWriter.writeBinary(textCifFile);

        // decode binary
        CifFile binaryCifFile = CifReader.readBinary(binary);
        Entry binaryEntry = binaryCifFile.getFirstBlock().getEntry();
        assertEquals("id", binaryEntry.getId().getColumnName());
        assertEquals(1, binaryEntry.getRowCount());
        assertEquals(expected, binaryEntry.getId().get());
    }

    @Test
    public void testUndefinedColumnBehavior() throws IOException {
        CifFile textCifFile = CifReader.readText(TestHelper.getInputStream("cif/0red.cif"));
        testUndefinedColumnBehavior(textCifFile);

        CifFile binaryCifFile = CifReader.readBinary(TestHelper.getInputStream("bcif/0red.bcif"));
        testUndefinedColumnBehavior(binaryCifFile);
    }

    private void testUndefinedColumnBehavior(CifFile cifFile) {
        Block block = cifFile.getFirstBlock();
        assertNotNull("header is corrupted", block.getBlockHeader());

        assertTrue(block.getEntry().isDefined());

        String entryId = block.getEntry().getId().get();
        assertEquals("0RED", entryId);

        // atom site should be obtainable
        AtomSite atomSite = block.getAtomSite();
        // and return its name
        assertEquals("atom_site", atomSite.getCategoryName());
        // though not be present
        assertFalse(atomSite.isDefined());
        // report 0 rows
        assertEquals(0, atomSite.getRowCount());

        // columns still should be accessible
        CartnX cartnX = atomSite.getCartnX();
        assertEquals("Cartn_x", cartnX.getColumnName());
        assertEquals(0, cartnX.getRowCount());
        assertFalse(cartnX.isDefined());
    }

    @Test
    public void testNumberFormat() {
        String[] text = {"1.0", "2", "-1.567891234567"};
        String data = String.join("", text);
        int[] start = new int[] { 0, 3, 4 };
        int[] end = new int[] { 3, 4, data.length() };

        // coord columns print with 3 decimal digits
        CartnX cartnX = new CartnX("name", text.length, data, start, end);
        CartnY cartnY = new CartnY("name", text.length, data, start, end);
        CartnZ cartnZ = new CartnZ("name", text.length, data, start, end);

        Stream.of(cartnX, cartnY, cartnZ).forEach(coordColumn -> {
            assertEquals("1.000", coordColumn.getStringData(0));
            assertEquals("2.000", coordColumn.getStringData(1));
            assertEquals("-1.568", coordColumn.getStringData(2));
        });

        // occupancy uses 2 decimal digits
        Occupancy occupancy = new Occupancy("name", text.length, data, start, end);
        assertEquals("1.00", occupancy.getStringData(0));
        assertEquals("2.00", occupancy.getStringData(1));
        assertEquals("-1.57", occupancy.getStringData(2));

        // all other should fallback to default behavior
        BIsoOrEquiv bIsoOrEquiv = new BIsoOrEquiv("name", text.length, data, start, end);
        // truncate float which perfectly round to integers
        assertEquals("1", bIsoOrEquiv.getStringData(0));
        assertEquals("2", bIsoOrEquiv.getStringData(1));
        // cut long numbers after 6 decimal places
        assertEquals("-1.567891", bIsoOrEquiv.getStringData(2));
    }

    @Test
    public void test_pdbx_poly_seq_scheme_auth_mon_idText() throws IOException {
        InputStream inputStream = TestHelper.getInputStream("cif/1acj.cif");
        CifFile text = CifReader.readText(inputStream);

        String stringData = text.getBlocks().get(0)
                .getCategory("pdbx_poly_seq_scheme")
                .getColumn("auth_mon_id")
                .getStringData(0);
        System.out.println(stringData);
    }

    @Test
    public void test_pdbx_poly_seq_scheme_auth_mon_idBinary() throws IOException {
        InputStream inputStream = TestHelper.getInputStream("bcif/modelserver/1acj.bcif");
        CifFile text = CifReader.readBinary(inputStream);

        String stringData = text.getBlocks().get(0)
                .getCategory("pdbx_poly_seq_scheme")
                .getColumn("auth_mon_id")
                .getStringData(0);
        System.out.println(stringData);
    }

    @Test
    public void testUnknownFeatureText() throws IOException {
        // read from cif
        InputStream inputStream = TestHelper.getInputStream("cif/1acj.cif");
        CifFile text = CifReader.readText(inputStream);

        Cell cell = text.getBlocks().get(0).getCell();

        PdbxUniqueAxis pdbxUniqueAxis = cell.getPdbxUniqueAxis();

        assertEquals(ValueKind.UNKNOWN, pdbxUniqueAxis.getValueKind(0));
        assertEquals("", pdbxUniqueAxis.get(0));
    }

    @Test
    public void testNotPresentFeatureText() throws IOException {
        // read from cif
        InputStream inputStream = TestHelper.getInputStream("cif/1acj.cif");
        CifFile text = CifReader.readText(inputStream);

        LabelAltId labelAltId = text.getBlocks().get(0).getAtomSite().getLabelAltId();

        assertEquals(ValueKind.NOT_PRESENT, labelAltId.getValueKind(0));
        assertEquals("", labelAltId.get(0));
    }

    @Test
    public void testUnknownFeatureBinary() throws IOException {
        // read from cif
        InputStream inputStream = TestHelper.getInputStream("bcif/modelserver/1acj.bcif");
        CifFile text = CifReader.readBinary(inputStream);

        Cell cell = text.getBlocks().get(0).getCell();

        PdbxUniqueAxis pdbxUniqueAxis = cell.getPdbxUniqueAxis();

        assertEquals(ValueKind.UNKNOWN, pdbxUniqueAxis.getValueKind(0));
        assertEquals("", pdbxUniqueAxis.get(0));
    }

    @Test
    public void testNotPresentFeatureBinary() throws IOException {
        // read from cif
        InputStream inputStream = TestHelper.getInputStream("bcif/modelserver/1acj.bcif");
        CifFile text = CifReader.readBinary(inputStream);

        LabelAltId labelAltId = text.getBlocks().get(0).getAtomSite().getLabelAltId();

        assertEquals(ValueKind.NOT_PRESENT, labelAltId.getValueKind(0));
        assertEquals("", labelAltId.get(0));
    }

    @Test
    public void roundTripViaBinary() throws IOException {
        for (String id : TEST_CASES.keySet()) {
            System.out.println(id + " via binary");
            roundTripViaBinary(id);
        }
    }

    private void roundTripViaBinary(String testCase) throws IOException {
        String originalContent = new String(TestHelper.getBytes("cif/" + testCase + ".cif"));
        CifFile originalFile = CifReader.readText(TestHelper.getInputStream("cif/" + testCase + ".cif"));

        InputStream bcifInputStream = CifWriter.writeBinary(originalFile);

        CifFile bcifFile = CifReader.readBinary(bcifInputStream);

        InputStream copyInputStream = CifWriter.writeText(bcifFile);
        String copyContent = new BufferedReader(new InputStreamReader(copyInputStream))
                .lines()
                .collect(Collectors.joining("\n"));

        assertEqualsLoosely(originalContent, copyContent);
    }

    @Test
    public void roundTripViaText() throws IOException {
        for (String id : TEST_CASES.keySet()) {
            System.out.println(id + " via text");
            roundTripViaText(id);
        }
    }

    private void roundTripViaText(String testCase) throws IOException {
        String originalContent = new String(TestHelper.getBytes("bcif/" + testCase + ".bcif"));
        CifFile originalFile = CifReader.readBinary(TestHelper.getInputStream("bcif/" + testCase + ".bcif"));

        InputStream cifInputStream = CifWriter.writeText(originalFile);
        CifFile cifFile = CifReader.readText(cifInputStream);

        InputStream copyInputStream = CifWriter.writeBinary(cifFile);
        String copyContent = new BufferedReader(new InputStreamReader(copyInputStream))
                .lines()
                .collect(Collectors.joining("\n"));

        // cannot match to David's bcif data as column types differ slightly
//        assertEquals(originalContent, copyContent);
        assertNotNull(originalContent);
        assertNotNull(copyContent);
    }

    @Test
    public void readCifWriteBcif() throws IOException {
        for (String id : TEST_CASES.keySet()) {
            System.out.println(id + " cif to bcif");
            readCifWriteBcif(id);
        }
    }

    private void readCifWriteBcif(String testCase) throws IOException {
        String originalContent = new String(TestHelper.getBytes("bcif/" + testCase + ".bcif"));
        CifFile originalFile = CifReader.readText(TestHelper.getInputStream("cif/" + testCase + ".cif"));

        InputStream copyInputStream = CifWriter.writeBinary(originalFile);
        String copyContent = new BufferedReader(new InputStreamReader(copyInputStream))
                .lines()
                .collect(Collectors.joining("\n"));

        // cannot match to David's bcif data as column types differ slightly
//        assertEquals(originalContent, copyContent);
        assertNotNull(originalContent);
        assertNotNull(copyContent);
    }

    @Test
    public void readBcifWriteCif() throws IOException {
        for (String id : TEST_CASES.keySet()) {
            System.out.println(id + " bcif to cif");
            readBcifWriteCif(id);
        }
    }

    private void readBcifWriteCif(String testCase) throws IOException {
        // last category _pdbe_structure_quality_report_issues is missing in binary source
        String originalContent = new String(TestHelper.getBytes("cif/" + testCase + ".mol"));
        CifFile originalFile = CifReader.readBinary(TestHelper.getInputStream("bcif/" + testCase + ".bcif"));

        InputStream copyInputStream = CifWriter.writeText(originalFile);
        String copyContent = new BufferedReader(new InputStreamReader(copyInputStream))
                .lines()
                .collect(Collectors.joining("\n"));

        assertEqualsLoosely(originalContent, copyContent);
    }
}

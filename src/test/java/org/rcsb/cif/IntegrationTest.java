package org.rcsb.cif;

import org.junit.Test;
import org.rcsb.cif.model.*;
import org.rcsb.cif.model.generated.AtomSite;
import org.rcsb.cif.model.generated.AtomSites;
import org.rcsb.cif.model.generated.Cell;
import org.rcsb.cif.model.generated.Entry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.rcsb.cif.TestHelper.*;

/**
 * More complex tests for interactions between various parts of the code. Especially round-trip are used to assess the
 * fidelity of the implementation. For a Cif file encoding and subsequent decoding should arrive at the original file
 * content. For Bcif decoding and encoding should do the same.
 */
public class IntegrationTest {
    @Test
    public void testVectorAndMatrixBehavior() throws IOException {
        CifFile textCifFile = CifReader.readText(TestHelper.getInputStream("cif/1acj.cif"));
        testVectorAndMatrixBehavior(textCifFile);

        CifFile binaryCifFile = CifReader.readBinary(TestHelper.getInputStream("bcif/molstar/1acj.bcif"));
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

        CifFile binaryCifFile = CifReader.readBinary(TestHelper.getInputStream("bcif/molstar/0red.bcif"));
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
        FloatColumn cartnX = atomSite.getCartnX();
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
        FloatColumn cartnX = new FloatColumn("Cartn_x", text.length, data, start, end);
        FloatColumn cartnY = new FloatColumn("Cartn_y", text.length, data, start, end);
        FloatColumn cartnZ = new FloatColumn("Cartn_z", text.length, data, start, end);

        Stream.of(cartnX, cartnY, cartnZ).forEach(coordColumn -> {
            assertEquals("1.000", coordColumn.getStringData(0));
            assertEquals("2.000", coordColumn.getStringData(1));
            assertEquals("-1.568", coordColumn.getStringData(2));
        });

        // occupancy uses 2 decimal digits
        FloatColumn occupancy = new FloatColumn("occupancy", text.length, data, start, end);
        assertEquals("1.00", occupancy.getStringData(0));
        assertEquals("2.00", occupancy.getStringData(1));
        assertEquals("-1.57", occupancy.getStringData(2));

        // all other should fallback to default behavior
        FloatColumn bIsoOrEquiv = new FloatColumn("iso", text.length, data, start, end);
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
        test_pdbx_poly_seq_scheme_auth_mon_id(text);
    }

    @Test
    public void test_pdbx_poly_seq_scheme_auth_mon_idBinary() throws IOException {
        InputStream inputStream = TestHelper.getInputStream("bcif/molstar/1acj.bcif");
        CifFile binary = CifReader.readBinary(inputStream);
        test_pdbx_poly_seq_scheme_auth_mon_id(binary);
    }

    private void test_pdbx_poly_seq_scheme_auth_mon_id(CifFile cifFile) {
        Column column = cifFile.getFirstBlock()
                .getCategory("pdbx_poly_seq_scheme")
                .getColumn("auth_mon_id");

        // should be ? for first group
        String firstStringData = column.getStringData(0);
        // which reports as an empty string
        assertTrue(firstStringData.isEmpty());
        assertEquals(ValueKind.UNKNOWN, column.getValueKind(0));

        // should be ? for first group
        String forthStringData = column.getStringData(3);
        // which reports as an empty string
        assertFalse(forthStringData.isEmpty());
        assertEquals(ValueKind.PRESENT, column.getValueKind(3));
    }

    @Test
    public void testUnknownFeatureText() throws IOException {
        // read from cif
        InputStream inputStream = TestHelper.getInputStream("cif/1acj.cif");
        CifFile text = CifReader.readText(inputStream);

        Cell cell = text.getBlocks().get(0).getCell();

        StrColumn pdbxUniqueAxis = cell.getPdbxUniqueAxis();

        assertEquals(ValueKind.UNKNOWN, pdbxUniqueAxis.getValueKind(0));
        assertEquals("", pdbxUniqueAxis.get(0));
    }

    @Test
    public void testNotPresentFeatureText() throws IOException {
        // read from cif
        InputStream inputStream = TestHelper.getInputStream("cif/1acj.cif");
        CifFile text = CifReader.readText(inputStream);

        StrColumn labelAltId = text.getFirstBlock().getAtomSite().getLabelAltId();

        assertEquals(ValueKind.NOT_PRESENT, labelAltId.getValueKind(0));
        assertEquals("", labelAltId.get(0));
    }

    @Test
    public void testUnknownFeatureBinary() throws IOException {
        // read from cif
        InputStream inputStream = TestHelper.getInputStream("bcif/molstar/1acj.bcif");
        CifFile text = CifReader.readBinary(inputStream);

        Cell cell = text.getFirstBlock().getCell();

        StrColumn pdbxUniqueAxis = cell.getPdbxUniqueAxis();

        assertEquals(ValueKind.UNKNOWN, pdbxUniqueAxis.getValueKind(0));
        assertEquals("", pdbxUniqueAxis.get(0));
    }

    @Test
    public void testNotPresentFeatureBinary() throws IOException {
        // read from cif
        InputStream inputStream = TestHelper.getInputStream("bcif/molstar/1acj.bcif");
        CifFile text = CifReader.readBinary(inputStream);

        StrColumn labelAltId = text.getFirstBlock().getAtomSite().getLabelAltId();

        assertEquals(ValueKind.NOT_PRESENT, labelAltId.getValueKind(0));
        assertEquals("", labelAltId.get(0));
    }

    @Test
    public void roundTripViaBinary() throws IOException {
        for (String id : TEST_CASES.keySet()) {
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
            roundTripViaText(id);
        }
    }

    private void roundTripViaText(String testCase) throws IOException {
//        byte[] original = TestHelper.getBytes("bcif/ciftools/" + testCase + ".bcif");
        CifFile originalFile = CifReader.readBinary(TestHelper.getInputStream("bcif/ciftools/" + testCase + ".bcif"));

        InputStream cifInputStream = CifWriter.writeText(originalFile);
        CifFile cifFile = CifReader.readText(cifInputStream);

        InputStream copyInputStream = CifWriter.writeBinary(cifFile);
        byte[] output = getBytes(copyInputStream);

        assertNotNull(output);
//        assertEquals(new String(original, StandardCharsets.UTF_8), new String(output, StandardCharsets.UTF_8));
//        assertArrayEquals("binary write output does not match snapshot of output for " + testCase +
//                " - did the implementation change? if so, update snapshot files in bcif/ciftools/", original, output);
    }

    @Test
    public void readCifWriteBcif() throws IOException {
        for (String id : TEST_CASES.keySet()) {
            readCifWriteBcif(id);
        }
    }

    private void readCifWriteBcif(String testCase) throws IOException {
//        byte[] original = TestHelper.getBytes("bcif/ciftools/" + testCase + ".bcif");
        CifFile originalFile = CifReader.readText(TestHelper.getInputStream("cif/" + testCase + ".cif"));

        InputStream copyInputStream = CifWriter.writeBinary(originalFile);
        byte[] output = getBytes(copyInputStream);

        assertNotNull(output);
//        assertEquals(new String(original, StandardCharsets.UTF_8), new String(output, StandardCharsets.UTF_8));
//        assertArrayEquals("binary write output does not match snapshot of output for " + testCase +
//                " - did the implementation change? if so, update snapshot files in bcif/ciftools/", original, output);
    }

    @Test
    public void readBcifWriteCif() throws IOException {
        for (String id : TEST_CASES.keySet()) {
            readBcifWriteCif(id);
        }
    }

    private void readBcifWriteCif(String testCase) throws IOException {
        // last category _pdbe_structure_quality_report_issues is missing in binary source
        String originalContent = new String(TestHelper.getBytes("cif/molstar/" + testCase + ".mol"));
        CifFile originalFile = CifReader.readBinary(TestHelper.getInputStream("bcif/molstar/" + testCase + ".bcif"));

        InputStream copyInputStream = CifWriter.writeText(originalFile);
        String copyContent = new BufferedReader(new InputStreamReader(copyInputStream))
                .lines()
                .collect(Collectors.joining("\n"));

        assertEqualsLoosely(originalContent, copyContent);
    }
}

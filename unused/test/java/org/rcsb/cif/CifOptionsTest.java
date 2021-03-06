package org.rcsb.cif;

import org.junit.Test;
import org.rcsb.cif.binary.codec.Codec;
import org.rcsb.cif.api.BinaryFile;
import org.rcsb.cif.api.CifFile;
import org.rcsb.cif.api.CifIO;
import org.rcsb.cif.api.TextFile;
import org.rcsb.cif.api.generated.AtomSite;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import static org.junit.Assert.*;
import static org.rcsb.cif.TestHelper.TEST_CASES;

public class CifOptionsTest {
    @Test
    public void testEncodingBehavior() throws IOException {
        CifFile textCifFile = CifIO.readFromInputStream(TestHelper.getInputStream("cif/1acj.cif"));

        byte[] binary1 = CifIO.writeBinary(textCifFile, CifOptions.builder()
                .encodingStrategyHint(new String(TestHelper.getBytes("encoding-hint/hint1.json")))
                .build());

        // check that precision was honored
        CifFile binaryCifFile1 = CifIO.readFromInputStream(new ByteArrayInputStream(binary1));
        AtomSite atomSite1 = binaryCifFile1.getFirstBlock().getAtomSite();
        atomSite1.getCartnX()
                .values()
                .map(d -> d * 10)
                .forEach(d -> assertEquals(Math.round(d), d, TestHelper.ERROR_MARGIN));
        atomSite1.getCartnY()
                .values()
                .map(d -> d * 100)
                .forEach(d -> assertEquals(Math.round(d), d, TestHelper.ERROR_MARGIN));
        atomSite1.getCartnZ()
                .values()
                .map(d -> d * 1000)
                .forEach(d -> assertEquals(Math.round(d), d, TestHelper.ERROR_MARGIN));

        byte[] binary2 = CifIO.writeBinary(textCifFile, CifOptions.builder()
                .encodingStrategyHint(new String(TestHelper.getBytes("encoding-hint/hint2.json")))
                .build());

        // check that precision was honored
        CifFile binaryCifFile2 = CifIO.readFromInputStream(new ByteArrayInputStream(binary2));
        AtomSite atomSite2 = binaryCifFile2.getFirstBlock().getAtomSite();
        atomSite2.getCartnX()
                .values()
                .map(d -> d * 10)
                .forEach(d -> assertEquals(Math.round(d), d, TestHelper.ERROR_MARGIN));
        atomSite2.getCartnY()
                .values()
                .map(d -> d * 1000)
                .forEach(d -> assertEquals(Math.round(d), d, TestHelper.ERROR_MARGIN));
        atomSite2.getCartnZ()
                .values()
                .map(d -> d * 100000)
                .forEach(d -> assertEquals(Math.round(d), d, TestHelper.ERROR_MARGIN));
    }

    @Test
    public void testEncoder() throws IOException {
        // the encoder name should be honored when specified
        String encoderName = "yet-another-cif-encoder";
        CifFile cifFile = CifIO.readFromInputStream(TestHelper.getInputStream("bcif/1acj.bcif"));

        byte[] bytes = CifIO.writeBinary(cifFile,
                CifOptions.builder().encoder(encoderName).build());

        Map<String, Object> map = Codec.MESSAGE_PACK_CODEC.decode(new ByteArrayInputStream(bytes));
        assertEquals(encoderName, map.get("encoder"));
    }

    @Test
    public void testFetchUrlText() throws IOException {
        // by switching to RCSB cif files, the implementation type should be text
        CifFile cifFile = CifIO.readById("1acj", CifOptions.builder()
                .fetchUrl("https://files.rcsb.org/download/%s.cif").build());
        assertTrue(cifFile instanceof TextFile);
    }

    @Test
    public void testFetchUrlBinary() throws IOException {
        // by switching to RCSB bcif files, the implementation type should be binary
        CifFile cifFile = CifIO.readById("1acj", CifOptions.builder()
                .fetchUrl("https://models.rcsb.org/%s.bcif").build());
        assertTrue(cifFile instanceof BinaryFile);
    }

    @Test
    public void testFilteringBehavior() throws IOException {
        for (String id : TEST_CASES.keySet()) {
            testFilteringBehavior(id);
        }
    }

    private static final CifOptions BLACKLIST_OPTIONS = CifOptions.builder()
            .categoryBlacklist("entry")
            .columnBlacklist("atom_site.Cartn_x")
            .columnBlacklist("atom_site.Cartn_y")
            .columnBlacklist("atom_site.Cartn_z")
            .build();
    private static final CifOptions WHITELIST_OPTIONS = CifOptions.builder()
            .categoryWhitelist("entry")
            .columnWhitelist("atom_site.Cartn_x")
            .columnWhitelist("atom_site.Cartn_y")
            .columnWhitelist("atom_site.Cartn_z")
            .build();

    private void testFilteringBehavior(String testCase) throws IOException {
        // check that file was loaded correctly
        CifFile file = CifIO.readFromInputStream(TestHelper.getInputStream("bcif/" + testCase + ".bcif"));
        assertEquals(testCase.toUpperCase(), file.getFirstBlock().getEntry().getId().get(0));

        // text file with some categories blacklisted
        CifFile blacklistTextFile = CifIO.readFromInputStream(new ByteArrayInputStream(CifIO.writeText(file, BLACKLIST_OPTIONS)));
        assertTrue(blacklistTextFile.getFirstBlock().getCategoryNames().size() > 0);
        assertFalse(blacklistTextFile.getFirstBlock().getEntry().isDefined());
        assertTrue(blacklistTextFile.getFirstBlock().getAtomSite().isDefined());
        assertTrue(blacklistTextFile.getFirstBlock().getAtomSite().getGroupPDB().isDefined());
        assertFalse(blacklistTextFile.getFirstBlock().getAtomSite().getCartnX().isDefined());
        assertFalse(blacklistTextFile.getFirstBlock().getAtomSite().getCartnY().isDefined());
        assertFalse(blacklistTextFile.getFirstBlock().getAtomSite().getCartnZ().isDefined());

        // text file with only some categories whitelisted
        CifFile whitelistTextFile = CifIO.readFromInputStream(new ByteArrayInputStream(CifIO.writeText(file, WHITELIST_OPTIONS)));
        assertTrue(whitelistTextFile.getFirstBlock().getCategoryNames().size() > 0);
        assertTrue(whitelistTextFile.getFirstBlock().getEntry().isDefined());
        assertTrue(whitelistTextFile.getFirstBlock().getAtomSite().isDefined());
        assertFalse(whitelistTextFile.getFirstBlock().getAtomSite().getGroupPDB().isDefined());
        assertTrue(whitelistTextFile.getFirstBlock().getAtomSite().getCartnX().isDefined());
        assertTrue(whitelistTextFile.getFirstBlock().getAtomSite().getCartnY().isDefined());
        assertTrue(whitelistTextFile.getFirstBlock().getAtomSite().getCartnZ().isDefined());

        // binary file with some categories blacklisted
        CifFile blacklistBinaryFile = CifIO.readFromInputStream(new ByteArrayInputStream(CifIO.writeBinary(file, BLACKLIST_OPTIONS)));
        assertTrue(blacklistBinaryFile.getFirstBlock().getCategoryNames().size() > 0);
        assertFalse(blacklistBinaryFile.getFirstBlock().getEntry().isDefined());
        assertTrue(blacklistBinaryFile.getFirstBlock().getAtomSite().isDefined());
        assertTrue(blacklistBinaryFile.getFirstBlock().getAtomSite().getGroupPDB().isDefined());
        assertFalse(blacklistBinaryFile.getFirstBlock().getAtomSite().getCartnX().isDefined());
        assertFalse(blacklistBinaryFile.getFirstBlock().getAtomSite().getCartnY().isDefined());
        assertFalse(blacklistBinaryFile.getFirstBlock().getAtomSite().getCartnZ().isDefined());

        // binary file with only some categories whitelisted
        CifFile whitelistBinaryFile = CifIO.readFromInputStream(new ByteArrayInputStream(CifIO.writeBinary(file, WHITELIST_OPTIONS)));
        assertTrue(whitelistBinaryFile.getFirstBlock().getCategoryNames().size() > 0);
        assertTrue(whitelistBinaryFile.getFirstBlock().getEntry().isDefined());
        assertTrue(whitelistBinaryFile.getFirstBlock().getAtomSite().isDefined());
        assertFalse(whitelistBinaryFile.getFirstBlock().getAtomSite().getGroupPDB().isDefined());
        assertTrue(whitelistBinaryFile.getFirstBlock().getAtomSite().getCartnX().isDefined());
        assertTrue(whitelistBinaryFile.getFirstBlock().getAtomSite().getCartnY().isDefined());
        assertTrue(whitelistBinaryFile.getFirstBlock().getAtomSite().getCartnZ().isDefined());
    }

    @Test
    public void testGzipWritingBehavior() throws IOException {
        // should wrap output in gzip if requested
        for (String id : TEST_CASES.keySet()) {
            testGzipWritingBehavior(id);
        }
    }

    private void testGzipWritingBehavior(String testCase) throws IOException {
        // check that file was loaded correctly
        CifFile file = CifIO.readFromInputStream(TestHelper.getInputStream("bcif/" + testCase + ".bcif"));
        assertEquals(testCase.toUpperCase(), file.getFirstBlock().getEntry().getId().get(0));

        // write text text with downstream gzip
        byte[] binaryGz = CifIO.writeText(file, CifOptions.builder().gzip(true).build());
        // magic number must be gzip
        assertEquals(GZIPInputStream.GZIP_MAGIC, (binaryGz[0] & 0xff | ((binaryGz[1] << 8) & 0xff00)));

        // write text text
        byte[] binary = CifIO.writeText(file, CifOptions.builder().gzip(false).build());
        // magic number must not be gzip
        assertNotEquals(GZIPInputStream.GZIP_MAGIC, (binary[0] & 0xff | ((binary[1] << 8) & 0xff00)));

        // write text text with downstream gzip
        byte[] textGz = CifIO.writeText(file, CifOptions.builder().gzip(true).build());
        // magic number must be gzip
        assertEquals(GZIPInputStream.GZIP_MAGIC, (textGz[0] & 0xff | ((textGz[1] << 8) & 0xff00)));

        // write text text
        byte[] text = CifIO.writeText(file, CifOptions.builder().gzip(false).build());
        // magic number must not be gzip
        assertNotEquals(GZIPInputStream.GZIP_MAGIC, (text[0] & 0xff | ((text[1] << 8) & 0xff00)));
    }
}
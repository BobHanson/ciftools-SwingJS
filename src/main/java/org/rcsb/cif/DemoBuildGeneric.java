package org.rcsb.cif;

import java.io.IOException;
import java.net.URL;

import org.rcsb.cif.generic.CifBlock;
import org.rcsb.cif.generic.CifBuilder;
import org.rcsb.cif.generic.CifFile;
import org.rcsb.cif.generic.CifIO;

public class DemoBuildGeneric {
    public static void main(String[] args) throws IOException {
        parseFile();
        System.out.println();
        buildModel();
    }

    private static void parseFile() throws IOException {
        String pdbId = "1acj";
        boolean parseBinary = true;

        // CIF and BinaryCIF are stored in the same data structure
        // to access the data, it does not matter where and in which format the data came from
        CifFile cifFile;
        if (parseBinary) {
            // parse binary CIF from PDBe
            cifFile = CifIO.readFromURL(new URL("https://www.ebi.ac.uk/pdbe/coordinates/" + pdbId + "/full?encoding=bcif"));
        } else {
            // parse CIF from RCSB PDB
            cifFile = CifIO.readFromURL(new URL("https://files.rcsb.org/download/" + pdbId + ".cif"));
        }

        // get first block of CIF
        CifBlock data = cifFile.getFirstBlock();

    }

    private static void buildModel() throws IOException {
        CifFile cifFile = new CifBuilder()
                // create a block
                .enterBlock("1EXP")
                // create a category with name 'entry'
                .enterCategory("entry")
                // set value of column 'id'
                .enterStrColumn("id")
                // to '1EXP'
                .add("1EXP")
                // leave current column and category
                .leaveColumn()
                .leaveCategory()
                .enterCategory("atom_site")
                .enterFloatColumn("cartn_x")
                .add(1.0, -2.4, 4.5)
                .markNextUnknown()
                .add(-3.14, 5.0)
                .leaveColumn()
                .enterFloatColumn("cartn_y")
                .add(0.0, -1.0, 2.72)
                .markNextNotPresent()
                .add(42, 100)
                .leaveColumn()
                .leaveCategory()
                .leaveBlock()
                .leaveFile();

        System.out.println(new String(CifIO.writeText(cifFile)));

        System.out.println(cifFile.getFirstBlock().getCategory("entry").getColumn("id").getStringData(0));
        cifFile.getFirstBlock()
                .getCategory("atom_site")
                .getFloatColumn("cartn_x")
                .values()
                .forEach(System.out::println);
    }
}

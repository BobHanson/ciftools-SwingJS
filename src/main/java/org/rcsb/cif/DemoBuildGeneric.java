package org.rcsb.cif;

import java.io.IOException;
import java.net.URL;

import org.rcsb.cif.generic.CifBlock;
import org.rcsb.cif.generic.CifBuilder;
import org.rcsb.cif.generic.CifFile;
import org.rcsb.cif.generic.CifIO;

public class DemoBuildGeneric {
    public static void main(String[] args) throws IOException {
        buildModel();
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

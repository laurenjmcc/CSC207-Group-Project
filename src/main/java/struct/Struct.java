package struct;

import org.biojava.nbio.structure.Structure;
import org.biojava.nbio.structure.StructureIO;
import org.biojava.nbio.structure.StructureTools;
import org.biojava.nbio.structure.align.gui.jmol.StructureAlignmentJmol;

public class Struct {
//    public static void main(String[] args) throws Exception {
//        Structure structure = StructureIO.getStructure("4HHB");
//        // and let's print out how many atoms are in this structure
//        System.out.println(StructureTools.getNrAtoms(structure));
//    }

    public static void main(String[] args) throws Exception {
        Structure struc = StructureIO.getStructure("2MW4");

        StructureAlignmentJmol jmolPanel = new StructureAlignmentJmol();

        jmolPanel.setStructure(struc);

        // send some commands to Jmol
        jmolPanel.evalString("select * ; color chain;");
        jmolPanel.evalString("select *; spacefill off; wireframe off; cartoon on;  ");
        jmolPanel.evalString("select ligands; cartoon off; wireframe 0.3; spacefill 0.5; color cpk;");
    }
}

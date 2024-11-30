package interface_adapter.structure;
import org.biojava.nbio.structure.Structure;
import org.biojava.nbio.structure.StructureIO;
import org.biojava.nbio.structure.StructureTools;
import org.biojava.nbio.structure.align.gui.jmol.StructureAlignmentJmol;
import org.biojava.nbio.structure.contact.StructureInterface;
import use_case.structure.StructureOutputBoundary;


public class StructurePresenter implements StructureOutputBoundary {

    public StructurePresenter() {
    }

    public void success(String pdbID) throws Exception{
        Structure struc = StructureIO.getStructure(pdbID);

        StructureAlignmentJmol jmolPanel = new StructureAlignmentJmol();

        jmolPanel.setStructure(struc);

        // send some commands to Jmol
        jmolPanel.evalString("select * ; color chain;");
        jmolPanel.evalString("select *; spacefill off; wireframe off; cartoon on;  ");
        jmolPanel.evalString("select ligands; cartoon off; wireframe 0.3; spacefill 0.5; color cpk;");

    }

}

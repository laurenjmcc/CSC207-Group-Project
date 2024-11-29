package API;

import org.biojava.nbio.structure.Structure;
import org.biojava.nbio.structure.gui.BiojavaJmol;

import javax.swing.*;

public class ProteinStructureViewer {
    public static void displayStructure(Structure structure) {
        BiojavaJmol jmolPanel = new BiojavaJmol();
        jmolPanel.setStructure(structure);

        JFrame frame = new JFrame("Protein Structure Viewer");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Depending on the BioJava version, use the appropriate method to get the component
        frame.add(jmolPanel.getFrame()); // Try getViewer(), getPanel(), or getFrame()

        frame.setSize(800, 600);
        frame.setVisible(true);
    }
}


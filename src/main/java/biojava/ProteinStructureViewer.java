package biojava;

import org.biojava.nbio.structure.Structure;
import org.biojava.nbio.structure.StructureIO;
import org.biojava.nbio.structure.gui.BiojavaJmol;

import javax.swing.*;
import java.awt.*;

public class ProteinStructureViewer {
    public void showStructure(String pdbId) {
        try {
            if (pdbId == null || pdbId.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Protein ID is empty.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Structure structure = StructureIO.getStructure(pdbId);

            if (structure == null) {
                JOptionPane.showMessageDialog(null, "No structure found for PDB ID: " + pdbId,
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            JFrame frame = new JFrame("Protein Structure: " + pdbId);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(800, 600);

            BiojavaJmol jmolPanel = new BiojavaJmol();
            jmolPanel.setStructure(structure);

            frame.getContentPane().add(jmolPanel.getFrame(), BorderLayout.CENTER);

            // Show the frame
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Error fetching or displaying the structure for PDB ID: " + pdbId,
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

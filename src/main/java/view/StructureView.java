package view;

import interface_adapter.change_password.LoggedInState;
import interface_adapter.structure.StructureController;
import interface_adapter.structure.StructurePresenter;
import interface_adapter.structure.StructureState;
import interface_adapter.structure.StructureViewModel;

import javax.swing.*;
import javax.swing.text.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class StructureView extends JPanel implements PropertyChangeListener, ActionListener {

    private final String viewName = "structure";
    private final StructureViewModel structureViewModel;

    private JLabel structureLabel;

    private StructureController structureController;
    private StructurePresenter structurePresenter;

    public StructureView(StructureViewModel structureViewModel) {

        this.structureViewModel = structureViewModel;
        this.structureViewModel.addPropertyChangeListener(this);

        JPanel innerContentPanel = new JPanel();
        innerContentPanel.setLayout(new BoxLayout(innerContentPanel, BoxLayout.X_AXIS));

        JLabel label1 = new JLabel("PDB ID: ");
        JTextArea  pdbIdText= new JTextArea();
        innerContentPanel.add(label1);
        innerContentPanel.add(pdbIdText);

        JPanel outerContentPanel = new JPanel();
        outerContentPanel.setLayout(new BoxLayout(outerContentPanel, BoxLayout.Y_AXIS));
        outerContentPanel.add(innerContentPanel);

        JButton buttonToViewStructure = new JButton("View Structure");
        outerContentPanel.add(buttonToViewStructure);


        this.add(outerContentPanel);

        buttonToViewStructure.addActionListener(
                evt -> {
                    structureController.execute();
                }
        );
    }
    /**
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final StructureState state = (StructureState) evt.getNewValue();
            structureLabel.setText(state.toString());

        }

    }
    public String getViewName() {return viewName;}

    /**
     * @param evt the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent evt) {System.out.println("Click " + evt.getActionCommand());

    }
}

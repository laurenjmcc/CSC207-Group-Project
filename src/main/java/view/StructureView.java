package view;

import interface_adapter.structure.StructureController;
import interface_adapter.structure.StructureState;
import interface_adapter.structure.StructureViewModel;
import interface_adapter.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class StructureView extends JPanel implements PropertyChangeListener, ActionListener {

    private final String viewName = "structure";
    private final StructureViewModel structureViewModel;
    private ViewManagerModel viewManagerModel; // Add reference to ViewManagerModel

    private JLabel structureLabel;

    private StructureController structureController;

    public StructureView(StructureViewModel structureViewModel) {
        this.structureViewModel = structureViewModel;
        this.structureViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Structure Screen");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setOpaque(true);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createVerticalStrut(30));
        this.add(title);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JPanel label1= new JPanel(new FlowLayout(FlowLayout.CENTER));
        label1.add(new JLabel("PDB ID: "));
        label1.setFont(new Font("Arial", Font.BOLD, 24));
        JTextField pdbIdText = new JTextField(15);
        pdbIdText.setPreferredSize(new Dimension(75, 25));
        label1.add(pdbIdText);
        this.add(Box.createVerticalStrut(80));
        this.add(label1);


        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton buttonToViewStructure = new JButton("View Structure");
        buttonToViewStructure.setFont(new Font("Arial", Font.PLAIN, 18));
        buttonPanel.add(buttonToViewStructure);

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 18));
        buttonPanel.add(backButton);

        this.add(buttonPanel);



        backButton.addActionListener(e -> handleBackButton());

        buttonToViewStructure.addActionListener(
                evt -> {
                    if (evt.getSource().equals(buttonToViewStructure)) {
                        StructureState structureState = structureViewModel.getState();
                        structureState.setPdbID(pdbIdText.getText());
                        String rawDataPdbID = structureState.getPdbID();
                        try {
                            structureController.execute(rawDataPdbID);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        );
    }

    /**
     * Handles the back button click event to navigate back to the Logged In View.
     */
    private void handleBackButton() {
            viewManagerModel.setState("logged in");
            viewManagerModel.firePropertyChanged();
    }

    /**
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final StructureState state = (StructureState) evt.getNewValue();
            if (structureLabel != null) {
                structureLabel.setText(state.toString());
            }
        }
    }

    public String getViewName() {
        return viewName;
    }

    /**
     * @param evt the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    public void setController(StructureController structureController) {
        this.structureController = structureController;
    }

    // Setter for ViewManagerModel to allow navigation control
    public void setViewManagerModel(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
    }
}


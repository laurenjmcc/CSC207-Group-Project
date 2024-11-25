package view;

import interface_adapter.analyze.AnalyzeController;
import interface_adapter.analyze.AnalyzePresenter;
import interface_adapter.analyze.AnalyzeState;
import interface_adapter.analyze.AnalyzeViewModel;
import interface_adapter.change_password.LoggedInState;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class AnalyzeView extends JPanel implements PropertyChangeListener {

    private final String viewName = "analyze";
    private final AnalyzeViewModel analyzeViewModel;

    private final JLabel protein; // comes from the textbox in the logged in view

    private final JLabel description;

    private final JLabel disease_string;

    private final JButton structure; // comes from the biojava api
    private final JButton more_info;
    private JButton BackButton = new JButton();
    private AnalyzePresenter analyzePresenter;
    private JButton disease;

    private AnalyzeController analyzeController;

    public AnalyzeView(AnalyzeViewModel analyzeViewModel) {

        this.analyzeViewModel = analyzeViewModel;
        this.analyzeViewModel.addPropertyChangeListener(this);


        protein = new JLabel("");
        // protein = new JLabel(the name of the protein goes here from the textbox);
        description = new JLabel("Description: ");
        disease_string = new JLabel("Click the Disease button to fetch disease information.");


        String protein_description_string = analyzeViewModel.getState().getProteinDescription();
        JLabel protein_description_label = new JLabel(protein_description_string);
        JPanel description_panel = new JPanel();
        description_panel.setLayout(new BoxLayout(description_panel, BoxLayout.X_AXIS));
        description_panel.add(description);
        description_panel.add(protein_description_label);



        final int[] currentIndex = {0};
        JPanel info_panel = new JPanel();
        info_panel.setLayout(new GridLayout(4, 2));
        info_panel.add(protein);
        info_panel.add(description_panel);
        info_panel.add(disease_string);


        final JPanel buttons = new JPanel();
        structure = new JButton("Structure");
        more_info = new JButton("More Information");
        disease = new JButton("Disease");
        BackButton = new JButton("Back");
        disease.addActionListener(e -> {
            ArrayList<String> protein_disease = analyzeViewModel.getState().getDisease();
            if (protein_disease != null && !protein_disease.isEmpty()) {
                disease_string.setText(protein_disease.get(currentIndex[0]));
                currentIndex[0] = (currentIndex[0] + 1) % protein_disease.size();
            } else {
                disease_string.setText("No disease information available. Run analyze first.");
            }
        });
        BackButton.addActionListener(e -> analyzePresenter.handleBackButton());
        buttons.add(structure);
        buttons.add(more_info);
        buttons.add(disease);
        buttons.add(BackButton);
        info_panel.add(buttons);




//        JPanel description_panel = new JPanel();
//        description_panel.setLayout(new BoxLayout(description_panel, BoxLayout.X_AXIS));
//        description_panel.add(description);
//        description_panel.add(protein_description_label);
//
//        JPanel disease_panel = new JPanel();
//        disease_panel.setLayout(new BoxLayout(disease_panel, BoxLayout.X_AXIS));
//        disease_panel.add(disease);
//        disease_panel.add(protein_disease_label);

        this.add(info_panel);
    }

    /**
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final AnalyzeState state = (AnalyzeState) evt.getNewValue();
            protein.setText(state.getProteinName());
        }
        else if (evt.getPropertyName().equals("password")) {
            final LoggedInState state = (LoggedInState) evt.getNewValue();
            JOptionPane.showMessageDialog(null, "password updated for " + state.getUsername());
        }
    }
    public void setAnalyzePresenter(AnalyzePresenter analyzePresenter) {
        this.analyzePresenter = analyzePresenter;
    }


    public String getViewName() {return viewName;}
}


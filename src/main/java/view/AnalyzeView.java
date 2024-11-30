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

        System.out.println("Hello");
        JLabel protein_description_label = new JLabel("Click Description Button to get the Description of the Protein");
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
        more_info = new JButton("More Information");
        more_info.setFont(new Font("Arial", Font.PLAIN, 18));
        disease = new JButton("Disease");
        disease.setFont(new Font("Arial", Font.PLAIN, 18));
        BackButton = new JButton("Back");
        BackButton.setFont(new Font("Arial", Font.PLAIN, 18));
        JButton descriptionButton = new JButton("Description");
        descriptionButton.setFont(new Font("Arial", Font.PLAIN, 18));

        descriptionButton.addActionListener(e -> {

            String protein_description_string = analyzeViewModel.getState().getProteinDescription();
            protein_description_label.setText("<html><div style='width:1000px;'>" + protein_description_string + "</div></html>");
        });

        disease.addActionListener(e -> {
            ArrayList<String> protein_disease = analyzeViewModel.getState().getDisease();
            if (protein_disease != null && !protein_disease.isEmpty()) {
                // Display the current disease
                disease_string.setText(protein_disease.get(currentIndex[0]));
                currentIndex[0]++;
                if (currentIndex[0] >= protein_disease.size()) {
                    currentIndex[0] = 0; // Reset the index
                    StringBuilder diseasesList = new StringBuilder("Diseases associated with this protein:\n");
                    for (String disease : protein_disease) {
                        diseasesList.append("- ").append(disease).append("\n");
                    }
                    JOptionPane.showMessageDialog(this, diseasesList.toString(), "Disease List", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                disease_string.setText("No disease information available. Run analyze first.");
            }
        });

        BackButton.addActionListener(e -> analyzePresenter.handleBackButton());

        buttons.add(more_info);
        buttons.add(disease);
        buttons.add(descriptionButton);
        buttons.add(BackButton);
        info_panel.add(buttons);



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



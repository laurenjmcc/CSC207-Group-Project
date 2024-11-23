package view;

import interface_adapter.analyze.AnalyzeController;
import interface_adapter.analyze.AnalyzePresenter;
import interface_adapter.analyze.AnalyzeState;
import interface_adapter.analyze.AnalyzeViewModel;
import interface_adapter.change_password.LoggedInState;
import interface_adapter.change_password.LoggedInViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.json.JSONObject;
import org.json.JSONArray;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import biojava.ProteinStructureViewer;

public class AnalyzeView extends JPanel implements PropertyChangeListener {

    private final String viewName = "analyze";
    private final AnalyzeViewModel analyzeViewModel;

    private final JLabel protein; // comes from the textbox in the logged in view

    private final JLabel description;

    private final JLabel disease;

    private final JButton structure; // comes from the biojava api
    private final JButton more_info;
    private JButton BackButton = new JButton();
    private AnalyzePresenter analyzePresenter;

    private AnalyzeController analyzeController;

    public AnalyzeView(AnalyzeViewModel analyzeViewModel) {

        this.analyzeViewModel = analyzeViewModel;
        this.analyzeViewModel.addPropertyChangeListener(this);


        protein = new JLabel("");
        // protein = new JLabel(the name of the protein goes here from the textbox);
        description = new JLabel("Description: ");
        disease = new JLabel("Disease: ");


        String protein_description_string = analyzeViewModel.getState().getProteinDescription();
        JLabel protein_description_label = new JLabel("Description from the API goes in place of this text");
        JPanel description_panel = new JPanel();
        description_panel.setLayout(new BoxLayout(description_panel, BoxLayout.X_AXIS));
        description_panel.add(description);
        description_panel.add(protein_description_label);

        String protein_disease_string = analyzeViewModel.getState().getProteinDisease();
        JLabel protein_disease_label = new JLabel("Disease information from the API goes in place of this text");
        JPanel disease_panel = new JPanel();
        disease_panel.setLayout(new BoxLayout(disease_panel, BoxLayout.X_AXIS));
        disease_panel.add(disease);
        disease_panel.add(protein_disease_label);

        JPanel info_panel = new JPanel();
        info_panel.setLayout(new GridLayout(4, 2));
        info_panel.add(protein);
        info_panel.add(description_panel);
        info_panel.add(disease_panel);

        final JPanel buttons = new JPanel();
        structure = new JButton("Structure");
        more_info = new JButton("More Information");
        BackButton = new JButton("Back");
        BackButton.addActionListener(e -> analyzePresenter.handleBackButton());
        buttons.add(structure);
        buttons.add(more_info);
        buttons.add(BackButton);

        info_panel.add(buttons);

        addStructureButtonListener();


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

    private void addStructureButtonListener() {
        structure.addActionListener(evt -> {
            String proteinName = analyzeViewModel.getState().getProteinName();

            if (proteinName == null || proteinName.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Protein name is empty.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String pdbId = mapProteinNameToPdbID(proteinName);

            if (pdbId != null) {
                ProteinStructureViewer viewer = new ProteinStructureViewer();
                viewer.showStructure(pdbId);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Could not find a PDB ID for protein: " + proteinName,
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    /**
     * Maps a protein name to a PDB ID using the RCSB PDB API.
     *
     * @param proteinName The name of the protein.
     * @return The corresponding PDB ID, or null if not found.
     */
    private String mapProteinNameToPdbID(String proteinName) {
        try {
            OkHttpClient client = new OkHttpClient();
            String url = "https://search.rcsb.org/rcsbsearch/v1/query?json=" +
                    "{\"query\":{\"type\":\"terminal\",\"service\":\"full_text\",\"parameters\":{\"value\":\"" +
                    proteinName + "\"}},\"request_options\":{\"return_all_hits\":true},\"return_type\":\"entry\"}";

            Request request = new Request.Builder().url(url).build();

            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();

            JSONObject json = new JSONObject(responseBody);
            JSONArray resultSet = json.getJSONArray("result_set");

            if (resultSet.length() > 0) {
                JSONObject firstResult = resultSet.getJSONObject(0);
                String pdbId = firstResult.getJSONObject("identifier").getString("value");
                return pdbId.toUpperCase();
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error mapping protein name to PDB ID.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
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


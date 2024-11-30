package view;

import entity.AnalysisResult;
import interface_adapter.past_result.PastResultController;
import interface_adapter.past_result.PastResultState;
import interface_adapter.past_result.PastResultViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/**
 * View for displaying past analysis results.
 */
public class PastResultView extends JPanel implements PropertyChangeListener {

    public static final String VIEW_NAME = "PastResultView";
    private final PastResultViewModel pastResultViewModel;
    private PastResultController pastResultController;

    public PastResultView(PastResultViewModel pastResultViewModel) {
        this.pastResultViewModel = pastResultViewModel;
        this.pastResultViewModel.addPropertyChangeListener(this);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        initializeComponents();
    }

    /**
     * Method to set the PastResultController.
     * @param pastResultController the controller to be set.
     */
    public void setPastResultController(PastResultController pastResultController) {
        this.pastResultController = pastResultController;
    }

    private void initializeComponents() {
        this.removeAll();

        JLabel headerLabel = new JLabel("Past Results");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(headerLabel);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(scrollPane);

        JButton backButton = new JButton("Back");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(backEvt -> {
            if (pastResultController != null) {
                CardLayout cardLayout = (CardLayout) this.getParent().getLayout();
                cardLayout.show(this.getParent(), "logged in");
            }
        });
        this.add(Box.createVerticalStrut(10));
        this.add(backButton);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            final PastResultState state = (PastResultState) evt.getNewValue();

            this.removeAll();
            initializeComponents();

            JScrollPane scrollPane = (JScrollPane) this.getComponent(1);
            JPanel resultsPanel = new JPanel();
            resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));
            scrollPane.setViewportView(resultsPanel);

            List<AnalysisResult> analysisResults = state.getAnalysisResults();

            if (analysisResults.isEmpty()) {
                JLabel noResultsLabel = new JLabel("No Past Results Available.");
                noResultsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                resultsPanel.add(noResultsLabel);
            } else {
                // Display each analysis result
                for (AnalysisResult result : analysisResults) {
                    JPanel resultPanel = createResultPanel(result);
                    resultsPanel.add(resultPanel);
                    resultsPanel.add(Box.createVerticalStrut(10));
                }
            }

            revalidate();
            repaint();
        }
    }

    private JPanel createResultPanel(AnalysisResult result) {
        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));
        resultPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        resultPanel.setBackground(Color.WHITE);
        resultPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Username and date
        String username = result.getUsername();
        String date = result.getAnalysisDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        JLabel userLabel = new JLabel("User: " + username + "   Date: " + date);
        userLabel.setFont(new Font("Arial", Font.BOLD, 14));
        userLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Protein name
        String proteinName = result.getProteinName();
        JLabel proteinLabel = new JLabel("Protein: " + proteinName);
        proteinLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        proteinLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Analysis data
        Map<String, Object> analysisData = result.getAnalysisData();
        String description = (String) analysisData.get("proteinDescription");
        String formattedDescription = description.replaceAll("\\.", ".\n");

        JTextArea descriptionArea = new JTextArea();
        descriptionArea.setText("Description:\n" + formattedDescription);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setEditable(false);
        descriptionArea.setBackground(Color.WHITE);
        descriptionArea.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Add components to result panel
        resultPanel.add(userLabel);
        resultPanel.add(proteinLabel);
        resultPanel.add(descriptionArea);

        return resultPanel;
    }

    /**
     * Returns the name of the view.
     * @return the view name
     */
    public String getViewName() {
        return pastResultViewModel.getViewName();
    }
}

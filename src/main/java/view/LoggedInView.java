package view;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.change_password.LoggedInState;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.past_result.PastResultController;

public class LoggedInView extends JPanel implements PropertyChangeListener {

    private final String viewName = "logged in";
    private final LoggedInViewModel loggedInViewModel;
    private final JLabel passwordErrorField = new JLabel();
    private ChangePasswordController changePasswordController;

    private final JLabel username;

    private final JButton analyzeButton;

    private final JTextField proteinInputField = new JTextField(15);
    private final JButton pastResultButton;
    private PastResultController pastResultController;

    private boolean hasAnalyzed = false; // Track if analysis has occurred
    private String analyzedProtein = ""; // Store the analyzed protein

    public LoggedInView(LoggedInViewModel loggedInViewModel) {
        this.loggedInViewModel = loggedInViewModel;
        this.loggedInViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Logged In Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel proteinInfo = new LabelTextPanel(
                new JLabel("Protein Name"), proteinInputField);

        final JLabel usernameInfo = new JLabel("Currently logged in: ");
        username = new JLabel();

        final JPanel buttons = new JPanel();
        analyzeButton = new JButton("Analyze");
        buttons.add(analyzeButton);

        pastResultButton = new JButton("Past Result");
        buttons.add(pastResultButton);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Add ActionListener for "Analyze" button
        analyzeButton.addActionListener(evt -> handleAnalyzeAction());

        // Add ActionListener for "Past Result" button
        pastResultButton.addActionListener(evt -> handlePastResultAction());

        // Add components to the layout
        this.add(title);
        this.add(usernameInfo);
        this.add(username);
        this.add(proteinInfo);
        this.add(passwordErrorField);
        this.add(buttons);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final LoggedInState state = (LoggedInState) evt.getNewValue();
            username.setText(state.getUsername());
        } else if (evt.getPropertyName().equals("password")) {
            final LoggedInState state = (LoggedInState) evt.getNewValue();
            JOptionPane.showMessageDialog(null, "Password updated for " + state.getUsername());
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setChangePasswordController(ChangePasswordController changePasswordController) {
        this.changePasswordController = changePasswordController;
    }

    public void setPastResultController(PastResultController pastResultController) {
        this.pastResultController = pastResultController;
    }

    private void handleAnalyzeAction() {
        String proteinName = proteinInputField.getText();

        if (proteinName == null || proteinName.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a protein name before analyzing.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        try {
            // Simulate analysis success
            JOptionPane.showMessageDialog(
                    this,
                    "Analysis completed for protein: " + proteinName,
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            // Mark as analyzed
            hasAnalyzed = true;
            analyzedProtein = proteinName;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "An error occurred during analysis: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void handlePastResultAction() {
        CardLayout cardLayout = (CardLayout) this.getParent().getLayout();

        if (!hasAnalyzed) {
            // Show "No Past Result" view
            JPanel noResultPanel = createNoResultPanel();
            this.getParent().add(noResultPanel, "NoResultView");
            cardLayout.show(this.getParent(), "NoResultView");
        } else {
            try {
                // Execute the Past Result use case via the controller
                pastResultController.execute(analyzedProtein);

                // Switch to the Past Result View
                cardLayout.show(this.getParent(), "PastResultView");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "An error occurred while fetching past results: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    private JPanel createNoResultPanel() {
        JPanel noResultPanel = new JPanel();
        noResultPanel.setLayout(new BoxLayout(noResultPanel, BoxLayout.Y_AXIS));

        JLabel noResultLabel = new JLabel("No Past Result");
        noResultLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton backButton = new JButton("Back");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(backEvt -> {
            CardLayout cardLayout = (CardLayout) this.getParent().getLayout();
            cardLayout.show(this.getParent(), "logged in");
        });

        noResultPanel.add(noResultLabel);
        noResultPanel.add(Box.createVerticalStrut(10)); // Add spacing
        noResultPanel.add(backButton);

        return noResultPanel;
    }
}
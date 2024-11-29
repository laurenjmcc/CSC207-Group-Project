package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.analyze.AnalyzeController;

import interface_adapter.ViewManagerModel;
import interface_adapter.ViewModel;

import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.change_password.LoggedInState;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.login.LoginState;
import interface_adapter.logout.LogoutController;
import interface_adapter.past_result.PastResultController;


/**
 * The View for when the user is logged into the program.
 */
public class LoggedInView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "logged in";
    private final LoggedInViewModel loggedInViewModel;
    private final JLabel passwordErrorField = new JLabel();

    private ChangePasswordController changePasswordController;
    private LogoutController logoutController;

    private AnalyzeController analyzeController;


    private ViewManagerModel viewManagerModel;

    private final JLabel username;

    private final JButton logOut;

    private final JTextField proteinInputField = new JTextField(15);

    private final JButton analyze;

    private final JTextField passwordInputField = new JTextField(15);
    private final JButton changePassword;
    private PastResultController pastResultController;
    private boolean hasAnalyzed = false; // Track if analysis has occurred
    private String analyzedProtein = ""; // Store the analyzed protein

    public LoggedInView(LoggedInViewModel loggedInViewModel) {
        this.loggedInViewModel = loggedInViewModel;
        this.loggedInViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Logged In Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel("Password"), passwordInputField);

        final LabelTextPanel proteinInfo = new LabelTextPanel(
                new JLabel("Protein"), proteinInputField
        );

        final JLabel usernameInfo = new JLabel("Currently logged in: ");
        username = new JLabel();

        final JPanel buttons = new JPanel();
        logOut = new JButton("Log Out");
        buttons.add(logOut);

        JButton pastResultButton = new JButton("Past Result");
        buttons.add(pastResultButton);

        analyze = new JButton("Analyze");
        buttons.add(analyze);


        changePassword = new JButton("Change Password");
        buttons.add(changePassword);

        pastResultButton.addActionListener(evt -> handlePastResultAction());

        final JButton createTeamButton = new JButton("Create Team");
        buttons.add(createTeamButton);

        createTeamButton.addActionListener(evt -> {
            if (evt.getSource().equals(createTeamButton)) {
                if (viewManagerModel != null) {
                    viewManagerModel.setState("create team");
                    viewManagerModel.firePropertyChanged();
                } else {
                    JOptionPane.showMessageDialog(this, "Error: ViewManagerModel not initialized.");
                }
            }
        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        passwordInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final LoggedInState currentState = loggedInViewModel.getState();
                currentState.setPassword(passwordInputField.getText());
                loggedInViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

        changePassword.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(changePassword)) {
                        final LoggedInState currentState = loggedInViewModel.getState();

                        this.changePasswordController.execute(
                                currentState.getUsername(),
                                currentState.getPassword()
                        );
                    }
                }
        );

        logOut.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(logOut)) {
                        final LoggedInState currentState = loggedInViewModel.getState();
                        logoutController.execute(currentState.getUsername());

                        // TODO: execute the logout use case through the Controller
                        // 1. get the state out of the loggedInViewModel. It contains the username.
                        // 2. Execute the logout Controller.
                    }
                }
        );

        proteinInputField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
                final LoggedInState currentState = loggedInViewModel.getState();
                currentState.setProteinname(proteinInputField.getText());
                loggedInViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

        analyze.addActionListener(
                evt -> {
                    if (evt.getSource().equals(analyze)) {
                        final LoggedInState currentState = loggedInViewModel.getState();
                        String proteinName = currentState.getProteinname();

                        if (proteinName == null || proteinName.trim().isEmpty()) {
                            JOptionPane.showMessageDialog(
                                    this,
                                    "Please enter a protein name before analyzing.",
                                    "Input Error",
                                    JOptionPane.WARNING_MESSAGE
                            );
                        } else {
                            try {
                                analyzeController.execute(proteinName);
                                hasAnalyzed = true; // Mark as analyzed
                                analyzedProtein = proteinName; // Store the analyzed protein
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(
                                        this,
                                        "An error occurred during analysis: " + e.getMessage(),
                                        "Analysis Error",
                                        JOptionPane.ERROR_MESSAGE
                                );
                            }
                        }
                    }
                }
        );

        final JButton structureButton = new JButton("Structure");
        buttons.add(structureButton);

        structureButton.addActionListener(evt -> {
            if (evt.getSource().equals(structureButton)) {
                analyzeController.setViewManagerModel(viewManagerModel);
                analyzeController.switchToStructureView();
            }
        });

        this.add(title);
        this.add(usernameInfo);
        this.add(username);

        this.add(passwordInfo);
        this.add(passwordErrorField);

        this.add(proteinInfo);

        this.add(buttons);




    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final LoggedInState state = (LoggedInState) evt.getNewValue();
            username.setText(state.getUsername());
        } else if (evt.getPropertyName().equals("password")) {
            final LoggedInState state = (LoggedInState) evt.getNewValue();
            JOptionPane.showMessageDialog(null, "password updated for " + state.getUsername());
        }

    }

    /**
     * React to a button click that results in evt.
     *
     * @param evt the ActionEvent to react to
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    public String getViewName() {
        return viewName;
    }

    public void setChangePasswordController(ChangePasswordController changePasswordController) {
        this.changePasswordController = changePasswordController;
    }

    public void setViewManagerModel(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
    }

    public void setLogoutController(LogoutController logoutController) {
        this.logoutController = logoutController;
    }

    public void setAnalyzeController(AnalyzeController analyzeController) {
        this.analyzeController = analyzeController;
    }
    public void setPastResultController(PastResultController pastResultController) {
        this.pastResultController = pastResultController;
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
                if (pastResultController != null) {
                    pastResultController.execute(analyzedProtein);
                    cardLayout.show(this.getParent(), "PastResultView");
                }
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

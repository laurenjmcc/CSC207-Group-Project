package view;

import interface_adapter.team.CreateTeamController;
import interface_adapter.team.CreateTeamState;
import interface_adapter.team.CreateTeamViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;

/**
 * View for creating a new team.
 */
public class CreateTeamView extends JPanel implements PropertyChangeListener {
    private final CreateTeamViewModel viewModel;
    private CreateTeamController controller;

    private JTextField teamNameField;
    private JTextField membersField; // Comma-separated usernames
    private JLabel messageLabel;
    private JButton createButton;
    private JButton cancelButton;

    public CreateTeamView(CreateTeamViewModel viewModel) {
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);
        initializeComponents();
    }

    private void initializeComponents() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Create New Team");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(titleLabel);

        teamNameField = new JTextField(20);
        membersField = new JTextField(20);

        this.add(new JLabel("Team Name:"));
        this.add(teamNameField);

        this.add(new JLabel("Team Members (comma-separated usernames):"));
        this.add(membersField);

        messageLabel = new JLabel();
        messageLabel.setForeground(Color.RED);
        this.add(messageLabel);

        JPanel buttonPanel = new JPanel();
        createButton = new JButton("Create Team");
        cancelButton = new JButton("Cancel");

        buttonPanel.add(createButton);
        buttonPanel.add(cancelButton);

        this.add(buttonPanel);

        createButton.addActionListener(e -> {
            String teamName = teamNameField.getText().trim();
            String membersText = membersField.getText().trim();
            String[] memberUsernames = membersText.split("\\s*,\\s*"); // Split by commas and trim spaces
            String creatorUsername = viewModel.getCurrentUsername();

            controller.createTeam(teamName, Arrays.asList(memberUsernames), creatorUsername);
        });

        cancelButton.addActionListener(e -> {
            viewModel.getViewManagerModel().setState("logged in");
            viewModel.getViewManagerModel().firePropertyChanged();
        });
    }

    public void setController(CreateTeamController controller) {
        this.controller = controller;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            CreateTeamState state = (CreateTeamState) evt.getNewValue();
            if (state.getErrorMessage() != null) {
                messageLabel.setText(state.getErrorMessage());
            } else if (state.getMessage() != null) {
                messageLabel.setForeground(Color.GREEN);
                messageLabel.setText(state.getMessage());
                teamNameField.setText("");
                membersField.setText("");
            }
        }
    }

    public String getViewName() {
        return viewModel.getViewName();
    }
}

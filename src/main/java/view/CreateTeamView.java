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
        titleLabel.setFont(new Font("Arial",Font.BOLD, 20));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createVerticalStrut(30));
        this.add(titleLabel);


        JPanel teamNamePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        teamNamePanel.add(new JLabel("Team Name:"));
        teamNamePanel.setFont(new Font("Arial", Font.PLAIN, 20));
        teamNameField = new JTextField(30);
        teamNameField.setPreferredSize(new Dimension(300, 50));
        teamNamePanel.add(teamNameField);
        this.add(Box.createVerticalStrut(80));
        this.add(teamNamePanel);


        JPanel membersPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        membersPanel.add(new JLabel("Team Members:"));
        membersPanel.setFont(new Font("Arial", Font.PLAIN, 20));
        membersField = new JTextField(30);
        membersField.setPreferredSize(new Dimension(300, 50));
        membersPanel.add(membersField);
        this.add(Box.createVerticalStrut(10));
        this.add(membersPanel);

        messageLabel = new JLabel();
        messageLabel.setForeground(Color.RED);
        this.add(messageLabel);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        createButton = new JButton("Create Team");
        createButton.setFont(new Font("Arial", Font.PLAIN, 18));
        cancelButton = new JButton("Back");
        cancelButton.setFont(new Font("Arial", Font.PLAIN, 18));
        buttonPanel.add(createButton);
        buttonPanel.add(cancelButton);
        this.add(Box.createVerticalStrut(10));
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
                JOptionPane.showMessageDialog(
                        this,
                        state.getErrorMessage(),
                        "Team Creation Failed",
                        JOptionPane.ERROR_MESSAGE
                );
            } else if (state.getMessage() != null) {
                JOptionPane.showMessageDialog(
                        this,
                        state.getMessage(),
                        "Team Created",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        }
    }

    public String getViewName() {
        return viewModel.getViewName();
    }
}

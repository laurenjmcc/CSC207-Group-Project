package view;

import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The View for changing passwords.
 */
public class ChangePasswordView extends JPanel implements ActionListener {

    public static final String VIEW_NAME = "ChangePasswordView";

    private ViewManagerModel viewManagerModel;
    private ChangePasswordController changePasswordController;

    private final JPasswordField oldPasswordField = new JPasswordField(15);
    private final JPasswordField newPasswordField = new JPasswordField(15);
    private final JPasswordField confirmPasswordField = new JPasswordField(15);

    private final JButton submitButton;
    private final JButton cancelButton;

    public ChangePasswordView() {
        JLabel titleLabel = new JLabel("Change Password");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel oldPasswordPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        JLabel oldPasswordLabel = new JLabel("Old Password:");
        oldPasswordLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        oldPasswordField.setFont(new Font("Arial", Font.PLAIN, 15));
        oldPasswordPanel.add(oldPasswordLabel);
        oldPasswordPanel.add(oldPasswordField);

        JPanel newPasswordPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        JLabel newPasswordLabel = new JLabel("New Password:");
        newPasswordLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        newPasswordField.setFont(new Font("Arial", Font.PLAIN, 15));
        newPasswordPanel.add(newPasswordLabel);
        newPasswordPanel.add(newPasswordField);

        JPanel confirmPasswordPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        confirmPasswordField.setFont(new Font("Arial", Font.PLAIN, 15));
        confirmPasswordPanel.add(confirmPasswordLabel);
        confirmPasswordPanel.add(confirmPasswordField);

        submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Arial", Font.PLAIN, 18));
        submitButton.addActionListener(this);

        cancelButton = new JButton("Back");
        cancelButton.setFont(new Font("Arial", Font.PLAIN, 18));
        cancelButton.addActionListener(e -> handleCancelAction());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(submitButton);
        buttonPanel.add(cancelButton);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createVerticalStrut(30));
        this.add(titleLabel);
        this.add(Box.createVerticalStrut(50));
        this.add(oldPasswordPanel);
        this.add(newPasswordPanel);
        this.add(confirmPasswordPanel);
        this.add(buttonPanel);
    }

    private void handleCancelAction() {
        if (viewManagerModel != null) {
            viewManagerModel.setState("logged in");
            viewManagerModel.firePropertyChanged();
        } else {
            JOptionPane.showMessageDialog(this, "Error: ViewManagerModel not initialized.");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            String oldPassword = new String(oldPasswordField.getPassword());
            String newPassword = new String(newPasswordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());

            if (changePasswordController != null) {
                changePasswordController.execute(oldPassword, newPassword, confirmPassword);
                // After successful change, switch back to logged-in view
                viewManagerModel.setState("logged in");
                viewManagerModel.firePropertyChanged();
            } else {
                JOptionPane.showMessageDialog(this, "Error: ChangePasswordController not initialized.");
            }
        }
    }

    public void setViewManagerModel(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
    }

    public void setChangePasswordController(ChangePasswordController changePasswordController) {
        this.changePasswordController = changePasswordController;
    }
}

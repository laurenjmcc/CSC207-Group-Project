package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;

/**
 * The View for the Signup Use Case.
 */
public class SignupView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "Sign up";

    private final SignupViewModel signupViewModel;
    private final JTextField usernameInputField = new JTextField(15);
    private final JPasswordField passwordInputField = new JPasswordField(15);
    private final JPasswordField repeatPasswordInputField = new JPasswordField(15);
    private SignupController signupController;

    private final JButton cancel;
    private final JButton tosignup;

    public SignupView(SignupViewModel signupViewModel) {
        this.signupViewModel = signupViewModel;
        signupViewModel.addPropertyChangeListener(this);


        final JLabel title = new JLabel(SignupViewModel.TITLE_LABEL);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setOpaque(true); // Set background color to off-white beige
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel usernamePanel = new JPanel();
        usernamePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        usernamePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        usernameInputField.setFont(new Font("Arial", Font.PLAIN, 15));
        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameInputField);

        JPanel passwordPanel = new JPanel();
        passwordPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel passwordLabel = new JLabel("Password:");
        passwordPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 20));
        passwordInputField.setFont(new Font("Arial", Font.PLAIN, 15));
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordInputField);

        JPanel repeat_passwordPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        JLabel repeat_passwordLabel = new JLabel("Repeat Password:");
        repeat_passwordLabel.setFont(new Font("Arial", Font.BOLD, 20));
        repeatPasswordInputField.setFont(new Font("Arial", Font.PLAIN, 15));
        repeat_passwordPanel.add(repeat_passwordLabel);
        repeat_passwordPanel.add(repeatPasswordInputField);

        final JPanel buttons = new JPanel();
        tosignup = new JButton(SignupViewModel.TO_SIGNUP_BUTTON_LABEL);
        tosignup.setFont(new Font("Arial", Font.PLAIN, 18));
        buttons.add(tosignup);

        cancel = new JButton(SignupViewModel.CANCEL_BUTTON_LABEL);
        cancel.setFont(new Font("Arial", Font.PLAIN, 18));
        buttons.add(cancel);

        tosignup.addActionListener(e -> signupController.execute(
                usernameInputField.getText(),
                new String(passwordInputField.getPassword()),
                new String(repeatPasswordInputField.getPassword())
        ));
        cancel.addActionListener(e -> signupController.switchToLoginView());

        cancel.addActionListener(this);

        addUsernameListener();
        addPasswordListener();
        addRepeatPasswordListener();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createVerticalStrut(30)); // Add space at the top to match LoginView

        this.add(title);
        this.add(Box.createVerticalStrut(90)); // Add space between title and input fields to match LoginView
        this.add(usernamePanel);
        this.add(passwordPanel);
        this.add(Box.createVerticalStrut(1)); // Add minimal space between fields to match LoginView
        this.add(repeat_passwordPanel);
        this.add(buttons);
        this.add(Box.createVerticalStrut(-1)); // Add space between input fields and buttons to match LoginView
    }

    private void addUsernameListener() {
        usernameInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final SignupState currentState = signupViewModel.getState();
                currentState.setUsername(usernameInputField.getText());
                signupViewModel.setState(currentState);
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
    }

    private void addPasswordListener() {
        passwordInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final SignupState currentState = signupViewModel.getState();
                currentState.setPassword(new String(passwordInputField.getPassword()));
                signupViewModel.setState(currentState);
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
    }

    private void addRepeatPasswordListener() {
        repeatPasswordInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final SignupState currentState = signupViewModel.getState();
                currentState.setRepeatPassword(new String(repeatPasswordInputField.getPassword()));
                signupViewModel.setState(currentState);
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
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final SignupState state = (SignupState) evt.getNewValue();
        if (state.getUsernameError() != null) {
            JOptionPane.showMessageDialog(this, state.getUsernameError());
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setSignupController(SignupController controller) {
        this.signupController = controller;
    }
}

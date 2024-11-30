package view;

import interface_adapter.login.LoginController;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JPanel implements ActionListener {

    private final JTextField usernameInputField = new JTextField(15);
    private final JPasswordField passwordInputField = new JPasswordField(15);
    private final JLabel usernameErrorField = new JLabel();
    private final JLabel passwordErrorField = new JLabel();
    private final JButton logIn;
    private final JButton createAccount;
    private final JButton cancel;
    private LoginController loginController;
    private final LoginViewModel loginViewModel;

    public LoginView(LoginViewModel loginViewModel) {
        this.loginViewModel = loginViewModel;
        this.setOpaque(false);
        this.loginViewModel.addPropertyChangeListener(evt -> {
            // You can add handling here if needed for property changes
        });
        JLabel title = new JLabel("Login Screen");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Username and password input panels
        JPanel usernamePanel = new JPanel();
        usernamePanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        usernameInputField.setFont(new Font("Arial", Font.PLAIN, 15));
        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameInputField);

        JPanel passwordPanel = new JPanel();
        passwordPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        passwordInputField.setFont(new Font("Arial", Font.PLAIN, 15));
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordInputField);


        // Buttons
        logIn = new JButton("Log In");
        logIn.setFont(new Font("Arial", Font.PLAIN, 18));
        createAccount = new JButton("Create Account");
        createAccount.setFont(new Font("Arial", Font.PLAIN, 18));
        cancel = new JButton("Cancel");
        cancel.setFont(new Font("Arial", Font.PLAIN, 18));



        JPanel buttons = new JPanel();
        buttons.add(logIn);
        buttons.add(createAccount);
        buttons.add(cancel);


        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createVerticalStrut(30)); // Add some space at the top
        this.add(title);
        this.add(Box.createVerticalStrut(90)); // Reduce space between title and input fields
        this.add(usernamePanel);
        passwordPanel.setAlignmentY(Component.TOP_ALIGNMENT);
        this.add(Box.createVerticalStrut(1));
        this.add(passwordPanel);
        this.add(usernameErrorField);
        this.add(passwordErrorField);
        this.add(Box.createVerticalStrut(-1)); // Space between input fields and buttons
        this.add(buttons);


        logIn.addActionListener(e -> {
            LoginState currentState = loginViewModel.getState();
            loginController.execute(currentState.getUsername(), currentState.getPassword());
        });

        createAccount.addActionListener(e -> {
            loginController.switchToSignupView();  // Switch to SignupView
        });

        cancel.addActionListener(this);


        usernameInputField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
                loginViewModel.getState().setUsername(usernameInputField.getText());
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

        passwordInputField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
                loginViewModel.getState().setPassword(new String(passwordInputField.getPassword()));
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
        if (evt.getSource() == cancel) {
            System.exit(0);  // Exit the application on cancel
        }
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }
}

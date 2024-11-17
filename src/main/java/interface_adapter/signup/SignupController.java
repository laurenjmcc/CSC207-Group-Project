package interface_adapter.signup;

import interface_adapter.ViewManagerModel;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInputData;

import javax.swing.*;
import java.util.concurrent.Executor;

/**
 * Controller for the Signup Use Case.
 */
public class SignupController {

    private final SignupInputBoundary userSignupUseCaseInteractor;
    private final ViewManagerModel viewManagerModel;

    public SignupController(SignupInputBoundary userSignupUseCaseInteractor, ViewManagerModel viewManagerModel) {
        this.userSignupUseCaseInteractor = userSignupUseCaseInteractor;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Executes the Signup Use Case.
     * @param username the username to sign up
     * @param password1 the password
     * @param password2 the password repeated
     */
    public void execute(String username, String password1, String password2) {
        if (username.trim().isEmpty() || password1.trim().isEmpty() || password2.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "All fields are required. Please fill out all fields.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (password1.equals(password2)) {
            // Attempt to sign up the user
            SignupInputData signupInputData = new SignupInputData(username, password1, password2);

            // Pass the SignupInputData object to the interactor
            userSignupUseCaseInteractor.execute(signupInputData);
            // After successful sign-up, switch to login view
            viewManagerModel.setState("login");
            viewManagerModel.firePropertyChanged();
        } else {
            // Show an error dialog if passwords do not match
            JOptionPane.showMessageDialog
                    (null, "Passwords do not match!", "Error",
                            JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Executes the "switch to LoginView" Use Case.
     */
    public void switchToLoginView() {
        viewManagerModel.setState("login");
        viewManagerModel.firePropertyChanged();
    }
}

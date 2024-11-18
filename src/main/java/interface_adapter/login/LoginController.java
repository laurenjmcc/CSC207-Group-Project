package interface_adapter.login;

import interface_adapter.ViewManagerModel;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;
import use_case.login.LoginUserDataAccessInterface;

import javax.swing.*;

/**
 * The controller for the Login Use Case.
 */
public class LoginController {

    private final LoginInputBoundary loginUseCaseInteractor;
    private final LoginUserDataAccessInterface userDataAccess;
    private ViewManagerModel viewManagerModel;

    public LoginController(LoginInputBoundary loginUseCaseInteractor, ViewManagerModel viewManagerModel,
                           LoginUserDataAccessInterface userDataAccess) {
        this.loginUseCaseInteractor = loginUseCaseInteractor;
        this.viewManagerModel = viewManagerModel;
        this.userDataAccess = userDataAccess;
    }

    /**
     * Executes the Login Use Case.
     * @param username the username of the user logging in
     * @param password the password of the user logging in
     */
    public void execute(String username, String password) {
        if (!userDataAccess.existsByName(username)) {
            // Show an error message if the user does not exist
            JOptionPane.showMessageDialog(null,
                    "No account found for the username '" + username + "'. Please create an account first.",
                    "Login Error",
                    JOptionPane.ERROR_MESSAGE);
            return; // Exit the method to prevent further execution
        }

        LoginInputData loginInputData = new LoginInputData(
                username, password);

        loginUseCaseInteractor.execute(loginInputData);
        userDataAccess.setCurrentUsername(username);
    }
    public void switchToSignupView() {
        // Switch to the signup view
        viewManagerModel.setState("sign up");
        viewManagerModel.firePropertyChanged();
    }
}

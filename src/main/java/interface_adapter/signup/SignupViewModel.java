package interface_adapter.signup;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Signup View.
 */
public class SignupViewModel extends ViewModel<SignupState> {

    public static final String TITLE_LABEL = "Sign Up screen";
    public static final String USERNAME_LABEL = "Choose username";
    public static final String PASSWORD_LABEL = "Choose password";

    public static final String CANCEL_BUTTON_LABEL = "Cancel";

    public static final String TO_SIGNUP_BUTTON_LABEL = "Sign up";

    public SignupViewModel() {
        super("Sign up");
        setState(new SignupState());
    }

}

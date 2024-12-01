package interface_adapter.change_password;

import use_case.change_password.ChangePasswordInputBoundary;
import use_case.change_password.ChangePasswordInputData;

public class ChangePasswordController {
    private final ChangePasswordInputBoundary changePasswordInteractor;

    public ChangePasswordController(ChangePasswordInputBoundary changePasswordInteractor) {
        this.changePasswordInteractor = changePasswordInteractor;
    }

    public void execute(String oldPassword, String newPassword, String confirmPassword) {
        ChangePasswordInputData inputData = new ChangePasswordInputData(oldPassword, newPassword, confirmPassword);
        changePasswordInteractor.execute(inputData);
    }
}

package use_case.change_password;

/**
 * The Change Password Use Case.
 */
public interface ChangePasswordInputBoundary {

    /**
     * Execute the Change Password Use Case.
     * @param inputData the input data for this use case
     */
    void execute(ChangePasswordInputData inputData);

}

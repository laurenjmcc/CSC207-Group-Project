package interface_adapter.team;

/**
 * State for the Create Team View.
 */
public class CreateTeamState {
    private String message;
    private String errorMessage;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

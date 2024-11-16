package interface_adapter.change_password;

/**
 * The State information representing the logged-in user.
 */
public class LoggedInState {
    private String username = "";

    private String password = "";
    private String passwordError;

    private String proteinname = "";
    private String analyzeError;

    public LoggedInState(LoggedInState copy) {
        username = copy.username;
        password = copy.password;
        passwordError = copy.passwordError;
        proteinname = copy.proteinname;
        analyzeError = copy.analyzeError;

    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public LoggedInState() {

    }
    // Username part
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Password part
    public void setPassword(String password) {
        this.password = password;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getPassword() {
        return password;
    }

    // Analyze part
    public String getProteinname() {return proteinname; }

    public void setProteinname(String proteinname) {
        this.proteinname = proteinname;
    }

    public void clickAnalyzeError(String error) {this.analyzeError = error;}
}

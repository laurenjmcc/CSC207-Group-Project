package use_case.team;

/**
 * Output Data for the Team Function Use Case.
 */
public class CreateTeamOutputData {
    private final String teamName;

    public CreateTeamOutputData(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamName() {
        return teamName;
    }
}

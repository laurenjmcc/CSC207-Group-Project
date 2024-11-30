package use_case.team;

import java.util.List;

public class CreateTeamInputData {
    private final String teamName;
    private final List<String> memberUsernames;
    private final String creatorUsername;

    public CreateTeamInputData(String teamName, List<String> memberUsernames, String creatorUsername) {
        this.teamName = teamName;
        this.memberUsernames = memberUsernames;
        this.creatorUsername = creatorUsername;
    }

    public String getTeamName() {
        return teamName;
    }

    public List<String> getMemberUsernames() {
        return memberUsernames;
    }

}

package interface_adapter.team;

import use_case.team.CreateTeamInputBoundary;
import use_case.team.CreateTeamInputData;

import java.util.List;

/**
 * Controller for the Create Team Use Case.
 */
public class CreateTeamController {
    private final CreateTeamInputBoundary interactor;

    public CreateTeamController(CreateTeamInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void createTeam(String teamName, List<String> memberUsernames, String creatorUsername) {
        CreateTeamInputData inputData = new CreateTeamInputData(teamName, memberUsernames, creatorUsername);
        interactor.execute(inputData);
    }
}

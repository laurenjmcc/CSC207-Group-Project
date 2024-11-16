package use_case.team;

import entity.Team;
import entity.User;
import use_case.login.LoginUserDataAccessInterface;
import java.util.HashSet;
import java.util.Set;

/**
 * Interactor for the Create Team Use Case.
 */
public class CreateTeamInteractor implements CreateTeamInputBoundary {
    private final TeamDataAccessInterface teamDataAccess;
    private final CreateTeamOutputBoundary presenter;
    private final LoginUserDataAccessInterface userDataAccess;

    public CreateTeamInteractor(TeamDataAccessInterface teamDataAccess,
                                CreateTeamOutputBoundary presenter,
                                LoginUserDataAccessInterface userDataAccess) {
        this.teamDataAccess = teamDataAccess;
        this.presenter = presenter;
        this.userDataAccess = userDataAccess;
    }

    @Override
    public void execute(CreateTeamInputData inputData) {
        String teamName = inputData.getTeamName();

        if (teamDataAccess.teamExists(teamName)) {
            presenter.prepareFailView("Team name already exists.");
            return;
        }
        Set<String> memberUsernames = new HashSet<>();

        for (String username : inputData.getMemberUsernames()) {
            if (!userDataAccess.existsByName(username)) {
                presenter.prepareFailView("User " + username + " does not exist.");
                return;
            }
            memberUsernames.add(username);

            User user = userDataAccess.get(username);
            user.addTeamName(teamName);
            userDataAccess.save(user);
        }

        Team team = new Team(teamName, memberUsernames);

        teamDataAccess.saveTeam(team);

        CreateTeamOutputData outputData = new CreateTeamOutputData(teamName);
        presenter.prepareSuccessView(outputData);
    }
}

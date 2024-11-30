package use_case.past_result;

import entity.AnalysisResult;
import entity.Team;
import entity.User;
import use_case.login.LoginUserDataAccessInterface;
import use_case.team.TeamDataAccessInterface;

import java.util.*;

public class ResultInteractor implements ResultInputBoundary {
    private final ResultOutputBoundary userPresenter;
    private final AnalysisResultDataAccessInterface analysisResultDataAccess;
    private final LoginUserDataAccessInterface userDataAccess;
    private final TeamDataAccessInterface teamDataAccess;

    public ResultInteractor(ResultOutputBoundary resultOutputBoundary,
                            AnalysisResultDataAccessInterface analysisResultDataAccess,
                            LoginUserDataAccessInterface userDataAccess,
                            TeamDataAccessInterface teamDataAccess) {
        this.userPresenter = resultOutputBoundary;
        this.analysisResultDataAccess = analysisResultDataAccess;
        this.userDataAccess = userDataAccess;
        this.teamDataAccess = teamDataAccess;
    }

    @Override
    public void execute() throws Exception {
        String currentUsername = userDataAccess.getCurrentUsername();
        User currentUser = userDataAccess.get(currentUsername);

        Set<String> teamNames = currentUser.getTeamNames();
        Set<String> allUsernames = new HashSet<>();
        allUsernames.add(currentUsername);

        for (String teamName : teamNames) {
            Team team = teamDataAccess.getTeam(teamName);
            allUsernames.addAll(team.getMemberUsernames());
        }

        List<AnalysisResult> results = analysisResultDataAccess.getResultsForUsers(allUsernames);

        if (results.isEmpty()) {
            ResultOutputData resultOutputData = new ResultOutputData(Collections.emptyList(), true);
            userPresenter.prepareFailView("No results found for user: " + currentUsername);
        } else {
            ResultOutputData resultOutputData = new ResultOutputData(results, false);
            userPresenter.prepareSuccessView(resultOutputData);
        }
    }
}

package use_case.team;

/**
 * Input Boundary for actions which are related to creating a team.
 */
public interface CreateTeamInputBoundary {

    /**
     * Executes the team use case.
     * @param inputData the input data
     */
    void execute(CreateTeamInputData inputData);
}

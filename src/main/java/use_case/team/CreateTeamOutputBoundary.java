package use_case.team;

public interface CreateTeamOutputBoundary {

    /**
     * Prepares the success view.
     * @param outputData the output data.
     */
    void prepareSuccessView(CreateTeamOutputData outputData);

    /**
     * Prepares the failure view.
     * @param errorMessage the error message.
     */
    void prepareFailView(String errorMessage);
}

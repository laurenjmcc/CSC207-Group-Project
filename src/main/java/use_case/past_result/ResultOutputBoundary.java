package use_case.past_result;

/**
 * The output boundary for the Past Result Use Case.
 */
public interface ResultOutputBoundary {
    /**
     * Prepares the success view for the Past Result Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(ResultOutputData outputData);

    /**
     * Prepares the failure view for the Past Result Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}

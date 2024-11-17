package use_case.past_result;

/**
 * The Past_result Use Case.
 */
public interface ResultInputBoundary {

    /**
     * Execute the Past Result Use Case.
     * @param changePasswordInputData the input data for this use case
     */
    void execute(ResultInputData changePasswordInputData) throws Exception;

}

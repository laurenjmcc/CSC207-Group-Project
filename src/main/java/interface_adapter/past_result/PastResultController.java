package interface_adapter.past_result;

import use_case.past_result.ResultInputBoundary;

public class PastResultController {
    private final ResultInputBoundary pastResultUseCaseInteractor;

    public PastResultController(ResultInputBoundary resultUseCaseInteractor) {
        this.pastResultUseCaseInteractor = resultUseCaseInteractor;
    }

    public void execute() throws Exception {
        pastResultUseCaseInteractor.execute();
    }
}

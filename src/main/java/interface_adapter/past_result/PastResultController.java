package interface_adapter.past_result;

import use_case.past_result.ResultInputBoundary;
import use_case.past_result.ResultInputData;

public class PastResultController {
    private final ResultInputBoundary pastResultUseCaseInteractor;

    public PastResultController(ResultInputBoundary ResultUseCaseInteractor) {
        this.pastResultUseCaseInteractor = ResultUseCaseInteractor;
    }

    public void execute(String protein) throws Exception {
        final ResultInputData resultInputData = new ResultInputData(protein);

        pastResultUseCaseInteractor.execute(resultInputData);
    }
}

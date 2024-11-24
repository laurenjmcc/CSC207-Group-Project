package interface_adapter.analyze;

import use_case.analyze.AnalyzeInputBoundary;
import use_case.analyze.AnalyzeInputData;

public class AnalyzeController {

    private final AnalyzeInputBoundary analyzeUseCaseInteractor;

    public AnalyzeController(AnalyzeInputBoundary analyzeUseCaseInteractor) {
        this.analyzeUseCaseInteractor = analyzeUseCaseInteractor;
    }
    public void execute(String proteinname) throws Exception {

        final AnalyzeInputData analyzeInputData = new AnalyzeInputData(proteinname);
        analyzeUseCaseInteractor.execute(analyzeInputData);

    }
}

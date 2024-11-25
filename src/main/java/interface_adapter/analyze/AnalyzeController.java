package interface_adapter.analyze;

import entity.PastResult;
import use_case.analyze.AnalyzeInputBoundary;
import use_case.analyze.AnalyzeInputData;


public class AnalyzeController {

    private final AnalyzeInputBoundary analyzeUseCaseInteractor;

    public AnalyzeController(AnalyzeInputBoundary analyzeUseCaseInteractor) {
        this.analyzeUseCaseInteractor = analyzeUseCaseInteractor;
    }
    public void execute(String proteinname) throws Exception {
        try {
            PastResult pastResult = new PastResult(proteinname);
            pastResult.add();
        } catch (Exception e) {
            throw new RuntimeException("Error during analysis: " + e.getMessage());
        }

        final AnalyzeInputData analyzeInputData = new AnalyzeInputData(proteinname);
        analyzeUseCaseInteractor.execute(analyzeInputData);

    }
}

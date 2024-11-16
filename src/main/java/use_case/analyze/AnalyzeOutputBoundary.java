package use_case.analyze;

public interface AnalyzeOutputBoundary {
    void prepareFailView(String s);

    void prepareSuccessView(AnalyzeOutputData analyzeOutputData);


}

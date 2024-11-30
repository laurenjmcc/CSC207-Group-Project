package use_case.past_result;

import entity.AnalysisResult;

import java.util.List;

public class ResultOutputData {
    private List<AnalysisResult> analysisResults;
    private final boolean useCaseFailed;

    public ResultOutputData(List<AnalysisResult> analysisResults, boolean useCaseFailed) {
        this.analysisResults = analysisResults;
        this.useCaseFailed = useCaseFailed;
    }

    public List<AnalysisResult> getAnalysisResults() {
        return analysisResults;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }

}

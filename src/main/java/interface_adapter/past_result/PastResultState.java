package interface_adapter.past_result;

import entity.AnalysisResult;

import java.util.ArrayList;
import java.util.List;

public class PastResultState {

    private List<AnalysisResult> analysisResults;

    public PastResultState() {
        this.analysisResults = new ArrayList<>();
    }

    public List<AnalysisResult> getAnalysisResults() {
        return analysisResults;
    }

    public void setAnalysisResults(List<AnalysisResult> analysisResults) {
        this.analysisResults = analysisResults;
    }
}

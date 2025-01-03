package entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class AnalysisResult {
    private String username;
    private List<String> teamNames;
    private String proteinName;
    private LocalDateTime analysisDate;
    private Map<String, Object> analysisData;

    public AnalysisResult() {
    }

    public AnalysisResult(String username, List<String> teamNames, String proteinName,
                          LocalDateTime analysisDate, Map<String, Object> analysisData) {
        this.username = username;
        this.teamNames = teamNames;
        this.proteinName = proteinName;
        this.analysisDate = analysisDate;
        this.analysisData = analysisData;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getTeamNames() {
        return teamNames;
    }

    public void setTeamNames(List<String> teamNames) {
        this.teamNames = teamNames;
    }

    public String getProteinName() {
        return proteinName;
    }

    public void setProteinName(String proteinName) {
        this.proteinName = proteinName;
    }

    public LocalDateTime getAnalysisDate() {
        return analysisDate;
    }

    public void setAnalysisDate(LocalDateTime analysisDate) {
        this.analysisDate = analysisDate;
    }

    public Map<String, Object> getAnalysisData() {
        return analysisData;
    }

    public void setAnalysisData(Map<String, Object> analysisData) {
        this.analysisData = analysisData;
    }
}

package interface_adapter.analyze;

public class AnalyzeState {

    private String proteinName;
    private String proteinDescription;
    private String proteinDisease;

    public AnalyzeState(String proteinName, String proteinDescription, String proteinDisease) {
        this.proteinName = proteinName;
        this.proteinDescription = proteinDescription;
        this.proteinDisease = proteinDisease;
    }

    public AnalyzeState() {
        this.proteinName = "";
        this.proteinDescription = "";
        this.proteinDisease = "";
    }

    public void setProteinName(String proteinName) {
        this.proteinName = proteinName;
    }

    public void setProteinDescription(String proteinDescription) {
        this.proteinDescription = proteinDescription;
    }

    public void setProteinDisease(String proteinDisease) {
        this.proteinDisease = proteinDisease;
    }

    public String getProteinName() {
        return proteinName;
    }
    public String getProteinDescription() {
        return proteinDescription;
    }

    public String getProteinDisease() {
        return proteinDisease;
    }

    public void setViewName(String loggedIn) {

    }
}

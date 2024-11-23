package interface_adapter.analyze;

import java.util.ArrayList;

public class AnalyzeState {

    private String proteinName;
    private String proteinDescription;
    private ArrayList<String> proteinDisease;

    public AnalyzeState(String proteinName, String proteinDescription, ArrayList<String> proteinDisease) {
        this.proteinName = proteinName;
        this.proteinDescription = proteinDescription;
        this.proteinDisease = proteinDisease;
    }

    public AnalyzeState() {
        this.proteinName = "";
        this.proteinDescription = "";
        this.proteinDisease = new ArrayList<>();
    }

    public void setProteinName(String proteinName) {
        this.proteinName = proteinName;
    }
    public void setProteinDescription(String proteinDescription) {
        this.proteinDescription = proteinDescription;
    }

    public void setProteinDisease(ArrayList<String> proteinDisease) {
        this.proteinDisease = proteinDisease;
    }

    public String getProteinName() {
        return proteinName;
    }
    public String getProteinDescription() {
        return proteinDescription;
    }

    public ArrayList<String> getProteinDisease() {
        return proteinDisease;
    }
}

package use_case.analyze;

public class AnalyzeOutputData {

    private String proteinName;
    private String proteinDescription;
    private final boolean useCaseFailed;

    public AnalyzeOutputData(String proteinName, String proteinDescription, boolean useCaseFailed) {
        this.proteinName = proteinName;
        this.proteinDescription = proteinDescription;
        this.useCaseFailed = useCaseFailed;
    }

    public String getProteinDescription() {
        return proteinDescription;
    }

    public String getProteinName() {
        return proteinName;
    }

}

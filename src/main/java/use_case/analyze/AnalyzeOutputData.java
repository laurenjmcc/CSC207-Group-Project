package use_case.analyze;

import java.util.ArrayList;
import java.util.List;

public class AnalyzeOutputData {

    private String proteinName;
    private String proteinDescription;
    private ArrayList<String> disease;
    private final boolean useCaseFailed;

    public AnalyzeOutputData(String proteinName, String proteinDescription, ArrayList<String> disease, boolean useCaseFailed) {
        this.proteinName = proteinName;
        this.proteinDescription = proteinDescription;
        this.useCaseFailed = useCaseFailed;
        this.disease = disease;
    }

    public String getProteinDescription() {
        return proteinDescription;
    }

    public String getProteinName() {
        return proteinName;
    }

    public ArrayList<String> getDiseases(){return disease;
    }

}

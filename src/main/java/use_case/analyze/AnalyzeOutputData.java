package use_case.analyze;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class AnalyzeOutputData {

    private String proteinName;
    private String proteinDescription;
    private ArrayList<String> acronym;
    private ArrayList<String> disease;
    private String proteinname;
    private Map<String, Set<String>> location;
    private final boolean useCaseFailed;

    public AnalyzeOutputData(String proteinName, String proteinDescription, ArrayList<String> acronym, ArrayList<String> disease, String proteinname, boolean useCaseFailed) {
        this.proteinName = proteinName;
        this.proteinDescription = proteinDescription;
        this.acronym = acronym;
        this.disease = disease;
        this.proteinname = proteinname;
        this.useCaseFailed = useCaseFailed;
    }


    public String getProteinName() {
        return proteinName;
    }

    public void setProteinName(String proteinName) {
        this.proteinName = proteinName;
    }

    public String getProteinDescription() {
        return proteinDescription;
    }

    public void setProteinDescription(String proteinDescription) {
        this.proteinDescription = proteinDescription;
    }

    public ArrayList<String> getAcronym() {
        return acronym;
    }

    public void setAcronym(ArrayList<String> acronym) {
        this.acronym = acronym;
    }

    public ArrayList<String> getDisease() {
        return disease;
    }

    public void setDisease(ArrayList<String> disease) {
        this.disease = disease;
    }

    public String getProteinname() {
        return proteinname;
    }

    public void setProteinname(String proteinname) {
        this.proteinname = proteinname;
    }

    public Map<String, Set<String>> getLocation() {
        return location;
    }

    public void setLocation(Map<String, Set<String>> location) {
        this.location = location;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}

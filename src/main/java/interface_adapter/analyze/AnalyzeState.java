package interface_adapter.analyze;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AnalyzeState {

    private String proteinName;
    private String proteinDescription;
    private ArrayList<String> acronym;
    private ArrayList<String> disease;
    private Map<String, Set<String>> location;

    public AnalyzeState(String proteinName, String proteinDescription,ArrayList<String> proteinDisease,  ArrayList<String> acronym, Map<String, Set<String>> location) {
        this.proteinName = proteinName;
        this.proteinDescription = proteinDescription;
        this.acronym = acronym;
        this.disease = proteinDisease;
        this.location = location;
    }

    public AnalyzeState() {
        this.acronym = new ArrayList<>();
        this.proteinName = "";
        this.proteinDescription = "";
        this.disease = new ArrayList<>();
        this.location = new HashMap<>();
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

    public Map<String, Set<String>> getLocation() {
        return location;
    }

    public void setLocation(Map<String, Set<String>> location) {
        this.location = location;
    }
}


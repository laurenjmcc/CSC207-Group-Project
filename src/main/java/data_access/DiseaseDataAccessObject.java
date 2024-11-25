package data_access;

import API.Protein_API;
import use_case.past_result.ResultUserDataAccessInterface;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;


public class DiseaseDataAccessObject{

    private final Protein_API protein_api;
    private ArrayList<String> acronym;
    private ArrayList<String> disease;
    private String ProteinName;
    private Map<String, Set<String>> result;

    public DiseaseDataAccessObject(String proteinID) throws Exception {
        this.protein_api = new Protein_API(proteinID);
        this.disease = protein_api.extractDisease();
        this.acronym = protein_api.acronyms();
        this.ProteinName = proteinID;
        this.result = protein_api.location();
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


    public ArrayList<String> DiseaseInfo() throws Exception {
        return protein_api.extractDisease();
    }

    public String getProteinDescription() throws Exception{
        return protein_api.get_description();
    }

    public boolean successCall(String proteinname) {
        return true;
    }
    public Map<String, Set<String>> getResult() throws Exception{
        return protein_api.location();
    }
    public void setResult(Map<String, Set<String>> result) throws Exception{
        this.result = result;
    }
}

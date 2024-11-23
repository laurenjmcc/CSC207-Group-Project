package data_access;

import API.Protein_API;
import use_case.analyze.AnalyzeProteinDataAccessInterface;

import java.util.ArrayList;


public class DiseaseDataAccessObject implements AnalyzeProteinDataAccessInterface {

    private final Protein_API protein_api;
    private ArrayList<String> acronym;
    private ArrayList<String> disease;
    private String ProteinName;

    public DiseaseDataAccessObject(String proteinID) throws Exception {
        this.protein_api = new Protein_API(proteinID);
        this.disease = protein_api.extractDisease();
        this.acronym = protein_api.acronyms();
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

    @Override
    public ArrayList<String> DiseaseInfo() throws Exception {
        return protein_api.extractDisease();
    }
    @Override
    public String getProteinDescription() throws Exception{
        return protein_api.getDescription();
    }
    @Override
    public boolean successCall(String proteinname) {
        return true;
    }
}

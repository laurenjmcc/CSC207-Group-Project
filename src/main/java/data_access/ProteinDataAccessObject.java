package data_access;

import API.Protein_API;
import use_case.analyze.AnalyzeProteinDataAccessInterface;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;


public class ProteinDataAccessObject implements AnalyzeProteinDataAccessInterface {

    private final Protein_API protein_api;
    private final String description;
    private final String accession;
    private ArrayList<String> acronym;
    private ArrayList<String> disease;
    private String proteinname;
    private Map<String, Set<String>> location;

    public ProteinDataAccessObject(String proteinID) throws Exception {
        this.protein_api = new Protein_API(proteinID);
        this.setProteinname(getProtein_api().protein_name);
        this.accession = protein_api.getUniProtAccession(proteinID);
        this.setDisease(getProtein_api().extractDisease());
        this.setAcronym(getProtein_api().acronyms());
        this.setLocation(getProtein_api().location());
        this.description = getProtein_api().get_description();
    }

    @Override
    public String getProteinname() {return proteinname;}

    @Override
    public ArrayList<String> getDisease() {
        return disease;
    }

    @Override
    public ArrayList<String> getAcronym() {return acronym;
    }

    @Override
    public Map<String, Set<String>> getlocation() {return getLocation();}

    @Override
    public String getProteinDescription() {return getDescription();}


    @Override
    public boolean successCall(String proteinname) {
        return true;
    }

    public Protein_API getProtein_api() {
        return protein_api;
    }

    public String getDescription() {
        return description;
    }

    public void setAcronym(ArrayList<String> acronym) {
        this.acronym = acronym;
    }

    public void setDisease(ArrayList<String> disease) {
        this.disease = disease;
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

    public String getAccession() {
        return accession;
    }
}
package data_access;

import API.Protein_API;
import org.json.JSONArray;
import use_case.past_result.ResultUserDataAccessInterface;

import java.util.ArrayList;


public class DiseaseDataAccessObject implements ResultUserDataAccessInterface {

    private static Protein_API protein_api = null;
    private JSONArray metadata;
    private ArrayList<String> acronym;
    private ArrayList<String> disease;
    private String ProteinName;

    public DiseaseDataAccessObject(String proteinID) throws Exception {
        this.protein_api = new Protein_API(proteinID);
        this.disease = protein_api.extractDisease();
        this.acronym = protein_api.acronyms();
        this.ProteinName = proteinID;
    }

    public JSONArray PI_API(String proteinID) throws Exception {
        return protein_api.PI_API();
        }
    public static ArrayList<String> acronyms() throws Exception {

        return protein_api.acronyms();
    }
    public static ArrayList<String> disease_names() throws Exception {
        return protein_api.extractDisease();
    }

    public JSONArray getMetadata() {
        return metadata;
    }

    public void setMetadata(JSONArray metadata) {
        this.metadata = metadata;
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
    public ArrayList<String> DiseaseInfo(String Protein_id) throws Exception {
        return protein_api.extractDisease();
    }
}

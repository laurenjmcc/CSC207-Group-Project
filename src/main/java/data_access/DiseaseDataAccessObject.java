package data_access;

import API.Protein_API;
import org.json.JSONArray;
import use_case.past_result.ResultUserDataAccessInterface;

import java.util.ArrayList;


public class DiseaseDataAccessObject implements ResultUserDataAccessInterface {

    private JSONArray metadata;
    private ArrayList<String> acronym;
    private ArrayList<String> disease;
    private String ProteinName;

    public DiseaseDataAccessObject(String proteinID){
        this.ProteinName = proteinID;
    }
    public JSONArray PI_API(String proteinID) throws Exception {
        this.setMetadata(Protein_API.PI_API(proteinID));
        return Protein_API.PI_API(proteinID);
        }
    public static ArrayList<String> acronyms(String proteinID) throws Exception {

        return Protein_API.acronyms(proteinID);
    }
    public static ArrayList<String> disease_names(String proteinID) throws Exception {
        return Protein_API.extractDiseaseIds(proteinID);
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
        return Protein_API.extractDiseaseIds(Protein_id);
    }
}

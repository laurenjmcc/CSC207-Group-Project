package entity;

import data_access.ProteinDataAccessObject;

public class PastResult {
    private ProteinDataAccessObject protein;
    private String protein_name;
    private String acession;
    public String description;


    public PastResult(String protein_name) throws Exception {
        this.setProtein(new ProteinDataAccessObject(protein_name));
        this.setAcession(getProtein().getProtein_id);
        this.setProtein_name(getProtein().getProteinname());
        this.setDescription(getProtein().getProteinDescription());
    }

    public ProteinDataAccessObject getProtein() {
        return protein;
    }

    public void setProtein(ProteinDataAccessObject protein) {
        this.protein = protein;
    }

    public String getProtein_name() {
        return protein_name;
    }

    public void setProtein_name(String protein_name) {
        this.protein_name = protein_name;
    }

    public String getAcession() {
        return acession;
    }

    public void setAcession(String acession) {
        this.acession = acession;
    }



    public void setDescription(String description) {
        this.description = description;
    }
}


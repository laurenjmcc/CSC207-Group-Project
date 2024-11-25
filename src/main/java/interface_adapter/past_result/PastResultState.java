package interface_adapter.past_result;

import java.util.ArrayList;

public class PastResultState {

    private String description;
    private String id;
    private String protein;

    // Default constructor
    public PastResultState() {
        this.description = "";
        this.id = "";
        this.protein = "";
    }

    // Copy constructor
    public PastResultState(PastResultState copy) {
        if (copy == null) {
            this.description = "";
            this.id = "";
            this.protein = "";
        } else {
            this.description = copy.description != null ? copy.description : "";
            this.protein = copy.protein != null ? copy.protein : "";
        }
    }

    // Getters and setters
    public String  getDisease() {
        return this.description;
    }

    public void setDisease(ArrayList<String> disease) {
        if (disease == null) {
            this.description = "";
        } else {
            this.description = ""; // Defensive copy
        }
    }

    public String getProtein() {
        return this.protein;
    }

    public void setProtein(String protein) {
        if (protein == null) {
            this.protein = "";
        } else {
            this.protein = protein;
        }
    }

    @Override
    public String toString() {
        return "PastResultState{" +
                "disease=" + description +
                ", protein='" + protein + '\'' +
                '}';
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
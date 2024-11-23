package interface_adapter.past_result;

import java.util.ArrayList;

public class PastResultState {

    private ArrayList<String> disease;
    private String protein;

    // Default constructor
    public PastResultState() {
        this.disease = new ArrayList<>();
        this.protein = "";
    }

    // Copy constructor
    public PastResultState(PastResultState copy) {
        if (copy == null) {
            this.disease = new ArrayList<>();
            this.protein = "";
        } else {
            this.disease = copy.disease != null ? new ArrayList<>(copy.disease) : new ArrayList<>();
            this.protein = copy.protein != null ? copy.protein : "";
        }
    }

    // Getters and setters
    public ArrayList<String> getDisease() {
        return this.disease;
    }

    public void setDisease(ArrayList<String> disease) {
        if (disease == null) {
            this.disease = new ArrayList<>();
        } else {
            this.disease = new ArrayList<>(disease); // Defensive copy
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
                "disease=" + disease +
                ", protein='" + protein + '\'' +
                '}';
    }
}
package interface_adapter.past_result;

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
            this.id = copy.id != null ? copy.id : "";
        }
    }

    // Getters and setters
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        if (description == null) {
            this.description = "";
        } else {
            this.description = description; // Defensive copy
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
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        if (id == null) {
            this.id = "";
        }else {
            this.id = id;
        }
    }

    @Override
    public String toString() {
        return "PastResultState{" +
                "disease=" + description +
                ", protein='" + protein + '\'' +
                '}';
    }

}
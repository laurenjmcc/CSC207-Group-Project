package use_case.past_result;

import java.util.ArrayList;

/**
 * Output Data for the Change Password Use Case.
 */
public class ResultOutputData {
    private ArrayList<String> diseases = new ArrayList<>();  // Add this field
    private final String protein;

    private final boolean useCaseFailed;

    public ResultOutputData(ArrayList<String> diseases, String protein, boolean useCaseFailed) {
        this.diseases = diseases;
        this.protein = protein;
        this.useCaseFailed = useCaseFailed;
    }

    public String getProtein() {
        return protein;
    }
    public ArrayList<String> getDiseases() {return diseases;}

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}

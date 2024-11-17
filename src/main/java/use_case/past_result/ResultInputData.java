package use_case.past_result;

/**
 * The input data for the Change Password Use Case.
 */
public class ResultInputData {

    private final String protein_name;


    public ResultInputData(String protein) {
        this.protein_name = protein;
    }

    String getProtein_name() {
        return protein_name;
    }

}

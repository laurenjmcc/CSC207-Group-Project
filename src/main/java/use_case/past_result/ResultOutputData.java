package use_case.past_result;

import java.util.ArrayList;

/**
 * Output Data for the Change Password Use Case.
 */
public class ResultOutputData {
   private String description;
   private String id;
   private String name;
   private final boolean useCaseFailed;

    public ResultOutputData(String description, String id, String name, boolean useCaseFailed) {
        this.description = description;
        this.id = id;
        this.name = name;
        this.useCaseFailed = useCaseFailed;
    }

    public String getDescription() {
        return description;
    }
    public String getId() {return this.id;}
    public String getName() {return this.name;}

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }

    public String getDiseases() {
        return description;
    }
}

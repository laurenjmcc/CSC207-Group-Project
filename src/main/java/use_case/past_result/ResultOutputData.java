package use_case.past_result;

/**
 * Output Data for the Change Password Use Case.
 */
public class ResultOutputData {
    private String description;
    private String id;
    private String name;
    private final boolean useCaseFailed;

    public ResultOutputData(String description, String id, String name, boolean useCaseFailed) {
        this.setDescription(description);
        this.setId(id);
        this.setName(name);
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

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}

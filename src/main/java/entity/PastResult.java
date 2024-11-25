package entity;

import data_access.ProteinDataAccessObject;
import interface_adapter.past_result.PastResultState;

import java.util.ArrayList;
import java.util.List;

public class PastResult {

    // Store all past results
    private static final List<PastResultEntry> results = new ArrayList<>();

    private final ProteinDataAccessObject protein;
    private final String description;
    private final String id;
    private final String proteinName;

    public static void main(String[] args) throws Exception {
        PastResult pastResult = new PastResult("p53");
        System.out.println(pastResult.getDescription());
    }
    // Constructor
    public PastResult(String proteinID) throws Exception {
        this.protein = new ProteinDataAccessObject(proteinID);
        this.description = protein.getProteinDescription();
        this.id = protein.getAccession();
        this.proteinName = protein.getProteinname();
    }

    // Add a single result
    public void add() {
        results.add(new PastResultEntry(proteinName, description, id));
    }
    public String getDescription() {
        // Format the description by adding a newline after each period
        return description.replaceAll("\\.", ".\n");
    }

    // Retrieve all past results
    public static List<PastResultEntry> getResults() {
        return new ArrayList<>(results); // Return a copy for safety
    }

    // Inner class to represent a single result
    public static class PastResultEntry {
        private final String proteinName;
        private final String description;
        private final String id;

        public PastResultEntry(String proteinName, String description, String id) {
            this.proteinName = proteinName;
            this.description = description;
            this.id = id;
        }

        public String getProteinName() {
            return proteinName;
        }

        public String getDescription() {
            return description.replaceAll("\\.", ".\n");
        }

        public String getId() {
            return id;
        }

        @Override
        public String toString() {
            return "Protein: " + proteinName + "(" + id + ") " + "Description:" + getDescription();
        }
    }
}
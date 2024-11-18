package data_access;

import use_case.analyze.AnalyzeProteinDataAccessInterface;

public class ProteinDataAccessObject implements AnalyzeProteinDataAccessInterface {

    @Override
    public String getProteinDescription(String proteinName) {
        // Eventually we will implement actual logic to get protein description
        // For now, ill just return a placeholder description
        return "Protein description for " + proteinName;
    }
    @Override
    public boolean successCall(String proteinName) {
        // Again, Eventually we will implement actual logic to get protein description
        // For now, return true if the protein name is not empty
        return proteinName != null && !proteinName.trim().isEmpty();
    }
}

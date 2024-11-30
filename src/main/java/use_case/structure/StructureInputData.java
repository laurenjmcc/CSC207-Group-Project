package use_case.structure;

public class StructureInputData {

    private final String pdbID;

    public StructureInputData(String rawDataPdbID) {
        this.pdbID = rawDataPdbID;
    }
    public String getPdbID() {
        return pdbID;
    }
}

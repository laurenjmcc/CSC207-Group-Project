package interface_adapter.structure;

public class StructureState {

    private String pdbID = "";

    private String pdbIDError;

    public StructureState(StructureState copy) {
        pdbID = copy.pdbID;
        pdbIDError = copy.pdbIDError;

    }

    public StructureState() {

    }
    public String getPdbID() {
        return pdbID;
    }
    public void setPdbID(String pdbID) {
        this.pdbID = pdbID;
    }

    public void clickButtonToViewStructureError(String error) {this.pdbIDError = error;}
}

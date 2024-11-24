package data_access;

public class ProteinDataAccessFactory{
    public ProteinDataAccessObject create(String proteinName) throws Exception {
        return new ProteinDataAccessObject(proteinName);
    }
}

package data_access;

public class DiseaseDataAccessFactory {
    public DiseaseDataAccessObject create(String proteinName) throws Exception {
        return new DiseaseDataAccessObject(proteinName);
    }
}

package data_access;

public class DiseaseDataAccessFactory {
    public DiseaseDataAccessObject create(String proteinName) {
        return new DiseaseDataAccessObject(proteinName);
    }
}

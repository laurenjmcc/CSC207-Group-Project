package use_case.past_result;

import data_access.DiseaseDataAccessFactory;
import data_access.DiseaseDataAccessObject;

import java.util.ArrayList;

/**
 * The Change Password Interactor.
 */
public class ResultInteractor implements ResultInputBoundary {
    private final DiseaseDataAccessFactory factory;
    private final ResultOutputBoundary userPresenter;

    public ResultInteractor(DiseaseDataAccessFactory factory, ResultOutputBoundary resultOutputBoundary) {
        this.factory = factory;
        this.userPresenter = resultOutputBoundary;
    }

    @Override
    public void execute(ResultInputData resultInputData) throws Exception {
        DiseaseDataAccessObject diseaseDataAccessObject = factory.create(resultInputData.getProtein_name());
        String proteinName = resultInputData.getProtein_name();
        System.out.println("Interactor: Fetching diseases for protein: " + resultInputData.getProtein_name());
        ArrayList<String> disease = diseaseDataAccessObject.DiseaseInfo(proteinName);
        System.out.println("Interactor: Diseases fetched: " + disease);
        final ResultOutputData resultOutputData = new ResultOutputData(disease, proteinName, false);
        userPresenter.prepareSuccessView(resultOutputData);
    }
}

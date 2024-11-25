package use_case.past_result;

import data_access.DiseaseDataAccessFactory;
import data_access.ProteinDataAccessFactory;
import data_access.ProteinDataAccessObject;

import java.util.ArrayList;

/**
 * The Change Password Interactor.
 */
public class ResultInteractor implements ResultInputBoundary {
    private final ProteinDataAccessFactory factory;
    private final ResultOutputBoundary userPresenter;

    public ResultInteractor(ProteinDataAccessFactory factory, ResultOutputBoundary resultOutputBoundary) {
        this.factory = factory;
        this.userPresenter = resultOutputBoundary;
    }

    @Override
    public void execute(ResultInputData resultInputData) throws Exception {
        ProteinDataAccessObject diseaseDataAccessObject = factory.create(resultInputData.getProtein_name());
        String description = diseaseDataAccessObject.getProteinDescription();
        String id = diseaseDataAccessObject.getProtein_id;
        String name = diseaseDataAccessObject.getProteinname();
        final ResultOutputData resultOutputData = new ResultOutputData(description, id, name,  false);
        userPresenter.prepareSuccessView(resultOutputData);
    }
}

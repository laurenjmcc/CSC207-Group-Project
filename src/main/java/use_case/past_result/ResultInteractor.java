package use_case.past_result;

import data_access.ProteinDataAccessObject;

import java.util.ArrayList;

/**
 * The Change Password Interactor.
 */
public class ResultInteractor implements ResultInputBoundary {
    private final ResultOutputBoundary userPresenter;

    public ResultInteractor(ResultOutputBoundary resultOutputBoundary) {
        this.userPresenter = resultOutputBoundary;
    }

    @Override
    public void execute(ResultInputData resultInputData) throws Exception {
        ProteinDataAccessObject dao = new ProteinDataAccessObject(resultInputData.getProtein_name());
        String proteinName = resultInputData.getProtein_name();
        ArrayList<String> disease = dao.DiseaseInfo();
        final ResultOutputData resultOutputData = new ResultOutputData(disease, proteinName, false);
        userPresenter.prepareSuccessView(resultOutputData);
    }
}

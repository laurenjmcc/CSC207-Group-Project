package use_case.analyze;

import data_access.ProteinDataAccessFactory;
import data_access.ProteinDataAccessObject;
import use_case.login.LoginOutputData;

public class AnalyzeInteractor implements AnalyzeInputBoundary{

    private final ProteinDataAccessFactory factory;
    private final AnalyzeOutputBoundary analyzePresenter ;

    public AnalyzeInteractor(ProteinDataAccessFactory factory,
                             AnalyzeOutputBoundary analyzePresenter) {
        this.factory = factory;
        this.analyzePresenter = analyzePresenter;
    }
    /**
     * @param analyzeInputData
     */
    @Override
    public void execute(AnalyzeInputData analyzeInputData) throws Exception {

        final String proteinname = analyzeInputData.getProteinname();
        ProteinDataAccessObject proteinDataAccessObject = factory.create(proteinname);

        if (!proteinDataAccessObject.successCall(proteinname)) {
            analyzePresenter.prepareFailView("Sorry! couldn't find the protein " + proteinname + "in the database.");
        }

        else {
            final String proteinDescription = proteinDataAccessObject.getProteinDescription();
            final AnalyzeOutputData analyzeOutputData = new AnalyzeOutputData(proteinname, proteinDescription, false);
            analyzePresenter.prepareSuccessView(analyzeOutputData);
        }


    }
}

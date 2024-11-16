package use_case.analyze;

import use_case.login.LoginOutputData;

public class AnalyzeInteractor implements AnalyzeInputBoundary{

    private final AnalyzeProteinDataAccessInterface proteinDataAccessObject;
    private final AnalyzeOutputBoundary analyzePresenter ;

    public AnalyzeInteractor(AnalyzeProteinDataAccessInterface proteinDataAccessObject,
                             AnalyzeOutputBoundary analyzePresenter) {
        this.proteinDataAccessObject = proteinDataAccessObject;
        this.analyzePresenter = analyzePresenter;
    }
    /**
     * @param analyzeInputData
     */
    @Override
    public void execute(AnalyzeInputData analyzeInputData) {

        final String proteinname = analyzeInputData.getProteinname();

        if (!proteinDataAccessObject.successCall(proteinname)) {
            analyzePresenter.prepareFailView("Sorry! couldn't find the protein " + proteinname + "in the database.");
        }

        else {
            final String proteinDescription = proteinDataAccessObject.getProteinDescription(proteinname);
            final AnalyzeOutputData analyzeOutputData = new AnalyzeOutputData(proteinname, proteinDescription, false);
            analyzePresenter.prepareSuccessView(analyzeOutputData);
        }


    }
}

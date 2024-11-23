package use_case.analyze;

import data_access.ProteinDataAccessFactory;
import data_access.ProteinDataAccessObject;

import java.util.ArrayList;

public class AnalyzeInteractor implements AnalyzeInputBoundary{

    private final ProteinDataAccessFactory factory;
    private final AnalyzeOutputBoundary analyzePresenter ;

    public AnalyzeInteractor(ProteinDataAccessFactory Factory,
                             AnalyzeOutputBoundary analyzePresenter) {
        this.factory = Factory;
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
            ArrayList<String> disease = proteinDataAccessObject.DiseaseInfo();
            final String proteinDescription = proteinDataAccessObject.getProteinDescription();
            final AnalyzeOutputData analyzeOutputData = new AnalyzeOutputData(proteinname, proteinDescription, disease, false);
            analyzePresenter.prepareSuccessView(analyzeOutputData);
        }


    }
}

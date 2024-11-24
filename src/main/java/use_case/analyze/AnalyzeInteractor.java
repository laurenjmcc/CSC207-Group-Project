package use_case.analyze;

import data_access.ProteinDataAccessFactory;
import data_access.ProteinDataAccessObject;
import use_case.login.LoginOutputData;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

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
        ProteinDataAccessObject proteinObject = factory.create(proteinname);

        if (!proteinObject.successCall(proteinname)) {
            analyzePresenter.prepareFailView("Sorry! couldn't find the protein " + proteinname + "in the database.");
        }

        else {

            String proteinName = proteinObject.getProteinname();
            ArrayList<String> diseaseList = proteinObject.getDisease();
            ArrayList<String> acronyms = proteinObject.getAcronym();
            Map<String, Set<String>> locations = proteinObject.getlocation();
            String proteinDescription = proteinObject.getProteinDescription();
            final AnalyzeOutputData analyzeOutputData = new AnalyzeOutputData(
                    proteinName,
                    proteinDescription,
                    acronyms,
                    diseaseList,
                    proteinName,
                    false);
            analyzePresenter.prepareSuccessView(analyzeOutputData);
        }


    }
}

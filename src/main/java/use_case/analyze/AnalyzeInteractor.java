package use_case.analyze;

import data_access.ProteinDataAccessFactory;
import data_access.ProteinDataAccessObject;
import entity.AnalysisResult;
import entity.User;
import use_case.login.LoginUserDataAccessInterface;
import use_case.past_result.AnalysisResultDataAccessInterface;

import java.time.LocalDateTime;
import java.util.*;

public class AnalyzeInteractor extends ProteinDataAccessFactory implements AnalyzeInputBoundary, AnalyzeProteinDataAccessInterface {

    private final ProteinDataAccessFactory factory;
    private final AnalyzeOutputBoundary analyzePresenter;
    private final AnalysisResultDataAccessInterface analysisResultDataAccess;
    private final LoginUserDataAccessInterface userDataAccess;

    public AnalyzeInteractor(ProteinDataAccessFactory factory,
                             AnalyzeOutputBoundary analyzePresenter,
                             AnalysisResultDataAccessInterface analysisResultDataAccess,
                             LoginUserDataAccessInterface userDataAccess) {
        this.factory = factory;
        this.analyzePresenter = analyzePresenter;
        this.analysisResultDataAccess = analysisResultDataAccess;
        this.userDataAccess = userDataAccess;
    }

    @Override
    public String getProteinname() {
        return "";
    }

    @Override
    public ArrayList<String> getDisease() {
        return null;
    }

    @Override
    public ArrayList<String> getAcronym() {
        return null;
    }

    @Override
    public Map<String, Set<String>> getlocation() {
        return Map.of();
    }

    @Override
    public String getProteinDescription() {
        return "";
    }

    @Override
    public boolean successCall(String proteinname) {
        return false;
    }

    @Override
    public void execute(AnalyzeInputData analyzeInputData) throws Exception {
        final String proteinName = analyzeInputData.getProteinname();
        AnalyzeProteinDataAccessInterface object = factory.create(proteinName);

        if (!object.successCall(proteinName)) {
            analyzePresenter.prepareFailView("Sorry! couldn't find the protein " + proteinName + " in the database.");
        } else {

            String proteinDescription = object.getProteinDescription();
            ArrayList<String> diseaseList = object.getDisease();
            ArrayList<String> acronyms = object.getAcronym();
            Map<String, Set<String>> locations = object.getlocation();

            final AnalyzeOutputData analyzeOutputData = new AnalyzeOutputData(
                    proteinName,
                    proteinDescription,
                    acronyms,
                    diseaseList,
                    proteinName,
                    false);

            String username = userDataAccess.getCurrentUsername();
            User currentUser = userDataAccess.get(username);
            Set<String> teamNamesSet = currentUser.getTeamNames();
            List<String> teamNames = new ArrayList<>(teamNamesSet);

            AnalysisResult analysisResult = new AnalysisResult();
            analysisResult.setUsername(username);
            analysisResult.setTeamNames(teamNames);
            analysisResult.setProteinName(proteinName);
            analysisResult.setAnalysisDate(LocalDateTime.now());

            Map<String, Object> analysisData = new HashMap<>();
            analysisData.put("proteinDescription", proteinDescription);
            analysisData.put("diseaseList", diseaseList);
            analysisData.put("acronyms", acronyms);
            analysisData.put("locations", locations);
            analysisResult.setAnalysisData(analysisData);

            analysisResultDataAccess.saveResult(analysisResult);

            analyzePresenter.prepareSuccessView(analyzeOutputData);
        }
    }
}


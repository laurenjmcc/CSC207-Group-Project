package interface_adapter.analyze;


import interface_adapter.ViewManagerModel;

import entity.PastResult;

import use_case.analyze.AnalyzeInputBoundary;
import use_case.analyze.AnalyzeInputData;
import view.ViewManager;

import javax.swing.text.View;


public class AnalyzeController {

    private final AnalyzeInputBoundary analyzeUseCaseInteractor;
    private ViewManagerModel viewManagerModel;

    public AnalyzeController(AnalyzeInputBoundary analyzeUseCaseInteractor) {
        this.analyzeUseCaseInteractor = analyzeUseCaseInteractor;
    }
    public void execute(String proteinname) throws Exception {
        try {
            PastResult pastResult = new PastResult(proteinname);
            pastResult.add();
        } catch (Exception e) {
            throw new RuntimeException("Error during analysis: " + e.getMessage());
        }

        final AnalyzeInputData analyzeInputData = new AnalyzeInputData(proteinname);
        analyzeUseCaseInteractor.execute(analyzeInputData);

    }
    public void setViewManagerModel(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
    }

    public void switchToStructureView() {
        viewManagerModel.setState("structure");
        viewManagerModel.firePropertyChanged();
    }
}

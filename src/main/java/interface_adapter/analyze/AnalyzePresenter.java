package interface_adapter.analyze;

import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.LoggedInState;
import interface_adapter.change_password.LoggedInViewModel;
import use_case.analyze.AnalyzeOutputBoundary;
import use_case.analyze.AnalyzeOutputData;

public class AnalyzePresenter implements AnalyzeOutputBoundary {

    private final LoggedInViewModel loggedInViewModel;
    private final AnalyzeViewModel analyzeViewModel;
    private final ViewManagerModel viewManagerModel;

    public AnalyzePresenter(ViewManagerModel viewManagerModel,
                            AnalyzeViewModel analyzeViewModel,
                            LoggedInViewModel loggedInViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.analyzeViewModel = analyzeViewModel;
        this.loggedInViewModel = loggedInViewModel;
    }

    /**
     * @param analyzeOutputData
     */
    @Override
    public void prepareSuccessView(AnalyzeOutputData analyzeOutputData) {
        // On success, switch to the analyze view.

        final AnalyzeState analyzeState = analyzeViewModel.getState();
        analyzeState.setProteinName(analyzeOutputData.getProteinName());
        analyzeState.setProteinDescription(analyzeOutputData.getProteinDescription());
        this.analyzeViewModel.setState(analyzeState);
        this.analyzeViewModel.firePropertyChanged();

        this.viewManagerModel.setState(analyzeViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();

    }

    /**
     * @param error
     */
    @Override
    public void prepareFailView(String error) {
        final LoggedInState loggedInState = loggedInViewModel.getState();
        loggedInState.clickAnalyzeError(error);
        loggedInViewModel.firePropertyChanged();
    }
}

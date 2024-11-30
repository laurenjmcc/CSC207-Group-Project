package interface_adapter.past_result;

import use_case.past_result.ResultOutputBoundary;
import use_case.past_result.ResultOutputData;

public class PastResultPresenter implements ResultOutputBoundary {
    private final PastResultViewModel pastResultViewModel;

    public PastResultPresenter(PastResultViewModel pastResultViewModel) {
        this.pastResultViewModel = pastResultViewModel;
    }

    @Override
    public void prepareSuccessView(ResultOutputData outputData) {
        PastResultState newState = new PastResultState();
        newState.setAnalysisResults(outputData.getAnalysisResults());
        this.pastResultViewModel.setState(newState);
        this.pastResultViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        System.out.println("Presenter: Failed to fetch past results. Error: " + errorMessage);
    }
}

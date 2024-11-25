package interface_adapter.past_result;

import use_case.past_result.ResultOutputBoundary;
import use_case.past_result.ResultOutputData;

public class PastResultPresenter implements ResultOutputBoundary {
    private final PastResultViewModel pastResultViewModel;

    public PastResultPresenter(PastResultViewModel pastresultViewModel) {
        this.pastResultViewModel = pastresultViewModel;
    }
    @Override
    public void prepareSuccessView(ResultOutputData outputData) {

        PastResultState newState = new PastResultState();
        newState.setDescription(outputData.getDescription());
        this.pastResultViewModel.setState(newState);
        this.pastResultViewModel.firePropertyChanged("disease");

        System.out.println("Presenter: Success view preparation completed.");
    }

    @Override
    public void prepareFailView(String errorMessage) {
        System.out.println("Presenter: Failed to fetch past results. Error: " + errorMessage);
    }
}


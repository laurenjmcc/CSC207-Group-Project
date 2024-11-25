package interface_adapter.past_result;

import interface_adapter.past_result.PastResultViewModel;
import use_case.past_result.ResultOutputBoundary;
import use_case.past_result.ResultOutputData;
import interface_adapter.past_result.PastResultState;

public class PastResultPresenter implements ResultOutputBoundary {
    private final PastResultViewModel pastResultViewModel;

    public PastResultPresenter(PastResultViewModel pastresultViewModel) {
        this.pastResultViewModel = pastresultViewModel;
    }
    @Override
    public void prepareSuccessView(ResultOutputData outputData) {
        // Notify the view model about the change

        PastResultState newState = new PastResultState();
        newState.setDescription(outputData.getDescription());
        newState.setId(outputData.getId());
        newState.setProtein(outputData.getName());
        this.pastResultViewModel.setState(newState);
        this.pastResultViewModel.firePropertyChanged("disease");

        System.out.println("Presenter: Success view preparation completed.");
    }

    @Override
    public void prepareFailView(String errorMessage) {
        System.out.println("Presenter: Failed to fetch past results. Error: " + errorMessage);
    }
}


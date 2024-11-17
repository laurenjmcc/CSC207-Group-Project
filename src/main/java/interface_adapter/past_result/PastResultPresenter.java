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
        // Notify the view model about the change
        System.out.println("Presenter: Preparing success view...");
        System.out.println("Presenter: Protein name received: " + outputData.getProtein());
        System.out.println("Presenter: Diseases received: " + outputData.getDiseases());

        PastResultState newState = new PastResultState();
        newState.setProtein(outputData.getProtein());
        newState.setDisease(outputData.getDiseases());
        this.pastResultViewModel.setState(newState);
        this.pastResultViewModel.firePropertyChanged("disease");

        System.out.println("Presenter: Success view preparation completed.");
    }

    @Override
    public void prepareFailView(String errorMessage) {
        System.out.println("Presenter: Failed to fetch past results. Error: " + errorMessage);
    }
}


package interface_adapter.past_result;

import interface_adapter.change_password.LoggedInViewModel;
import use_case.change_password.ChangePasswordOutputData;
import use_case.past_result.ResultOutputBoundary;
import use_case.past_result.ResultOutputData;

import java.util.ArrayList;

public class PastResultPresenter implements ResultOutputBoundary {
    private final PastResultViewModel pastResultViewModel;

    public PastResultPresenter(PastResultViewModel pastresultViewModel) {
        this.pastResultViewModel = pastresultViewModel;
    }
    @Override
    public void prepareSuccessView(ResultOutputData outputData) {

        PastResultState newState = new PastResultState();
        newState.setProtein(outputData.getProtein());
        newState.setDisease(outputData.getDiseases());
        this.pastResultViewModel.setState(newState);
        this.pastResultViewModel.firePropertyChanged("disease");

    }

    @Override
    public void prepareFailView(String errorMessage) {
    }
}


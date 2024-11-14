package interface_adapter.team;

import interface_adapter.ViewManagerModel;
import use_case.team.CreateTeamOutputBoundary;
import use_case.team.CreateTeamOutputData;

/**
 * Presenter for the Create Team Use Case.
 */
public class CreateTeamPresenter implements CreateTeamOutputBoundary {
    private final CreateTeamViewModel viewModel;
    private final ViewManagerModel viewManagerModel;

    public CreateTeamPresenter(CreateTeamViewModel viewModel, ViewManagerModel viewManagerModel) {
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(CreateTeamOutputData outputData) {
        CreateTeamState state = viewModel.getState();
        state.setTeamName(outputData.getTeamName());
        state.setMessage("Team created successfully!");
        viewModel.firePropertyChanged();

        viewManagerModel.setState("logged in");
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        CreateTeamState state = viewModel.getState();
        state.setErrorMessage(errorMessage);
        viewModel.firePropertyChanged();
    }
}

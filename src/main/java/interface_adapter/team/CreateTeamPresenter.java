package interface_adapter.team;

import interface_adapter.ViewManagerModel;
import use_case.team.CreateTeamOutputBoundary;
import use_case.team.CreateTeamOutputData;

/**
 * Presenter for the Create Team Use Case.
 */
public class CreateTeamPresenter implements CreateTeamOutputBoundary {
    private final CreateTeamViewModel viewModel;

    public CreateTeamPresenter(CreateTeamViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void prepareSuccessView(CreateTeamOutputData outputData) {
        CreateTeamState state = new CreateTeamState();
        state.setMessage("Team \"" + outputData.getTeamName() + "\" created successfully!");
        viewModel.setState(state);

    }

    @Override
    public void prepareFailView(String errorMessage) {
        CreateTeamState state = new CreateTeamState();
        state.setErrorMessage(errorMessage);
        viewModel.setState(state);
    }
}

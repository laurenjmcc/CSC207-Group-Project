package interface_adapter.team;

import interface_adapter.ViewManagerModel;
import interface_adapter.ViewModel;

/**
 * ViewModel for the Create Team View.
 */
public class CreateTeamViewModel extends ViewModel<CreateTeamState> {
    private String currentUsername;
    private ViewManagerModel viewManagerModel;

    public CreateTeamViewModel() {
        super("create team");
        setState(new CreateTeamState());
    }

    public String getCurrentUsername() {
        return currentUsername;
    }

    public void setCurrentUsername(String currentUsername) {
        this.currentUsername = currentUsername;
    }

    public ViewManagerModel getViewManagerModel() {
        return viewManagerModel;
    }

    public void setViewManagerModel(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
    }
}



package interface_adapter.team;

import interface_adapter.ViewManagerModel;
import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * ViewModel for the Create Team View.
 */
public class CreateTeamViewModel {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private String currentUsername;
    private ViewManagerModel viewManagerModel;
    private CreateTeamState state = new CreateTeamState();

    public String getViewName() {
        return "create team";
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

    public CreateTeamState getState() {
        return state;
    }

    public void setState(CreateTeamState state) {
        support.firePropertyChange("state", this.state, state);
        this.state = state;
    }
    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }
}



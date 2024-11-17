package interface_adapter.past_result;

import interface_adapter.ViewModel;
import interface_adapter.login.LoginState;
import view.PastResultView;

public class PastResultViewModel extends ViewModel<PastResultState> {
    private String title;
    private String description;
    public PastResultViewModel() {
        super("disease");
        setState(new PastResultState());
    }

}

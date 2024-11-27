package interface_adapter.past_result;

import interface_adapter.ViewModel;

/**
 * ViewModel for the Past Result View.
 */
public class PastResultViewModel extends ViewModel<PastResultState> {

    public PastResultViewModel() {
        super("PastResultView");
        setState(new PastResultState());
    }

    /**
     * Returns the name of the view associated with this ViewModel.
     * @return the view name
     */
    public String getViewName() {
        return "PastResultView";
    }
}

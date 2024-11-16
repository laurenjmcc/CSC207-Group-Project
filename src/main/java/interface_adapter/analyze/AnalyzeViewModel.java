package interface_adapter.analyze;

import interface_adapter.ViewModel;

public class AnalyzeViewModel extends ViewModel<AnalyzeState> {

    public AnalyzeViewModel() {
        super("analyze");
        setState(new AnalyzeState());
    }
}

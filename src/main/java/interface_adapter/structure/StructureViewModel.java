package interface_adapter.structure;

import interface_adapter.ViewModel;

public class StructureViewModel extends ViewModel<StructureState> {

    public StructureViewModel() {
        super("structure");
        setState(new StructureState());
    }
}

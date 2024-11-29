package interface_adapter.structure;

import interface_adapter.signup.SignupController;
import use_case.structure.StructureInputBoundary;

public class StructureController {
    private final StructureInputBoundary structureUseCaseInteractor;

    public StructureController(StructureInputBoundary structureUseCaseInteractor) {
        this.structureUseCaseInteractor = structureUseCaseInteractor;
    }

    public void execute() {
        structureUseCaseInteractor.execute();
    }
}


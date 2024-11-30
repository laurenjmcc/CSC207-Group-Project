package interface_adapter.structure;


import use_case.analyze.AnalyzeInputBoundary;
import use_case.analyze.AnalyzeInputData;
import use_case.structure.StructureInputBoundary;
import use_case.structure.StructureInputData;

import interface_adapter.signup.SignupController;
import use_case.structure.StructureInputBoundary;

public class StructureController {
    private final StructureInputBoundary structureUseCaseInteractor;

    public StructureController(StructureInputBoundary structureUseCaseInteractor) {
        this.structureUseCaseInteractor = structureUseCaseInteractor;
    }


    public void execute(String rawDataPdbID) throws Exception {
        final StructureInputData structureInputData = new StructureInputData(rawDataPdbID);
        structureUseCaseInteractor.execute(structureInputData);

    }
}


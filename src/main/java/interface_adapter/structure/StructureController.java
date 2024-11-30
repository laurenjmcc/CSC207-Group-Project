package interface_adapter.structure;

import use_case.structure.StructureInputBoundary;
import use_case.structure.StructureInputData;


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


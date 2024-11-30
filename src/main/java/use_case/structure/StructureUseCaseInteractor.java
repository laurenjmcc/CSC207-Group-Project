package use_case.structure;

import use_case.analyze.AnalyzeOutputBoundary;

public class StructureUseCaseInteractor implements StructureInputBoundary{

    private final StructureOutputBoundary structurePresenter;

    public StructureUseCaseInteractor(StructureOutputBoundary structurePresenter) {
        this.structurePresenter = structurePresenter;
    }

    /**
     * @param structureInputData
     */
    @Override
    public void execute(StructureInputData structureInputData) throws Exception {
        final String pdbID = structureInputData.getPdbID();
        structurePresenter.success(pdbID);


    }
}

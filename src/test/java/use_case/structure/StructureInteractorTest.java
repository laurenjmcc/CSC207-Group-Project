package use_case.structure;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class StructureInteractorTest {
    @Test
    void testExecuteSuccess() throws Exception {
        // Mock StructureOutputBoundary
        StructureOutputBoundary mockPresenter = new StructureOutputBoundary() {
            @Override
            public void success(String pdbID) throws Exception {
                assertEquals("1XYZ", pdbID); // Validate the PDB ID passed to the presenter
            }
        };

        // Create Interactor with the mock presenter
        StructureUseCaseInteractor interactor = new StructureUseCaseInteractor(mockPresenter);

        // Create input data
        StructureInputData inputData = new StructureInputData("1XYZ");

        // Execute interactor
        interactor.execute(inputData);
    }

    @Test
    void testExecuteFailure() {
        // Mock StructureOutputBoundary
        StructureOutputBoundary mockPresenter = new StructureOutputBoundary() {
            @Override
            public void success(String pdbID) throws Exception {
                throw new Exception("Mock exception for invalid PDB ID");
            }
        };

        // Create Interactor with the mock presenter
        StructureUseCaseInteractor interactor = new StructureUseCaseInteractor(mockPresenter);

        // Create input data with an invalid PDB ID
        StructureInputData inputData = new StructureInputData("INVALID");

        // Assert exception is thrown during execution
        Exception exception = assertThrows(Exception.class, () -> interactor.execute(inputData));
        assertEquals("Mock exception for invalid PDB ID", exception.getMessage());
    }
}

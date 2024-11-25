package use_case.past_result;

import data_access.DiseaseDataAccessFactory;
import data_access.ProteinDataAccessFactory;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ResultInteractorTest {

    @Test
    void successTest() throws Exception {
        String proteinName = "p53";

        DiseaseDataAccessFactory factory = new DiseaseDataAccessFactory();
        ResultOutputBoundary presenter = new ResultOutputBoundary() {
            @Override
            public void prepareSuccessView(ResultOutputData outputData) {
                assertEquals(proteinName, outputData.getProtein(), "Protein name should match");
                assertNotNull(outputData.getDiseases());
                assertEquals(Arrays.asList("Disease1", "Disease2"), outputData.getDiseases());
                assertFalse(outputData.isUseCaseFailed());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case failure is unexpected: " + errorMessage);
            }
        };

        ResultInteractor interactor = new ResultInteractor(factory, presenter);
        ResultInputData inputData = new ResultInputData(proteinName);
        interactor.execute(inputData);
    }

    @Test
    void exceptionThrownTest() {
        String proteinName = "ProteinWithException";
        ProteinDataAccessFactory factory = new ProteinDataAccessFactory();

        ResultOutputBoundary presenter = new ResultOutputBoundary() {
            @Override
            public void prepareSuccessView(ResultOutputData outputData) {
                fail("Use case success is unexpected");
            }

            @Override
            public void prepareFailView(String errorMessage) {

            }
        };

        ResultInteractor interactor = new ResultInteractor(factory, presenter);
        ResultInputData inputData = new ResultInputData(proteinName);
        Exception exception = assertThrows(Exception.class, () -> interactor.execute(inputData));
        assertEquals("Simulated exception", exception.getMessage());
    }
}
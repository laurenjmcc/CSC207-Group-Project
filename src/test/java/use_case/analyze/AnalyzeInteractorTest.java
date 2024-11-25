package use_case.analyze;

import data_access.ProteinDataAccessFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnalyzeInteractorTest {

    @Test
    void successTest() throws Exception {
        String proteinName = "p53";

        ProteinDataAccessFactory factory = new ProteinDataAccessFactory();

        AnalyzeOutputBoundary presenter = new AnalyzeOutputBoundary() {
            @Override
            public void prepareFailView(String s) {
                fail("Use case failure is unexpected: " + s);
            }

            @Override
            public void prepareSuccessView(AnalyzeOutputData outputData) {
                assertEquals(proteinName, outputData.getProteinName());
                assertNotNull(outputData.getProteinDescription());
                assertNotNull(outputData.getAcronym());
                assertNotNull(outputData.getDisease());
                assertFalse(outputData.isUseCaseFailed());
            }
        };
        AnalyzeInteractor interactor = new AnalyzeInteractor(factory, presenter);
        AnalyzeInputData inputData = new AnalyzeInputData(proteinName);
        interactor.execute(inputData);
    }

    @Test
    void exceptionThrownTest() {

        String proteinName = "ProteinWithException";
        ProteinDataAccessFactory factory = new ProteinDataAccessFactory();

        AnalyzeOutputBoundary presenter = new AnalyzeOutputBoundary() {
            @Override
            public void prepareFailView(String s) {
                assertEquals("An error occurred while analyzing the protein.", s);
            }

            @Override
            public void prepareSuccessView(AnalyzeOutputData outputData) {
                fail("Use case success is unexpected");
            }
        };

        AnalyzeInteractor interactor = new AnalyzeInteractor(factory, presenter);
        AnalyzeInputData inputData = new AnalyzeInputData(proteinName);
        assertThrows(Exception.class, () -> interactor.execute(inputData));
    }
}

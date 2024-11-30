package use_case.analyze;

import data_access.ProteinDataAccessFactory;
import data_access.ProteinDataAccessObject;
import entity.AnalysisResult;
import entity.User;
import org.junit.jupiter.api.Test;
import use_case.login.LoginUserDataAccessInterface;
import use_case.past_result.AnalysisResultDataAccessInterface;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class AnalyzeInteractorTest {

    @Test
    void successTest() throws Exception {
        // Mock ProteinDataAccessFactory
        ProteinDataAccessFactory factory = new ProteinDataAccessFactory() {
            @Override
            public ProteinDataAccessObject create(String proteinName) throws Exception {
                return new ProteinDataAccessObject(proteinName) {
                    @Override
                    public boolean successCall(String proteinname) {
                        return true;
                    }

                    @Override
                    public String getProteinDescription() {
                        return "Test Protein Description";
                    }

                    @Override
                    public ArrayList<String> getDisease() {
                        return new ArrayList<>(List.of("Disease1", "Disease2"));
                    }

                    @Override
                    public ArrayList<String> getAcronym() {
                        return new ArrayList<>(List.of("Acronym1", "Acronym2"));
                    }

                    @Override
                    public Map<String, Set<String>> getlocation() {
                        return Map.of("Cell", Set.of("Nucleus", "Cytoplasm"));
                    }
                };
            }
        };

        // Mock AnalyzeOutputBoundary
        AnalyzeOutputBoundary presenter = new AnalyzeOutputBoundary() {
            @Override
            public void prepareFailView(String s) {
                fail("Use case failure is unexpected: " + s);
            }

            @Override
            public void prepareSuccessView(AnalyzeOutputData outputData) {
                assertEquals("p53", outputData.getProteinName());
                assertEquals("Test Protein Description", outputData.getProteinDescription());
                assertEquals(List.of("Acronym1", "Acronym2"), outputData.getAcronym());
                assertEquals(List.of("Disease1", "Disease2"), outputData.getDisease());
                assertFalse(outputData.isUseCaseFailed());
            }
        };

        // Mock AnalysisResultDataAccessInterface
        AnalysisResultDataAccessInterface analysisResultDataAccess = new AnalysisResultDataAccessInterface() {
            @Override
            public void saveResult(AnalysisResult result) {
                assertNotNull(result);
                assertEquals("p53", result.getProteinName());
            }

            @Override
            public List<AnalysisResult> getResultsForUsers(Set<String> usernames) {
                return null; // Not needed for this test
            }
        };

        // Mock LoginUserDataAccessInterface
        LoginUserDataAccessInterface userDataAccess = new LoginUserDataAccessInterface() {
            @Override
            public boolean existsByName(String username) {
                return false;
            }

            @Override
            public void save(User user) {
                // Not needed for this test
            }

            @Override
            public User get(String username) {
                return new User() {
                    @Override
                    public String getName() {
                        return "TestUser";
                    }

                    @Override
                    public String getPassword() {
                        return "password";
                    }

                    @Override
                    public Set<String> getTeamNames() {
                        return Set.of("Team1");
                    }

                    @Override
                    public void addTeamName(String teamName) {
                        // Not needed for this test
                    }
                };
            }

            @Override
            public String getCurrentUsername() {
                return "TestUser";
            }

            @Override
            public void setCurrentUsername(String username) {
                // Not needed for this test
            }
        };

        // Create and execute interactor
        AnalyzeInteractor interactor = new AnalyzeInteractor(factory, presenter, analysisResultDataAccess, userDataAccess);
        AnalyzeInputData inputData = new AnalyzeInputData("p53");
        interactor.execute(inputData);

        // Directly invoke inherited interface methods for 100% method coverage
        assertEquals("", interactor.getProteinname());
        assertNull(interactor.getDisease());
        assertNull(interactor.getAcronym());
        assertTrue(interactor.getlocation().isEmpty());
        assertEquals("", interactor.getProteinDescription());
        assertFalse(interactor.successCall("nonexistent"));
    }

    @Test
    void failureTest() throws Exception {
        // Mock ProteinDataAccessFactory
        ProteinDataAccessFactory factory = new ProteinDataAccessFactory() {
            @Override
            public ProteinDataAccessObject create(String proteinName) throws Exception {
                return new ProteinDataAccessObject(proteinName) {
                    @Override
                    public boolean successCall(String proteinname) {
                        return false; // Simulate failure
                    }
                };
            }
        };

        // Mock AnalyzeOutputBoundary
        AnalyzeOutputBoundary presenter = new AnalyzeOutputBoundary() {
            @Override
            public void prepareFailView(String s) {
                assertEquals("Sorry! couldn't find the protein p53 in the database.", s);
            }

            @Override
            public void prepareSuccessView(AnalyzeOutputData outputData) {
                fail("Use case success is unexpected.");
            }
        };

        // Mock AnalysisResultDataAccessInterface and LoginUserDataAccessInterface
        AnalysisResultDataAccessInterface analysisResultDataAccess = new AnalysisResultDataAccessInterface() {
            @Override
            public void saveResult(AnalysisResult result) {
                fail("Result should not be saved on failure.");
            }

            @Override
            public List<AnalysisResult> getResultsForUsers(Set<String> usernames) {
                return null; // Not needed for this test
            }
        };

        LoginUserDataAccessInterface userDataAccess = new LoginUserDataAccessInterface() {
            @Override
            public boolean existsByName(String username) {
                return false;
            }

            @Override
            public void save(User user) {
                // Not needed for this test
            }

            @Override
            public User get(String username) {
                return null; // Simulate no user found
            }

            @Override
            public String getCurrentUsername() {
                return "TestUser";
            }

            @Override
            public void setCurrentUsername(String username) {
                // Not needed for this test
            }
        };

        // Create and execute interactor
        AnalyzeInteractor interactor = new AnalyzeInteractor(factory, presenter, analysisResultDataAccess, userDataAccess);
        AnalyzeInputData inputData = new AnalyzeInputData("p53");
        interactor.execute(inputData);

        // Directly invoke inherited interface methods for coverage
        assertEquals("", interactor.getProteinname());
        assertNull(interactor.getDisease());
        assertNull(interactor.getAcronym());
        assertTrue(interactor.getlocation().isEmpty());
        assertEquals("", interactor.getProteinDescription());
        assertFalse(interactor.successCall("nonexistent"));
    }
}
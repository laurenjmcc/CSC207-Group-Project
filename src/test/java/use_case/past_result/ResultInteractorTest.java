package use_case.past_result;

import entity.AnalysisResult;
import entity.Team;
import entity.User;
import org.junit.jupiter.api.Test;
import use_case.login.LoginUserDataAccessInterface;
import use_case.team.TeamDataAccessInterface;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ResultInteractorTest {

    @Test
    void successTest() throws Exception {
        // Mock output boundary
        ResultOutputBoundary successPresenter = new ResultOutputBoundary() {
            @Override
            public void prepareSuccessView(ResultOutputData data) {
                // Verify results are passed correctly
                assertEquals(1, data.getAnalysisResults().size());
                assertEquals("P53", data.getAnalysisResults().get(0).getProteinName());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        // Mock user data access
        LoginUserDataAccessInterface userDataAccess = new LoginUserDataAccessInterface() {
            private String currentUsername = "TestUser";

            @Override
            public boolean existsByName(String username) {
                return false; // Not needed for this test
            }

            @Override
            public void save(User user) {
                // Not needed for this test
            }

            @Override
            public User get(String username) {
                return new User() {
                    private final Set<String> teamNames = new HashSet<>(Collections.singletonList("TeamA"));

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
                        return teamNames;
                    }

                    @Override
                    public void addTeamName(String teamName) {
                        teamNames.add(teamName);
                    }
                };
            }

            @Override
            public String getCurrentUsername() {
                return currentUsername;
            }

            @Override
            public void setCurrentUsername(String username) {
                this.currentUsername = username;
            }
        };

        // Mock team data access
        TeamDataAccessInterface teamDataAccess = new TeamDataAccessInterface() {
            @Override
            public boolean teamExists(String teamName) {
                return "TeamA".equals(teamName); // Simulate existing team
            }

            @Override
            public void saveTeam(Team team) {
                // Simulate saving a team
            }

            @Override
            public Team getTeam(String teamName) {
                if ("TeamA".equals(teamName)) {
                    return new Team("TeamA", new HashSet<>(Arrays.asList("TestUser", "TeamMember1")));
                }
                return null;
            }
        };

        // Mock analysis result data access
        AnalysisResultDataAccessInterface resultDataAccess = new AnalysisResultDataAccessInterface() {
            @Override
            public void saveResult(AnalysisResult result) throws IOException {
                // Simulate saving a result
            }

            @Override
            public List<AnalysisResult> getResultsForUsers(Set<String> usernames) throws IOException {
                if (usernames.contains("TestUser")) {
                    return List.of(
                            new AnalysisResult(
                                    "TestUser",
                                    List.of("TeamA"),
                                    "P53",
                                    LocalDateTime.now(),
                                    Map.of("mutation", "missense_variant")
                            )
                    );
                }
                return Collections.emptyList();
            }
        };

        // Create and execute interactor
        ResultInteractor interactor = new ResultInteractor(successPresenter, resultDataAccess, userDataAccess, teamDataAccess);
        interactor.execute();
    }

    @Test
    void failureTest() throws Exception {
        // Mock output boundary
        ResultOutputBoundary failurePresenter = new ResultOutputBoundary() {
            @Override
            public void prepareSuccessView(ResultOutputData data) {
                fail("Success view is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                // Verify error message
                assertEquals("No results found for user: TestUser", error);
            }
        };

        // Mock user data access
        LoginUserDataAccessInterface userDataAccess = new LoginUserDataAccessInterface() {
            private String currentUsername = "TestUser";

            @Override
            public boolean existsByName(String username) {
                return false; // Not needed for this test
            }

            @Override
            public void save(User user) {
                // Not needed for this test
            }

            @Override
            public User get(String username) {
                return new User() {
                    private final Set<String> teamNames = new HashSet<>();

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
                        return teamNames; // Simulate no associated teams
                    }

                    @Override
                    public void addTeamName(String teamName) {
                        teamNames.add(teamName);
                    }
                };
            }

            @Override
            public String getCurrentUsername() {
                return currentUsername;
            }

            @Override
            public void setCurrentUsername(String username) {
                this.currentUsername = username;
            }
        };

        // Mock team data access
        TeamDataAccessInterface teamDataAccess = new TeamDataAccessInterface() {
            @Override
            public boolean teamExists(String teamName) {
                return false; // Simulate no existing teams
            }

            @Override
            public void saveTeam(Team team) {
                // Not needed for this test
            }

            @Override
            public Team getTeam(String teamName) {
                return null; // Simulate no team found
            }
        };

        // Mock analysis result data access
        AnalysisResultDataAccessInterface resultDataAccess = new AnalysisResultDataAccessInterface() {
            @Override
            public void saveResult(AnalysisResult result) throws IOException {
                // Not needed for this test
            }

            @Override
            public List<AnalysisResult> getResultsForUsers(Set<String> usernames) throws IOException {
                return Collections.emptyList(); // Ensure no results are returned
            }
        };

        // Create and execute interactor
        ResultInteractor interactor = new ResultInteractor(failurePresenter, resultDataAccess, userDataAccess, teamDataAccess);
        interactor.execute();
    }
}
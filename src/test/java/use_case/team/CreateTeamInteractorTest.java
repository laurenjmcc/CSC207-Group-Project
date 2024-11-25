package use_case.team;

import data_access.InMemoryTeamDataAccessObject;
import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.Team;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.login.LoginUserDataAccessInterface;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CreateTeamInteractorTest {

    @Test
    void successTest() {
        TeamDataAccessInterface teamDataAccess = new InMemoryTeamDataAccessObject();
        LoginUserDataAccessInterface userDataAccess = new InMemoryUserDataAccessObject();
        UserFactory userFactory = new CommonUserFactory();

        User user1 = userFactory.create("Alice", "password1");
        User user2 = userFactory.create("Bob", "password2");
        userDataAccess.save(user1);
        userDataAccess.save(user2);

        CreateTeamOutputBoundary presenter = new CreateTeamOutputBoundary() {
            @Override
            public void prepareSuccessView(CreateTeamOutputData outputData) {
                assertEquals("TeamX", outputData.getTeamName(), "Team name should match");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case failure is unexpected: " + errorMessage);
            }
        };

        CreateTeamInputBoundary interactor = new CreateTeamInteractor(teamDataAccess, presenter, userDataAccess);
        CreateTeamInputData inputData = new CreateTeamInputData("TeamX",
                Arrays.asList("Alice", "Bob"), "Alice");
        interactor.execute(inputData);
        assertTrue(teamDataAccess.teamExists("TeamX"));

        Team team = teamDataAccess.getTeam("TeamX");
        Set<String> expectedMembers = new HashSet<>(Arrays.asList("Alice", "Bob"));
        assertEquals(expectedMembers, team.getMemberUsernames());
        User updatedUser1 = userDataAccess.get("Alice");
        assertTrue(updatedUser1.getTeamNames().contains("TeamX"));

        User updatedUser2 = userDataAccess.get("Bob");
        assertTrue(updatedUser2.getTeamNames().contains("TeamX"));
    }

    @Test
    void failTeamAlreadyExistsTest() {
        TeamDataAccessInterface teamDataAccess = new InMemoryTeamDataAccessObject();
        LoginUserDataAccessInterface userDataAccess = new InMemoryUserDataAccessObject();
        UserFactory userFactory = new CommonUserFactory();

        Team existingTeam = new Team("TeamX", new HashSet<>(Arrays.asList("Alice", "Bob")));
        teamDataAccess.saveTeam(existingTeam);

        CreateTeamOutputBoundary presenter = new CreateTeamOutputBoundary() {
            @Override
            public void prepareSuccessView(CreateTeamOutputData outputData) {
                fail("Use case success is unexpected");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Team name already exists.", errorMessage);
            }
        };

        CreateTeamInputBoundary interactor = new CreateTeamInteractor(teamDataAccess, presenter, userDataAccess);
        CreateTeamInputData inputData = new CreateTeamInputData("TeamX", Arrays.asList("Alice", "Bob"), "Alice");
        interactor.execute(inputData);
        assertEquals(existingTeam, teamDataAccess.getTeam("TeamX"));
    }

    @Test
    void failureUserDoesNotExistTest() {
        TeamDataAccessInterface teamDataAccess = new InMemoryTeamDataAccessObject();
        LoginUserDataAccessInterface userDataAccess = new InMemoryUserDataAccessObject();
        UserFactory userFactory = new CommonUserFactory();

        User user1 = userFactory.create("Alice", "password1");
        userDataAccess.save(user1);

        CreateTeamOutputBoundary presenter = new CreateTeamOutputBoundary() {
            @Override
            public void prepareSuccessView(CreateTeamOutputData outputData) {
                fail("Use case success is unexpected");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("User Bob does not exist.", errorMessage);
            }
        };

        CreateTeamInputBoundary interactor = new CreateTeamInteractor(teamDataAccess, presenter, userDataAccess);
        CreateTeamInputData inputData = new CreateTeamInputData("TeamY", Arrays.asList("Alice", "Bob"), "Alice");
        interactor.execute(inputData);
        assertFalse(teamDataAccess.teamExists("TeamY"));
    }
    @Test
    void failureCreatorNotInMemberListTest() {
        TeamDataAccessInterface teamDataAccess = new InMemoryTeamDataAccessObject();
        LoginUserDataAccessInterface userDataAccess = new InMemoryUserDataAccessObject();
        UserFactory userFactory = new CommonUserFactory();

        User user1 = userFactory.create("Alice", "password1");
        User user2 = userFactory.create("Bob", "password2");
        userDataAccess.save(user1);
        userDataAccess.save(user2);

        CreateTeamOutputBoundary presenter = new CreateTeamOutputBoundary() {
            @Override
            public void prepareSuccessView(CreateTeamOutputData outputData) {
                assertEquals("TeamZ", outputData.getTeamName());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case failure is unexpected: " + errorMessage);
            }
        };

        CreateTeamInputBoundary interactor = new CreateTeamInteractor(teamDataAccess, presenter, userDataAccess);
        CreateTeamInputData inputData = new CreateTeamInputData("TeamZ", Arrays.asList("Alice", "Bob"), "Charlie");

        interactor.execute(inputData);
        assertTrue(teamDataAccess.teamExists("TeamZ"));

        Team team = teamDataAccess.getTeam("TeamZ");
        Set<String> expectedMembers = new HashSet<>(Arrays.asList("Alice", "Bob"));
        assertEquals(expectedMembers, team.getMemberUsernames());
        assertNull(userDataAccess.get("Lauren"));
    }
}

package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CommonUserTest {
    private CommonUser user;

    @BeforeEach
    void setUp() {
        user = new CommonUser("name", "password");
    }

    @Test
    void testConstructor() {
        assertEquals("name", user.getName());
        assertEquals("password", user.getPassword());
        assertNotNull(user.getTeamNames());
        assertTrue(user.getTeamNames().isEmpty());
    }

    @Test
    void testGetName() {
        assertEquals("name", user.getName());
    }

    @Test
    void testGetPassword() {
        assertEquals("password", user.getPassword());
    }

    @Test
    void testSetPassword() {
        user.setPassword("newPassword");
        assertEquals("newPassword", user.getPassword());
    }

    @Test
    void testGetTeamNamesEmpty() {
        Set<String> teamNames = user.getTeamNames();
        assertTrue(teamNames.isEmpty());
    }

    @Test
    void testAddTeamName() {
        user.addTeamName("testTeam");
        Set<String> teamNames = user.getTeamNames();
        assertEquals(1, teamNames.size());
        assertTrue(teamNames.contains("testTeam"));
    }

    @Test
    void testAddDuplicateTeamNames() {
        user.addTeamName("TeamA");
        user.addTeamName("TeamA");
        Set<String> teamNames = user.getTeamNames();
        assertEquals(1, teamNames.size());
        assertTrue(teamNames.contains("TeamA"));
    }

    @Test
    void testAddNullTeamName() {
        user.addTeamName(null);
        Set<String> teamNames = user.getTeamNames();
        assertTrue(teamNames.contains(null));
    }
}
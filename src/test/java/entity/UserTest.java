package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private User user;

    @BeforeEach
    void setUp() {
        user = new CommonUser("name", "password");
    }

    @Test
    void getName() {
        assertEquals("name", user.getName());
    }

    @Test
    void getPassword() {
        assertEquals("password", user.getPassword());
    }

    @Test
    void getTeamNames() {
        Set<String> teamNames = user.getTeamNames();
        assertTrue(teamNames.isEmpty());
    }

    @Test
    void addTeamName() {
        user.addTeamName("team1");
        assertEquals(1, user.getTeamNames().size());
    }
}
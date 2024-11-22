package entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserFactoryTest {
    private UserFactory userFactory;

    @BeforeEach
    void setUp() {
        userFactory = new CommonUserFactory();
    }

    @Test
    void create() {
        String name = "testUser";
        String password = "testPassword";
        User user = userFactory.create(name, password);
        assertNotNull(user);
        assertInstanceOf(CommonUser.class, user);
        assertEquals(name, user.getName());
        assertEquals(password, user.getPassword());
        assertNotNull(user.getTeamNames());
        assertTrue(user.getTeamNames().isEmpty());
    }
}
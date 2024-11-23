package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommonUserFactoryTest {
    private CommonUserFactory userFactory;

    @BeforeEach
    void setUp() {
        userFactory = new CommonUserFactory();
    }

    @Test
    void testCreateUserValidInput() {
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

    @Test
    void testCreateMultipleUsers() {
        User user1 = userFactory.create("testUser1", "testPassword1");
        User user2 = userFactory.create("testUser2", "testPassword2");
        assertNotSame(user1, user2);
        assertEquals("testUser1", user1.getName());
        assertEquals("testPassword1", user1.getPassword());
        assertEquals("testUser2", user2.getName());
        assertEquals("testPassword2", user2.getPassword());
    }

    @Test
    void testCreateUserSpecialCharacterWhiteSpaceName() {
        String name = "  user!@#$%^&*()_+  ";
        String password = "  pass!@#$%^&*()_+  ";

        User user = userFactory.create(name, password);
        assertNotNull(user);
        assertInstanceOf(CommonUser.class, user);
        assertEquals(name, user.getName());
        assertEquals(password, user.getPassword());
        assertNotNull(user.getTeamNames());
        assertTrue(user.getTeamNames().isEmpty());

    }
}
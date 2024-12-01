package use_case.change_password;

import entity.User;
import entity.CommonUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.login.LoginUserDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ChangePasswordInteractorTest {

    private ChangePasswordInteractor interactor;
    private TestUserRepository userRepository;
    private TestChangePasswordPresenter presenter;

    @BeforeEach
    void setUp() {
        userRepository = new TestUserRepository();
        presenter = new TestChangePasswordPresenter();
        interactor = new ChangePasswordInteractor(userRepository, presenter);
    }

    @Test
    void testChangePasswordSuccess() {
        String username = "testUser";
        String oldPassword = "oldPass";
        String newPassword = "newPass";
        String confirmPassword = "newPass";

        userRepository.addUser(new CommonUser(username, oldPassword));
        userRepository.setCurrentUsername(username);

        interactor.execute(new ChangePasswordInputData(oldPassword, newPassword, confirmPassword));
        User updatedUser = userRepository.get(username);
        assertEquals(newPassword, updatedUser.getPassword());
        assertNull(presenter.errorMessage);
        assertNotNull(presenter.outputData);
        assertEquals(username, presenter.outputData.getUsername());
        assertFalse(presenter.outputData.isUseCaseFailed());
    }

    @Test
    void testChangePasswordIncorrectOldPassword() {
        String username = "testUser";
        String oldPassword = "wrongOldPass";
        String newPassword = "newPass";
        String confirmPassword = "newPass";

        userRepository.addUser(new CommonUser(username, "correctOldPass"));
        userRepository.setCurrentUsername(username);

        interactor.execute(new ChangePasswordInputData(oldPassword, newPassword, confirmPassword));
        User user = userRepository.get(username);
        assertEquals("correctOldPass", user.getPassword());
        assertEquals("Incorrect old password.", presenter.errorMessage);
        assertNull(presenter.outputData);
    }

    @Test
    void testChangePasswordNewPasswordsDoNotMatch() {
        String username = "testUser";
        String oldPassword = "oldPass";
        String newPassword = "newPass";
        String confirmPassword = "differentNewPass";

        userRepository.addUser(new CommonUser(username, oldPassword));
        userRepository.setCurrentUsername(username);
        interactor.execute(new ChangePasswordInputData(oldPassword, newPassword, confirmPassword));
        User user = userRepository.get(username);
        assertEquals(oldPassword, user.getPassword());
        assertEquals("New passwords do not match.", presenter.errorMessage);
        assertNull(presenter.outputData);
    }

    @Test
    void testChangePasswordNoUserLoggedIn() {
        String oldPassword = "anyOldPass";
        String newPassword = "newPass";
        String confirmPassword = "newPass";

        interactor.execute(new ChangePasswordInputData(oldPassword, newPassword, confirmPassword));
        assertEquals("No user is currently logged in.", presenter.errorMessage);
        assertNull(presenter.outputData);
    }

    private static class TestUserRepository implements LoginUserDataAccessInterface {

        private final Map<String, User> users = new HashMap<>();
        private String currentUsername;

        void addUser(User user) {
            users.put(user.getName(), user);
        }

        @Override
        public boolean existsByName(String username) {
            return users.containsKey(username);
        }

        @Override
        public void save(User user) {
            users.put(user.getName(), user);
        }

        @Override
        public User get(String username) {
            return users.get(username);
        }

        @Override
        public String getCurrentUsername() {
            return currentUsername;
        }

        @Override
        public void setCurrentUsername(String username) {
            this.currentUsername = username;
        }
    }

    private static class TestChangePasswordPresenter implements ChangePasswordOutputBoundary {

        ChangePasswordOutputData outputData;
        String errorMessage;

        @Override
        public void prepareSuccessView(ChangePasswordOutputData outputData) {
            this.outputData = outputData;
        }

        @Override
        public void prepareFailView(String errorMessage) {
            this.errorMessage = errorMessage;
        }
    }
}

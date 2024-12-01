package use_case.change_password;

import entity.User;
import use_case.login.LoginUserDataAccessInterface;

public class ChangePasswordInteractor implements ChangePasswordInputBoundary {
    private final LoginUserDataAccessInterface userRepository;
    private final ChangePasswordOutputBoundary presenter;

    public ChangePasswordInteractor(LoginUserDataAccessInterface userRepository,
                                    ChangePasswordOutputBoundary presenter) {
        this.userRepository = userRepository;
        this.presenter = presenter;
    }

    @Override
    public void execute(ChangePasswordInputData inputData) {
        String username = userRepository.getCurrentUsername();
        if (username == null) {
            presenter.prepareFailView("No user is currently logged in.");
            return;
        }

        User user = userRepository.get(username);
        if (!user.getPassword().equals(inputData.getOldPassword())) {
            presenter.prepareFailView("Incorrect old password.");
            return;
        }

        if (!inputData.getNewPassword().equals(inputData.getConfirmPassword())) {
            presenter.prepareFailView("New passwords do not match.");
            return;
        }

        user.setPassword(inputData.getNewPassword());
        userRepository.save(user);

        ChangePasswordOutputData outputData = new ChangePasswordOutputData(username, false);
        presenter.prepareSuccessView(outputData);
    }
}

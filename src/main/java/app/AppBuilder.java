package app;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import data_access.JSONTeamDataAccessObject;
import data_access.JSONUserDataAccessObject;
import data_access.ProteinDataAccessObject;
import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.analyze.AnalyzeController;
import interface_adapter.analyze.AnalyzePresenter;
import interface_adapter.analyze.AnalyzeViewModel;
import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.change_password.ChangePasswordPresenter;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.logout.LogoutController;
import interface_adapter.logout.LogoutPresenter;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.team.CreateTeamController;
import interface_adapter.team.CreateTeamPresenter;
import use_case.analyze.AnalyzeInputBoundary;
import use_case.analyze.AnalyzeInteractor;
import use_case.analyze.AnalyzeOutputBoundary;
import interface_adapter.team.CreateTeamViewModel;
import use_case.analyze.AnalyzeProteinDataAccessInterface;
import use_case.change_password.ChangePasswordInputBoundary;
import use_case.change_password.ChangePasswordInteractor;
import use_case.change_password.ChangePasswordOutputBoundary;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutInputBoundary;
import use_case.logout.LogoutInteractor;
import use_case.logout.LogoutOutputBoundary;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupUserDataAccessInterface;
import use_case.team.CreateTeamInputBoundary;
import use_case.team.CreateTeamInteractor;
import use_case.team.CreateTeamOutputBoundary;
import view.*;

public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();

    private final UserFactory userFactory = new CommonUserFactory();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    // Single instance of JSONUserDataAccessObject
    private final JSONUserDataAccessObject userDataAccessObject =
            new JSONUserDataAccessObject("users.json", userFactory);

    private final JSONTeamDataAccessObject teamDataAccessObject =
            new JSONTeamDataAccessObject("teams.json");

    private SignupView signupView;
    private SignupViewModel signupViewModel;
    private LoginViewModel loginViewModel;
    private LoggedInViewModel loggedInViewModel;
    private LoggedInView loggedInView;
    private LoginView loginView;
    private CreateTeamView createTeamView;
    private CreateTeamViewModel createTeamViewModel;
    private AnalyzeView analyzeView;
    private AnalyzeViewModel analyzeViewModel;

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    public AppBuilder addLoginView() {
        loginViewModel = new LoginViewModel();
        loginView = new LoginView(loginViewModel);

        LoginUserDataAccessInterface userDataAccess = userDataAccessObject;
        LoginOutputBoundary loginPresenter = new LoginPresenter(viewManagerModel, loggedInViewModel, loginViewModel);

        LoginInteractor loginInteractor = new LoginInteractor(userDataAccess, loginPresenter);
        LoginController loginController = new LoginController(loginInteractor, viewManagerModel, userDataAccessObject);

        loginView.setLoginController(loginController);

        cardPanel.add(loginView, "login");
        return this;
    }

    public AppBuilder addSignupView() {
        signupViewModel = new SignupViewModel();
        signupView = new SignupView(signupViewModel);

        SignupUserDataAccessInterface userDataAccess = userDataAccessObject;

        SignupOutputBoundary signupPresenter = new SignupPresenter(viewManagerModel, signupViewModel, loginViewModel);
        SignupInputBoundary signupInteractor = new SignupInteractor(userDataAccess, signupPresenter, userFactory);

        // Pass userDataAccessObject to SignupController
        SignupController signupController = new SignupController(signupInteractor, viewManagerModel, userDataAccessObject);
        signupView.setSignupController(signupController);

        cardPanel.add(signupView, "sign up");
        return this;
    }

    public AppBuilder addLoggedInView() {
        loggedInViewModel = new LoggedInViewModel();
        loggedInView = new LoggedInView(loggedInViewModel);
        loggedInView.setViewManagerModel(viewManagerModel);
        cardPanel.add(loggedInView, loggedInView.getViewName());
        return this;
    }

    public AppBuilder addAnalyzeView() {
        analyzeViewModel = new AnalyzeViewModel();
        analyzeView = new AnalyzeView(analyzeViewModel);
        cardPanel.add(analyzeView, analyzeView.getViewName());
        return this;
    }

    public AppBuilder addLoginUseCase() {
        final LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel,
                loggedInViewModel, loginViewModel);
        final LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary);

        final LoginController loginController = new LoginController(loginInteractor,
                viewManagerModel, userDataAccessObject);
        loginView.setLoginController(loginController);
        return this;
    }

    public AppBuilder addChangePasswordUseCase() {
        final ChangePasswordOutputBoundary changePasswordOutputBoundary =
                new ChangePasswordPresenter(loggedInViewModel);

        final ChangePasswordInputBoundary changePasswordInteractor =
                new ChangePasswordInteractor(userDataAccessObject, changePasswordOutputBoundary, userFactory);

        final ChangePasswordController changePasswordController =
                new ChangePasswordController(changePasswordInteractor);
        loggedInView.setChangePasswordController(changePasswordController);
        return this;
    }

    public AppBuilder addLogoutUseCase() {
        final LogoutOutputBoundary logoutOutputBoundary = new LogoutPresenter(viewManagerModel,
                loggedInViewModel, loginViewModel);

        final LogoutInputBoundary logoutInteractor =
                new LogoutInteractor(userDataAccessObject, logoutOutputBoundary);

        final LogoutController logoutController = new LogoutController(logoutInteractor);
        loggedInView.setLogoutController(logoutController);
        return this;
    }

    public AppBuilder addAnalyzeUseCase() {
        final AnalyzeOutputBoundary analyzeOutputBoundary = new AnalyzePresenter(viewManagerModel,
                analyzeViewModel, loggedInViewModel);
        final AnalyzeProteinDataAccessInterface proteinDataAccessObject = new ProteinDataAccessObject();
        final AnalyzeInputBoundary analyzeInteractor = new AnalyzeInteractor(proteinDataAccessObject, analyzeOutputBoundary);
        final AnalyzeController analyzeController = new AnalyzeController(analyzeInteractor);
        loggedInView.setAnalyzeController(analyzeController);
        return this;
    }

    public AppBuilder addCreateTeamView() {
        createTeamViewModel = new CreateTeamViewModel();
        createTeamViewModel.setViewManagerModel(viewManagerModel);

        // Set currentUsername from userDataAccessObject
        createTeamViewModel.setCurrentUsername(userDataAccessObject.getCurrentUsername());

        createTeamView = new CreateTeamView(createTeamViewModel);
        cardPanel.add(createTeamView, createTeamView.getViewName());
        return this;
    }

    public AppBuilder addCreateTeamUseCase() {
        final CreateTeamOutputBoundary createTeamOutputBoundary =
                new CreateTeamPresenter(createTeamViewModel);

        final CreateTeamInputBoundary createTeamInteractor =
                new CreateTeamInteractor(teamDataAccessObject, createTeamOutputBoundary, userDataAccessObject);

        final CreateTeamController createTeamController = new CreateTeamController(createTeamInteractor);
        createTeamView.setController(createTeamController);
        return this;
    }

    public JFrame build() {
        final JFrame application = new JFrame("SequenceIQ");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        application.setContentPane(cardPanel);
        application.pack();
        application.setLocationRelativeTo(null);

        viewManagerModel.setState("login");
        viewManagerModel.firePropertyChanged();

        return application;
    }
}

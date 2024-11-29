package app;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import data_access.*;
import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.analyze.AnalyzeController;
import interface_adapter.analyze.AnalyzePresenter;
import interface_adapter.analyze.AnalyzeState;
import interface_adapter.analyze.AnalyzeViewModel;
import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.change_password.ChangePasswordPresenter;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.logout.LogoutController;
import interface_adapter.logout.LogoutPresenter;
import interface_adapter.past_result.PastResultController;
import interface_adapter.past_result.PastResultPresenter;
import interface_adapter.past_result.PastResultViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.structure.StructureViewModel;
import interface_adapter.team.CreateTeamController;
import interface_adapter.team.CreateTeamPresenter;
import use_case.analyze.AnalyzeInputBoundary;
import use_case.analyze.AnalyzeInteractor;
import use_case.analyze.AnalyzeOutputBoundary;
import use_case.analyze.AnalyzeProteinDataAccessInterface;
import interface_adapter.team.CreateTeamViewModel;
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
import use_case.past_result.ResultInputBoundary;
import use_case.past_result.ResultInteractor;
import use_case.past_result.ResultOutputBoundary;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupUserDataAccessInterface;
import use_case.team.CreateTeamInputBoundary;
import use_case.team.CreateTeamInteractor;
import use_case.team.CreateTeamOutputBoundary;
import view.*;
import data_access.JSONUserDataAccessObject;
import data_access.JSONTeamDataAccessObject;
import data_access.ProteinDataAccessObject;

/**
 * The AppBuilder class is responsible for putting together the pieces of
 * our CA architecture; piece by piece.
 * <p/>
 * This is done by adding each View and then adding related Use Cases.
 */
// Checkstyle note: you can ignore the "Class Data Abstraction Coupling"
//                  and the "Class Fan-Out Complexity" issues for this lab; we encourage
//                  your team to think about ways to refactor the code to resolve these
//                  if your team decides to work with this as your starter code
//                  for your final project this term.
public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    // thought question: is the hard dependency below a problem?
    private final UserFactory userFactory = new CommonUserFactory();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);
    private final JSONUserDataAccessObject userDataAccessObject=
            new JSONUserDataAccessObject("users.json", userFactory);

    private final JSONTeamDataAccessObject teamDataAccessObject =
            new JSONTeamDataAccessObject("teams.json");

    // thought question: is the hard dependency below a problem?


    private SignupView signupView;
    private SignupViewModel signupViewModel;
    private LoginViewModel loginViewModel;
    private LoggedInViewModel loggedInViewModel;
    private LoggedInView loggedInView;
    private LoginView loginView;
    private CreateTeamView createTeamView;
    private CreateTeamViewModel createTeamViewModel;
    private PastResultViewModel pastResultViewModel;
    private PastResultView pastResultsView;


    private AnalyzeView analyzeView;
    private AnalyzeViewModel analyzeViewModel;

    private StructureView structureView;
    private StructureViewModel structureViewModel;

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    /**
     * Adds the Login View to the application.
     * @return this builder
     */
    public AppBuilder addLoginView() {
        loginViewModel = new LoginViewModel();
        loginView = new LoginView(loginViewModel);
        // Create instances of required dependencies
        LoginUserDataAccessInterface userDataAccess = new InMemoryUserDataAccessObject();
        LoginOutputBoundary loginPresenter = new LoginPresenter(viewManagerModel, loggedInViewModel, loginViewModel);

        LoginInteractor loginInteractor = new LoginInteractor(userDataAccess, loginPresenter);
        LoginController loginController = new LoginController(loginInteractor, viewManagerModel,userDataAccess);

        loginView.setLoginController(loginController);

        cardPanel.add(loginView, "login");  // "login" identifier for LoginView
        return this;
    }

    /**
     * Adds the Signup View to the application.
     * @return this builder
     */
    public AppBuilder addSignupView() {
        signupViewModel = new SignupViewModel();
        signupView = new SignupView(signupViewModel);

        SignupUserDataAccessInterface userDataAccess = userDataAccessObject;

        SignupOutputBoundary signupPresenter = new SignupPresenter(viewManagerModel, signupViewModel, loginViewModel);
        SignupInputBoundary signupInteractor = new SignupInteractor(userDataAccess, signupPresenter, userFactory);

        SignupController signupController = new SignupController(signupInteractor, viewManagerModel, userDataAccessObject);
        signupView.setSignupController(signupController);

        // Add the signup view to the card panel
        cardPanel.add(signupView, "sign up");
        return this;
    }



    /**
     * Adds the LoggedIn View to the application.
     * @return this builder
     */
    public AppBuilder addLoggedInView() {
        loggedInViewModel = new LoggedInViewModel();
        loggedInView = new LoggedInView(loggedInViewModel);
        loggedInView.setViewManagerModel(viewManagerModel);
        cardPanel.add(loggedInView, loggedInView.getViewName());
        return this;
    }

    public AppBuilder addAnalyzeView() {
        analyzeViewModel = new AnalyzeViewModel();
        LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
        AnalyzePresenter analyzePresenter = new AnalyzePresenter(viewManagerModel, analyzeViewModel, loggedInViewModel);

        // Initialize View and inject Presenter
        analyzeView = new AnalyzeView(analyzeViewModel);
        analyzeView.setAnalyzePresenter(analyzePresenter); // Back button depends on this



        cardPanel.add(analyzeView, analyzeView.getViewName());
        return this;
    }

    // CODE SMELLS BEGIN - WARNING!! This is incomplete
    public AppBuilder addStructureView() {
        structureViewModel = new StructureViewModel();

        structureView = new StructureView(structureViewModel);

        cardPanel.add(structureView, structureView.getViewName());
        return this;
    }

    // CODE SMELLS END
    public AppBuilder addPastResultsView() {
        pastResultViewModel = new PastResultViewModel();
        pastResultsView = new PastResultView(pastResultViewModel);
        cardPanel.add(pastResultsView, "PastResultView");
        return this;
    }

    /**
     * Adds the Login Use Case to the application.
     * @return this builder
     */
    public AppBuilder addLoginUseCase() {
        final LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel,
                loggedInViewModel, loginViewModel);
        final LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary);

        // Pass viewManagerModel to LoginController
        final LoginController loginController = new LoginController(loginInteractor,
                viewManagerModel,userDataAccessObject);
        loginView.setLoginController(loginController);
        return this;
    }

    /**
     * Adds the Change Password Use Case to the application.
     * @return this builder
     */
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

    /**
     * Adds the Logout Use Case to the application.
     * @return this builder
     */
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
        ProteinDataAccessFactory factory = new ProteinDataAccessFactory();
        final AnalyzeInputBoundary analyzeInteractor = new AnalyzeInteractor(factory, analyzeOutputBoundary);
        final AnalyzeController analyzeController = new AnalyzeController(analyzeInteractor);
        loggedInView.setAnalyzeController(analyzeController);
        return this;
    }
    public AppBuilder addPastResultsUseCase() {
        DiseaseDataAccessFactory factory = new DiseaseDataAccessFactory();
        final ResultOutputBoundary resultOutputBoundary = new PastResultPresenter(pastResultViewModel);
        final ResultInputBoundary resultInteractor = new ResultInteractor(factory, resultOutputBoundary);
        final PastResultController pastResultController = new PastResultController(resultInteractor);
        loggedInView.setPastResultController(pastResultController);
        pastResultsView.setPastResultController(pastResultController);
        return this;
    }

    public AppBuilder addCreateTeamView() {
        createTeamViewModel = new CreateTeamViewModel();
        createTeamViewModel.setViewManagerModel(viewManagerModel);
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

    /**
     * Creates the JFrame for the application and initially sets the SignupView to be displayed.
     * @return the application
     */
    public JFrame build() {
        final JFrame application = new JFrame("ProteinVerse");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        application.setContentPane(cardPanel);
        application.pack();
        application.setLocationRelativeTo(null); // Center on screen



        viewManagerModel.setState("login");
        viewManagerModel.firePropertyChanged();

        return application;
    }
}


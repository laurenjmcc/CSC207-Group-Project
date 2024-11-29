package app;

import javax.swing.JFrame;
import java.awt.*;

/**
 * The Main class of our application.
 */
public class Main {
    /**
     * Builds and runs the CA architecture of the application.
     * @param args unused arguments
     */
    public static void main(String[] args) {
        final AppBuilder appBuilder = new AppBuilder();
        final JFrame application = appBuilder
                                            .addLoginView()
                                            .addSignupView()
                                            .addLoggedInView()
                                            .addAnalyzeView()
                                            .addPastResultsView()
                                            .addCreateTeamView()
                                            .addStructureView()
                                            .addLoginUseCase()
                                            .addLogoutUseCase()
                                            .addChangePasswordUseCase()
                                            .addAnalyzeUseCase()
                                            .addPastResultsUseCase()
                                            .addCreateTeamUseCase()
                                            .build();
        application.setSize(1440,600);
        application.setVisible(true);
    }
}

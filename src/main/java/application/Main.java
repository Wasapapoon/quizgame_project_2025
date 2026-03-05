package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import pane.RootPane;

import java.util.Objects;

/**
 * The Main class serves as the entry point for the JavaFX application.
 * It initializes the primary stage, sets up the global scene using the RootPane,
 * and configures window properties such as full-screen mode and stylesheets.
 */
public class Main extends Application {

    /**
     * The main method which serves as the static entry point of the program.
     * It calls the launch method to start the JavaFX application lifecycle.
     * @param args Command line arguments passed to the application.
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * The start method initializes the primary Stage of the application.
     * It sets the scene with the Singleton instance of RootPane, applies
     * external CSS for fonts, and enforces full-screen display settings.
     * @param stage The primary stage for this application.
     */
    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(RootPane.getRootPane());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/font.css")).toExternalForm());
        stage.setFullScreen(true);
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.setScene(scene);
        stage.setTitle("QUIZ-GAME");
        stage.setResizable(false);
        stage.show();
    }
}
package utils;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;
import pane.GamePane;
import item.base.BasePuzzle;

/**
 * The DigitalTimer class represents a visual countdown clock in the game UI.
 * It manages the timing logic for puzzles, updates the display text,
 * triggers hint revelations through the GamePane, and handles timeout events.
 */
public class DigitalTimer extends StackPane {

    /** The current number of seconds remaining in the countdown. */
    private int timeRemaining = 30;

    /** The JavaFX Timeline used to execute the countdown logic every second. */
    private Timeline timeline;

    /** The UI Label that displays the current remaining time. */
    private Label timerLabel;

    /** A Runnable task executed when the countdown reaches zero. */
    private Runnable onTimeOut;

    /** Reference to the GamePane to trigger time-based hint updates. */
    private GamePane gamePane;

    /** The specific puzzle currently being timed. */
    private BasePuzzle currentQuestion;

    /** The difficulty level string used to determine hint timing logic. */
    private String difficultyLevel;

    /**
     * Constructs a DigitalTimer with a default label of "30".
     * Configures the visual style, including font, color, and a glow effect.
     */
    public DigitalTimer() {
        timerLabel = new Label("30");
        timerLabel.setFont(Font.font("Impact", FontWeight.BOLD, 120));
        timerLabel.setTextFill(Color.web("#FFFF00"));
        DropShadow glow = new DropShadow();
        glow.setColor(Color.web("#FFFFFF", 0.2));
        glow.setRadius(25);
        glow.setSpread(0.6);
        timerLabel.setEffect(glow);
        setAlignment(Pos.CENTER);
        getChildren().add(timerLabel);
    }

    /**
     * Stores the current game state variables to allow the timer to
     * communicate with the game UI for hint updates.
     * @param pane The current game layout container.
     * @param question The puzzle currently active.
     * @param difficultyLevel The game mode or difficulty string.
     */
    public void setGameContext(GamePane pane, BasePuzzle question, String difficultyLevel) {
        this.gamePane = pane;
        this.currentQuestion = question;
        this.difficultyLevel = difficultyLevel;
    }

    /**
     * Initiates the countdown from a specified number of seconds.
     * Changes the text color to red when time is running low (below 10 seconds).
     * @param seconds The total duration of the countdown.
     */
    public void start(int seconds) {
        stop();
        this.timeRemaining = seconds;
        timerLabel.setTextFill(Color.web("#FFFF00"));
        updateLabel();

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            timeRemaining--;
            updateLabel();


            if (gamePane != null && currentQuestion != null && difficultyLevel != null) {
                gamePane.updateHintsByTime(timeRemaining, currentQuestion, difficultyLevel);
            }

            if (timeRemaining <= 9) {
                timerLabel.setTextFill(Color.RED);
            }
            if (timeRemaining <= 0) {
                stop();
                if (onTimeOut != null) onTimeOut.run();
            }
        }));
        timeline.setCycleCount(seconds);
        timeline.play();
    }

    /**
     * Immediately stops the countdown timeline if it is currently running.
     */
    public void stop() {
        if (timeline != null) timeline.stop();
    }

    /**
     * Updates the text of the timer label to reflect the current remaining time.
     */
    private void updateLabel() {
        timerLabel.setText(String.valueOf(timeRemaining));
    }

    /**
     * Assigns a custom action to be performed once the timer hits zero.
     * @param onTimeOut The logic to be executed upon timeout.
     */
    public void setOnTimeOut(Runnable onTimeOut) {
        this.onTimeOut = onTimeOut;
    }
}
package pane;

import entity.Player;
import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;
import utils.Goto;

import java.util.Objects;

/**
 * The TextPane class provides a user interface component for players to input their answers.
 * It handles answer validation, visual feedback through color changes, sound effects,
 * and controls the game flow based on whether the answer is correct or incorrect.
 */
public class TextPane extends VBox {

    /**
     * The current game mode (e.g., EASY, MEDIUM, HARD, BATTLE, or EXTREME)
     * which determines the specific game logic and transition rules.
     */
    private String gameMode;

    /**
     * The original prompt text to be displayed when the input field is empty.
     */
    private String originalPrompt;

    /**
     * The input field where the player types their answer.
     */
    private TextField textField = new TextField();


    /**
     * Constructs a TextPane and initializes the UI layout including the player label,
     * text input field with a custom background, and the submit button.
     * It sets up event handlers for mouse interactions and answer submission logic.
     * @param gameMode The current difficulty or competition mode.
     * @param playerLabelText The text to display identifying the player (e.g., "PLAYER A INPUT").
     * @param currentPlayer The player object who is currently using this input pane.
     * @param opponentPlayer The opposing player object, used for HP deduction in battle mode.
     * @param opponentLifePane The UI component representing the opponent's health, updated upon correct answers.
     */
    public TextPane(String gameMode, String playerLabelText, Player currentPlayer, Player opponentPlayer, LifePane opponentLifePane) {
        this.gameMode = gameMode;
        setAlignment(Pos.CENTER);

        Label playerLabel = new Label(playerLabelText);
        playerLabel.setFont(Font.font("Noto Sans Thai", FontWeight.BOLD, 24));
        playerLabel.setTextFill(Color.WHITE);

        if (gameMode.equals("BATTLE")) {
            if (playerLabelText.contains("PLAYER A")) {
                originalPrompt = "กด TAB เพื่อตอบ";
            } else if (playerLabelText.contains("PLAYER B")) {
                originalPrompt = "กด ENTER เพื่อตอบ";
            } else {
                originalPrompt = "ตอบเป็นภาษาไทย";
            }
        } else {
            originalPrompt = "ตอบเป็นภาษาไทย";
        }
        textField.setPromptText(originalPrompt);
        textField.setPrefWidth(400);
        textField.setMaxWidth(550);
        textField.setPrefHeight(65);
        textField.setMaxHeight(65);
        textField.setFont(Font.font("Noto Sans Thai", FontWeight.SEMI_BOLD, 18));
        textField.setAlignment(Pos.CENTER);
        textField.setBackground(new Background(new BackgroundImage(new Image(Objects.requireNonNull(Goto.class.getResourceAsStream("/textback.png"))),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, true, false))));

        Button button = new Button("Submit");
        button.setPrefHeight(50);
        button.setPrefWidth(180);
        button.setFont(Font.font("Noto Sans Thai", FontWeight.SEMI_BOLD, 20));
        button.setBackground(new Background(new BackgroundImage(new Image(Objects.requireNonNull(Goto.class.getResourceAsStream("/choiceback.png"))),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, true, false))));

        button.setOnMouseExited(mouseEvent -> {
            button.setBackground(new Background(new BackgroundImage(new Image(Objects.requireNonNull(Goto.class.getResourceAsStream("/choiceback.png"))),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                    new BackgroundSize(100, 100, true, true, true, false))));
            button.setTextFill(Color.BLACK);
        });

        button.setOnMouseEntered(mouseEvent -> {
            button.setBackground(new Background(new BackgroundImage(new Image(Objects.requireNonNull(Goto.class.getResourceAsStream("/choiceback2.png"))),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                    new BackgroundSize(100, 100, true, true, true, false))));
            button.setTextFill(Color.WHITE);
        });

        button.setOnAction(actionEvent -> {
            boolean checkAnswer = Goto.checkAnswer(textField.getText(), gameMode, currentPlayer, opponentPlayer, opponentLifePane);
            textField.setBackground(new Background(new BackgroundFill(checkAnswer ? Color.GREEN : Color.RED, new CornerRadii(5), Insets.EMPTY)));

            String soundPath = checkAnswer ? "/sound/correct.mp3" : "/sound/wrong.mp3";
            try {
                Media soundMedia = new Media(Objects.requireNonNull(getClass().getResource(soundPath)).toExternalForm());
                MediaPlayer mediaPlayer = new MediaPlayer(soundMedia);
                mediaPlayer.play();
            } catch (Exception e) {
                System.out.println("Sound file not found");
            }

            PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
            pause.setOnFinished(event -> {
                textField.setBackground(new Background(new BackgroundImage(new Image(Objects.requireNonNull(Goto.class.getResourceAsStream("/textback.png"))),
                        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                        new BackgroundSize(100, 100, true, true, true, false))));

                if (gameMode.equals("EXTREME")) {
                    if (!checkAnswer) {
                        textField.clear();
                        resetPromptText();
                        Goto.resultPage(gameMode);
                    } else {
                        Goto.checkQuiz(gameMode);
                    }
                } else {
                    if (checkAnswer){
                        Goto.checkQuiz(gameMode);
                    }
                    else {
                        if(gameMode.equals("BATTLE")){
                            textField.clear();
                            resetPromptText();
                            textField.setDisable(true);
                        }
                    }

                }
            });
            pause.play();
        });

        getChildren().addAll(playerLabel, textField, button);
    }

    /**
     * Resets the TextField's prompt text to its original value.
     */
    public void resetPromptText() {
        textField.setPromptText(originalPrompt);
    }

    /**
     * Provides access to the TextField component used for player input.
     * This is useful for programmatically focusing the field or clearing text.
     * @return The TextField instance used in this pane.
     */
    public TextField getTextField() {
        return textField;
    }
}
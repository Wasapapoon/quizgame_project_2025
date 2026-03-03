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

public class TextPane extends VBox {
    private String difficultyLevel;

    public TextPane(String difficultyLevel, String playerLabelText, Player currentPlayer, Player opponentPlayer, LifePane opponentLifePane) {
        this.difficultyLevel = difficultyLevel;
        setAlignment(Pos.CENTER);

        Label playerLabel = new Label(playerLabelText);
        playerLabel.setFont(Font.font("Noto Sans Thai", FontWeight.BOLD, 24));
        playerLabel.setTextFill(Color.WHITE);

        TextField textField = new TextField();
        textField.setPromptText("ตอบเป็นภาษาไทย");
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
            boolean checkAnswer = Goto.checkAnswer(textField.getText(), difficultyLevel, currentPlayer, opponentPlayer, opponentLifePane);
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
                if (difficultyLevel.equals("EXTREME")) {
                    if (!checkAnswer) {
                        Goto.scorePage(difficultyLevel);
                        Goto.setAnswerStreak(0);
                    } else {
                        Goto.checkQuiz(difficultyLevel);
                    }
                } else {
                    Goto.checkQuiz(difficultyLevel);
                }
                Goto.setHintClick(false);
            });
            pause.play();
        });

        getChildren().addAll(playerLabel, textField, button);
    }
}
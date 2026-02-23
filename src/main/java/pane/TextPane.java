package pane;

import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Objects;

public class TextPane extends VBox {
    private String difficultyLevel; 

    public TextPane(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel; 
        Goto.initializeWindowSize(Goto.getRootPane());
        
        setSpacing(15);
        setAlignment(Pos.BOTTOM_CENTER);

        Button button = new Button("Submit");
        button.setPrefHeight(50);
        button.setPrefWidth(200);

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

        button.setFont(Font.font("Noto Sans Thai", FontWeight.SEMI_BOLD, 20));

        TextField textField = new TextField();
        textField.setPromptText("ตอบเป็นตัวเลข");
        textField.setPrefWidth(2000);
        textField.setPrefHeight(100);
        textField.setFont(Font.font("Noto Sans Thai", FontWeight.SEMI_BOLD, 16));


        textField.setBackground(new Background(new BackgroundImage(new Image(Objects.requireNonNull(Goto.class.getResourceAsStream("/textback.png"))),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, true, false))));

        VBox.setMargin(textField, new Insets(0, 400, 0, 400));

        button.setOnMouseClicked(mouseEvent -> {
        	
        	Boolean checkAnswer = Goto.checkAnswer(textField.getText(), difficultyLevel);
        	textField.setBackground(new Background(new BackgroundFill(checkAnswer ? Color.GREEN : Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
            String soundPath = checkAnswer ? "/sound/correct.mp3" : "/sound/wrong.mp3";
            Media soundMedia = new Media(Objects.requireNonNull(getClass().getResource(soundPath)).toExternalForm());
            MediaPlayer mediaPlayer = new MediaPlayer(soundMedia);
            mediaPlayer.play();
            
			PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
			pause.setOnFinished(event -> {
				if(difficultyLevel == "EXTREME") {
					if(!checkAnswer) {
						Goto.scorePage(difficultyLevel);
						Goto.setAnswerStreak(0);
					}
					else {
						Goto.checkQuiz(difficultyLevel);
					}
				}
				else {
					Goto.checkQuiz(difficultyLevel);
				}
				
				Goto.setHintClick(false);
				
			});
			pause.play();	
			
        });

        getChildren().add(textField);
        getChildren().add(button);
    }
}

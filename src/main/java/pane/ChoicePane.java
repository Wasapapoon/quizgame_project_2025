package pane;

import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
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
import java.util.ArrayList;
import java.util.Objects;

public class ChoicePane extends HBox {
    private String difficultyLevel; 
    
    public ChoicePane(ArrayList<String> choices, String difficultyLevel) { 
        this.difficultyLevel = difficultyLevel;
        Goto.initializeWindowSize(Goto.getRootPane());
        setAlignment(Pos.CENTER);
        setPadding(new Insets(10));
        int count = choices.size();
        int w = 100;
        int h = 50;
        int margin = 50;

        switch (count) {
            case 2:
                w = 370;
                h = 75;
                margin = 100;
                break;
            case 4:
                w = 300;
                h = 60;
                margin = 15;
                break;
        }
        
        
        for (String choice : choices) {
            Button button = new Button(choice);
            button.setPrefWidth(w);
            button.setPrefHeight(h);
            HBox.setMargin(button, new Insets(Goto.getWindowHeight() - 374, margin, 0, margin));

            button.setBackground(new Background(new BackgroundImage(new Image(Objects.requireNonNull(Goto.class.getResourceAsStream("/choiceback.png"))),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                    new BackgroundSize(100, 100, true, true, true, false))));

            button.setFont(Font.font("Noto Sans Thai", FontWeight.SEMI_BOLD, 20));

            
            button.setOnMouseClicked(mouseEvent -> {
            	
           		Boolean checkAnswer = Goto.checkAnswer(choice, difficultyLevel);

					button.setBackground(new Background(new BackgroundFill(checkAnswer ? Color.GREEN : Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
					button.setTextFill(Color.BLACK);
					button.setOpacity(0.8);

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

            getChildren().add(button);
        }
    }
}

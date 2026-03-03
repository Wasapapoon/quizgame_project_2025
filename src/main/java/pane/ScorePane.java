package pane;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utils.Goto;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Objects;

import static utils.Goto.*;

public class ScorePane extends VBox {
    private String difficultyLevel; 
    
    public ScorePane(int yourScore, String difficultyLevel) {
        this.difficultyLevel = difficultyLevel; 
        initializeWindowSize(getRootPane());
        setAlignment(Pos.CENTER);
        setSpacing(20);
        setPadding(new Insets(32,0,32,0));
                
        Button tryAgain = new Button("Try Again");
        tryAgain.setFont(Font.font("Noto Sans Thai", FontWeight.BOLD, 36));
        tryAgain.setPrefWidth(400);
        tryAgain.setPrefHeight(100);
        tryAgain.setOnMouseClicked(mouseEvent -> {
            playerA.setHp(3);
            playerB.setHp(3);
        	initQuiz(difficultyLevel);
        	setHintClick(false);
        }); 

        Button exit = new Button("Return");
        exit.setFont(Font.font("Noto Sans Thai", FontWeight.BOLD, 36));
        exit.setPrefWidth(400);
        exit.setPrefHeight(100);
        exit.setOnMouseClicked(mouseEvent -> {

            Stage stage = (Stage) getRootPane().getScene().getWindow();
            double width = stage.getWidth();
            double height = stage.getHeight();

            BackgroundImage homeBg = new BackgroundImage(
                new Image(Objects.requireNonNull(Goto.class.getResourceAsStream("/puzzle_background.jpg"))), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(width, height, false, false, false, false));
                getRootPane().setBackground(new Background(homeBg));

            playerA.setHp(3);
            playerB.setHp(3);

            titleScreenPage();
        });

        VBox vBox = new VBox(20);
        vBox.getChildren().addAll(tryAgain,exit);
        vBox.setAlignment(Pos.BOTTOM_CENTER);
        VBox.setMargin(vBox, new Insets(500,0,0,0));

        getChildren().add(vBox);
    }

}

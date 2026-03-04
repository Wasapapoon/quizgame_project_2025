package pane;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import utils.Goto;

import java.util.Objects;

import static utils.Goto.*;

public class ResultPane extends VBox {
    private String difficultyLevel; 
    
    public ResultPane(String difficultyLevel) {
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
            gameTimer.stop();
        	initQuiz(difficultyLevel);
        }); 

        Button exit = new Button("Return");
        exit.setFont(Font.font("Noto Sans Thai", FontWeight.BOLD, 36));
        exit.setPrefWidth(400);
        exit.setPrefHeight(100);
        exit.setOnMouseClicked(mouseEvent -> {

            gameTimer.stop();

            Stage stage = (Stage) getRootPane().getScene().getWindow();
            double width = stage.getWidth();
            double height = stage.getHeight();

            BackgroundImage homeBg = new BackgroundImage(
                new Image(Objects.requireNonNull(Goto.class.getResourceAsStream("/puzzle_background2.png"))), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(width, height, false, false, false, false));
                getRootPane().setBackground(new Background(homeBg));

            titleScreenPage();
        });

        VBox vBox = new VBox(20);
        vBox.getChildren().addAll(tryAgain,exit);
        vBox.setAlignment(Pos.BOTTOM_CENTER);
        VBox.setMargin(vBox, new Insets(500,0,0,0));

        getChildren().add(vBox);
    }

}

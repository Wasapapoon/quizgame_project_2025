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

       
        
        Text scoreText = new Text("YOUR SCORE : " + yourScore );
        scoreText.setFill(Color.WHITE);
        scoreText.setFont(Font.font("Noto Sans Thai", FontWeight.BOLD, 40));
        if (yourScore < 60) {
        	 VBox.setMargin(scoreText, new Insets(50,0,0,0));
        }
        else {
        	 VBox.setMargin(scoreText, new Insets(40,0,0,0));
        }
        
        Image img = null;
        Image star = null;


        if (yourScore >= 230) {

            star = new Image(Objects.requireNonNull(Goto.class.getResourceAsStream("/3_star.png")));
            img = new Image(Objects.requireNonNull(Goto.class.getResourceAsStream("/you_win.png")));
        }
        else if (yourScore >= 140) {

            star = new Image(Objects.requireNonNull(Goto.class.getResourceAsStream("/2_star.png")));
            img = new Image(Objects.requireNonNull(Goto.class.getResourceAsStream("/you_win.png")));
        }
        else if (yourScore >= 60) {

            star = new Image(Objects.requireNonNull(Goto.class.getResourceAsStream("/1_star.png")));
            img = new Image(Objects.requireNonNull(Goto.class.getResourceAsStream("/you_win.png")));
        }

        else {

            star = new Image(Objects.requireNonNull(Goto.class.getResourceAsStream("/0_star.png")));
            img = new Image(Objects.requireNonNull(Goto.class.getResourceAsStream("/game_over.png")));
        }


        ImageView starPic = new ImageView(star);
        starPic.setPreserveRatio(true);
        starPic.setFitHeight(135);
        
        
        ImageView imgPic = new ImageView(img);
        imgPic.setPreserveRatio(true);
        if (yourScore < 60) {
        	imgPic.setFitHeight(100);
        	VBox.setMargin(starPic, new Insets(20,0,0,0));
        }
        else {
        	imgPic.setFitHeight(170);
        	VBox.setMargin(imgPic, new Insets(-30,0,0,0));
        	VBox.setMargin(starPic, new Insets(-20,0,0,0));
        }
                
        Button tryAgain = new Button("Try Again");
        tryAgain.setFont(Font.font("Noto Sans Thai", FontWeight.BOLD, 36));
        tryAgain.setPrefWidth(400);
        tryAgain.setPrefHeight(100);
        tryAgain.setOnMouseClicked(mouseEvent -> {
        	initQuiz(difficultyLevel);
        	setHintClick(false);
        }); 

        Button exit = new Button("Return");
        exit.setFont(Font.font("Noto Sans Thai", FontWeight.BOLD, 36));
        exit.setPrefWidth(400);
        exit.setPrefHeight(100);
        exit.setOnMouseClicked(mouseEvent -> titleScreenPage());

        VBox vBox = new VBox(20);
        vBox.getChildren().addAll(tryAgain,exit);
        vBox.setAlignment(Pos.CENTER);
        if (yourScore < 60) {
        	VBox.setMargin(vBox, new Insets(70,0,0,0));
        }
        else {
        	VBox.setMargin(vBox, new Insets(60,0,0,0));
        }
        
        
        getChildren().addAll(imgPic, starPic, scoreText, vBox);
    }

}

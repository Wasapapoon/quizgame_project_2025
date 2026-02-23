package pane;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import mode.GameLevelSelector;
import utils.Goto;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Objects;

public class TitleScreenPane extends VBox { 
    private Button playButton;
    private GameLevelSelector gameLevelSelector; 

    public TitleScreenPane() {
        setAlignment(Pos.CENTER);
        gameLevelSelector = new GameLevelSelector();


        Image logoImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/logo.PNG")));
        ImageView logo = new ImageView(logoImage);
        logo.setPreserveRatio(true);
        logo.setFitHeight(360);
        VBox.setMargin(logo, new Insets(10,0,0,0));
        getChildren().add(logo);

        Button exitButton = new Button("Exit");
        exitButton.setFont(Font.font("Noto Sans Thai", FontWeight.BOLD, 36));
        exitButton.setPrefWidth(400);
        exitButton.setPrefHeight(100);
        VBox.setMargin(exitButton, new Insets(60,0,0,0));

        exitButton.setOnMouseClicked(e -> Platform.exit());


        playButton = new Button("Play");
        playButton.setFont(Font.font("Noto Sans Thai", FontWeight.BOLD, 36));
        playButton.setPrefWidth(400);
        playButton.setPrefHeight(100);
        playButton.setOnMouseClicked(e -> {
            Goto.levelSelectionPage(gameLevelSelector);
        });
        playButton.setBackground(new Background(new BackgroundFill(Color.GREENYELLOW , CornerRadii.EMPTY, Insets.EMPTY)));
        VBox.setMargin(playButton, new Insets(60,0,0,0));
        playButton.setOnMouseEntered(e -> playButton.setBackground(new Background(new BackgroundFill(Color.YELLOWGREEN , CornerRadii.EMPTY, Insets.EMPTY))));
        playButton.setOnMouseExited(e -> playButton.setBackground(new Background(new BackgroundFill(Color.GREENYELLOW , CornerRadii.EMPTY, Insets.EMPTY))));

        VBox.setMargin(playButton, new Insets(80, 0, 20, 0));
        VBox.setMargin(exitButton, new Insets(15, 0, 50, 0));

        getChildren().addAll(playButton, exitButton);


    }

   
}

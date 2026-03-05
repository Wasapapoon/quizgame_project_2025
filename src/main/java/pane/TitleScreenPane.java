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
import mode.GameModeSelector;
import utils.Goto;

import java.util.Objects;

/**
 * The TitleScreenPane class represents the initial landing page of the game.
 * It serves as the main menu, displaying the game logo and providing options
 * to either start the game by navigating to the mode selection or exit the application.
 */
public class TitleScreenPane extends VBox {

    /**
     * The button used to initiate the transition to the game level selection screen.
     */
    private Button playButton;

    /**
     * The selector object responsible for managing and passing game mode configurations
     * throughout the application.
     */
    private GameModeSelector gameModeSelector;

    /**
     * Constructs the TitleScreenPane, initializing the main menu's layout and components.
     * This includes setting up the game logo image, the "Play" button with hover effects,
     * and the "Exit" button to close the application.
     * It also initializes a new {@link mode.GameModeSelector} instance for the session.
     */
    public TitleScreenPane() {
        setAlignment(Pos.CENTER);
        gameModeSelector = new GameModeSelector();


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
            Goto.levelSelectionPage(gameModeSelector);
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

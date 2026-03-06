package pane;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import utils.Goto;

import java.util.Objects;

import static utils.Goto.*;

/**
 * ResultPane is a UI component that displays the game over screen or final results.
 * It provides options for players to either restart the game in the current mode
 * or return to the title screen.
 */
public class ResultPane extends VBox {

    /** The specific game difficulty or mode that was just played (e.g., EASY, MEDIUM, BATTLE). */
    private String gameMode;

    /**
     * Constructs the ResultPane with a specific background and navigation buttons.
     * It initializes the layout to show "Try Again" and "Return" options.
     * @param gameMode The mode the player was in, used to restart the session correctly.
     */
    public ResultPane(String gameMode) {
        this.gameMode = gameMode;
        initializeWindowSize(getRootPane());
        setAlignment(Pos.CENTER);
        setSpacing(20);
        setPadding(new Insets(32,0,32,0));
                
        Button tryAgain = new Button("Try Again");
        styleButton(tryAgain);
        tryAgain.setOnMouseClicked(mouseEvent -> {
            gameTimer.stop();
        	initQuiz(this.gameMode);
        }); 

        Button exit = new Button("Return");
        styleButton(exit);
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

    private void styleButton(Button button) {
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

        button.setFont(Font.font("Noto Sans Thai", FontWeight.BOLD, 36));
        button.setPrefWidth(400);
        button.setPrefHeight(100);
    }

}

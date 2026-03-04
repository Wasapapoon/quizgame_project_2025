package pane;

import java.util.Objects;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import mode.GameMode;
import mode.GameModeSelector;
import utils.Goto;

public class ModeSelectionPane extends VBox {
    private static GameModeSelector gameModeSelector;
    private static Boolean backButtonClicked = false;
    
    public ModeSelectionPane(GameModeSelector gameModeSelector) {
        this.gameModeSelector = gameModeSelector;

        setSpacing(20);
        setAlignment(Pos.CENTER);
        setPadding(new Insets(20));

        VBox buttonContainer = new VBox(30);
        buttonContainer.setAlignment(Pos.CENTER);
        VBox.setMargin(buttonContainer, new Insets(50,0,0,0));

        Button easyButton = createDifficultyButton("Easy", GameMode.EASY);
        Button mediumButton = createDifficultyButton("Medium", GameMode.MEDIUM);
        Button hardButton = createDifficultyButton("Hard", GameMode.HARD);
        Button battleButton = createDifficultyButton("Battle", GameMode.BATTLE);


        easyButton.setPrefWidth(400);
        mediumButton.setPrefWidth(400);
        hardButton.setPrefWidth(400);
        battleButton.setPrefWidth(400);
        
        easyButton.setPrefHeight(110);
        mediumButton.setPrefHeight(110);
        hardButton.setPrefHeight(110);
        battleButton.setPrefHeight(110);

        Text text = new Text("Select Mode");
        text.setFont(Font.font("Noto Sans Thai", FontWeight.BOLD, 45));
        text.setFill(Color.WHITE);
        
        buttonContainer.getChildren().addAll(easyButton, mediumButton, hardButton, battleButton);
        getChildren().addAll(text, buttonContainer, createBackButton());
    }

    private Button createDifficultyButton(String text, GameMode difficulty) {
        Button button = new Button(text);
        button.setFont(Font.font("Noto Sans Thai", FontWeight.BOLD, 25));
        button.setPrefWidth(120);
        button.setPrefHeight(50);
        button.setTextFill(Color.BLACK);
        button.setStyle("-fx-background-color: lightgray;");

        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: darkgray;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: lightgray;"));

        button.setOnAction(e -> {
            gameModeSelector.setDifficultyLevel(difficulty);
            gameModeSelector.startGame();
            setBackButtonClicked(false);
        });

        return button;
    }
    
    private Button createBackButton() {
    	Button back = new Button("Back");
        back.setBackground(new Background(new BackgroundImage(new Image(Objects.requireNonNull(Goto.class.getResourceAsStream("/choiceback.png"))),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, true, false))));

        back.setOnMouseExited(mouseEvent -> {
            back.setBackground(new Background(new BackgroundImage(new Image(Objects.requireNonNull(Goto.class.getResourceAsStream("/choiceback.png"))),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                    new BackgroundSize(100, 100, true, true, true, false))));
            back.setTextFill(Color.BLACK);
        });

        back.setOnMouseEntered(mouseEvent -> {
            back.setBackground(new Background(new BackgroundImage(new Image(Objects.requireNonNull(Goto.class.getResourceAsStream("/choiceback2.png"))),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                    new BackgroundSize(100, 100, true, true, true, false))));
            back.setTextFill(Color.WHITE);
        });

        back.setFont(Font.font("Noto Sans Thai", FontWeight.BOLD, 20));
        back.setPrefWidth(300);
        back.setPrefHeight(80);
        VBox.setMargin(back, new Insets(110, 0, 0, 0));
        
        back.setOnMouseClicked(mouseEvent -> {
        	setBackButtonClicked(true);
        	Goto.titleScreenPage();
        });
        
        return back;
    }

	public static Boolean getBackButtonClicked() {
		return backButtonClicked;
	}

	public static void setBackButtonClicked(Boolean backButtonClicked) {
		ModeSelectionPane.backButtonClicked = backButtonClicked;
	}
    

}

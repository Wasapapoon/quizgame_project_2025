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

/**
 * ModeSelectionPane is a UI component that provides the interface for players
 * to select their desired game mode (EASY, MEDIUM, HARD, or BATTLE).
 * It manages the transition from the menu to the actual gameplay.
 */
public class ModeSelectionPane extends VBox {

    /** Reference to the GameModeSelector to handle mode logic and start the game. */
    private static GameModeSelector gameModeSelector;

    /** Flag to track if the back button has been clicked to return to the title screen. */
    private static Boolean backButtonClicked = false;

    /**
     * Constructs the ModeSelectionPane layout, including mode buttons and a back button.
     * @param gameModeSelector The selector object used to set difficulty and start the game.
     */
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

        Text text = new Text("Select Mode");
        text.setFont(Font.font("Noto Sans Thai", FontWeight.BOLD, 45));
        text.setFill(Color.WHITE);
        
        buttonContainer.getChildren().addAll(easyButton, mediumButton, hardButton, battleButton);
        getChildren().addAll(text, buttonContainer, createBackButton());
    }

    /**
     * Creates a styled button for a specific game difficulty or mode.
     * Sets up hover effects and the action to start the game when clicked.
     * @param text The text to be displayed on the button.
     * @param difficulty The GameMode associated with this button.
     * @return A configured Button object.
     */
    private Button createDifficultyButton(String text, GameMode difficulty) {
        Button button = new Button(text);
        styleButton(button);

        button.setOnAction(e -> {
            gameModeSelector.setDifficultyLevel(difficulty);
            gameModeSelector.startGame();
            setBackButtonClicked(false);
        });

        return button;
    }

    /**
     * Creates a "Back" button with custom background images and hover effects.
     * When clicked, it sets the backButtonClicked flag and returns to the title screen.
     * @return A styled Back button.
     */
    private Button createBackButton() {
    	Button back = new Button("Back");
        styleButton(back, 20, 300, 80);

        VBox.setMargin(back, new Insets(110, 0, 0, 0));
        
        back.setOnMouseClicked(mouseEvent -> {
        	setBackButtonClicked(true);
        	Goto.titleScreenPage();
        });
        
        return back;
    }

    private void styleButton(Button button) {
        styleButton(button, 25, 400, 110);
    }

    private void styleButton(Button button, int fontSize, double width, double height) {
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

        button.setFont(Font.font("Noto Sans Thai", FontWeight.BOLD, fontSize));
        button.setPrefWidth(width);
        button.setPrefHeight(height);
    }

    /** @return True if the back button was clicked, otherwise false. */
	public static Boolean getBackButtonClicked() {
		return backButtonClicked;
	}

    /** @param backButtonClicked State of the back button interaction. */
	public static void setBackButtonClicked(Boolean backButtonClicked) {
		ModeSelectionPane.backButtonClicked = backButtonClicked;
	}
    

}

package mode;

import utils.Goto;

/**
 * The GameModeSelector class is responsible for managing the difficulty state of the game.
 * It acts as a bridge between the mode selection interface and the game initialization logic,
 * ensuring the correct game mode is preserved and passed to the game controller.
 */
public class GameModeSelector {

    /**
     * The currently selected difficulty level or game mode.
     */
	private GameMode currentLevel;

    /**
     * Constructs a new GameModeSelector and initializes the default difficulty to {@link GameMode#EASY}.
     */
    public GameModeSelector() {
        this.currentLevel = GameMode.EASY;
    }

    /**
     * Updates the current difficulty level to the specified GameMode.
     * @param level The new GameMode to be set (e.g., EASY, MEDIUM, HARD, BATTLE).
     */
    public void setDifficultyLevel(GameMode level) {
        this.currentLevel = level;
    }

    /**
     * Retrieves the currently set difficulty level.
     * @return The current {@link GameMode} stored in this selector.
     */
    public GameMode getDifficultyLevel() {
       return currentLevel;
    }

    /**
     * Initiates the game session by calling the global quiz initialization utility
     * with the string representation of the currently selected difficulty level.
     */
    public void startGame() {
        Goto.initQuiz(currentLevel.toString());
    }   
}


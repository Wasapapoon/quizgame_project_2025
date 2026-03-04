package mode;

import utils.Goto; 

public class GameModeSelector {
	private GameMode currentLevel;

    public GameModeSelector() {
        this.currentLevel = GameMode.EASY;
    }

    public void setDifficultyLevel(GameMode level) {
        this.currentLevel = level;
    }
    
    public GameMode getDifficultyLevel() {
       return currentLevel;
    }

    public void startGame() {
        Goto.initQuiz(currentLevel.toString());
    }   
}


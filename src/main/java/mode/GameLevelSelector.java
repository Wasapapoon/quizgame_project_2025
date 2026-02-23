package mode;

import utils.Goto; 

public class GameLevelSelector {
	private DifficultyLevel currentLevel;

    public GameLevelSelector() {
        this.currentLevel = DifficultyLevel.EASY;
    }

    public void setDifficultyLevel(DifficultyLevel level) {
        this.currentLevel = level;
    }
    
    public DifficultyLevel getDifficultyLevel() {
       return currentLevel;
    }

    public void startGame() {
        Goto.initQuiz(currentLevel.toString());
    }   
}


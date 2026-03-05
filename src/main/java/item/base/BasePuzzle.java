package item.base;

import java.util.List;

/**
 * The BasePuzzle class is an abstract blueprint for all puzzle types in the game.
 * It defines the core properties of a puzzle, such as its correct answer,
 * and requires all sub-classes to implement logic for image management
 * and time limits.
 */
public abstract class BasePuzzle {

    /**
     * The correct text string representing the solution to the puzzle.
     */
    private final String answer;

    /**
     * Constructs a new BasePuzzle with a predefined solution.
     * @param answer The correct answer string for this puzzle.
     */
    public BasePuzzle(String answer) {
        this.answer = answer;
    }

    /**
     * Retrieves the correct answer for the puzzle.
     * @return The answer string.
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Indicates whether the current puzzle includes visual images as part of its content.
     * @return True if the puzzle has pictures, otherwise false.
     */
    public abstract boolean getHasPicture();

    /**
     * Assigns a specific picture file name to the puzzle's collection.
     * @param pictureName The file name of the image to be added or set.
     */
    public abstract void setPictureName(String pictureName);

    /**
     * Retrieves a list of all picture file names associated with this puzzle.
     * @return A List of strings representing the image resource paths.
     */
    public abstract List<String> getPictureNames();

    /**
     * Retrieves the time duration allocated for players to solve this specific puzzle.
     * @return The time limit in seconds.
     */
    public abstract int getTimeLimit();

}
package item.level;

import item.base.BasePuzzle;
import item.usage.hasHint;

import java.util.ArrayList;
import java.util.List;

/**
 * The Hard class represents the most challenging difficulty level for a puzzle.
 * It extends {@link item.base.BasePuzzle} and implements the {@link item.usage.hasHint} interface,
 * featuring a longer time limit and a progressive hint system to assist players.
 */
public class Hard extends BasePuzzle implements hasHint {

    /**
     * A list of resource file names for the images associated with this puzzle.
     */
    private final List<String> pictureNames;

    /**
     * A collection of text strings provided as hints to the player during the round.
     */
    private List<String> hint;

    /**
     * The fixed time duration in seconds allocated for solving a hard puzzle.
     * Default is set to 60 seconds.
     */
    private final int timeLimit = 60;

    /**
     * Constructs a Hard puzzle instance with a specific answer, picture list, and hint list.
     * @param answer The correct solution string for the puzzle.
     * @param pictureNames A list of image file names used to visualize the puzzle.
     * @param hint A list of textual clues to be revealed over time.
     */
    public Hard(String answer, List<String> pictureNames, List<String> hint) {
        super(answer);
        this.pictureNames = new ArrayList<>();
        for (String pictureName : pictureNames) {
            setPictureName(pictureName);
        }
        setHint(hint);
    }

    /**
     * Placeholder method for setting a picture name.
     * In the Hard level implementation, this method currently performs no action.
     * @param pictureName The file name of the image.
     */
    @Override
    public void setPictureName(String pictureName) {
        this.pictureNames.add(pictureName);
    }

    /**
     * Retrieves the list of image resource paths assigned to this puzzle.
     * @return A List of image file name strings.
     */
    @Override
    public List<String> getPictureNames() {
        return pictureNames;
    }

    /**
     * Retrieves the collection of hints associated with this puzzle.
     * @return A List of hint strings.
     */
    @Override
    public List<String> getHint() {
        return hint;
    }

    /**
     * Updates or sets the collection of hints for this puzzle.
     * @param hint A List of strings to be used as new hints.
     */
    public void setHint(List<String> hint) {
        this.hint = hint;
    }

    /**
     * Retrieves the predefined time limit for this difficulty level.
     * @return The integer value of the time limit (60 seconds).
     */
    @Override
    public int getTimeLimit() {
        return timeLimit;
    }
}
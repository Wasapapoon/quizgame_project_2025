package item.level;

import item.base.BasePuzzle;
import item.usage.hasHint;

import java.util.List;

/**
 * The Medium class represents an intermediate difficulty level for a puzzle.
 * It extends {@link item.base.BasePuzzle} and implements the {@link item.usage.hasHint} interface,
 * offering a balanced challenge with a moderate time limit and available clues.
 */
public class Medium extends BasePuzzle implements hasHint {

    /**
     * A list of resource file names for the images associated with this puzzle.
     */
    private final List<String> pictureNames;

    /**
     * A collection of text strings provided as hints to the player during the round.
     */
    private List<String> hint;

    /**
     * The fixed time duration in seconds allocated for solving a medium puzzle.
     * Default is set to 40 seconds.
     */
    private final int timeLimit = 40;

    /**
     * Constructs a Medium puzzle instance with a specific answer, picture list, and hint list.
     * @param answer The correct solution string for the puzzle.
     * @param pictureNames A list of image file names used to visualize the puzzle.
     * @param hint A list of textual clues to be revealed as time decreases.
     */
    public Medium(String answer, List<String> pictureNames, List<String> hint) {
        super(answer);
        this.pictureNames = pictureNames;
        this.hint = hint;
    }

    /**
     * Confirms that this difficulty level includes visual images for the player.
     * @return True, as Medium puzzles always possess pictures.
     */
    @Override
    public boolean getHasPicture() {
        return true;
    }

    /**
     * Placeholder method for setting a picture name.
     * In the Medium level implementation, this method currently performs no action.
     * @param pictureName The file name of the image.
     */
    @Override
    public void setPictureName(String pictureName) {
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
     * Indicates that this puzzle type supports the hint revelation system.
     * @return True, as the Medium class implements the hasHint interface.
     */
    @Override
    public boolean getHasHint() {
        return true;
    }

    /**
     * Retrieves the predefined time limit for this difficulty level.
     * @return The integer value of the time limit (40 seconds).
     */
    @Override
    public int getTimeLimit() {
        return timeLimit;
    }
}
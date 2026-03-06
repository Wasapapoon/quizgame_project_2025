package item.level;

import item.base.BasePuzzle;

import java.util.ArrayList;
import java.util.List;

/**
 * The Easy class represents the basic difficulty level of a puzzle in the game.
 * It extends {@link item.base.BasePuzzle} and implements a predefined time limit
 * and a specific collection of images to be displayed.
 */
public class Easy extends BasePuzzle {

    /**
     * A list containing the resource file names for the images associated with this puzzle.
     */
    private final List<String> pictureNames;

    /**
     * The fixed time duration in seconds allocated for solving an easy puzzle.
     * Default is set to 20 seconds.
     */
    private final int timeLimit = 20;

    /**
     * Constructs an Easy puzzle instance with a specific answer and a list of pictures.
     * Sets the hasPicture flag to true upon initialization.
     * @param answer The correct solution string for the puzzle.
     * @param pictureNames A list of image file names used to visualize the puzzle.
     */
    public Easy(String answer, List<String> pictureNames) {
        super(answer);
        this.pictureNames = new ArrayList<>();
        for (String pictureName : pictureNames) {
            setPictureName(pictureName);
        }
    }

    /**
     * Placeholder method for setting a picture name.
     * In the Easy level implementation, this method currently performs no action.
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
     * Retrieves the predefined time limit for this difficulty level.
     * @return The integer value of the time limit (20 seconds).
     */
    @Override
    public int getTimeLimit() {
        return timeLimit;
    }
}
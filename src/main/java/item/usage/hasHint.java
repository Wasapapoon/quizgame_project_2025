package item.usage;

import java.util.List;

/**
 * The hasHint interface is designed for puzzle objects that provide progressive clues.
 * It defines the necessary methods for managing and retrieving a collection of hints
 * that can be revealed to the player during a game session.
 */
public interface hasHint {

    /**
     * Checks if the puzzle instance supports the hint system.
     * @return True if hints are available for this puzzle, otherwise false.
     */
	boolean getHasHint();

    /**
     * Sets or updates the collection of textual hints for the puzzle.
     * @param hint A list of strings representing the clues to be displayed.
     */
    void setHint(List<String> hint);

    /**
     * Retrieves the list of hints associated with the puzzle.
     * @return A list of hint strings.
     */
    List<String> getHint();

}

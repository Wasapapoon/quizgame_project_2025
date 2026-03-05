package pane;

import entity.Player;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import utils.Goto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * LifePane is a UI component that displays the player's name and their remaining health points (HP).
 * It uses heart icons to visually represent the player's current status in the game.
 */
public class LifePane extends VBox {

    /** A list containing the ImageView objects for each heart icon shown on the screen. */
    private List<ImageView> hearts = new ArrayList<>();

    /** The player associated with this life display */
    private Player player;

    /**
     * Constructs a LifePane for the specified player, initializing their name display
     * and setting up the initial three-heart health bar.
     * @param player The player object whose name and HP will be monitored and displayed.
     */
    public LifePane(Player player) {
        this.player = player;
        setSpacing(10);
        setAlignment(Pos.CENTER);

        Label nameLabel = new Label(player.getName());
        nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        nameLabel.setTextFill(Color.WHITE);

        HBox heartsBox = new HBox(10);
        heartsBox.setAlignment(Pos.CENTER);

        for (int i = 0; i < 3; i++) {
            ImageView heart = new ImageView(new Image(Objects.requireNonNull(Goto.class.getResourceAsStream("/heart.png"))));
            heart.setFitWidth(50);
            heart.setPreserveRatio(true);
            hearts.add(heart);
            heartsBox.getChildren().add(heart);
        }

        updateHazardDisplay();
        getChildren().addAll(nameLabel, heartsBox);
    }

    /**
     * Synchronizes the visual heart icons with the player's actual HP.
     * Hearts are hidden or removed from the layout when the player loses health.
     */
    public void updateHazardDisplay() {
        for (int i = 0; i < hearts.size(); i++) {
            if (i < player.getHp()) {
                hearts.get(i).setVisible(true);
            } else {
                hearts.get(i).setVisible(false);
                hearts.get(i).setManaged(false);
            }
        }
    }

    /**
     * Decreases the player's HP by one and immediately updates the visual heart display.
     * This method is typically called when a player misses a question or time runs out.
     */
    public void reduceHP() {
        player.reduceHp();
        updateHazardDisplay();
    }
}
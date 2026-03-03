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

public class LifePane extends VBox {
    private List<ImageView> hearts = new ArrayList<>();
    private Player player;

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

    public void updateHazardDisplay() {
        for (int i = 0; i < hearts.size(); i++) {
            if (i < player.getHp()) {
                hearts.get(i).setVisible(true);
            } else {
                hearts.get(i).setVisible(false);
            }
        }
    }

    public void reduceHP() {
        player.reduceHp();
        updateHazardDisplay();
    }
}
package pane;

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
    private int hp = 3;

    public LifePane(String playerName) {
        setSpacing(10);
        setAlignment(Pos.CENTER);

        Label nameLabel = new Label(playerName);
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

        getChildren().addAll(nameLabel, heartsBox);
    }

    public void reduceHP() {
        if (hp > 0) {
            hp--;
            hearts.get(hp).setVisible(false);
        }
    }
}
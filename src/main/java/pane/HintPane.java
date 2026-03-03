package pane;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import utils.Goto;

import java.util.List;
import java.util.Objects;

import item.base.BasePuzzle;
import item.usage.hasHint;

import static utils.Goto.quizPage;

public class HintPane extends VBox {
    private String difficultyLevel;

    public HintPane(BasePuzzle question, String difficultyLevel, int index) {
        this.difficultyLevel = difficultyLevel;
        Goto.initializeWindowSize(Goto.getRootPane());

        setSpacing(16);
        setPadding(new Insets(32, 0, 32, 0));
        setAlignment(Pos.CENTER);

        List<String> hints = ((hasHint) question).getHint();

        if (((hasHint) question).getHasHint() && hints != null && index < hints.size()) {
            String specificHint = hints.get(index);
            Text text = new Text("Hint : " + specificHint);
            text.setFill(Color.YELLOW);

            if (specificHint.length() < 50) {
                text.setFont(Font.font("Noto Sans Thai", FontWeight.BOLD, 36));
            } else {
                text.setFont(Font.font("Noto Sans Thai", FontWeight.BOLD, 28));
            }

            VBox.setMargin(text, new Insets(330 + (Goto.getWindowHeight() - 864) / 2.0, 0, 0, 0));
            getChildren().add(text);
        }

        Button back = new Button("Back");
        back.setBackground(new Background(new BackgroundImage(new Image(Objects.requireNonNull(Goto.class.getResourceAsStream("/choiceback.png"))),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, true, false))));

        back.setOnMouseExited(mouseEvent -> {
            back.setBackground(new Background(new BackgroundImage(new Image(Objects.requireNonNull(Goto.class.getResourceAsStream("/choiceback.png"))),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                    new BackgroundSize(100, 100, true, true, true, false))));
            back.setTextFill(Color.BLACK);
        });

        back.setOnMouseEntered(mouseEvent -> {
            back.setBackground(new Background(new BackgroundImage(new Image(Objects.requireNonNull(Goto.class.getResourceAsStream("/choiceback2.png"))),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                    new BackgroundSize(100, 100, true, true, true, false))));
            back.setTextFill(Color.WHITE);
        });

        back.setFont(Font.font("Noto Sans Thai", FontWeight.BOLD, 20));
        back.setPrefWidth(300);
        back.setPrefHeight(80);

        VBox.setMargin(back, new Insets(Goto.getWindowHeight() - 594, 0, 0, 0));

        back.setOnAction(mouseEvent -> quizPage(difficultyLevel));

        getChildren().add(back);
    }
}
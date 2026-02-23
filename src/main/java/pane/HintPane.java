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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.regex.Pattern;

import item.base.BaseQuestion;
import item.usage.hasHint;

import static utils.Goto.quizPage;

public class HintPane extends VBox {
    private String difficultyLevel; 

    public HintPane(BaseQuestion question, String difficultyLevel) { 
        this.difficultyLevel = difficultyLevel; 
        Goto.initializeWindowSize(Goto.getRootPane());
        
        setSpacing(16);
        setPadding(new Insets(32, 0, 32, 0));
        setAlignment(Pos.CENTER);

        boolean hintLine = Pattern.compile("\n").matcher(((hasHint) question).getHint()).find();

        if (((hasHint) question).getHasHint()) {
            Text text = new Text("Hint : " + ((hasHint) question).getHint());
            text.setFill(Color.WHITE);
            if (text.getText().length() < 50) {
                text.setFont(Font.font("Noto Sans Thai", FontWeight.BOLD, 36));
            } else {
                text.setFont(Font.font("Noto Sans Thai", FontWeight.BOLD, 32));
            }

            if (!hintLine) {
                VBox.setMargin(text, new Insets(330 + (Goto.getWindowHeight() - 864) / 2, 0, 0, 0));
            } else {
                VBox.setMargin(text, new Insets(300 + (Goto.getWindowHeight() - 864) / 2, 0, 0, 0));
            }
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
        if(!hintLine) {
        	VBox.setMargin(back, new Insets(Goto.getWindowHeight()-594, 0, 0, 0));
        }
        else {
        	VBox.setMargin(back, new Insets(Goto.getWindowHeight()-634, 0, 0, 0));
        }
        

        back.setOnMouseClicked(mouseEvent -> quizPage(difficultyLevel));

        
        getChildren().add(back);
    }
}

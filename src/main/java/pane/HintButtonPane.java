package pane;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import utils.Goto;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Objects;

import item.base.BaseQuestion;
import item.usage.hasHint;

public class HintButtonPane extends HBox {
	
    private String difficultyLevel;
   
    public HintButtonPane(BaseQuestion question, String difficultyLevel) { 
        this.difficultyLevel = difficultyLevel; 
        Button hintButton = new Button("Hint");
        hintButton.setPrefWidth(165);
        hintButton.setPrefHeight(50);

        hintButton.setBackground(new Background(new BackgroundImage(new Image(Objects.requireNonNull(Goto.class.getResourceAsStream("/choiceback.png"))),
BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
new BackgroundSize(100, 100, true, true, true, false))));

        hintButton.setOnMouseExited(mouseEvent -> {
            hintButton.setBackground(new Background(new BackgroundImage(new Image(Objects.requireNonNull(Goto.class.getResourceAsStream("/choiceback.png"))),
BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
new BackgroundSize(100, 100, true, true, true, false))));
            hintButton.setTextFill(Color.BLACK);
        });

        hintButton.setOnMouseEntered(mouseEvent -> {
            hintButton.setBackground(new Background(new BackgroundImage(new Image(Objects.requireNonNull(Goto.class.getResourceAsStream("/choiceback2.png"))),
BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
new BackgroundSize(100, 100, true, true, true, false))));
            hintButton.setTextFill(Color.WHITE);
        });

        hintButton.setFont(Font.font("Noto Sans Thai", FontWeight.BOLD, 17));
        HBox.setMargin(hintButton, new Insets(0, 40, 0, 0));

        
        hintButton.setOnMouseClicked(mouseEvent -> {
        	Goto.hintPage(question, difficultyLevel);
        	if(!((hasHint) question).useHint() && !Goto.getHintClick()) {
        		((hasHint) question).setUseHint(true);
        		Goto.setHintClick(true);
        	}
        	else {
        		((hasHint) question).setUseHint(false);
        	}
        	
        });

        getChildren().add(hintButton);
    }
}

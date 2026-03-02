package pane;

import item.base.BaseQuestion;
import item.usage.hasHint;
import item.usage.hasPicture;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import utils.Goto;

import java.util.List;

public class QuizPane extends GridPane {

    public QuizPane(BaseQuestion question, String difficultyLevel) {
        Goto.initializeWindowSize(Goto.getRootPane());
        setPadding(new Insets(32, 0, 32, 0));
        setAlignment(Pos.CENTER);

        if (question instanceof hasPicture) {
            int spacing = (question instanceof hasHint) ? 25 : 45;
            HBox imagesBox = new HBox(spacing);
            imagesBox.setAlignment(Pos.CENTER);

            List<String> pictures = ((hasPicture) question).getPictureNames();

            if (pictures != null) {
                for (int i = 0; i < pictures.size(); i++) {
                    String fileName = pictures.get(i);
                    String path = "/" + fileName;
                    var resource = Goto.class.getResourceAsStream(path);

                    if (resource != null) {
                        final int index = i;
                        VBox column = new VBox(10);
                        column.setAlignment(Pos.CENTER);

                        ImageView quizImg = new ImageView(new Image(resource));
                        quizImg.setPreserveRatio(true);
                        if (question instanceof hasHint){
                            quizImg.setFitWidth(240);
                        }
                        else {
                            quizImg.setFitWidth(295);
                        }

                        column.getChildren().add(quizImg);

                        if (question instanceof hasHint) {
                            Button hintBtn = new Button("Hint");
                            hintBtn.setPrefWidth(80);
                            hintBtn.setPrefHeight(30);
                            hintBtn.setStyle("-fx-background-color: #FFD700; -fx-text-fill: black; -fx-font-weight: bold; -fx-background-radius: 5;");

                            hintBtn.setOnAction(event -> {
                                List<String> hints = ((hasHint) question).getHint();
                                if (hints != null && index < hints.size()) {
                                    if (!((hasHint) question).useHint() && !Goto.getHintClick()) {
                                        ((hasHint) question).setUseHint(true);
                                        Goto.setHintClick(true);
                                    }
                                    Goto.hintPage(question, difficultyLevel, index);
                                }
                            });

                            column.getChildren().add(hintBtn);
                        }

                        imagesBox.getChildren().add(column);
                    }
                }
            }


            imagesBox.setAlignment(Pos.CENTER);
            getChildren().add(imagesBox);
        }
    }
}

package pane;

import item.base.BaseQuestion;
import item.usage.hasPicture;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import utils.Goto;

import java.util.List;

public class QuizPane extends GridPane {

    public QuizPane(BaseQuestion question) {
        Goto.initializeWindowSize(Goto.getRootPane());
        setPadding(new Insets(32, 0, 32, 0));
        setAlignment(Pos.CENTER);

        if (question instanceof hasPicture) {
            HBox imagesBox = new HBox(25);
            imagesBox.setAlignment(Pos.CENTER);

            List<String> pictures = ((hasPicture) question).getPictureNames();

            if (pictures != null) {
                for (String fileName : pictures) {
                    String path = "/" + fileName;
                    var resource = Goto.class.getResourceAsStream(path);

                    if (resource != null) {
                        VBox column = new VBox(10);
                        column.setAlignment(Pos.CENTER);

                        ImageView quizImg = new ImageView(new Image(resource));
                        quizImg.setPreserveRatio(true);
                        quizImg.setFitWidth(280);

                        Button hintBtn = new Button("Hint");
                        hintBtn.setStyle("-fx-background-color: #FFD700; -fx-text-fill: black; -fx-font-weight: bold;");

                        column.getChildren().addAll(quizImg, hintBtn);
                        imagesBox.getChildren().add(column);
                    }
                }
            }

            GridPane.setMargin(imagesBox, new Insets(0));
            imagesBox.setAlignment(Pos.CENTER);
            getChildren().add(imagesBox);
        }
    }
}
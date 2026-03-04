package pane;

import item.base.BasePuzzle;
import item.usage.hasHint;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import utils.Goto;
import java.util.ArrayList;
import java.util.List;

public class GamePane extends GridPane {

    private List<Text> hintTextList = new ArrayList<>();

    public GamePane(BasePuzzle question, String gameMode) {

        Goto.initializeWindowSize(Goto.getRootPane());
        setPadding(new Insets(32, 0, 32, 0));
        setAlignment(Pos.CENTER);

        HBox imagesBox = new HBox(35);
        imagesBox.setAlignment(Pos.CENTER);

        List<String> pictures = question.getPictureNames();
        List<String> hints = (question instanceof hasHint) ? ((hasHint) question).getHint() : null;

        if (pictures != null) {
            for (int i = 0; i < pictures.size(); i++) {
                String fileName = pictures.get(i);
                var resource = Goto.class.getResourceAsStream("/" + fileName);

                if (resource != null) {
                    VBox column = new VBox(15);
                    column.setAlignment(Pos.CENTER);

                    ImageView quizImg = new ImageView(new Image(resource));
                    quizImg.setPreserveRatio(true);
                    if(question instanceof hasHint){
                        quizImg.setFitWidth(240);
                    }
                    else {
                        quizImg.setFitWidth(280);
                    }

                    column.getChildren().add(quizImg);

                    if (question instanceof hasHint && hints != null && i < hints.size()) {
                        Text hintDisplay = new Text("hint " + ": " + hints.get(i));
                        hintDisplay.setFont(Font.font("Noto Sans Thai", FontWeight.BOLD, 18));
                        hintDisplay.setFill(Color.YELLOW);
                        hintDisplay.setVisible(false);

                        hintTextList.add(hintDisplay);
                        column.getChildren().add(hintDisplay);
                    }
                    imagesBox.getChildren().add(column);
                }
            }
        }
        getChildren().add(imagesBox);
    }

    public void updateHintsByTime(int currentTime, BasePuzzle question, String gameMode) {
        if (!(question instanceof hasHint) || hintTextList.isEmpty()) return;

        Platform.runLater(() -> {

            String level = gameMode.toUpperCase();

            if (((hasHint) question).getHint().size() == 3) {
                if (currentTime <= 25 && hintTextList.size() > 0) {
                    hintTextList.get(0).setVisible(true);
                }
                if (currentTime <= 20 && hintTextList.size() > 1) {
                    hintTextList.get(1).setVisible(true);
                }
                if (currentTime <= 15 && hintTextList.size() > 2) {
                    hintTextList.get(2).setVisible(true);
                }
            } else if (((hasHint) question).getHint().size() == 4) {
                if (currentTime <= 35 && hintTextList.size() > 0) {
                    hintTextList.get(0).setVisible(true);
                }
                if (currentTime <= 30 && hintTextList.size() > 1) {
                    hintTextList.get(1).setVisible(true);
                }
                if (currentTime <= 25 && hintTextList.size() > 2) {
                    hintTextList.get(2).setVisible(true);
                }
                if (currentTime <= 20 && hintTextList.size() > 3) {
                    hintTextList.get(3).setVisible(true);
                }
            }
        });
    }
}
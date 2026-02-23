package pane;

import item.base.BaseQuestion;
import item.usage.hasPicture;
import javafx.geometry.HPos;
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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.regex.Pattern;

public class QuizPane extends GridPane {
	
	
    public QuizPane(BaseQuestion question){
    	Goto.initializeWindowSize(Goto.getRootPane());
        setPadding(new Insets(32,0,32,0));
        Text questionText = new Text(question.getQuestion());
        if(questionText.getText().length() < 50) {
        	 questionText.setFont(Font.font("Noto Sans Thai", FontWeight.BOLD, 36));
        }
        else {
        	questionText.setFont(Font.font("Noto Sans Thai", FontWeight.BOLD, 32));
        }
       
        questionText.setFill(Color.WHITE);
        setAlignment(Pos.CENTER);
        boolean line = Pattern.compile("\n").matcher(question.getQuestion()).find();
        

        if(question instanceof hasPicture) {
        	if(((hasPicture) question).getHasPicture()){
                ImageView quizImg = new ImageView(new Image(Objects.requireNonNull(Goto.class.getResourceAsStream("/" + ((hasPicture) question).getPictureName()))));
                quizImg.setPreserveRatio(true);
                quizImg.setFitWidth(550);
                GridPane.setMargin(quizImg, new Insets(30, 0, 0, 0));
                questionText.setTranslateY(265);
                GridPane.setHalignment(quizImg, HPos.CENTER);
                GridPane.setHalignment(questionText, HPos.CENTER);
                getChildren().add(questionText);
                getChildren().add(quizImg);
            } else {
            	if(!line) {
            		questionText.setTranslateY(270 + (Goto.getWindowHeight()-864)/2);
            	}
            	else {
            		questionText.setTranslateY(240 + (Goto.getWindowHeight()-864)/2);
            	}
                getChildren().add(questionText);
            }
        }
        else {
        	if(!line) {
        		questionText.setTranslateY(270 + (Goto.getWindowHeight()-864)/2);
        	}
        	else {
        		questionText.setTranslateY(240 + (Goto.getWindowHeight()-864)/2);
        	}
        	
            getChildren().add(questionText);
        }
        
    }
}

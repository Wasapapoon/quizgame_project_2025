package pane;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import utils.Goto;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Objects;

public class RootPane extends VBox {
    private static RootPane instance;

    private RootPane() {
        Background bg;
        BackgroundImage bgImg = new BackgroundImage(new Image(Objects.requireNonNull(Goto.class.getResourceAsStream("/puzzle_background.jpg"))), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(100, 100, true, true, true, true));
        bg = new Background(bgImg);
        setBackground(bg);
        setAlignment(Pos.TOP_CENTER);
        setSpacing(16);
        setPadding(new Insets(32,0,32,0));
        
        setPrefSize(Screen.getPrimary().getBounds().getWidth(),Screen.getPrimary().getBounds().getHeight());
        
        Text text = new Text("");
        text.setFont(Font.font("Verdana",FontWeight.BOLD,10));
        getChildren().add(text);

        Goto.setRootPane(this);
        Goto.titleScreenPage();
        
    }

    public static RootPane getRootPane() {
        if (instance == null)
            instance = new RootPane();
        return instance;
    }
}

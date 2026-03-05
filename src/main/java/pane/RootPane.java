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

/**
 * The RootPane class serves as the primary container for the entire application's UI.
 * It implements the Singleton Pattern to ensure that only one root container exists
 * throughout the game's lifecycle, providing a centralized point for page switching.
 * * @author [Your Name]
 */
public class RootPane extends VBox {

    /** * The single static instance of RootPane used to implement the Singleton Pattern.
     */
    private static RootPane instance;

    /**
     * Private constructor that initializes the main container's layout.
     * It sets the background image, alignment, spacing, and padding.
     * It also sets the initial preferred size based on the primary screen bounds
     * and triggers the title screen via the {@link utils.Goto} utility.
     */
    private RootPane() {
        Background bg;
        BackgroundImage bgImg = new BackgroundImage(new Image(Objects.requireNonNull(Goto.class.getResourceAsStream("/puzzle_background2.png"))), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(100, 100, true, true, true, true));
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

    /**
     * Retrieves the single instance of RootPane.
     * If the instance has not been created yet, it initializes a new one.
     * @return The singleton instance of the RootPane.
     */
    public static RootPane getRootPane() {
        if (instance == null)
            instance = new RootPane();
        return instance;
    }
}

package utils;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

public class DigitalTimer extends StackPane {

    private int timeRemaining = 30;
    private Timeline timeline;
    private Label timerLabel;
    private Runnable onTimeOut;

    public DigitalTimer() {
        timerLabel = new Label("30");

        timerLabel.setFont(Font.font("Impact", FontWeight.BOLD, 120));
        timerLabel.setTextFill(Color.web("#FFFF00"));

        DropShadow glow = new DropShadow();
        glow.setColor(Color.web("#FFFF00", 0.8));
        glow.setRadius(25);
        glow.setSpread(0.6);
        timerLabel.setEffect(glow);

        setAlignment(Pos.CENTER);
        getChildren().add(timerLabel);
    }

    public void start(int seconds) {
        stop();
        this.timeRemaining = seconds;
        updateLabel();

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            timeRemaining--;
            updateLabel();

            if (timeRemaining <= 5) {
                timerLabel.setTextFill(Color.RED);
                if (timerLabel.getEffect() instanceof DropShadow ds) {
                    ds.setColor(Color.RED);
                }
            } else {
                timerLabel.setTextFill(Color.web("#FFFF00"));
                if (timerLabel.getEffect() instanceof DropShadow ds) {
                    ds.setColor(Color.web("#FFFF00", 0.8));
                }
            }

            if (timeRemaining <= 0) {
                stop();
                if (onTimeOut != null) onTimeOut.run();
            }
        }));
        timeline.setCycleCount(seconds);
        timeline.play();
    }

    public void stop() {
        if (timeline != null) {
            timeline.stop();
        }
    }

    private void updateLabel() {
        timerLabel.setText(String.format("%02d", timeRemaining));
    }

    public void setOnTimeOut(Runnable onTimeOut) {
        this.onTimeOut = onTimeOut;
    }

    public int getTimeRemaining() {
        return timeRemaining;
    }
}
package utils;

import entity.Player;
import item.base.BasePuzzle;
import item.level.Easy;
import item.level.Hard;
import item.level.Medium;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import pane.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import mode.GameLevelSelector;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;

public class Goto {
    
    private static RootPane rootPane;

    private static MediaPlayer mediaPlayer;

    private static final ArrayList<BasePuzzle> questions = new ArrayList<>();

    public static Player player = new Player("HP LEFT");

    public static Player playerA = new Player("PLAYER A");

    public static Player playerB = new Player("PLAYER B");

    public static DigitalTimer gameTimer = new DigitalTimer();

    private static EventHandler<KeyEvent> currentFilter;
    
    private static double windowWidth ;
    
    private static double windowHeight ;


    public static void initializeWindowSize(RootPane root) {
    	rootPane = root;
        setWindowWidth(rootPane.getScene().getWindow().getWidth());
        setWindowHeight(rootPane.getScene().getWindow().getHeight());
    }
    public static void setMediaPlayer(String musicUrl) {
        try {
            Media media = new Media(musicUrl);
            mediaPlayer = new MediaPlayer(media);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setRootPane(RootPane rootPane) {
        Goto.rootPane = rootPane;
    }
    
    public static RootPane getRootPane() {
    	return rootPane;
    }
    

    private static void music(String musicPath){
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.dispose();
        }

        mediaPlayer = new MediaPlayer(new Media(musicPath));
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }

    public static void clear(){
        if(rootPane.getChildren().size() > 1){
                rootPane.getChildren().remove(1,rootPane.getChildren().size());
        }
    }

    public static void titleScreenPage(){
        clear();
        if (!LevelSelectionPane.getBackButtonClicked()) {
            music(Objects.requireNonNull(Goto.class.getResource("/music/title_screen_music.mp3")).toExternalForm());
        }
        rootPane.getChildren().add(new TitleScreenPane());  
    }

    public static void initQuiz(String difficultyLevel) {
    	if (difficultyLevel.equals("EXTREME")) {
            music(Objects.requireNonNull(Goto.class.getResource("/music/extreme_quiz.mp3")).toExternalForm());
        } else {
            music(Objects.requireNonNull(Goto.class.getResource("/music/quiz_music.mp3")).toExternalForm());
        }
        
        questions.clear();
        
        ArrayList<BasePuzzle> easyLevelQuestion= new ArrayList<>();
        ArrayList<BasePuzzle> MediumLevelQuestion = new ArrayList<>();
        ArrayList<BasePuzzle> HardLevelQuestion = new ArrayList<>();
        ArrayList<BasePuzzle> MixedLevelQuestion = new ArrayList<>();
        ArrayList<BasePuzzle> ExtremeLevelQuestion = new ArrayList<>();

        switch (difficultyLevel) {
            case "EASY" -> {
               easyLevelQuestion.add(new Easy("บางพลัด", List.of("easy1_1.png", "easy1_2.jpeg")));
               easyLevelQuestion.add(new Easy( "ฮ่องเต้", List.of("easy2_1.png", "easy2_2.png")));
               easyLevelQuestion.add(new Easy("ประยุทธ์", List.of("easy3_1.png", "easy3_2.png")));
               easyLevelQuestion.add(new Easy( "มายคราฟ", List.of("easy4_1.png", "easy4_2.png")));

               Collections.shuffle(easyLevelQuestion);
               questions.addAll(easyLevelQuestion);
               singlePlayerPage(difficultyLevel);
            }
            case "MEDIUM" -> {
                MediumLevelQuestion.add(new Medium( "ชีวิตคู่", List.of("medium1_1.jpg", "medium1_2.png", "medium1_3.jpg"),List.of("ชูมือ","วิดพื้น","จำนวนคี่")));
                MediumLevelQuestion.add(new Medium( "ไลน์เรนเจอร์", List.of("medium2_1.png", "medium2_2.jpg", "medium2_3.png"),List.of("เส้น (line)","เล่น","เจอเพื่อน")));
                MediumLevelQuestion.add(new Medium( "ไดโนเสาร์", List.of("medium3_1.png", "medium3_2.png", "medium3_3.png"),List.of("ไดร์เป่าผม","no","ดาวเสาร์")));
                MediumLevelQuestion.add(new Medium( "มันคือแป้ง", List.of("medium4_1.png", "medium4_2.png", "medium4_3.png"),List.of("มัน","เป็น อยู่ คือ","แป้งเย็น")));

                Collections.shuffle(MediumLevelQuestion);
                questions.addAll(MediumLevelQuestion);
                singlePlayerPage(difficultyLevel);
            }
            case "HARD" -> {
                HardLevelQuestion.add(new Hard( "กินก๋วยเตี๋ยวหกคน", List.of("hard1_1.png", "hard1_2.jpg", "hard1_3.png", "hard1_4.png"),List.of("ตัวอักษร","ตัวอักษร","ตัวอักษร","ตัวอักษร")));
                HardLevelQuestion.add(new Hard( "เต้มงคลกิตติ์", List.of("hard2_1.png", "hard2_2.png", "hard2_3.png", "hard2_4.png"),List.of("เต้","มงกุฏ","คน","git")));
                HardLevelQuestion.add(new Hard( "เป็ดย่างบรรทัดทอง", List.of("hard3_1.png", "hard3_2.png", "hard3_3.png", "hard3_4.png"),List.of("เป็ดย่าง","ไม้บรรทัด","no ไม้","ทอง")));

                Collections.shuffle(HardLevelQuestion);
                questions.addAll(HardLevelQuestion);
                singlePlayerPage(difficultyLevel);
            }
            case "MIXED" -> {
                MixedLevelQuestion.add(new Easy( "ประยุทธ์", List.of("easy3_1.png", "easy3_2.png")));
                MixedLevelQuestion.add(new Easy( "มายคราฟ", List.of("easy4_1.png", "easy4_2.png")));
                MixedLevelQuestion.add(new Medium( "ชีวิตคู่", List.of("medium1_1.jpg", "medium1_2.png", "medium1_3.jpg"),List.of("ชูมือ","วิดพื้น","จำนวนคี่")));
                MixedLevelQuestion.add(new Medium( "ไลน์เรนเจอร์", List.of("medium2_1.png", "medium2_2.jpg", "medium2_3.png"),List.of("เส้น (line)","เล่น","เจอเพื่อน")));
                MixedLevelQuestion.add(new Hard( "เต้มงคลกิตติ์", List.of("hard2_1.png", "hard2_2.png", "hard2_3.png", "hard2_4.png"),List.of("เต้","มงกุฏ","คน","git")));

                Collections.shuffle(MixedLevelQuestion);
                questions.addAll(MixedLevelQuestion);
                quizPage(difficultyLevel);
            }
            default -> {
                ExtremeLevelQuestion.add(new Medium( "ชีวิตคู่", List.of("medium1_1.jpg", "medium1_2.png", "medium1_3.jpg"),List.of("ชูมือ","วิดพื้น","จำนวนคี่")));
                ExtremeLevelQuestion.add(new Medium( "ไลน์เรนเจอร์", List.of("medium2_1.png", "medium2_2.jpg", "medium2_3.png"),List.of("เส้น (line)","เล่น","เจอเพื่อน")));
                ExtremeLevelQuestion.add(new Medium( "ไดโนเสาร์", List.of("medium3_1.png", "medium3_2.png", "medium3_3.png"),List.of("ไดร์เป่าผม","no","ดาวเสาร์")));
                ExtremeLevelQuestion.add(new Medium( "มันคือแป้ง", List.of("medium4_1.png", "medium4_2.png", "medium4_3.png"),List.of("มัน","เป็น อยู่ คือ","แป้งเย็น")));
                ExtremeLevelQuestion.add(new Hard( "กินก๋วยเตี๋ยวหกคน", List.of("hard1_1.png", "hard1_2.jpg", "hard1_3.png", "hard1_4.png"),List.of("ตัวอักษร","ตัวอักษร","ตัวอักษร","ตัวอักษร")));
                ExtremeLevelQuestion.add(new Hard( "เต้มงคลกิตติ์", List.of("hard2_1.png", "hard2_2.png", "hard2_3.png", "hard2_4.png"),List.of("เต้","มงกุฏ","คน","git")));
                ExtremeLevelQuestion.add(new Hard( "เป็ดย่างบรรทัดทอง", List.of("hard3_1.png", "hard3_2.png", "hard3_3.png", "hard3_4.png"),List.of("เป็ดย่าง","ไม้บรรทัด","no ไม้","ทอง")));

                Collections.shuffle(ExtremeLevelQuestion);
                questions.addAll(ExtremeLevelQuestion);
                quizPage(difficultyLevel);
            }
        }
    }

    public static void quizPage(String difficultyLevel) {
        clear();

        Stage stage = (Stage) rootPane.getScene().getWindow();
        double width = stage.getWidth();
        double height = stage.getHeight();

        BackgroundImage bgImg = new BackgroundImage(new Image(Objects.requireNonNull(Goto.class.getResourceAsStream("/game_background2.png"))),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(width, height, false, false, false, false));
        rootPane.setBackground(new Background(bgImg));

        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.TOP_RIGHT);
        hBox.setPadding(new Insets(20, 20, 0, 0));
        rootPane.getChildren().add(hBox);

        HBox topStatus = new HBox();
        topStatus.setAlignment(Pos.TOP_CENTER);
        topStatus.setPadding(new Insets(-20, 50, 0, 50));

        LifePane playerALife = new LifePane(playerA);
        LifePane playerBLife = new LifePane(playerB);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        BasePuzzle currentQuestion = questions.getFirst();
        GamePane gamePane = new GamePane(currentQuestion, difficultyLevel);

        topStatus.getChildren().addAll(playerALife, spacer, playerBLife);
        rootPane.getChildren().add(topStatus);

        gameTimer.setAlignment(Pos.CENTER);
        gameTimer.setGameContext(gamePane, currentQuestion, difficultyLevel);
        rootPane.getChildren().add(gameTimer);

        VBox.setMargin(gameTimer, new Insets(-120, 0, 0, 0));
        gameTimer.start(currentQuestion.getTimeLimit());

        VBox.setVgrow(gamePane, Priority.ALWAYS);
        rootPane.getChildren().add(gamePane);

        HBox inputContainer = new HBox(80);
        inputContainer.setAlignment(Pos.CENTER);
        inputContainer.setPadding(new Insets(0, 60, 50, 60));

        TextPane textPane1 = new TextPane(difficultyLevel, "PLAYER A INPUT", playerA, playerB, playerBLife);
        TextPane textPane2 = new TextPane(difficultyLevel, "PLAYER B INPUT", playerB, playerA, playerALife);

        HBox.setHgrow(textPane1, Priority.ALWAYS);
        HBox.setHgrow(textPane2, Priority.ALWAYS);

        inputContainer.getChildren().addAll(textPane1, textPane2);
        rootPane.getChildren().add(inputContainer);

        textPane1.getTextField().setDisable(true);
        textPane2.getTextField().setDisable(true);

        Platform.runLater(() -> {
            Scene scene = rootPane.getScene();
            if (scene != null) {
                if (currentFilter != null) {
                    scene.removeEventFilter(javafx.scene.input.KeyEvent.KEY_PRESSED, currentFilter);
                }

                currentFilter = event -> {
                    if (textPane1.getTextField().isDisable() && textPane2.getTextField().isDisable()) {
                        if (event.getCode() == KeyCode.TAB) {
                            textPane1.getTextField().setDisable(false);
                            textPane2.getTextField().setDisable(true);
                            textPane1.getTextField().requestFocus();
                            event.consume();
                        } else if (event.getCode() == KeyCode.ENTER) {
                            textPane2.getTextField().setDisable(false);
                            textPane1.getTextField().setDisable(true);
                            textPane2.getTextField().requestFocus();
                            event.consume();
                        }
                    }

                    if (event.getCode() == KeyCode.RIGHT) {
                        event.consume();
                        checkQuiz(difficultyLevel);
                    }
                };
                scene.addEventFilter(javafx.scene.input.KeyEvent.KEY_PRESSED, currentFilter);
            }
        });

        gameTimer.setOnTimeOut(() -> {
            playerA.setHp(playerA.getHp() - 1);
            playerB.setHp(playerB.getHp() - 1);
            playerALife.updateHazardDisplay();
            playerBLife.updateHazardDisplay();

            checkQuiz(difficultyLevel);
        });

        if (playerA.getHp() <= 0 || playerB.getHp() <= 0) {
            resultPage(difficultyLevel);
            playerA.setHp(3);
            playerB.setHp(3);
        }

    }

    public static void singlePlayerPage(String difficultyLevel) {
        clear();

        Stage stage = (Stage) rootPane.getScene().getWindow();
        double width = stage.getWidth();
        double height = stage.getHeight();

        BackgroundImage bgImg = new BackgroundImage(new Image(Objects.requireNonNull(Goto.class.getResourceAsStream("/game_background2.png"))),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(width, height, false, false, false, false));
        rootPane.setBackground(new Background(bgImg));

        HBox topStatus = new HBox();
        topStatus.setAlignment(Pos.TOP_RIGHT);
        topStatus.setPadding(new Insets(20, 50, 0, 50));

        LifePane playerLife = new LifePane(player);
        topStatus.getChildren().add(playerLife);
        rootPane.getChildren().add(topStatus);

        BasePuzzle currentQuestion = questions.getFirst();
        GamePane gamePane = new GamePane(currentQuestion, difficultyLevel);

        gameTimer.setAlignment(Pos.CENTER);
        gameTimer.setGameContext(gamePane, currentQuestion, difficultyLevel);
        rootPane.getChildren().add(gameTimer);

        VBox.setMargin(gameTimer, new Insets(-120, 0, 0, 0));
        gameTimer.start(currentQuestion.getTimeLimit());

        VBox.setVgrow(gamePane, Priority.ALWAYS);
        rootPane.getChildren().add(gamePane);

        HBox inputContainer = new HBox();
        inputContainer.setAlignment(Pos.CENTER);
        inputContainer.setPadding(new Insets(0, 0, 50, 0));

        TextPane textPane = new TextPane(difficultyLevel, "YOUR INPUT", player, null, playerLife);
        HBox.setHgrow(textPane, Priority.ALWAYS);
        inputContainer.getChildren().add(textPane);
        rootPane.getChildren().add(inputContainer);

        gameTimer.setOnTimeOut(() -> {
            player.setHp(player.getHp() - 1);
            playerLife.updateHazardDisplay();
            checkQuiz(difficultyLevel);
        });

        if (player.getHp() <= 0) {
            resultPage(difficultyLevel);
            player.setHp(3);
        }
    }


    public static Boolean checkAnswer(String choice, String difficultyLevel, Player currentPlayer, Player opponentPlayer, LifePane opponentLifePane) {
        Boolean isCorrect = choice.equals(questions.getFirst().getAnswer());

        if(difficultyLevel.equals("MIXED")){
            if (isCorrect) {
                opponentLifePane.reduceHP();
            }
        }

        return isCorrect;
    }


    public static void checkQuiz(String difficultyLevel) { 
        clear();
        questions.removeFirst();
        if (questions.isEmpty()) {
            resultPage(difficultyLevel);
            player.setHp(3);
            playerA.setHp(3);
            playerB.setHp(3);
        } else{
            if(difficultyLevel.equals("MIXED")){
                quizPage(difficultyLevel);
            }
            else{
                singlePlayerPage(difficultyLevel);
            }


        }
    }


    public static void resultPage(String difficultyLevel) {
        clear();

        Stage stage = (Stage) rootPane.getScene().getWindow();
        double width = stage.getWidth();
        double height = stage.getHeight();

        String winBgPath;
        if(difficultyLevel.equals("MIXED")){
            if (playerA.getHp() > playerB.getHp()) {
                winBgPath = "/playerA_win.png";
            } else if (playerB.getHp() > playerA.getHp()) {
                winBgPath = "/playerB_win.png";
            } else {
                winBgPath = "/draw_background.png";
            }
        }
        else{
            if (player.getHp() > 0){
                winBgPath = "/you_win.png";
            }
            else{
                winBgPath = "/game_over.png";
            }
        }


        BackgroundImage bgImg = new BackgroundImage(
                new Image(Objects.requireNonNull(Goto.class.getResourceAsStream(winBgPath))),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(width, height, false, false, false, false)
        );

        rootPane.setBackground(new Background(bgImg));

        music(Objects.requireNonNull(Goto.class.getResource("/music/score_music.mp3")).toExternalForm());

        rootPane.getChildren().add(new ResultPane(difficultyLevel));
    }
    
    public static void levelSelectionPage(GameLevelSelector gameLevelSelector) {
    	clear();
    	
    	rootPane.getChildren().add(new LevelSelectionPane(gameLevelSelector));
    }

	public static double getWindowWidth() {
		return windowWidth;
	}

	public static void setWindowWidth(double windowWidth) {
		Goto.windowWidth = windowWidth;
	}

	public static double getWindowHeight() {
		return windowHeight;
	}

	public static void setWindowHeight(double windowHeight) {
		Goto.windowHeight = windowHeight;
	}
}
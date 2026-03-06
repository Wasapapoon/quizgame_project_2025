package utils;

import entity.Player;
import item.base.BasePuzzle;
import item.level.Easy;
import item.level.Hard;
import item.level.Medium;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import mode.GameMode;
import pane.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import mode.GameModeSelector;
import javafx.stage.Stage;

/**
 * The Goto class serves as the primary utility and navigation controller for the application.
 * It manages global states, including player data, game timers, background music,
 * and handles transitions between different UI panes such as the title screen, quiz pages, and result screens.
 */
public class Goto {

    /** The root container used to swap different game screens. */
    private static RootPane rootPane;

    /** The media player instance used for playing background music and sound effects. */
    private static MediaPlayer mediaPlayer;

    /** The list of puzzles generated for the current game session. */
    private static final ArrayList<BasePuzzle> questions = new ArrayList<>();

    /** The list of custom puzzles created during the session. */
    private static final ArrayList<CustomPuzzleEntry> customPuzzles = new ArrayList<>();

    /** The identifier for the next custom puzzle to be added. */
    private static long nextCustomPuzzleId = 1;

    /** The player instance for single-player modes (Easy, Medium, Hard). */
    public static Player player = new Player("HP LEFT");

    /** Player A instance used for the competitive Battle mode. */
    public static Player playerA = new Player("PLAYER A");

    /** Player B instance used for the competitive Battle mode. */
    public static Player playerB = new Player("PLAYER B");

    /** The shared digital timer used to track remaining time during puzzles. */
    public static DigitalTimer gameTimer = new DigitalTimer();

    /** The current key event filter used to handle specific key inputs (e.g., Buzz-in keys). */
    private static EventHandler<KeyEvent> currentFilter;

    /** The recorded width of the primary application window. */
    private static double windowWidth;

    /** The recorded height of the primary application window. */
    private static double windowHeight;


    /**
     * A record of a custom puzzle entry including its unique ID, difficulty level, and puzzle instance.
     */
    public static final class CustomPuzzleEntry {
        /** The unique identifier of the custom puzzle entry. */
        public final long id;
        /** The difficulty level associated with the custom puzzle. */
        public final GameMode difficulty;
        /** The puzzle instance containing the answer and images. */
        public final BasePuzzle puzzle;

        /**
         * Constructs a new CustomPuzzleEntry.
         * @param id The unique ID for this entry.
         * @param difficulty The difficulty level of the puzzle.
         * @param puzzle The puzzle instance.
         */
        public CustomPuzzleEntry(long id, GameMode difficulty, BasePuzzle puzzle) {
            this.id = id;
            this.difficulty = difficulty;
            this.puzzle = puzzle;
        }
    }

    /**
     * Retrieves the current list of custom puzzle entries.
     * @return A list of all custom puzzle entries.
     */
    public static List<CustomPuzzleEntry> getCustomPuzzles() {
        return new ArrayList<>(customPuzzles);
    }

    /**
     * Adds a new custom puzzle to the system.
     * @param difficulty The difficulty level for the puzzle.
     * @param answer The correct solution string.
     * @param pictureNamesOrPaths A list of image resource names or filesystem paths.
     * @param hints A list of hint strings (may be null for Easy mode).
     * @return The unique ID of the newly created puzzle entry.
     */
    public static long addCustomPuzzle(GameMode difficulty, String answer, List<String> pictureNamesOrPaths, List<String> hints) {
        Objects.requireNonNull(difficulty, "difficulty");
        Objects.requireNonNull(answer, "answer");
        Objects.requireNonNull(pictureNamesOrPaths, "pictureNamesOrPaths");

        BasePuzzle puzzle = switch (difficulty) {
            case EASY -> new Easy(answer, pictureNamesOrPaths);
            case MEDIUM -> new Medium(answer, pictureNamesOrPaths, hints == null ? List.of() : hints);
            case HARD -> new Hard(answer, pictureNamesOrPaths, hints == null ? List.of() : hints);
            default -> throw new IllegalArgumentException("Unsupported difficulty for custom puzzle: " + difficulty);
        };

        long id = nextCustomPuzzleId++;
        customPuzzles.add(new CustomPuzzleEntry(id, difficulty, puzzle));
        return id;
    }

    /**
     * Removes a custom puzzle entry by its unique identifier.
     * @param id The ID of the puzzle to remove.
     * @return True if a puzzle was removed, otherwise false.
     */
    public static boolean removeCustomPuzzle(long id) {
        return customPuzzles.removeIf(p -> p.id == id);
    }

    /**
     * Retrieves custom puzzles filtered by the specified game mode.
     * @param gameMode The game mode or difficulty string.
     * @return A list of puzzles matching the criteria.
     */
    private static List<BasePuzzle> getCustomPuzzlesForMode(String gameMode) {
        ArrayList<BasePuzzle> result = new ArrayList<>();
        for (CustomPuzzleEntry e : customPuzzles) {
            if ("BATTLE".equals(gameMode)) {
                result.add(e.puzzle);
            } else if (e.difficulty.toString().equals(gameMode)) {
                result.add(e.puzzle);
            }
        }
        return result;
    }


    /**
     * Initializes and records the current window dimensions from the provided RootPane.
     * @param root The RootPane instance from which to extract window size.
     */
    public static void initializeWindowSize(RootPane root) {
        rootPane = root;
        setWindowWidth(rootPane.getScene().getWindow().getWidth());
        setWindowHeight(rootPane.getScene().getWindow().getHeight());
    }


    /**
     * Initializes the media player with a specific music URL.
     * @param musicUrl The resource path or URL of the music file.
     */
    public static void setMediaPlayer(String musicUrl) {
        try {
            Media media = new Media(musicUrl);
            mediaPlayer = new MediaPlayer(media);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the global reference for the RootPane container.
     * @param rootPane The RootPane instance to be used globally.
     */
    public static void setRootPane(RootPane rootPane) {
        Goto.rootPane = rootPane;
    }


    /**
     * Retrieves the global RootPane instance.
     * @return The current RootPane.
     */
    public static RootPane getRootPane() {
        return rootPane;
    }

    /**
     * Stops current playback and starts playing background music from the specified path in an infinite loop.
     * @param musicPath The resource path for the music file.
     */
    private static void music(String musicPath){
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.dispose();
        }

        mediaPlayer = new MediaPlayer(new Media(musicPath));
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }

    /**
     * Clears all UI components from the RootPane except for the primary background layer.
     */
    public static void clear(){
        if(rootPane.getChildren().size() > 1){
            rootPane.getChildren().remove(1,rootPane.getChildren().size());
        }
    }

    /**
     * Navigates to the title screen and starts the title background music.
     */
    public static void titleScreenPage(){
        clear();
        if (!ModeSelectionPane.getBackButtonClicked() && !CustomPuzzlePane.getBackButtonClicked()) {
            music(Objects.requireNonNull(Goto.class.getResource("/music/title_screen_music.mp3")).toExternalForm());
        }
        rootPane.getChildren().add(new TitleScreenPane());
    }

    /**
     * Initializes the quiz data based on the selected game mode.
     * This method resets player health, populates the question list, and shuffles the puzzles.
     * @param gameMode The difficulty or mode string (EASY, MEDIUM, HARD, BATTLE).
     */
    public static void initQuiz(String gameMode) {
        if (gameMode.equals("BATTLE")) {
            music(Objects.requireNonNull(Goto.class.getResource("/music/extreme_quiz.mp3")).toExternalForm());
        } else {
            music(Objects.requireNonNull(Goto.class.getResource("/music/quiz_music.mp3")).toExternalForm());
        }

        player.setHp(3);
        playerA.setHp(3);
        playerB.setHp(3);

        questions.clear();

        ArrayList<BasePuzzle> EasyLevel= new ArrayList<>();
        ArrayList<BasePuzzle> MediumLevel = new ArrayList<>();
        ArrayList<BasePuzzle> HardLevel = new ArrayList<>();
        ArrayList<BasePuzzle> BattleMode = new ArrayList<>();

        switch (gameMode) {
            case "EASY" -> {
                EasyLevel.add(new Easy("บางพลัด", List.of("easy1_1.png", "easy1_2.jpeg")));
                EasyLevel.add(new Easy( "ฮ่องเต้", List.of("easy2_1.png", "easy2_2.png")));
                EasyLevel.add(new Easy("ประยุทธ์", List.of("easy3_1.png", "easy3_2.png")));
                EasyLevel.add(new Easy( "มายคราฟ", List.of("easy4_1.png", "easy4_2.png")));

                EasyLevel.addAll(getCustomPuzzlesForMode(gameMode));
                Collections.shuffle(EasyLevel);
                questions.addAll(EasyLevel);
                singlePlayerPage(gameMode);
            }
            case "MEDIUM" -> {
                MediumLevel.add(new Medium( "ชีวิตคู่", List.of("medium1_1.jpg", "medium1_2.png", "medium1_3.jpg"),List.of("ชูมือ","วิดพื้น","จำนวนคี่")));
                MediumLevel.add(new Medium( "ไลน์เรนเจอร์", List.of("medium2_1.png", "medium2_2.jpg", "medium2_3.png"),List.of("เส้น (line)","เล่น","เจอเพื่อน")));
                MediumLevel.add(new Medium( "ไดโนเสาร์", List.of("medium3_1.png", "medium3_2.png", "medium3_3.png"),List.of("ไดร์เป่าผม","no","ดาวเสาร์")));
                MediumLevel.add(new Medium( "มันคือแป้ง", List.of("medium4_1.png", "medium4_2.png", "medium4_3.png"),List.of("มัน","เป็น อยู่ คือ","แป้งเย็น")));

                MediumLevel.addAll(getCustomPuzzlesForMode(gameMode));
                Collections.shuffle(MediumLevel);
                questions.addAll(MediumLevel);
                singlePlayerPage(gameMode);
            }
            case "HARD" -> {
                HardLevel.add(new Hard( "กินก๋วยเตี๋ยวหกคน", List.of("hard1_1.png", "hard1_2.jpg", "hard1_3.png", "hard1_4.png"),List.of("ตัวอักษร","ตัวอักษร","ตัวอักษร","ตัวอักษร")));
                HardLevel.add(new Hard( "เต้มงคลกิตติ์", List.of("hard2_1.png", "hard2_2.png", "hard2_3.png", "hard2_4.png"),List.of("เต้","มงกุฏ","คน","git")));
                HardLevel.add(new Hard( "เป็ดย่างบรรทัดทอง", List.of("hard3_1.png", "hard3_2.png", "hard3_3.png", "hard3_4.png"),List.of("เป็ดย่าง","ไม้บรรทัด","no ไม้","ทอง")));

                HardLevel.addAll(getCustomPuzzlesForMode(gameMode));
                Collections.shuffle(HardLevel);
                questions.addAll(HardLevel);
                singlePlayerPage(gameMode);
            }
            default -> {
                BattleMode.add(new Easy( "ประยุทธ์", List.of("easy3_1.png", "easy3_2.png")));
                BattleMode.add(new Easy( "มายคราฟ", List.of("easy4_1.png", "easy4_2.png")));
                BattleMode.add(new Medium( "ชีวิตคู่", List.of("medium1_1.jpg", "medium1_2.png", "medium1_3.jpg"),List.of("ชูมือ","วิดพื้น","จำนวนคี่")));
                BattleMode.add(new Medium( "ไลน์เรนเจอร์", List.of("medium2_1.png", "medium2_2.jpg", "medium2_3.png"),List.of("เส้น (line)","เล่น","เจอเพื่อน")));
                BattleMode.add(new Hard( "เต้มงคลกิตติ์", List.of("hard2_1.png", "hard2_2.png", "hard2_3.png", "hard2_4.png"),List.of("เต้","มงกุฏ","คน","git")));

                BattleMode.addAll(getCustomPuzzlesForMode(gameMode));
                Collections.shuffle(BattleMode);
                questions.addAll(BattleMode);
                quizPage(gameMode);
            }
        }
    }

    /**
     * Constructs and displays the multi-player Battle mode quiz page.
     * Sets up buzz-in event filters and manages health deduction for both players.
     * @param gameMode The string representing the BATTLE mode.
     */
    public static void quizPage(String gameMode) {
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
        GamePane gamePane = new GamePane(currentQuestion, gameMode);

        topStatus.getChildren().addAll(playerALife, spacer, playerBLife);
        rootPane.getChildren().add(topStatus);

        gameTimer.setAlignment(Pos.CENTER);
        gameTimer.setGameContext(gamePane, currentQuestion, gameMode);
        rootPane.getChildren().add(gameTimer);

        VBox.setMargin(gameTimer, new Insets(-120, 0, 0, 0));
        gameTimer.start(currentQuestion.getTimeLimit());

        VBox.setVgrow(gamePane, Priority.ALWAYS);
        rootPane.getChildren().add(gamePane);

        HBox inputContainer = new HBox(80);
        inputContainer.setAlignment(Pos.CENTER);
        inputContainer.setPadding(new Insets(0, 60, 50, 60));

        TextPane textPane1 = new TextPane(gameMode, "PLAYER A INPUT", playerA, playerB, playerBLife);
        TextPane textPane2 = new TextPane(gameMode, "PLAYER B INPUT", playerB, playerA, playerALife);

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
                            textPane1.getTextField().setPromptText("ตอบเป็นภาษาไทย");
                            event.consume();
                        } else if (event.getCode() == KeyCode.ENTER) {
                            textPane2.getTextField().setDisable(false);
                            textPane1.getTextField().setDisable(true);
                            textPane2.getTextField().requestFocus();
                            textPane2.getTextField().setPromptText("ตอบเป็นภาษาไทย");
                            event.consume();
                        }
                    }

                    if (event.getCode() == KeyCode.RIGHT) {
                        event.consume();
                        checkQuiz(gameMode);
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

            checkQuiz(gameMode);
        });

        if (playerA.isDead() || playerB.isDead()) {
            resultPage(gameMode);
            playerA.setHp(3);
            playerB.setHp(3);
        }

    }

    /**
     * Constructs and displays the single-player quiz page for Easy, Medium, or Hard levels.
     * @param gameMode The selected difficulty level string.
     */
    public static void singlePlayerPage(String gameMode) {
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
        GamePane gamePane = new GamePane(currentQuestion, gameMode);

        gameTimer.setAlignment(Pos.CENTER);
        gameTimer.setGameContext(gamePane, currentQuestion, gameMode);
        rootPane.getChildren().add(gameTimer);

        VBox.setMargin(gameTimer, new Insets(-120, 0, 0, 0));
        gameTimer.start(currentQuestion.getTimeLimit());

        VBox.setVgrow(gamePane, Priority.ALWAYS);
        rootPane.getChildren().add(gamePane);

        HBox inputContainer = new HBox();
        inputContainer.setAlignment(Pos.CENTER);
        inputContainer.setPadding(new Insets(0, 0, 50, 0));

        TextPane textPane = new TextPane(gameMode, "YOUR INPUT", player, null, playerLife);
        HBox.setHgrow(textPane, Priority.ALWAYS);
        inputContainer.getChildren().add(textPane);
        rootPane.getChildren().add(inputContainer);

        Platform.runLater(() -> {
            Scene scene = rootPane.getScene();
            if (scene != null) {
                if (currentFilter != null) {
                    scene.removeEventFilter(javafx.scene.input.KeyEvent.KEY_PRESSED, currentFilter);
                }

                currentFilter = event -> {
                    if (event.getCode() == KeyCode.RIGHT) {
                        event.consume();
                        checkQuiz(gameMode);
                    }
                };
                scene.addEventFilter(javafx.scene.input.KeyEvent.KEY_PRESSED, currentFilter);
            }
        });

        gameTimer.setOnTimeOut(() -> {
            player.setHp(player.getHp() - 1);
            playerLife.updateHazardDisplay();
            checkQuiz(gameMode);
        });

        if (player.isDead()) {
            resultPage(gameMode);
        }
    }

    /**
     * Validates the player's answer against the current puzzle's solution.
     * In Battle mode, a correct answer will deduct health from the opponent.
     * @param choice The answer string entered by the player.
     * @param gameMode The current game mode.
     * @param currentPlayer The player who submitted the answer.
     * @param opponentPlayer The opposing player.
     * @param opponentLifePane The life pane of the opponent to be updated.
     * @return True if the answer is correct, otherwise false.
     */
    public static Boolean checkAnswer(String choice, String gameMode, Player currentPlayer, Player opponentPlayer, LifePane opponentLifePane) {
        Boolean isCorrect = choice.equals(questions.getFirst().getAnswer());

        if(gameMode.equals("BATTLE")){
            if (isCorrect) {
                opponentLifePane.reduceHP();
            }
        }

        return isCorrect;
    }

    /**
     * Checks the remaining questions and navigates to the next question or the results page.
     * @param gameMode The current game mode.
     */
    public static void checkQuiz(String gameMode) {
        clear();

        if (!questions.isEmpty()) {
            questions.removeFirst();
        }
        if (questions.isEmpty()) {
            resultPage(gameMode);
        } else {
            if (gameMode.equals("BATTLE")) {
                quizPage(gameMode);
            } else {
                singlePlayerPage(gameMode);
            }
        }
    }

    /**
     * Displays the final result page with unique backgrounds for Win, Loss, or Draw states.
     * @param gameMode The game mode that was just completed.
     */
    public static void resultPage(String gameMode) {
        clear();

        Stage stage = (Stage) rootPane.getScene().getWindow();
        double width = stage.getWidth();
        double height = stage.getHeight();

        String winBgPath;
        if(gameMode.equals("BATTLE")){
            if (playerA.getHp() > playerB.getHp()) {
                winBgPath = "/playerA_win.png";
            } else if (playerB.getHp() > playerA.getHp()) {
                winBgPath = "/playerB_win.png";
            } else {
                winBgPath = "/draw_background.png";
            }
        }
        else{
            if (!player.isDead()){
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
        
        Platform.runLater(() -> {
            Scene scene = rootPane.getScene();
            if (scene != null && currentFilter != null) {
                scene.removeEventFilter(javafx.scene.input.KeyEvent.KEY_PRESSED, currentFilter);
                currentFilter = null;
            }
        });

        rootPane.getChildren().add(new ResultPane(gameMode));
    }

    /**
     * Displays the level selection screen using the provided selector logic.
     * @param gameModeSelector The selector instance managing mode states.
     */
    public static void levelSelectionPage(GameModeSelector gameModeSelector) {
        clear();
        rootPane.getChildren().add(new ModeSelectionPane(gameModeSelector));
    }

    /**
     * Navigates to the custom puzzle management page.
     */
    public static void customPuzzlePage() {
        clear();
        rootPane.getChildren().add(new CustomPuzzlePane());
    }
    /** @return The current window width. */
	public static double getWindowWidth() {
		return windowWidth;
	}

    /** @param windowWidth The width to set. */
	public static void setWindowWidth(double windowWidth) {
		Goto.windowWidth = windowWidth;
	}

    /** @return The current window height. */
	public static double getWindowHeight() {
		return windowHeight;
	}

    /** @param windowHeight The height to set. */
	public static void setWindowHeight(double windowHeight) {
		Goto.windowHeight = windowHeight;
	}
}
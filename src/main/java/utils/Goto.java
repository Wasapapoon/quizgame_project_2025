package utils;

import item.base.BaseQuestion;
import item.level.EasyQuestion;
import item.level.HardQuestion;
import item.level.MediumQuestion;
import item.quiz.ChoiceQuiz;
import item.usage.ChoiceType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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

    private static final ArrayList<BaseQuestion> questions = new ArrayList<>();

    private static final ArrayList<ChoiceQuiz> choiceAnswers = new ArrayList<>();

    private static int yourScore = 0;
    
    private static int answerStreak = 0;

    private static Boolean hintClick = false;

    public static DigitalTimer gameTimer = new DigitalTimer();
    
    private static double windowWidth ;
    
    private static double windowHeight ;

    private static int currentHintIndex = 0;


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
        choiceAnswers.clear();
        yourScore = 0;
        
        ArrayList<BaseQuestion> easyLevelQuestion= new ArrayList<>();
        ArrayList<BaseQuestion> MediumLevelQuestion = new ArrayList<>();
        ArrayList<BaseQuestion> HardLevelQuestion = new ArrayList<>();
        ArrayList<BaseQuestion> MixedLevelQuestion = new ArrayList<>();
        ArrayList<BaseQuestion> ExtremeLevelQuestion = new ArrayList<>();


        ChoiceQuiz cq1 = new ChoiceQuiz();
        ChoiceQuiz cq2 = new ChoiceQuiz();
        ChoiceQuiz cq3 = new ChoiceQuiz();
        ChoiceQuiz cq4 = new ChoiceQuiz();
        ChoiceQuiz cq5 = new ChoiceQuiz();
        ChoiceQuiz cq6 = new ChoiceQuiz();
        ChoiceQuiz cq7 = new ChoiceQuiz();
        ChoiceQuiz cq8 = new ChoiceQuiz();
        ChoiceQuiz cq9 = new ChoiceQuiz();
        ChoiceQuiz cq10 = new ChoiceQuiz();
        ChoiceQuiz cq11 = new ChoiceQuiz();
        ChoiceQuiz cq12 = new ChoiceQuiz();
        ChoiceQuiz cq13 = new ChoiceQuiz();
        ChoiceQuiz cq14 = new ChoiceQuiz();
        ChoiceQuiz cq15 = new ChoiceQuiz();
        ChoiceQuiz cq16 = new ChoiceQuiz();
        //Extreme Question Choice
        ChoiceQuiz cq17 = new ChoiceQuiz();

        
        cq1.addChoices("กาชาไลน์เรนเจอร์ เกลือไหม", "เกลือ", "ไม่เกลือ");
       
        cq2.addChoices("อยากมีแฟน ทำไงดี","หาคู่ด้วย CU-NEX", "สุ่มกาชา", "เล่น line ranger", "ถูกมากกว่า 1 ข้อ");
        
        cq3.addChoices("อาหารชนิดใดที่มีโปรตีนสูงที่สุด", "ข้าว", "ไข่", "มันฝรั่ง", "แตงโม");
        
        cq4.addChoices("ควรนอนหลับวันละกี่ชั่วโมงเพื่อสุขภาพที่ดี", "3-4 ชั่วโมง", "5-6 ชั่วโมง", "7-8 ชั่วโมง", "10-12 ชั่วโมง");
        
        cq5.addChoices("อุปกรณ์ใดใช้ในการตรวจวัดอุณหภูมิร่างกาย", "เครื่องวัดความดัน", "เทอร์โมมิเตอร์", "เครื่องวัดออกซิเจนในเลือด", "เครื่องชั่งน้ำหนัก");
        
        cq6.addChoices("สีของผักที่มีสารต้านอนุมูลอิสระสูงคือสีอะไร", "สีเขียว", "สีแดง", "สีม่วง", "ถูกทุกข้อ");
        
        cq7.addChoices("เวลาใดเหมาะสมกับการนอนหลับที่สุด", "23:00", "2:00", "5:00", "ถูกมากกว่า 1 ข้อ");
        
        cq8.addChoices("เครื่องดื่มชนิดใดที่ช่วยให้ร่างกายสดชื่นมากที่สุด", "น้ำเปล่า", "เหล้า", "น้ำอัดลม", "ถูกมากกว่า 1 ข้อ");
        
        cq9.addChoices("ทำไมเราไม่ควรนำจักรยานแช่ตู้เย็น", "เดี๋ยวจักรยานจะหนาว", "เดี๋ยวจักรยานจะร้อน");
        
        cq10.addChoices("ใครคือนายกคนปัจจุบันของไทย", "ทักษิณ ชินวัตร", "เศรษฐา ทวีสิน", "แพทองธาร ชินวัตร", "ถูกมากกว่า 1 ข้อ");
       
        cq11.addChoices("อุปกรณ์ใดต่อไปนี้เป็นหน่วยประมวลผลกลางของคอมพิวเตอร์", "RAM", "SSD", "CPU", "PSU");
        
        cq12.addChoices("ข้อใดเป็นสัตว์เลี้ยงยอดนิยม", "สิงโต", "ปลาวาฬ", "แมว", "หมี");
        
        cq13.addChoices("ข้อใดไม่ใช่อุปกรณ์เก็บข้อมูลถาวร", "HDD", "SSD", "RAM", "Flash Drive");
        
        cq14.addChoices("ถ้า\"พี่โต\"เป็นพระเอกหนังพี่โตจะเล่นแนวไหน", "หนังดราม่า\"โตแล้วเจ็บ\"", "หนังแอ็กชัน \"โตแมน ไรเดอร์\"", "หนังรัก \"โตโต้อกหัก\"","หนังตลก \"โตแหลกแจกฮา\"");
        
        cq15.addChoices("ถ้าเจอปุ่ม \"ห้ามกด\" คุณจะทำอะไร", "กดทันทีแบบไม่ต้องคิด", "เดินผ่านแบบไม่สนใจ", "ถามคนข้างๆว่ากดแล้วเป็นอะไร", "ตั้งวงสนทนาเพื่อวิเคราะห์ปุ่มนี้");
        
        cq16.addChoices("ถ้าเราอยู่ในเกม RPG และโดน NPC ด่า เราจะทำยังไง", "ตอบกลับไปให้เจ็บแสบ","ช่วยเหลือเพื่อให้ NPC ใจอ่อน", "เดินไปเปิดหีบสมบัติต่อ", "กระโดดเตะ NPC" );
        //Extreme Question Choice
        cq17.addChoices("มดอะไรกัดเจ็บที่สุด", "มดทอระยิด", "มดแดง", "มดดำ", "มดราชินี");

        choiceAnswers.add(cq1);
        choiceAnswers.add(cq2);
        choiceAnswers.add(cq3);
        choiceAnswers.add(cq4);
        choiceAnswers.add(cq5);
        choiceAnswers.add(cq6);
        choiceAnswers.add(cq7);
        choiceAnswers.add(cq8);
        choiceAnswers.add(cq9);
        choiceAnswers.add(cq10);
        choiceAnswers.add(cq11);
        choiceAnswers.add(cq12);
        choiceAnswers.add(cq13);
        choiceAnswers.add(cq14);
        choiceAnswers.add(cq15);
        choiceAnswers.add(cq16);
        choiceAnswers.add(cq17);

        questions.clear();

        switch (difficultyLevel) {
            case "EASY" -> {

               easyLevelQuestion.add(new EasyQuestion(ChoiceType.TEXT, "บางพลัด", List.of("easy1_1.png", "easy1_2.jpeg")));
               easyLevelQuestion.add(new EasyQuestion(ChoiceType.TEXT, "ฮ่องเต้", List.of("easy2_1.png", "easy2_2.png")));
               easyLevelQuestion.add(new EasyQuestion(ChoiceType.TEXT, "ประยุทธ์", List.of("easy3_1.png", "easy3_2.png")));
               easyLevelQuestion.add(new EasyQuestion(ChoiceType.TEXT, "มายคราฟ", List.of("easy4_1.png", "easy4_2.png")));



               Collections.shuffle(easyLevelQuestion);
               questions.addAll(easyLevelQuestion);
               quizPage(difficultyLevel);
            }
            case "MEDIUM" -> {
                MediumLevelQuestion.add(new MediumQuestion(ChoiceType.TEXT, "ชีวิตคู่", List.of("medium1_1.jpg", "medium1_2.png", "medium1_3.jpg"),List.of("แมว","เป็ด","ไก่")));
                MediumLevelQuestion.add(new MediumQuestion(ChoiceType.TEXT, "ไลน์เรนเจอร์", List.of("medium2_1.png", "medium2_2.jpg", "medium2_3.png"),List.of("แมว","เป็ด","ไก่")));
                MediumLevelQuestion.add(new MediumQuestion(ChoiceType.TEXT, "ไดโนเสาร์", List.of("medium3_1.png", "medium3_2.png", "medium3_3.png"),List.of("แมว","เป็ด","ไก่")));
                MediumLevelQuestion.add(new MediumQuestion(ChoiceType.TEXT, "มันคือแป้ง", List.of("medium4_1.png", "medium4_2.png", "medium4_3.png"),List.of("แมว","เป็ด","ไก่")));

                Collections.shuffle(MediumLevelQuestion);
                questions.addAll(MediumLevelQuestion);
                quizPage(difficultyLevel);
            }
            case "HARD" -> {

                HardLevelQuestion.add(new HardQuestion(ChoiceType.TEXT, "กินก๋วยเตี๋ยวหกคน", List.of("hard1_1.png", "hard1_2.jpg", "hard1_3.png", "hard1_4.png"),List.of("แมว","เป็ด","ไก่","ห่าน")));
                HardLevelQuestion.add(new HardQuestion(ChoiceType.TEXT, "เต้มงคลกิตติ์", List.of("hard2_1.png", "hard2_2.png", "hard2_3.png", "hard2_4.png"),List.of("แมว","เป็ด","ไก่","ห่าน")));
                HardLevelQuestion.add(new HardQuestion(ChoiceType.TEXT, "เป็ดย่างบรรทัดทอง", List.of("hard3_1.png", "hard3_2.png", "hard3_3.png", "hard3_4.png"),List.of("แมว","เป็ด","ไก่","ห่าน")));


                Collections.shuffle(HardLevelQuestion);
                questions.addAll(HardLevelQuestion);
                quizPage(difficultyLevel);
            }
            case "MIXED" -> {

                MixedLevelQuestion.add(new EasyQuestion(ChoiceType.TEXT, "ประยุทธ์", List.of("easy3_1.png", "easy3_2.png")));
                MixedLevelQuestion.add(new EasyQuestion(ChoiceType.TEXT, "มายคราฟ", List.of("easy4_1.png", "easy4_2.png")));
                MixedLevelQuestion.add(new MediumQuestion(ChoiceType.TEXT, "ชีวิตคู่", List.of("medium1_1.jpg", "medium1_2.png", "medium1_3.jpg"),List.of("แมว","เป็ด","ไก่")));
                MixedLevelQuestion.add(new MediumQuestion(ChoiceType.TEXT, "ไลน์เรนเจอร์", List.of("medium2_1.png", "medium2_2.jpg", "medium2_3.png"),List.of("แมว","เป็ด","ไก่")));
                MixedLevelQuestion.add(new HardQuestion(ChoiceType.TEXT, "เต้มงคลกิตติ์", List.of("hard2_1.png", "hard2_2.png", "hard2_3.png", "hard2_4.png"),List.of("แมว","เป็ด","ไก่","ห่าน")));

                Collections.shuffle(MixedLevelQuestion);
                questions.addAll(MixedLevelQuestion);
                quizPage(difficultyLevel);
            }
            default -> {
                ExtremeLevelQuestion.add(new MediumQuestion(ChoiceType.TEXT, "ชีวิตคู่", List.of("medium1_1.jpg", "medium1_2.png", "medium1_3.jpg"),List.of("แมว","เป็ด","ไก่")));
                ExtremeLevelQuestion.add(new MediumQuestion(ChoiceType.TEXT, "ไลน์เรนเจอร์", List.of("medium2_1.png", "medium2_2.jpg", "medium2_3.png"),List.of("แมว","เป็ด","ไก่")));
                ExtremeLevelQuestion.add(new MediumQuestion(ChoiceType.TEXT, "ไดโนเสาร์", List.of("medium3_1.png", "medium3_2.png", "medium3_3.png"),List.of("แมว","เป็ด","ไก่")));
                ExtremeLevelQuestion.add(new MediumQuestion(ChoiceType.TEXT, "มันคือแป้ง", List.of("medium4_1.png", "medium4_2.png", "medium4_3.png"),List.of("แมว","เป็ด","ไก่")));
                ExtremeLevelQuestion.add(new HardQuestion(ChoiceType.TEXT, "กินก๋วยเตี๋ยวหกคน", List.of("hard1_1.png", "hard1_2.jpg", "hard1_3.png", "hard1_4.png"),List.of("แมว","เป็ด","ไก่","ห่าน")));
                ExtremeLevelQuestion.add(new HardQuestion(ChoiceType.TEXT, "เต้มงคลกิตติ์", List.of("hard2_1.png", "hard2_2.png", "hard2_3.png", "hard2_4.png"),List.of("แมว","เป็ด","ไก่","ห่าน")));
                ExtremeLevelQuestion.add(new HardQuestion(ChoiceType.TEXT, "เป็ดย่างบรรทัดทอง", List.of("hard3_1.png", "hard3_2.png", "hard3_3.png", "hard3_4.png"),List.of("แมว","เป็ด","ไก่","ห่าน")));

                Collections.shuffle(ExtremeLevelQuestion);
                questions.addAll(ExtremeLevelQuestion);
                quizPage(difficultyLevel);
            }
        }
           
    }


    public static Button skipButton(String difficultyLevel) { 
        Button skip = new Button("Skip");
        
        skip.setPrefWidth(165);
        skip.setPrefHeight(50);

        skip.setBackground(new Background(new BackgroundImage(new Image(Objects.requireNonNull(Goto.class.getResourceAsStream("/choiceback.png"))),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, true, false))));

        skip.setOnMouseExited(mouseEvent -> {
            skip.setBackground(new Background(new BackgroundImage(new Image(Objects.requireNonNull(Goto.class.getResourceAsStream("/choiceback.png"))),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                    new BackgroundSize(100, 100, true, true, true, false))));
            skip.setTextFill(Color.BLACK);
        });

        skip.setOnMouseEntered(mouseEvent -> {
            skip.setBackground(new Background(new BackgroundImage(new Image(Objects.requireNonNull(Goto.class.getResourceAsStream("/choiceback2.png"))),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                    new BackgroundSize(100, 100, true, true, true, false))));
            skip.setTextFill(Color.WHITE);
        });

        skip.setFont(Font.font("Noto Sans Thai", FontWeight.BOLD, 17));
        HBox.setMargin(skip, new Insets(0, 40, 0, 0));

        
        skip.setOnMouseClicked(mouseEvent -> {
        	checkQuiz(difficultyLevel);
        	setHintClick(false);
        });

        return skip;
    }
    public static void quizPage(String difficultyLevel) {
        clear();

        Stage stage = (Stage) rootPane.getScene().getWindow();
        double width = stage.getWidth();
        double height = stage.getHeight();

        BackgroundImage bgImg = new BackgroundImage(new Image(Objects.requireNonNull(Goto.class.getResourceAsStream("/titlescreen_background.jpg"))),
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

        LifePane playerALife = new LifePane("PLAYER A");
        LifePane playerBLife = new LifePane("PLAYER B");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        topStatus.getChildren().addAll(playerALife, spacer, playerBLife);
        rootPane.getChildren().add(topStatus);

        gameTimer.setAlignment(Pos.CENTER);
        rootPane.getChildren().add(gameTimer);
        VBox.setMargin(gameTimer, new Insets(-120, 0, 0, 0));
        gameTimer.start(40);

        QuizPane quizPane = new QuizPane(questions.getFirst(), difficultyLevel);
        VBox.setVgrow(quizPane, Priority.ALWAYS);
        rootPane.getChildren().add(quizPane);

        HBox inputContainer = new HBox(80);
        inputContainer.setAlignment(Pos.CENTER);
        inputContainer.setPadding(new Insets(0, 60, 50, 60));

        TextPane textPane1 = new TextPane(difficultyLevel, "PLAYER A INPUT");
        TextPane textPane2 = new TextPane(difficultyLevel, "PLAYER B INPUT");

        HBox.setHgrow(textPane1, Priority.ALWAYS);
        HBox.setHgrow(textPane2, Priority.ALWAYS);

        inputContainer.getChildren().addAll(textPane1, textPane2);
        rootPane.getChildren().add(inputContainer);

        gameTimer.setOnTimeOut(() -> {
            System.out.println("Time's up!");
        });
    }

    public static void hintPage(BaseQuestion question, String difficultyLevel, int index) {
        clear();
        currentHintIndex = index;
        rootPane.getChildren().add(new HintPane(question, difficultyLevel, index));
    }


    public static Boolean checkAnswer(String choice, String difficultyLevel) {
	
        Boolean isCorrect = choice.equals(questions.getFirst().getAnswer());

        if (isCorrect) {
        	setAnswerStreak(getAnswerStreak() + 1);
        	
        	if(questions.getFirst().getChoiceType() == ChoiceType.CHOICE) {
        		yourScore += (questions.getFirst().getScore() + (5)*(getAnswerStreak() - 1));
        	}
        	else {
        		yourScore += ((2)*(questions.getFirst().getScore()) + (5)*(getAnswerStreak() - 1));
        	}
        }
        
        else {
        	yourScore -= 5;
        	setAnswerStreak(0);
        }
        
        
      
        if(yourScore < 0) {
        	yourScore = 0;
        }
        
        return isCorrect;
    }


    public static void checkQuiz(String difficultyLevel) { 
        clear();
        questions.removeFirst();
        if (questions.isEmpty()) {
            scorePage(difficultyLevel); 
            setAnswerStreak(0);
        } else{
            quizPage(difficultyLevel); 
        }
    }


    public static void scorePage(String difficultyLevel){
        clear();
        
        Stage stage = (Stage) rootPane.getScene().getWindow(); 
        double width = stage.getWidth();
        double height = stage.getHeight();
        
        Background bg;
        BackgroundImage bgImg = new BackgroundImage(new Image(Objects.requireNonNull(Goto.class.getResourceAsStream("/puzzle_background.jpg"))), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(width, height, false, false, false, false));
        bg = new Background(bgImg);
        rootPane.setBackground(bg);

        music(Objects.requireNonNull(Goto.class.getResource("/music/score_music.mp3")).toExternalForm());
        rootPane.getChildren().add(new ScorePane(yourScore, difficultyLevel)); 
    }
    
    public static void levelSelectionPage(GameLevelSelector gameLevelSelector) {
    	clear();
    	
    	rootPane.getChildren().add(new LevelSelectionPane(gameLevelSelector));
    }
    

	public static int getAnswerStreak() {
		return answerStreak;
	}

	public static void setAnswerStreak(int answerStreak) {
		Goto.answerStreak = answerStreak;
	}

	public static Boolean getHintClick() {
		return hintClick;
	}

	public static void setHintClick(Boolean hintClick) {
		Goto.hintClick = hintClick;
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
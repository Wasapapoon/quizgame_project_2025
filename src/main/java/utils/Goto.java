package utils;

import item.base.BaseQuestion;
import item.level.EasyQuestion;
import item.level.HardQuestion;
import item.level.MediumQuestion;
import item.quiz.ChoiceQuiz;
import item.usage.ChoiceType;
import item.usage.hasHint;
import item.usage.hasPicture;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import pane.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.regex.Pattern;

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
        if(mediaPlayer != null){
            mediaPlayer.stop();
        }
        Media media = new Media(musicPath);
        mediaPlayer = new MediaPlayer(media);

        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();setMediaPlayer(musicPath);
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
        ArrayList<BaseQuestion> MixedLvelQuestion = new ArrayList<>();
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

        switch (difficultyLevel) {
            case "EASY" -> {
                easyLevelQuestion.add(new EasyQuestion(ChoiceType.CHOICE, "ข้อใดเป็นสัตว์เลี้ยงยอดนิยม", "แมว"));
                easyLevelQuestion.add(new EasyQuestion(ChoiceType.CHOICE, "อุปกรณ์ใดต่อไปนี้เป็นหน่วยประมวลผลกลางของคอมพิวเตอร์", "CPU"));
                easyLevelQuestion.add(new EasyQuestion(ChoiceType.CHOICE, "กาชาไลน์เรนเจอร์ เกลือไหม", "เกลือ"));
                easyLevelQuestion.add(new EasyQuestion(ChoiceType.CHOICE, "อาหารชนิดใดที่มีโปรตีนสูงที่สุด", "ไข่"));
                easyLevelQuestion.add(new EasyQuestion(ChoiceType.CHOICE, "ควรนอนหลับวันละกี่ชั่วโมงเพื่อสุขภาพที่ดี", "7-8 ชั่วโมง"));
                easyLevelQuestion.add(new EasyQuestion(ChoiceType.CHOICE, "อุปกรณ์ใดใช้ในการตรวจวัดอุณหภูมิร่างกาย", "เทอร์โมมิเตอร์"));
                easyLevelQuestion.add(new EasyQuestion(ChoiceType.TEXT, "ปีหน้าคือปี ค.ศ. อะไร", "2026", "quiz_pic01.png"));
                easyLevelQuestion.add(new EasyQuestion(ChoiceType.TEXT, "ปีที่แล้วคือปี ค.ศ. อะไร", "2024"));
                easyLevelQuestion.add(new EasyQuestion(ChoiceType.TEXT, "ปีนี้คือปี ค.ศ. อะไร", "2025", "quiz_pic02.jpg"));
                easyLevelQuestion.add(new EasyQuestion(ChoiceType.TEXT, "ในรูปมีแมวกี่ตัว", "1", "cat.jpeg"));
                Collections.shuffle(easyLevelQuestion);
                questions.addAll(easyLevelQuestion);
                quizPage(difficultyLevel);
            }
            case "MEDIUM" -> {
                MediumLevelQuestion.add(new MediumQuestion(ChoiceType.CHOICE, "ข้อใดไม่ใช่อุปกรณ์เก็บข้อมูลถาวร", "RAM", "อุปกรณ์ที่เก็บข้อมูล \"ชั่วคราว\"\nจะลบข้อมูลเมื่อปิดเครื่อง"));
                MediumLevelQuestion.add(new MediumQuestion(ChoiceType.CHOICE, "สีของผักที่มีสารต้านอนุมูลอิสระสูงคือสีอะไร", "ถูกทุกข้อ", "มีหลายสี"));
                MediumLevelQuestion.add(new MediumQuestion(ChoiceType.CHOICE, "อยากมีแฟน ทำไงดี", "หาคู่ด้วย CU-NEX", "ใช้แอพในการหาคู่"));
                MediumLevelQuestion.add(new MediumQuestion(ChoiceType.CHOICE, "ทำไมเราไม่ควรนำจักรยานแช่ตู้เย็น", "เดี๋ยวจักรยานจะหนาว", "ในตู้เย็นมันหนาว"));
                MediumLevelQuestion.add(new MediumQuestion(ChoiceType.CHOICE, "ใครคือนายกคนปัจจุบันของไทย", "ทักษิณ ชินวัตร", "ให้ตอบนายกตัวจริง"));
                MediumLevelQuestion.add(new MediumQuestion(ChoiceType.TEXT, "50 เดือนนับจากวันที่ 1 ม.ค. 2568 คือปีอะไร", "2572", "4 ปี คือ 48 เดือน"));
                MediumLevelQuestion.add(new MediumQuestion(ChoiceType.TEXT, "ประเทศไทยมีทั้งหมดกี่อำเภอ", "878", "เกือบๆ 900 อำเภอ", "quiz_pic03.jpg"));
                MediumLevelQuestion.add(new MediumQuestion(ChoiceType.TEXT, "500 สัปดาห์นับจากวันที่ 1 ม.ค. 2568 คือปีอะไร", "2577", "10 ปี เท่ากับ 520 สัปดาห์"));
                MediumLevelQuestion.add(new MediumQuestion(ChoiceType.TEXT, "ควอร์ไทล์ที่3 การแจกแจงแบบสม่ำเสมอ U(0,20)คือเท่าใด", "15", "สูตรหาควอร์ไทล์ที่ 3 สำหรับ\nการแจกแจงแบบสม่ำเสมอคือ Q3=a+0.75(b−a)"));
                MediumLevelQuestion.add(new MediumQuestion(ChoiceType.TEXT, "มัธยฐานของการแจกแจงสม่ำเสมอ U(5,25) คือเท่าใด", "15", " สูตรมัธยฐานของการแจกแจงสม่ำเสมอ\nคำนวณจาก:Median=(a+b)/2"));


                Collections.shuffle(MediumLevelQuestion);
                questions.addAll(MediumLevelQuestion);
                quizPage(difficultyLevel);
            }
            case "HARD" -> {

                HardLevelQuestion.add(new HardQuestion(ChoiceType.CHOICE, "เวลาใดเหมาะสมกับการนอนหลับที่สุด", "5:00", "ตอบกวนๆ"));
                HardLevelQuestion.add(new HardQuestion(ChoiceType.CHOICE, "เครื่องดื่มชนิดใดที่ช่วยให้ร่างกายสดชื่นมากที่สุด", "เหล้า", "ตอบกวนๆ"));
                HardLevelQuestion.add(new HardQuestion(ChoiceType.CHOICE, "ถ้า\"พี่โต\"เป็นพระเอกหนังพี่โตจะเล่นแนวไหน", "หนังดราม่า\"โตแล้วเจ็บ\"", "พี่โตต้องเป็นสายดราม่า"));
                HardLevelQuestion.add(new HardQuestion(ChoiceType.CHOICE, "ถ้าเจอปุ่ม \"ห้ามกด\" คุณจะทำอะไร", "กดทันทีแบบไม่ต้องคิด", "คนเรามักมีสัญชาตญาณขัดคำสั่งโดยอัตโนมัติ"));
                HardLevelQuestion.add(new HardQuestion(ChoiceType.CHOICE, "ถ้าเราอยู่ในเกม RPG และโดน NPC ด่า เราจะทำยังไง", "กระโดดเตะ NPC", "คนที่เล่นเกมแนวนี้มักจะชอบทดสอบระบบเสมอ"));

                HardLevelQuestion.add(new HardQuestion(ChoiceType.TEXT, "5000 วันนับจากวันที่ 1 ม.ค. 2568 คือปีอะไร", "2581", "10 ปี ประมาณ 3600 วัน, 3 ปี ประมาณ 1000 วัน"));
                HardLevelQuestion.add(new HardQuestion(ChoiceType.TEXT, "10000 วัน ประมาณกี่ปี", "27", "30 ปี ประมาณ 11000 วัน"));
                HardLevelQuestion.add(new HardQuestion(ChoiceType.TEXT, "20000 ชั่วโมง ประมาณกี่ปี", "2.28", "1 ปี มีประมาณ 8760 ชั่วโมง"));
                HardLevelQuestion.add(new HardQuestion(ChoiceType.TEXT, "1000000 วินาที ประมาณกี่วัน", "12", "10 วัน เท่ากับ 864000 วินาที"));
                HardLevelQuestion.add(new HardQuestion(ChoiceType.TEXT, "1000000 วินาที ประมาณกี่วัน", "12", "10 วัน เท่ากับ 864000 วินาที"));


                Collections.shuffle(HardLevelQuestion);
                questions.addAll(HardLevelQuestion);
                quizPage(difficultyLevel);
            }
            case "MIXED" -> {
                MixedLvelQuestion.add(new EasyQuestion(ChoiceType.CHOICE, "กาชาไลน์เรนเจอร์ เกลือไหม", "เกลือ"));
                MixedLvelQuestion.add(new EasyQuestion(ChoiceType.CHOICE, "อุปกรณ์ใดใช้ในการตรวจวัดอุณหภูมิร่างกาย", "เทอร์โมมิเตอร์"));
                MixedLvelQuestion.add(new EasyQuestion(ChoiceType.TEXT, "ปีหน้าคือปี ค.ศ. อะไร", "2026", "quiz_pic01.png"));
                MixedLvelQuestion.add(new EasyQuestion(ChoiceType.TEXT, "ปีนี้คือปี ค.ศ. อะไร", "2025", "quiz_pic02.jpg"));

                MixedLvelQuestion.add(new MediumQuestion(ChoiceType.CHOICE, "ทำไมเราไม่ควรนำจักรยานแช่ตู้เย็น", "เดี๋ยวจักรยานจะหนาว", "ในตู้เย็นมันหนาว"));
                MixedLvelQuestion.add(new MediumQuestion(ChoiceType.CHOICE, "ใครคือนายกคนปัจจุบันของไทย", "ทักษิณ ชินวัตร", "ให้ตอบนายกตัวจริง"));
                MixedLvelQuestion.add(new MediumQuestion(ChoiceType.TEXT, "ประเทศไทยมีทั้งหมดกี่อำเภอ", "878", "เกือบๆ 900 อำเภอ", "quiz_pic03.jpg"));

                MixedLvelQuestion.add(new HardQuestion(ChoiceType.CHOICE, "เวลาใดเหมาะสมกับการนอนหลับที่สุด", "5:00", "ตอบกวนๆ"));
                MixedLvelQuestion.add(new HardQuestion(ChoiceType.CHOICE, "เครื่องดื่มชนิดใดที่ช่วยให้ร่างกายสดชื่นมากที่สุด", "เหล้า", "ตอบกวนๆ"));
                MixedLvelQuestion.add(new HardQuestion(ChoiceType.TEXT, "5000 วันนับจากวันที่ 1 ม.ค. 2568 คือปีอะไร", "2581", "10 ปี ประมาณ 3600 วัน, 3 ปี ประมาณ 1000 วัน"));

                Collections.shuffle(MixedLvelQuestion);
                questions.addAll(MixedLvelQuestion);


                quizPage(difficultyLevel);
            }
            default -> {
                ExtremeLevelQuestion.add(new HardQuestion(ChoiceType.CHOICE, "เวลาใดเหมาะสมกับการนอนหลับที่สุด", "5:00", "ตอบกวนๆ"));
                ExtremeLevelQuestion.add(new HardQuestion(ChoiceType.CHOICE, "เครื่องดื่มชนิดใดที่ช่วยให้ร่างกายสดชื่นมากที่สุด", "เหล้า", "ตอบกวนๆ"));
                ExtremeLevelQuestion.add(new HardQuestion(ChoiceType.CHOICE, "ถ้า\"พี่โต\"เป็นพระเอกหนังพี่โตจะเล่นแนวไหน", "หนังดราม่า\"โตแล้วเจ็บ\"", "พี่โตต้องเป็นสายดราม่า"));
                ExtremeLevelQuestion.add(new HardQuestion(ChoiceType.CHOICE, "ถ้าเจอปุ่ม \"ห้ามกด\" คุณจะทำอะไร", "กดทันทีแบบไม่ต้องคิด", "คนเรามักมีสัญชาตญาณขัดคำสั่งโดยอัตโนมัติ"));
                ExtremeLevelQuestion.add(new HardQuestion(ChoiceType.CHOICE, "ถ้าเราอยู่ในเกม RPG และโดน NPC ด่า เราจะทำยังไง", "กระโดดเตะ NPC", "คนที่เล่นเกมแนวนี้มักจะชอบทดสอบระบบเสมอ"));
                ExtremeLevelQuestion.add(new HardQuestion(ChoiceType.CHOICE, "มดอะไรกัดเจ็บที่สุด", "มดทอระยิด", "มิตรทรยศ"));
                ExtremeLevelQuestion.add(new HardQuestion(ChoiceType.TEXT, "ถ้าพี่โตบอกจะเริ่มลดน้ำหนักพรุ่งนี้โอกาสที่จะเป็นจริงกี่เปอร์เซ็นต์", "0", "พี่โตไม่มีทางลดน้ำหนัก\nเพราะพูดอย่างเดียวแต่ไม่เริ่มทำสักที"));
                ExtremeLevelQuestion.add(new HardQuestion(ChoiceType.TEXT, "ถ้าพี่โตบอกว่าจะออกกำลังกายทุกวันตั้งแต่พรุ่งนี้\n        โอกาสที่พรุ่งนี้จะมาถึงคือกี่เปอร์เซ็นต์", "-1", "ต่ำกว่าศูนย์อยู่หนึ่งซึ่งแปลว่าไม่มีวันเกิดขึ้นจริง"));
                ExtremeLevelQuestion.add(new HardQuestion(ChoiceType.TEXT, "โอกาสที่พี่โตจะตอบแชททันทีในช่วงงานยุ่ง ๆ เป็นกี่เปอร์เซ็นต์", "1", "อาจมีโอกาสเกิดขึ้น แต่แทบเป็นไปไม่ได้"));
                ExtremeLevelQuestion.add(new HardQuestion(ChoiceType.TEXT, "  หากพี่โตตั้งใจจะสอน Java ให้นักเรียนภายใน\nเวลาที่กำหนดพี่โตจะใช้เวลาสอนจริงนานเท่าไหร่", "9999999999", "มากมายมหาศาล"));


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
        
        Background bg;
        BackgroundImage bgImg = new BackgroundImage(new Image(Objects.requireNonNull(Goto.class.getResourceAsStream("/quiz_background.jpg"))), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(width, height, false, false, false, false));
        bg = new Background(bgImg);
        rootPane.setBackground(bg);
        
        
        
        ArrayList<String> choices = new ArrayList<>();
        if(questions.getFirst().getChoiceType() == ChoiceType.CHOICE){
            for(ChoiceQuiz c:choiceAnswers){
                if(Objects.equals(c.getQuestion(), questions.getFirst().getQuestion())){
                    choices.addAll(c.getChoices());
                }
            }
        }
             
        
        HBox hBox = new HBox(0);
        hBox.setAlignment(Pos.TOP_RIGHT);
        
        if(questions.getFirst() instanceof hasHint) {
        	
        	hBox.getChildren().addAll(new HintButtonPane(questions.getFirst(), difficultyLevel), skipButton(difficultyLevel));
        }
        else {
        	hBox.getChildren().add(skipButton(difficultyLevel));
        }
        
        rootPane.getChildren().add(hBox);
     	
        if(questions.getFirst() instanceof hasHint) {
        	if(((hasHint) questions.getFirst()).useHint()) {
        		yourScore -= 2;
        	}
        	if(yourScore < 0) {
        		yourScore = 0;
        	}
        }
      
        Label score = new Label("Your Score : " + yourScore);
        score.setFont(Font.font("Noto Sans Thai", FontWeight.BOLD, 36));
        score.setTextFill(Color.WHITE);
        VBox.setMargin(score, new Insets(-80, 0, 0, 0));
        rootPane.getChildren().add(score);
        rootPane.getChildren().add(new QuizPane(questions.getFirst()));
        
        boolean questionLine = Pattern.compile("\n").matcher(questions.getFirst().getQuestion()).find();
        
        ChoicePane choicePane = new ChoicePane(choices, difficultyLevel);
        TextPane textPane = new TextPane(difficultyLevel);
        
        if(questions.getFirst() instanceof hasPicture) {
        	
        	 if(((hasPicture) questions.getFirst()).getHasPicture()) {
        		VBox.setMargin(textPane, new Insets(getWindowHeight()-659, 0, 0, 0));
             }
        	 else if(!((hasPicture) questions.getFirst()).getHasPicture() && questionLine){
        		VBox.setMargin(textPane, new Insets(getWindowHeight()-424, 0, 0, 0));
        	 }
        	 else {
        		 VBox.setMargin(textPane, new Insets(getWindowHeight()-374, 0, 0, 0)); 
        	 }
        }
       
        else {
        	
        	if(questionLine) {
        		VBox.setMargin(textPane, new Insets(getWindowHeight()-424, 0, 0, 0));
        	}
        	else {
        		VBox.setMargin(textPane, new Insets(getWindowHeight()-374, 0, 0, 0));
        	}	
        }
        
        if (questions.getFirst().getChoiceType() == ChoiceType.CHOICE) {
        	rootPane.getChildren().add(choicePane); 
        } else {
        	rootPane.getChildren().add(textPane);
        }
        
    }

    

    public static void hintPage(BaseQuestion question, String difficultyLevel) { 
        clear();
        rootPane.getChildren().add(new HintPane(question, difficultyLevel)); 
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
        BackgroundImage bgImg = new BackgroundImage(new Image(Objects.requireNonNull(Goto.class.getResourceAsStream("/quiz_background_2.jpg"))), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(width, height, false, false, false, false));
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
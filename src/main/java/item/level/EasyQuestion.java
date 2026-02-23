package item.level;

import item.base.BaseQuestion;
import item.usage.ChoiceType;
import item.usage.hasPicture;

public class EasyQuestion extends BaseQuestion implements hasPicture {

    private String question;

    private String answer;

    private Boolean hasPicture = false;
    
    private String pictureName;

    private final int SCORE = 10;

    public EasyQuestion(ChoiceType type, String question, String answer) {
        super(type,question,answer);
    }

    public EasyQuestion(ChoiceType type, String question, String answer, String pictureName) {
        super(type,question,answer);
        hasPicture = true;
        setPictureName(pictureName);
    }

    @Override
    public String getAnswer(){
        return answer;
    }

    @Override
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public int getScore(){
        return SCORE;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Override
    public boolean getHasPicture() {
        return hasPicture;
    }

    @Override
    public String getPictureName() {
        return pictureName;
    }

    @Override
    public void setPictureName(String pictureName){
        this.pictureName = pictureName;
    }

}



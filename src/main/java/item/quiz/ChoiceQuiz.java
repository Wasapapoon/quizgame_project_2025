package item.quiz;

import item.base.BaseQuiz;
import item.usage.ChoiceType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ChoiceQuiz extends BaseQuiz {

    private String question;

    private ArrayList<String> choices = new ArrayList<>();

    public ChoiceQuiz() {
        super(ChoiceType.CHOICE);
    }

    public void addChoices(String question, String... choice){
        setQuestion(question);
        choices.addAll(Arrays.asList(choice));
        Collections.shuffle(choices);
    }

    public ArrayList<String> getChoices() {
        return choices;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

}

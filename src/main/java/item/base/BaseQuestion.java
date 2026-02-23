package item.base;

import item.usage.ChoiceType;

public abstract class BaseQuestion extends BaseQuiz {

    public BaseQuestion(ChoiceType choiceType, String question, String answer) {
        super(choiceType);
        setQuestion(question);
        setAnswer(answer);
    }

    public abstract String getAnswer();

    public abstract void setAnswer(String answer);

    public abstract String getQuestion() ;

    public abstract void setQuestion(String question);

    public abstract int getScore();

}

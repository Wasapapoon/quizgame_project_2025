package item.base;

import item.usage.ChoiceType;

public abstract class BaseQuestion {
    private final ChoiceType type;
    private final String answer;

    public BaseQuestion(ChoiceType type, String answer) {
        this.type = type;
        this.answer = answer;
    }

    public ChoiceType getChoiceType() {
        return type;
    }

    public String getAnswer() {
        return answer;
    }


    public int getScore() {
        return 0;
    }

    public String getQuestion() {
        return "abc" ;
    }
}
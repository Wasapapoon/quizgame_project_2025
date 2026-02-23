package item.level;

import item.base.BaseQuestion;
import item.usage.ChoiceType;
import item.usage.hasHint;

public class HardQuestion extends BaseQuestion implements hasHint {

    private String question;

    private String answer;
    
    private String hint;
    
    private final Boolean hasHint = true;
    
    private Boolean useHint = false;

    private final int SCORE = 20;

    public HardQuestion(ChoiceType type, String question, String answer, String hint) {
        super(type, question,answer);
        this.setHint(hint);
    }

    public String getQuestion() {
        return question;
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

    public void setQuestion(String question) {
        this.question = question;
    }
    
    @Override
	public void setHint(String hint) {
		this.hint = hint ;
		
	}

	@Override
	public String getHint() {
		
		return hint;
	}

	@Override
	public boolean getHasHint() {
		
		return hasHint;
	}

	@Override
	public boolean useHint() {
		
		return useHint;
	}

	@Override
	public void setUseHint(boolean useHint) {
		this.useHint = useHint;
		
	}

}

package item.level;

import item.base.BaseQuestion;
import item.usage.ChoiceType;
import item.usage.hasHint;
import item.usage.hasPicture;

public class MediumQuestion extends BaseQuestion implements hasHint, hasPicture {

    private String question;

    private String answer;

    private Boolean hasPicture = false;

    private String pictureName;
    
    private String hint;
    
    private Boolean useHint = false;
    
    private final Boolean hasHint = true;

    private final int SCORE = 15;

    public MediumQuestion(ChoiceType type, String question, String answer, String hint) {
        super(type,question,answer);
        this.setHint(hint);
    }

    public MediumQuestion(ChoiceType type, String question, String answer, String hint, String pictureName) {
        super(type,question,answer);
        hasPicture = true;
        this.setPictureName(pictureName);
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

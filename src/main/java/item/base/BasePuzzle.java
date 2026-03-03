package item.base;

import java.util.List;

public abstract class BasePuzzle {

    private final String answer;

    public BasePuzzle(String answer) {
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public abstract boolean getHasPicture();

    public abstract void setPictureName(String pictureName);

    public abstract List<String> getPictureNames();

    public abstract int getTimeLimit();

}
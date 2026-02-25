package item.level;

import item.base.BaseQuestion;
import item.usage.ChoiceType;
import item.usage.hasPicture;

import java.util.List;

public class EasyQuestion extends BaseQuestion implements hasPicture {

    private final List<String> pictureNames;
    private boolean hasPicture = false;

    public EasyQuestion(ChoiceType type, String answer, List<String> pictureNames) {
        super(type, answer);
        this.pictureNames = pictureNames;
        this.hasPicture = true;
    }

    @Override
    public boolean getHasPicture() {
        return hasPicture;
    }

    @Override
    public void setPictureName(String pictureName) {

    }

    @Override
    public List<String> getPictureNames() {
        return pictureNames;
    }

}
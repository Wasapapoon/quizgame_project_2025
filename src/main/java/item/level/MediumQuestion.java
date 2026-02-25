package item.level;

import item.base.BaseQuestion;
import item.usage.ChoiceType;
import item.usage.hasHint;
import item.usage.hasPicture;

import java.util.List;

public class MediumQuestion extends BaseQuestion implements hasPicture, hasHint {

    private final List<String> pictureNames;
    private String hint;
    private boolean useHint = false;

    public MediumQuestion(ChoiceType type, String answer, List<String> pictureNames, String hint) {
        super(type, answer);
        this.pictureNames = pictureNames;
        this.hint = hint;
    }

    @Override
    public boolean getHasPicture() {
        return true;
    }

    @Override
    public void setPictureName(String pictureName) {

    }

    @Override
    public List<String> getPictureNames() {
        return pictureNames;
    }

    @Override
    public String getHint() {
        return hint;
    }

    @Override
    public void setHint(String hint) {
        this.hint = hint;
    }

    @Override
    public boolean getHasHint() {
        return true;
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
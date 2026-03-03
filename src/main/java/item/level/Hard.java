package item.level;

import item.base.BasePuzzle;
import item.usage.hasHint;

import java.util.List;

public class Hard extends BasePuzzle implements hasHint {

    private final List<String> pictureNames;
    private List<String> hint;

    public Hard(String answer, List<String> pictureNames, List<String> hint) {
        super(answer);
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
    public List<String> getHint() {
        return hint;
    }

    public void setHint(List<String> hint) {
        this.hint = hint;
    }

    @Override
    public boolean getHasHint() {
        return true;
    }
}
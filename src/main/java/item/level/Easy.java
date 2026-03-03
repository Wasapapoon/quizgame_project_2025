package item.level;

import item.base.BasePuzzle;

import java.util.List;

public class Easy extends BasePuzzle {

    private final List<String> pictureNames;
    private boolean hasPicture = false;

    public Easy(String answer, List<String> pictureNames) {
        super(answer);
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
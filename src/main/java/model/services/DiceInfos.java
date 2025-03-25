package model.services;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DiceInfos {
    Image backImg = new Image(getClass().getResourceAsStream("/imgs/img/BackTest.png"));
    private int imgId;
    private ImageView imgvDice;

    public DiceInfos(int imgId, ImageView labelDice) {
        this.imgId = imgId;
        imgvDice = labelDice;
    }

    public void diableClick() {
        if (imgvDice != null) {
            imgvDice.setOnMouseClicked(null);
        }
    }

    public void setBackImg(){
        imgvDice.setImage(backImg);
    }
    public int getImgId() {
        return imgId;
    }

    public ImageView getImgvDice() {
        return imgvDice;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public void setImgvDice(ImageView imgvDice) {
        this.imgvDice = imgvDice;
    }
}

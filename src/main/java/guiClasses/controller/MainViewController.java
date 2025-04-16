package guiClasses.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {
    private AudioClip clickUiSound;
    private AudioClip clickCloseSound;
    @FXML
    private ImageView imgvVowelImg;

    @FXML
    private ImageView imgvDiceImg;

    @FXML
    private ImageView imgvMathImg;

    @FXML
    private ImageView imgvCloseImg;

    @FXML
    private void onImgvCloseImgClick(MouseEvent event){
        clickCloseSound.play();
        Platform.exit();
    }

    @FXML
    private void onImgvMathImgClick(MouseEvent event){
        clickUiSound.play();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/mathViews/ChooseMathView.fxml"));
            Parent root = loader.load();
            Scene scene = imgvVowelImg.getScene();
            scene.setRoot(root);
        } catch (IOException e) {
            //System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void onImgvVowelImgClick(MouseEvent event){
        clickUiSound.play();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/vowelViews/VowelView.fxml"));
            Parent root = loader.load();
            Scene scene = imgvVowelImg.getScene();
            scene.setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());

        }
    }

    @FXML
    private void onImgvDiceImgClick(MouseEvent event){
        clickUiSound.play();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/memoryViews/MemoryView.fxml"));
            Parent root = loader.load();
            Scene scene = imgvDiceImg.getScene();
            scene.setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String soundClickPath = getClass().getResource("/sounds/click/ClickOnUI01.mp3").toString();
        this.clickUiSound = new AudioClip(soundClickPath);
        this.clickUiSound.setVolume(0.1);

        String soundClosePath = getClass().getResource("/sounds/close/closeSound.mp3").toString();
        this.clickCloseSound = new AudioClip(soundClosePath);
        this.clickCloseSound.setVolume(1.0);
    }
}

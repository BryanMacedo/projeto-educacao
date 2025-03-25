package guiClasses.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MemoryCongratulationViewController implements Initializable {
    private AudioClip congratulationSound;
    @FXML
    private ImageView imgvHomeImg;

    @FXML
    private ImageView imgvVowelImg;


    @FXML
    private Label labelCongratulation;

    @FXML
    private Button btPlayAgain;

    @FXML
    private void onImgvVowelImgClick(MouseEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/CompleteTheVowel.fxml"));
            Parent root = loader.load();
            Scene scene = imgvHomeImg.getScene();
            scene.setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void onImgvHomeImgClick(MouseEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml"));
            Parent root = loader.load();
            Scene scene = imgvHomeImg.getScene();
            scene.setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void onBtPlayAgainAction(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MemoryView.fxml"));
            Parent root = loader.load();
            Scene scene = labelCongratulation.getScene();
            scene.setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String soundClickPath = getClass().getResource("/sounds/congratulation/congratulation.mp3").toString();
        this.congratulationSound = new AudioClip(soundClickPath);
        this.congratulationSound.setVolume(0.2); // volume 0.0 a 1.0

        congratulationSound.play();
    }
}

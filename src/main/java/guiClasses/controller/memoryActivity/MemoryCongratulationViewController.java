package guiClasses.controller.memoryActivity;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
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
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MemoryCongratulationViewController implements Initializable {
    private AudioClip congratulationSound;
    private AudioClip clickUiSound;
    private AudioClip clickBackSound;

    @FXML
    private ImageView imgvHomeImg;

    @FXML
    private ImageView imgvVowelImg;

    @FXML
    private ImageView imgvMathImg;


    @FXML
    private Label labelCongratulation;

    @FXML
    private Button btPlayAgain;

    @FXML
    private ImageView imgvCloseImg;

    @FXML
    private void onImgvCloseImgClick(MouseEvent event){
        clickUiSound.play();
        Platform.exit();
    }

    @FXML
    private void onImgvMathImgClick(MouseEvent event){
        clickUiSound.play();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/mathViews/ChooseMathView.fxml"));
            Parent root = loader.load();
            Scene scene = imgvMathImg.getScene();
            scene.setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void onImgvVowelImgClick(MouseEvent event){
        clickUiSound.play();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/vowelViews/VowelView.fxml"));
            Parent root = loader.load();
            Scene scene = imgvHomeImg.getScene();
            scene.setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void onImgvHomeImgClick(MouseEvent event){
        if (event.getTarget() instanceof ImageView) {
            clickUiSound.play();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/mainViews/MainView.fxml"));
                Parent root = loader.load();
                Scene scene = imgvHomeImg.getScene();
                scene.setRoot(root);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @FXML
    private void onBtPlayAgainAction(){
        clickBackSound.play();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/memoryViews/MemoryView.fxml"));
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
        this.congratulationSound.setVolume(0.2);

        String soundClickOnUiPath = getClass().getResource("/sounds/click/ClickOnUI01.mp3").toString();
        this.clickUiSound = new AudioClip(soundClickOnUiPath);
        this.clickUiSound.setVolume(0.1);

        String soundBackPath = getClass().getResource("/sounds/back/BackSound01.mp3").toString();
        this.clickBackSound = new AudioClip(soundBackPath);
        this.clickBackSound.setVolume(0.3);

        congratulationSound.play();

        btPlayAgain.setDisable(true);
        imgvVowelImg.setDisable(true);
        imgvHomeImg.setDisable(true);
        imgvMathImg.setDisable(true);

        PauseTransition pause = new PauseTransition(Duration.seconds(4.5));
        pause.setOnFinished(event -> {
            btPlayAgain.setDisable(false);
            imgvVowelImg.setDisable(false);
            imgvHomeImg.setDisable(false);
            imgvMathImg.setDisable(false);
        });
        pause.play();
    }
}

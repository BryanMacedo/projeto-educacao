package guiClasses.controller.mathActivity;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;
import model.entities.mathEntities.TypeMathOperation;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static model.entities.mathEntities.TypeMathOperation.*;

public class ChooseMathViewController implements Initializable {
    private final Image imgSumSelected = new Image(getClass().getResourceAsStream("/imgs/img/imgsMathActivity/sumSelected.png"));
    private final Image imgSumUnselected = new Image(getClass().getResourceAsStream("/imgs/img/imgsMathActivity/sumUnselected.png"));
    private final Image imgSubtractSelected = new Image(getClass().getResourceAsStream("/imgs/img/imgsMathActivity/subtractSelected.png"));
    private final Image imgSubtractUnselected = new Image(getClass().getResourceAsStream("/imgs/img/imgsMathActivity/subtractUnselected.png"));
    private final Image imgMultiplySelected = new Image(getClass().getResourceAsStream("/imgs/img/imgsMathActivity/multiplySelected.png"));
    private final Image imgMultiplyUnselected = new Image(getClass().getResourceAsStream("/imgs/img/imgsMathActivity/multiplyUnselected.png"));
    private final Image imgDivideSelected = new Image(getClass().getResourceAsStream("/imgs/img/imgsMathActivity/divideSelected.png"));
    private final Image imgDivideUnselected = new Image(getClass().getResourceAsStream("/imgs/img/imgsMathActivity/divideUnselected.png"));

    private static List<TypeMathOperation> mathOperations = new ArrayList<>();

    @FXML
    private ImageView imgvHomeImg;

    @FXML
    private ImageView imgvVowelImg;

    @FXML
    private ImageView imgvDiceImg;

    @FXML
    private ImageView imgvSumImg;

    @FXML
    private ImageView imgvSubtractImg;

    @FXML
    private ImageView imgvMultiplyImg;

    @FXML
    private ImageView imgvDivideImg;

    @FXML
    private Label labelEarlyExpression;

    @FXML
    private Button btStart;

    private AudioClip clickSound;
    private AudioClip clickUnselectSound;
    private AudioClip errorSound;
    private AudioClip clickUiSound;
    private AudioClip clickBtSound;

    private void changeImgvs(ImageView idImageView, Image selectedImg, Image unselectedImg, TypeMathOperation mathOperation){
        if (!mathOperations.contains(mathOperation)){
            clickSound.play();
            labelEarlyExpression.setStyle("-fx-text-fill: white;");
            idImageView.setImage(selectedImg);
            mathOperations.add(mathOperation);
        }else {
            clickUnselectSound.play();
            idImageView.setImage(unselectedImg);
            mathOperations.remove(mathOperation);
        }
    }

    @FXML
    private void onImgvSumImgClick(MouseEvent event) {
        if (event.getTarget() == imgvSumImg || imgvSumImg.getBoundsInParent().contains(event.getX(), event.getY())) {
            changeImgvs(imgvSumImg, imgSumSelected, imgSumUnselected, ADICAO);
            event.consume();
        }
    }

    @FXML
    private void onImgvSubtractImgClick(MouseEvent event) {
        event.consume();
        changeImgvs(imgvSubtractImg, imgSubtractSelected, imgSubtractUnselected, SUBTRACAO);
    }

    @FXML
    private void onImgvMultiplyClick(MouseEvent event) {
        event.consume();
        changeImgvs(imgvMultiplyImg, imgMultiplySelected, imgMultiplyUnselected, MULTIPLICACAO);
    }

    @FXML
    private void onImgvDivideClick(MouseEvent event) {
        event.consume();
        imgvDivideImg.setImage(imgDivideSelected);
        changeImgvs(imgvDivideImg, imgDivideSelected, imgDivideUnselected, DIVISAO);
    }

    @FXML
    private void onBtStartAction(){
        if (!mathOperations.isEmpty()){
            clickBtSound.play();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/mathViews/MathView.fxml"));
                Parent root = loader.load();
                Scene scene = imgvVowelImg.getScene();
                scene.setRoot(root);
            } catch (IOException e) {
                //System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }else {
            errorSound.play();
            labelEarlyExpression.setStyle("-fx-text-fill: red;");
        }
    }

    public static List<TypeMathOperation> getMathOperations() {
        return mathOperations;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mathOperations.clear();

        String soundClickPath = getClass().getResource("/sounds/click/ClickSound01.mp3").toString();
        this.clickSound = new AudioClip(soundClickPath);
        this.clickSound.setVolume(0.2);

        String soundClickUnselectPath = getClass().getResource("/sounds/click/ClickSound01Unselect.mp3").toString();
        this.clickUnselectSound = new AudioClip(soundClickUnselectPath);
        this.clickUnselectSound.setVolume(0.2);

        String soundErrorPath = getClass().getResource("/sounds/error/ErrorSound01.mp3").toString();
        this.errorSound = new AudioClip(soundErrorPath);
        this.errorSound.setVolume(0.3);

        String soundClickOnUiPath = getClass().getResource("/sounds/click/ClickOnUI01.mp3").toString();
        this.clickUiSound = new AudioClip(soundClickOnUiPath);
        this.clickUiSound.setVolume(0.1);

        String soundClickBtStartPath = getClass().getResource("/sounds/click/ClickSoundBtStart.mp3").toString();
        this.clickBtSound = new AudioClip(soundClickBtStartPath);
        this.clickBtSound.setVolume(0.3);


    }

    @FXML
    private void onImgvDiceImgClick(MouseEvent event) {
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

    @FXML
    private void onImgvVowelImgClick(MouseEvent event) {
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
    private void onImgvHomeImgClick(MouseEvent event) {
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

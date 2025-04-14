package guiClasses.controller.mathActivity;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChooseMathViewController implements Initializable {
    private final Image imgSumSelected = new Image(getClass().getResourceAsStream("/imgs/img/imgsMathActivity/sumSelected.png"));
    private final Image imgSubtractSelected = new Image(getClass().getResourceAsStream("/imgs/img/imgsMathActivity/subtractSelected.png"));
    private final Image imgMultiplySelected = new Image(getClass().getResourceAsStream("/imgs/img/imgsMathActivity/multiplySelected.png"));
    private final Image imgDivideSelected = new Image(getClass().getResourceAsStream("/imgs/img/imgsMathActivity/divideSelected.png"));

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
    private void onImgvSumImgClick(MouseEvent event){
        if (event.getTarget() instanceof ImageView) {
            imgvSumImg.setImage(imgSumSelected);
        }
    }

    @FXML
    private void onImgvSubtractImgClick(MouseEvent event){
        event.consume();
        imgvSubtractImg.setImage(imgSubtractSelected);
    }

    @FXML
    private void onImgvMultiplyClick(MouseEvent event){
        event.consume();
        imgvMultiplyImg.setImage(imgMultiplySelected);
    }

    @FXML
    private void onImgvDivideClick(MouseEvent event){
        event.consume();
        imgvDivideImg.setImage(imgDivideSelected);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void onImgvDiceImgClick(MouseEvent event){
        //clickUiSound.play();
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
    private void onImgvVowelImgClick(MouseEvent event){
        //clickUiSound.play();
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
    private void onImgvHomeImgClick(MouseEvent event){
        //clickUiSound.play();
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

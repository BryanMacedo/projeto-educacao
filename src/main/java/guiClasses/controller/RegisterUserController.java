package guiClasses.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterUserController implements Initializable {
    @FXML
    private Button buttonBackArrow;

    @FXML
    private void onButtonBackArrowAction() {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml"));
            Parent root = loader.load();

            Scene scene = buttonBackArrow.getScene();
            scene.setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image backArrowIcon = new Image(getClass().getResourceAsStream("/imgs/icons/BackArrowIcon.png"));

        ImageView backArrowIconImageView = new ImageView(backArrowIcon);

        backArrowIconImageView.setFitWidth(30);
        backArrowIconImageView.setFitHeight(30);

        buttonBackArrow.setGraphic(backArrowIconImageView);

    }
}

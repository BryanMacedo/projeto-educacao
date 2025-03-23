package guiClasses.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CongratulationViewController implements Initializable {
    @FXML
    private Label labelCongratulation;

    @FXML
    private Button btPlayAgain;

    @FXML
    private void onBtPlayAgainAction(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/TestNewMainView.fxml"));
            Parent root = loader.load();
            Scene scene = labelCongratulation.getScene();
            scene.setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
    }
}

package guiClasses.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginUserViewController implements Initializable {
    @FXML
    private Button btEnter;

    @FXML
    private void onbtEnterAction(){
        System.out.println("onbtEnterAction: Esta funcionando!");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

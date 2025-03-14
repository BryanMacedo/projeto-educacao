package guiClasses.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {
    // AO CRIAR UM CONTROLLER VERIFICAR SE O MODULO-INFO.JAVA ESTA ABRINDO E EXPORTANDO A CLASSE

    @FXML
    private Button buttonRegisterUsers;

    @FXML
    private Button buttonPlay;

    @FXML
    private Button buttonRanking;

    @FXML
    private void onButtonRegisterUsersAction(){
        System.out.println("onButtonRegisterUsersAction: esta funcionado!");
    }

    @FXML
    private void onButtonPlayAction(){
        System.out.println("onButtonPlayAction: esta funcionado!");
    }

    @FXML
    private void onButtonRankingAction(){
        System.out.println("onButtonRankingAction: esta funcionado!");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

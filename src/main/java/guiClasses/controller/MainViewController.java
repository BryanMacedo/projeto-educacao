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

public class MainViewController implements Initializable {
    // AO CRIAR UM CONTROLLER VERIFICAR SE O MODULO-INFO.JAVA ESTA ABRINDO E EXPORTANDO A CLASSE

    @FXML
    private Button btRegisterUsers;

    @FXML
    private Button btPlay;

    @FXML
    private Button btRanking;

    @FXML
    private void onBtRegisterUsersAction() {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/RegisterUserView.fxml"));
            Parent root = loader.load();

            Scene scene = btRegisterUsers.getScene();
            scene.setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void onBtPlayAction() {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/LoginUserView.fxml"));
            Parent root = loader.load();

            Scene scene = btPlay.getScene();
            scene.setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void onBtRankingAction() {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/RankingView.fxml"));
            Parent root = loader.load();

            Scene scene = btRanking.getScene();
            scene.setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image registerIcon = new Image(getClass().getResourceAsStream("/imgs/icons/RegisterIcon.png"));
        Image playIcon = new Image(getClass().getResourceAsStream("/imgs/icons/PlayIcon.png"));
        Image rankingIcon = new Image(getClass().getResourceAsStream("/imgs/icons/RankingIcon.png"));

        ImageView registerIconImageView = new ImageView(registerIcon);
        ImageView playIconImageView = new ImageView(playIcon);
        ImageView rankingIconImageView = new ImageView(rankingIcon);

        registerIconImageView.setFitWidth(20);
        registerIconImageView.setFitHeight(20);

        playIconImageView.setFitWidth(20);
        playIconImageView.setFitHeight(20);

        rankingIconImageView.setFitWidth(20);
        rankingIconImageView.setFitHeight(20);

        btRegisterUsers.setGraphic(registerIconImageView);
        btPlay.setGraphic(playIconImageView);
        btRanking.setGraphic(rankingIconImageView);
    }
}

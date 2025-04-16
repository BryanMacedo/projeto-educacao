package org.bryanmacedo.atividadesEducacionais;

import db.DB;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/mainViews/MainView.fxml"));
        primaryStage.setTitle("Atividades Educacionais");
        primaryStage.setScene(new Scene(root, 1445, 833));
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH); //desabilita o esc
        primaryStage.show();

        Image icon = new Image(getClass().getResourceAsStream("/imgs/icons/iconApp/Icon.png"));
        primaryStage.getIcons().add(icon);
    }


    public static void main(String[] args) {
        launch(args);
        DB.closeConnection();
    }
}





















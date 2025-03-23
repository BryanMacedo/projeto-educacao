package org.bryanmacedo.separacaosilabica;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/CompleteTheVowel.fxml"));
        primaryStage.setTitle("Educação");
        primaryStage.setScene(new Scene(root, 1445, 833));
        primaryStage.show();

        Image icon = new Image(getClass().getResourceAsStream("/imgs/icons/IconTest01.png"));
        primaryStage.getIcons().add(icon);
    }


    public static void main(String[] args) {
        launch(args);
    }
}





















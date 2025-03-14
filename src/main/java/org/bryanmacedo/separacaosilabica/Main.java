package org.bryanmacedo.separacaosilabica;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml")); // carrega o arquivo FXML
            Parent parent = loader.load();
            Scene mainScene = new Scene(parent);

            mainScene.getStylesheets().add(getClass().getResource("/styles/buttonStyle.css").toExternalForm());
            mainScene.getStylesheets().add(getClass().getResource("/styles/background.css").toExternalForm());

            primaryStage.setScene(mainScene);
            primaryStage.setTitle("Separação silábica");

            Image icon = new Image(getClass().getResourceAsStream("/imgs/iconeTemporario.png"));
            primaryStage.getIcons().add(icon);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
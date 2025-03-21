package org.bryanmacedo.separacaosilabica;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainTestNewView extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/TestNewMainView.fxml"));
        primaryStage.setTitle("Travel");
        primaryStage.setScene(new Scene(root, 1445, 833));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}





















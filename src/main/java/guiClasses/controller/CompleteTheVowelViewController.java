package guiClasses.controller;

import db.DB;
import db.Exception.DbException;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;
import model.entities.Word;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class CompleteTheVowelViewController implements Initializable {
    private AudioClip clickSound;
    private AudioClip clickUiSound;
    private AudioClip confirmSound;
    private AudioClip errorSound;
    List<Word> words = new ArrayList<>();

    @FXML
    private ImageView imgvHomeImg;

    @FXML
    private ImageView imgvDiceImg;

    @FXML
    private ImageView MainImage;

    @FXML
    private Label labelWordToComplete;

    @FXML
    private Button btLetterA;

    @FXML
    private Button btLetterE;

    @FXML
    private Button btLetterI;

    @FXML
    private Button btLetterO;

    @FXML
    private Button btLetterU;

    @FXML
    private void onImgvDiceImgClick(MouseEvent event){
        clickUiSound.play();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MemoryView.fxml"));
            Parent root = loader.load();
            Scene scene = imgvDiceImg.getScene();
            scene.setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void onImgvHomeImgClick(MouseEvent event){
        clickUiSound.play();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml"));
            Parent root = loader.load();
            Scene scene = imgvHomeImg.getScene();
            scene.setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void onBtLetterA_Action() {
        writeVowelInTheLabel("A");
    }

    @FXML
    private void onBtLetterE_Action() {
        writeVowelInTheLabel("E");
    }

    @FXML
    private void onBtLetterI_Action() {
        writeVowelInTheLabel("I");
    }

    @FXML
    private void onBtLetterO_Action() {
        writeVowelInTheLabel("O");
    }

    @FXML
    private void onBtLetterU_Action() {
        writeVowelInTheLabel("U");
    }

    private void writeVowelInTheLabel(String vowel){
        clickSound.play();
        String newTextLabel = labelWordToComplete.getText();

        newTextLabel = newTextLabel.replaceFirst("_", vowel);

        labelWordToComplete.setText(newTextLabel);
        VerifyWord();
    }

    private void VerifyWord() {
        String textLabelWordToComplete = labelWordToComplete.getText();
        if (!textLabelWordToComplete.contains("_") && textLabelWordToComplete.equals(words.get(0).getFullWords())) {
            labelWordToComplete.setStyle("-fx-text-fill: #FFD700;");
            PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
            pause.setOnFinished(event -> {
                confirmSound.play();
                words.remove(0);
                next();
                labelWordToComplete.setStyle("-fx-text-fill: white;");
            });
            pause.play();

        } else if (!textLabelWordToComplete.contains("_") && !textLabelWordToComplete.equals(words.get(0).getFullWords())) {
            labelWordToComplete.setStyle("-fx-text-fill: #B22222;");
            PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
            pause.setOnFinished(event -> {
                errorSound.play();
                labelWordToComplete.setText(words.get(0).getWordWith_());
                labelWordToComplete.setStyle("-fx-text-fill: white;");
            });
            pause.play();
        }
    }

    private void next() {
        if (!words.isEmpty()) {
            labelWordToComplete.setText(words.get(0).getWordWith_());
            Image newImage = new Image(getClass().getResourceAsStream("/imgs/img/" + words.get(0).getImgName()));
            MainImage.setImage(newImage);
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/VowelCongratulationView.fxml"));
                Parent root = loader.load();
                Scene scene = labelWordToComplete.getScene();
                scene.setRoot(root);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            conn = DB.getConnection();
            st = conn.prepareStatement("SELECT * FROM words");
            rs = st.executeQuery();

            while (rs.next()) {
                Word newWord = new Word(rs.getString("FullWord"), rs.getString("WordWith_"), rs.getString("ImgName"));
                words.add(newWord);
            }

            Collections.shuffle(words);

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }

        if (!words.isEmpty()) {
            labelWordToComplete.setText(words.get(0).getWordWith_());
            Image newImage = new Image(getClass().getResourceAsStream("/imgs/img/" + words.get(0).getImgName()));
            MainImage.setImage(newImage);
        }

        String soundClickPath = getClass().getResource("/sounds/click/ClickSound01.mp3").toString();
        this.clickSound = new AudioClip(soundClickPath);
        this.clickSound.setVolume(0.2);

        String soundConfirmPath = getClass().getResource("/sounds/confirm/confirmationSound01.mp3").toString();
        this.confirmSound = new AudioClip(soundConfirmPath);
        this.confirmSound.setVolume(0.2);

        String soundErrorPath = getClass().getResource("/sounds/error/ErrorSound01.mp3").toString();
        this.errorSound = new AudioClip(soundErrorPath);
        this.errorSound.setVolume(0.3);

        String soundClickOnUiPath = getClass().getResource("/sounds/click/ClickOnUI01.mp3").toString();
        this.clickUiSound = new AudioClip(soundClickOnUiPath);
        this.clickUiSound.setVolume(0.3);
    }
}

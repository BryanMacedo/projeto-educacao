package guiClasses.controller.vowelActivity;

import db.DB;
import db.Exception.DbException;
import javafx.animation.FadeTransition;
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
import model.entities.vowelEntities.Word;

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

public class VowelViewController implements Initializable {
    private AudioClip clickSound;
    private AudioClip clickUiSound;
    private AudioClip confirmSound;
    private AudioClip errorSound;
    private List<Word> words = new ArrayList<>();
    private List<Button> bts = new ArrayList<>();

    @FXML
    private ImageView imgvMathImg;

    @FXML
    private ImageView imgvHomeImg;

    @FXML
    private ImageView imgvDiceImg;

    @FXML
    private ImageView MainImage;

    @FXML
    private Label labelWordToComplete;

    @FXML
    private Label explanatoryText;

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
    private void onImgvMathImgClick(MouseEvent event){
        clickUiSound.play();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/mathViews/MathView.fxml"));
            Parent root = loader.load();
            Scene scene = imgvMathImg.getScene();
            scene.setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void onImgvDiceImgClick(MouseEvent event){
        clickUiSound.play();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/memoryViews/MemoryView.fxml"));
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/mainViews/MainView.fxml"));
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
            for (Button bt : bts) {
                bt.setDisable(true);
            }
            labelWordToComplete.setStyle("-fx-text-fill: green;");
            PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
            pause.setOnFinished(event -> {
                confirmSound.play();
                words.remove(0);
                next();
                labelWordToComplete.setStyle("-fx-text-fill: white;");
                for (Button bt : bts) {
                    bt.setDisable(false);
                }
            });
            pause.play();

        } else if (!textLabelWordToComplete.contains("_") && !textLabelWordToComplete.equals(words.get(0).getFullWords())) {
            for (Button bt : bts) {
                bt.setDisable(true);
            }
            labelWordToComplete.setStyle("-fx-text-fill: #B22222;");
            PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
            pause.setOnFinished(event -> {
                errorSound.play();
                labelWordToComplete.setText(words.get(0).getWordWith_());
                labelWordToComplete.setStyle("-fx-text-fill: white;");
                for (Button bt : bts) {
                    bt.setDisable(false);
                }
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
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/vowelViews/VowelCongratulationView.fxml"));
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

        FadeTransition fadeOut = new FadeTransition(Duration.seconds(2), explanatoryText);
        fadeOut.setFromValue(1.0); // Opacidade inicial (totalmente visível)
        fadeOut.setToValue(0.0);   // Opacidade final (totalmente invisível)
        fadeOut.setDelay(Duration.seconds(4));

        fadeOut.play();

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
        this.clickUiSound.setVolume(0.1);

        bts.add(btLetterA);
        bts.add(btLetterE);
        bts.add(btLetterI);
        bts.add(btLetterO);
        bts.add(btLetterU);
    }
}

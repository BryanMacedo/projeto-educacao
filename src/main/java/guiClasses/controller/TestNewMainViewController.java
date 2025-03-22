package guiClasses.controller;

import db.DB;
import db.Exception.DbException;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import model.entities.Word;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class TestNewMainViewController implements Initializable {
    List<Word> words = new ArrayList<>();

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
    private void onBtLetterA_Action(){
        String buttonLetter = "A";
        String textLabelWordToComplete = labelWordToComplete.getText();

        String newTextLabel = textLabelWordToComplete.replaceFirst("_", buttonLetter);

        labelWordToComplete.setText(newTextLabel);

        VerifyWord();
    }

    @FXML
    private void onBtLetterE_Action(){
        String buttonLetter = "E";
        String textLabelWordToComplete = labelWordToComplete.getText();

        String newTextLabel = textLabelWordToComplete.replaceFirst("_", buttonLetter);

        labelWordToComplete.setText(newTextLabel);
        VerifyWord();
    }

    @FXML
    private void onBtLetterI_Action(){
        String buttonLetter = "I";
        String textLabelWordToComplete = labelWordToComplete.getText();

        String newTextLabel = textLabelWordToComplete.replaceFirst("_", buttonLetter);

        labelWordToComplete.setText(newTextLabel);
        VerifyWord();

    }

    @FXML
    private void onBtLetterO_Action(){
        String buttonLetter = "O";
        String textLabelWordToComplete = labelWordToComplete.getText();

        String newTextLabel = textLabelWordToComplete.replaceFirst("_", buttonLetter);

        labelWordToComplete.setText(newTextLabel);
        VerifyWord();
    }

    @FXML
    private void onBtLetterU_Action(){
        String buttonLetter = "U";
        String textLabelWordToComplete = labelWordToComplete.getText();

        String newTextLabel = textLabelWordToComplete.replaceFirst("_", buttonLetter);

        labelWordToComplete.setText(newTextLabel);
        VerifyWord();
    }

    private void VerifyWord(){
        String textLabelWordToComplete = labelWordToComplete.getText();
        if (!textLabelWordToComplete.contains("_") && textLabelWordToComplete.equals(words.get(0).getFullWords())){
            labelWordToComplete.setStyle("-fx-text-fill: green;");
            PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
            pause.setOnFinished(event -> {
                words.remove(0);
                next();
                labelWordToComplete.setStyle("-fx-text-fill: white;");
            });
            pause.play();

        } else if (!textLabelWordToComplete.contains("_") && !textLabelWordToComplete.equals(words.get(0).getFullWords())) {
            labelWordToComplete.setStyle("-fx-text-fill: red;");
            PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
            pause.setOnFinished(event -> {
                labelWordToComplete.setText(words.get(0).getWordWith_());
                labelWordToComplete.setStyle("-fx-text-fill: white;");
            });
            pause.play();
        }
    }

    private void next() {
        if (!words.isEmpty()) {
            labelWordToComplete.setText(words.get(0).getWordWith_());
            Image newImage = new Image(getClass().getResourceAsStream("/imgs/icons/" + words.get(0).getImgName()));
            MainImage.setImage(newImage);
        } else {
            MainImage.setImage(null);
            labelWordToComplete.setStyle("-fx-text-fill: green;");
            labelWordToComplete.setText("Parabéns! Você acertou todas!");
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

            while (rs.next()){
                Word newWord = new Word(rs.getString("FullWord"), rs.getString("WordWith_"), rs.getString("ImgName"));
                words.add(newWord);
            }

            Collections.shuffle(words);

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }

        if (!words.isEmpty()){
            labelWordToComplete.setText(words.get(0).getWordWith_());
            Image newImage = new Image(getClass().getResourceAsStream("/imgs/icons/" + words.get(0).getImgName()));
            MainImage.setImage(newImage);
        }
    }
}

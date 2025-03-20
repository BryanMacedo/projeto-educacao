package guiClasses.controller;

import db.DB;
import db.Exception.DbException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class PlayViewController implements Initializable {
    private List<Map.Entry<String, String>> randomWords;
    private int points;

    @FXML
    private Button btBackArrow;

    @FXML
    private Label labelFullWord;

    @FXML
    private Label labelShowPoints;

    @FXML
    private TextField tfAnswerWrite;

    @FXML
    private Button btAnswer;

    @FXML
    private void onBtAnswerAction() {
        if (!randomWords.isEmpty()) {
            Map.Entry<String, String> word = randomWords.get(0);
            labelFullWord.setText(word.getKey());

            String userAnswer = tfAnswerWrite.getText().trim();
            String rightAnswer = word.getValue();

            if (userAnswer.equalsIgnoreCase(rightAnswer)) {
                showAlertCongratulation("Resposta Correta", "Parabéns! Você acertou!");
                points += 5;
                labelShowPoints.setText(points + " PTS");
                tfAnswerWrite.clear();
            } else {
                showAlertCongratulation("Resposta Incorreta", "A resposta correta é: " + rightAnswer);

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml"));
                    Parent root = loader.load();

                    Scene scene = btBackArrow.getScene();
                    scene.setRoot(root);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        randomWords.remove(0);

        if (!randomWords.isEmpty()) {
            Map.Entry<String, String> nextWord = randomWords.get(0);
            labelFullWord.setText(nextWord.getKey());
        } else {
            showAlertCongratulation("PARABÉNS", "VOCÊ ACERTOU TODAS AS PALAVRAS!");
            labelFullWord.setText("Fim do jogo!");
        }


    }


    @FXML
    private void getRandomWords() {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        HashMap<String, String> wordsInOrder = new HashMap<>();

        try {
            conn = DB.getConnection();
            st = conn.prepareStatement("SELECT * FROM words");
            rs = st.executeQuery();

            while (rs.next()) {
                String fullWord = rs.getString("FullWord");
                String separateWord = rs.getString("SeparateWord");
                wordsInOrder.put(fullWord, separateWord);

            }

            randomWords = new ArrayList<>(wordsInOrder.entrySet());
            Collections.shuffle(randomWords);
            for (Map.Entry<String, String> entry : randomWords) {
                System.out.println("Chave: " + entry.getKey() + ", Valor: " + entry.getValue());
            }
            System.out.println("------------------------------------");

            if (!randomWords.isEmpty()) {
                Map.Entry<String, String> firstWord = randomWords.get(0);
                labelFullWord.setText(firstWord.getKey());
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @FXML
    private void onBtBackArrowAction() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/LoginUserView.fxml"));
            Parent root = loader.load();

            Scene scene = btBackArrow.getScene();
            scene.setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void showAlertCongratulation(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getRandomWords();
        Image backArrowIcon = new Image(getClass().getResourceAsStream("/imgs/icons/BackArrowIcon.png"));

        ImageView backArrowIconImageView = new ImageView(backArrowIcon);

        backArrowIconImageView.setFitWidth(30);
        backArrowIconImageView.setFitHeight(30);

        btBackArrow.setGraphic(backArrowIconImageView);

    }
}

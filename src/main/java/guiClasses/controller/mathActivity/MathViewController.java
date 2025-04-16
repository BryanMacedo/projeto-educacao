package guiClasses.controller.mathActivity;

import db.DB;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;
import model.entities.mathEntities.MathematicalExpression;
import model.entities.mathEntities.TypeMathOperation;

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

public class MathViewController implements Initializable {
//    ChooseMathViewController chooseView = new ChooseMathViewController();
//    List<TypeMathOperation> mathOperations = chooseView.getMathOperations();

    List<TypeMathOperation> mathOperationsToQuery = ChooseMathViewController.getMathOperations();

    private List<MathematicalExpression> expressionsList = new ArrayList<>();

    private AudioClip clickUiSound;
    private AudioClip clickSound;
    private AudioClip confirmSound;
    private AudioClip errorSound;
    private AudioClip clickCloseSound;

    @FXML
    private ImageView imgvHomeImg;

    @FXML
    private ImageView imgvVowelImg;

    @FXML
    private ImageView imgvDiceImg;

    @FXML
    private Label labelMain;

    @FXML
    private Label labelEarlyExpression;

    @FXML
    private Label explanatoryText;

    @FXML
    private Label labelOperation;
    @FXML
    private Label labelAnswerUser;

    @FXML
    private Button btR1;

    @FXML
    private Button btR2;

    @FXML
    private Button btR3;

    @FXML
    private Button btR4;

    @FXML
    private ImageView imgvCloseImg;

    @FXML
    private void onImgvCloseImgClick(MouseEvent event){
        clickCloseSound.play();
        Platform.exit();
    }

    @FXML
    private void onBtR1Action(){
        clickSound.play();
        labelAnswerUser.setText(btR1.getText());
        verifyExpression();
    }

    @FXML
    private void onBtR2Action(){
        clickSound.play();
        labelAnswerUser.setText(btR2.getText());
        verifyExpression();
    }

    @FXML
    private void onBtR3Action(){
        clickSound.play();
        labelAnswerUser.setText(btR3.getText());
        verifyExpression();
    }

    @FXML
    private void onBtR4Action(){
        clickSound.play();
        labelAnswerUser.setText(btR4.getText());
        verifyExpression();
    }

    private void nextExpression(){
        if (!expressionsList.isEmpty()){
            String strResult = String.valueOf(expressionsList.get(0).getResult());
            String strEarly = String.valueOf(expressionsList.get(0).getEarlyExpression());
            String strType = String.valueOf(expressionsList.get(0).getOperation().getStrOperation());
            labelMain.setText(strResult);
            labelEarlyExpression.setText(strEarly);
            labelOperation.setText(strType);
            labelAnswerUser.setText("?");
            btR1.setText(String.valueOf(expressionsList.get(0).getAnswers().get(0)));
            btR2.setText(String.valueOf(expressionsList.get(0).getAnswers().get(1)));
            btR3.setText(String.valueOf(expressionsList.get(0).getAnswers().get(2)));
            btR4.setText(String.valueOf(expressionsList.get(0).getAnswers().get(3)));
        }else {
            //chamar a congratulationView
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/mathViews/MathCongratulationView.fxml"));
                Parent root = loader.load();
                Scene scene = labelMain.getScene();
                scene.setRoot(root);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void verifyExpression(){
        // desabilitar os botões
        btR1.setDisable(true);
        btR2.setDisable(true);
        btR3.setDisable(true);
        btR4.setDisable(true);

        String strType = labelOperation.getText();

        int earlyExpression = Integer.parseInt(labelEarlyExpression.getText());
        int userAnswer = Integer.parseInt(labelAnswerUser.getText());
        int result = Integer.parseInt(labelMain.getText());

        switch (strType){
            case "+" ->{
                if (earlyExpression + userAnswer == result){
                    labelMain.setStyle(("-fx-text-fill: green;"));
                    labelEarlyExpression.setStyle(("-fx-text-fill: green;"));
                    labelOperation.setStyle(("-fx-text-fill: green;"));
                    labelAnswerUser.setStyle(("-fx-text-fill: green;"));

                    PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
                    pause.setOnFinished(event -> {
                        confirmSound.play();
                        labelMain.setStyle(("-fx-text-fill: white;"));
                        labelEarlyExpression.setStyle(("-fx-text-fill: white;"));
                        labelOperation.setStyle(("-fx-text-fill: white;"));
                        labelAnswerUser.setStyle(("-fx-text-fill: white;"));

                        expressionsList.remove(0);

                        // reabilitar
                        btR1.setDisable(false);
                        btR2.setDisable(false);
                        btR3.setDisable(false);
                        btR4.setDisable(false);

                        //metodo q troca a expressão
                        nextExpression();
                    });
                    pause.play();

                }else {
                    labelMain.setStyle(("-fx-text-fill: red;"));
                    labelEarlyExpression.setStyle(("-fx-text-fill: red;"));
                    labelOperation.setStyle(("-fx-text-fill: red;"));
                    labelAnswerUser.setStyle(("-fx-text-fill: red;"));

                    PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
                    pause.setOnFinished(event -> {
                        errorSound.play();
                        labelMain.setStyle(("-fx-text-fill: white;"));
                        labelEarlyExpression.setStyle(("-fx-text-fill: white;"));
                        labelOperation.setStyle(("-fx-text-fill: white;"));
                        labelAnswerUser.setStyle(("-fx-text-fill: white;"));
                        labelAnswerUser.setText("?");

                        // reabilitar
                        btR1.setDisable(false);
                        btR2.setDisable(false);
                        btR3.setDisable(false);
                        btR4.setDisable(false);
                    });
                    pause.play();
                }
            }
            case "-" ->{
                if (earlyExpression - userAnswer == result){
                    labelMain.setStyle(("-fx-text-fill: green;"));
                    labelEarlyExpression.setStyle(("-fx-text-fill: green;"));
                    labelOperation.setStyle(("-fx-text-fill: green;"));
                    labelAnswerUser.setStyle(("-fx-text-fill: green;"));

                    PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
                    pause.setOnFinished(event -> {
                        confirmSound.play();
                        labelMain.setStyle(("-fx-text-fill: white;"));
                        labelEarlyExpression.setStyle(("-fx-text-fill: white;"));
                        labelOperation.setStyle(("-fx-text-fill: white;"));
                        labelAnswerUser.setStyle(("-fx-text-fill: white;"));

                        expressionsList.remove(0);

                        // reabilitar
                        btR1.setDisable(false);
                        btR2.setDisable(false);
                        btR3.setDisable(false);
                        btR4.setDisable(false);

                        //metodo q troca a expressão
                        nextExpression();
                    });
                    pause.play();

                }else {
                    labelMain.setStyle(("-fx-text-fill: red;"));
                    labelEarlyExpression.setStyle(("-fx-text-fill: red;"));
                    labelOperation.setStyle(("-fx-text-fill: red;"));
                    labelAnswerUser.setStyle(("-fx-text-fill: red;"));

                    PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
                    pause.setOnFinished(event -> {
                        errorSound.play();
                        labelMain.setStyle(("-fx-text-fill: white;"));
                        labelEarlyExpression.setStyle(("-fx-text-fill: white;"));
                        labelOperation.setStyle(("-fx-text-fill: white;"));
                        labelAnswerUser.setStyle(("-fx-text-fill: white;"));
                        labelAnswerUser.setText("?");

                        // reabilitar
                        btR1.setDisable(false);
                        btR2.setDisable(false);
                        btR3.setDisable(false);
                        btR4.setDisable(false);
                    });
                    pause.play();
                }
            }

            case "x" ->{
                if (earlyExpression * userAnswer == result){
                    labelMain.setStyle(("-fx-text-fill: green;"));
                    labelEarlyExpression.setStyle(("-fx-text-fill: green;"));
                    labelOperation.setStyle(("-fx-text-fill: green;"));
                    labelAnswerUser.setStyle(("-fx-text-fill: green;"));

                    PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
                    pause.setOnFinished(event -> {
                        confirmSound.play();
                        labelMain.setStyle(("-fx-text-fill: white;"));
                        labelEarlyExpression.setStyle(("-fx-text-fill: white;"));
                        labelOperation.setStyle(("-fx-text-fill: white;"));
                        labelAnswerUser.setStyle(("-fx-text-fill: white;"));

                        expressionsList.remove(0);

                        // reabilitar
                        btR1.setDisable(false);
                        btR2.setDisable(false);
                        btR3.setDisable(false);
                        btR4.setDisable(false);

                        //metodo q troca a expressão
                        nextExpression();
                    });
                    pause.play();

                }else {
                    labelMain.setStyle(("-fx-text-fill: red;"));
                    labelEarlyExpression.setStyle(("-fx-text-fill: red;"));
                    labelOperation.setStyle(("-fx-text-fill: red;"));
                    labelAnswerUser.setStyle(("-fx-text-fill: red;"));

                    PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
                    pause.setOnFinished(event -> {
                        errorSound.play();
                        labelMain.setStyle(("-fx-text-fill: white;"));
                        labelEarlyExpression.setStyle(("-fx-text-fill: white;"));
                        labelOperation.setStyle(("-fx-text-fill: white;"));
                        labelAnswerUser.setStyle(("-fx-text-fill: white;"));
                        labelAnswerUser.setText("?");

                        // reabilitar
                        btR1.setDisable(false);
                        btR2.setDisable(false);
                        btR3.setDisable(false);
                        btR4.setDisable(false);
                    });
                    pause.play();
                }
            }
            case "%" ->{
                if ((float) earlyExpression / (float) userAnswer == result){
                    labelMain.setStyle(("-fx-text-fill: green;"));
                    labelEarlyExpression.setStyle(("-fx-text-fill: green;"));
                    labelOperation.setStyle(("-fx-text-fill: green;"));
                    labelAnswerUser.setStyle(("-fx-text-fill: green;"));

                    PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
                    pause.setOnFinished(event -> {
                        confirmSound.play();
                        labelMain.setStyle(("-fx-text-fill: white;"));
                        labelEarlyExpression.setStyle(("-fx-text-fill: white;"));
                        labelOperation.setStyle(("-fx-text-fill: white;"));
                        labelAnswerUser.setStyle(("-fx-text-fill: white;"));

                        expressionsList.remove(0);

                        // reabilitar
                        btR1.setDisable(false);
                        btR2.setDisable(false);
                        btR3.setDisable(false);
                        btR4.setDisable(false);

                        //metodo q troca a expressão
                        nextExpression();
                    });
                    pause.play();

                }else {
                    labelMain.setStyle(("-fx-text-fill: red;"));
                    labelEarlyExpression.setStyle(("-fx-text-fill: red;"));
                    labelOperation.setStyle(("-fx-text-fill: red;"));
                    labelAnswerUser.setStyle(("-fx-text-fill: red;"));

                    PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
                    pause.setOnFinished(event -> {
                        errorSound.play();
                        labelMain.setStyle(("-fx-text-fill: white;"));
                        labelEarlyExpression.setStyle(("-fx-text-fill: white;"));
                        labelOperation.setStyle(("-fx-text-fill: white;"));
                        labelAnswerUser.setStyle(("-fx-text-fill: white;"));
                        labelAnswerUser.setText("?");

                        // reabilitar
                        btR1.setDisable(false);
                        btR2.setDisable(false);
                        btR3.setDisable(false);
                        btR4.setDisable(false);
                    });
                    pause.play();
                }
            }

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(2), explanatoryText);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.setDelay(Duration.seconds(4));

        fadeOut.play();

        String soundClickOnUiPath = getClass().getResource("/sounds/click/ClickOnUI01.mp3").toString();
        this.clickUiSound = new AudioClip(soundClickOnUiPath);
        this.clickUiSound.setVolume(0.1);

        String soundClickPath = getClass().getResource("/sounds/click/ClickSound01.mp3").toString();
        this.clickSound = new AudioClip(soundClickPath);
        this.clickSound.setVolume(0.2);

        String soundConfirmPath = getClass().getResource("/sounds/confirm/confirmationSound01.mp3").toString();
        this.confirmSound = new AudioClip(soundConfirmPath);
        this.confirmSound.setVolume(0.2);

        String soundErrorPath = getClass().getResource("/sounds/error/ErrorSound01.mp3").toString();
        this.errorSound = new AudioClip(soundErrorPath);
        this.errorSound.setVolume(0.3);

        String soundClosePath = getClass().getResource("/sounds/close/closeSound.mp3").toString();
        this.clickCloseSound = new AudioClip(soundClosePath);
        this.clickCloseSound.setVolume(1.0);

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        for (TypeMathOperation typeMathOperation : mathOperationsToQuery) {
            try {
                conn = DB.getConnection();
                st = conn.prepareStatement("SELECT * FROM mathexpression WHERE TypeMathOperation = ?");
                st.setString(1, String.valueOf(typeMathOperation));
                rs = st.executeQuery();

                while (rs.next()){
                    List<Integer> answers = new ArrayList<>();

                    int result = rs.getInt("Result");
                    int earlyExpression = rs.getInt("EarlyExpression");
                    TypeMathOperation type = TypeMathOperation.valueOf(rs.getString("TypeMathOperation"));
                    answers.add(rs.getInt("R1"));
                    answers.add(rs.getInt("R2"));
                    answers.add(rs.getInt("R3"));
                    answers.add(rs.getInt("R4"));

                    Collections.shuffle(answers);
                    MathematicalExpression newExpression = new MathematicalExpression(type, result, earlyExpression, answers);
                    expressionsList.add(newExpression);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        Collections.shuffle(expressionsList);
        
        nextExpression();
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
    private void onImgvVowelImgClick(MouseEvent event){
        clickUiSound.play();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/vowelViews/VowelView.fxml"));
            Parent root = loader.load();
            Scene scene = imgvVowelImg.getScene();
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
}

package guiClasses.controller;

import db.DB;
import db.Exception.DbException;
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
import model.entities.MathematicalExpression;
import model.entities.TypeMathOperation;

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
    private List<MathematicalExpression> expressionsList = new ArrayList<>();


    private AudioClip clickUiSound;

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
    private void onBtR1Action(){
        labelAnswerUser.setText(btR1.getText());
    }

    @FXML
    private void onBtR2Action(){
        labelAnswerUser.setText(btR2.getText());
    }

    @FXML
    private void onBtR3Action(){
        labelAnswerUser.setText(btR3.getText());
    }

    @FXML
    private void onBtR4Action(){
        labelAnswerUser.setText(btR4.getText());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String soundClickOnUiPath = getClass().getResource("/sounds/click/ClickOnUI01.mp3").toString();
        this.clickUiSound = new AudioClip(soundClickOnUiPath);
        this.clickUiSound.setVolume(0.3);

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            conn = DB.getConnection();
            st = conn.prepareStatement("SELECT * FROM mathexpression");
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

        if (!expressionsList.isEmpty()){
            String strResult = String.valueOf(expressionsList.get(0).getResult());
            String strEarly = String.valueOf(expressionsList.get(0).getEarlyExpression());
            String strType = String.valueOf(expressionsList.get(0).getOperation().getStrOperation());
            labelMain.setText(strResult);
            labelEarlyExpression.setText(strEarly);
            labelOperation.setText(strType);
            btR1.setText(String.valueOf(expressionsList.get(0).getAnswers().get(0)));
            btR2.setText(String.valueOf(expressionsList.get(0).getAnswers().get(1)));
            btR3.setText(String.valueOf(expressionsList.get(0).getAnswers().get(2)));
            btR4.setText(String.valueOf(expressionsList.get(0).getAnswers().get(3)));
        }
    }

    @FXML
    private void onImgvDiceImgClick(MouseEvent event){
        clickUiSound.play();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MemoryView.fxml"));
            Parent root = loader.load();
            Scene scene = imgvDiceImg.getScene();
            scene.setRoot(root);
        } catch (IOException e) {
//            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void onImgvVowelImgClick(MouseEvent event){
        clickUiSound.play();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/CompleteTheVowel.fxml"));
            Parent root = loader.load();
            Scene scene = imgvVowelImg.getScene();
            scene.setRoot(root);
        } catch (IOException e) {
//            System.out.println(e.getMessage());
            e.printStackTrace();
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
//            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}

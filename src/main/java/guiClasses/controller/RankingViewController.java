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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RankingViewController implements Initializable {
    @FXML
    private Button btBackArrow;

    @FXML
    private Label labelScore01;

    @FXML
    private Label labelScore02;

    @FXML
    private Label labelScore03;

    @FXML
    private Label labelScore04;

    @FXML
    private Label labelScore05;

    @FXML
    private Label labelScore06;

    @FXML
    private Label labelScore07;

    @FXML
    private Label labelScore08;

    @FXML
    private Label labelScore09;

    @FXML
    private Label labelScore010;

    List<String> userData = new ArrayList<>();

    @FXML
    private void onBtBackArrowAction() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml"));
            Parent root = loader.load();

            Scene scene = btBackArrow.getScene();
            scene.setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void getUsersScoring(){
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        if (!userData.isEmpty()){
            userData.clear();
        }

        try {
            conn = DB.getConnection();
            st = conn.prepareStatement("SELECT FirstName, LastName, Scoring FROM scoring ORDER BY Scoring DESC LIMIT 10;");
            rs = st.executeQuery();

            while (rs.next()){
                String fullName = (rs.getString("FirstName")+ " " + rs.getString("LastName")).toUpperCase();
                int scoring = rs.getInt("scoring");
                userData.add(fullName + " - " + scoring + " PTS");
            }

            writeOnLabel();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }

    }

    @FXML
    private void writeOnLabel(){
        labelScore01.setText(userData.get(0));
        labelScore02.setText(userData.get(1));
        labelScore03.setText(userData.get(2));
        labelScore04.setText(userData.get(3));
        labelScore05.setText(userData.get(4));
        labelScore06.setText(userData.get(5));
        labelScore07.setText(userData.get(6));
        labelScore08.setText(userData.get(7));
        labelScore09.setText(userData.get(8));
        labelScore010.setText(userData.get(9));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getUsersScoring();
        Image backArrowIcon = new Image(getClass().getResourceAsStream("/imgs/icons/BackArrowIcon.png"));

        ImageView backArrowIconImageView = new ImageView(backArrowIcon);

        backArrowIconImageView.setFitWidth(30);
        backArrowIconImageView.setFitHeight(30);

        btBackArrow.setGraphic(backArrowIconImageView);
    }
}

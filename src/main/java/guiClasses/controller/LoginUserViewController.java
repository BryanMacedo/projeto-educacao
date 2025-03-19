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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginUserViewController implements Initializable {
    @FXML
    private Button btEnter;

    @FXML
    private Button btBackArrow;

    @FXML
    private TextField tfLogin;

    @FXML
    private TextField tfPassword;

    @FXML
    private void onBtBackArrowAction(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml"));
            Parent root = loader.load();

            Scene scene = btBackArrow.getScene();
            scene.setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void onBtEnterAction(){
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        if (tfLogin.getText().isEmpty() || tfPassword.getText().isEmpty()){
            showAlertError("Campos não preenchidos.", "Por favor preencha todos os campos da area de Login.");
        }

        String loginWritten = tfLogin.getText();
        String passwordWritten = tfPassword.getText();

        try{
            conn = DB.getConnection();
            st = conn.prepareStatement("SELECT * FROM users WHERE Login = ?");
            st.setString(1, loginWritten);
            rs = st.executeQuery();

            if (rs.next()){
                String passwordFromDB = rs.getString("Password");
                if (BCrypt.checkpw(passwordWritten, passwordFromDB)){
                    try{
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/PlayView.fxml"));
                        Parent root = loader.load();

                        Scene scene = btEnter.getScene();
                        scene.setRoot(root);
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }

                }else {
                    showAlertError("Senha incorreta.", "A senha do usuário esta incorreta, por favor tente novamente.");
                }
            }else {
                showAlertError("Usuário não encontrado.", "Usuário não encontrado, por favor tente novamente.");
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }

    }

    private void showAlertError(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image backArrowIcon = new Image(getClass().getResourceAsStream("/imgs/icons/BackArrowIcon.png"));

        ImageView backArrowIconImageView = new ImageView(backArrowIcon);

        backArrowIconImageView.setFitWidth(30);
        backArrowIconImageView.setFitHeight(30);

        btBackArrow.setGraphic(backArrowIconImageView);

    }
}

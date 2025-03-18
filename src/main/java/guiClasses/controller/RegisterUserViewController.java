package guiClasses.controller;

import db.DB;
import db.Exception.DbException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.entities.User;
import model.services.LoginAndPasswordMaker;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class RegisterUserViewController implements Initializable {
    @FXML
    private Button btBackArrow;

    @FXML
    private RadioButton rbStudent;

    @FXML
    private RadioButton rbTeacher;

    @FXML
    private ToggleGroup toggleGroupUsers;

    @FXML
    private ToggleGroup toggleGroupSex;

    @FXML
    private TextField tfFirstName;

    @FXML
    private TextField tfLastName;

    @FXML
    private DatePicker dpDateOfBirth;

    @FXML
    private RadioButton rbGirl;

    @FXML
    private RadioButton rbBoy;

    @FXML
    private Button btRegisterUser;

    @FXML
    private void onBtRegisterUserAction(){
        if (toggleGroupUsers.getSelectedToggle() == null || tfFirstName.getText().isEmpty() || tfLastName.getText().isEmpty() || dpDateOfBirth.getValue() == null || toggleGroupSex.getSelectedToggle() == null){
            showAlertError("Campos não preenchidos.", "Por favor preencha todos os campos do formulário.");
        }else {
            String userType = rbStudent.isSelected() ? "Aluno" : "Professor";
            String firstName = tfFirstName.getText();
            String lastName = tfLastName.getText();
            String dateOfBirth = dpDateOfBirth.getValue().toString();
            String sexUser = rbBoy.isSelected() ? "Menino" : "Menina";

            User newUser = new User(userType,firstName,lastName,dateOfBirth,sexUser);
            System.out.println(newUser);

            Connection conn = null;
            PreparedStatement st = null;
            ResultSet rs = null;

            try {
                conn = DB.getConnection();
                st = conn.prepareStatement("INSERT INTO users (FirstName, LastName, TypeUser, DateOfBirth, Gender) VALUES (?,?,?,?,?)");
                st.setString(1, newUser.getFirstName());
                st.setString(2, newUser.getLastName());
                st.setString(3, newUser.getTypeUser());
                st.setString(4, newUser.getDateOfBirth());
                st.setString(5, newUser.getSexUser());
                st.executeUpdate();

            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }finally {
                DB.closeStatement(st);
            }

            LoginAndPasswordMaker maker = new LoginAndPasswordMaker();
            maker.makerLoginAndPassword(firstName, lastName, dateOfBirth);

            String login = maker.getLogin();
            String password = maker.getPassword();

            //showAlertRegister("Novo usuário", "Novo usuário cadastrado!");
            showAlertRegister("Novo usuário cadastrado!", "Seu Login é: " + login + "\nE sua senha é: " + password);

            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml"));
                Parent root = loader.load();

                Scene scene = btRegisterUser.getScene();
                scene.setRoot(root);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

    };


    @FXML
    private void onBtBackArrowAction() {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml"));
            Parent root = loader.load();

            Scene scene = btBackArrow.getScene();
            scene.setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void showAlertError(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void showAlertRegister(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
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

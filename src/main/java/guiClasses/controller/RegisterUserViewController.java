package guiClasses.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.entities.User;

import java.io.IOException;
import java.net.URL;
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
    private TextField tfName;

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
        if (toggleGroupUsers.getSelectedToggle() == null || tfName.getText().isEmpty() || dpDateOfBirth.getValue() == null || toggleGroupSex.getSelectedToggle() == null){
            showAlertError("Campos não preenchidos.", "Por favor preencha todos os campos do formulário.");
        }else {
            String userType = rbStudent.isSelected() ? "Aluno" : "Professor";
            String userName = tfName.getText();
            String dateOfBirth = dpDateOfBirth.getValue().toString();
            String sexUser = rbBoy.isSelected() ? "Menino" : "Menina";

            User newUser = new User(userType, userName, dateOfBirth, sexUser);
            System.out.println(newUser);

            showAlertRegister("Novo usuário", "Novo usuário cadastrado!");

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

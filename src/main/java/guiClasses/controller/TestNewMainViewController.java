package guiClasses.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class TestNewMainViewController implements Initializable {
    @FXML
    private ImageView centerImageView;

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
        if (!textLabelWordToComplete.contains("_") && textLabelWordToComplete.equals("CASA")){
            labelWordToComplete.setStyle("-fx-text-fill: green;");
        } else if (!textLabelWordToComplete.contains("_") && !textLabelWordToComplete.equals("CASA")) {
            labelWordToComplete.setStyle("-fx-text-fill: red;");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

package guiClasses.controller;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;
import model.services.DiceInfos;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class MemoryViewController implements Initializable {
    private AudioClip clickSound;
    private AudioClip confirmSound;
    private AudioClip errorSound;
    private List<Integer> numbersLine1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
    private List<Integer> numbersLine2 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
    private List<DiceInfos> diceInfosList = new ArrayList<>();
    private List<String> namesImgsFrontL1 = new ArrayList<>();
    private List<String> namesImgsFrontL2 = new ArrayList<>();
    private int hits = 0;

    @FXML
    private ImageView imgvVowelImg;

    @FXML
    private ImageView imgvHomeImg;

    @FXML
    private ImageView imgvDice1ImgLine1;

    @FXML
    private ImageView imgvDice2ImgLine1;

    @FXML
    private ImageView imgvDice3ImgLine1;

    @FXML
    private ImageView imgvDice4ImgLine1;

    @FXML
    private ImageView imgvDice5ImgLine1;

    @FXML
    private ImageView imgvDice6ImgLine1;

    @FXML
    private ImageView imgvDice1ImgLine2;

    @FXML
    private ImageView imgvDice2ImgLine2;

    @FXML
    private ImageView imgvDice3ImgLine2;

    @FXML
    private ImageView imgvDice4ImgLine2;

    @FXML
    private ImageView imgvDice5ImgLine2;

    @FXML
    private ImageView imgvDice6ImgLine2;

    private void checkIds() {
        if (diceInfosList.size() > 1) {
            if (diceInfosList.get(0).getImgId() == diceInfosList.get(1).getImgId()) {
                PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
                pause.setOnFinished(event -> {
                    confirmSound.play();
                    System.out.println("Correto!");
                    diceInfosList.get(0).diableClick();
                    diceInfosList.get(1).diableClick();
                    diceInfosList.clear();
                    hits++;
                    if (hits == 6) {
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MemoryCongratulationView.fxml"));
                            Parent root = loader.load();
                            Scene scene = imgvDice1ImgLine1.getScene();
                            scene.setRoot(root);
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                });
                pause.play();
            } else {
                PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
                pause.setOnFinished(event -> {
                    errorSound.play();
                    System.out.println("Errado!");
                    diceInfosList.get(0).setBackImg();
                    diceInfosList.get(1).setBackImg();
                    diceInfosList.clear();
                });
                pause.play();
            }
        }
    }

    private void onClick(int imgId, ImageView imageView, int index, boolean line) {
        clickSound.play();
        DiceInfos di = new DiceInfos(imgId, imageView);
        diceInfosList.add(di);
        checkIds();
        System.out.println(imgId);
        if (line) {
            Image newImage = new Image(getClass().getResourceAsStream("/imgs/img/" + namesImgsFrontL1.get(index)));
            imageView.setImage(newImage);
        } else {
            Image newImage = new Image(getClass().getResourceAsStream("/imgs/img/" + namesImgsFrontL2.get(index)));
            imageView.setImage(newImage);
        }
    }

    // revela a imagem da Linha 1 - inicio

    @FXML
    private void onImgvDice1ImgLine1Action() {
        int imgId = Character.getNumericValue(namesImgsFrontL1.get(0).charAt(9));
        onClick(imgId, imgvDice1ImgLine1, 0, true);
    }

    @FXML
    private void onImgvDice2ImgLine1Action() {
        int imgId = Character.getNumericValue(namesImgsFrontL1.get(1).charAt(9));
        onClick(imgId, imgvDice2ImgLine1, 1, true);
    }

    @FXML
    private void onImgvDice3ImgLine1Action() {
        int imgId = Character.getNumericValue(namesImgsFrontL1.get(2).charAt(9));
        onClick(imgId, imgvDice3ImgLine1, 2, true);
    }

    @FXML
    private void onImgvDice4ImgLine1Action() {
        int imgId = Character.getNumericValue(namesImgsFrontL1.get(3).charAt(9));
        onClick(imgId, imgvDice4ImgLine1, 3, true);
    }

    @FXML
    private void onImgvDice5ImgLine1Action() {
        int imgId = Character.getNumericValue(namesImgsFrontL1.get(4).charAt(9));
        onClick(imgId, imgvDice5ImgLine1, 4,true);
    }

    @FXML
    private void onImgvDice6ImgLine1Action() {
        int imgId = Character.getNumericValue(namesImgsFrontL1.get(5).charAt(9));
        onClick(imgId, imgvDice6ImgLine1, 5,true);
    }

    // revela a imagem da Linha 1 - fim


    // revela a imagem da Linha 2 - inicio
    @FXML
    private void onImgvDice1ImgLine2Action() {
        int imgId = Character.getNumericValue(namesImgsFrontL2.get(0).charAt(9));
        onClick(imgId, imgvDice1ImgLine2, 0, false);
    }

    @FXML
    private void onImgvDice2ImgLine2Action() {
        int imgId = Character.getNumericValue(namesImgsFrontL2.get(1).charAt(9));
        onClick(imgId, imgvDice2ImgLine2, 1, false);
    }

    @FXML
    private void onImgvDice3ImgLine2Action() {
        int imgId = Character.getNumericValue(namesImgsFrontL2.get(2).charAt(9));
        onClick(imgId, imgvDice3ImgLine2, 2, false);
    }

    @FXML
    private void onImgvDice4ImgLine2Action() {
        int imgId = Character.getNumericValue(namesImgsFrontL2.get(3).charAt(9));
        onClick(imgId, imgvDice4ImgLine2, 3, false);
    }

    @FXML
    private void onImgvDice5ImgLine2Action() {
        int imgId = Character.getNumericValue(namesImgsFrontL2.get(4).charAt(9));
        onClick(imgId, imgvDice5ImgLine2, 4, false);
    }

    @FXML
    private void onImgvDice6ImgLine2Action() {
        int imgId = Character.getNumericValue(namesImgsFrontL2.get(5).charAt(9));
        onClick(imgId, imgvDice6ImgLine2, 5, false);
    }
    // revela a imagem da Linha 2 - fim

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String soundClickPath = getClass().getResource("/sounds/click/ClickSound01.mp3").toString();
        this.clickSound = new AudioClip(soundClickPath);
        this.clickSound.setVolume(0.2); // volume 0.0 a 1.0

        String soundConfirmPath = getClass().getResource("/sounds/confirm/confirmationSound01.mp3").toString();
        this.confirmSound = new AudioClip(soundConfirmPath);
        this.confirmSound.setVolume(0.2);

        String soundErrorPath = getClass().getResource("/sounds/error/ErrorSound01.mp3").toString();
        this.errorSound = new AudioClip(soundErrorPath);
        this.errorSound.setVolume(0.3);


        Collections.shuffle(numbersLine1);
        do {
            Collections.shuffle(numbersLine2);
        } while (numbersLine1.equals(numbersLine2));

        for (int i = 0; i < 6; i++) {
            namesImgsFrontL1.add("FrontTest" + numbersLine1.get(i) + ".png");
            namesImgsFrontL2.add("FrontTest" + numbersLine2.get(i) + ".png");
        }
    }

    // vai para outras views

    @FXML
    private void onImgvVowelImgClick(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/CompleteTheVowel.fxml"));
            Parent root = loader.load();
            Scene scene = imgvVowelImg.getScene();
            scene.setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void onImgvHomeImgClick(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml"));
            Parent root = loader.load();
            Scene scene = imgvHomeImg.getScene();
            scene.setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
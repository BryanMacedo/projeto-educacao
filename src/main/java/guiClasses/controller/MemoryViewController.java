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
    private final Image backImg = new Image(getClass().getResourceAsStream("/imgs/img/BackTest.png"));
    private AudioClip clickSound;
    private AudioClip clickUiSound;
    private AudioClip confirmSound;
    private AudioClip errorSound;
    private List<Integer> numbersLine1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
    private List<Integer> numbersLine2 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
    private List<DiceInfos> diceInfosList = new ArrayList<>();
    private List<String> namesImgsFrontL1 = new ArrayList<>();
    private List<String> namesImgsFrontL2 = new ArrayList<>();
    private List<Image> images = new ArrayList<>();
    private List<ImageView> imageViewListL1 = new ArrayList<>();
    private List<ImageView> imageViewListL2 = new ArrayList<>();
    private List<ImageView> imgvsClicked = new ArrayList<>();
    private List<ImageView> right = new ArrayList<>();
    private int hits = 0;

    @FXML
    private ImageView imgvVowelImg;

    @FXML
    private ImageView imgvHomeImg;

    @FXML
    private ImageView imgvMathImg;

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
            //desabilita todas as views para não causar bugs
            for (int i = 0; i < imageViewListL1.size(); i++) {
                imageViewListL1.get(i).setDisable(true);
            }

            for (int i = 0; i < imageViewListL2.size(); i++) {
                imageViewListL2.get(i).setDisable(true);
            }
            if (diceInfosList.get(0).getImgId() == diceInfosList.get(1).getImgId()) {
                PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
                pause.setOnFinished(event -> {
                    confirmSound.play();
                    System.out.println("Correto!");
                    diceInfosList.get(0).diableClick();
                    diceInfosList.get(1).diableClick();
                    right.add(diceInfosList.get(0).getImgvDice());
                    right.add(diceInfosList.get(1).getImgvDice());
                    diceInfosList.clear();
                    hits++;
                    imgvsClicked.clear();
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

                    if (right.isEmpty()) {
                        for (ImageView imageView : imageViewListL1) {
                            imageView.setDisable(false);
                        }

                        for (ImageView imageView : imageViewListL2) {
                            imageView.setDisable(false);
                        }

                    } else {
                        // habilita todas as q n estão corretas
                        for (ImageView imageView : imageViewListL1) {
                            if (!right.contains(imageView)) {
                                imageView.setDisable(false);
                            }
                        }

                        for (ImageView imageView : imageViewListL2) {
                            if (!right.contains(imageView)) {
                                imageView.setDisable(false);
                            }
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

                    if (imgvsClicked.size() > 1) {
                        imgvsClicked.clear();

                        // habilita todas as q n estão corretas
                        for (ImageView imageView : imageViewListL1) {
                            if (!right.contains(imageView)) {
                                imageView.setDisable(false);
                            }
                        }

                        for (ImageView imageView : imageViewListL2) {
                            if (!right.contains(imageView)) {
                                imageView.setDisable(false);
                            }
                        }
                    }


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
        imgvDice1ImgLine1.setDisable(true);
        imgvsClicked.add(imgvDice1ImgLine1);
    }

    @FXML
    private void onImgvDice2ImgLine1Action() {
        int imgId = Character.getNumericValue(namesImgsFrontL1.get(1).charAt(9));
        onClick(imgId, imgvDice2ImgLine1, 1, true);
        imgvDice2ImgLine1.setDisable(true);
        imgvsClicked.add(imgvDice2ImgLine1);
    }

    @FXML
    private void onImgvDice3ImgLine1Action() {
        int imgId = Character.getNumericValue(namesImgsFrontL1.get(2).charAt(9));
        onClick(imgId, imgvDice3ImgLine1, 2, true);
        imgvDice3ImgLine1.setDisable(true);
        imgvsClicked.add(imgvDice3ImgLine1);
    }

    @FXML
    private void onImgvDice4ImgLine1Action() {
        int imgId = Character.getNumericValue(namesImgsFrontL1.get(3).charAt(9));
        onClick(imgId, imgvDice4ImgLine1, 3, true);
        imgvDice4ImgLine1.setDisable(true);
        imgvsClicked.add(imgvDice4ImgLine1);
    }

    @FXML
    private void onImgvDice5ImgLine1Action() {
        int imgId = Character.getNumericValue(namesImgsFrontL1.get(4).charAt(9));
        onClick(imgId, imgvDice5ImgLine1, 4, true);
        imgvDice5ImgLine1.setDisable(true);
        imgvsClicked.add(imgvDice5ImgLine1);
    }

    @FXML
    private void onImgvDice6ImgLine1Action() {
        int imgId = Character.getNumericValue(namesImgsFrontL1.get(5).charAt(9));
        onClick(imgId, imgvDice6ImgLine1, 5, true);
        imgvDice6ImgLine1.setDisable(true);
        imgvsClicked.add(imgvDice6ImgLine1);
    }

    // revela a imagem da Linha 1 - fim


    // revela a imagem da Linha 2 - inicio
    @FXML
    private void onImgvDice1ImgLine2Action() {
        int imgId = Character.getNumericValue(namesImgsFrontL2.get(0).charAt(9));
        onClick(imgId, imgvDice1ImgLine2, 0, false);
        imgvDice1ImgLine2.setDisable(true);
        imgvsClicked.add(imgvDice1ImgLine2);
    }

    @FXML
    private void onImgvDice2ImgLine2Action() {
        int imgId = Character.getNumericValue(namesImgsFrontL2.get(1).charAt(9));
        onClick(imgId, imgvDice2ImgLine2, 1, false);
        imgvDice2ImgLine2.setDisable(true);
        imgvsClicked.add(imgvDice2ImgLine2);
    }

    @FXML
    private void onImgvDice3ImgLine2Action() {
        int imgId = Character.getNumericValue(namesImgsFrontL2.get(2).charAt(9));
        onClick(imgId, imgvDice3ImgLine2, 2, false);
        imgvDice3ImgLine2.setDisable(true);
        imgvsClicked.add(imgvDice3ImgLine2);
    }

    @FXML
    private void onImgvDice4ImgLine2Action() {
        int imgId = Character.getNumericValue(namesImgsFrontL2.get(3).charAt(9));
        onClick(imgId, imgvDice4ImgLine2, 3, false);
        imgvDice4ImgLine2.setDisable(true);
        imgvsClicked.add(imgvDice4ImgLine2);
    }

    @FXML
    private void onImgvDice5ImgLine2Action() {
        int imgId = Character.getNumericValue(namesImgsFrontL2.get(4).charAt(9));
        onClick(imgId, imgvDice5ImgLine2, 4, false);
        imgvDice5ImgLine2.setDisable(true);
        imgvsClicked.add(imgvDice5ImgLine2);
    }

    @FXML
    private void onImgvDice6ImgLine2Action() {
        int imgId = Character.getNumericValue(namesImgsFrontL2.get(5).charAt(9));
        onClick(imgId, imgvDice6ImgLine2, 5, false);
        imgvDice6ImgLine2.setDisable(true);
        imgvsClicked.add(imgvDice6ImgLine2);
    }
    // revela a imagem da Linha 2 - fim


    private void showFrontImgs() {

        //L1
        images.clear();
        for (int i = 0; i < namesImgsFrontL1.size(); i++) {
            Image newImage = new Image(getClass().getResourceAsStream("/imgs/img/" + namesImgsFrontL1.get(i)));
            images.add(newImage);
        }

        for (int i = 0; i < imageViewListL1.size(); i++) {
            imageViewListL1.get(i).setImage(images.get(i));
            imageViewListL1.get(i).setDisable(true);
        }

        //L2
        images.clear();
        for (int i = 0; i < namesImgsFrontL2.size(); i++) {
            Image newImage = new Image(getClass().getResourceAsStream("/imgs/img/" + namesImgsFrontL2.get(i)));
            images.add(newImage);
        }

        for (int i = 0; i < imageViewListL2.size(); i++) {
            imageViewListL2.get(i).setImage(images.get(i));
            imageViewListL2.get(i).setDisable(true);
        }

        PauseTransition pause = new PauseTransition(Duration.seconds(3.0));
        pause.setOnFinished(event -> {

            for (int i = 0; i < imageViewListL1.size(); i++) {
                imageViewListL1.get(i).setImage(backImg);
                imageViewListL1.get(i).setDisable(false);
            }

            for (int i = 0; i < imageViewListL2.size(); i++) {
                imageViewListL2.get(i).setImage(backImg);
                imageViewListL2.get(i).setDisable(false);
            }
        });
        pause.play();
    }

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

        String soundClickOnUiPath = getClass().getResource("/sounds/click/ClickOnUI01.mp3").toString();
        this.clickUiSound = new AudioClip(soundClickOnUiPath);
        this.clickUiSound.setVolume(0.3);


        Collections.shuffle(numbersLine1);
        do {
            Collections.shuffle(numbersLine2);
        } while (numbersLine1.equals(numbersLine2));

        for (int i = 0; i < 6; i++) {
            namesImgsFrontL1.add("FrontTest" + numbersLine1.get(i) + ".png");
            namesImgsFrontL2.add("FrontTest" + numbersLine2.get(i) + ".png");
        }

        //L1
        imageViewListL1.add(imgvDice1ImgLine1);
        imageViewListL1.add(imgvDice2ImgLine1);
        imageViewListL1.add(imgvDice3ImgLine1);
        imageViewListL1.add(imgvDice4ImgLine1);
        imageViewListL1.add(imgvDice5ImgLine1);
        imageViewListL1.add(imgvDice6ImgLine1);

        //L2
        imageViewListL2.add(imgvDice1ImgLine2);
        imageViewListL2.add(imgvDice2ImgLine2);
        imageViewListL2.add(imgvDice3ImgLine2);
        imageViewListL2.add(imgvDice4ImgLine2);
        imageViewListL2.add(imgvDice5ImgLine2);
        imageViewListL2.add(imgvDice6ImgLine2);

        showFrontImgs();
    }

    // vai para outras views

    @FXML
    private void onImgvMathImgClick(MouseEvent event){
        clickUiSound.play();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MathView.fxml"));
            Parent root = loader.load();
            Scene scene = imgvMathImg.getScene();
            scene.setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void onImgvVowelImgClick(MouseEvent event) {
        clickUiSound.play();
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
        clickUiSound.play();
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
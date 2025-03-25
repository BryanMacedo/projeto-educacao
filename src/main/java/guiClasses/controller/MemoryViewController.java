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
import javafx.util.Duration;
import model.services.DiceInfos;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class MemoryViewController implements Initializable {
    List<Integer> numbersLine1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
    List<Integer> numbersLine2 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
    List<DiceInfos> diceInfosList = new ArrayList<>();
    int hits = 0;

    // AO FINALIZAR A LÓGICA MERLHORAR ESTA MERDA
    String nameImgFront1L1 = null;
    String nameImgFront2L1 = null;
    String nameImgFront3L1 = null;
    String nameImgFront4L1 = null;
    String nameImgFront5L1 = null;
    String nameImgFront6L1 = null;

    String nameImgFront1L2 = null;
    String nameImgFront2L2 = null;
    String nameImgFront3L2 = null;
    String nameImgFront4L2 = null;
    String nameImgFront5L2 = null;
    String nameImgFront6L2 = null;


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

    private void checkIds(){
        if (diceInfosList.size() > 1){
            if (diceInfosList.get(0).getImgId() == diceInfosList.get(1).getImgId()){
                PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
                pause.setOnFinished(event -> {
                    System.out.println("Correto!");
                    diceInfosList.get(0).diableClick();
                    diceInfosList.get(1).diableClick();
                    diceInfosList.clear();
                    hits++;
                    if (hits == 6){
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
            }else {
                PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
                pause.setOnFinished(event -> {
                    System.out.println("Errado!");
                    diceInfosList.get(0).setBackImg();
                    diceInfosList.get(1).setBackImg();
                    diceInfosList.clear();
                });
                pause.play();
            }
        }
    }

    // revela a imagem da Linha 1 - inicio
    // AO FINALIZAR A LÓGICA MERLHORAR ESTA MERDA


    @FXML
    private void onImgvDice1ImgLine1Action() {
        int imgId = Character.getNumericValue(nameImgFront1L1.charAt(9));
        System.out.println(imgId);
        DiceInfos diceInfos = new DiceInfos(imgId, imgvDice1ImgLine1);
        diceInfosList.add(diceInfos);
        checkIds();
        Image newImage = new Image(getClass().getResourceAsStream("/imgs/img/" + nameImgFront1L1));
        imgvDice1ImgLine1.setImage(newImage);
    }

    @FXML
    private void onImgvDice2ImgLine1Action() {
        int imgId = Character.getNumericValue(nameImgFront2L1.charAt(9));
        System.out.println(imgId);
        DiceInfos diceInfos = new DiceInfos(imgId, imgvDice2ImgLine1);
        diceInfosList.add(diceInfos);
        checkIds();
        Image newImage = new Image(getClass().getResourceAsStream("/imgs/img/" + nameImgFront2L1));
        imgvDice2ImgLine1.setImage(newImage);
    }

    @FXML
    private void onImgvDice3ImgLine1Action() {
        int imgId = Character.getNumericValue(nameImgFront3L1.charAt(9));
        System.out.println(imgId);
        DiceInfos diceInfos = new DiceInfos(imgId, imgvDice3ImgLine1);
        diceInfosList.add(diceInfos);
        checkIds();
        Image newImage = new Image(getClass().getResourceAsStream("/imgs/img/" + nameImgFront3L1));
        imgvDice3ImgLine1.setImage(newImage);
    }

    @FXML
    private void onImgvDice4ImgLine1Action() {
        int imgId = Character.getNumericValue(nameImgFront4L1.charAt(9));
        System.out.println(imgId);
        DiceInfos diceInfos = new DiceInfos(imgId, imgvDice4ImgLine1);
        diceInfosList.add(diceInfos);
        checkIds();
        Image newImage = new Image(getClass().getResourceAsStream("/imgs/img/" + nameImgFront4L1));
        imgvDice4ImgLine1.setImage(newImage);
    }

    @FXML
    private void onImgvDice5ImgLine1Action() {
        int imgId = Character.getNumericValue(nameImgFront5L1.charAt(9));
        System.out.println(imgId);
        DiceInfos diceInfos = new DiceInfos(imgId, imgvDice5ImgLine1);
        diceInfosList.add(diceInfos);
        checkIds();
        Image newImage = new Image(getClass().getResourceAsStream("/imgs/img/" + nameImgFront5L1));
        imgvDice5ImgLine1.setImage(newImage);
    }

    @FXML
    private void onImgvDice6ImgLine1Action() {
        int imgId = Character.getNumericValue(nameImgFront6L1.charAt(9));
        System.out.println(imgId);
        DiceInfos diceInfos = new DiceInfos(imgId, imgvDice6ImgLine1);
        diceInfosList.add(diceInfos);
        checkIds();
        Image newImage = new Image(getClass().getResourceAsStream("/imgs/img/" + nameImgFront6L1));
        imgvDice6ImgLine1.setImage(newImage);
    }

    // revela a imagem da Linha 1 - fim



    // revela a imagem da Linha 2 - inicio
    // AO FINALIZAR A LÓGICA MERLHORAR ESTA MERDA

    @FXML
    private void onImgvDice1ImgLine2Action() {
        int imgId = Character.getNumericValue(nameImgFront1L2.charAt(9));
        System.out.println(imgId);
        DiceInfos diceInfos = new DiceInfos(imgId, imgvDice1ImgLine2);
        diceInfosList.add(diceInfos);
        checkIds();
        Image newImage = new Image(getClass().getResourceAsStream("/imgs/img/" + nameImgFront1L2));
        imgvDice1ImgLine2.setImage(newImage);
    }

    @FXML
    private void onImgvDice2ImgLine2Action() {
        int imgId = Character.getNumericValue(nameImgFront2L2.charAt(9));
        System.out.println(imgId);
        DiceInfos diceInfos = new DiceInfos(imgId, imgvDice2ImgLine2);
        diceInfosList.add(diceInfos);
        checkIds();
        Image newImage = new Image(getClass().getResourceAsStream("/imgs/img/" + nameImgFront2L2));
        imgvDice2ImgLine2.setImage(newImage);
    }

    @FXML
    private void onImgvDice3ImgLine2Action() {
        int imgId = Character.getNumericValue(nameImgFront3L2.charAt(9));
        System.out.println(imgId);
        DiceInfos diceInfos = new DiceInfos(imgId, imgvDice3ImgLine2);
        diceInfosList.add(diceInfos);
        checkIds();
        Image newImage = new Image(getClass().getResourceAsStream("/imgs/img/" + nameImgFront3L2));
        imgvDice3ImgLine2.setImage(newImage);
    }

    @FXML
    private void onImgvDice4ImgLine2Action() {
        int imgId = Character.getNumericValue(nameImgFront4L2.charAt(9));
        System.out.println(imgId);
        DiceInfos diceInfos = new DiceInfos(imgId, imgvDice4ImgLine2);
        diceInfosList.add(diceInfos);
        checkIds();
        Image newImage = new Image(getClass().getResourceAsStream("/imgs/img/" + nameImgFront4L2));
        imgvDice4ImgLine2.setImage(newImage);
    }

    @FXML
    private void onImgvDice5ImgLine2Action() {
        int imgId = Character.getNumericValue(nameImgFront5L2.charAt(9));
        System.out.println(imgId);
        DiceInfos diceInfos = new DiceInfos(imgId, imgvDice5ImgLine2);
        diceInfosList.add(diceInfos);
        checkIds();
        Image newImage = new Image(getClass().getResourceAsStream("/imgs/img/" + nameImgFront5L2));
        imgvDice5ImgLine2.setImage(newImage);
    }

    @FXML
    private void onImgvDice6ImgLine2Action() {
        int imgId = Character.getNumericValue(nameImgFront6L2.charAt(9));
        System.out.println(imgId);
        DiceInfos diceInfos = new DiceInfos(imgId, imgvDice6ImgLine2);
        diceInfosList.add(diceInfos);
        checkIds();
        Image newImage = new Image(getClass().getResourceAsStream("/imgs/img/" + nameImgFront6L2));
        imgvDice6ImgLine2.setImage(newImage);
    }

    // revela a imagem da Linha 2 - fim


    // vai para a atividade de vogais
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

    // volta para a mainView
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image backImg = new Image(getClass().getResourceAsStream("/imgs/img/BackTest.png"));
        // AO FINALIZAR A LÓGICA MERLHORAR ESTA MERDA
        Collections.shuffle(numbersLine1);
        do {
            Collections.shuffle(numbersLine2);
        } while (numbersLine1.equals(numbersLine2));

        for (Integer i : numbersLine1) {
            System.out.println(i);
        }

        System.out.println("----------");

        for (Integer i : numbersLine2) {
            System.out.println(i);
        }

        nameImgFront1L1 = "FrontTest" + numbersLine1.get(0) + ".png";
        nameImgFront2L1 = "FrontTest" + numbersLine1.get(1) + ".png";
        nameImgFront3L1 = "FrontTest" + numbersLine1.get(2) + ".png";
        nameImgFront4L1 = "FrontTest" + numbersLine1.get(3) + ".png";
        nameImgFront5L1 = "FrontTest" + numbersLine1.get(4) + ".png";
        nameImgFront6L1 = "FrontTest" + numbersLine1.get(5) + ".png";

        nameImgFront1L2 = "FrontTest" + numbersLine2.get(0) + ".png";
        nameImgFront2L2 = "FrontTest" + numbersLine2.get(1) + ".png";
        nameImgFront3L2 = "FrontTest" + numbersLine2.get(2) + ".png";
        nameImgFront4L2 = "FrontTest" + numbersLine2.get(3) + ".png";
        nameImgFront5L2 = "FrontTest" + numbersLine2.get(4) + ".png";
        nameImgFront6L2 = "FrontTest" + numbersLine2.get(5) + ".png";
    }
}
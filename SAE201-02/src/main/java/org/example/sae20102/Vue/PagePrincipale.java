package org.example.sae20102.Vue;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.awt.*;
import java.io.File;



public class PagePrincipale extends Application {
    Group root = new Group();
    Image myImage = new Image("gameMenuImage.gif");
    Image myImage2 = new Image("StartPasAppuye.png");
    Image myImage3 = new Image("START_APPUYE_00000.png");
    Image nomJeu = new Image("Mech-Miners.png");
    Image myImage4 = new Image("quitPasAppuye_00000.png");
    Image myImage5 = new Image("quitAppuye.png");

    ImageView background = new ImageView(myImage);
    ImageView start = new ImageView(myImage2);
    ImageView startAppuye = new ImageView(myImage3);
    ImageView nomJeuu = new ImageView(nomJeu);
    ImageView quitPasAppuye = new ImageView(myImage4);
    ImageView quitAppuye = new ImageView(myImage5);

    @Override
    public void start(Stage stage) {
        background.setFitWidth(960);
        background.setFitHeight(540);
        background.setPreserveRatio(true);


        start.setFitWidth(180);
        start.setFitHeight(75);
        start.setLayoutX(412);
        start.setLayoutY(270);
        start.setPreserveRatio(true);


        startAppuye.setFitWidth(180);
        startAppuye.setFitHeight(75);
        startAppuye.setLayoutX(412);
        startAppuye.setLayoutY(275);
        startAppuye.setPreserveRatio(true);

        quitPasAppuye.setFitWidth(150);
        quitPasAppuye.setFitHeight(45);
        quitPasAppuye.setLayoutX(433);
        quitPasAppuye.setLayoutY(350);
        quitPasAppuye.setPreserveRatio(true);

        quitAppuye.setFitWidth(150);
        quitAppuye.setFitHeight(45);
        quitAppuye.setLayoutX(433);
        quitAppuye.setLayoutY(350);
        quitAppuye.setPreserveRatio(true);

        nomJeuu.setLayoutX(180);
        nomJeuu.setLayoutY(150);

        start.setId("start");
        nomJeuu.setId("nomJeuu");
        quitPasAppuye.setId("quitter");

        EventManager ev = new EventManager(this);
        start.setOnMouseClicked(ev);
        quitPasAppuye.setOnMouseClicked(ev);



        root.getChildren().addAll(background, start, nomJeuu, quitPasAppuye);
        Scene scene = new Scene(root, 960, 490);
        stage.setTitle("Jeu");
        stage.setScene(scene);
        stage.show();
    }

    public void startAnimation(){
        root.getChildren().remove(start);
        root.getChildren().add(startAppuye);
    }



    public static void main(String[] args) {
        launch();
    }
}
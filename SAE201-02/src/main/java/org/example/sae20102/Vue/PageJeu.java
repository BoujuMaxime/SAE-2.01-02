package org.example.sae20102.Vue;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import org.example.sae20102.Model.*;
import org.example.sae20102.Controller;


public class PageJeu extends Application {

    private Scene scene;

    private Group root = new Group();

    private Image Image1 = new Image(getClass().getResource("/image/gameMenuImage.gif").toString());
    private Image Image2 = new Image(getClass().getResource("/image/StartPasAppuye.png").toString());
    private Image Image3 = new Image(getClass().getResource("/image/StartAppuye.png").toString());
    private Image Image4 = new Image(getClass().getResource("/image/QuitPasAppuye.png").toString());
    private Image Image5 = new Image(getClass().getResource("/image/QuitAppuye.png").toString());
    private Image Image6 = new Image(getClass().getResource("/image/Mech_Miners.png").toString());

    private ImageView background = new ImageView(Image1);
    private ImageView startPasAppuye = new ImageView(Image2);
    private ImageView startAppuye = new ImageView(Image3);
    private ImageView nomJeu = new ImageView(Image6);
    private ImageView quitPasAppuye = new ImageView(Image4);
    private ImageView quitAppuye = new ImageView(Image5);

    private Stage stage;
    private EventManager ev;

    private Controller controller = new Controller();
    private Grille grille;

    private Rectangle[] rectangles = new Rectangle[100];

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        controller = new Controller();
        this.grille = controller.getGrille();
        displayMenu();
    }

    public void displayMenu(){
        background.setFitWidth(960);
        background.setFitHeight(540);
        background.setPreserveRatio(true);

        startPasAppuye.setFitWidth(180);
        startPasAppuye.setFitHeight(75);
        startPasAppuye.setLayoutX(412);
        startPasAppuye.setLayoutY(270);
        startPasAppuye.setPreserveRatio(true);

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

        nomJeu.setLayoutX(180);
        nomJeu.setLayoutY(150);

        startPasAppuye.setId("start");
        nomJeu.setId("nomJeuu");
        quitPasAppuye.setId("quitter");

        ev = new EventManager(this);
        startPasAppuye.setOnMouseClicked(ev);
        quitPasAppuye.setOnMouseClicked(ev);

        root.getChildren().addAll(background, startPasAppuye, nomJeu, quitPasAppuye);
        this.scene = new Scene(root, 960, 490);
        stage.setTitle("Page d'Acceuil");
        stage.setScene(this.scene);
        stage.show();
    }

    public void displayGame() {
        this.stage.setWidth(500);
        this.stage.setHeight(535);
       for (int i = 0; i < 10; i++) {
           for (int j = 0; j < 10; j++) {
               Secteur secteur = this.grille.getSecteur(i, j);
               Rectangle rect = new Rectangle(50, 50);
               rect.setOnMouseClicked(ev);
               rectangles[i * 10 + j] = rect;
               switch (secteur.toString()) {
                   case "M":
                       rect.setFill(Color.RED);
                       break;
                   case "E":
                       rect.setFill(Color.LIGHTGREY);
                       break;
                   case "R":
                       rect.setFill(Color.GREY);
                       break;
                   case "X":
                       rect.setFill(Color.BLUE);
                       break;
                   default:
                       rect.setFill(Color.GREEN);
                       break;
               }
               rect.setX(i * 50);
               rect.setY(j * 50);
               root.getChildren().add(rect);
           }
       }
    }

    public void pressStart() {
        root.getChildren().remove(startPasAppuye);
        root.getChildren().add(startAppuye);
        root.getChildren().clear();
        displayGame();
    }

    public void pressQuit(){
        root.getChildren().remove(quitPasAppuye);
        root.getChildren().add(quitAppuye);
    }

    public void pressRobot(Rectangle r){
        // On ouvre la fenetre d'information du robot
        int x = (int) r.getX() / 50;
        int y = (int) r.getY() / 50;
        Secteur secteur = this.grille.getSecteur(x, y);
        Robot[] robots = this.controller.getRobots();
        for (Robot robot : robots) {
            if (robot.getSecteur().equals(secteur)) {
                PageRobot pageRobot = new PageRobot(robot, r);
                pageRobot.show();
            }
        }
        System.out.println("Robot");
    }

    public void pressMine(){

    }

    public void pressEntrepot() {

    }
}
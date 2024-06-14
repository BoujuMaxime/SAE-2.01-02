package org.example.sae20102.Vue;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import javafx.util.Duration;
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

    private Image Image7 = new Image(getClass().getResource("/image/grass01.png").toString());
    private Image Image8 = new Image(getClass().getResource("/image/water01.png").toString());
    private Image Image9 = new Image(getClass().getResource("/image/hut.png").toString());
    private Image Image10 = new Image(getClass().getResource("/image/robot01.png").toString());
    private Image Image11 = new Image(getClass().getResource("/image/mine01.png").toString());
    private Image Image12 = new Image(getClass().getResource("/image/robot02.png").toString());
    private Image Image13 = new Image(getClass().getResource("/image/robot03.png").toString());
    private Image Image14 = new Image(getClass().getResource("/image/brouillard.png").toString());
    private Image Image15 = new Image(getClass().getResource("/image/Dijkstra.png").toString());

    ImagePattern ImagePattern1 = new ImagePattern(Image7);
    ImagePattern ImagePattern2 = new ImagePattern(Image8);
    ImagePattern ImagePattern3 = new ImagePattern(Image9);
    ImagePattern ImagePattern4 = new ImagePattern(Image10);
    ImagePattern ImagePattern5 = new ImagePattern(Image11);
    ImagePattern ImagePattern6 = new ImagePattern(Image12);
    ImagePattern ImagePattern7 = new ImagePattern(Image13);
    ImagePattern ImagePattern8 = new ImagePattern(Image14);

    private ImageView background = new ImageView(Image1);
    private ImageView startPasAppuye = new ImageView(Image2);
    private ImageView startAppuye = new ImageView(Image3);
    private ImageView nomJeu = new ImageView(Image6);
    private ImageView quitPasAppuye = new ImageView(Image4);
    private ImageView quitAppuye = new ImageView(Image5);
    private ImageView dijkstra = new ImageView(Image15);

    private boolean start = false;

    private Stage stage;
    private EventManager ev;

    private Controller controller;
    private Grille grille;

    private Rectangle[] rectangles = new Rectangle[100];

    @Override
    public void start(Stage stage) {
        if (start) {
            this.stage = stage;
            controller = new Controller();
            this.grille = controller.getGrille();
            displayGame();
        }
        else {
            this.stage = stage;
            displayMenu();
        }
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

        quitPasAppuye.setFitWidth(150);
        quitPasAppuye.setFitHeight(45);
        quitPasAppuye.setLayoutX(433);
        quitPasAppuye.setLayoutY(410);
        quitPasAppuye.setPreserveRatio(true);

        dijkstra.setFitWidth(180);
        dijkstra.setFitHeight(75);
        dijkstra.setLayoutX(412);
        dijkstra.setLayoutY(340);
        dijkstra.setPreserveRatio(true);

        nomJeu.setLayoutX(180);
        nomJeu.setLayoutY(150);

        startPasAppuye.setId("start");
        nomJeu.setId("nomJeu");
        quitPasAppuye.setId("quitter");
        dijkstra.setId("dijkstra");

        ev = new EventManager(this);
        startPasAppuye.setOnMouseClicked(ev);
        quitPasAppuye.setOnMouseClicked(ev);
        dijkstra.setOnMouseClicked(ev);

        root.getChildren().addAll(background, startPasAppuye, nomJeu, dijkstra,  quitPasAppuye);
        this.scene = new Scene(root, 960, 490);
        stage.setTitle("Page d'Accueil");
        stage.setScene(this.scene);
        stage.show();
    }

    public void displayGame() {
        this.stage.setWidth(510);
        this.stage.setHeight(535);
        this.stage.setTitle("Jeu");
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Secteur secteur = this.grille.getSecteur(i, j);
                Rectangle rect = new Rectangle(50, 50);
                rect.setOnMouseClicked(ev);
                rectangles[i * 10 + j] = rect;
                switch (secteur.toString()) {
                    case " M":
                        rect.setFill(ImagePattern5);
                        break;
                    case " E":
                        rect.setFill(ImagePattern3);
                        break;
                    case "R ":
                        rect.setFill(ImagePattern4);
                        break;
                    case "RE":
                        rect.setFill(ImagePattern6);
                        break;
                    case "RM":
                        rect.setFill(ImagePattern7);
                        break;
                    case "XX":
                        rect.setFill(ImagePattern2);
                        break;
                    default:
                        rect.setFill(ImagePattern1);
                        break;
                }
                if (!secteur.estConnu()) {
                    rect.setFill(ImagePattern8);
                }
                rect.setX(j * 50);
                rect.setY(i * 50);
                root.getChildren().add(rect);
            }
        }
    }


    public void play() {
        // Declare the timeline variable
        final Timeline[] timelineHolder = new Timeline[1];

        // Create the timeline
        timelineHolder[0] = new Timeline(new KeyFrame(Duration.seconds(0.15), event -> {
            // Update the game state
            this.grille = controller.play();

            // Display the updated game state
            displayGame();

            // Check if all mines are empty
            Mine[] mines = this.controller.getMines();
            boolean allMinesEmpty = true;
            for (Mine mine : mines) {
                if (mine.getCapacite() > 0) {
                    allMinesEmpty = false;
                    break;
                }
            }

            // Stop the timeline if all mines are empty
            if (allMinesEmpty) {
                timelineHolder[0].stop();
            }
        }));

        // Set the timeline to repeat 10000 times
        timelineHolder[0].setCycleCount(10000);

        // Start the timeline
        timelineHolder[0].play();
    }
    public void pressStart() {
        root.getChildren().remove(startPasAppuye);
        root.getChildren().add(startAppuye);
        root.getChildren().clear();
        this.start = true;
        start(stage);
    }

    public void pressQuit(){
        root.getChildren().remove(quitPasAppuye);
        root.getChildren().add(quitAppuye);
    }

    public void pressRobot(Rectangle r){
        int y = (int) r.getX() / 50;
        int x = (int) r.getY() / 50;
        Secteur secteur = this.grille.getSecteur(x, y);
        Robot[] robots = this.controller.getRobots();
        for (Robot robot : robots) {
            if (robot.getSecteur().equals(secteur)) {
                PageRobot pageRobot = new PageRobot(robot, r, this.controller, this.rectangles, this);
                pageRobot.show();
            }
        }
    }

    public void pressMine(Rectangle r){
        int y = (int) r.getX() / 50;
        int x = (int) r.getY() / 50;
        Secteur secteur = this.grille.getSecteur(x, y);
        Mine[] mines = this.controller.getMines();
        for (Mine mine : mines) {
            if (mine.getSecteur().equals(secteur)) {
                PageMine pageMine = new PageMine(mine, r);
                pageMine.show();
            }
        }

    }

    public void pressEntrepot(Rectangle r){
        int y = (int) r.getX() / 50;
        int x = (int) r.getY() / 50;
        Secteur secteur = this.grille.getSecteur(x, y);
        Entrepot[] entrepots = this.controller.getEntrepots();
        for (Entrepot entrepot : entrepots) {
            if (entrepot.getSecteur().equals(secteur)) {
                PageEntrepot pageEntrepot = new PageEntrepot(entrepot, r);
                pageEntrepot.show();
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
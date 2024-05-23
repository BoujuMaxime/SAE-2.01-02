package org.example.sae20102.Vue;

import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Label;

import org.example.sae20102.Model.Robot;


public class PageRobot extends Stage {
    private Scene scene;
    private Group root ;
    private Robot robot;
    private Rectangle rectangle;
    private Label type;
    private Label quantite;
    private Label capacite;
    private Label recolte;



    public PageRobot(Robot robot, Rectangle rectangle) {
        this.root = new Group();
        this.robot = robot;
        this.rectangle = rectangle;
        this.type = new Label("Type: " + this.robot.getNature());
        this.quantite = new Label("Quantite: " + this.robot.getQuantite());
        this.capacite = new Label("Capacite: " + this.robot.getCapacite());
        this.recolte = new Label("Recolte: " + this.robot.getRecolte());

        this.type.setLayoutX(10);
        this.type.setLayoutY(10);
        this.quantite.setLayoutX(10);
        this.quantite.setLayoutY(30);
        this.capacite.setLayoutX(10);
        this.capacite.setLayoutY(50);
        this.recolte.setLayoutX(10);
        this.recolte.setLayoutY(70);

        this.root.getChildren().addAll(this.type, this.quantite, this.capacite, this.recolte);
        this.scene = new Scene(root, 200, 200);
        this.setTitle(STR."Robot \{this.robot.getNumR()}");
        this.setScene(scene);
    }
}

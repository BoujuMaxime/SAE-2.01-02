package org.example.sae20102.Vue;

import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Label;

import org.example.sae20102.Model.Entrepot;

public class PageEntrepot extends Stage {
    private Scene scene;
    private Group root;
    private Entrepot entrepot;
    private Rectangle rectangle;
    private Label type;
    private Label quantite;

    public PageEntrepot(Entrepot entrepot, Rectangle rectangle) {
        this.root = new Group();
        this.entrepot = entrepot;
        this.rectangle = rectangle;
        this.type = new Label("Type: " + this.entrepot.getNature());
        this.quantite = new Label("Quantite: " + this.entrepot.getQuantite());

        this.type.setLayoutX(10);
        this.type.setLayoutY(10);
        this.quantite.setLayoutX(10);
        this.quantite.setLayoutY(30);

        this.root.getChildren().addAll(this.type, this.quantite);
        this.scene = new Scene(root, 200, 200);
        this.setTitle("Entrepot " + this.entrepot.getNumE());
        this.setScene(scene);
    }
}

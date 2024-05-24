package org.example.sae20102.Vue;

import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Label;
import org.example.sae20102.Model.Mine;


public class PageMine extends Stage {
    private Scene scene;
    private Group root;
    private Mine mine;
    private Rectangle rectangle;
    private Label type;
    private Label capacite;

    public PageMine(Mine mine, Rectangle rectangle) {
        this.root = new Group();
        this.mine = mine;
        this.rectangle = rectangle;
        this.type = new Label("Type: " + this.mine.getNature());
        this.capacite = new Label("Capacite: " + this.mine.getCapacite());

        this.type.setLayoutX(10);
        this.type.setLayoutY(10);
        this.capacite.setLayoutX(10);
        this.capacite.setLayoutY(30);

        this.root.getChildren().addAll(this.type, this.capacite);
        this.scene = new Scene(root, 200, 200);
        this.setTitle("Mine " + this.mine.getNumM());
        this.setScene(scene);
    }
}

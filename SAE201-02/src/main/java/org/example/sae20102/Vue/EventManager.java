package org.example.sae20102.Vue;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import org.example.sae20102.Model.Robot;

import java.util.Objects;

public class EventManager implements EventHandler {
    PageJeu p;

    public EventManager(PageJeu p) {
        this.p = p;
    }
    @Override
    public void handle(Event event) {
         if (event.getSource() instanceof ImageView) {
             if (event.toString().contains("start")) {
                 p.pressStart();
             } else if (event.toString().contains("quitter")) {
                 p.pressQuit();
                 System.exit(0);
             }
         }
         if (event.getSource() instanceof Rectangle) {
             System.out.println("Rectangle");
             if (((Rectangle) event.getSource()).getFill() == p.ImagePattern4) {
                 p.pressRobot((Rectangle) event.getSource());
             } else if (((Rectangle) event.getSource()).getFill() == p.ImagePattern3) {
                 p.pressEntrepot((Rectangle) event.getSource());
             } else if (((Rectangle) event.getSource()).getFill() == p.ImagePattern5) {
                 p.pressMine((Rectangle) event.getSource());
             }
         }
    }
}

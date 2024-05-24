package org.example.sae20102.Vue;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import org.example.sae20102.Model.Robot;

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
             if (((Rectangle) event.getSource()).getFill() == Color.GREY) {
                 p.pressRobot((Rectangle) event.getSource());
             } else if (((Rectangle) event.getSource()).getFill() == Color.LIGHTGREY) {
                 p.pressEntrepot((Rectangle) event.getSource());
             } else if (((Rectangle) event.getSource()).getFill() == Color.RED) {
                 p.pressMine((Rectangle) event.getSource());
             }
         }
    }
}

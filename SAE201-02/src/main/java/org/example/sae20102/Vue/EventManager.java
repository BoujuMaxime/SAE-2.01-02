package org.example.sae20102.Vue;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;

import java.awt.*;

import static javafx.application.Application.launch;

public class EventManager implements EventHandler {
    PagePrincipale p;
    public EventManager(PagePrincipale p) {
        this.p = p;
    }
    @Override
    public void handle(Event event) {
         if (event.getSource() instanceof ImageView) {
             if (event.toString().contains("start")){
                 p.startAnimation();
             } else if (event.toString().contains("quitter")) {
                 System.exit(0);
             }
         }
    }
}

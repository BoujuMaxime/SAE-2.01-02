package org.example.sae20102.Vue;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class EventManager implements EventHandler<Event> {
    PagePrincipale p;

    public EventManager(PagePrincipale p) {
        this.p = p;
    }

    @Override
    public void handle(Event event) {
        if (event.getSource() instanceof ImageView) {
            if (event instanceof MouseEvent) {
                MouseEvent mouseEvent = (MouseEvent) event;
                ImageView imageView = (ImageView) event.getSource();

                if (imageView.getId() != null && (imageView.getId().equals("start") || imageView.getId().equals("quitter"))) {
                    if (mouseEvent.getEventType() == MouseEvent.MOUSE_ENTERED) {
                        p.scene.setCursor(Cursor.HAND);
                    } else if (mouseEvent.getEventType() == MouseEvent.MOUSE_EXITED) {
                        p.scene.setCursor(Cursor.DEFAULT);
                    } else if (mouseEvent.getEventType() == MouseEvent.MOUSE_CLICKED) {
                        if (imageView.getId().equals("quitter")) {
                            System.exit(0);
                        } else if (event.toString().contains("start")) {
                            p.startAnimation();
                        }
                    }
                } else if (!imageView.getId().equals("start") || !imageView.getId().equals("quitter")){
                        p.scene.setCursor(Cursor.DEFAULT);
                    }
                }
            }
    }
}

package org.example.sae20102.Model;

import java.util.Map;

class Node {
    int distance;
    Node previous;
    Secteur secteur;

    Node(Secteur secteur) {
        this.distance = Integer.MAX_VALUE;
        this.secteur = secteur;
    }

    public int getX() {
        return secteur.getLigne();
    }

    public int getY() {
        return secteur.getColonne();
    }
}
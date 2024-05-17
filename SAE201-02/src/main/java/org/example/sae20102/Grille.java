package org.example.sae20102;

import java.util.ArrayList;

public class Grille {
    private int taille;
    private Secteur[][] secteurs;
    private ArrayList<Robot> robots;
    private ArrayList<Mine> mines;
    private ArrayList<Entrepot> entrepots;

    public Grille() {
        this.taille = 10;
        this.secteurs = new Secteur[taille][taille];
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                this.secteurs[i][j] = new Secteur();
            }
        }
    }

    public void addEau(int x, int y) {
        this.secteurs[x][y].addEau();
    }

    public void addRobot(Robot robot, int x, int y) {
        this.secteurs[x][y].addRobot(robot);
    }

    public void moveRobot(int x1, int y1, int x2, int y2, Robot robot) {
        this.secteurs[x2][y2].addRobot(robot);
        this.secteurs[x1][y1].removeRobot();
    }

    public void addMine(Mine mine, int x, int y) {
        this.secteurs[x][y].addMine(mine);
        this.mines.add(mine);
    }

    public void addEntrepot(Entrepot entrepot, int x, int y) {
        this.secteurs[x][y].addEntrepot(entrepot);
        this.entrepots.add(entrepot);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // Ajouter la ligne de bordure supérieure
        sb.append("+");
        for (int i = 0; i < taille; i++) {
            sb.append("---+");
        }
        sb.append("\n");

        // Ajouter les lignes de la grille avec des bordures et séparations
        for (int i = 0; i < taille; i++) {
            sb.append("|");  // Bordure gauche
            for (int j = 0; j < taille; j++) {
                sb.append(" " + secteurs[i][j].toString() + " |");
            }
            sb.append("\n");

            // Ajouter une ligne de séparation
            sb.append("+");
            for (int k = 0; k < taille; k++) {
                sb.append("---+");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

}
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
        this.robots = new ArrayList<>();
        this.mines = new ArrayList<>();
        this.entrepots = new ArrayList<>();
    }

    public void addEau(int x, int y) {
        this.secteurs[x][y].addEau();
    }

    public void addRobot(Robot robot, int x, int y) {
        this.secteurs[x][y].addRobot(robot);
        this.robots.add(robot);
        this.robots.get(this.robots.size() - 1).setCoord(x, y);
    }

    public void moveRobot(int x1, int y1, int x2, int y2, Robot robot) {
        this.secteurs[x2][y2].addRobot(robot);
        this.secteurs[x1][y1].removeRobot();
        this.robots.get(this.robots.indexOf(robot)).setCoord(x2, y2);
    }

    public void addMine(Mine mine, int x, int y) {
        this.secteurs[x][y].addMine(mine);
        this.mines.add(mine);
    }

    public void addEntrepot(Entrepot entrepot, int x, int y) {
        this.secteurs[x][y].addEntrepot(entrepot);
        this.entrepots.add(entrepot);
    }

    public Robot getRobot(String numRobot) {
        for (Robot robot : this.robots) {
            if (robot.getNumR().equals(numRobot)) {
                return robot;
            }
        }
        return null;
    }

    public Secteur getSecteur(int x, int y) {
        return this.secteurs[x][y];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();


        sb.append("+");
        for (int i = 0; i < taille; i++) {
            sb.append("---+");
        }
        sb.append("\n");

        for (int i = 0; i < taille; i++) {
            sb.append("|");
            for (int j = 0; j < taille; j++) {
                sb.append(" " + secteurs[i][j].toString() + " |");
            }
            sb.append("\n");

            sb.append("+");
            for (int k = 0; k < taille; k++) {
                sb.append("---+");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

}
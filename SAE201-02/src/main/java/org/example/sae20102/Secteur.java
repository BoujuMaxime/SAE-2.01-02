package org.example.sae20102;

public class Secteur {

    private int ligne;
    private int colonne;
    private String[][] secteur;

    public Secteur(){
        this.ligne = 2;
        this.colonne = 2;
        this.secteur = new String[this.ligne][this.colonne];
        this.createSecteur();
    }

    public String[][] createSecteur(){
        for (int i = 0; i < this.ligne; i++) {
            for (int j = 0; j < this.colonne; j++) {
                secteur[i][j] = " ";
            }
        }
        return secteur;
    }

    public void addEau(){
        secteur[0][0] = "X";
        secteur[0][1] = "X";
        secteur[1][0] = "X";
        secteur[1][1] = "X";
    }

    public void addMine(Mine mine) {
        secteur[0][0] = "M";
        secteur[0][1] = String.valueOf(mine.getNumM());
    }

    public void addRobot(Robot robot) {
        secteur[1][0] = "R";
        secteur[1][1] = String.valueOf(robot.getNumR());
    }

    public void removeRobot() {
        secteur[1][0] = " ";
        secteur[1][1] = " ";
    }

    public void addEntrepot(Entrepot entrepot) {
        secteur[0][0] = "E";
        secteur[0][1] = String.valueOf(entrepot.getNumE());
    }

    @Override
    public String toString(){
        if (secteur[0][0].equals("X")) {
            return "X";
        } else if (secteur[1][0].equals("R")) {
            return "R";
        } else if (secteur[0][0].equals("M")) {
            return "M";
        } else if (secteur[0][0].equals("E")) {
            return "E";
        } else {
            return " ";
        }
    }
}
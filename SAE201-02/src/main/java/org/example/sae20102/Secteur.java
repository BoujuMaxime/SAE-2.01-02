package org.example.sae20102;

public class Secteur {

    private int ligne;
    private int colonne;
    private String[][] cellules;

    public Secteur(){
        this.ligne = 2;
        this.colonne = 2;
        this.cellules = new String[this.ligne][this.colonne];
        this.createSecteur();
    }

    public String[][] createSecteur(){
        for (int i = 0; i < this.ligne; i++) {
            for (int j = 0; j < this.colonne; j++) {
                cellules[i][j] = " ";
            }
        }
        return cellules;
    }

    public void addEau(){
        cellules[0][0] = "X";
        cellules[0][1] = "X";
        cellules[1][0] = "X";
        cellules[1][1] = "X";
    }

    public void addMine(Mine mine) {
        cellules[0][0] = "M";
        cellules[0][1] = String.valueOf(mine.getNumM());
    }

    public void addRobot(Robot robot) {
        cellules[1][0] = "R";
        cellules[1][1] = String.valueOf(robot.getNumR());
    }

    public void removeRobot() {
        cellules[1][0] = " ";
        cellules[1][1] = " ";
    }

    public void addEntrepot(Entrepot entrepot) {
        cellules[0][0] = "E";
        cellules[0][1] = String.valueOf(entrepot.getNumE());
    }

    public String[][] getCellules(){
        return cellules;
    }

    @Override
    public String toString(){
        if (cellules[0][0].equals("X")) {
            return "X";
        } else if (cellules[1][0].equals("R")) {
            return "R";
        } else if (cellules[0][0].equals("M")) {
            return "M";
        } else if (cellules[0][0].equals("E")) {
            return "E";
        } else {
            return " ";
        }
    }
}
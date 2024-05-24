package org.example.sae20102.Model;

public class Secteur {
    private int ligne;
    private int colonne;
    private String[][] cellules;

    public Secteur(int ligne, int colonne) {
        this.ligne = ligne;
        this.colonne = colonne;
        this.cellules = new String[2][2];
        this.createSecteur();
    }

    public void createSecteur() {
        /* Secteur vide
         * | | |
         * | | |
         * */
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                cellules[i][j] = " ";
            }
        }
    }

    public void addEau(){
        /* Secteur avec de l'eau
        * |X|X|
        * |X|X|
        * */
        cellules[0][0] = "X";
        cellules[0][1] = "X";
        cellules[1][0] = "X";
        cellules[1][1] = "X";
    }

    public void addMine(Mine mine) {
        /* Secteur avec une mine
        * |M|*|
        * | | |
        * */
        cellules[0][0] = "M";
        cellules[0][1] = String.valueOf(mine.getNumM());
    }

    public void addEntrepot(Entrepot entrepot) {
        /* Secteur avec un entrepot
         * |E|*|
         * | | |
         */
        cellules[0][0] = "E";
        cellules[0][1] = String.valueOf(entrepot.getNumE());
    }

    public void addRobot(Robot robot) {
        /* Secteur avec un robot
        * | | |
        * |R|*|
         */
        /* Secteur avec un robot et une mine
        * |M|*|
        * |R|*|
        * */
        cellules[1][0] = "R";
        cellules[1][1] = String.valueOf(robot.getNumR());
        robot.setSecteur(this);
    }

    public void removeRobot() {
        cellules[1][0] = " ";
        cellules[1][1] = " ";
    }

    public int getLigne(){
        return this.ligne;
    }

    public int getColonne(){
        return this.colonne;
    }

    public String getCellule(int i, int j){
        return cellules[i][j];
    }

    @Override
    public String toString(){
        String str = "";
        if (cellules[0][0].equals("X")) {
            str += "XX";
        } else if (cellules[1][0].equals("R") && cellules[0][0].equals("M")) {
            str += "RM";
        } else if (cellules[1][0].equals("R") && cellules[0][0].equals("E")) {
            str += "RE";
        } else if (cellules[0][0].equals("M")) {
            str += " M";
        } else if (cellules[0][0].equals("E")) {
            str += " E";
        } else if (cellules[1][0].equals("R")) {
            str += "R ";
        } else {
            str += "  ";
        }
        return str;

    }
}
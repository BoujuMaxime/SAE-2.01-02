package org.example.sae20102;

public class Robot {
    private TypeM nature;
    private int capacite;
    private int quantite;
    private String numRobot;
    private int x;
    private int y;

    public Robot(TypeM nature, String numRobot) {
        this.quantite = 0;
        this.capacite = 100;
        this.nature = nature;
        this.numRobot = numRobot;
    }

    public void fill(Mine mine) {
        if (this.nature.equals(mine.getNature())) {
            int quantite = Math.min(this.capacite - this.quantite, mine.getCapacite());
            this.quantite += quantite;
            mine.extrate(quantite);
        }
    }

    public void unload(Entrepot entrepot) {
        if (this.nature.equals(entrepot.getNature())) {
            entrepot.fill(this.quantite);
        }
    }

    public int getQuantite() {
        return quantite;
    }

    public TypeM getNature() {
        return nature;
    }

    public String getNumR(){
        return this.numRobot;
    }

    public void setCoord(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
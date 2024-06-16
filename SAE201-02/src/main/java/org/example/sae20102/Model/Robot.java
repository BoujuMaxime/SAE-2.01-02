package org.example.sae20102.Model;

public class Robot {
    private TypeM nature;
    private int capacite;
    private int quantite;
    private int recolte;
    private String numRobot;
    private Secteur secteur;

    public Robot(TypeM nature, String numRobot, Secteur secteur) {
        this.quantite = 0;
        this.capacite = 5 + (int)(Math.random() * 4);
        this.recolte = 1 + (int)(Math.random() * 2);
        this.nature = nature;
        this.numRobot = numRobot;
        this.secteur = secteur;
    }

    public boolean Fill(Mine mine) {
        if (this.nature.equals(mine.getNature())) { //
            if (this.quantite < this.capacite) { // Robot non plein
                int quantite = Math.min(this.recolte, mine.getCapacite());
                quantite = Math.min(quantite, this.capacite - this.quantite);
                this.quantite += quantite;
                return mine.Extrate(quantite);
            }
        }
        return false;
    }

    public boolean Unload(Entrepot entrepot) {
        if (this.nature.equals(entrepot.getNature())) {
            entrepot.Fill(this.quantite);
            this.quantite = 0;
            return true;
        }
        return false;
    }

    public int getQuantite() {
        return quantite;
    }

    public int getCapacite() {
        return capacite;
    }

    public TypeM getNature() {
        return nature;
    }

    public String getNumR(){
        return this.numRobot;
    }

    public Secteur getSecteur(){
        return this.secteur;
    }

    public int getRecolte(){
        return this.recolte;
    }

    public void setQuantite(int quantite){
        this.quantite = quantite;
    }

    public void setSecteur(Secteur secteur){
        this.secteur = secteur;
    }

}
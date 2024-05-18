package org.example.sae20102;

public class Robot {
    private TypeM nature;
    private int capacite;
    private int quantite;
    private String numRobot;
    private Secteur secteur;

    public Robot(TypeM nature, String numRobot, Secteur secteur) {
        this.quantite = 0;
        this.capacite = 100;
        this.nature = nature;
        this.numRobot = numRobot;
        this.secteur = secteur;
    }

    public void Fill(Mine mine) {
        if (this.nature.equals(mine.getNature())) {
            int quantite = Math.min(this.capacite - this.quantite, mine.getCapacite());
            this.quantite += quantite;
            mine.Extrate(quantite);
        }
    }

    public void Unload(Entrepot entrepot) {
        if (this.nature.equals(entrepot.getNature())) {
            entrepot.Fill(this.quantite);
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

    public void setSecteur(Secteur secteur){
        this.secteur = secteur;
    }

    public Secteur getSecteur(){
        return this.secteur;
    }
}
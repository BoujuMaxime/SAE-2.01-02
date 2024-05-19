package org.example.sae20102;

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

    public void Fill(Mine mine) {
        if (this.nature.equals(mine.getNature())) {
            if (this.quantite < this.capacite) {
                int quantite = Math.min(this.recolte, mine.getCapacite());
                this.quantite += quantite;
                mine.Extrate(quantite);
            }
        } else {
            System.out.println("Le robot ne peut pas transporter ce type de minerai");
        }
    }

    public void Unload(Entrepot entrepot) {
        if (this.nature.equals(entrepot.getNature())) {
            entrepot.Fill(this.quantite);
        }
        else {
            System.out.println("L'entrepot ne peut pas recevoir ce type de minerai");
        }
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

    public void setSecteur(Secteur secteur){
        this.secteur = secteur;
    }

    public Secteur getSecteur(){
        return this.secteur;
    }
}
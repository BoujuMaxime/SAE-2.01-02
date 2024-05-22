package org.example.sae20102.Model;

public class Mine {
    private int capacite;
    private TypeM minerai;
    private String numMine;
    private Secteur secteur;

    public Mine(TypeM minerai, String numMine, Secteur secteur) {
        this.capacite = 50 + (int)(Math.random() * 50);
        this.minerai = minerai;
        this.numMine = numMine;
        this.secteur = secteur;
    }


    public void Extrate(int quantite) {
        if (quantite <= capacite) {
            capacite -= quantite;
        }
    }

    public TypeM getNature(){
        return this.minerai;
    }

    public String getNumM(){
        return this.numMine;
    }

    public int getCapacite() {
        return capacite;
    }

    public Secteur getSecteur(){
        return this.secteur;
    }
}
package org.example.sae20102;

public class Entrepot {
    private int quantite;
    private TypeM minerai;
    private String numEntrepot;
    private Secteur secteur;

    public Entrepot(TypeM minerai, String numEntrepot, Secteur secteur) {
        this.quantite = 0;
        this.minerai = minerai;
        this.numEntrepot = numEntrepot;
        this.secteur = secteur;
    }

    public void Fill(int quantite) {
        this.quantite += quantite;
    }

    public int getQuantite() {
        return quantite;
    }

    public TypeM getNature() {
        return minerai;
    }

    public String getNumE(){
        return this.numEntrepot;
    }

    public Secteur getSecteur(){
        return this.secteur;
    }

    public void setSecteur(Secteur secteur){
        this.secteur = secteur;
    }
}
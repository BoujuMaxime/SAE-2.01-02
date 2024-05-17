package org.example.sae20102;

public class Entrepot {
    private int quantite;
    private TypeM minerai;
    private String numEntrepot;

    public Entrepot(TypeM minerai, String numEntrepot) {
        this.quantite = 0;
        this.minerai = minerai;
        this.numEntrepot = numEntrepot;
    }

    public void fill(int quantite) {
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
}
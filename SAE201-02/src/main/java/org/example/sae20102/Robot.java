package org.example.sae20102;

public class Robot {
    private TypeM nature;
    private int capacite;
    private int quantite;
    private TypeM natureEntrepot;


    public Robot(TypeM nature, TypeM natureEntrepot) {
        this.capacite = 100;
        this.nature = nature;
        this.quantite = 0;
        this.natureEntrepot = natureEntrepot;
    }

    public void remplir(Mine mine) {
        if (this.nature.equals(mine.getNature())) {
            int quantite = Math.min(this.capacite - this.quantite, mine.getCapacite());
            this.quantite += quantite;
            mine.extraire(quantite);
        }
    }

    public void vider(Entrepot entrepot) {
        if (this.natureEntrepot.equals(entrepot.getNature())) {
            entrepot.remplir(this.quantite);
        }
    }

    public int getQuantite() {
        return quantite;
    }

    public TypeM getNature() {
        return nature;
    }
}
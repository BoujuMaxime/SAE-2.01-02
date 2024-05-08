package org.example.sae20102;

public class Robot {
    private TypeM nature;
    private int capacite;
    private int quantite;
    private TypeM natureEntrepot;
    private int capaciter;


    public Robot(TypeM nature, TypeM natureEntrepot) {
        this.capacite = 100;
        this.nature = nature;
        this.quantite = 0;
        this.natureEntrepot = natureEntrepot;
    }

    public void remplir(Mine mine) {
        if (this.nature.equals(mine.getNature())) {
            int quantite = Math.min(this.capaciter - this.quantite, mine.getQuantite());
            this.quantite += quantite;
            mine.extraire(quantite);
        }
    }

    public void vider(Entrepot entrepot) {
        if (this.natureEntrepot.equals(entrepot.getNature())) {
            int quantite = Math.min(this.quantite, entrepot.getCapaciter() - entrepot.getQuantite());
            this.quantite -= quantite;
            entrepot.remplir(quantite);
        }
    }

    public int getQuantite() {
        return quantite;
    }

    public String getNature() {
        return nature;
    }
}
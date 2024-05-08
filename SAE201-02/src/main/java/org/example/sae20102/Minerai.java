package org.example.sae20102;

public class Minerai {
    private TypeM nature;
    private int quantite;

    public Minerai(TypeM nature) {
        this.nature = nature;
        this.quantite = 0;
    }

    public void ajouter(int quantite) {
        this.quantite += quantite;
    }

    public int getQuantite() {
        return quantite;
    }

    public TypeM getNature() {
        return nature;
    }
}

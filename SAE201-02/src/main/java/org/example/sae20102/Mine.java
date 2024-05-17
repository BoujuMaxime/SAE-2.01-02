package org.example.sae20102;

import org.example.sae20102.TypeM;

public class Mine {
    private int capacite;
    private Minerai minerai;

    public Mine(Minerai minerai) {
        this.capacite = 50 + (int)(Math.random() * 50);
        this.minerai = minerai;
    }

    public int getCapacite() {
        return capacite;
    }

    public Minerai getMinerai() {
        return minerai;
    }

    public void extraire(int quantite) {
        if (quantite <= capacite) {
            capacite -= quantite;
        }
    }

    public TypeM getNature(){
        return this.minerai.getNature();
    }
}
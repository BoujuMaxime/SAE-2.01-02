package org.example.sae20102;

import org.example.sae20102.TypeM;

public class Mine {
    private int capacite;
    private TypeM minerai;
    private String numMine;

    public Mine(TypeM minerai, String numMine) {
        this.capacite = 50 + (int)(Math.random() * 50);
        this.minerai = minerai;
        this.numMine = numMine;
    }

    public void extrate(int quantite) {
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


}
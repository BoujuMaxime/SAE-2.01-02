package org.example.sae20102;

import org.example.sae20102.TypeM;

public class Mine {
    private int capaciter;
    private Minerai minerai;

    public Mine(nature) {
        this.capaciter = 50 + (int)(Math.random() * 50);
        this.minerai = minerai;
    }

    public int getCapaciter() {

        return capaciter;
    }

    public Minerai getMinerai() {
        return minerai;
    }

    public void extraire(int quantite) {
        if (quantite <= capaciter) {
            capaciter -= quantite;
        }
    }
}
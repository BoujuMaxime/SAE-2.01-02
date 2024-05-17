package org.example.sae20102;

public class Main {
    public static void main(String[] args) {
        Minerai or = new Minerai(TypeM.OR);
        Minerai nickel = new Minerai(TypeM.NICKEL);
        Mine mine1 = new Mine(or);
        Mine mine2 = new Mine(nickel);
        Entrepot entrepot1 = new Entrepot(TypeM.OR);
        Entrepot entrepot2 = new Entrepot(TypeM.NICKEL);
        Robot robot1 = new Robot(TypeM.OR, TypeM.OR);
        Robot robot2 = new Robot(TypeM.NICKEL, TypeM.NICKEL);
        Robot robot3 = new Robot(TypeM.OR, TypeM.NICKEL);
        Robot robot4 = new Robot(TypeM.NICKEL, TypeM.OR);
    }
}


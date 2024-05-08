package org.example.sae20102;

public class Main {
    public static void main(String[] args) {
        Mine mine1 = new Mine(TypeM.OR);
        Mine mine2 = new Mine(TypeM.NICKEL);
        Entrepot entrepot1 = new Entrepot(TypeM.OR);
        Entrepot entrepot2 = new Entrepot(TypeM.NICKEL);
        Robot robot1 = new Robot(TypeM.OR, TypeM.OR);
        Robot robot2 = new Robot(TypeM.NICKEL, TypeM.NICKEL);
        Robot robot3 = new Robot(TypeM.OR, TypeM.NICKEL);
        Robot robot4 = new Robot(TypeM.NICKEL, TypeM.OR);
        robot1.remplir(mine1);
        robot1.vider(entrepot1);
        robot2.remplir(mine2);
        robot2.vider(entrepot2);
        robot3.remplir(mine1);
        robot3.vider(entrepot2);
        robot4.remplir(mine2);
        robot4.vider(entrepot1);
    }
}


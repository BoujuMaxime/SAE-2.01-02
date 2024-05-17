package org.example.sae20102;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Mine mine1 = new Mine(TypeM.OR, "1");
        Mine mine2 = new Mine(TypeM.NICKEL, "2");
        Entrepot entrepot1 = new Entrepot(TypeM.OR, "1");
        Entrepot entrepot2 = new Entrepot(TypeM.NICKEL, "2");
        Robot robot1 = new Robot(TypeM.OR, "1");
        Robot robot2 = new Robot(TypeM.NICKEL, "2");

        Grille grille = new Grille();
        grille.addEau(0, 5);
        grille.addEau(0, 6);
        grille.addMine(mine1, 4, 6);
        grille.addMine(mine2, 6, 3);
        grille.addEntrepot(entrepot1, 0, 9);
        grille.addEntrepot(entrepot2, 9, 0);
        grille.addRobot(robot1, 0, 0);
        grille.addRobot(robot2, 9, 9);

        System.out.println(grille);

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Choisissez un robot (1 ou 2) : ");
            int numRobot = scanner.nextInt();
            System.out.println("Choisissez une action (1 pour bouger, 2 pour remplir, 3 pour décharger) : ");
            int action = scanner.nextInt();
            if (action == 1) {
                System.out.println("Choisissez la direction (1 pour haut, 2 pour bas, 3 pour gauche, 4 pour droite) : ");
                int direction = scanner.nextInt();
                if (direction == 1) {
                    grille.moveRobot(0, 0, 0, 1, robot1);
                } else if (direction == 2) {
                    grille.moveRobot(0, 0, 0, -1, robot1);
                } else if (direction == 3) {
                    grille.moveRobot(0, 0, -1, 0, robot1);
                } else if (direction == 4) {
                    grille.moveRobot(0, 0, 1, 0, robot1);
                }
            } else if (action == 2) {
                robot1.fill(mine1);
            } else if (action == 3) {
                robot1.unload(entrepot1);
            }
        }
    }
}
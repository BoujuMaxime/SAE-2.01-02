package org.example.sae20102;

import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Grille grille = createGrille();

        while (true) {
            System.out.println(grille);
            Scanner scanner = new Scanner(System.in);
            System.out.println("Choisissez un robot (1 ou 2) : ");
            String numRobot = scanner.next();
            Robot robot = grille.getRobot(numRobot);

            Secteur secteurRobot = grille.getSecteur(robot.getX(), robot.getY());

            if (secteurRobot.getCellules()[0][0].equals(" ")) {
                System.out.println("Choisissez la direction (Z pour haut, S pour bas, Q pour gauche, D pour droite) : ");
                String direction = scanner.next();
                moov(robot, grille, direction);
            }
        }
    }

    private static Grille createGrille () {
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
        return grille;
    }

    private static Grille  moov(Robot robot, Grille grille, String direction) {
        int x = robot.getX();
        int y = robot.getY();

        if (Objects.equals(direction, "Z")) {
            grille.moveRobot(x, y, x - 1, y, robot);
        } else if (Objects.equals(direction, "S")) {
            grille.moveRobot(x, y, x + 1, y, robot);
        } else if (Objects.equals(direction, "Q")) {
            grille.moveRobot(x, y, x, y - 1, robot);
        } else if (Objects.equals(direction, "D")) {
            grille.moveRobot(x, y, x, y + 1, robot);
        }

        return grille;
    }
}
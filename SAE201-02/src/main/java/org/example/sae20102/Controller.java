package org.example.sae20102;

import org.example.sae20102.Model.*;
import org.example.sae20102.Model.Robot;

import java.util.Objects;

public class Controller  {
    private int nbMines;
    private int nbEntrepots;
    private int nbRobots;
    private  Entrepot[] entrepots;
    private  Mine[] mines;
    private  Robot[] robots;
    private Grille grille;


    public Controller() {
        nbMines = 2 + ((int)(Math.random() * 3));
        nbEntrepots = 2 ;
        nbRobots = 2 + ((int)(Math.random() * 3));

        entrepots = new Entrepot[nbEntrepots];
        mines = new Mine[nbMines];
        robots = new Robot[nbRobots];
        grille = createGrille(robots, mines, entrepots);
    }

    private static Grille createGrille(Robot[] robots, Mine[] mines, Entrepot[] entrepots) {
        System.out.println("Création de la grille");
        Grille grille = new Grille();
        int nbEau = (int)(Math.random() * 10);
        int nbMines = mines.length;
        int nbEntrepots = entrepots.length;
        int nbRobots = robots.length;

        System.out.println("nbMines = " + nbMines);
        System.out.println("nbEntrepots = " + nbEntrepots);
        System.out.println("nbRobots = " + nbRobots);
        System.out.println("nbEau = " + nbEau);

        grille = createEau(grille, nbEau);
        grille = createMines(mines, grille, nbMines);
        grille = createEntrepots(entrepots, grille, nbEntrepots);
        grille = createRobots(robots, grille, nbRobots);

        return grille;
    }

        private static Grille createEau(Grille grille, int nbEau) {
            // On ajoute de l'eau sur la grille
            for (int i = 0; i < nbEau; i++) {
                // Si le secteur est vide, on ajoute de l'eau
                while (true) {
                    int x = (int)(Math.random() * 10);
                    int y = (int)(Math.random() * 10);
                    Secteur secteur = grille.getSecteur(x, y);
                    if (secteur.getCellule(0, 0).equals(" ")) {
                        secteur.addEau();
                        break;
                    }
                }
            }
            return grille;
        }

        private static Grille createMines(Mine[] mines, Grille grille, int nbMines) {
            // On ajoute des mines sur la grille
            for (int i = 0; i < nbMines; i++) {
                // Si le secteur est vide, on ajoute une mine
                while (true) {
                    int x = (int)(Math.random() * 10);
                    int y = (int)(Math.random() * 10);
                    Secteur secteur = grille.getSecteur(x, y);
                    if (secteur.getCellule(0, 0).equals(" ")) {
                        if (i % 2 == 0) {
                            Mine mine = new Mine(TypeM.NICKEL, Integer.toString(i), secteur);
                            mines[i] = mine;
                            grille.addMine(mine, x, y);
                            break;
                        } else {
                            Mine mine = new Mine(TypeM.OR, Integer.toString(i), secteur);
                            mines[i] = mine;
                            grille.addMine(mine, x, y);
                            break;
                        }
                    }
                }
            }
            return grille;
        }

        private static Grille createEntrepots(Entrepot[] entrepots, Grille grille, int nbEntrepots) {
            // On ajoute des entrepots sur la grille
            for (int i = 0; i < nbEntrepots; i++) {
                // Si le secteur est vide, on ajoute un entrepot
                while (true) {
                    int x = (int)(Math.random() * 10);
                    int y = (int)(Math.random() * 10);
                    Secteur secteur = grille.getSecteur(x, y);
                    if (secteur.getCellule(0, 0).equals(" ")) {
                        if (i % 2 == 0) {
                            Entrepot entrepot = new Entrepot(TypeM.NICKEL, Integer.toString(i), secteur);
                            entrepots[i] = entrepot;
                            grille.addEntrepot(entrepot, x, y);
                            break;
                        } else {
                            Entrepot entrepot = new Entrepot(TypeM.OR, Integer.toString(i), secteur);
                            entrepots[i] = entrepot;
                            grille.addEntrepot(entrepot, x, y);
                            break;
                        }
                    }
                }
            }
            return grille;
        }

        private static Grille createRobots(Robot[] robots, Grille grille, int nbRobots) {
            // On ajoute des robots sur la grille
            for (int i = 0; i < nbRobots; i++) {
                // Si le secteur est vide, on ajoute un robot
                while (true) {
                    int x = (int)(Math.random() * 10);
                    int y = (int)(Math.random() * 10);
                    Secteur secteur = grille.getSecteur(x, y);
                    if (secteur.getCellule(1, 0).equals(" ")) {
                        if (i % 2 == 0) {
                            Robot robot = new Robot(TypeM.NICKEL, Integer.toString(i), secteur);
                            robots[i] = robot;
                            grille.addRobot(robot, x, y);
                            break;
                        } else {
                            Robot robot = new Robot(TypeM.OR, Integer.toString(i), secteur);
                            robots[i] = robot;
                            grille.addRobot(robot, x, y);
                            break;
                        }
                    }
                }
            }
            return grille;
        }

    public boolean MoveRobot(Robot robot, String direction) {
        int x = robot.getSecteur().getLigne();
        int y = robot.getSecteur().getColonne();

        switch (direction) {
            case "H":
                if (x > 0) {
                    if (Objects.equals(grille.getSecteur(x - 1, y).getCellule(1, 0), " ")) {
                        grille.moveRobot(x, y, x - 1, y, robot);
                        return true;
                    }
                }
                break;
            case "B":
                if (x < 9) {
                    if (Objects.equals(grille.getSecteur(x + 1, y).getCellule(1, 0), " ")) {
                        grille.moveRobot(x, y, x + 1, y, robot);
                        return true;
                    }
                }
                break;
            case "D":
                if (y < 9) {
                    if (Objects.equals(grille.getSecteur(x, y + 1).getCellule(1, 0), " ")) {
                        grille.moveRobot(x, y, x, y + 1, robot);
                        return true;
                    }
                }
                break;
            case "G":
                if (y > 0) {
                    if (Objects.equals(grille.getSecteur(x, y - 1).getCellule(1, 0), " ")) {
                        grille.moveRobot(x, y, x, y - 1, robot);
                        return true;
                    }
                }
                break;
        }

        return false;
    }

    public boolean FillRobot(Robot robot, Mine mine) {
        return robot.Fill(mine);
    }

    public boolean UnloadRobot(Robot robot, Entrepot entrepot) {
        return robot.Unload(entrepot);
    }

    public Grille getGrille() {
        return grille;
    }

    public Robot[] getRobots() {
        return robots;
    }

    public Entrepot[] getEntrepots() {
        return entrepots;
    }

    public Mine[] getMines() {
        return mines;
    }

    public int getNbMines() {
        return nbMines;
    }

    public int getNbEntrepots() {
        return nbEntrepots;
    }

    public int getNbRobots() {
        return nbRobots;
    }

    public Grille play() {
        for (Robot robot : robots) {
            String choice = CerveauRobot.getChoiceRobot(robot, grille, mines, entrepots);
            switch (choice) {
                case "Fill":
                    for (Mine mine : mines) {
                        if (mine.getSecteur().equals(robot.getSecteur())) {
                            FillRobot(robot, mine);
                        }
                    }
                    break;
                case "Unload":
                    for (Entrepot entrepot : entrepots) {
                        if (entrepot.getSecteur().equals(robot.getSecteur())) {
                            UnloadRobot(robot, entrepot);
                        }
                    }
                    break;
                case "Move":
                    String direction = CerveauRobot.getDirectionRobot(robot, grille, mines, entrepots);
                    MoveRobot(robot, direction);
                    break;
            }
            System.out.println(grille);
            System.out.println("Robot " + robot.getNumR() + " Choice : " + choice);
        }
        return grille;
    }
}
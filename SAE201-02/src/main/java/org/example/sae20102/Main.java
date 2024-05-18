package org.example.sae20102;

import java.util.ArrayList;
import java.util.Objects;

public class Main {

    public static void main(String[] args) {
        ArrayList<Robot> robots = new ArrayList<>();
        ArrayList<Mine> mines = new ArrayList<>();
        ArrayList<Entrepot> entrepots = new ArrayList<>();
        Grille grille = createGrille(robots, mines, entrepots);

        System.out.println(grille);
    }


    private static Grille createGrille(ArrayList<Robot> robots, ArrayList<Mine> mines, ArrayList<Entrepot> entrepots) {
        Grille grille = new Grille();

        int nbMines = 2 + ((int)(Math.random() * 3));
        int nbEntrepots = 2 ;
        int nbRobots = 2 + ((int)(Math.random() * 3));
        int nbEau = (int)(Math.random() * 10);
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

        private static Grille createMines(ArrayList<Mine> mines, Grille grille, int nbMines) {
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
                            mines.add(mine);
                            grille.addMine(mine, x, y);
                            break;
                        } else {
                            Mine mine = new Mine(TypeM.OR, Integer.toString(i), secteur);
                            mines.add(mine);
                            grille.addMine(mine, x, y);
                            break;
                        }
                    }
                }
            }
            return grille;
        }

        private static Grille createEntrepots(ArrayList<Entrepot> entrepots, Grille grille, int nbEntrepots) {
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
                            entrepots.add(entrepot);
                            grille.addEntrepot(entrepot, x, y);
                            break;
                        } else {
                            Entrepot entrepot = new Entrepot(TypeM.OR, Integer.toString(i), secteur);
                            entrepots.add(entrepot);
                            grille.addEntrepot(entrepot, x, y);
                            break;
                        }
                    }
                }
            }
            return grille;
        }

        private static Grille createRobots(ArrayList<Robot> robots, Grille grille, int nbRobots) {
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
                            robots.add(robot);
                            grille.addRobot(robot, x, y);
                            break;
                        } else {
                            Robot robot = new Robot(TypeM.OR, Integer.toString(i), secteur);
                            robots.add(robot);
                            grille.addRobot(robot, x, y);
                            break;
                        }
                    }
                }
            }
            return grille;
        }

    private static Grille Move(Robot robot, Grille grille, String direction) {
            int x = robot.getSecteur().getLigne();
            int y = robot.getSecteur().getColonne();

            if (Objects.equals(direction, "H")) {
                grille.moveRobot(x, y, x - 1, y, robot);
            } else if (Objects.equals(direction, "B")) {
                grille.moveRobot(x, y, x + 1, y, robot);
            } else if (Objects.equals(direction, "G")) {
                grille.moveRobot(x, y, x, y - 1, robot);
            } else if (Objects.equals(direction, "D")) {
                grille.moveRobot(x, y, x, y + 1, robot);
            }
            return grille;
        }

    private static void FillRobot(Robot robot, ArrayList<Mine> mines) {
        Secteur secteur = robot.getSecteur();
        for (Mine mine : mines) {
            if (mine.getSecteur() == secteur) {
                robot.Fill(mine);
            }
        }
    }

    private static void UnloadRobot(Robot robot, ArrayList<Entrepot> entrepots) {
        Secteur secteur = robot.getSecteur();
        for (Entrepot entrepot : entrepots) {
            if (entrepot.getSecteur() == secteur) {
                robot.Unload(entrepot);
            }
        }
    }

}
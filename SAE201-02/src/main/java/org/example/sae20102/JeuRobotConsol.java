package org.example.sae20102;

import java.util.Objects;
import java.util.Scanner;

public class JeuRobotConsol {

    public static void main(String[] args) {

        int nbMines = 2 + ((int)(Math.random() * 3));
        int nbEntrepots = 2 ;
        int nbRobots = 2 + ((int)(Math.random() * 3));

        Entrepot[] entrepots = new Entrepot[nbEntrepots];
        Mine[] mines = new Mine[nbMines];
        Robot[] robots = new Robot[nbRobots];
        Grille grille = createGrille(robots, mines, entrepots);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Jeu Humain (H) ou Robot (R) : ");
        String jeu = scanner.nextLine();
        if (Objects.equals(jeu, "H")) {
            jeuHumainConsol(robots, mines, entrepots, grille);
        } else {
            jeuRobotConsol(robots, mines, entrepots, grille);
        }
    }

    private static void jeuRobotConsol(Robot[] robots, Mine[] mines, Entrepot[] entrepots, Grille grille) {
    Scanner scanner = new Scanner(System.in);
    while (true) {
        System.out.println(grille);
        System.out.println("Appuyer sur entrée pour continuer");
        scanner.nextLine();
        for (Robot robot : robots) {
            Secteur secteurRobot = robot.getSecteur();
            String cellule = secteurRobot.getCellule(0, 0);
            String direction, reponse;
            switch (cellule) {
                case " ":
                    direction = CerveauRobot.getDirectionRobot(robot, grille);
                    MoveRobot(robot, grille, direction);
                    break;
                case "M":
                    int numMine = Integer.parseInt(secteurRobot.getCellule(0, 1));
                    Mine mine = mines[numMine];
                    reponse = CerveauRobot.getReponseRobot(robot, mine);
                    if (Objects.equals(reponse, "O")) {
                        FillRobot(robot, mine);
                    } else {
                        direction = CerveauRobot.getDirectionRobot(robot, grille);
                        MoveRobot(robot, grille, direction);
                    }
                    break;
                case "E":
                    int numEntrepot = Integer.parseInt(secteurRobot.getCellule(0, 1));
                    Entrepot entrepot = entrepots[numEntrepot];
                    reponse = CerveauRobot.getReponseRobot(robot, entrepot);
                    if (Objects.equals(reponse, "O")) {
                        UnloadRobot(robot, entrepot);
                    } else {
                        direction = CerveauRobot.getDirectionRobot(robot, grille);
                        MoveRobot(robot, grille, direction);
                    }
                    break;
            }
        }
    }
}

    private static void jeuHumainConsol(Robot[] robots, Mine[] mines, Entrepot[] entrepots, Grille grille) {
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println(grille);
            for (Robot robot : robots) {
                Secteur secteurRobot = robot.getSecteur();
                String cellule = secteurRobot.getCellule(0, 0);
                String direction, reponse;
                switch (cellule) {
                    case " ":
                        System.out.println("Direction (H/B/G/D) : ");
                        direction = scanner.nextLine();
                        MoveRobot(robot, grille, direction);
                        break;
                    case "M":
                        int numMine = Integer.parseInt(secteurRobot.getCellule(0, 1));
                        Mine mine = mines[numMine];
                        System.out.println("Voulez-vous remplir le robot ? (O/N) : ");
                        reponse = scanner.nextLine();
                        if (Objects.equals(reponse, "O")) {
                            FillRobot(robot, mine);
                        } else {
                            System.out.println("Direction (H/B/G/D) : ");
                            direction = scanner.nextLine();
                            MoveRobot(robot, grille, direction);
                        }
                        break;
                    case "E":
                        int numEntrepot = Integer.parseInt(secteurRobot.getCellule(0, 1));
                        Entrepot entrepot = entrepots[numEntrepot];
                        System.out.println("Voulez-vous décharger le robot ? (O/N) : ");
                        reponse = scanner.nextLine();
                        if (Objects.equals(reponse, "O")) {
                            UnloadRobot(robot, entrepot);
                        } else {
                            System.out.println("Direction (H/B/G/D) : ");
                            direction = scanner.nextLine();
                            MoveRobot(robot, grille, direction);
                        }
                        break;
                }
            }
        }
    }

    private static Grille createGrille(Robot[] robots, Mine[] mines, Entrepot[] entrepots) {
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

    private static void MoveRobot(Robot robot, Grille grille, String direction) {
            int x = robot.getSecteur().getLigne();
            int y = robot.getSecteur().getColonne();

            if (Objects.equals(direction, "H")) {
                if (x > 0 ) {
                    if (!grille.getSecteur(x - 1, y).getCellule(0, 0).equals("X") && !grille.getSecteur(x - 1, y).getCellule(0, 0).equals("R")) {
                        grille.moveRobot(x, y, x - 1, y, robot);
                    } else {System.out.println("Déplacement impossible");}
                } else {System.out.println("Déplacement impossible hors limite");}
            }
            if (Objects.equals(direction, "B")) {
                if (x < 9 ) {
                    if (!grille.getSecteur(x + 1, y).getCellule(0, 0).equals("X") && !grille.getSecteur(x + 1, y).getCellule(0, 0).equals("R")) {
                        grille.moveRobot(x, y, x + 1, y, robot);
                    } else {System.out.println("Déplacement impossible");}
                } else {System.out.println("Déplacement impossible or limite");}
            }
            if (Objects.equals(direction, "G")) {
                if (y > 0 ) {
                    if (!grille.getSecteur(x, y - 1).getCellule(0, 0).equals("X") && !grille.getSecteur(x, y - 1).getCellule(0, 0).equals("R")) {
                        grille.moveRobot(x, y, x, y - 1, robot);
                    } else {System.out.println("Déplacement impossible");}
                } else {System.out.println("Déplacement impossible hors limite");}
            }
            if (Objects.equals(direction, "D")) {
                if (y < 9 ) {
                    if (!grille.getSecteur(x, y + 1).getCellule(0, 0).equals("X") && !grille.getSecteur(x, y + 1).getCellule(0, 0).equals("R")) {
                        grille.moveRobot(x, y, x, y + 1, robot);
                    } else {System.out.println("Déplacement impossible");}
                } else {System.out.println("Déplacement impossible hors limite");}
            }
    }

    private static void FillRobot(Robot robot, Mine mine) {
        robot.Fill(mine);
    }

    private static void UnloadRobot(Robot robot, Entrepot entrepot) {
        robot.Unload(entrepot);
    }
}
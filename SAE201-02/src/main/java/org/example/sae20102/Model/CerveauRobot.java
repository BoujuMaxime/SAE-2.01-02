package org.example.sae20102.Model;

import org.example.sae20102.Controller;

import java.util.*;

public class CerveauRobot {

    // Fonctionnement de l'algorithme:
    // Si le robot est vide, on se dirige vers la mine la plus proche avec l'algorithme de Dijkstra
    // Une fois la mine atteinte, on rempli le robot
    // Si le robot est plein, on se dirige vers l'entrepot le plus proche avec l'algorithme de Dijkstra
    // Une fois l'entrepot atteint, on décharge le robot

    public static String getChoiceRobot(Robot robot, Grille grille, Mine[] mines, Entrepot[] entrepots) {
        if (robot.getQuantite() < robot.getCapacite()) { // Robot empty
            // If is already in a "Mine"
            if (grille.getSecteur(robot.getSecteur().getLigne(), robot.getSecteur().getColonne()).getCellule(0, 0).equals("M")) {
                return "Fill";
            }
            else {
                return "Move";
            }
        }
        else if (robot.getQuantite() == robot.getCapacite()) { // Robot full
            // If is already in a "Entrepot"
            if (grille.getSecteur(robot.getSecteur().getLigne(), robot.getSecteur().getColonne()).getCellule(0, 0).equals("E")) {
                return "Unload";
            }
            else {
                return "Move";
            }
        }
        return "Error";
    }

    public static String getDirectionRobot(Robot robot, Grille grille, Mine[] mines, Entrepot[] entrepots) {
        Secteur targetSecteur;

        if (robot.getQuantite() == 0) { // Robot is empty
            Mine nearestMine = findNearestMine(robot, mines);
            targetSecteur = nearestMine.getSecteur();
        } else { // Robot is full
            Entrepot nearestEntrepot = findNearestEntrepot(robot, entrepots);
            targetSecteur = nearestEntrepot.getSecteur();
        }

        int dx = targetSecteur.getLigne() - robot.getSecteur().getLigne();
        int dy = targetSecteur.getColonne() - robot.getSecteur().getColonne();

        if (Math.abs(dx) > Math.abs(dy)) {
            return dx > 0 ? "B" : "H";
        } else {
            return dy > 0 ? "D" : "G";
        }
    }

    private static Entrepot findNearestEntrepot(Robot robot, Entrepot[] entrepots) {
        int minDist = Integer.MAX_VALUE;
        Entrepot nearest = null;
        for (Entrepot entrepot : entrepots) {
            int dist = Math.abs(robot.getSecteur().getLigne() - entrepot.getSecteur().getLigne())
                    + Math.abs(robot.getSecteur().getColonne() - entrepot.getSecteur().getColonne());
            if (dist < minDist) {
                minDist = dist;
                nearest = entrepot;
            }
        }
        return nearest;

    }

    private static Mine findNearestMine(Robot robot, Mine[] mines) {
        int minDist = Integer.MAX_VALUE;
        Mine nearest = null;
        for (Mine mine : mines) {
            int dist = Math.abs(robot.getSecteur().getLigne() - mine.getSecteur().getLigne())
                    + Math.abs(robot.getSecteur().getColonne() - mine.getSecteur().getColonne());
            if (dist < minDist) {
                minDist = dist;
                nearest = mine;
            }
        }
        return nearest;
    }
}

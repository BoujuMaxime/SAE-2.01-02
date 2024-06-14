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
        Mine nearestMine = findNearestMine(robot, mines);
        Entrepot nearestEntrepot = findNearestEntrepot(robot, entrepots);
        if (nearestMine != null && nearestEntrepot != null) {
            if (robot.getQuantite() < robot.getCapacite() && nearestMine.getCapacite() > 0) { // Robot empty and mine not empty
                // If is already in a "Mine" of the same type
                if (robot.getSecteur().equals(nearestMine.getSecteur())) {
                    return "Fill";
                } else {
                    return "Move";
                }
            } else if (robot.getQuantite() == robot.getCapacite()) { // Robot full
                // If is already in a "Entrepot" of the same type
                if (robot.getSecteur().equals(nearestEntrepot.getSecteur())) {
                    return "Unload";
                } else {
                    return "Move";
                }
            }
        }
        return "Error";
    }

    public static List<Secteur> dijkstra(Robot robot, Grille grille, boolean findMine, Mine[] mines, Entrepot[] entrepots) {
        // Initialization
        int[][] dist = new int[10][10];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        dist[robot.getSecteur().getLigne()][robot.getSecteur().getColonne()] = 0;

        PriorityQueue<Secteur> queue = new PriorityQueue<>(Comparator.comparingInt(s -> dist[s.getLigne()][s.getColonne()]));
        queue.add(robot.getSecteur());

        Map<Secteur, Secteur> pathMap = new HashMap<>();

        while (!queue.isEmpty()) {
            Secteur current = queue.poll();

            for (Secteur neighbor : grille.getNeighbors(current)) {
                if (neighbor != null && !neighbor.getCellule(0, 0).equals("X") && !neighbor.getCellule(1, 0).equals("R")) {
                    int alt = dist[current.getLigne()][current.getColonne()] + 1;
                    if (alt < dist[neighbor.getLigne()][neighbor.getColonne()]) {
                        dist[neighbor.getLigne()][neighbor.getColonne()] = alt;
                        queue.add(neighbor);
                        pathMap.put(neighbor, current);

                        if (findMine && neighbor == findNearestMine(robot, mines).getSecteur() || !findMine && neighbor == findNearestEntrepot(robot, entrepots).getSecteur()) {
                            List<Secteur> path = new ArrayList<>();
                            for (Secteur at = neighbor; at != null; at = pathMap.get(at)) {
                                path.add(at);
                            }
                            Collections.reverse(path);
                            return path;
                        }
                    }
                }
            }
        }

        return null;
    }

    public static String getDirectionRobot(Robot robot, Grille grille, Mine[] mines, Entrepot[] entrepots) {
        List<Secteur> path = dijkstra(robot, grille, robot.getQuantite() < robot.getCapacite(), mines, entrepots);
        if (path != null && path.size() > 1) {
            Secteur next = path.get(1);
            if (next.getLigne() < robot.getSecteur().getLigne()) {
                return "H";
            } else if (next.getLigne() > robot.getSecteur().getLigne()) {
                return "B";
            } else if (next.getColonne() < robot.getSecteur().getColonne()) {
                return "G";
            } else if (next.getColonne() > robot.getSecteur().getColonne()) {
                return "D";
            }
        }
        return "Error";
    }


    private static Entrepot findNearestEntrepot(Robot robot, Entrepot[] entrepots) {
        int minDist = Integer.MAX_VALUE;
        Entrepot nearest = null;
        for (Entrepot entrepot : entrepots) {
            int dist = Math.abs(robot.getSecteur().getLigne() - entrepot.getSecteur().getLigne())
                    + Math.abs(robot.getSecteur().getColonne() - entrepot.getSecteur().getColonne());
            if (dist < minDist && entrepot.getNature().equals(robot.getNature())) {
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
            if (dist < minDist && mine.getNature().equals(robot.getNature()) && mine.getCapacite() > 0) {
                minDist = dist;
                nearest = mine;
            }
        }
        return nearest;
    }
}
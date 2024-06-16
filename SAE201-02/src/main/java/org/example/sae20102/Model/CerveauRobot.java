package org.example.sae20102.Model;

import java.util.*;

import static java.util.Comparator.comparingInt;

public class CerveauRobot {

    // Fonctionnement de l'algorithme:
    // Si le robot est vide, on se dirige vers la mine la plus proche avec l'algorithme de Dijkstra
    // Une fois la mine atteinte, on rempli le robot
    // Si le robot est plein, on se dirige vers l'entrepot le plus proche avec l'algorithme de Dijkstra
    // Une fois l'entrepot atteint, on décharge le robot

    public static String getChoiceRobot(Robot robot, Grille grille, Mine[] mines, Entrepot[] entrepots) {
        Mine nearestMine = MineProche(robot, mines);
        Entrepot nearestEntrepot = EntrepotProche(robot, entrepots);
        if (nearestMine != null && nearestEntrepot != null) {
            // Si toutes les cases ne sont pas connues
            if (BrouillardRestant(grille)) {
                return "Discover";
            }
            else if (robot.getQuantite() < robot.getCapacite() && nearestMine.getCapacite() > 0) { // Robot vide et mine non vide
                // Si nous sommes deja sur la mine
                if (robot.getSecteur().equals(nearestMine.getSecteur())) {
                    return "Fill";
                } else {    // Sinon on se dirige vers la mine
                    return "Move";
                }
            } else if (robot.getQuantite() == robot.getCapacite()) { // Robot plein
                // Si nous sommes deja sur l'entrepot
                if (robot.getSecteur().equals(nearestEntrepot.getSecteur())) {
                    return "Unload";
                } else {   // Sinon on se dirige vers l'entrepot
                    return "Move";
                }
            }
        }
        else if (nearestEntrepot != null) {
            if (robot.getQuantite() > 0) { // Robot pas vide
                // Si nous sommes deja sur l'entrepot
                if (robot.getSecteur().equals(nearestEntrepot.getSecteur())) {
                    return "Unload";
                } else {   // Sinon on se dirige vers l'entrepot
                    return "Move";
                }
            }
        }
        return "Move";
    }

    public static List<Secteur> dijkstra(Robot robot, Grille grille, boolean findMine, Mine[] mines, Entrepot[] entrepots) {
        // Initialisation de la matrice des distances
        int[][] dist = new int[10][10];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE); // On initialise toutes les distances à l'infini
        }
        dist[robot.getSecteur().getLigne()][robot.getSecteur().getColonne()] = 0;

        // Priority queue pour lister les secteurs à visiter en ordre croissant de distance
        PriorityQueue<Secteur> queue = new PriorityQueue<>(comparingInt(s -> dist[s.getLigne()][s.getColonne()]));
        queue.add(robot.getSecteur());

        // Map pour stocker le chemin le plus court entre un secteur et le secteur actuel
        Map<Secteur, Secteur> pathMap = new HashMap<>();

        // Tant qu'il reste des secteurs à visiter
        while (!queue.isEmpty()) {
            Secteur current = queue.poll();

            // Pour chaque voisin du secteur actuel
            for (Secteur neighbor : grille.getNeighbors(current)) {
                if(MineProche(robot, mines) != null) {
                    // Si le voisin est la mine la plus proche ou l'entrepot le plus proche ou si le voisin n'est pas un obstacle
                    if (findMine && neighbor == MineProche(robot, mines).getSecteur() || !findMine && neighbor == EntrepotProche(robot, entrepots).getSecteur()
                            || (neighbor != null && !neighbor.getCellule(0, 0).equals("X") && !neighbor.getCellule(1, 0).equals("R"))) {

                        // Alors on calcule la distance alternative
                        int alt = dist[current.getLigne()][current.getColonne()] + 1;

                        // Si cette distance est plus courte que la distance enrégistrée
                        if (alt < dist[neighbor.getLigne()][neighbor.getColonne()]) {
                            // On met à jour la distance
                            dist[neighbor.getLigne()][neighbor.getColonne()] = alt;
                            queue.add(neighbor);
                            pathMap.put(neighbor, current);

                            // Si le voisin est une destination
                            if (findMine && neighbor == MineProche(robot, mines).getSecteur() || !findMine && neighbor == EntrepotProche(robot, entrepots).getSecteur()) {
                                List<Secteur> path = new ArrayList<>();
                                for (Secteur secteur = neighbor; secteur != null; secteur = pathMap.get(secteur)) {
                                    path.add(secteur);
                                }
                                Collections.reverse(path);
                                return path; // On retourne le chemin
                            }
                        }
                    }
                }
                // On prend le cas du dernier depot vers un entrepot
                else if (!findMine && neighbor == EntrepotProche(robot, entrepots).getSecteur()
                        || (neighbor != null && !neighbor.getCellule(0, 0).equals("X") && !neighbor.getCellule(1, 0).equals("R"))) {
                    // Alors on calcule la distance alternative
                    int alt = dist[current.getLigne()][current.getColonne()] + 1;

                    // Si cette distance est plus courte que la distance enrégistrée
                    if (alt < dist[neighbor.getLigne()][neighbor.getColonne()]) {
                        // On met à jour la distance
                        dist[neighbor.getLigne()][neighbor.getColonne()] = alt;
                        queue.add(neighbor);
                        pathMap.put(neighbor, current);

                        // Si le voisin est une destination
                        if (neighbor == EntrepotProche(robot, entrepots).getSecteur()) {
                            List<Secteur> path = new ArrayList<>();
                            for (Secteur secteur = neighbor; secteur != null; secteur = pathMap.get(secteur)) {
                                path.add(secteur);
                            }
                            Collections.reverse(path);
                            return path; // On retourne le chemin
                        }
                    }
                }
            }
        }
        return null;
    }

    public static String getDirectionRobot(Robot robot, Grille grille, Mine[] mines, Entrepot[] entrepots) {
        // On recupere le chemin le plus court grace a l'algorithme de Dijkstra
        List<Secteur> path = dijkstra(robot, grille, robot.getQuantite() < robot.getCapacite(), mines, entrepots);
        if (path != null && path.size() > 1) {  // Si le chemin existe et contient plus d'un secteur car le premier est le secteur actuel
            Secteur next = path.get(1);         // On recupere le prochain secteur et on retourne la direction pour s'y rendre
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
        // retourner un random si le robot est bloqué entre H et B ou G et D
        Random rand = new Random();
        switch(rand.nextInt(4)) {
            case 0:
                return "H";
            case 1:
                return "B";
            case 2:
                return "G";
            default:
                return "D";
        }
    }

    public static String Visite(Robot robot, Grille grille, Mine[] mines, Entrepot[] entrepots) {
        // Si un secteur voisin n'est pas connu, on se dirige vers lui
        if (VisiteBrouillard(robot, grille) != null) {
            return VisiteBrouillard(robot, grille);
        }

        // Sinon, on se déplace aléatoirement
        Random rand = new Random();
        switch(rand.nextInt(4)) {
            case 0:
                return "H";
            case 1:
                return "B";
            case 2:
                return "G";
            default:
                return "D";
        }
    }

    public static String VisiteBrouillard(Robot robot, Grille grille) {
        Secteur current = robot.getSecteur();
        Secteur[] neighbors = grille.getNeighbors(current);
        for (Secteur neighbor : neighbors) {
            if(neighbor != null && !neighbor.getCellule(0, 0).equals("X") && !neighbor.getCellule(1, 0).equals("R")) {
                Secteur[] neighborsNeighbor = grille.getNeighbors(neighbor);
                for (Secteur neighborNeighbor : neighborsNeighbor) {
                    if (neighborNeighbor != null && !grille.estConnu(neighborNeighbor)) {
                        if (neighbor.getLigne() < current.getLigne()) {
                            return "H";
                        } else if (neighbor.getLigne() > current.getLigne()) {
                            return "B";
                        } else if (neighbor.getColonne() < current.getColonne()) {
                            return "G";
                        } else if (neighbor.getColonne() > current.getColonne()) {
                            return "D";
                        }
                    }
                }
            }
        }
        return null;
    }

    private static Entrepot EntrepotProche(Robot robot, Entrepot[] entrepots) {
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

    private static Mine MineProche(Robot robot, Mine[] mines) {
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

    private static boolean BrouillardRestant(Grille grille) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (!grille.estConnu(grille.getSecteur(i, j))) {
                    return true;
                }
            }
        }
        return false;
    }
}
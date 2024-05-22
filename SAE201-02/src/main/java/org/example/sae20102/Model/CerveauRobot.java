package org.example.sae20102.Model;

public class CerveauRobot {
    // Si un robot est vide, il doit se diriger vers la mine de son minerai la plus proche
    // Si un robot est plein, il doit se diriger vers l'entrepot de son minerai le plus proche
    // Si il est sur une case avec une mine, il doir l'extraire
    // Si il est sur une case avec un entrepot, il doit decharger

    public static String getDirectionRobot(Robot robot, Grille grille) {
        String direction = "";

        int rdm = (int) (Math.random() * 4);
        if ( rdm == 0 ) {
            direction = "H";
        } else if ( rdm == 1 ) {
            direction = "B";
        } else if ( rdm == 2 ) {
            direction = "G";
        } else {
            direction = "D";
        }

        return direction;
    }

    public static String getReponseRobot(Robot robot, Object type) {
        if ( type.getClass().getName().equals("org.example.sae20102.Model.Mine") ) {
            if ( robot.getQuantite() == 0 ) {
                return "O";
            } else {
                return "N";
            }
        } else {
            if ( robot.getQuantite() == robot.getCapacite() ) {
                return "O";
            } else {
                return "N";
            }
        }
    }
}

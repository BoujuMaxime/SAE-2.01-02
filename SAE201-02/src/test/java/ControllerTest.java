
import org.example.sae20102.*;
import org.example.sae20102.Model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ControllerTest {

    public void GridCreation() {
        Controller controller = new Controller();
        Grille grille = controller.getGrille();


    }

    @Test
    public void dontMoveRobotInWater() {
        Controller controller = new Controller();
        Grille grille = controller.getGrille();
        Robot robot = controller.getRobots()[0];

        grille.addEau(0, 0);
        grille.addRobot(robot, 1, 0);

        assertFalse(controller.MoveRobot(robot, "H"));
    }

    @Test
    public void moveRobot() {
        Controller controller = new Controller();
        Grille grille = controller.getGrille();
        Robot robot = controller.getRobots()[0];
        Secteur secteur = grille.getSecteur(0, 0);

        grille.addRobot(robot, 0, 0);


        assertTrue(controller.MoveRobot(robot, "B"));
    }

    @Test
    public void fillRobot() {
        Controller controller = new Controller();
        Grille grille = controller.getGrille();
        Secteur secteur = grille.getSecteur(0, 0);
        Robot robot = new Robot(TypeM.OR, "0", secteur);
        Mine mine = new Mine(TypeM.OR, "0", secteur);

        assertTrue(controller.FillRobot(robot, mine));
    }

    @Test
    public void dontFillRobotIfDifferentMineral() {
        Controller controller = new Controller();
        Grille grille = controller.getGrille();
        Secteur secteur = grille.getSecteur(0, 0);
        Robot robot = new Robot(TypeM.OR, "0", secteur);
        Mine mine = new Mine(TypeM.NICKEL, "0", secteur);

        assertFalse(controller.FillRobot(robot, mine));
    }


}

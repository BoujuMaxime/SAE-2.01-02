
import org.example.sae20102.*;
import org.example.sae20102.Model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ControllerTest {

    public void GridCreation() {
        Controller controller = new Controller();
        Grille grille = controller.getGrille();

        int nbMines = controller.getNbMines();
        int nbEntrepots = controller.getNbEntrepots();
        int nbRobots = controller.getNbRobots();

        assertEquals(nbMines, controller.getMines().length);
        assertEquals(nbEntrepots, controller.getEntrepots().length);
        assertEquals(nbRobots, controller.getRobots().length);
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
    public void dontMoveRobotIfRobotAlreadyThere() {
        Controller controller = new Controller();
        Grille grille = controller.getGrille();
        Robot robot1 = controller.getRobots()[0];
        Robot robot2 = controller.getRobots()[1];

        grille.addRobot(robot1, 0, 0);
        grille.addRobot(robot2, 1, 0);

        assertFalse(controller.MoveRobot(robot1, "H"));
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

    @Test
    public void dontFillRobotIfFull() {
        Controller controller = new Controller();
        Grille grille = controller.getGrille();
        Secteur secteur = grille.getSecteur(0, 0);
        Robot robot = new Robot(TypeM.OR, "0", secteur);
        Mine mine = new Mine(TypeM.OR, "0", secteur);
        robot.setQuantite(robot.getCapacite());


        assertFalse(controller.FillRobot(robot, mine));
    }

    @Test
    public void unloadRobot() {
        Controller controller = new Controller();
        Grille grille = controller.getGrille();
        Secteur secteur = grille.getSecteur(0, 0);
        Robot robot = new Robot(TypeM.OR, "0", secteur);
        Entrepot entrepot = new Entrepot(TypeM.OR, "0", secteur);

        robot.setQuantite(10);

        assertTrue(controller.UnloadRobot(robot, entrepot));
    }

    @Test
    public void dontUnloadRobotIfDifferentMineral() {
        Controller controller = new Controller();
        Grille grille = controller.getGrille();
        Secteur secteur = grille.getSecteur(0, 0);
        Robot robot = new Robot(TypeM.OR, "0", secteur);
        Entrepot entrepot = new Entrepot(TypeM.NICKEL, "0", secteur);

        robot.setQuantite(10);

        assertFalse(controller.UnloadRobot(robot, entrepot));
    }
}
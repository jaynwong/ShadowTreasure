import bagel.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;


/**
 * An example Bagel game.
 */
public class ShadowTreasure extends AbstractGame {

    private final Image background = new Image("res/images/background.png");
    private static final int POS_X = 1;
    private static final int POS_Y = 2;
    private static final int POS_ENERGY = 3;
    private static final int TICK = 10;
    private int frame = 0;
    private Player player;
    private Entity sandwich, zombie;
    private boolean sandwichEaten, zombieEaten = false;


    // for rounding double number; use this to print the location of the player
    private static DecimalFormat df = new DecimalFormat("0.00");

    public static void printInfo(double x, double y, int e) {
        System.out.println(df.format(x) + "," + df.format(y) + "," + e);
    }

    public ShadowTreasure() throws IOException {
        this.loadEnvironment("res/IO/environment.csv");
    }


    /**
     * Load from input file
     * Code adapted from Lecture 7
     */
    private void loadEnvironment(String filename){

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String data;

            while ((data = br.readLine()) != null) {

                // extracting coordinates from data
                double x = Double.parseDouble(data.split(",")[POS_X]);
                double y = Double.parseDouble(data.split(",")[POS_Y]);

                // assigning coordinates to respective classes and constructors
                if (data.contains("Player")) {
                    int initialEnergy = Integer.parseInt(data.split(",")[POS_ENERGY]);
                    player = new Player(x, y, initialEnergy);
                }
                else if (data.contains("Sandwich")) {
                    sandwich = new Sandwich(x, y);
                }
                else {
                    // can only be zombie
                    zombie = new Zombie(x, y);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Algorithm 1 implementation
     */
    private void algoOne () {
        if (zombie.meetEntity(player.getPosition())) {

            // prevent re-eating sandwich and re-adding energy level
            if (!zombieEaten) {
                player.energyReduce();
            }
            zombieEaten = true;
            Window.close();
        }

        if (sandwich.meetEntity(player.getPosition())) {

            // prevent re-eating sandwich and re-adding energy level
            if (!sandwichEaten) {
                player.energyRaise();
            }
            sandwichEaten = true;
        }
    }


    /**
     * Performs a state update.
     */
    @Override
    public void update(Input input) {
        // update along with tick "simulation changes every 10 frames = 1 tick" except background
        background.drawFromTopLeft(0,0);
        player.drawEnergy();
        zombie.drawImage();

        // only print sandwich if it has not been eaten
        if (!sandwichEaten) {
            sandwich.drawImage();
        }

        if (frame%TICK==0) {
            // drawing all entities when a tick occurs
            algoOne();
            player.drawImage();
            player.printMovement();

            // strategy to approach entity
            player.approachEntity(zombie.getPosition(), sandwich.getPosition());

        }
        else {
            // continue to display retained energy level
            player.drawImage();
        }
        player.drawEnergy();
        frame++;
    }


    /**
     * The entry point for the program.
     */
    public static void main(String[] args) throws IOException {
        ShadowTreasure game = new ShadowTreasure();
        game.run();
    }
}
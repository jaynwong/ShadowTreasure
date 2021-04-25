import bagel.DrawOptions;
import bagel.Font;
import bagel.Image;
import bagel.util.Point;


public class Player extends Entity {

    private static final double ENERGY_X = 20;
    private static final double ENERGY_Y = 760;
    private static final int ENERGY_REDUCE = 3;
    private static final int ENERGY_RAISE = 5;
    private static final int FONT_SIZE = 20;
    private static final int STEP_SIZE = 10;
    private static final double RGB = 0.0;
    private final Image player = new Image("res/images/player.png");
    private int energyLevel;
    private double playerDirectionX, playerDirectionY;


    /**
     * constructor for x and y coordinates
     */
    public Player(double x, double y, int energyLevel){
        super(x, y);
        this.energyLevel = energyLevel;
    }


    /**
     * Draws player entity
     */
    @Override
    protected void drawImage () {
        player.draw(x, y);
    }


    /**
     * Draws energy level of player
     */
    protected void drawEnergy () {
        Font font = new Font("res/font/DejaVuSans-Bold.ttf", FONT_SIZE);
        DrawOptions color = new DrawOptions();
        color.setBlendColour(RGB, RGB, RGB);
        font.drawString("energy level: " + energyLevel,ENERGY_X, ENERGY_Y, color);

    }

    /**
     * Updates energy level when player meets sandwich
     */
    protected void energyRaise() {
        energyLevel += ENERGY_RAISE;
    }


    /**
     * Updates energy level when player meets zombie
     */
    protected void energyReduce() {
        energyLevel -= ENERGY_REDUCE;
    }


    /**
     * Strategy to approach entity
     */
    protected void approachEntity(Point zombiePos, Point sandwichPos) {
        if (energyLevel >= ENERGY_REDUCE) {
            // move to zombie
            setPlayerDirectionTo(zombiePos, new Point(x,y));
            x += STEP_SIZE * playerDirectionX;
            y += STEP_SIZE * playerDirectionY;
        } else {
            // move towards sandwich
            setPlayerDirectionTo(sandwichPos, new Point (x,y));
            x += STEP_SIZE * playerDirectionX;
            y += STEP_SIZE * playerDirectionY;
        }
    }


    /**
     * Deciding the direction of player
     * adapted from workshop 4 solutions
     */
    private void setPlayerDirectionTo(Point Dest, Point PlayerPos) {
        double Len = PlayerPos.distanceTo(Dest);
        playerDirectionX = (Dest.x - x) / Len;
        playerDirectionY = (Dest.y - y) / Len;
    }


    /**
     * Prints updated movement of player
     */
    protected void printMovement () {
        ShadowTreasure.printInfo(x, y, energyLevel);
    }
}

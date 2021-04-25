import bagel.util.Point;

public class Entity {

    protected double x, y;
    private static final int DISTANCE = 50;

    /**
     * Constructor for coordinates of all entities
     */
    public Entity(double x, double y) {
        this.x = x;
        this.y = y;
    }


    /**
     * Draw entities
     */
    protected void drawImage () { }


    /**
     * Gets the current position of entities
     */
    protected Point getPosition() {
        Point position = new Point(x, y);
        return position;
    }


    /**
     * Checks if player meets other entities
     */
    protected boolean meetEntity(Point playerPos) {
        boolean meet = false;
        Point location = new Point(x, y);
        if (location.distanceTo(playerPos) < DISTANCE) {
            meet = true;
        }
        return meet;
    }
}

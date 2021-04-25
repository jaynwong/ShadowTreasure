import bagel.Image;

public class Zombie extends Entity {

    private Image zombie = new Image("res/images/zombie.png");
    public Zombie(double x, double y) {
        super(x, y);
    }

    @Override
    protected void drawImage() {
        zombie.draw(x, y);
    }
}

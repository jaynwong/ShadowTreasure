import bagel.Image;

public class Sandwich extends Entity {

    private Image sandwich = new Image("res/images/sandwich.png");
    public Sandwich(double x, double y) {
        super(x, y);
    }

    @Override
    protected void drawImage () {
        sandwich.draw(x, y);
    }

}

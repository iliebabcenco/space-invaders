public class Bomb extends BaseObject{
    public Bomb(double x, double y) {
        super(x, y, 1);
    }
    @Override
    public void move() {
        y ++;
    }
    @Override
    public void draw(Canvas canvas) {
        canvas.setPoint(x, y,'B');
    }
}

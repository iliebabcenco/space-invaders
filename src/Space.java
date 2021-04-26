import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Space {
    private int width;
    private int height;
    private SpaceShip ship;
    private List<Ufo> ufos = new ArrayList<>();
    private List<Rocket> rockets = new ArrayList<>();
    private List<Bomb> bombs = new ArrayList<>();
    public static Space game;
    public static void main(String[] args) {

    }

    public Space(int width, int height) {
        this.width = width;
        this.height = height;
    }



    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public SpaceShip getShip() {
        return ship;
    }

    public List<Ufo> getUfos() {
        return ufos;
    }

    public List<Rocket> getRockets() {
        return rockets;
    }

    public List<Bomb> getBombs() {
        return bombs;
    }

    public void setShip(SpaceShip ship) {
        this.ship = ship;
    }

    public void run() {
        Canvas canvas = new Canvas(width, height);

        KeyboardObserver keyboardObserver = new KeyboardObserver();
        keyboardObserver.start();

        while (ship.isAlive()) {
            if (keyboardObserver.hasKeyEvents()) {
                KeyEvent event = keyboardObserver.getEventFromTop();
                System.out.print(event.getKeyCode());
                if (event.getKeyCode() == KeyEvent.VK_LEFT)
                    ship.moveLeft();
                else if (event.getKeyCode() == KeyEvent.VK_RIGHT)
                    ship.moveRight();
                else if (event.getKeyCode() == KeyEvent.VK_SPACE)
                    ship.fire();
            }

            moveAllItems();

            checkBombs();
            checkRockets();
            removeDead();

            createUfo();

            canvas.clear();
            draw(canvas);
            canvas.print();

            Space.sleep(300);
        }

        System.out.println("Game Over!");
    }

    public void moveAllItems() {
        List<BaseObject> list = this.getAllItems();
        for (BaseObject baseObject : list) {
            baseObject.move();
        }
    }

    public List<BaseObject> getAllItems() {
        List<BaseObject> baseObjects = new ArrayList<>();
        baseObjects.add(this.ship);
        baseObjects.addAll(this.bombs);
        baseObjects.addAll(this.rockets);
        baseObjects.addAll(this.ufos);
        return baseObjects;
    }

    public void createUfo() {
        if (ufos.isEmpty()) {
            Ufo ufo = new Ufo((float) width/2, 0);
        }
    }

    public void checkBombs() {
        for (Bomb bomb: bombs) {
            if (bomb.isIntersect(ship)) {
                bomb.die();
                ship.die();
            }
            if (bomb.getY() > height) {
                bomb.die();
            }
        }
    }

    public void checkRockets() {

        for (Ufo ufo: ufos) {
            for (Rocket rocket: rockets) {
                if (rocket.isIntersect(ufo)) {
                    rocket.die();
                    ufo.die();
                }
                if (rocket.getY() < 0) {
                    rocket.die();
                }
            }
        }
    }

    public void removeDead() {
        ufos.removeIf(ufo -> !ufo.isAlive());
        rockets.removeIf(rocket -> !rocket.isAlive());
        bombs.removeIf(bomb -> !bomb.isAlive());
    }
    public void draw(Canvas canvas) {
        //тут нужно отрисовать все объекты игры
    }

    public static void sleep(int delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException ignored) {
        }
    }
}

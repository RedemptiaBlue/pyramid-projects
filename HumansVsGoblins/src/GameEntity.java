public class GameEntity {
    protected String name;
    protected Coordinate locale;
    protected int health = 100;
    protected char symbol;

    public Coordinate getLocale() {
        return locale;
    }
    public int getHealth() {
        return health;
    }
    public  char getSymbol() {
        return symbol;
    }

    public void setHealth(int h) {
        this.health = h;
    };

    public void moveNorth() {
        this.locale.set(this.locale.getX(), this.locale.getY() - 1);
    }
    public void moveSouth() {
        this.locale.set(this.locale.getX(), this.locale.getY() + 1);
    }
    public void moveEast() {
        this.locale.set(this.locale.getX() + 1, this.locale.getY());
    }
    public void moveWest() {
        this.locale.set(this.locale.getX() - 1, this.locale.getY());
    }

    public String attack(GameEntity e) {
        String result = null;
        boolean hit = (int)-Math.floor(Math.random() * 2) == 0;
        if (hit) {
            e.setHealth(e.getHealth() - 10);
            result = "Hit!";
        } else {
            result = "Miss!";
        }
        return result;
    }
}

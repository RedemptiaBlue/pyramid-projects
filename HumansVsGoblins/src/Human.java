public class Human extends GameEntity{
    @Override
    public String toString() {
        return "Human{" +
                "locale=" + locale +
                ", health=" + health +
                '}';
    }

    public Human(Coordinate c) {
        this.name = "human";
        this.locale = c;
        this.symbol = 'H';
    }
}

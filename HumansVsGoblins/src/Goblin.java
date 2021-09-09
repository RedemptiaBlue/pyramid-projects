public class Goblin extends GameEntity{
    @Override
    public String toString() {
        return "Goblin{" +
                "locale=" + locale +
                ", health=" + health +
                '}';
    }

    public Goblin(Coordinate c) {
        this.name = "goblin";
        this.locale = c;
        this.symbol = 'G';
    }
}

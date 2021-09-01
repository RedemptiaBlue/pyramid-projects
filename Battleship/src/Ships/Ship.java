package Ships;

public abstract class Ship{
    String name;
    char acronym;
    int length;
    Coordinates coord;
    boolean vert;

    public char getAcronym() {
        return this.acronym;
    }

    public int getLength() {
        return this.length;
    }

    public int getX() {
        return this.coord.getX();
    }

    public int getY() {
        return this.coord.getY();
    }

    public void setCoord(int x, int y) {
        this.coord = new Coordinates(x, y);
    }

    public void setCoord(Coordinates c) {
        this.coord = c;
    }

    public void setVert(boolean vert) {
        this.vert = vert;
    }

    public boolean isVert() {
        return this.vert;
    }

    public String getName() {
        return this.name;
    }
}

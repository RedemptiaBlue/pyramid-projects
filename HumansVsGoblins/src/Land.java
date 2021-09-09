import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Land {
    private ArrayList<ArrayList<Character>> grid;

    void setGrid(HashMap<String, GameEntity> entities) {
        grid = this.instantiateGrid();
        for (GameEntity e : entities.values()) {
            int x = e.getLocale().getX();
            int y = e.getLocale().getY();
            grid.get(y).set(x, e.getSymbol());
        }
    }

    public ArrayList<ArrayList<Character>> getGrid() {
        return this.grid;
    }

    ArrayList<ArrayList<Character>> instantiateGrid() {
        return new ArrayList<ArrayList<Character>>(){{
            for (int i = 0; i < 9; i++) {
                ArrayList<Character> row = new ArrayList<>(){{
                    for (int j = 0; j < 9; j++) {
                        add('~');
                    }
                }};
                add(row);
            }
        }};
    }

    void printGrid() {
        StringBuilder topRow = new StringBuilder();
        topRow.append("\n   ");
        for (int i = 0; i < 9; i++) {
            topRow.append("  ").append (i);
        }
        System.out.println(topRow);
        int i = 0;
        for (ArrayList<Character> r : grid) {
            StringBuilder rowString = new StringBuilder();
            rowString.append(i).append("  ");
            for (char tile : r) {
                rowString.append("  ").append(tile);
            }
            System.out.println(rowString);
            i++;
        }
        System.out.println("\n");
    }

    public Land() {
        this.grid = instantiateGrid();
    }
}

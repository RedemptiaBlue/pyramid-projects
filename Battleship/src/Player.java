import Ships.*;

import java.util.ArrayList;

public class Player {


    private String name;

    private ArrayList<ArrayList<Character>> playerBoard;
    private ArrayList<ArrayList<Character>> enemyBoard;

    public Ship[] ships;

    private int hitsLeft;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<ArrayList<Character>> getPlayerBoard() {
        return this.playerBoard;
    }

    public ArrayList<ArrayList<Character>> getEnemyBoard() {
        return this.enemyBoard;
    }

    public void setShipCoord(int x, int y, Ship ship, boolean vert){
        char c = ship.getAcronym();
        this.playerBoard.get(x).set(y, c);
        if (vert) {
            for (int i = y; i < ship.getLength(); i++) {

            }
        }
    }

    public void addHit() {
        this.hitsLeft = this.hitsLeft - 1;
    }

    public int getHitsLeft() {
        return this.hitsLeft;
    }

    public Player(String name) {
        this.name = name;
        this.playerBoard = new ArrayList<ArrayList<Character>>(){{
            for (int i = 0; i < 9; i++) {
                ArrayList<Character> row = new ArrayList<>(){{
                    for (int j = 0; j < 9; j++) {
                        add('~');
                    }
                }};
                add(row);
            }
        }};
        this.enemyBoard = new ArrayList<ArrayList<Character>>(){{
            for (int i = 0; i < 9; i++) {
                ArrayList<Character> row = new ArrayList<>(){{
                    for (int j = 0; j < 9; j++) {
                        add('~');
                    }
                }};
                add(row);
            }
        }};
        this.hitsLeft = 17;
        this.ships = new Ship[]{new Carrier(), new BattleshipShip(), new Destroyer(), new Submarine(), new PatrolBoat()};
    };
}

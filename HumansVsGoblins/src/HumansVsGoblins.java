import java.security.KeyStore;
import java.util.*;

public class HumansVsGoblins {
    static Scanner s = new Scanner(System.in);
    static HashMap<String, GameEntity> entities = new HashMap<>();
    static Land l;

    static void addGoblin(String name) {
        entities.put(name, new Goblin(new Coordinate(4,0)));
    }

    static void addHuman(String name) {
        entities.put(name, new Human(new Coordinate(4,8)));
    }

    static void promptMove(GameEntity mover) {
        boolean valid = false;
        while (!valid) {
            String i = "";
            if (mover.getSymbol() == 'H') {
                System.out.println("Which way to move? (n/s/e/w):");
                i = s.nextLine();
            } else {
                switch ((int)Math.floor(Math.random()*  4)) {
                    case 0:
                        i = "n";
                        break;
                    case 1:
                        i = "s";
                        break;
                    case 2:
                        i = "e";
                        break;
                    case 3:
                        i = "w";
                        break;
                }
            }
            if (i.equals("n") || i.equals("s") || i.equals("e") || i.equals("w")) {
                int plusX = 0;
                int plusY = 0;
                switch (i) {
                    case "n":
                        plusY -= 1;
                        break;
                    case "s":
                        plusY += 1;
                        break;
                    case "e":
                        plusX += 1;
                        break;
                    case "w":
                        plusX -= 1;
                        break;
                }
                Coordinate locationToMove = new Coordinate(mover.getLocale().getX() + plusX, mover.getLocale().getY() + plusY);
                try {
                    char LocationToMoveChar = l.getGrid().get(locationToMove.getY()).get(locationToMove.getX());
                    if (LocationToMoveChar != '~') {
                        for (GameEntity e : entities.values()) {
                            if (e.getLocale().equals(locationToMove) && e.getSymbol() != mover.getSymbol()) {
                                System.out.printf("%s went for an attack on %s. It was a %s!\n",mover.name, e.name, mover.attack(e));
                                System.out.printf("%s has %s health left.\n", e.name, e.getHealth());
                            }
                        }
                    } else {
                        switch (i) {
                            case "n":
                                mover.moveNorth();
                                break;
                            case "s":
                                mover.moveSouth();
                                break;
                            case "e":
                                mover.moveEast();
                                break;
                            case "w":
                                mover.moveWest();
                                break;
                        }
                    }
                    l.setGrid(entities);
                    l.printGrid();
                    valid = true;
                } catch (Exception e) {
                    if (mover.getSymbol() == 'H') {
                        System.out.println("Out of Bounds");
                    }
                }
            }else {
            System.out.println("Invalid input");
            }
        }
    }

    static String checkDead() {
        String r = "none";
        for (String e : entities.keySet()) {
            if (entities.get(e).health <= 0) {
                r = e;
            }
        }
        return r;
    }

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            l = new Land();
            System.out.println("-------------------");
            System.out.println("Humans Vs Zombies");
            System.out.println("-------------------");
            addGoblin("g1");
            addHuman("h1");
            l.setGrid(entities);
            l.printGrid();

            System.out.println("You're the Human.");
            boolean gameRunning = true;
            while (gameRunning) {
                for (GameEntity e : entities.values()){
                    promptMove(e);
                    for (String p : entities.keySet()) {
                        if (checkDead().equals(p)) {
                            System.out.printf("%s is dead", entities.get(p).name);
                            gameRunning = false;
                        }
                    }
                }
            }


            boolean valid = false;
            while(!valid) {
                System.out.println("Play Again? (yes/no)(y/n)");
                String i = s.nextLine();
                if (i.equals("yes") || i.equals("y")){
                    valid = true;
                } else if (i.equals("no") || i.equals("n")){
                    valid = true;
                    exit = true;
                } else {
                    System.out.println("Invalid input");
                }
            }
        }
    }
}

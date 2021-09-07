import Ships.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Battleship {
    static Scanner sc = new Scanner(System.in);


    static void displayBoard(ArrayList<ArrayList<Character>> board) {
        System.out.println("\n   1 2 3 4 5 6 7 8 9");
        int i = 1;
        for (ArrayList<Character> row : board) {
            StringBuilder rowString = new StringBuilder();
            rowString.append(i).append(" ");
            for (char tile : row) {
                rowString.append(" ").append(tile);
            }
            System.out.println(rowString);
            i++;
        }
        System.out.println("\n");
    }

    static Coordinates selectCoord(){
        Pattern tuple = Pattern.compile("\\(([1-9]+),([1-9]+)\\)");
        Coordinates parsed = null;
        try {
            Matcher matcher = tuple.matcher(sc.nextLine());
            if (matcher.find()) {
                parsed = new Coordinates(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("Invalid Input");
        }
        return parsed;
    }

    static void placeShipsFor(Player p) {
        displayBoard(p.getPlayerBoard());
        System.out.printf("%s, please enter the coordinates for your ships.\n", p.getName());
        for (Ship s : p.ships) {
            boolean placementisValid = false;
            while (!placementisValid){
                boolean coordIsValid = false;
                while (!coordIsValid) {
                    System.out.printf("Enter the coordinates for the %s: ", s.getName());
                    Coordinates coords = selectCoord();
                    if (coords != null) {
                        try {
                            char SelectedTile = p.getPlayerBoard().get(coords.getY() - 1).get(coords.getX() - 1);
                            if ((SelectedTile == '~')) {
                                s.setCoord(coords);
                                coordIsValid = true;
                            } else {
                                String ship = "";
                                switch (SelectedTile) {
                                    case 'c':
                                        ship = "Carrier";
                                        break;
                                    case 'b':
                                        ship = "Battleship";
                                        break;
                                    case 'd':
                                        ship = "Destroyer";
                                        break;
                                    case 'p':
                                        ship = "Patrol Boat";
                                        break;
                                    case 's':
                                        ship = "Submarine";
                                        break;

                                }
                                System.out.printf("%s is already located there, enter another location.\n", ship);
                            }
                        } catch (Exception e) {
                            System.out.println("Coordinates out of bounds");
                        }
                    }
                }
                System.out.println("Place horizontally or vertically (h or v)");
                try {
                    String vert = sc.nextLine();
                    Matcher m = (Pattern.compile("[vh]")).matcher(vert);
                    if (m.find()) {
                        switch (vert) {
                            case "v":
                                if ((s.getY() - 1) + (s.getLength()) < 10) {
                                    placementisValid = true;
                                    s.setVert(true);
                                    for (int i = 0; i < s.getLength(); i++) {
                                        p.getPlayerBoard().get(s.getY() - 1 + i).set(s.getX() - 1, s.getAcronym());
                                    }
                                } else {
                                    System.out.println("Ship placement is out of bounds.");
                                    sc.close();
                                }
                                break;
                            case "h":
                                if ((s.getX() - 1) + (s.getLength()) < 10) {
                                    placementisValid = true;
                                    s.setVert(false);
                                    for (int i = 0; i < s.getLength(); i++) {
                                        p.getPlayerBoard().get(s.getY() - 1).set(s.getX() - 1 + i, s.getAcronym());
                                    }
                                } else {
                                    System.out.println("Ship placement is out of bounds.");
                                }
                                break;
                        }
                    } else {
                        throw new Exception();
                    }
                } catch (Exception e ){
                    System.out.println("Invalid input");
                }
            }
            displayBoard(p.getPlayerBoard());
        }
        System.out.println("------------------------");
    }

    static boolean checkWinFor(Player p) {
        return p.getHitsLeft() == 0;
    }

    static void runTurn(Player attacker, Player defender) {
        boolean attackIsValid = false;
        while (!attackIsValid) {
            displayBoard(attacker.getEnemyBoard());
            System.out.printf("%s, enter the coordinates for your next attack: ", attacker.getName());
            Coordinates coords = selectCoord();
            if (coords != null) {
                char tileToAttack = defender.getPlayerBoard().get(coords.getY() - 1).get(coords.getX() - 1);
                char tileToMark = attacker.getEnemyBoard().get(coords.getY() - 1).get(coords.getX() - 1);
                if (tileToMark != '~'){
                    System.out.println("You've already attacked this tile");
                } else {
                    attackIsValid = true;
                    if (tileToAttack != '~') {
                        attacker.addHit();
                        System.out.println("It's a hit!");
                        attacker.getEnemyBoard().get(coords.getY() - 1).set(coords.getX() - 1, 'x');
                    } else {
                        System.out.println("You missed!");
                        attacker.getEnemyBoard().get(coords.getY() - 1).set(coords.getX() - 1, 'm');
                    }
                    displayBoard(attacker.getEnemyBoard());
                }
            }

        }
        System.out.println("------------------------");
    }
    static String getNameInput() {
        String input = null;
        boolean valid = false;
        while (!valid){
            try {
                String i = sc.nextLine();
                if (i.equals("")) {
                    throw new Exception();
                }
                valid = true;
                input = i;
            } catch (Exception e) {
                System.out.println("Input cannot be blank");
            }
        }
        return input;
    }

    static void runGame() {

        System.out.println("Battleship Multiplayer");
        System.out.print("Enter player 1 name: ");
        Player player1 = new Player(getNameInput());
        System.out.print("Enter player 2 name: ");
        Player player2 = new Player(getNameInput());
        placeShipsFor(player1);
        placeShipsFor(player2);
        boolean winner = false;
        Player attacker = player1;
        Player defender = player2;
        while (!winner) {
            runTurn(attacker, defender);
            winner = checkWinFor(attacker);
            if (winner) {
                System.out.printf("%s has won!\n", attacker.getName());
            }
            if (attacker.equals(player1)) {
                attacker = player2;
                defender = player1;
            } else {
                attacker = player1;
                defender = player2;
            }
        }
    }

    public static void main(String[] args) {
        boolean exit = false;
        if (!exit) {
            runGame();
            System.out.println("Play again? (y / n)");
            String input = sc.nextLine();
            Matcher exitM = (Pattern.compile("[yn]")).matcher(input);
            if (exitM.find()) {
                if (input.equals("n")) {
                    exit = true;
                }
            }
        }
    }
}

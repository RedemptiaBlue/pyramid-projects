import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NoughtsAndCrosses {
    static final Scanner sc = new Scanner(System.in);
    static ArrayList<String> tiles = new ArrayList<>();
    static boolean gameRunning;
    static String player;
    static String computer;
    static ArrayList<Integer> playerTiles = new ArrayList<>();
    static ArrayList<Integer> computerTiles = new ArrayList<>();
    static final int[][] winList = {
            {1, 2, 3}, {4, 5 ,6}, {7, 8, 9},
            {1, 4, 7}, {2, 5, 8}, {3, 6, 9},
            {1, 5, 9}, {3, 5, 7}
    };

    static void chooseXOrO() {
        boolean playerNotSet = true;
        while (playerNotSet) {
            String input = sc.nextLine();
            if (input.equals("x") || input.equals("X")) {
                player = "X";
                computer = "O";
                playerNotSet = false;
            } else if (input.equals("o") || input.equals("O")) {
                player = "O";
                computer = "X";
                playerNotSet = false;
            } else {
                System.out.println("Invalid input");
                System.out.println("Do you want to be X or O?");
            }
        }
    }

    static void takeComputerTurn() {
        ArrayList<Integer> choices = new ArrayList<>() {{
            for (int i = 0; i < tiles.size(); i++) {
                if (tiles.get(i).equals(" ")) {
                    add(i);
                }
            }
        }};
        int randomEmptyTile = choices.get((int)Math.floor(Math.random() * choices.size()));
        tiles.set(randomEmptyTile, computer);
        computerTiles.add(randomEmptyTile + 1);
    }

    static void takePlayerTurn() {
        boolean turnNotTaken = true;
        while (turnNotTaken) {
            System.out.println("What is your next move? (1-9)");
            String input = sc.nextLine();
            try {
                int i = Integer.parseInt(input);
                if (tiles.get(i - 1).equals(" ")) {
                    tiles.set(i - 1, player);
                    playerTiles.add(i);
                    turnNotTaken = false;
                } else {
                    System.out.println("That spot is already taken.");
                }

            } catch (Exception NumberFormatException) {
                System.out.println("Please pick a number 1-9");
            }
        }
    }

    static String checkForWin() {
        String winner = "none";
        for (int[] winSeq : winList) {
            int compMatch = 0;
            int playerMatch = 0;
            for(int tile: winSeq) {
                if (playerTiles.contains(tile)) {
                    playerMatch += 1;
                }else if (computerTiles.contains(tile)){
                    compMatch += 1;
                }
            }
            if (compMatch == 3) {
                winner = computer;
                break;
            } else if (playerMatch == 3) {
                winner = player;
                break;
            }
        }
        return winner;
    }

    static boolean checkForDraw() {
        return !tiles.contains(" ");
    }

    static void printBoard() {
        System.out.printf(" %s | %s | %s \n", tiles.get(0), tiles.get(1), tiles.get(2));
        System.out.println("-----------");
        System.out.printf(" %s | %s | %s \n", tiles.get(3), tiles.get(4), tiles.get(5));
        System.out.println("-----------");
        System.out.printf(" %s | %s | %s \n", tiles.get(6), tiles.get(7), tiles.get(8));
    }

    static void runGame() {
        tiles.clear();
        tiles.addAll(List.of(" ", " ", " ", " ", " ", " ", " ", " ", " "));
        playerTiles.clear();
        computerTiles.clear();

        System.out.println("Welcome to Tic-Tac-Toe");

        System.out.println("Do you want to be X or O?");

        chooseXOrO();

        System.out.println("The computer will go first");
        gameRunning = true;
        while (gameRunning) {
            takeComputerTurn();
            printBoard();
            if (checkForWin().equals(computer)) {
                System.out.println("Computer wins!");
                gameRunning = false;
                break;
            };
            if (checkForDraw()) {
                System.out.println("Draw");
                gameRunning = false;
                break;
            }
            takePlayerTurn();
            if (checkForWin().equals(player)) {
                System.out.println("Player wins!");
                gameRunning = false;
                break;
            };

        }
    }

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            runGame();
            System.out.println("Play again? (yes/no)");
            String input = sc.nextLine();
            if (input.equals("no")) {
                exit = true;
            }
        }
        System.out.println("Thanks for playing!");
    }
}

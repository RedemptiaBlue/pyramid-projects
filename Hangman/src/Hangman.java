import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Hangman {
    private static final Scanner sc = new Scanner(System.in);

    private static final ArrayList<String> words = new ArrayList<>() {{
        add("cat");
        add("dog");
        add("apple");
        add("car");
        add("coffee");
    }};
    private static final String[] bodyParts = new String[]{"O", "|", "|", "/", "\\"};
    private static ArrayList<String> missedLetters;
    private static String missed;
    private static ArrayList<String> guessWord;
    private static ArrayList<String> guessedList;
    private static String guessed;
    private static ArrayList<String> body;
    private static int misses;

    private static void setup() {
        missedLetters = new ArrayList<>();
        missed = "";
        guessWord = getGuessedWordLetters();
        guessedList = new ArrayList<>();
        guessed = createEmptyGuessedString();
        misses = 0;
        body = initializeBodyArray();

    }

    private static ArrayList<String> getGuessedWordLetters() {
        return new ArrayList<>() {{
            this.addAll(Arrays.asList(words.get((int) Math.floor(Math.random() * words.size())).split("")));
        }};
    }

    private static String createEmptyGuessedString() {
        for (String x : guessWord) {
            guessedList.add("_");
        }
        return stringifyList(guessedList);
    }

    private static String stringifyList(ArrayList l) {
        StringBuilder s =new StringBuilder();
        for (var item : l) {
            s.append(item);
        }
        return s.toString();
    };

    private static ArrayList<String> initializeBodyArray() {
        return new ArrayList<>() {{
            for (int i = 0; i < 5; i++) {
                add(" ");
            }
        }};
    }

    private static void addBodyPartToGallow(){
        for (int i = 0; i < misses; i++) {
            body.set(i, bodyParts[i]);
        }
    }

    private static void printGallow() {
        System.out.println("\n+---+");
        System.out.printf("%s   |\n", body.get(0));
        System.out.printf("%s   |\n", body.get(1));
        System.out.printf("%s   |\n", body.get(2));
        System.out.printf("%s%s===\n", body.get(3), body.get(4));

        System.out.printf("missed: %s\n ", missed);

        System.out.printf("%s\n", guessed);
    }

    public static boolean wordFound() {
        boolean hasMissingLetter = false;
        for (String l : guessedList) {
            if (l.equals("_")) {
                hasMissingLetter = true;
            }
        }
        return !hasMissingLetter;
    }

    public static boolean playAgainPrompt() {
        System.out.println("You did it! Play again?");
        boolean valid = false;
        String yesNo = null;
        while (!valid){
            try {
                yesNo = sc.nextLine();
                if (!yesNo.equals("yes") && !yesNo.equals("no")) {
                    throw new Exception();
                }
                valid = true;
            } catch (Exception e) {
                System.out.println("Invalid input");
            }
        }
        return yesNo.equals("no");
    }

    public static void checkInput(String input) {
        try {
            if (input.length() != 1) {
                throw new Exception();
            }
            boolean inMissedLetters = false;
            for (var l : missedLetters) {
                if (input.equals(l)) {
                    inMissedLetters = true;
                }
            }
            if (inMissedLetters) {
                System.out.println("You've already chosen that letter.");
            } else {
                boolean match = false;
                for (int i = 0; i < guessWord.size(); i++) {
                    if (input.equals(guessWord.get(i))) {
                        match = true;
                        guessedList.set(i, guessWord.get(i));
                        guessed = stringifyList(guessedList);
                    }
                }
                if (!match) {
                    misses += 1;
                    missedLetters.add(input);
                    missed = stringifyList(missedLetters);
                    addBodyPartToGallow();
                }
            }
        } catch (Exception e) {
            System.out.println("Invalid Input Please Try again.");
        }
    }

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit){
            setup();

            System.out.println("\nH A N G M A N");

            boolean gameRunning = true;
            while (gameRunning) {

                printGallow();

                if (body.get(4).equals(bodyParts[4])) {
                    gameRunning = false;
                    System.out.println("Game Over! Play again?");
                    exit = playAgainPrompt();
                    continue;
                }
                if (wordFound()) {
                    gameRunning = false;
                    exit = playAgainPrompt();
                    continue;
                }

                System.out.println("Guess a letter");

                String input = sc.nextLine();

                checkInput(input);
            }
        }
    }
}

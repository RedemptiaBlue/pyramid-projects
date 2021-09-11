import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Hangman2 {

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
    private static String guessWord;
    private static ArrayList<String> guessedList;
    private static String guessed;
    private static String[] body;
    private static int misses;

    private static void setup() {
        missedLetters = new ArrayList<>();
        guessWord = words.get((int) Math.floor(Math.random() * words.size()));
        guessedList = new ArrayList<>();
        misses = 0;
        body = new String[5];

    }

    static void printState() throws IOException {
        List<String> gallows = Files.readAllLines(Path.of("Hangman2/src/Gallows.txt"));;
        String[] g = gallows.get(misses).split("n");
        Arrays.stream(g).forEach((line) -> {
            System.out.println(line.replace("\\", ""));
        });
        System.out.printf("Missed Letters: %s%n", missed);
        System.out.println(guessed);
    }

    static void guessLetter() {
        Pattern p = Pattern.compile("\\w");
        try {
            System.out.println("Pick a letter:");
            String i = sc.nextLine();
            Matcher m = p.matcher(i);
            if (m.find()){
                String letter = m.group(0);
                if (List.of(guessWord.split("")).contains(letter)) {
                    guessedList.add(letter);
                } else if (missedLetters.contains(letter)) {
                    System.out.println("You've already selected this letter.");
                }
                else {
                    missedLetters.add(letter);
                    misses += 1;
                    body[misses - 1] = bodyParts[misses - 1];
                }
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Try again.");
        }

    }


    public static void main(String[] args) {
        boolean exit = false;
        String mode = "gameStart";
        while (!exit) {
            switch (mode) {
                case "gameStart":
                    setup();
                    System.out.println("\nH A N G M A N");
                    mode = "gameRun";
                    break;
                case "gameRun":
                    missed = missedLetters.stream().reduce("",(a, b)-> a + b);
                    guessed = Arrays.stream(guessWord.split("")).reduce("",(a, b)-> guessedList.contains(b)? a + b: a + "_");
                    try {
                        printState();
                        if (guessed.equals(guessWord)){
                            System.out.println("You Win!");
                            mode = "gameEnd";
                        } else if (Arrays.stream(body).allMatch(Objects::nonNull)) {
                            System.out.println("Game Over.");
                            mode = "gameEnd";
                        } else {
                            guessLetter();
                        }
                    } catch (IOException e) {
                        System.out.println("Gallows not found");
                        exit = true;
                    }
                    break;
                case "gameEnd":
                    System.out.println("Play again?");
                    String i = sc.nextLine();
                    if (i.equals("no") || i.equals("n")) {
                        exit = true;
                    } else if (i.equals("yes") || i.equals("y")) {
                        mode = "gameStart";
                    }
                    break;
            }
        }
    }
}

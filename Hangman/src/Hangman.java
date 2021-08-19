import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Hangman {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<String> words = new ArrayList<>() {{
            add("cat");
            add("dog");
            add("apple");
            add("car");
            add("coffee");
        }};
        boolean exit = false;
        while (!exit){
            ArrayList<String> missedLetters = new ArrayList<>();
            StringBuilder missedString = new StringBuilder();
            for (var l : missedLetters) {
                missedString.append(l);
            }

            ArrayList<String> guessWord = new ArrayList<>() {{
                this.addAll(Arrays.asList(words.get((int) Math.floor(Math.random() * words.size())).split("")));
            }};
            ArrayList<String> guessed = new ArrayList<>();
            for (String l : guessWord) {
                guessed.add("_");
            }
            StringBuilder guessedString = new StringBuilder();
            for (var l : guessed) {
                guessedString.append(l);
            }
            int misses = 0;
            ArrayList<String> body = new ArrayList<>() {{
                for (int i = 0; i < 5; i++) {
                    add(" ");
                }
            }};
            String[] bodyParts = {"O", "|", "|", "/", "\\"};

            boolean gameRunning = true;

            while (gameRunning) {
                System.out.println("\nH A N G M A N");

                System.out.println("+---+");
                System.out.printf("%s   |\n", body.get(0));
                System.out.printf("%s   |\n", body.get(1));
                System.out.printf("%s   |\n", body.get(2));
                System.out.printf("%s%s===\n", body.get(3), body.get(4));

                System.out.printf("missed: %s\n ", missedString);

                System.out.printf("%s\n", guessedString);

                if (body.get(4).equals(bodyParts[4])) {
                    gameRunning = false;
                    System.out.println("Game Over! Play again?");
                    String yesNo = sc.nextLine();
                    if (yesNo.equals("no")) {
                        exit = true;
                    }
                    continue;
                }
                boolean notGuessed = true;
                for (String l : guessed) {
                    if (l.equals("_")){
                        notGuessed = false;
                        break;
                    }
                }
                if (notGuessed) {
                    gameRunning = false;
                    System.out.println("You did it! Play again?");
                    String yesNo = sc.nextLine();
                    if (yesNo.equals("no")) {
                        exit = true;
                    }
                    continue;
                }

                System.out.println("Guess a letter");

                String input = sc.nextLine();

                if (input.length() != 1) {
                    System.out.println("Invalid input Try again");
                }

                boolean inMissedLetters = false;
                for (var l : missedLetters) {
                    if (input.equals(l)) {
                        inMissedLetters = true;
                    }
                }
                if (inMissedLetters) {
                    System.out.println("You've already chosen that letter.");
                }
                else {
                    boolean match = false;
                    for (int i = 0; i < guessWord.size(); i++) {
                        if (input.equals(guessWord.get(i))) {
                            match = true;
                            guessed.set(i, guessWord.get(i));
                            guessedString = new StringBuilder("");
                            for (var l : guessed) {
                                guessedString.append(l);
                            }
                        }
                    }
                    if (!match) {
                        misses += 1;
                        missedLetters.add(input);
                        missedString = new StringBuilder();
                        for (var l : missedLetters) {
                            missedString.append(l);
                        }
                        for (int i = 0; i < misses; i++) {
                            body.set(i, bodyParts[i]);
                        }
                    }
                }
            }
        }
    }
}

import java.util.Scanner;

public class NumberGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nHello! I'm Number Game? And who are you?");
        String name = sc.nextLine();
        System.out.printf("Welcome, %s! \n", name);
        boolean endGame = true;
        while (endGame) {
            System.out.println("I'm thinking of a number between 1 and 20.");
            int num = (int) Math.floor(Math.random() * 20 + 1);
            System.out.println("Take a guess!");
            boolean guessed = false;
            while (!guessed) {
                int guess = Integer.parseInt(sc.nextLine());
                if (guess == num) {
                    System.out.println("Correct! Great job!");
                    guessed = true;
                } else if (guess > num) {
                    System.out.println("Too high!");
                } else {
                    System.out.println("Too low!");
                }
            }
            System.out.println("Game Over");
            System.out.println("Play again? (yes/no)");
            String playAgain = sc.nextLine();
            if (playAgain.equals("no")) {
                endGame = false;
            }
        }
        System.out.printf("Let's play some other time! Bye %s! \n\n", name);
    }
}

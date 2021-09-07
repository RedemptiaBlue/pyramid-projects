import java.util.Scanner;

public class NumberGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nHello! I'm Number Game? And who are you?");
        String name = sc.nextLine();
        System.out.printf("Welcome, %s! \n", name);
        boolean exit = false;
        while (!exit) {
            System.out.println("I'm thinking of a number between 1 and 20.");
            int num = (int) Math.floor(Math.random() * 20 + 1);
            System.out.println("Take a guess!");
            boolean guessed = false;
            while (!guessed) {
                boolean validGuess = false;
                while (!validGuess) {
                    try {
                        int guess = Integer.parseInt(sc.nextLine());
                        if (guess > 0 && guess < 20) {
                            validGuess = true;
                            if (guess == num) {
                                System.out.println("Correct! Great job!");
                                guessed = true;
                            } else if (guess > num) {
                                System.out.println("Too high!");
                            } else {
                                System.out.println("Too low!");
                            }
                        } else {
                            throw new Exception();
                        }
                    } catch (Exception e) {
                        System.out.println("Please enter a valid number. Try Again.");
                    }
                }
            }
            System.out.println("Game Over");
            System.out.println("Play again? (yes/no)");
            boolean validInput = false;
            while (!validInput){
                try {
                    String input = sc.nextLine();
                    if (input.equals("yes") || input.equals("no")) {
                        validInput = true;
                        if (input.equals("no")){
                            exit = true;
                        }
                    } else {
                        throw new Exception();
                    }
                } catch (Exception e) {
                    System.out.println("Invalid Input");
                }
            }
        }
        System.out.printf("Let's play some other time! Bye %s! \n\n", name);
    }
}

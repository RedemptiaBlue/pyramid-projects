import java.util.Scanner;

public class DragonCave {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("You are in a land full of dragons. In front of you,");
        Thread.sleep(1500);
        System.out.println("you see two caves. In one cave, the dragon is friendly");
        Thread.sleep(1500);
        System.out.println("and will share his treasure with you. The other dragon");
        Thread.sleep(1500);
        System.out.println("is greedy and hungry and will eat you on sight");
        Thread.sleep(1500);

        Scanner sc = new Scanner(System.in);
        String input = null;

        boolean badOrNoInput = true;
        while (badOrNoInput) {
            System.out.println("Which cave will you go into? (1 or 2)");
            Thread.sleep(1000);
            input = sc.nextLine();
            if (!input.equals("1") && !input.equals("2")) {
                System.out.println("You only have two choices. Really. Try again.");
                Thread.sleep(1500);
            } else {
                badOrNoInput = false;
            }
        }

        System.out.println("You approach the cave...");
        Thread.sleep(1500);
        System.out.println("It is dark and spooky...");
        Thread.sleep(1500);
        System.out.println("A large dragon jumps out in front of you! He opens his jaws and...");
        Thread.sleep(1500);
        switch (input) {
            case "1":
                System.out.println("gobbles you down in one bite!");
                Thread.sleep(1500);
                System.out.println("You are dead. Game Over");
                break;
            case "2":
                System.out.println("licks your face. He then invites you into his cave.");
                Thread.sleep(1500);
                System.out.println("it's filled halfway to the ceiling with treasure!");
                Thread.sleep(1500);
                System.out.println("The dragon lets you leave with as much as you can carry.");
                Thread.sleep(1500);
                System.out.println("You win! The End");
        }
    }
}

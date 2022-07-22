// Brock Williams and Rayan Amir

import java.util.Scanner;


public class Game {
    public static void main(String[] args) {
        System.out.println("--- Enter 1 for Beginner, 2 for Intermediate, 3 for Expert Mode ---");
        Scanner modeScanner = new Scanner(System.in);
        int mode = modeScanner.nextInt();

        System.out.println("--- Enter 'd' for debug mode, 'b' for Battleboat mode ---");
        Scanner debugScan = new Scanner(System.in);
        String debug = debugScan.next();

        Board board = new Board(mode);
        board.placeBoats();
        Scanner scanner = new Scanner(System.in);

        if (debug.equals("d")) {
            int xPos = 0;
            int yPos = 0;
            while (board.getUnsunkBoats() > 0) {
                board.print();
                board.display();

                System.out.println("Turn: " + board.getTurnCount());
                System.out.println("Enter an X and Y coordinate to fire: " + "\n");
                xPos = scanner.nextInt();
                yPos = scanner.nextInt();


                System.out.println("Firing at " + "(" + xPos + ", " + yPos + ")" + "\n");
                board.fire(xPos, yPos);

                System.out.println("Number of unsunk boats left: " + board.getUnsunkBoats());


            }
            System.out.println("You won in " + board.getTurnCount() + " turns!");
        }
        else if (debug.equals("b")) {
            int xPos = 0;
            int yPos = 0;
            while (board.getUnsunkBoats() > 0) {
                board.print();

                System.out.println("Turn: " + board.getTurnCount());
                System.out.println("Enter an X and Y coordinate to fire: " + "\n");
                xPos = scanner.nextInt();
                yPos = scanner.nextInt();




                System.out.println("Firing at " + "(" + xPos + ", " + yPos + ")" + "\n");
                board.fire(xPos, yPos);

                System.out.println("Number of unsunk boats left: " + board.getUnsunkBoats() + "\n");
            }
            System.out.println("You won in " + board.getTurnCount() + " turns!");
        }
    }
}
// Brock Williams and Rayan Amir

import java.util.Random;

public class Board {
    private int x;
    private int y;
    private int numBoats;
    private int[] boatSizes;
    private int mode;
    private Cell[][] board;
    private BattleBoat[] boats;
    private int turnCount;
    private int unsunkBoats;
    public boolean c;
    public int d;
    private boolean sunk;

    public Board(int mode1) {
        mode = mode1;


        if (mode == 1) {
            this.unsunkBoats = 1;
            this.turnCount = 0;
            this.board = new Cell[3][3];
            this.numBoats = 1;
            this.boats = new BattleBoat[this.numBoats];
            this.boatSizes = new int[this.numBoats];
            boatSizes[0] = 2;
            boats[0] = new BattleBoat(boatSizes[0]);


            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    this.board[i][j] = new Cell(i, j, '-');
                }
            }

            System.out.println("\n" + "Easy mode. Placing 1 boat with a size of 2 for each side.\n");

        } else if (mode == 2) {
            this.unsunkBoats = 3;
            this.turnCount = 0;
            this.board = new Cell[6][6]; // creates board object with 6x6 grid
            this.numBoats = 3;
            this.boats = new BattleBoat[this.numBoats]; // initializes BattleBoats array to fit the number of boats
            this.boatSizes = new int[this.numBoats]; // create array to store boat sizes
            boatSizes[0] = 4;
            boatSizes[1] = 3;
            boatSizes[2] = 2;

            for (int i = 0; i < this.numBoats; i++) { // initializes each boat size to a BattleBoat object
                boats[i] = new BattleBoat(boatSizes[i]);
            }

            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    this.board[i][j] = new Cell(i, j, '-');
                }
            }

            System.out.println("\n" + "Intermediate mode. Placing 3 boats with a size of 2, 3, and 4 for each side.\n");

        } else if (mode == 3) {
            this.unsunkBoats = 5;
            this.turnCount = 0;
            this.board = new Cell[9][9];
            this.numBoats = 5;
            this.boats = new BattleBoat[this.numBoats];
            this.boatSizes = new int[this.numBoats];
            boatSizes[0] = 5;
            boatSizes[1] = 4;
            boatSizes[2] = 3;
            boatSizes[3] = 3;
            boatSizes[4] = 2;

            for (int i = 0; i < this.numBoats; i++) {
                boats[i] = new BattleBoat(boatSizes[i]);
            }


            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    this.board[i][j] = new Cell(i, j, '-');
                }
            }

            System.out.println("\n" + "Expert mode. Placing 3 boats with a size of 2, 3, 3, 4, and 5 for each side.\n");

        }
    }

    public void placeBoats() {
        for(BattleBoat b : boats) {
            boolean validPlacement = false;

            Random rand = new Random();

            while (!validPlacement) {
                x = rand.nextInt(board.length);
                y = rand.nextInt(board.length);

                boolean orientation = rand.nextBoolean();
                b.setOrientation(orientation);
                c = b.getOrientation();
                d = b.getSize();

                boolean outOfRange = false;

                boolean overlapped = false;


                if (orientation == true) { //horizontal
                    for (int i = x; i < x + b.getSize(); i++) {

                        if (i >= board.length) {
                            outOfRange = true; // Checks if out of range
                        }

                        else if (this.board[i][y].getStatus() == 'B') {
                            overlapped = true;
                            //We overlapped an existing boat
                        }
                    }
                }

                if (orientation == false) {
                    for (int i = y; i < y + b.getSize(); i++) {
                        if (i >= board.length) { // checks if outOfRange
                            outOfRange = true;
                        } else if (this.board[x][i].getStatus() == 'B') {
                            overlapped = true; // checks if overlaps with another boat
                        }
                    }
                }

                if(!overlapped && !outOfRange) {

                    if (orientation == true) { // horizontal
                        for (int i = x; i < x + b.getSize(); i++) {
                            if (this.board[i][y].getStatus() == '-') {
                                this.board[i][y].setStatus('B');
                                b.getSpaces()[i-x] = this.board[i][y];
                                this.board[i][y].setBoat(b);
                            }
                        }
                    }

                    if (orientation == false) {
                        for (int i = y; i < y + b.getSize(); i++) {
                            if(board[x][i].getStatus() == '-' ) {
                                board[x][i].setStatus('B');
                                b.getSpaces()[i-y] = this.board[x][i];
                                this.board[x][i].setBoat(b);
                            }
                        }
                    }

                    validPlacement = true; // if the boats aren't overlapped AND not outOfRange, exit while loop
                }
            }
        }
    }

    public int getTurnCount() {
        return this.turnCount;
    }

    public int getUnsunkBoats() {
        return this.unsunkBoats;
    }

    public int fire(int x, int y) { // How to sink ships?
        if (x < board.length || y < board.length) {
            if (this.board[x][y].getStatus() == 'B') {
                this.board[x][y].setStatus('H');

                if (sunk(this.board[x][y].getBoat()) == 1) {
                    System.out.println("Sunk!" + "\n");
                }
                else {
                    System.out.println("Hit!" + "\n");
                }

                return this.turnCount += 1; // return 1 if its a "correct" move

            } else if (this.board[x][y].getStatus() == '-') {
                this.board[x][y].setStatus('M');
                System.out.println("Miss!" + "\n");


                return this.turnCount += 1;
                // increment by 1 turn everytime
            } else if (this.board[x][y].getStatus() == 'H') {
                System.out.println("Penalty!" + "\n");
                // increment turn count by 2 for a penalty ("skips" turn)

                return this.turnCount += 2;

            } else if (this.board[x][y].getStatus() == 'M') {
                System.out.println("Penalty!" + "\n");

                return this.turnCount += 2;
            }
        }
        System.out.println("Out of bounds, please select a number between 0 and " + (this.board.length - 1) );
        return this.turnCount += 2; // return 2 if its an "incorrect" move
    }

    public int sunk(BattleBoat b) {
        int sunkOrNot = 0;
        int counter = 0;
        for (int i = 0; i < b.getSize(); i++) {
            int d = b.getSpaces()[i].getRow();
            int e = b.getSpaces()[i].getCol();

            if (this.board[d][e].getStatus() == 'H') {
                counter += 1;
            }
        }

        if (counter == b.getSize()) {
            unsunkBoats -= 1;
            sunkOrNot = 1;
        }
        return sunkOrNot;
    }

    public int getBoardLength() {
        return this.board.length;
    }


    public void print() { // Hides where boat objects on the board are
        String aString = "";
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                if (this.board[i][j].getStatus() == 'B') {
                    aString += '-' + " ";
                }
                if (this.board[i][j].getStatus() == 'H') {
                    aString += 'H' + " ";
                }
                if (this.board[i][j].getStatus() == 'M') {
                    aString += 'M' + " ";
                }
                if (this.board[i][j].getStatus() == '-') {
                    aString += '-' + " ";
                }
            }
            aString += "\n";
        }
        System.out.println(aString);
    }


    public void display() { // Used for debugging, shows where each boat, hit, and miss is located at
        String aString = "";
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                aString += this.board[i][j].getStatus() + " ";
            }
            aString += "\n";
        }
        System.out.println(aString);
    }


}
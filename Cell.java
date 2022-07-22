// Rayan Amir and Brock Williams
public class Cell {
    private int row;
    private int col;
    private char status;
    private BattleBoat boat;

    public Cell(int rowCell, int colCell, char statusCell) {
        this.row = rowCell;
        this.col = colCell;
        this.status = statusCell;

    }

    public void setStatus(char c) {
        this.status = c;
    }

    public char getStatus() {
        return this.status;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    public BattleBoat getBoat() {
        return this.boat;
    }

    public void setBoat(BattleBoat b) {
        this.boat = b;
    }
}
// Rayan Amir and Brock Williams
public class BattleBoat {

    private int size;
    private boolean orientation; // 3 data members for BattleBoat class
    private Cell[] spaces;

    public BattleBoat(int length){
        this.size = length; // member variable "size" is equal to the length of the boat
        this.spaces = new Cell[size];
    }

    public void setSpaces(Cell[] cells) { // mutator for the spaces (cells)
        spaces = cells;
    }

    public Cell[] getSpaces() { // accessor for the spaces
        return this.spaces;
    }

    public int getSize() { // accessor for the size, used in placeBoats()
        return this.size;
    }

    public boolean getOrientation() { // accessor for orientation
        return this.orientation;
    }

    public void setOrientation(boolean orientation1) { // mutator for orientation, used in placeBoats()
        orientation = orientation1; }
}
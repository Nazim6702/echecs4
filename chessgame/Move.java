package chessgame;

public class Move {
    private Cell from;
    private Cell to;

    public Move(Cell from, Cell to) {
        this.from = from;
        this.to = to;
    }

    public Cell getFrom() {
        return from;
    }

    public Cell getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "Move from " + from + " to " + to;
    }
}

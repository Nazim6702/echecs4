package chessgame;

public class Cell {
    private int x;
    private int y;
    private Piece currentPiece;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.currentPiece = null;
    }

    // Ajout des getters pour x et y
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isEmpty() {
        return currentPiece == null;
    }

    public void setPiece(Piece piece) {
        this.currentPiece = piece;
    }

    public Piece getPiece() {
        return currentPiece;
    }
}

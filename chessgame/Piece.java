package chessgame;

import java.util.ArrayList;
import java.util.List;

public abstract class Piece {
    protected String type;
    protected Player owner;

    public Piece(String type, Player owner) {
        this.type = type;
        this.owner = owner;
    }

    public String getType() {
        return type;
    }

    public Player getOwner() {
        return owner;
    }

    public abstract boolean move(Cell from, Cell to);

    public abstract List<Cell> getPossibleMoves(Board board);

    protected List<Cell> getLinearMoves(Board board, int dx, int dy) {
        List<Cell> moves = new ArrayList<>();
        int x = this.position.getX();
        int y = this.position.getY();

        while (true) {
            x += dx;
            y += dy;
            if (!board.isValidCell(x, y)) {
                break;
            }

            Cell targetCell = board.getCell(x, y);
            if (targetCell.isEmpty()) {
                moves.add(targetCell);
            } else {
                if (!targetCell.getPiece().getOwner().equals(owner)) {
                    moves.add(targetCell); // Capture possible
                }
                break; // Arrête le déplacement après rencontrer une pièce
            }
        }

        return moves;
    }

}

package chessgame;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
    public Pawn(Player owner) {
        super("Pawn", owner);
    }

    @Override
    public List<Cell> getPossibleMoves(Board board) {
        List<Cell> moves = new ArrayList<>();
        int x = this.position.getX();
        int y = this.position.getY();

        int direction = (owner.getColor() == Color.RED || owner.getColor() == Color.BLUE) ? 1 : -1;

        // Avancer d'une case
        int newX = x + direction;
        if (board.isValidCell(newX, y) && board.getCell(newX, y).isEmpty()) {
            moves.add(board.getCell(newX, y));
        }

        // Capture en diagonale
        int[] dy = {-1, 1};
        for (int offset : dy) {
            int newY = y + offset;
            if (board.isValidCell(newX, newY)) {
                Cell targetCell = board.getCell(newX, newY);
                if (!targetCell.isEmpty() && !targetCell.getPiece().getOwner().equals(owner)) {
                    moves.add(targetCell);
                }
            }
        }

        return moves;
    }
}

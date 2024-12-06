package chessgame;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {
    public King(Player owner) {
        super("King", owner);
    }

    @Override
    public List<Cell> getPossibleMoves(Board board) {
        List<Cell> moves = new ArrayList<>();
        int x = this.position.getX();
        int y = this.position.getY();

        // Vérifie toutes les cases adjacentes (8 directions)
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0)
                    continue; // Ignore la case actuelle
                int newX = x + dx;
                int newY = y + dy;
                if (board.isValidCell(newX, newY)) {
                    Cell targetCell = board.getCell(newX, newY);
                    if (targetCell.isEmpty() || !targetCell.getPiece().getOwner().equals(owner)) {
                        moves.add(targetCell);
                    }
                }
            }
        }

        return moves;
    }

    @Override
    public King clone() {
        return (King) super.clone();
    }

    @Override
    public boolean move(Cell from, Cell to) {
        int dx = Math.abs(from.getX() - to.getX());
        int dy = Math.abs(from.getY() - to.getY());
        return dx <= 1 && dy <= 1; // Une case autour
    }

}

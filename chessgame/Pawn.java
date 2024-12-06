package chessgame;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
    
    private final int initialRow; // Ligne initiale du pion

    public Pawn(Player owner, int initialRow) {
        super("Pawn", owner);
        this.initialRow = initialRow; // Définit la ligne initiale
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
        int[] dy = { -1, 1 };
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

    @Override
    public Pawn clone() {
        return (Pawn) super.clone(); // Appel à clone() de la classe parent
    }

    @Override
    public boolean move(Cell from, Cell to) {
        int dx = from.getX() - to.getX();
        int dy = Math.abs(from.getY() - to.getY());

        // Mouvement en avant (une case ou deux cases si c'est le premier tour)
        if (dy == 0 && (dx == 1 || (dx == 2 && from.getX() == initialRow))) {
            return true;
        }

        // Capture diagonale
        if (dy == 1 && dx == 1 && to.getPiece() != null && !to.getPiece().getOwner().equals(this.getOwner())) {
            return true;
        }

        return false; // Mouvement invalide
    }

}

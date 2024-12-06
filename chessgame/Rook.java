package chessgame;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {
    public Rook(Player owner) {
        super("Rook", owner);
    }

    @Override
    public List<Cell> getPossibleMoves(Board board) {
        List<Cell> moves = new ArrayList<>();

        // Mouvements horizontaux et verticaux
        moves.addAll(getLinearMoves(board, 1, 0)); // Droite
        moves.addAll(getLinearMoves(board, -1, 0)); // Gauche
        moves.addAll(getLinearMoves(board, 0, 1)); // Bas
        moves.addAll(getLinearMoves(board, 0, -1)); // Haut

        return moves;
    }

    @Override
    public Rook clone() {
        return (Rook) super.clone(); // Appel à clone() de la classe parent
    }

    @Override
    public boolean move(Cell from, Cell to) {
        int dx = Math.abs(from.getX() - to.getX());
        int dy = Math.abs(from.getY() - to.getY());
        return (dx == 0 && dy > 0) || (dy == 0 && dx > 0); // Déplacement horizontal ou vertical
    }

}

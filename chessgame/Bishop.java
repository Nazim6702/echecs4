package chessgame;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {
    public Bishop(Player owner) {
        super("Bishop", owner);
    }

    @Override
    public List<Cell> getPossibleMoves(Board board) {
        List<Cell> moves = new ArrayList<>();

        // Mouvements diagonaux
        moves.addAll(getLinearMoves(board, 1, 1)); // Diagonale bas-droite
        moves.addAll(getLinearMoves(board, -1, -1)); // Diagonale haut-gauche
        moves.addAll(getLinearMoves(board, 1, -1)); // Diagonale haut-droite
        moves.addAll(getLinearMoves(board, -1, 1)); // Diagonale bas-gauche

        return moves;
    }

    @Override
    public Bishop clone() {
        return (Bishop) super.clone(); // Appel à clone() de la classe parent
    }

    @Override
    public boolean move(Cell from, Cell to) {
        int dx = Math.abs(from.getX() - to.getX());
        int dy = Math.abs(from.getY() - to.getY());
        return dx == dy; // Déplacement diagonal
    }

}

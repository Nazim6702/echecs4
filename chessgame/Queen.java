package chessgame;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {
    public Queen(Player owner) {
        super("Queen", owner);
    }

    @Override
    public List<Cell> getPossibleMoves(Board board) {
        List<Cell> moves = new ArrayList<>();

        // Combinaison des mouvements de la tour et du fou
        moves.addAll(getLinearMoves(board, 1, 0)); // Droite
        moves.addAll(getLinearMoves(board, -1, 0)); // Gauche
        moves.addAll(getLinearMoves(board, 0, 1)); // Bas
        moves.addAll(getLinearMoves(board, 0, -1)); // Haut
        moves.addAll(getLinearMoves(board, 1, 1)); // Diagonale bas-droite
        moves.addAll(getLinearMoves(board, -1, -1)); // Diagonale haut-gauche
        moves.addAll(getLinearMoves(board, 1, -1)); // Diagonale haut-droite
        moves.addAll(getLinearMoves(board, -1, 1)); // Diagonale bas-gauche

        return moves;
    }

    @Override
    public Queen clone() {
        return (Queen) super.clone();
    }

    @Override
    public boolean move(Cell from, Cell to) {
        int dx = Math.abs(from.getX() - to.getX());
        int dy = Math.abs(from.getY() - to.getY());
        return (dx == dy) || (dx == 0 || dy == 0); // Diagonal ou ligne droite
    }

}

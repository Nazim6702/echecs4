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
        moves.addAll(getLinearMoves(board, 1, 0));  // Droite
        moves.addAll(getLinearMoves(board, -1, 0)); // Gauche
        moves.addAll(getLinearMoves(board, 0, 1));  // Bas
        moves.addAll(getLinearMoves(board, 0, -1)); // Haut

        return moves;
    }
}

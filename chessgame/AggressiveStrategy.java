package chessgame;

import java.util.List;

public class AggressiveStrategy implements Strategy {
    @Override
    public Move calculateMove(Player player, Board board) {
        for (Piece piece : player.getPieces()) {
            List<Cell> possibleMoves = piece.getPossibleMoves(board);
            for (Cell target : possibleMoves) {
                if (target.getPiece() != null && !target.getPiece().getOwner().equals(player)) {
                    // Capture une pièce ennemie
                    return new Move(piece.getPosition(), target);
                }
            }
        }
        // Aucun mouvement agressif trouvé, jouer un mouvement aléatoire
        return makeRandomMove(player, board);
    }

    private Move makeRandomMove(Player player, Board board) {
        for (Piece piece : player.getPieces()) {
            List<Cell> possibleMoves = piece.getPossibleMoves(board);
            if (!possibleMoves.isEmpty()) {
                return new Move(piece.getPosition(), possibleMoves.get(0)); // Premier mouvement possible
            }
        }
        return null; // Aucun mouvement disponible
    }
}

package chessgame;

import java.util.List;

public class AggressiveStrategy implements Strategy {
    @Override
    public Move calculateMove(Player player, Board board) {
        for (Piece piece : player.getPieces()) {
            List<Cell> possibleMoves = piece.getPossibleMoves(board);
            for (Cell target : possibleMoves) {
                if (target.getPiece() != null && !target.getPiece().getOwner().equals(player)) {
                    // Trouve une capture
                    return new Move(board.getCell(piece.getPosition().getX(), piece.getPosition().getY()), target);
                }
            }
        }

        // Si aucune capture n'est possible, retourne un mouvement aléatoire
        return makeRandomMove(player, board);
    }

    private Move makeRandomMove(Player player, Board board) {
        for (Piece piece : player.getPieces()) {
            List<Cell> possibleMoves = piece.getPossibleMoves(board);
            if (!possibleMoves.isEmpty()) {
                return new Move(board.getCell(piece.getPosition().getX(), piece.getPosition().getY()), possibleMoves.get(0));
            }
        }
        return null; // Aucun mouvement disponible
    }
}

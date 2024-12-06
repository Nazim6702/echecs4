package chessgame;

import java.util.List;

public class DefensiveStrategy implements Strategy {
    @Override
    public Move calculateMove(Player player, Board board) {
        for (Piece piece : player.getPieces()) {
            List<Cell> possibleMoves = piece.getPossibleMoves(board);
            for (Cell target : possibleMoves) {
                if (target.isEmpty() || !target.getPiece().getOwner().equals(player)) {
                    // Mouvement sans danger
                    return new Move(board.getCell(piece.getPosition().getX(), piece.getPosition().getY()), target);
                }
            }
        }

        // Si aucun mouvement défensif n'est trouvé, retourne un mouvement aléatoire
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
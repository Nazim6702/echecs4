package chessgame;

import java.util.List;

public class DefensiveStrategy implements Strategy {
    @Override
    public Move calculateMove(Player player, Board board) {
        for (Piece piece : player.getPieces()) {
            List<Cell> possibleMoves = piece.getPossibleMoves(board);
            for (Cell target : possibleMoves) {
                // Évite les cases dangereuses ou protège le roi
                if (isSafeMove(piece, target, board)) {
                    return new Move(piece.getPosition(), target);
                }
            }
        }
        // Aucun mouvement défensif trouvé, jouer un mouvement aléatoire
        return makeRandomMove(player, board);
    }

    private boolean isSafeMove(Piece piece, Cell target, Board board) {
        // Logique simplifiée : ne pas se déplacer vers une case déjà attaquée
        return !isCellUnderAttack(target, piece.getOwner(), board);
    }

    private boolean isCellUnderAttack(Cell cell, Player player, Board board) {
        for (Player opponent : board.getPlayers()) { // Utilise la méthode getPlayers()
            if (!opponent.equals(player) && !opponent.isEliminated()) {
                for (Piece enemyPiece : opponent.getPieces()) {
                    if (enemyPiece.getPossibleMoves(board).contains(cell)) {
                        return true; // La cellule est attaquée
                    }
                }
            }
        }
        return false;
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

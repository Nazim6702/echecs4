package chessgame;

import java.util.ArrayList;
import java.util.List;

public class ChessGame {
    private List<Player> players;
    private Board board;
    private Player currentTurn;
    private boolean isGameOver;

    public ChessGame() {
        this.players = new ArrayList<>();
        this.board = new Board();
        this.isGameOver = false;
    }

    public void startGame() {
        initializePlayers();
        board.setPlayers(players); // Passe les joueurs au plateau
        board.initializeBoard(players); // Initialisation du plateau
        currentTurn = players.get(0); // Les rouges commencent
        System.out.println("Le jeu commence !");
        
        while (!isGameOver) {
            playTurn(currentTurn);
            checkGameState();
            rotateTurn();
        }
    
        System.out.println("Le jeu est terminé !");
        displayScores();
    }
    
    

    private void initializePlayers() {
        players.add(new Player("Red", Color.RED, new AggressiveStrategy()));
        players.add(new Player("Blue", Color.BLUE, new DefensiveStrategy()));
        players.add(new Player("Yellow", Color.YELLOW, new AggressiveStrategy()));
        players.add(new Player("Green", Color.GREEN, new DefensiveStrategy()));
    }
    

    private void playTurn(Player player) {
        if (isInCheck(player, board)) {
            System.out.println(player.getName() + " est en échec !");
            if (isCheckmate(player, board)) {
                System.out.println(player.getName() + " est en échec et mat et éliminé !");
                player.eliminate();
                return;
            }
        }
    
        Move move = player.makeMove(board);
        if (move != null && board.updateBoard(move)) {
            System.out.println(player.getName() + " joue : " 
                + move.getFrom().toString() + " -> " + move.getTo().toString());
        } else {
            System.out.println(player.getName() + " ne peut pas jouer. Mouvement invalide.");
        }
        
    }
    
    

    private void checkGameState() {
        int activePlayers = 0;
        for (Player player : players) {
            if (!player.isEliminated()) {
                activePlayers++;
            }
        }

        if (activePlayers <= 1) {
            isGameOver = true;
        }
    }

    private void rotateTurn() {
        int currentIndex = players.indexOf(currentTurn);
    
        // Trouver le prochain joueur valide
        for (int i = 0; i < players.size(); i++) {
            currentIndex = (currentIndex + 1) % players.size();
            if (!players.get(currentIndex).isEliminated()) {
                currentTurn = players.get(currentIndex);
                return;
            }
        }
    
        // Si aucun joueur valide trouvé, cela signifie que la partie est terminée
        isGameOver = true;
        System.out.println("Tous les joueurs sont éliminés. Fin de la partie !");
    }
    

    private void displayScores() {
        System.out.println("Scores finaux :");
        for (Player player : players) {
            System.out.println(player.getName() + ": " + player.getScore() + " points");
        }
    }


    private boolean isInCheck(Player player, Board board) {
        Cell kingPosition = board.getKingPosition(player);
        for (Player opponent : players) {
            if (!opponent.equals(player) && !opponent.isEliminated()) {
                for (Piece piece : opponent.getPieces()) {
                    List<Cell> moves = piece.getPossibleMoves(board);
                    if (moves.contains(kingPosition)) {
                        return true;
                    }
                }
            }
        }
        System.out.println(player.getName() + " : Roi en position " + kingPosition);
        return false;
    }

    
    
    
    private boolean isCheckmate(Player player, Board board) {
        // Si le roi n'est pas en échec, pas besoin de vérifier l'échec et mat
        if (!isInCheck(player, board)) {
            return false;
        }
    
        // Parcourir toutes les pièces du joueur pour vérifier si un mouvement légal peut sauver le roi
        for (Piece piece : player.getPieces()) {
            List<Cell> possibleMoves = piece.getPossibleMoves(board);
            for (Cell targetCell : possibleMoves) {
                // Simuler le déplacement pour vérifier si le roi est toujours en échec
                Board simulatedBoard = board.simulateMove(piece, targetCell);
                if (!isInCheck(player, simulatedBoard)) {
                    return false; // Au moins un mouvement peut sauver le roi
                }
            }
        }

        System.out.println("Vérification d'échec et mat pour " + player.getName());

    
        return true; // Aucun mouvement ne peut sauver le roi, échec et mat
    }
    
}

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
        board.initializeBoard();
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
            System.out.println(player.getName() + " joue : " + move);
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
        do {
            currentIndex = (currentIndex + 1) % players.size();
        } while (players.get(currentIndex).isEliminated());
        currentTurn = players.get(currentIndex);
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
        return false;
    }

    
    
    
    private boolean isCheckmate(Player player, Board board) {
        if (!isInCheck(player, board)) {
            return false;
        }
    
        for (Piece piece : player.getPieces()) {
            List<Cell> moves = piece.getPossibleMoves(board);
            for (Cell move : moves) {
                Board simulatedBoard = board.simulateMove(piece, move);
                if (!isInCheck(player, simulatedBoard)) {
                    return false; // Un mouvement peut sauver le roi
                }
            }
        }
     
        return true; // Aucun mouvement légal ne sauve le roi
    }
    
}

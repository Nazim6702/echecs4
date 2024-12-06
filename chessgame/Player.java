package chessgame;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private Color color;
    private int score;
    private List<Piece> pieces;
    private boolean eliminated;
    private Strategy strategy; // Ajout de la stratégie


    public Player(String name, Color color, Strategy strategy) {
        this.name = name;
        this.color = color;
        this.strategy = strategy; // Initialisation de la stratégie

        this.score = 0;
        this.pieces = new ArrayList<>();
        this.eliminated = false;
    }

    public Move makeMove(Board board) {
        if (eliminated) {
            return null; // Le joueur est éliminé
        }
        return strategy.calculateMove(this, board);
    }

    public void eliminate() {
        this.eliminated = true;
    }

    public boolean isEliminated() {
        return eliminated;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int points) {
        this.score += points;
    }

    public List<Piece> getPieces() {
        return pieces; // Retourne la liste des pièces du joueur
    }
    

    

    
}

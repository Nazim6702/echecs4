package chessgame;

public class Main {
    public static void main(String[] args) {
        // Initialisation
        ChessGame game = new ChessGame();

        // Ajout de messages pour guider les tests
        System.out.println("Bienvenue dans le jeu d'échecs à 4 joueurs !");
        System.out.println("Initialisation du jeu...");
        
        // Simulation d'une partie
        game.startGame();

        System.out.println("Merci d'avoir joué !");
    }
}
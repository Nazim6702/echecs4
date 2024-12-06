package chessgame;

public class SuperPiece extends Piece {
    public SuperPiece(Player owner) {
        super("SuperPiece", owner);
    }

    @Override
    public boolean move(Cell from, Cell to) {
        // Logique personnalisée pour permettre des déplacements spéciaux
        return true; // Exemple : mouvement toujours valide
    }
}

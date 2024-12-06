package chessgame;

import java.util.List;

public class Board {
    private Cell[][] cells;
    private List<Player> players;


    public Board() {
        cells = new Cell[16][16]; // Plateau de 16x16
        initializeCells();
    }

    private void initializeCells() {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
    }

    public Cell getCell(int x, int y) {
        if (x < 0 || x >= 16 || y < 0 || y >= 16) {
            throw new IllegalArgumentException("Indices hors limites : x = " + x + ", y = " + y);
        }
        return cells[x][y];
    }

    public void setCell(int x, int y, Cell cell) {
        if (x < 0 || x >= cells.length || y < 0 || y >= cells[0].length) {
            throw new IllegalArgumentException("Indices hors limites : x = " + x + ", y = " + y);
        }
        cells[x][y] = cell;
    }
    
    

    public void initializeBoard(List<Player> players) {
        // Joueur Rouge
        placePawns(1, 0, players.get(0)); // Ligne des pions rouges
        placeBackRank(0, 0, players.get(0)); // Ligne des pièces principales rouges
    
        // Joueur Bleu
        placePawns(1, 8, players.get(1)); // Ligne des pions bleus
        placeBackRank(0, 8, players.get(1)); // Ligne des pièces principales bleues
    
        // Joueur Jaune
        placePawns(14, 8, players.get(2)); // Ligne des pions jaunes
        placeBackRank(15, 8, players.get(2)); // Ligne des pièces principales jaunes
    
        // Joueur Vert
        placePawns(14, 0, players.get(3)); // Ligne des pions verts
        placeBackRank(15, 0, players.get(3)); // Ligne des pièces principales vertes
    }
    



    public void placeBackRank(int row, int colStart, Player player) {
        String[] order = {"Rook", "Knight", "Bishop", "Queen", "King", "Bishop", "Knight", "Rook"};
        for (int i = 0; i < 8; i++) {
            if (colStart + i >= 16) {
                throw new IllegalArgumentException("Indice colonne invalide : " + (colStart + i));
            }
            Cell cell = getCell(row, colStart + i);
            Piece piece;
            switch (order[i]) {
                case "Rook":
                    piece = new Rook(player);
                    break;
                case "Knight":
                    piece = new Knight(player);
                    break;
                case "Bishop":
                    piece = new Bishop(player);
                    break;
                case "Queen":
                    piece = new Queen(player);
                    break;
                case "King":
                    piece = new King(player);
                    break;
                default:
                    throw new IllegalStateException("Type de pièce inattendu : " + order[i]);
            }
            piece.setPosition(cell);
            cell.setPiece(piece);
            player.getPieces().add(piece);
        }
    }
    
    



    public boolean updateBoard(Move move) {
        // Logique pour appliquer un mouvement au plateau
        return true;
    }

    public Cell getKingPosition(Player player) {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                Cell cell = getCell(i, j);
                if (cell.getPiece() instanceof King && cell.getPiece().getOwner().equals(player)) {
                    return cell;
                }
            }
        }
        return null; // Aucun roi trouvé
    }
    

    public boolean isValidCell(int x, int y) {
        return x >= 0 && x < 16 && y >= 0 && y < 16;
    }

    public Board copy() {
        Board newBoard = new Board();
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                Cell originalCell = this.getCell(i, j);
                Cell newCell = new Cell(i, j);
                if (!originalCell.isEmpty()) {
                    Piece originalPiece = originalCell.getPiece();
                    Piece newPiece = originalPiece.clone(); // Supposant que clone() est implémenté
                    newPiece.setPosition(newCell);
                    newCell.setPiece(newPiece);
                }
                newBoard.setCell(i, j, newCell); // Appel à la méthode setCell
            }
        }
        return newBoard;
    }
    
    

    public Board simulateMove(Piece piece, Cell targetCell) {
        // Créer une copie du plateau
        Board simulatedBoard = this.copy();
    
        // Récupérer la cellule d'origine et la cellule cible dans le plateau simulé
        Cell fromCell = simulatedBoard.getCell(piece.getPosition().getX(), piece.getPosition().getY());
        Cell toCell = simulatedBoard.getCell(targetCell.getX(), targetCell.getY());
    
        // Déplacer la pièce sur le plateau simulé
        toCell.setPiece(piece);
        fromCell.setPiece(null);
    
        // Mettre à jour la position de la pièce simulée
        piece.setPosition(toCell);
    
        return simulatedBoard;
    }

    public void placePawns(int row, int colStart, Player player) {
        for (int i = 0; i < 8; i++) { // Chaque joueur a 8 pions
            Cell cell = getCell(row, colStart + i);
            Pawn pawn = new Pawn(player, row); // Passe la rangée initiale comme argument
            pawn.setPosition(cell);
            cell.setPiece(pawn);
            player.getPieces().add(pawn);
        }
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
    
    public List<Player> getPlayers() {
        return players;
    }

}

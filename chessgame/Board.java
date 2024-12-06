package chessgame;

public class Board {
    private Cell[][] cells;

    public Board() {
        cells = new Cell[16][16];
    }

    public void initializeBoard() {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
    }

    public boolean updateBoard(Move move) {
        // Logique pour appliquer un mouvement au plateau
        return true;
    }

    public Cell getKingPosition(Player player) {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                Cell cell = cells[i][j];
                if (cell.getPiece() instanceof King && cell.getPiece().getOwner().equals(player)) {
                    return cell;
                }
            }
        }
        return null; // Retourne null si le roi n'est pas trouvé
    }

    public boolean isValidCell(int x, int y) {
        return x >= 0 && x < 16 && y >= 0 && y < 16;
    }



    
}

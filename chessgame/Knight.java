package chessgame;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {
    public Knight(Player owner) {
        super("Knight", owner);
    }

    @Override
    public List<Cell> getPossibleMoves(Board board) {
        List<Cell> moves = new ArrayList<>();
        int x = this.position.getX();
        int y = this.position.getY();

        // Tous les déplacements possibles en "L"
        int[][] knightMoves = {
                { 2, 1 }, { 2, -1 }, { -2, 1 }, { -2, -1 },
                { 1, 2 }, { 1, -2 }, { -1, 2 }, { -1, -2 }
        };

        for (int[] move : knightMoves) {
            int newX = x + move[0];
            int newY = y + move[1];
            if (board.isValidCell(newX, newY)) {
                Cell targetCell = board.getCell(newX, newY);
                if (targetCell.isEmpty() || !targetCell.getPiece().getOwner().equals(owner)) {
                    moves.add(targetCell);
                }
            }
        }

        return moves;
    }

    @Override
    public Knight clone() {
        return (Knight) super.clone();
    }

    @Override
    public boolean move(Cell from, Cell to) {
        int dx = Math.abs(from.getX() - to.getX());
        int dy = Math.abs(from.getY() - to.getY());
        return (dx == 2 && dy == 1) || (dx == 1 && dy == 2); // Mouvement en "L"
    }

}

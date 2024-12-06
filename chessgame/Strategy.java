package chessgame;

public interface Strategy {
    Move calculateMove(Player player, Board board);
}

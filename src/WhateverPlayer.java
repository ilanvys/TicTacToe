import java.util.Random;

public class WhateverPlayer implements Player {
    WhateverPlayer() {}
    /**
     * Plays a turn in the game using the whatever tactic.
     * Chooses a random cell with the mark given,
     * @param board the game board.
     * @param mark the mark to play a turn with.
     */
    public void playTurn(Board board, Mark mark) {
        Random rand = new Random();
        boolean valid_mark = false;
        while (!valid_mark) {
            int randRow = rand.nextInt(Board.SIZE);
            int randCol = rand.nextInt(Board.SIZE);
            valid_mark = board.putMark(mark, randRow, randCol);
        }
    }
}

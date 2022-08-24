import java.util.Random;

public class CleverPlayer implements Player {
    Player whateverPlayer = new WhateverPlayer();

    CleverPlayer() {}

    /**
     * Plays a turn in the game using the clever tactic.
     * Chooses a random cell with the mark given,
     * then checks for all the empty cells near the mark,
     * always chooses the first empty cell possible.
     * if there are no empty cells, it just chooses a random cell,
     * using WhateverPlayer playTurn method
     * @param board the game board.
     * @param mark the mark to play a turn with.
     */
    public void playTurn(Board board, Mark mark) {
        if (!markExist(board, mark)) {
            whateverPlayer.playTurn(board, mark);
        }
        Random rand = new Random();
        boolean isMark = false;
        int randRow = 0, randCol = 0;
        while (!isMark) {
            randRow = rand.nextInt(Board.SIZE);
            randCol = rand.nextInt(Board.SIZE);
            if (board.getMark(randRow, randCol) == mark) {
                isMark = true;
            }
        }
        int[][] blankNextToMark = this.blankNextToMark(board, randRow, randCol);
        int possibleCellsCount = countNumOfCells(blankNextToMark);
        if (possibleCellsCount == 0) {
            whateverPlayer.playTurn(board, mark);
        }
        else {
            int randIndex = rand.nextInt(possibleCellsCount);
            board.putMark(mark, blankNextToMark[0][0], blankNextToMark[0][0]);
        }
    }
}

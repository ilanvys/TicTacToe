import java.util.Random;

public class SnartypamtsPlayer implements Player {
    Player whateverPlayer = new WhateverPlayer();

    SnartypamtsPlayer() {}
    /**
     * Plays a turn in the game using the snartypamts tactic.
     * Chooses a random cell with the mark given,
     * then checks for all the empty cells near the mark,
     * chooses a random empty cell from all the ones possible.
     * if there are no empty cells, it retries that tactic for several times.
     * @param board the game board.
     * @param mark the mark to play a turn with.
     */
    public void playTurn(Board board, Mark mark) {
        if (!markExist(board, mark)) {
            whateverPlayer.playTurn(board, mark);
        }
        Random rand = new Random();
        boolean turnFinished = false;
        int triesCounter = Board.SIZE * Board.SIZE;
        while(!turnFinished && triesCounter > 0) {
            boolean isMark = false;
            int randRow = 0, randCol = 0;

            while(!isMark) {
                randRow = rand.nextInt(Board.SIZE);
                randCol = rand.nextInt(Board.SIZE);
                if (board.getMark(randRow, randCol) == mark) {
                    isMark = true;
                }
            }

            int[][] blankNextToMark = this.blankNextToMark(board, randRow, randCol);
            int possibleCellsCount = countNumOfCells(blankNextToMark);
            if(possibleCellsCount == 0) {
                triesCounter--;
            } else {
                int randIndex = rand.nextInt(possibleCellsCount);
                board.putMark(mark, blankNextToMark[randIndex][0], blankNextToMark[randIndex][1]);
                turnFinished = true;
            }
        }
        if (!turnFinished) {
            whateverPlayer.playTurn(board, mark);
        }
    }
}

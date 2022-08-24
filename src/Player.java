import java.util.Arrays;

public interface Player {
    void playTurn(Board board, Mark mark);

    /**
     * Recieves coordinates for a cell,
     * returns an array of coordinates with all the cells,
     * that are empty
     * @param row the row of given cell.
     * @param col the col of given cell.
     */
    default int[][] blankNextToMark(Board board, int row, int col) {
        int[][] blankCells = new int[8][2];
        for (int[] r : blankCells) {
            Arrays.fill(r, Board.SIZE + 1);
        }
        int blankCellsCounter = 0;

        for (int i = row - 1; i <= (row + 1) && i >= 0 && i < Board.SIZE; i++) {

            for (int j = col - 1; j <= (col + 1) && j >= 0 && j < Board.SIZE; j++) {

                if ((i != row || j != col) && board.getMark(i, j) == Mark.BLANK) {
                    blankCells[blankCellsCounter][0] = i;
                    blankCells[blankCellsCounter][1] = j;
                    blankCellsCounter++;
                }
            }
        }
        return blankCells;
    }

    /**
     * Recieves blankCells array and counts the number of cells,
     * that are not with the default value of Board.SIZE + 1.
     * @param blankCells an array of possible empty cells.
     */
    default int countNumOfCells(int[][] blankCells) {
        int counter = 0;
        for (int[] r : blankCells) {
            if(r[0] != Board.SIZE + 1) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * Checks if given mark appears somewhere in the game board.
     * @param board the game board.
     * @param mark the mark to check
     */
    default boolean markExist(Board board, Mark mark) {
        boolean markExist = false;
        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                if (board.getMark(i, j) == mark) {
                    return true;
                }
            }
        }
        return false;
    }
}

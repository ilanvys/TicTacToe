import java.util.Arrays;

public class Board {
    public enum  GameStatus { DRAW, X_WIN, O_WIN, IN_PROGRESS }

    public static final int SIZE = 6;
    public static final int WIN_STREAK = 4;
    public GameStatus gamesStatus = GameStatus.IN_PROGRESS;

    private Mark[][] board = new Mark[SIZE][SIZE];
    private int blankCellsCounter;
    private boolean gameEnded = false;

    Board() {
        this.board = board;
        this.blankCellsCounter = SIZE * SIZE;
        this.gameEnded = gameEnded;
        for (Mark[] row : board) {
            Arrays.fill(row, Mark.BLANK);
        }
    }
    
    /**
    * Updates the board with the mark given,
    * after checking it is in a valid position.
    * @param mark the to add to the board.
    * @param row the row to add the mark to.
    * @param col the column to add the mark to.
    */
    public boolean putMark(Mark mark, int row, int col) { 
        if(row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
            return false;
        }
        if(getMark(row, col) == Mark.BLANK) {
            this.board[row][col] = mark;
            this.blankCellsCounter--;
            gameEnded();
            return true;
        }
        return false;
     }

    /**
    * Get the mark in the board in the position given.
    * @param row the row to get the mark from.
    * @param col the column to get the mark from.
    */
    public Mark getMark(int row, int col) {
        if(row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
            return Mark.BLANK;
        }
        return this.board[row][col];
    }

    /**
    * Check if the game ended with a draw, X won or O won.
    * return weather the game has ended.
    */
    public boolean gameEnded() {
        if(this.blankCellsCounter == 0) {
            this.gameEnded = true;
            this.gamesStatus = GameStatus.DRAW;
            return true;
        }
        if (checkWin(Mark.X)) {
            this.gamesStatus = GameStatus.X_WIN;
            this.gameEnded = true;
        }
        if (checkWin(Mark.O)) {
            this.gamesStatus = GameStatus.O_WIN;
            this.gameEnded = true;
        }
        return this.gameEnded;
    }

    /**
    * Get the mark that won the game, Blank if the game ended with a draw.
    */
    public Mark getWinner() {
        if(this.gamesStatus == GameStatus.X_WIN) {
            return Mark.X;
        }
        if(this.gamesStatus == GameStatus.O_WIN) {
            return Mark.O;
        }
        if(this.gamesStatus == GameStatus.DRAW) {
            return Mark.BLANK;
        }
        return null;
    }

    /**
    * Check if a player won the game.
    * @param mark the mark to check if won the game.
    */
    private boolean checkWin(Mark mark) {
        int counter;
        // Check rows
        for (int row = 0; row < SIZE; row++) {
            counter = 0;
            for (int col = 0; col < SIZE; col++) {
                if(this.board[row][col] == mark) {
                    counter++;
                    if (counter == WIN_STREAK) {
                        return true;
                    }
                }
                else {
                    counter = 0;
                }
            }
        }

        // Check cols
        for (int col = 0; col < SIZE; col++) {
            counter = 0;
            for (int row = 0; row < SIZE; row++) {
                if(this.board[row][col] == mark) {
                    counter++;
                    if (counter == WIN_STREAK) {
                        return true;
                    }
                }
                else {
                    counter = 0;
                }
            }
        }

        // Check diagonal
        for (int row = 0; row <= SIZE - WIN_STREAK; row++) {
            for (int col = 0; col <= SIZE - WIN_STREAK; col++) {
                counter = 0;
                for (int streak = 0; streak < WIN_STREAK; streak++) {
                    if(this.board[row + streak][col + streak] != mark) {
                        break;
                    }
                    else {
                        counter++;
                        if (counter == WIN_STREAK) {
                            return true;
                        }
                    }
                }
            }
        }

        // Check anti diagonal
        for (int row = SIZE - 1; row >= WIN_STREAK - 1; row--) {
            for (int col = 0; col <= SIZE - WIN_STREAK; col++) {
                counter = 0;
                for (int streak = 0; streak < WIN_STREAK; streak++) {
                    if(this.board[row - streak][col + streak] != mark) {
                        break;
                    }
                    else {
                        counter++;
                        if (counter == WIN_STREAK) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}

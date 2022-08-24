public class Game {
    private Player playerX;
    private Player playerO;
    private Renderer renderer;

    public Game(Player playerX, Player playerO, Renderer renderer) {
        this.playerO = playerO;
        this.playerX = playerX;
        this.renderer = renderer;
    }

    /**
    * Run an iteration of the TicTacToe game.
    */
    public Mark run() {
        Board board = new Board();
        int turn_counter = 0;
        while (board.gamesStatus == Board.GameStatus.IN_PROGRESS) {
            renderer.renderBoard(board);
            if(turn_counter % 2 == 0) {
                playerX.playTurn(board, Mark.X);
            }
            else {
                playerO.playTurn(board, Mark.O);
            }
            turn_counter++;
        }
        renderer.renderBoard(board);
        return board.getWinner();
    }
    
    /**
    * Print out which mark won the game.
    * @param winner the mark that won the game.
    */
    private Mark announceWinner(Mark winner) {
        if (winner == Mark.X) {
            System.out.println("X wins!");
        }
        if (winner == Mark.O) {
            System.out.println("O wins!");
        }
        if (winner == Mark.BLANK) {
            System.out.println("Draw!");
        }
        return winner;
    }
}

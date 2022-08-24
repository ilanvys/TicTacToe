import java.util.Scanner;

public class HumanPlayer implements Player {
    public HumanPlayer() {}

    /**
    * Recieves a user input to play a turn in the game.
    * @param board the game board.
    * @param mark the mark to play a turn with.
    */
    public void playTurn(Board board, Mark mark) {
        boolean isMarkValid = false;
        while (!isMarkValid) {
            System.out.println("Player " + mark + ", type coordinates: ");
            Scanner in = new Scanner(System.in);
            int num = in.nextInt();
            isMarkValid = board.putMark(mark, num / 10 - 1, num % 10 - 1);
            if(!isMarkValid) {
                System.out.println("Invalid coordinates, type again: ");
            }
        }
    }
}

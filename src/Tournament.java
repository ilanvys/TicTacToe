import static java.lang.String.format;

public class Tournament {
    private static final int ARG_NUM = 4;
    private static final int ARG_ROUNDS = 0;
    private static final int ARG_RENDERER = 1;
    private static final int ARG_PLAYER_1 = 2;
    private static final int ARG_PLAYER_2 = 3;

    private int rounds;
    private Player player1;
    private Player player2;
    private Renderer renderer;

    Tournament(int rounds, Renderer renderer, Player[] players) {
        this.renderer = renderer;
        this.player1 = players[0];
        this.player2 = players[1];
        this.rounds = rounds;
    }

    /**
     * Runs a tournament of 2 players that compete with each other for
     * a defined number of rounds.
     */
    public void playTournament() {
        int[] tournamentScore = new int[]{ 0, 0, 0 };
        for (int i = 0; i < this.rounds; i++) {
            if(i % 2 == 0) {
                Game game = new Game(this.player1, this.player2, this.renderer);
                Mark winner = game.run();
                tournamentScore[getWinnerIndex(i, winner)]++;
            }
            else {
                Game game = new Game(this.player2, this.player1, this.renderer);
                Mark winner = game.run();
                tournamentScore[getWinnerIndex(i, winner)]++;
            }
            System.out.print(format("=== player 1: %d | player 2: %d | Draws: %d ===\r",
                    tournamentScore[0], tournamentScore[1], tournamentScore[2]));
        }
    }

    /**
     * returns the correct index of the winner to add a point to,
     * for the array the keeps count of the score of the tournament
     * @param index index of the game finished
     * @param winner mark of the winner
     */
    private int getWinnerIndex(int index, Mark winner) {
        if(index % 2 == 0){
            if(winner == Mark.X) {
                return 0;
            }
            if(winner == Mark.O) {
                return 1;
            }
        }
        else {
            if(winner == Mark.X) {
                return 1;
            }
            if(winner == Mark.O) {
                return 0;
            }
        }
        return 2;
    };

    /**
     * validates the all the instances of the classes were created properly.
     * if not, prints an error message and exists the program.
     * @param args array of args inserted to the main method
     * @param rounds num of rounds for the tournaments
     * @param renderer type of renderer for the tournament
     * @param player1 player type
     * @param player2 player type
     */
    public static void validateInstanceCreation(String[] args, int rounds, Renderer renderer, Player player1, Player player2){
        if (renderer == null) {
            System.err.println(format("Failed to create renderer. Incorrect input %s", args[ARG_RENDERER]));
        }
        if (player1 == null) {
            System.err.println(format("Failed to create player 1. Incorrect input %s", args[ARG_PLAYER_1]));
        }
        if (player2 == null) {
            System.err.println(format("Failed to create player 2. Incorrect input %s", args[ARG_PLAYER_2]));
        }
        if(rounds <= 0){
            System.err.println(format("Rounds should be a positive number. %s is incorrect", args[ARG_ROUNDS]));
        }
    }

    public static void main(String[] args) {
        PlayerFactory playerFactory = new PlayerFactory();
        RendererFactory rendererFactory = new RendererFactory();
        if(args.length != ARG_NUM) {
            System.err.println(format("Failed to run tournament. Expected %d arguments", ARG_NUM));
            return;
        }

        Renderer renderer = rendererFactory.buildRenderer(args[ARG_RENDERER]);
        Player player1 = playerFactory.buildPlayer(args[ARG_PLAYER_1]);
        Player player2 = playerFactory.buildPlayer(args[ARG_PLAYER_2]);
        int rounds = Integer.parseInt(args[ARG_ROUNDS]);

        validateInstanceCreation(args, rounds, renderer, player1, player2);
        Player[] playersArr = new Player[]{player1, player2};
        Tournament tournament = new Tournament(rounds, renderer, playersArr);
        tournament.playTournament();
    }
}

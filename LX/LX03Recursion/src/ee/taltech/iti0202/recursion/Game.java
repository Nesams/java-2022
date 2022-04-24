package ee.taltech.iti0202.recursion;
public class Game {

    private Strategy[] players;
    private Board board;

    public Game(Strategy playerA, Strategy playerB) {
        this.players = new Strategy[2];
        this.players[0] = playerA;
        this.players[0].setNumber(1);
        this.players[1] = playerB;
        this.players[1].setNumber(2);
        board = new Board();
    }

    public int play() {
        for (int i = 0; i < 9; i++) {
            int[] move = this.players[i % 2].getMove();
            // System.out.println(move);
            board.move(move[0], move[1]);
            // System.out.println(board.toStr());
            this.players[(i + 1) % 2].moveOpponent(move[0], move[1]);
            if (board.getWinner() != 0) {
                return board.getWinner();
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        Strategy a = new RandomStrategy();
        Strategy b = new StudentStrategy();
        Game game = new Game(a, b);
        System.out.println(game.play());
    }
}
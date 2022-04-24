package ee.taltech.iti0202.recursion;
public class StudentStrategy implements Strategy {

    private int number;
    private Board board;

    public StudentStrategy() {
        this.board = new Board();
    }

    @Override
    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public void moveOpponent(int x, int y) {
        this.board.move(x, y);
    }

    @Override
    public int[] getMove() {
        Board copiedBoard = new Board(board);
        int[] move = getBestMove(copiedBoard);
        this.board.move(move[0], move[1]);
        return move;
    }

    public int[] getBestMove(Board board) {
        // Iterate through all legal moves and get the value of each move with getMinValue, then return the move with the highest value.
        int[] bestMove = new int[]{-1, -1};
        int bestValue = Integer.MIN_VALUE;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (!board.move(row, col)) {
                    board.move(row, col);
                    int moveValue = miniMax(board, 6, false);
                    board.move(row, col);
                    if (moveValue > bestValue) {
                        bestMove[0] = row;
                        bestMove[1] = col;
                        bestValue = moveValue;
                    }
                }
            }
        }
        return bestMove;
    }

    public static int miniMax(Board board, int depth, boolean isMax) {
        int boardVal = board.getMoveCounter();

        // Terminal node (win/lose/draw) or max depth reached.
        if (Math.abs(boardVal) == 10 || depth == 0
                || board.isFull()) {
            return boardVal;
        }

        // Maximising player, find the maximum attainable value.
        if (isMax) {
            int highestVal = Integer.MIN_VALUE;
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (board.move(row, col)) {
                        highestVal = Math.max(highestVal, miniMax(board,
                                depth - 1, false));
                    }
                }
            }
            return highestVal;
            // Minimising player, find the minimum attainable value;
        } else {
            int lowestVal = Integer.MAX_VALUE;
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (board.move(row, col)) {
                        lowestVal = Math.min(lowestVal, miniMax(board,
                                depth - 1, true));
                    }
                }
            }
            return lowestVal;
        }
    }

    public int getMinValue(Board board) {
        // Iterate through all legal moves, recursively (using getMaxValue) get value of each move and return the min of those values
        return 0;
    }

    public int getMaxValue(Board board) {
        // Iterate through all legal moves, recursively (using getMinValue) get value of each move and return the max of those values
        return 0;
    }
}
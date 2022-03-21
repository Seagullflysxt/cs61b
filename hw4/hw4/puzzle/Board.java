package hw4.puzzle;

import edu.princeton.cs.algs4.Queue;

public class Board implements WorldState {
    int N;
    int[][] board;
    final int BLANK = 0;

    /**Constructs a board from an N-by-N array of tiles where
     tiles[i][j] = tile at row i, column j*/
    public Board(int[][] tiles) {
        N = tiles.length;
        board = new int[N][N];
        for (int i = 0; i < N; i += 1) {
            System.arraycopy(tiles[i], 0, board[i], 0, N);
        }
    }
    /**Returns value of tile at row i, column j (or 0 if blank)*/
    public int tileAt(int i, int j) {
        if (!validate(i, j)) {
            throw new IndexOutOfBoundsException();
        }
        return board[i][j];
    }

    private boolean validate(int i, int j) {
        if (i < 0 || i > N - 1 || j < 0 || j > N - 1) {
            return false;
        }
        return true;
    }
    /**Returns the board size N*/
    public int size() {
        return N;
    }
    /**Returns the neighbors of the current board
     *
     * @author http://joshh.ug/neighbors.html*/
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int[] blank = findBlank(board);
        int row = blank[0];
        int col = blank[1];
        if (validate(row - 1, col)) {
            neighbors.enqueue(new Board(swap(row - 1, col, row, col)));
        }
        if (validate(row + 1, col)) {
            neighbors.enqueue(new Board(swap(row + 1, col, row, col)));
        }
        if (validate(row, col - 1)) {
            neighbors.enqueue(new Board(swap(row, col - 1, row, col)));
        }
        if (validate(row, col + 1)) {
            neighbors.enqueue(new Board(swap(row, col + 1, row, col)));
        }


        return neighbors;
    }
    private int[][] swap(int i, int j, int m, int n) {
        int temp = board[i][j];
        board[i][j] = board[m][n];
        board[m][n] = temp;
        return board;
    }

    private int[] findBlank(int[][] b) {
        int[] toReture = new int[2];
        for (int i = 0; i < b.length; i += 1) {
            for (int j = 0; j < b.length; j += 1) {
                if (b[i][j] == 0) {
                    toReture[0] = i;
                    toReture[1] = j;
                }
            }
        }
        return toReture;
    }
    /**Hamming estimate described below
     * The number of tiles in the wrong position.*/
    public int hamming() {
        int cnt = 0;
        for (int i = 0; i < N; i += 1) {
            for (int j = 0; j < N; j += 1) {
                if (board[i][j] == 0) {
                    continue;
                }
                if (board[i][j] != i * N + 1 + j) {
                    cnt += 1;
                }
            }
        }
        return cnt;
    }
    /**Manhattan estimate described below*/
    public int manhattan() {
        int cnt = 0;
        for (int i = 0; i < N; i += 1) {
            for (int j = 0; j < N; j += 1) {
                if (board[i][j] == 0) {
                    continue;
                }
                int temp = board[i][j];
                int rowOfTemp = (N * N - 1) / N;
                int colOfTemp = (N - (N * N - 1) % N);
                int tempCnt = (rowOfTemp + colOfTemp) - (i + j);
                cnt += tempCnt;
            }
        }
        return cnt;
    }
    /**Estimated distance to goal. This method should
     simply return the results of manhattan() when submitted to
     Gradescope.*/
    public int estimatedDistanceToGoal() {
        return manhattan();

    }
    /**Returns true if this board's tile values are the same
     position as y's*/
    public boolean equals(Object y) {
        if (this == y) {
            return true;
        }
        if (y.getClass() != this.getClass()) {
            return false;
        }
        Board bOfy = (Board) y;
        if (bOfy.N != this.N) {
            return false;
        }
        for (int i = 0; i < N; i += 1) {
            for (int j = 0; j < N; j += 1) {
                if (board[i][j] !=  bOfy.board[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
    public int hashCode() {
        return 0;
    }

    /** Returns the string representation of the board. 
      * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i, j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}

package hw4.puzzle;

import edu.princeton.cs.algs4.Queue;

public class Board implements WorldState {
    private int size;
    private int[][] board;
    private final int BLANK = 0;

    /**Constructs a board from an N-by-N array of tiles where
     tiles[i][j] = tile at row i, column j*/
    public Board(int[][] tiles) {
        size = tiles.length;
        board = new int[size][size];
        for (int i = 0; i < size; i += 1) {
            System.arraycopy(tiles[i], 0, board[i], 0, size);
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
        if (i < 0 || i > size - 1 || j < 0 || j > size - 1) {
            return false;
        }
        return true;
    }
    /**Returns the board size N*/
    public int size() {
        return size;
    }
    /**Returns the neighbors of the current board
     *
     * @author http://joshh.ug/neighbors.html*/
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }
    /**Hamming estimate described below
     * The number of tiles in the wrong position.*/
    public int hamming() {
        int cnt = 0;
        for (int i = 0; i < size; i += 1) {
            for (int j = 0; j < size; j += 1) {
                if (board[i][j] == 0) {
                    continue;
                }
                if (board[i][j] != i * size + 1 + j) {
                    cnt += 1;
                }
            }
        }
        return cnt;
    }
    /**Manhattan estimate described below
     * The sum of the Manhattan distances
     * (sum of the vertical and horizontal distance)
     * from the tiles to their goal positions.
     * Do not count 0.*/
    public int manhattan() {
        int cnt = 0;
        for (int i = 0; i < size; i += 1) {
            for (int j = 0; j < size; j += 1) {
                if (board[i][j] == 0) {
                    continue;
                }
                int temp = board[i][j];
                int rowOfTemp = (temp - 1) / size;
                int colOfTemp = (temp - 1) % size;
                int tempCnt = Math.abs(rowOfTemp - i) + Math.abs(colOfTemp - j);
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
        if (y == null || y.getClass() != this.getClass()) {
            return false;
        }
        if (this == y) {
            return true;
        }
        Board bOfy = (Board) y;
        if (bOfy.size != this.size) {
            return false;
        }
        for (int i = 0; i < size; i += 1) {
            for (int j = 0; j < size; j += 1) {
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

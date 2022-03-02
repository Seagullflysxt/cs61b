package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final int size;
    private boolean[][] grid;
    private WeightedQuickUnionUF wqu;
    //same with wqu's all operation,except union to bottom
    //using wqu2 to flag isFull();
    private WeightedQuickUnionUF wqu2;
    private int opendSites;

    private final int vtTop;
    private final int vtBottom;



    //// create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N < 0) {
            throw  new IllegalArgumentException();
        }
        size = N;
        grid = new boolean[N][N];
        wqu = new WeightedQuickUnionUF(N * N + 2);
        wqu2 = new WeightedQuickUnionUF(N * N + 1);

        opendSites = 0;
        vtTop = 0;
        vtBottom = N * N + 1;
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 0 || row > size - 1 || col < 0 || col > size - 1) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        if (grid[row][col]) {
            return;
        }
        grid[row][col] = true;
        opendSites += 1;
        int meIndex = xyTo1D(row, col);
        if (row == size - 1) {
            wqu.union(meIndex, vtBottom);
        }
        if (row == 0) {
            wqu.union(meIndex, vtTop);
            wqu2.union(meIndex, vtTop);

        }
        meUnionTo(row, col, row - 1, col);
        meUnionTo(row, col, row + 1, col);
        meUnionTo(row, col, row, col - 1);
        meUnionTo(row, col, row, col + 1);

    }

    private  void meUnionTo(int row, int col, int toRow, int toCol) {
        if (toRow < 0 || toRow > size - 1 || toCol < 0 || toCol > size - 1) {
            return;
        }

        int meIndex = xyTo1D(row, col);
        int toIndex = xyTo1D(toRow, toCol);
        if (isOpen(toRow, toCol)) {
            wqu.union(meIndex, toIndex);
            wqu2.union(meIndex, toIndex);
        }
    }

    //is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 0 || row > size - 1 || col < 0 || col > size - 1) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        return grid[row][col];
    }

    //is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 0 || row > size - 1 || col < 0 || col > size - 1) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        int meIndex = xyTo1D(row, col);
        //if using wqu, after the first full,all sites opend at bottom
        //will be connected to top that will lead to backwash.
        return wqu2.connected(meIndex, vtTop);
    }

    //number of open sites
    public int numberOfOpenSites() {
        return opendSites;
    }

    //does the system percolate?
    public boolean percolates() {
        return wqu.connected(vtTop, vtBottom);
    }

    private int xyTo1D(int row, int col) {
        if (row < 0 || row > size - 1 || col < 0 || col > size - 1) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        return size * row + col + 1;
    }

    //use for unit testing (not required)
    public static void main(String[] args) {

    }
}

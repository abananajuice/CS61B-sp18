package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.ArrayList;

public class Percolation {
    private static int scale;
    private WeightedQuickUnionUF grid;
    private boolean[][] grid_matrix;
    // crteate another grid to avoid back wash.
    private WeightedQuickUnionUF grid_antibackwash;
    private int vir_top;
    private int vir_bottom;

    private int openSite_num = 0;

    public Percolation(int N)                // create N-by-N grid, with all sites initially blocked
    {
        printAruException(N);
        scale = N;
        vir_top = scale * scale;
        vir_bottom = vir_top + 1;
        grid_matrix = new boolean[scale][scale];

        grid = new WeightedQuickUnionUF(N * N + 2);

        grid_antibackwash = new WeightedQuickUnionUF(N * N + 1);

    }

    public void open(int row, int col)       // open the site (row, col) if it is not open already
    {
        // add Exception
        printBouException(row);
        printBouException(col);


        // show neighbor sites' position.
        int next[][] = new int[][]{
                {0, 1},
                {1, 0},
                {0, -1},
                {-1, 0}
        };

        if (isOpen(row, col)) {
            return;
        }

        grid_matrix[row][col] = true;
        openSite_num += 1;


        // union neighbor sites
        for (int i = 0; i < 4; i++) {
            int row_n = row + next[i][0];
            int col_n = col + next[i][1];

            if ((col_n < scale) && (col_n >= 0)) {
                if (row_n == scale) {
                    grid.union(xyTo1d(row, col), vir_bottom);
                    continue;
                } else if (row_n == -1) {
                    grid.union(xyTo1d(row, col), vir_top);
                    grid_antibackwash.union(xyTo1d(row, col), vir_top);
                    continue;
                } else {
                    if (isOpen(row_n, col_n)) {
                        grid.union(xyTo1d(row, col), xyTo1d(row_n, col_n));
                        grid_antibackwash.union(xyTo1d(row, col), xyTo1d(row_n, col_n));
                    }
                }
            }
        }


    }

    public boolean isOpen(int row, int col)  // is the site (row, col) open?
    {
        printBouException(row);
        printBouException(col);
        return grid_matrix[row][col];
    }

    public boolean isFull(int row, int col)  // is the site (row, col) full?
    {
        printBouException(row);
        printBouException(col);
        if (isOpen(row, col)) {
            return grid_antibackwash.connected(xyTo1d(row, col), vir_top);
        }
        return false;
    }

    public int numberOfOpenSites()           // number of open sites
    {
        return openSite_num;
    }

    public boolean percolates()              // does the system percolate?
    {
        return grid.connected(vir_bottom, vir_top);
    }

    private int xyTo1d(int x, int y) {
        return x * scale + y;
    }


    private void printAruException(int x) {
        if (x <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
    }

    private void printBouException(int x) {
        if ((x < 0) || (x > (scale - 1))) {
            throw new java.lang.IndexOutOfBoundsException();
        }
    }

    public static void main(String[] args)   // use for unit testing (not required)
    {

    }
}

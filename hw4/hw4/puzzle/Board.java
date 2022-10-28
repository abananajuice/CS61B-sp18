package hw4.puzzle;

import edu.princeton.cs.algs4.Queue;

import java.lang.IndexOutOfBoundsException;

public class Board implements WorldState {

    int[][] title;
    private static int BLANK = 0;
    private int N;
    private int[][] goal;
    private int estdist;

    // Constructs a board from an N-by-N array of tiles where
    // tiles[i][j] = tile at row i, column j
    public Board(int[][] tiles) {
        if (tiles == null || tiles[0] == null || tiles.length != tiles[0].length) {
            throw new IllegalArgumentException();
        }

        estdist = -1;
        this.N = title[0].length;
        goal = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                this.title[i][j] = tiles[i][j];

                int goalV = i * N + j + 1;
                if (goalV == N * N) goalV = 0;
                goal[i][j] = goalV;
            }
        }
    }

    // Returns value of tile at row i, column j (or 0 if blank)
    public int tileAt(int i, int j) {

        if (i < 0 || i >= N || j < 0 || j >= N) {
            throw new IndexOutOfBoundsException();
        }
        return title[i][j];
    }

    // Returns the board size N
    public int size() {
        return N;
    }

    // Returns the neighbors of the current board
    // from:http://joshh.ug/neighbors.html
    @Override
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


    // Hamming estimate described below:The number of tiles in the wrong position.
    public int hamming() {
        int HamValue = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tileAt(i, j) != goal[i][j] && tileAt(i, j) != 0) {
                    HamValue += 1;
                }
            }
        }

        return HamValue;
    }

    // Manhattan estimate described below
    public int manhattan() {
        int ManhValue = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tileAt(i, j) != goal[i][j]) {
                    if (tileAt(i, j) == 0) {
                        continue;
                    } else {
                        ManhValue += Math.abs(i - (tileAt(i, j) - 1) / N) + Math.abs(j - (tileAt(i, j) - 1) % N);
                    }
                }
            }

        }
        return ManhValue;
    }

    // Estimated distance to goal. This method should
    // simply return the results of manhattan() when submitted to
    // Gradescope.
    @Override
    public int estimatedDistanceToGoal() {
        if (estdist == -1) {
            estdist = manhattan();
        }
        return estdist;

    }

    // Returns true if this board's tile values are the same
    // position as y's
    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null || this.getClass() != y.getClass()) return false;

        Boolean res = true;
        Board nb = (Board) y;

        if (this.size() != nb.size()) return false;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (this.tileAt(i, j) != nb.tileAt(i, j)) {
                    res = false;
                    return res;
                }
            }
        }
        return res;
    }

    /**
     * Returns the string representation of the board.
     * Uncomment this method.
     */
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

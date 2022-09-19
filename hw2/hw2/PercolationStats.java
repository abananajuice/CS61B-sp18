package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private int trial;
    private double[] result;
    private double res_mean;
    private double res_stddev;

    public PercolationStats(int N, int T, PercolationFactory pf)   // perform T independent experiments on an N-by-N grid
    {
        if ((N <= 0) || (T <= 0)) {
            throw new java.lang.IllegalArgumentException();
        }

        trial = T;
        result = new double[T];

        // T times trials
        for (int times = 0; times < trial; T++) {
            StdRandom.setSeed(times);
            Percolation newgrid = pf.make(N);
            while (newgrid.percolates()) {
                int row = StdRandom.uniform(0, N);
                int col = StdRandom.uniform(0, N);

                if (!newgrid.isOpen(row, col)) {
                    newgrid.open(row, col);
                }
            }
            result[times] = ((double) newgrid.numberOfOpenSites()) / (N * N);
        }

        res_mean = mean();
        res_stddev = stddev();

        System.out.println(confidenceLow());
        System.out.println(confidenceHigh());


    }

    public double mean()                                           // sample mean of percolation threshold
    {
        return StdStats.mean(result);
    }

    public double stddev()                                         // sample standard deviation of percolation threshold
    {
        return StdStats.stddev(result);
    }

    public double confidenceLow()                                  // low endpoint of 96% confidence interval
    {
        return res_mean - (1.96 * res_stddev) / (Math.sqrt(trial));
    }

    public double confidenceHigh()                                 // high endpoint of 95% confidence interval
    {
        return res_mean + (1.96 * res_stddev) / (Math.sqrt(trial));
    }
}

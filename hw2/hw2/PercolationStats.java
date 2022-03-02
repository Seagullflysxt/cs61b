package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;


public class PercolationStats {

    private Percolation P;
    private final double[] thresholds;
    //private double singleThreshold;
    //private double threshold;
    private double  meanOfThresh;
    private double devOfThresh;


    //perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        meanOfThresh = 0.0;
        devOfThresh = 0.0;
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        thresholds = new double[T];
        for (int i = 0; i < T; i += 1) {
            P = pf.make(N);
            while (!P.percolates()) {
                int randomRow = StdRandom.uniform(0, N);
                int randomCol = StdRandom.uniform(0, N);
                P.open(randomRow, randomCol);
            }
            double opendSites = P.numberOfOpenSites();
            double singleThreshold = opendSites / (N * N);
            thresholds[i] = singleThreshold;
        }
    }
    //sample mean of percolation threshold
    public double mean() {
        meanOfThresh = StdStats.mean(thresholds);
        return meanOfThresh;
    }
    //sample standard deviation of percolation threshold
    public double stddev() {
        devOfThresh = StdStats.stddev(thresholds);
        return devOfThresh;
    }

    //low endpoint of 95% confidence interval
    public double confidenceLow() {
        return meanOfThresh
                - (1.96 * devOfThresh) / (Math.sqrt(thresholds.length));
    }

    //high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return meanOfThresh
                + (1.96 * devOfThresh) / (Math.sqrt(thresholds.length));
    }

}

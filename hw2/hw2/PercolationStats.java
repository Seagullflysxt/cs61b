package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;


public class PercolationStats {

    private Percolation P;
    private double[] thresholds;
    private double singleThreshold;
    private double threshold;
    private double meanOfThresh;
    private double devOfThresh;


    //perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {

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
            singleThreshold = opendSites / (N * N);
            thresholds[i] = singleThreshold;
        }


    }
    //sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(thresholds);
    }
    //sample standard deviation of percolation threshold
    public double stddev() {

        return StdStats.stddev(thresholds);
    }

    //low endpoint of 95% confidence interval
    public double confidenceLow() {
        meanOfThresh = mean();
        devOfThresh = stddev();

        return meanOfThresh
                - (1.96 * Math.sqrt(devOfThresh)) / (Math.sqrt(thresholds.length));
    }

    //high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return meanOfThresh
                + (1.96 * Math.sqrt(devOfThresh)) / (Math.sqrt(thresholds.length));
    }

}

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {

    private double[] fractions; // Double array containing the perc. thresholds
    private int trials; // Amount of trials
    // Fixed 1.96 for 95% confidence interval
    private final double CONFIDENCE_95 = 1.96;


    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) throw new
                IllegalArgumentException("One of the arguments is <= 0.");
        this.fractions = new double[trials];
        this.trials = trials;
        double size = n * n; // The n x n size of the array.
        for (int i = 0; i < trials; i++) { // Loop through trial amounts
            Percolation percolation = new Percolation(n);
            // Loop through until it percolates
            while (!percolation.percolates()) {
                int row = StdRandom.uniform(0, n); // Random row
                int col = StdRandom.uniform(0, n); // Random col
                percolation.open(row, col); // Open the random spot
            }
            // Once done, store the threshold in the fractions array
            fractions[i] = (percolation.numberOfOpenSites() / (size));
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(fractions);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(fractions);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        double mean = mean();
        double stddev = stddev();
        return mean - ((CONFIDENCE_95 * stddev) / Math.sqrt(trials));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        double mean = mean();
        double stddev = stddev();
        return mean + ((CONFIDENCE_95 * stddev) / Math.sqrt(trials));
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        Stopwatch stopwatch = new Stopwatch();
        PercolationStats percstats = new PercolationStats(n, t);
        double time = stopwatch.elapsedTime();
        StdOut.printf("mean() %14s%.6f%n", " = ", percstats.mean());
        StdOut.printf("stddev() %12s%.6f%n", " = ", percstats.stddev());
        StdOut.printf("confidenceLow() %5s%.6f%n", " = ",
                      percstats.confidenceLow());
        StdOut.printf("confidenceHigh() %4s%.6f%n", " = ",
                      percstats.confidenceHigh());
        StdOut.printf("elapsed time %8s%f%n", " = ", time);
    }
}
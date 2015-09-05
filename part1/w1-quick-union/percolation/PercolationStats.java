import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	private double[] estimate_values;
	private int T;

	public PercolationStats(int N, int T)     // perform T independent experiments on an N-by-N grid
	{
		if (N <= 0 || T <= 0) {
			throw new java.lang.IllegalArgumentException ();
		}
		this.T = T;
		estimate_values = new double [T];
		for (int i = 0; i < T; i++) {
			Percolation p = new Percolation (N);
			estimate_values [i] = estimatePercolate (p, N);
		}
	}

	private double estimatePercolate (Percolation p, int N) {
		int j = 0;

		while (p.percolates () == false) {
			j++;
			while (true) {
				int row = StdRandom.uniform (1, N + 1);
				int col = StdRandom.uniform (1, N + 1);
				if (p.isOpen (row, col) == false) {
					p.open (row, col);
					break;
				}
			}
		}

		return (double) j / N / N;
	}

	public double mean()                      // sample mean of percolation threshold
	{
		return StdStats.mean (estimate_values);
	}
	public double stddev()                    // sample standard deviation of percolation threshold
	{
		return StdStats.stddev (estimate_values);
	}
	public double confidenceLo()              // low  endpoint of 95% confidence interval
	{
		return mean () - 1.96 * stddev () / Math.sqrt (T);
	}
	public double confidenceHi()              // high endpoint of 95% confidence interval
	{
		return mean () + 1.96 * stddev () / Math.sqrt (T);
	}

	public static void main(String[] args)    // test client (described below)
	{
		if (args.length < 2) {
			throw new java.lang.IllegalArgumentException ();
		}
		else {
			int N = Integer.parseInt (args[0]);
			int T = Integer.parseInt (args[1]);
			PercolationStats ps = new PercolationStats (N, T);
			System.out.println ("mean = " + ps.mean ());
			System.out.println ("stddev = " + ps.stddev ());
			System.out.println ("95% confidence interval = " + ps.confidenceLo () + ", " + ps.confidenceHi ());
		}
	}
}
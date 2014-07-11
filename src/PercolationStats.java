public class PercolationStats {

	private int times;
	private double thresholds[];
	private double var;
	private double mean;
	private double stddev;

	public PercolationStats(int N, int T) { // perform T independent
											// computational experiments on an
											// N-by-N grid
		if (N <= 0 || T <= 0) {
			throw new IllegalArgumentException(String.format(
					"%d and %d must greater than 0", N, T));
		}
		times = T;
		thresholds = new double[T];
		var = 0;
		mean = 0;
		stddev = 0;
		perform(N);
	}

	private void perform(int N) {

		int totalSites = N * N;
		for (int i = 0; i < times; i++) {
			Percolation p = new Percolation(N);
			int openSites = 0;
			while (!p.percolates()) {
				int randI = randomIndex(N);
				int randJ = randomIndex(N);
				if (!p.isOpen(randI, randJ)) {
					p.open(randI, randJ);
					openSites++;
				}
			}
			thresholds[i] = (double) openSites / totalSites;
		}

	}

	public double mean() { // sample mean of percolation threshold
		if (mean == 0) {
			mean = StdStats.mean(thresholds);
		}
		return mean;
	}

	public double stddev() { // sample standard deviation of percolation
								// threshold
		if (stddev == 0) {
			stddev = StdStats.stddev(thresholds);
		}
		return stddev;
	}

	public double confidenceLo() { // returns lower bound of the 95% confidence
									// interval
		if (var == 0) {
			var = 1.96 * stddev() / Math.sqrt(times);
		}
		return mean() - var;
	}

	public double confidenceHi() { // returns upper bound of the 95% confidence
									// interval
		if (var == 0) {
			var = 1.96 * stddev() / Math.sqrt(times);
		}
		return mean() + var;
	}

	public static void main(String[] args) { // test client, described below
		if (args.length != 2) {
			Usage();
		} else {
			int N = 1, T = 1;
			try {
				N = Integer.parseInt(args[0]);
				T = Integer.parseInt(args[1]);
			} catch (Exception e) {
				Usage();
			}
			PercolationStats ps = new PercolationStats(N, T);
			StdOut.println("mean\t\t\t\t= " + ps.mean());
			StdOut.println("stddev\t\t\t\t= " + ps.stddev());
			StdOut.println("95% confidence interval = " + ps.confidenceLo()
					+ "," + ps.confidenceHi());
		}
	}

	private static void Usage() {
		StdOut.println("java " + PercolationStats.class.getName()
				+ " GridSize ComputationTimes");
		System.exit(0);
	}

	private int randomIndex(int max) {
		return StdRandom.uniform(max) + 1;
	}
}

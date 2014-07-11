import java.io.IOException;

public class Percolation {
	private WeightedQuickUnionUF uf;
	private int gridNum;
	private boolean[][] grid;
	private int topSite, bottomSite;

	public Percolation(int N) { // create N-by-N grid, with all sites blocked
		if (N <= 0) {
			throw new IllegalArgumentException();
		}
		uf = new WeightedQuickUnionUF(N * N + 2);
		topSite = N * N;
		bottomSite = topSite + 1;
		grid = new boolean[N][N];
		gridNum = N;
		for (int i = 1; i <= N; i++) {
			uf.union(i - 1, topSite);
			uf.union(bottomSite, toIndex(gridNum, i));
		}
	}

	public void open(int i, int j) { // open site (row i, column j) if it is not
										// already
		if (!isOpen(i, j)) {
			grid[i - 1][j - 1] = true;
			int index = toIndex(i, j);
			// up
			checkAndUnion(index, i - 1, j);
			// right
			checkAndUnion(index, i, j + 1);
			// down
			checkAndUnion(index, i + 1, j);
			// left
			checkAndUnion(index, i, j - 1);
		}
	}

	private void checkAndUnion(int p, int i, int j) {
		if (checkOpenAndInBounds(i, j)) {
			uf.union(p, toIndex(i, j));
		}
	}

	public boolean isOpen(int i, int j) { // is site (row i, column j) open?
		checkInBounds(i, j);
		return grid[i - 1][j - 1];
	}
	//FIXME This API will get wrong answer when it is percolate.
	public boolean isFull(int i, int j) { // is site (row i, column j) full?
		if (isOpen(i, j)) {
			return uf.connected(topSite, toIndex(i, j));
		} else {
			return false;
		}
	}

	public boolean percolates() { // does the system percolate?
		return uf.connected(topSite, bottomSite);
	}

	private void checkInBounds(int i, int j) {
		if (!isInBounds(i) || !(isInBounds(j))) {
			throw new IndexOutOfBoundsException(String.format(
					"%d or %d out of bounds 1..N (%d)", i, j, gridNum));
		}
	}

	private boolean isInBounds(int i) {
		return (i >= 1 && i <= gridNum);
	}

	private boolean checkOpenAndInBounds(int i, int j) {
		return isInBounds(i) && isInBounds(j) && isOpen(i, j);
	}

	private int toIndex(int i, int j) {
		return (i - 1) * gridNum + (j - 1);
	}

	public static void main(String[] args) throws IOException {

	}
}

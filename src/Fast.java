import java.util.Arrays;
import java.util.Comparator;

public class Fast {

	private static final int K = 3; // adjacent points
	private Point[] points;
	private int maxFoundKeyIndex = 0;

	private void createPoints(int num) {
		points = new Point[num];
	}

	private int rank(double key, int i, int hi) {
		int lo = 0;
		Point point = points[i];
		while (lo <= hi) {
			// Key is in a[lo..hi] or not present.
			int mid = lo + (hi - lo) / 2;
			double value = point.slopeTo(points[mid]);
			if (key < value)
				hi = mid - 1;
			else if (key > value)
				lo = mid + 1;
			else
				return mid;
		}
		return -1;
	}

	private boolean printPoints(int pIndex, int qIndex, int indexRange,
			double slopValue) {
		Point point = points[pIndex];
		Comparator<Point> comparator = point.SLOPE_ORDER;
		if (maxFoundKeyIndex > 0) {
			Arrays.sort(points, 0, maxFoundKeyIndex, comparator);
			int index = rank(slopValue, pIndex, maxFoundKeyIndex - 1);
			if (index != -1) {
				return false;
			}
		}
		StdOut.print(point);
		Point point2 = null;
		for (int m = 0; m <= indexRange; m++) {
			point2 = points[qIndex - indexRange + m];
			StdOut.print(" -> " + point2);
		}
		point.drawTo(point2);
		StdOut.println();
		if (indexRange == K - 1)
			return false;
		return true;
	}

	public static void main(String[] args) { // test client, described below
		if (args.length != 1) {
			Usage();
		} else {
			String name = args[0];

			In in = new In(name);
			int num = in.readInt();
			Fast fast = new Fast();
			fast.createPoints(num);
			Point[] points = fast.points;
			StdDraw.setXscale(0, 32768);
			StdDraw.setYscale(0, 32768);
			for (int i = 0; i < num; i++) {
				int x = in.readInt();
				int y = in.readInt();
				points[i] = new Point(x, y);
				points[i].draw();
			}
			in.close();
			for (int i = 0; i < num - K; i++) {
				Arrays.sort(points, i, num);
				Point point = points[i];
				Comparator<Point> comparator = point.SLOPE_ORDER;
				Arrays.sort(points, i + 1, num, comparator);
				int k = 0;
				double value, oldvalue = 0;
				boolean savePoints = false;
				for (int j = i + 1; j < num - 1; j++) {
					value = point.slopeTo(points[j]);
					if (value == point.slopeTo(points[j + 1])) {
						k++;
						oldvalue = value;
					} else {
						if (k >= K - 1) {

							if (fast.printPoints(i, j, k, oldvalue)) {
								savePoints = true;
							}

						}
						k = 0;
					}
				}
				if (k >= K - 1) {
					if (fast.printPoints(i, num - 1, k, oldvalue)) {
						savePoints = true;
					}
				}
				if (savePoints) {

					if (i > fast.maxFoundKeyIndex) {
						points[i] = points[fast.maxFoundKeyIndex];
						points[fast.maxFoundKeyIndex] = point;
					}
					fast.maxFoundKeyIndex++;
				}
			}
		}
	}

	private static void Usage() {
		StdOut.println("java " + Fast.class.getName() + " filename");
		System.exit(0);
	}

}

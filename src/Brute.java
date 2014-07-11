import java.util.Arrays;

public class Brute {

	public static void main(String[] args) { // test client, described below
		if (args.length != 1) {
			Usage();
		} else {
			String name = args[0];
			In in = new In(name);
			int num = in.readInt();
			Point[] points = new Point[num];
			StdDraw.setXscale(0, 32768);
			StdDraw.setYscale(0, 32768);
			for (int i = 0; i < num; i++) {
				int x = in.readInt();
				int y = in.readInt();
				points[i] = new Point(x, y);
				points[i].draw();
			}
			in.close();
			Arrays.sort(points);
			for (int i = 0; i < num - 3; i++) {
				Point point = points[i];
				for (int j = i + 1; j < num - 2; j++) {
					Point point2 = points[j];
					for (int k = j + 1; k < num - 1; k++) {
						Point point3 = points[k];
						for (int m = k + 1; m < num; m++) {
							Point point4 = points[m];
							if (point.slopeTo(point2) == point.slopeTo(point3)
									&& point.slopeTo(point2) == point
											.slopeTo(point4)) {
								StdOut.println(point + " -> " + point2 + " -> "
										+ point3 + " -> " + point4);
								point.drawTo(point4);
							}
						}
					}
				}
			}
		}
	}

	private static void Usage() {
		StdOut.println("java " + Brute.class.getName() + " filename");
		System.exit(0);
	}

}

/*************************************************************************
 * Name:Yi Wei
 * Email:sc.yiwei@gmail.com
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {

	// compare points by slope
	public final Comparator<Point> SLOPE_ORDER = new Comparator<Point>() {

		@Override
		public int compare(Point o1, Point o2) {
			// TODO Auto-generated method stub
			double value = slopeTo(o1) - slopeTo(o2);
			if (value < 0)
				return -1;
			if (value > 0)
				return 1;
			else {
				return 0;
			}
		}
	}; // YOUR DEFINITION HERE

	private final int x; // x coordinate
	private final int y; // y coordinate

	// create the point (x, y)
	public Point(int x, int y) {
		/* DO NOT MODIFY */
		this.x = x;
		this.y = y;
	}

	// plot this point to standard drawing
	public void draw() {
		/* DO NOT MODIFY */
		StdDraw.point(x, y);
	}

	// draw line between this point and that point to standard drawing
	public void drawTo(Point that) {
		/* DO NOT MODIFY */
		StdDraw.line(this.x, this.y, that.x, that.y);
	}

	// slope between this point and that point
	public double slopeTo(Point that) {
		final double result = (double) (that.y - this.y) / (that.x - this.x);
		if (result == Double.NEGATIVE_INFINITY) {
			return Double.POSITIVE_INFINITY;
		} else if (Double.isNaN(result)) {
			return Double.NEGATIVE_INFINITY;
		} else {
			if (this.y == that.y)
				return 0;
			return result;
		}
	}

	// is this point lexicographically smaller than that one?
	// comparing y-coordinates and breaking ties by x-coordinates
	public int compareTo(Point that) {
		if (y < that.y)
			return -1;
		if (y > that.y)
			return 1;
		if (x < that.x)
			return -1;
		if (x > that.x)
			return 1;
		return 0;
	}

	// return string representation of this point
	public String toString() {
		/* DO NOT MODIFY */
		return "(" + x + ", " + y + ")";
	}

	// unit test
	public static void main(String[] args) {
		Point p = new Point(2, 4);
		Point q = new Point(4, 2);
		Point r = new Point(2, 4);
		StdOut.println(p.SLOPE_ORDER.compare(q, r));
		p = new Point(31245, 23089);
		q = new Point(29323, 23089);
		StdOut.println(p.slopeTo(q));
		StdOut.println(q.slopeTo(p));
		p = new Point(5, 5);
		q = new Point(5, 0);
		r = new Point(5, 9);
		StdOut.println(p.slopeTo(q));
		StdOut.println(p.slopeTo(r));
		StdOut.println(p.SLOPE_ORDER.compare(q, r));
	}
}

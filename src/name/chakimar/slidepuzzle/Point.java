package name.chakimar.slidepuzzle;

public class Point {

	private int x;
	private int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Point) {
			Point p = (Point)obj;
			if (p.getX() == this.x && p.getY() == this.y) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = this.x * 10 + this.y;
		return hashCode;
	}

	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}
	
}

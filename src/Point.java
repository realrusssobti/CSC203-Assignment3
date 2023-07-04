final class Point {
    public final int x;
    public final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static boolean adjacent(Point p1, Point p2) {
        // check if the points are adjacent
        if (p1.x + 1 == p2.x && p1.y == p2.y) {
            return true;
        } else if (p1.x - 1 == p2.x && p1.y == p2.y) {
            return true;
        } else if (p1.x == p2.x && p1.y + 1 == p2.y) {
            return true;
        } else return p1.x == p2.x && p1.y - 1 == p2.y;
    }

    public String toString() {
        return "(" + x + "," + y + ")";
    }

    public boolean equals(Object other) {
        return other instanceof Point &&
                ((Point) other).x == this.x &&
                ((Point) other).y == this.y;
    }

    public int hashCode() {
        int result = 17;
        result = result * 31 + x;
        result = result * 31 + y;
        return result;
    }

    public boolean adjacent(Point p2) {
        // check if the points are adjacent
        if (this.x + 1 == p2.x && this.y == p2.y) {
            return true;
        } else if (this.x - 1 == p2.x && this.y == p2.y) {
            return true;
        } else if (this.x == p2.x && this.y + 1 == p2.y) {
            return true;
        } else return this.x == p2.x && this.y - 1 == p2.y;

    }
}

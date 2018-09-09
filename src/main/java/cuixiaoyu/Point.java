package cuixiaoyu;

public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static double getDistance(Point p1, Point p2) {
        int xx = Math.abs(p1.x - p2.x);
        int yy = Math.abs(p1.y - p2.y);

        return Math.sqrt(xx*xx + yy*yy);
    }

    public static void main(String[] args) {
        System.out.println(getDistance(new Point(1, 2), new Point(1, 3)));
    }
}

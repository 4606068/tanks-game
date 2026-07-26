package models;

public record Point(int x, int y) {
    public static Point difference(Point point1, Point point2) {
        return new Point(Math.abs(point1.x - point2.x), Math.abs(point1.y - point2.y));
    }

    /*public static int makeDifference(Point staticPoint, Point mobilePoint, int difference) {

    }*/
}

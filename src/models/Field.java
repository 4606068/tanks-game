package models;

import java.awt.*;

public class Field {
    final int WIDTH;
    final int HEIGHT;
    static final Color COLOR = Color.LIGHT_GRAY;
    //private int[]

    public Field(int width, int height) {
        WIDTH = width;
        HEIGHT = height;
    }

    public Point getPlayerTankStartPosition() {
        int x = WIDTH / 2;
        int y = HEIGHT - Tank.SIDE / 2;
        return new Point(x, y);
    }

    public Point[] getEnemyTankStartPositions() {
        Point[] startPositions = new Point[3];

        int x1 = Tank.SIDE / 2;
        int x2 = WIDTH / 2;
        int x3 = WIDTH - Tank.SIDE / 2;
        int y = Tank.SIDE / 2;

        startPositions[0] = new Point(x1, y);
        startPositions[1] = new Point(x2, y);
        startPositions[2] = new Point(x3, y);

        return startPositions;
    }

}
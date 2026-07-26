package models;

import java.awt.*;

public class Bullet {
    private static final int SIDE = Tank.SIDE / 5;
    private static final int SPEED = 4;
    private static final Color COLOR = Color.WHITE;
    private Point position;
    private Direction direction;
    private boolean isOnField;


    public Bullet() {
        isOnField = false;
    }

    public Bullet(Point position, Direction direction) {
        this.position = position;
        isOnField = true;
        this.direction = direction;
    }

    public static int getSide() {
        return SIDE;
    }

    public static Color getColor() {
        return COLOR;
    }

    /*public static int getSpeed() {
        return speed;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }*/

    public boolean isOnField() {
        return isOnField;
    }

    public void checkVisibility(Field field) {
        boolean visible;
        visible = ((position.x() >  -SIDE / 2) &&
                (position.x() < field.WIDTH + SIDE / 2) &&
                (position.y() > -SIDE / 2) &&
                (position.y() < field.HEIGHT + SIDE / 2));
        isOnField = visible;
    }

    public Point getTopLeftPoint() {
        int topLeftX = position.x() - SIDE / 2;
        int topLeftY = position.y() - SIDE / 2;
        return new Point(topLeftX, topLeftY);
    }

    public void move() {
        int x = position.x();
        int y = position.y();
        switch (direction) {
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y+= SPEED;
                break;
            case LEFT:
                x -= SPEED;
                break;
        }
        position = new Point(x, y);
    }

}

package models;

public abstract class Tank {
    static final int SIDE = 40;
    private Point position;
    private Direction direction;
    private Bullet bullet;

    public Tank(Point position, Direction direction) {
        this.position = position;
        this.direction = direction;
        bullet = new Bullet();
    }
    public Bullet getBullet() {
        return bullet;
    }
    public Direction getDirection() {
        return direction;
    }
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Point getPosition() {
        return position;
    }

    public Point getTracksTopLeftPoint() {
        return new Point(position.x() - SIDE / 2, position.y() - SIDE / 2);
    }

    public Point getBaseTopLeftPoint() {
        int x = position.x();
        int y = position.y();
        switch (direction) {
            case UP, DOWN -> {
                x -= SIDE * 3 / 10;
                y -= SIDE / 2;
            }
            case RIGHT, LEFT -> {
                x -= SIDE / 2;
                y -= SIDE * 3 / 10;
            }
        }
        return new Point(x, y);
    }
    public int getBaseWidth() {
        return switch (direction) {
            case UP, DOWN -> SIDE * 3 / 5;
            case RIGHT, LEFT -> SIDE;
        };
    }
    public int getBaseHeight() {
        return switch (direction) {
            case UP, DOWN -> SIDE;
            case RIGHT, LEFT -> SIDE * 3 / 5;
        };
    }
    public Point getBarrelTopLeftPoint() {
        int x = position.x();
        int y = position.y();
        switch (direction) {
            case UP -> {
                x -= SIDE / 10;
                y -= SIDE;
            }
            case LEFT -> {
                x -= SIDE;
                y -= SIDE / 10;
            }
            case DOWN -> {
                x -= SIDE / 10;
            }
            case RIGHT -> {
                y -= SIDE / 10;
            }
        }
        return new Point(x, y);
    }
    public int getBarrelWidth() {
        return switch (direction) {
            case UP, DOWN -> SIDE / 5;
            case RIGHT, LEFT -> SIDE;
        };
    }
    public int getBarrelHeight() {
        return switch (direction) {
            case UP, DOWN -> SIDE;
            case RIGHT, LEFT -> SIDE / 5;
        };
    }

    public void move(Direction direction, int speed, Field field) {
        setDirection(direction);
        int x = position.x();
        int y = position.y();
        switch (direction) {
            case UP -> y = Math.max(y - speed, SIDE / 2);
            case RIGHT -> x = Math.min(x + speed, field.WIDTH - SIDE / 2);
            case DOWN -> y = Math.min(y + speed, field.HEIGHT - SIDE / 2);
            case LEFT -> x = Math.max(x - speed, SIDE / 2);
        }
        position = new Point(x, y);
    }

    public void fireAShot() {
        bullet = new Bullet(getBulletStartPosition(), direction);
    }

    private Point getBulletStartPosition() {
        int x = 0;
        int y = 0;

        switch (direction) {
            case UP -> {
                x = position.x();
                y = position.y() - SIDE - Bullet.getSide() / 2;
            }
            case RIGHT -> {
                x = position.x() + SIDE + Bullet.getSide() / 2;
                y = position.y();
            }
            case DOWN -> {
                x = position.x();
                y = position.y() + SIDE + Bullet.getSide() / 2;
            }
            case LEFT -> {
                x = position.x() - SIDE - Bullet.getSide() / 2;
                y = position.y();
            }
        }

        return new Point(x, y);
    }

    public boolean bulletIsOnField() {
        return bullet.isOnField();
    }

    public void moveBullet(Field field) {
        bullet.move();
        bullet.checkVisibility(field);
    }

}

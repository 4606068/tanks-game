package models;

import java.awt.*;

public class EnemyTank extends Tank {
    private boolean onField = false;
    private boolean alive = true;
    final static int SPEED = 2;
    final static Color MAIN_COLOR = Color.DARK_GRAY;
    final static Color TRACKS_COLOR = Color.BLACK;
    final int indexInArray;

    public EnemyTank(Point position, int index) {
        super(position, Direction.getRandomDirection());
        indexInArray = index;
    }

    public boolean isOnField() {
        return onField;
    }
    public void setOnField(boolean onField) {
        this.onField = onField;
    }

    public void move(Field field) {
        Point prevPosition = this.getPosition();
        this.move(this.getDirection(), SPEED, field);
        Point newPosition = this.getPosition();
        if (prevPosition.equals(newPosition)) {
            this.setDirection(Direction.getRandomDirection());
            move(field);
        }
    }
}

package models;


import java.awt.*;

public class PlayerTank extends Tank {
    final static int SPEED = 3;
    final static Color MAIN_COLOR = Color.GREEN;
    final static Color TRACKS_COLOR = Color.GRAY;
    public PlayerTank(Point position) {
        super(position, Direction.UP);

    }
}

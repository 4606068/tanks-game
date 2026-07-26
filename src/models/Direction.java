package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public enum Direction {
    UP,
    DOWN,
    RIGHT,
    LEFT;
    private static final ArrayList<Direction> directions = new ArrayList<>(Arrays.asList(UP, DOWN, RIGHT, LEFT));

    public static Direction getRandomDirection() {
        Collections.shuffle(directions);
        return directions.get(0);
    }
}

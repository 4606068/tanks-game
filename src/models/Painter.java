package models;

import java.awt.*;

public class Painter {
    private static Graphics g;

    public static void setGraphics(Graphics g) {
        Painter.g = g;
    }

    public static void paintField(Field field) {
        g.setColor(Field.COLOR);
        g.fillRect(0, 0, field.WIDTH, field.HEIGHT);
    }

    public static void paintTank(Tank tank) {
        Color mainColor;
        Color tracksColor;

        if (tank instanceof PlayerTank) {
            mainColor = PlayerTank.MAIN_COLOR;
            tracksColor = PlayerTank.TRACKS_COLOR;
        }
        else {
            mainColor = EnemyTank.MAIN_COLOR;
            tracksColor =EnemyTank.TRACKS_COLOR;
        }

        g.setColor(tracksColor);
        paintTankTracks(tank);
        g.setColor(mainColor);
        paintTankBase(tank);
        paintTankBarrel(tank);
    }

    private static void paintTankTracks(Tank tank) {
        Point topLeftPoint = tank.getTracksTopLeftPoint();
        g.fillRect(topLeftPoint.x(), topLeftPoint.y(), Tank.SIDE, Tank.SIDE);
    }

    private static void paintTankBase(Tank tank) {
        Point topLeftPoint = tank.getBaseTopLeftPoint();
        g.fillRect(topLeftPoint.x(), topLeftPoint.y(), tank.getBaseWidth(), tank.getBaseHeight());
    }

    private static void paintTankBarrel(Tank tank) {
        Point topLeftPoint = tank.getBarrelTopLeftPoint();
        g.fillRect(topLeftPoint.x(), topLeftPoint.y(), tank.getBarrelWidth(), tank.getBarrelHeight());
    }

    public static void paintBullet(Bullet bullet) {
        g.setColor(Bullet.getColor());
        Point topLeftPoint = bullet.getTopLeftPoint();
        g.fillOval(topLeftPoint.x(), topLeftPoint.y(), Bullet.getSide(), Bullet.getSide());
    }

}

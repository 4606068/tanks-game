package models;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static models.Direction.*;

// штаб, дочерние пули (енум тип пуль), класс блока, столкновение объектов

public class Game extends JPanel implements ActionListener, KeyListener {
    private Field field;
    private PlayerTank playerTank;
    private EnemyTank[] enemyTanks = new EnemyTank[5];
    private int enemiesOnField = 0;
    private final Timer timer;
    private boolean gameOver;
    public Game() {
        gameOver = false;
        addKeyListener(this);
        setFocusable(true); //???
        setFocusTraversalKeysEnabled(false); //???
        timer = new Timer(1, this);

        field = new Field(800, 800);
        frames(this);//???

        createPlayer();
        createEnemies();

        timer.start();
    }

    public static void main(String[] args) {
        Game game = new Game();
    }

    public void frames(Game game) {
        JFrame frame = new JFrame("Game");
        frame.add(game);
        frame.setSize(field.WIDTH + 16, field.HEIGHT + 39);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        //super.paintComponent(g);
        Painter.setGraphics(g);

        Painter.paintField(field);

        Painter.paintTank(playerTank);
        if (playerTank.bulletIsOnField())
            Painter.paintBullet(playerTank.getBullet());

        for(EnemyTank enemyTank : enemyTanks) {
            if (enemyTank.isOnField()) {
                Painter.paintTank(enemyTank);
                if (enemyTank.bulletIsOnField()) {
                    Painter.paintBullet(enemyTank.getBullet());
                }
            }
        }

        if (gameOver) {
            timer.stop();
        }
    }

    @Override
    public void keyPressed (KeyEvent e) {
        int key = e.getKeyCode();
        if (!gameOver) {
            switch (key) {
                case KeyEvent.VK_LEFT, KeyEvent.VK_A:
                    playerTank.move(LEFT, PlayerTank.SPEED, field);
                    break;
                case KeyEvent.VK_RIGHT, KeyEvent.VK_D:
                    playerTank.move(RIGHT, PlayerTank.SPEED, field);
                    break;
                case KeyEvent.VK_UP, KeyEvent.VK_W:
                    playerTank.move(UP, PlayerTank.SPEED, field);
                    break;
                case KeyEvent.VK_DOWN, KeyEvent.VK_S:
                    playerTank.move(DOWN, PlayerTank.SPEED, field);
                    break;
                case KeyEvent.VK_SPACE:
                    if (!playerTank.bulletIsOnField())
                        playerTank.fireAShot();
            }
        }
    }

    @Override
    public void actionPerformed (ActionEvent e) {
        if (!gameOver) {
            if (playerTank.bulletIsOnField()) {
                playerTank.moveBullet(field);
            }
            for (EnemyTank enemyTank : enemyTanks) {
                if (enemyTank.isOnField()) {
                    if (enemyTank.bulletIsOnField()) {
                        enemyTank.moveBullet(field);
                    }
                    else {
                        enemyTank.fireAShot();
                    }
                    enemyTank.move(field);
                }
            }
            repaint();
        }
        else {
            Game game = new Game();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {} //???

    @Override
    public void keyReleased(KeyEvent e) {} //???

    public void createPlayer() {
        Point position = field.getPlayerTankStartPosition();
        playerTank = new PlayerTank(position);
    }

    public void createEnemies() {
        for (int i = 0; i < enemyTanks.length; i++) {
            enemyTanks[i] = new EnemyTank(field.getEnemyTankStartPositions()[i%3], i);
            if (i < 3) {
                enemyTanks[i].setOnField(true);
                enemiesOnField++;
            }
        }
    }


    /*public Point move(PlayerTank playerTank, Direction direction) {
        Point futurePoint = playerTank.move(direction, PlayerTank.SPEED);
        //(move returns only the point and doesn't change the position)
        //checkField (returns max or min depends on the direction)
            {
                int x = futurePoint.x();
                int y = futurePoint.y();
                switch (direction) {
                    case ->
                } y = max(y, obj.side / 2)
                left -> x = max(x, obj.side / 2)
                down -> y = min(y, field.height - obj.side / 2)
                right -> x = min(x, field.width - obj.side / 2)
                fp = new Point(x, y)
            }
    }*/

    /*private Point checkMovement() {
        if (obj instanceof PlayerTank) {
            obj = (PlayerTank)obj;
            for (int i = 0; i < enemyTanks)
        }
        else if (obj instanceof EnemyTank) {
            obj = (EnemyTank)obj;
        }
        return new Point(0, 0);
    }*/
}

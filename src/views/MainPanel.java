package views;

import models.Enemy;
import models.IGame;
import presenters.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class MainPanel extends JPanel {

    private BufferedImage background;

    public MainPanel(KeyListener keyListener) {
        addKeyListener(keyListener);
    }

    public void init() {
        paintBackground();
        requestFocus();
        requestFocusInWindow();
    }

    public void updateGame(IGame gameData) {
        paintBackground();
        paintHero(gameData);
        paintEnemies(gameData);
        repaint();
    }

    private void paintBackground() {
        if (background == null) {
            background = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        }
        Graphics g = background.getGraphics();
        g.setColor(Color.BLACK);
    }

    private void paintHero(IGame gameData) {
        Graphics g = background.getGraphics();
        super.paint(g);
        g.setColor(Color.BLUE);
        g.fillRect(gameData.getHeroPositionX(), gameData.getHeroPositionY(), Constants.HERO_WIDTH, Constants.HERO_HEIGHT);
        g.drawString(gameData.getHeroLives() + " / 3", 1000, 20);
    }

    private void paintEnemies(IGame gameData) {
        Graphics g = background.getGraphics();
        g.setColor(Color.red);
        for (Enemy enemy : gameData.getEnemies()) {
            g.drawRect(enemy.getPosX(), enemy.getPosY(), Constants.ENEMY_SIZE, Constants.ENEMY_SIZE);
        }

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(background, 0, 0, this);
    }
}
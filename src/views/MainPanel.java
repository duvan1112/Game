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
        setBackground(Color.BLACK);
        addKeyListener(keyListener);
    }

    public void init() {
        paintBackground();
        requestFocus();
        requestFocusInWindow();
    }

    public void updateGame(IGame gameData) {
        paintBackground();
        paintWalls(gameData);
        paintGems(gameData);
        paintHero(gameData);
        paintEnemies(gameData);
        paintScore(gameData);
        repaint();
    }

    private void paintBackground() {
        if (background == null) {
            background = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        }
        Graphics g = background.getGraphics();
        super.paint(g);
    }

    private void paintWalls(IGame gameData) {
        Graphics g = background.getGraphics();
        g.setColor(Color.ORANGE);
        for (int i = 0; i < gameData.getWalls().length; i++) {
            g.drawRect(gameData.getWalls()[i].x, gameData.getWalls()[i].y, gameData.getWalls()[i].width, gameData.getWalls()[i].height);
        }
    }

    private void paintGems(IGame gameData) {
        Graphics g = background.getGraphics();
        Color[] colors = {Color.BLUE, Color.GREEN, Color.yellow, Color.MAGENTA, Color.orange,Color.red};
        for (int i = 0; i < gameData.getGems().length; i++) {
            g.setColor(colors[i]);
            if (gameData.getGems()[i].isVisible()) {
                g.drawRect(gameData.getGems()[i].getPosX(), gameData.getGems()[i].getPosY(), gameData.getGems()[i].getWidth(), gameData.getGems()[i].getHeight());
            }
        }
    }

    private void paintHero(IGame gameData) {
        Graphics g = background.getGraphics();
        g.setColor(Color.BLUE);
        g.fillRect(gameData.getHeroPositionX(), gameData.getHeroPositionY(), Constants.HERO_WIDTH, Constants.HERO_HEIGHT);
    }

    private void paintEnemies(IGame gameData) {
        Graphics g = background.getGraphics();
        g.setColor(Color.red);
        for (Enemy enemy : gameData.getEnemies()) {
            g.drawRect(enemy.getPosX(), enemy.getPosY(), Constants.ENEMY_SIZE, Constants.ENEMY_SIZE);
        }

    }

    private void paintScore(IGame gameData) {
        Graphics g = background.getGraphics();
        g.setColor(Color.WHITE);
        g.drawString("Energia: ",800,20);
        g.drawString("Gemas: " + gameData.getNumberOfGems(), 900, 20);
        g.drawString("Vidas: " + gameData.getHeroLives() + " / 3", 1000, 20);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(background, 0, 0, this);
    }
}
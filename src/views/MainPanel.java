package views;

import models.IGame;
import presenters.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class MainPanel extends JPanel {

    public BufferedImage background;

    public MainPanel(KeyListener keyListener) {
        setSize(Constants.GAME_WIDTH,Constants.GAME_HEIGHT);
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
        if(background == null) {
            background = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
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
        g.fillRect(400, 300, Constants.ENEMY_SIZE, Constants.ENEMY_SIZE);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(background, 0, 0, this);
    }


}

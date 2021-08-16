package views;

import models.Enemy;
import models.IGame;
import presenters.Constants;
import utilities.Utilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class MainPanel extends JPanel {

    private BufferedImage background;
    private final Image imgEnergy;
    private final Image imgGhost;
    private final BufferedImage imgHero;
    private Image subImage;
    public MainPanel(KeyListener keyListener) {
        Image temp = new ImageIcon(Objects.requireNonNull(getClass().getResource(Constants.IMAGE_HERO))).getImage();
        imgHero = Utilities.imageToBufferedImage(Utilities.getScaledImage(temp, Constants.HERO_WIDTH * 3, Constants.HERO_HEIGHT));
        imgEnergy = new ImageIcon(Objects.requireNonNull(getClass().getResource(Constants.IMAGE_ENERGY))).getImage();
        imgGhost = new ImageIcon(Objects.requireNonNull(getClass().getResource(Constants.IMAGE_GHOST))).getImage();

        setBackground(Color.BLACK);
        addKeyListener(keyListener);
        heroAnimation();
    }

    private void heroAnimation() {
        AtomicInteger x = new AtomicInteger();
        Timer timer = new Timer(300, e -> {
            subImage = imgHero.getSubimage(x.get(), 0, Constants.HERO_WIDTH, Constants.HERO_HEIGHT);
            x.addAndGet(50);
            if (x.get() >= 150) {
                x.set(0);
            }
        });
        timer.start();
    }

    public void init() {
        paintBackground();
        requestFocus();
        requestFocusInWindow();
    }

    public void updateGame(IGame gameData) {
        paintBackground();
        paintWalls(gameData);
        paintEnergy(gameData);
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
        g.setColor(Color.decode("#1032EF"));
        Graphics2D graphics2D=(Graphics2D) g;
        graphics2D.setStroke(new BasicStroke(5));
        for (int i = 0; i < gameData.getWalls().length; i++) {
            graphics2D.drawRect(gameData.getWalls()[i].x, gameData.getWalls()[i].y, gameData.getWalls()[i].width, gameData.getWalls()[i].height);
        }
    }

    private void paintEnergy(IGame gameData) {
        Graphics g = background.getGraphics();
        for (int i = 0; i < gameData.getEnergy().length; i++) {
            if (gameData.getEnergy()[i].isVisible()) {
                g.drawImage(imgEnergy, gameData.getEnergy()[i].x, gameData.getEnergy()[i].y, Constants.ENERGY_SIZE, Constants.ENERGY_SIZE, this);
            }
        }
    }

    private void paintGems(IGame gameData) {
        Graphics g = background.getGraphics();
        Color[] colors = {Color.BLUE, Color.GREEN, Color.yellow, Color.MAGENTA, Color.orange, Color.red};
        for (int i = 0; i < gameData.getGems().length; i++) {
            g.setColor(colors[i]);
            if (gameData.getGems()[i].isVisible()) {
                g.drawRect(gameData.getGems()[i].getPosX(), gameData.getGems()[i].getPosY(), gameData.getGems()[i].getWidth(), gameData.getGems()[i].getHeight());
            }
        }
    }

    private void paintHero(IGame gameData) {
        Graphics g = background.getGraphics();
        g.drawImage(subImage, gameData.getHeroPositionX(), gameData.getHeroPositionY(), Constants.HERO_WIDTH, Constants.HERO_HEIGHT, this);
    }

    private void paintEnemies(IGame gameData) {
        Graphics g = background.getGraphics();
        g.setColor(Color.red);
        for (Enemy enemy : gameData.getEnemies()) {
            g.drawImage(imgGhost, enemy.getPosX(), enemy.getPosY(), Constants.ENEMY_SIZE, Constants.ENEMY_SIZE, this);
        }
    }

    private void paintScore(IGame gameData) {
        Graphics g = background.getGraphics();
        g.setColor(Color.WHITE);
        g.drawString("Energia: " + gameData.getQuantityOfEnergy() + " / 10", 800, 20);
        g.drawString("Gemas: " + gameData.getNumberOfGems(), 920, 20);
        g.drawString("Vidas: " + gameData.getHeroLives() + " / 3", 1000, 20);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(background, 0, 0, this);
    }
}
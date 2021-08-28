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
    private String heroPath;
    private final Image imgEnergy;
    private final Image imgGhost;
    private Image wall1;
    private Image wall2;
    private BufferedImage imgHero;
    private final BufferedImage imgGems;
    private final BufferedImage imgPacman;
    private Image subImageHero;
    private Image subImagePacman;
    private boolean pacmanDirection;

    public MainPanel(KeyListener keyListener) {
        setSize(Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
        heroPath =Constants.IMAGE_HERO_IRONMAN;
        imgHero = Utilities.imageToBufferedImage(Utilities.getScaledImage(new ImageIcon(Objects.requireNonNull(getClass().getResource(heroPath))).getImage(), Constants.HERO_WIDTH * 3, Constants.HERO_HEIGHT));
        imgGems = Utilities.imageToBufferedImage(Utilities.getScaledImage(new ImageIcon(Objects.requireNonNull(getClass().getResource(Constants.IMAGE_GEMS))).getImage(), Constants.GEM_WIDTH, Constants.GEM_HEIGHT * 6));
        imgPacman = Utilities.imageToBufferedImage(Utilities.getScaledImage(new ImageIcon(Objects.requireNonNull(getClass().getResource(Constants.IMAGE_PACMAN))).getImage(), Constants.PACMAN_SIZE * 6, Constants.PACMAN_SIZE));

        imgEnergy = new ImageIcon(Objects.requireNonNull(getClass().getResource(Constants.IMAGE_ENERGY))).getImage();
        imgGhost = new ImageIcon(Objects.requireNonNull(getClass().getResource(Constants.IMAGE_GHOST))).getImage();
        wall1 = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/images/wall2.jpg"))).getImage();
        wall2 = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/images/wall.jpg"))).getImage();
        setBackground(Color.BLACK);
        addKeyListener(keyListener);
        heroAnimation();
    }

    private void heroAnimation() {
        AtomicInteger x = new AtomicInteger();
        AtomicInteger xPacman = new AtomicInteger(0);
        AtomicInteger x2p = new AtomicInteger(150);
        Timer timer = new Timer(300, e -> {
            subImageHero = imgHero.getSubimage(x.get(), 0, Constants.HERO_WIDTH, Constants.HERO_HEIGHT);
            x.addAndGet(50);
            if (x.get() >= 150) {
                x.set(0);
            }

            if (pacmanDirection) {
                if (xPacman.get() == 200) {
                    xPacman.set(0);
                }
                subImagePacman = imgPacman.getSubimage(xPacman.get(), 0, Constants.PACMAN_SIZE, Constants.PACMAN_SIZE);
                xPacman.addAndGet(Constants.PACMAN_SIZE);
            } else {
                if (x2p.get() == 300) {
                    x2p.set(150);
                }
                subImagePacman = imgPacman.getSubimage(x2p.get(), 0, Constants.PACMAN_SIZE, Constants.PACMAN_SIZE);
                x2p.addAndGet(Constants.PACMAN_SIZE);
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
        getInfoAnimation(gameData);
        paintBackground();
        paintWalls(gameData);
        paintEnergy(gameData);
        paintGems(gameData);
        paintHero(gameData);
        paintEnemies(gameData);
        paintScore(gameData);
        repaint();
    }

    private void getInfoAnimation(IGame gameData) {
        if (gameData.getHeroPositionX() > gameData.getPacman().getPosX()) {
            pacmanDirection = true;
        } else {
            pacmanDirection = false;
        }
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
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setStroke(new BasicStroke(5));
        for (int i = 0; i < gameData.getWalls().length; i++) {
            if (i==0){
                graphics2D.drawImage(wall1,gameData.getWalls()[i].x, gameData.getWalls()[i].y, gameData.getWalls()[i].width, gameData.getWalls()[i].height,this);
            }else {
                graphics2D.drawImage(wall2,gameData.getWalls()[i].x, gameData.getWalls()[i].y, gameData.getWalls()[i].width, gameData.getWalls()[i].height,this);
            }
//            graphics2D.drawRect(gameData.getWalls()[i].x, gameData.getWalls()[i].y, gameData.getWalls()[i].width, gameData.getWalls()[i].height);
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
        int y = 0;
        for (int i = 0; i < gameData.getGems().length; i++) {
            Image sub = imgGems.getSubimage(0, y, Constants.GEM_WIDTH, Constants.GEM_HEIGHT);
            y += Constants.GEM_HEIGHT;
            if (gameData.getGems()[i].isVisible()) {
                g.drawImage(sub, gameData.getGems()[i].getPosX(), gameData.getGems()[i].getPosY(), Constants.GEM_WIDTH, Constants.GEM_HEIGHT, this);
            }
        }
    }

    private void paintHero(IGame gameData) {
        Graphics g = background.getGraphics();
        g.drawImage(subImageHero, gameData.getHeroPositionX(), gameData.getHeroPositionY(), Constants.HERO_WIDTH, Constants.HERO_HEIGHT, this);
    }

    private void paintEnemies(IGame gameData) {
        Graphics g = background.getGraphics();
        g.setColor(Color.red);
        g.drawImage(subImagePacman, gameData.getPacman().getPosX(), gameData.getPacman().getPosY(), Constants.PACMAN_SIZE, Constants.PACMAN_SIZE, this);
        for (Enemy enemy : gameData.getEnemies()) {
            g.drawImage(imgGhost, enemy.getPosX(), enemy.getPosY(), Constants.ENEMY_SIZE, Constants.ENEMY_SIZE, this);
        }
    }

    private void paintScore(IGame gameData) {
        Graphics g = background.getGraphics();
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setFont(new Font("Arial", Font.PLAIN, 14));
        g2.setColor(new Color(255, 255, 255, 200));
        g2.fillRoundRect(795, 10, 280, 50, 10, 10);
        g2.setColor(Color.BLACK);
        g2.drawString("Energia: " + gameData.getQuantityOfEnergy() + " / 10", 800, 40);
        g2.drawString("Gemas: " + gameData.getNumberOfGems(), 920, 40);
        g2.drawString("Vidas: " + gameData.getHeroLives() + " / 3", 1000, 40);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(background, 0, 0, this);
    }

    public void setHeroPath(String heroPath){
        this.heroPath = heroPath;
        imgHero = Utilities.imageToBufferedImage(Utilities.getScaledImage(new ImageIcon(Objects.requireNonNull(getClass().getResource(heroPath))).getImage(), Constants.HERO_WIDTH * 3, Constants.HERO_HEIGHT));
    }
}
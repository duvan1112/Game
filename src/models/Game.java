package models;

import presenters.Constants;

import java.awt.*;

public class Game extends Thread implements IGame {

    private final Hero hero;
    private final Enemy[] enemies;
    private final Wall[] walls;
    private final Gem[] gems;
    private boolean play;

    public Game() {
        hero = new Hero(Constants.HERO_INIT_POS_X, Constants.HERO_INIT_POS_Y);
        enemies = new Enemy[7];
        walls = new Wall[5];
        gems = new Gem[6];
        play = true;
        initComponents();
        start();
    }

    private void initComponents() {
        enemies[0] = new Enemy(80, 20);
        enemies[1] = new Enemy(500, 200);
        enemies[2] = new Enemy(320, 350);
        enemies[3] = new Enemy(600, 450);
        enemies[4] = new Enemy(350, 530);
        enemies[5] = new Enemy(900, 400);
        enemies[6] = new Enemy(800, 500);

        walls[0] = new Wall(0, 300, 150, 20);
        walls[1] = new Wall(200, 0, 20, 150);
        walls[2] = new Wall(130, 500, 20, 220);
        walls[3] = new Wall(300, 300, 20, 420);
        walls[4] = new Wall(650, 300, 20, 420);

        gems[0] = new Gem(50, 20, 30, 20);
        gems[1] = new Gem(200, 650, 30, 20);
        gems[2] = new Gem(450, 650, 30, 20);
        gems[3] = new Gem(0, 90, 30, 20);
        gems[4] = new Gem(0, 120, 30, 20);
        gems[5] = new Gem(0, 150, 30, 20);
    }

    @Override
    public void run() {
        while (play) {
            enemies[0].moveUpDown(0, 300);
            enemies[1].moveRightLeft(0, Constants.GAME_WIDTH);
            enemies[2].moveRightLeft(320, 650);
            enemies[3].moveRightLeft(320, 650);
            enemies[4].moveRightLeft(320, 650);
            enemies[5].moveRightLeft(670, Constants.GAME_WIDTH);
            enemies[6].moveRightLeft(670, Constants.GAME_WIDTH);
            checkCollisions();
            checkGemCollision();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void checkCollisions() {
        for (Enemy enemy : enemies) {
            if (hero.checkCollision(enemy)) {
                hero.setPosX(Constants.HERO_INIT_POS_X);
                hero.setPosY(Constants.HERO_INIT_POS_Y);
                hero.reduceLives();
                if (hero.isTotallyDead()) {
                    play = false;
                    break;
                }
            }
        }
    }

    private void checkGemCollision() {
        for (int i=0;i<gems.length-1;i++) {
            if (new Rectangle(hero.getPosX(), hero.getPosY(), Constants.HERO_WIDTH, Constants.HERO_HEIGHT).intersects(gems[i].getPosX(), gems[i].getPosY(), gems[i].getWidth(), gems[i].getHeight())){
                gems[i].setVisible(false);
            }
        }
        if (getNumberOfGems()==5 && new Rectangle(hero.getPosX(),hero.getPosY(),Constants.HERO_WIDTH,Constants.HERO_HEIGHT).intersects(gems[5].getPosX(),gems[5].getPosY(),gems[5].getWidth(),gems[5].getHeight())){
            gems[5].setVisible(false);
        }
    }

    public boolean checkWallCollision(int x, int y, int width, int height) {
        boolean aux;
        for (Wall wall : walls) {
            aux = wall.intersects(x, y, width, height);
            if (aux) {
                return true;
            }
        }
        return false;
    }

    public void moveHeroLeft() {
        if (!checkWallCollision(hero.getPosX() - Constants.HERO_MOVE_SIZE, hero.getPosY(), Constants.HERO_WIDTH, Constants.HERO_HEIGHT)) {
            hero.moveLeft();
        }
    }

    public void moveHeroRight() {
        if (!checkWallCollision(hero.getPosX() + Constants.HERO_MOVE_SIZE, hero.getPosY(), Constants.HERO_WIDTH, Constants.HERO_HEIGHT)) {
            hero.moveRight();
        }
    }

    public void moveHeroUP() {
        if (!checkWallCollision(hero.getPosX(), hero.getPosY() - Constants.HERO_MOVE_SIZE, Constants.HERO_WIDTH, Constants.HERO_HEIGHT)) {
            hero.moveUp();
        }
    }

    public void moveHeroDown() {
        if (!checkWallCollision(hero.getPosX(), hero.getPosY() + Constants.HERO_MOVE_SIZE, Constants.HERO_WIDTH, Constants.HERO_HEIGHT)) {
            hero.moveDown();
        }
    }

    @Override
    public int getHeroPositionX() {
        return hero.getPosX();
    }

    @Override
    public int getHeroPositionY() {
        return hero.getPosY();
    }

    @Override
    public Enemy[] getEnemies() {
        return enemies;
    }

    @Override
    public int getHeroLives() {
        return hero.getLives();
    }

    @Override
    public Wall[] getWalls() {
        return walls;
    }

    @Override
    public Gem[] getGems() {
        return gems;
    }

    @Override
    public int getNumberOfGems() {
        int count = 0;
        for (Gem gem : gems) {
            if (!gem.isVisible()) {
                count++;
            }
        }
        return count;
    }

}
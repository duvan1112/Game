package models;

import presenters.Constants;

import java.awt.*;

public class Game extends Thread implements IGame {

    private final Hero hero;
    private final Enemy[] enemies;
    private final Enemy pacman;
    private final Wall[] walls;
    private final Gem[] gems;
    private final Energy[] energy;
    private boolean play;
    private final Sound sound;

    public Game() {
        hero = new Hero(Constants.HERO_INIT_POS_X, Constants.HERO_INIT_POS_Y);
        enemies = new Enemy[7];
        pacman = new Enemy(800, 20);
        walls = new Wall[5];
        gems = new Gem[6];
        energy = new Energy[10];
        play = true;
        sound = new Sound(Constants.MUSIC_SOUND);
        initComponents();
        start();
    }

    public Game(int heroX, int heroY, int pacX, int pacY) {
        hero = new Hero(heroX, heroY);
        enemies = new Enemy[7];
        pacman = new Enemy(pacX, pacY);
        walls = new Wall[5];
        gems = new Gem[6];
        energy = new Energy[10];
        play = true;
        sound = new Sound(Constants.MUSIC_SOUND);
        sound.run();
    }

    public void loadComponents(int[] enemyPos) {
        enemies[0] = new Enemy(80, enemyPos[0]);
        enemies[1] = new Enemy(enemyPos[1], 200);
        enemies[2] = new Enemy(enemyPos[2], 350);
        enemies[3] = new Enemy(enemyPos[3], 450);
        enemies[4] = new Enemy(enemyPos[4], 530);
        enemies[5] = new Enemy(enemyPos[5], 400);
        enemies[6] = new Enemy(enemyPos[6], 500);

        walls[0] = new Wall(0, 300, 150, 20);
        walls[1] = new Wall(200, 0, 20, 150);
        walls[2] = new Wall(130, 500, 20, 220);
        walls[3] = new Wall(300, 300, 20, 420);
        walls[4] = new Wall(650, 300, 20, 420);

        gems[0] = new Gem(90, 20, Constants.GEM_WIDTH, Constants.GEM_HEIGHT);
        gems[1] = new Gem(200, 650, Constants.GEM_WIDTH, Constants.GEM_HEIGHT);
        gems[2] = new Gem(450, 650, Constants.GEM_WIDTH, Constants.GEM_HEIGHT);
        gems[3] = new Gem(1000, 450, Constants.GEM_WIDTH, Constants.GEM_HEIGHT);
        gems[4] = new Gem(500, 30, Constants.GEM_WIDTH, Constants.GEM_HEIGHT);
        gems[5] = new Gem(900, 650, Constants.GEM_WIDTH, Constants.GEM_HEIGHT);

        energy[0] = new Energy(50, 450);
        energy[1] = new Energy(50, 400);
        energy[2] = new Energy(50, 350);
        energy[3] = new Energy(470, 500);
        energy[4] = new Energy(350, 450);
        energy[5] = new Energy(900, 600);
        energy[6] = new Energy(300, 30);
        energy[7] = new Energy(540, 360);
        energy[8] = new Energy(500, 200);
        energy[9] = new Energy(90, 100);
        start();
    }

    private void initComponents() {
        enemies[0] = new Enemy(80, 20);
        enemies[1] = new Enemy(500, 200);
        enemies[2] = new Enemy(320, 350);
        enemies[3] = new Enemy(600, 450);
        enemies[4] = new Enemy(350, 530);
        enemies[5] = new Enemy(680, 400);
        enemies[6] = new Enemy(1000, 500);

        walls[0] = new Wall(0, 300, 150, 20);
        walls[1] = new Wall(200, 0, 20, 150);
        walls[2] = new Wall(130, 500, 20, 220);
        walls[3] = new Wall(300, 300, 20, 420);
        walls[4] = new Wall(650, 300, 20, 420);

        gems[0] = new Gem(90, 20, Constants.GEM_WIDTH, Constants.GEM_HEIGHT);
        gems[1] = new Gem(200, 650, Constants.GEM_WIDTH, Constants.GEM_HEIGHT);
        gems[2] = new Gem(450, 650, Constants.GEM_WIDTH, Constants.GEM_HEIGHT);
        gems[3] = new Gem(1000, 450, Constants.GEM_WIDTH, Constants.GEM_HEIGHT);
        gems[4] = new Gem(500, 30, Constants.GEM_WIDTH, Constants.GEM_HEIGHT);
        gems[5] = new Gem(900, 650, Constants.GEM_WIDTH, Constants.GEM_HEIGHT);

        energy[0] = new Energy(50, 450);
        energy[1] = new Energy(50, 400);
        energy[2] = new Energy(50, 350);
        energy[3] = new Energy(470, 500);
        energy[4] = new Energy(350, 450);
        energy[5] = new Energy(900, 600);
        energy[6] = new Energy(300, 30);
        energy[7] = new Energy(540, 360);
        energy[8] = new Energy(500, 200);
        energy[9] = new Energy(90, 100);
    }

    @Override
    public void run() {
        while (play) {
            movePacman();
            enemies[0].moveUpDown(0, 300);
            enemies[1].moveRightLeft(0, Constants.GAME_WIDTH);
            enemies[2].moveRightLeft(320, 650);
            enemies[3].moveRightLeft(320, 650);
            enemies[4].moveRightLeft(320, 650);
            enemies[5].moveRightLeft(670, Constants.GAME_WIDTH);
            enemies[6].moveRightLeft(670, Constants.GAME_WIDTH);
            checkCollisions();
            checkGemCollision();
            checkEnergyCollision();
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        sound.stop();
    }

    private void movePacman() {
        if (hero.getPosX() > pacman.getPosX()) {
            pacman.moveRight(1);
        }
        if (hero.getPosX() < pacman.getPosX()) {
            pacman.moveLeft(1);
        }
        if (hero.getPosY() < pacman.getPosY()) {
            pacman.moveUp(1);
        }
        if (hero.getPosY() > pacman.getPosY()) {
            pacman.moveDown(1);
        }
    }

    private void checkCollisions() {
        for (Enemy enemy : enemies) {
            if (hero.checkCollision(enemy) || hero.checkCollision(pacman)) {
                deathSound();
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

    private void deathSound() {
        new Sound(Constants.DEATH_SOUND).run();
    }

    private void checkEnergyCollision() {
        for (Energy energy1 : energy) {
            if (energy1.intersects(hero.getPosX(), hero.getPosY(), Constants.HERO_WIDTH, Constants.HERO_HEIGHT)) {
                energy1.setVisible(false);
            }
        }
    }

    private void checkGemCollision() {
        for (int i = 0; i < gems.length - 1; i++) {
            if (new Rectangle(hero.getPosX(), hero.getPosY(), Constants.HERO_WIDTH, Constants.HERO_HEIGHT).intersects(gems[i].getPosX(), gems[i].getPosY(), gems[i].getWidth(), gems[i].getHeight())) {
                gems[i].setVisible(false);
            }
        }
        if (getQuantityOfEnergy() == 10 && getNumberOfGems() == 5 && new Rectangle(hero.getPosX(), hero.getPosY(), Constants.HERO_WIDTH, Constants.HERO_HEIGHT).intersects(gems[5].getPosX(), gems[5].getPosY(), gems[5].getWidth(), gems[5].getHeight())) {
            gems[5].setVisible(false);
            play = false;
        }
    }

    public boolean checkWallCollision(int x, int y, int width, int height) {
        boolean aux;
        for (Wall wall : walls) {
            aux = wall.intersects(x, y, width, height);
            if (aux) {
                return false;
            }
        }
        return true;
    }

    public void moveHeroLeft() {
        if (checkWallCollision(hero.getPosX() - Constants.HERO_MOVE_SIZE, hero.getPosY(), Constants.HERO_WIDTH, Constants.HERO_HEIGHT)) {
            hero.moveLeft();
        }
    }

    public void moveHeroRight() {
        if (checkWallCollision(hero.getPosX() + Constants.HERO_MOVE_SIZE, hero.getPosY(), Constants.HERO_WIDTH, Constants.HERO_HEIGHT)) {
            hero.moveRight();
        }
    }

    public void moveHeroUP() {
        if (checkWallCollision(hero.getPosX(), hero.getPosY() - Constants.HERO_MOVE_SIZE, Constants.HERO_WIDTH, Constants.HERO_HEIGHT)) {
            hero.moveUp();
        }
    }

    public void moveHeroDown() {
        if (checkWallCollision(hero.getPosX(), hero.getPosY() + Constants.HERO_MOVE_SIZE, Constants.HERO_WIDTH, Constants.HERO_HEIGHT)) {
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
    public Enemy getPacman() {
        return pacman;
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

    public void setPlay(boolean play) {
        this.play = play;
    }

    public boolean isPlaying() {
        return play;
    }

    @Override
    public Energy[] getEnergy() {
        return energy;
    }

    @Override
    public int getQuantityOfEnergy() {
        int count = 0;
        for (Energy energy : energy) {
            if (!energy.isVisible()) {
                count++;
            }
        }
        return count;
    }

    public void setVisibleGems(boolean[] booleans) {
        for (int i = 0; i < gems.length; i++) {
            gems[i].setVisible(booleans[i]);
        }
    }

    public void setVisibleEnergy(boolean[] booleans) {
        for (int i = 0; i < energy.length; i++) {
            energy[i].setVisible(booleans[i]);
        }
    }

    public void setHeroLives(int lives) {
        hero.setLives(lives);
    }

    public Sound getSound() {
        return sound;
    }
}
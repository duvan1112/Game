package models;

import presenters.Constants;

public class Game extends Thread implements IGame {

    private final Hero hero;
    private  Enemy[] enemies;
    private boolean play;

    public Game(){
        hero = new Hero(Constants.HERO_INIT_POS_X,Constants.HERO_INIT_POS_Y);
        enemies= new Enemy[7];
        play =true;
        createEnemies();
        start();
    }

    private void createEnemies() {
        enemies[0]=new Enemy(30,20);
        enemies[1]=new Enemy(500,200);
        enemies[2]=new Enemy(400,400);
        enemies[3]=new Enemy(500,450);
        enemies[4]=new Enemy(400,500);
        enemies[5]=new Enemy(900,400);
        enemies[6]=new Enemy(800,450);
    }

    public void moveHeroLeft() {
        hero.moveLeft();
    }

    public void moveHeroRight() {
        hero.moveRight();
    }

    public void moveHeroUP(){
        hero.moveUp();
    }

    public void moveHeroDown(){
        hero.moveDown();
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
        return  enemies;
    }

    @Override
    public int getHeroLives() {
        return hero.getLives();
    }

}

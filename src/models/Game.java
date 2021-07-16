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
        start();
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

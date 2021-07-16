package models;

import presenters.Constants;

public class Hero {

    private int posX;
    private  int posY;
    private int lives;

    public Hero(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        lives = Constants.HERO_MAX_LIVES;
    }

    public void moveLeft() {
        if(posX - Constants.HERO_MOVE_SIZE >= 0){
            posX -= Constants.HERO_MOVE_SIZE;
        }
    }

    public void moveRight() {
        if((posX + Constants.HERO_WIDTH) <= Constants.GAME_WIDTH){
            posX += Constants.HERO_MOVE_SIZE;
        }
    }

    public void moveUp(){
        if ((posY- Constants.HERO_MOVE_SIZE)>=0){
            posY-= Constants.HERO_MOVE_SIZE;
        }
    }

    public void moveDown(){
        if ((posY+ Constants.HERO_HEIGHT)<=Constants.GAME_HEIGHT){
            posY+= Constants.HERO_MOVE_SIZE;
        }
    }

    public void reduceLives(){
        this.lives--;
    }

    public int getLives(){
        return lives;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public boolean isTotallyDead(){
        return lives <= 0;
    }

}

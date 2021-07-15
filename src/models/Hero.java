package models;

import presenters.Constants;

public class Hero {

    public static final int WIDTH = 50;
    public static final int HEIGHT = 100;
    public static final int MOVE_SIZE = 5;
    public static final int MAX_LIVES = 3;

    private int posX;
    private  int posY;
    private int lives;

    public Hero(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        lives = MAX_LIVES;
    }

    public void moveLeft() {
        if(posX - MOVE_SIZE >= 0){
            posX -= MOVE_SIZE;
        }
    }

    public void moveRight() {
        if((posX + WIDTH) <= Constants.GAME_WIDTH){
            posX += MOVE_SIZE;
        }
    }

    public void moveUp(){
        if ((posY-MOVE_SIZE)>=0){
            posY-=MOVE_SIZE;
        }
    }

    public void moveDown(){
        if ((posY+HEIGHT)>=Constants.GAME_HEIGHT){
            posY+=MOVE_SIZE;
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

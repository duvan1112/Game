package models;

import presenters.Constants;

import java.awt.*;

public class Enemy {

    private int posX;
    private int posY;
    private boolean isAttack;

    public Enemy(int x, int y) {
        this.posX = x;
        this.posY = y;
        isAttack = true;
    }

    public void moveLeft() {
        if(posX - Constants.ENEMY_MOVE_SIZE >= 0){
            posX -= Constants.ENEMY_MOVE_SIZE;
        }
    }

    public void moveRight() {
        if((posX + Constants.ENEMY_SIZE) <= Constants.GAME_WIDTH){
            posX += Constants.ENEMY_MOVE_SIZE;
        }
    }

    public void moveUp(){
        if ((posY- Constants.HERO_MOVE_SIZE)>=0){
            posY-= Constants.HERO_MOVE_SIZE;
        }
    }

    public void moveDown(){
        if ((posY+ Constants.HERO_HEIGHT)>=Constants.GAME_HEIGHT){
            posY+= Constants.ENEMY_MOVE_SIZE;
        }
    }

    public boolean checkCollision(Rectangle rectangle) {
        Rectangle recEnemy = new Rectangle(posX, posX, Constants.ENEMY_SIZE, Constants.ENEMY_SIZE);
        return recEnemy.intersects(rectangle);
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void disableAttack() {
        isAttack = false;
    }

    public boolean isAttack() {
        return isAttack;
    }
}

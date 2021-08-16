package models;

import presenters.Constants;

import java.awt.*;

public class Enemy {

    private int posX;
    private int posY;
    private boolean isAttack;
    private int moveSizeUpDown = Constants.ENEMY_MOVE_SIZE;
    private int moveSizeLeftRight = Constants.ENEMY_MOVE_SIZE;

    public Enemy(int x, int y) {
        this.posX = x;
        this.posY = y;
        isAttack = true;
    }

    public void moveRightLeft(int min, int max) {
        if(posX == min){
            moveSizeLeftRight = Constants.ENEMY_MOVE_SIZE;
        }if (posX==max-Constants.ENEMY_SIZE){
            moveSizeLeftRight =-Constants.ENEMY_MOVE_SIZE;
        }
        posX+= moveSizeLeftRight;
    }

    public void moveUpDown(int min,int max){
       if (posY==min){
           moveSizeUpDown =Constants.ENEMY_MOVE_SIZE;
       }if (posY==max-Constants.ENEMY_SIZE){
           moveSizeUpDown =-Constants.ENEMY_MOVE_SIZE;
        }
       posY+= moveSizeUpDown;
    }

    public void moveLeft() {
        if (posX - Constants.ENEMY_MOVE_SIZE >= 0) {
            posX -= Constants.ENEMY_MOVE_SIZE;
        }
    }

    public void moveRight() {
        if ((posX + Constants.ENEMY_SIZE) <= Constants.GAME_WIDTH) {
            posX += Constants.ENEMY_MOVE_SIZE;
        }
    }

    public void moveUp() {
        if ((posY - Constants.ENEMY_MOVE_SIZE) >= 0) {
            posY -= Constants.ENEMY_MOVE_SIZE;
        }
    }

    public void moveDown() {
        if ((posY + Constants.ENEMY_SIZE) <= (Constants.GAME_HEIGHT-Constants.BORDER_SIZE_TOP)) {
            posY += Constants.ENEMY_MOVE_SIZE;
        }
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

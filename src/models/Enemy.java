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

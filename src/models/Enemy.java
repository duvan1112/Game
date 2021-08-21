package models;

import presenters.Constants;

public class Enemy {

    private int posX;
    private int posY;
    private int moveSizeUpDown = Constants.ENEMY_MOVE_SIZE;
    private int moveSizeLeftRight = Constants.ENEMY_MOVE_SIZE;

    public Enemy(int x, int y) {
        this.posX = x;
        this.posY = y;

    }

    public void moveRightLeft(int min, int max) {
        if (posX == min) {
            moveSizeLeftRight = Constants.ENEMY_MOVE_SIZE;
        }
        if (posX == max - Constants.ENEMY_SIZE) {
            moveSizeLeftRight = -Constants.ENEMY_MOVE_SIZE;
        }
        posX += moveSizeLeftRight;
    }

    public void moveUpDown(int min, int max) {
        if (posY == min) {
            moveSizeUpDown = Constants.ENEMY_MOVE_SIZE;
        }
        if (posY == max - Constants.ENEMY_SIZE) {
            moveSizeUpDown = -Constants.ENEMY_MOVE_SIZE;
        }
        posY += moveSizeUpDown;
    }

    public void moveLeft(int moveSize) {
        if (posX - Constants.ENEMY_MOVE_SIZE >= 0) {
            posX -= moveSize;
        }
    }

    public void moveRight(int moveSize) {
        if ((posX + Constants.ENEMY_SIZE) <= Constants.GAME_WIDTH) {
            posX += moveSize;
        }
    }

    public void moveUp(int moveSize) {
        if ((posY - Constants.ENEMY_MOVE_SIZE) >= 0) {
            posY -= moveSize;
        }
    }

    public void moveDown(int moveSize) {
        if ((posY + Constants.ENEMY_SIZE) <= (Constants.GAME_HEIGHT - Constants.BORDER_SIZE_TOP)) {
            posY += moveSize;
        }
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
}

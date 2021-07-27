package models;

public interface IGame {
    int getHeroPositionX();

    int getHeroPositionY();

    Enemy[] getEnemies();

    int getHeroLives();

    Wall[] getWalls();
}

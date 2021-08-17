package models;

public interface IGame {
    int getHeroPositionX();

    int getHeroPositionY();

    Enemy[] getEnemies();

    Enemy getPacman();

    int getHeroLives();

    Wall[] getWalls();

    Gem[] getGems();

    int getNumberOfGems();

    Energy[] getEnergy();

    int getQuantityOfEnergy();
}

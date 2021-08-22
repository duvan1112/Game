package presenters;

import views.JFMainWindow;

public class Constants {
    public static final String GAME_TITLE = "Infinity Gems";
    public static final int GAME_WIDTH = 1080;
    public static final int GAME_HEIGHT = 720;
    public static int BORDER_SIZE_TOP= JFMainWindow.getTopEdge();

    //Hero
    public static final int HERO_WIDTH = 50;
    public static final int HERO_HEIGHT = 80;
    public static final int HERO_MOVE_SIZE = 5;
    public static final int HERO_MAX_LIVES = 3;
    public static final int HERO_INIT_POS_X = 30;
    public static final int HERO_INIT_POS_Y = 500;

    //Enemy
    public static final int PACMAN_SIZE = 50;
    public static final int ENEMY_SIZE = 50;
    public static final int ENEMY_MOVE_SIZE = 2;

    //Energy
    public static final int ENERGY_SIZE = 15;

    //Gems
    public static final int GEM_WIDTH = 40;
    public static final int GEM_HEIGHT = 40;

    //img
    public static final String IMAGE_HERO_IRONMAN= "/resources/images/ironMan.png";
    public static final String IMAGE_HERO_BLACK_WIDOW="/resources/images/blackWidow.png";
    public static final String IMAGE_HERO_SPIDERMAN="/resources/images/spiderman.png";
    public static final String IMAGE_HERO_ODIN="/resources/images/MyOdin.png";

    public static final String IMAGE_GHOST="/resources/images/ghost.png";
    public static final String IMAGE_ENERGY="/resources/images/Energy.png";
    public static final String IMAGE_GEMS = "/resources/images/Gems.png";
    public static final String IMAGE_PACMAN = "/resources/images/pacman.png";

    //sound
    public static final String MUSIC_SOUND = "/resources/sounds/Arcade_DimitriVegas.wav";
    public static final String DEATH_SOUND = "/resources/sounds/deathSound.wav";
}

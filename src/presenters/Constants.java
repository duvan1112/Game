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
    public static final int ENEMY_SIZE = 50;
    public static final int ENEMY_MOVE_SIZE = 2;

    public static final int ENERGY_SIZE = 30;

    //img
    public static final String IMAGE_HERO= "/resources/images/ironMan.png";
    public static final String IMAGE_GHOST="/resources/images/ghost.png";
    public static final String IMAGE_ENERGY="/resources/images/Energy.png";
}

package presenters;

import models.Game;
import models.Sound;
import persistence.FileManager;
import views.JFMainWindow;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Presenter implements ActionListener, KeyListener {

    private final JFMainWindow window;
    private Game game;
    private final FileManager fileManager;

    public Presenter() {
        fileManager = new FileManager();
        window = new JFMainWindow(this, this);
        window.showMainMenu();
    }

    private void updateUi() {
        Timer timerUpdate = new Timer(1, e -> {
            if (game.getHeroLives() == 0) {
                window.refreshGame(game);
                game.setPlay(false);
                window.showDefeatDialog(true);
                ((Timer) e.getSource()).stop();
            } else if(game.getNumberOfGems()==6){
                window.showVictoryDialog(true);
                ((Timer) e.getSource()).stop();
            } else {
                window.refreshGame(game);
            }
        });
        timerUpdate.start();
    }

    private void autoSave() {
        new Thread(() -> {
            while (game.isPlaying()) {
                saveGame();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void saveGame() {
        fileManager.write(game);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        switch (Commands.valueOf(actionEvent.getActionCommand())) {
            case NEW_GAME:
                initGame();
                break;
            case CONTINUE_GAME:
                continueGame();
                break;
            case HEROES:
                heroes();
                break;
            case MUSIC:
                music();
                break;
            case EXIT:
                System.exit(0);
                break;
            case MENU:
                window.showVictoryDialog(false);
                window.showDefeatDialog(false);
                window.showMainMenu();
                break;
        }
    }

    private void music() {

    }

    private void heroes() {

    }

    private void continueGame() {
        window.showVictoryDialog(false);
        window.showDefeatDialog(false);
        game = new Game();
        //setts
        window.initGameScreen();
        updateUi();
        autoSave();
    }

    private void initGame() {
        window.showVictoryDialog(false);
        window.showDefeatDialog(false);

        game = new Game();
        window.initGameScreen();
        updateUi();
        autoSave();
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int code = keyEvent.getExtendedKeyCode();
        switch (code) {
            case KeyEvent.VK_LEFT:
                game.moveHeroLeft();
                break;
            case KeyEvent.VK_RIGHT:
                game.moveHeroRight();
                break;
            case KeyEvent.VK_UP:
                game.moveHeroUP();
                break;
            case KeyEvent.VK_DOWN:
                game.moveHeroDown();
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}

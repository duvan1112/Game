package presenters;

import models.Game;
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
    private boolean music;

    public Presenter() {
        fileManager = new FileManager();
        music = true;
        window = new JFMainWindow(this, this);
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
        fileManager.writeJson(game);
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
                music=window.musicBtn();
                break;
            case EXIT:
                System.exit(0);
                break;
            case MENU:
                window.showVictoryDialog(false);
                window.showDefeatDialog(false);
                window.showMainMenu();
                break;
            case IRONMAN:
                window.setHeroPath(Constants.IMAGE_HERO_IRONMAN);
                window.setVisibleHeroes(false);
                break;
            case SPIDERMAN:
                window.setHeroPath(Constants.IMAGE_HERO_SPIDERMAN);
                window.setVisibleHeroes(false);
                break;
            case BLACKWIDOW:
                window.setHeroPath(Constants.IMAGE_HERO_BLACK_WIDOW);
                window.setVisibleHeroes(false);
                break;
            case ODIN:
                window.setHeroPath(Constants.IMAGE_HERO_ODIN);
                window.setVisibleHeroes(false);
                break;
        }
    }

    private void heroes() {
        window.setVisibleHeroes(true);
    }

    private void continueGame() {
        window.showVictoryDialog(false);
        window.showDefeatDialog(false);
        //setts
        String[] data =fileManager.readJson();
        if (Integer.parseInt(data[27])!=0) {
            game = new Game(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2]), Integer.parseInt(data[3]));
            game.getSound().stop();
            int[] posEnemies = new int[7];
            for (int i = 0; i < posEnemies.length; i++) {
                posEnemies[i] = Integer.parseInt(data[i + 10]);
            }
            game.loadComponents(posEnemies);

            boolean[] gems = new boolean[6];
            for (int i = 0; i < gems.length; i++) {
                gems[i] = Boolean.parseBoolean(data[i + 4]);
            }
            game.setVisibleGems(gems);

            boolean[] energy = new boolean[10];
            for (int i = 0; i < energy.length; i++) {
                energy[i] = Boolean.parseBoolean(data[i + 17]);
            }
            game.setVisibleEnergy(energy);
            game.setHeroLives(Integer.parseInt(data[27]));
            if (music) {
                game.getSound().run();
            }
            window.initGameScreen();
            updateUi();
            autoSave();
        }else {
            initGame();
        }

    }

    private void initGame() {
        window.showVictoryDialog(false);
        window.showDefeatDialog(false);
        game = new Game();
        if (music){
            game.getSound().run();
        }
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

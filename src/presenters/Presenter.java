package presenters;

import models.Game;
import views.JFMainWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Presenter implements ActionListener, KeyListener {

    private final JFMainWindow window;
    private final Game game;

    public Presenter() {
        game = new Game();
        window = new JFMainWindow(this);
        updateUi();
    }

    private void updateUi() {
        Timer timerUpdate = new Timer(1, e -> {

            if (game.getNumberOfGems() == 6 || game.getHeroLives() == 0) {
                window.refreshGame(game);
                game.setPlay(false);
                JOptionPane.showMessageDialog(null, "Game Over");
                ((Timer)e.getSource()).stop();
            } else {
                window.refreshGame(game);
            }
        });
        timerUpdate.start();
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {

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

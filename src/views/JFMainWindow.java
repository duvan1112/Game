package views;

import models.IGame;
import presenters.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.util.Objects;

public class JFMainWindow extends JFrame {

    private final MainPanel mainPanel;

    public JFMainWindow(KeyListener keyListener) {
        setSize(Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
        setTitle(Constants.GAME_TITLE);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/images/mainIcon.png"))).getImage());

        mainPanel = new MainPanel(keyListener);
        add(mainPanel, BorderLayout.CENTER);
        setVisible(true);
        mainPanel.init();
    }

    public void refreshGame(IGame gameData){
        mainPanel.updateGame(gameData);
    }
}

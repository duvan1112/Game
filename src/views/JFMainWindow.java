package views;

import models.IGame;
import presenters.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.Objects;

public class JFMainWindow extends JFrame {

    private final MainPanel mainPanel;
    private final PopUpWindow defeat;
    private final PopUpWindow victory;
    private final JPMenu mainMenu;
    private JDHeroes jdHeroes;
    private static int topEdge;

    public JFMainWindow(KeyListener keyListener, ActionListener actionListener) {
        setSize(Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
        setTitle(Constants.GAME_TITLE);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/images/mainIcon.png"))).getImage());
        mainMenu = new JPMenu(actionListener);
        mainPanel = new MainPanel(keyListener);
        jdHeroes = new JDHeroes(actionListener);
        defeat = new PopUpWindow("Derrota", this, actionListener);
        victory = new PopUpWindow("Victoria", this, actionListener);
        add(mainMenu);
        setVisible(true);
        topEdge = getHeight() - getContentPane().getHeight();
    }

    public static int getTopEdge() {
        return topEdge;
    }

    public void refreshGame(IGame gameData) {
        mainPanel.updateGame(gameData);
    }
    public void setVisibleHeroes(boolean b){
        jdHeroes.setVisible(b);
    }
    public void initGameScreen() {
        remove(mainMenu);
        add(mainPanel, BorderLayout.CENTER);
        mainPanel.init();
        repaint();
    }

    public void showMainMenu() {
        remove(mainPanel);
        add(mainMenu,BorderLayout.CENTER);
        repaint();
    }

    public void showDefeatDialog(boolean b){
        defeat.setVisible(b);
    }

    public void showVictoryDialog(boolean b){
        victory.setVisible(b);
    }

    public void setHeroPath(String path){
        mainPanel.setHeroPath(path);
    }
    public boolean musicBtn(){
        return mainMenu.musicBtn();
    }
}

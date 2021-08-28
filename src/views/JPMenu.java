package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class JPMenu extends JPanel {
    private JPMenuLeft menuLeft;
    public JPMenu(ActionListener actionListener) {
        menuLeft = new JPMenuLeft(actionListener);
        initComponents(actionListener);
    }

    private void initComponents(ActionListener actionListener) {
        setLayout(new GridLayout(0,2));
        menuLeft = new JPMenuLeft(actionListener);
        add(menuLeft);
        JPMenuRight menuRight = new JPMenuRight();
        add(menuRight);
    }

    public boolean musicBtn(){
        return menuLeft.manageMusicBtn();
    }
}

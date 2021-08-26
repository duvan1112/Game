package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class JPMenu extends JPanel {
    public JPMenu(ActionListener actionListener) {
        initComponents(actionListener);
    }

    private void initComponents(ActionListener actionListener) {
        setLayout(new GridLayout(0,2));
        JPMenuLeft jpMenuRight = new JPMenuLeft(actionListener);
        add(jpMenuRight);
        JPMenuRight jpMenuLeft = new JPMenuRight();
        add(jpMenuLeft);
    }
}

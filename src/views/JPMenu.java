package views;

import javax.swing.*;
import java.awt.*;

public class JPMenu extends JPanel {
    public JPMenu() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridLayout(0,2));
        JPMenuLeft jpMenuRight = new JPMenuLeft();
        add(jpMenuRight);
        JPMenuRight jpMenuLeft = new JPMenuRight();
        add(jpMenuLeft);

    }
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1080,720);
        frame.add(new JPMenu());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}

package views;

import javax.swing.*;
import java.util.Objects;

public class JFMainWindow extends JFrame {

    public JFMainWindow() {
        setSize(Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
        setTitle(Constants.GAME_TITLE);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/images/mainIcon.png"))).getImage());

    }
}

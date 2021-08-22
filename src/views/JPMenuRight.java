package views;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class JPMenuRight extends JPanel {
    Image pacman;
    Image ironman;
    Image ghost;

    public JPMenuRight() {
    initComponents();
    pacman = new ImageIcon(getClass().getResource("/resources/images/pacmanMenu.png")).getImage();
    ironman = new ImageIcon(getClass().getResource("/resources/images/ironmanMenu.jpg")).getImage();
    ghost = new ImageIcon(getClass().getResource("/resources/images/ghost.png")).getImage();
    }

    private void initComponents() {
        setBackground(Color.RED);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(ironman,0,0,getWidth(),getHeight(),this);
        g2d.drawImage(pacman,40,230,pacman.getWidth(null)/2,pacman.getHeight(null)/2,this);
        g2d.drawImage(ghost,160,30,70,70,this);
        g2d.drawImage(ghost,50,500,70,70,this);
    }

}

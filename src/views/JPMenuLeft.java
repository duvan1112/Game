package views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class JPMenuLeft extends JPanel {
    JButton[] buttons;
    public JPMenuLeft() {
        buttons = new JButton[5];
        initComponents();
    }

    private void initComponents() {
        setBorder(new EmptyBorder(10,20,30,20));
        setLayout(new GridLayout(6,0,10,30));
        setBackground(Color.BLACK);

        JLabel tittle = new JLabel("Infinity Gems");
        tittle.setHorizontalAlignment(0);
        tittle.setForeground(Color.lightGray);
        tittle.setFont(new Font("Times New Roman",Font.BOLD,80));
        add(tittle);

        String[] text={"Juego Nuevo","Cargar Juego","Heroes","Música: On","Salir"};
        for (int i=0;i<buttons.length;i++) {
            buttons[i] = new JButton(text[i]);
            buttons[i].setBackground(Color.decode("#b50000"));
            buttons[i].setFocusPainted(false);
            buttons[i].setBorder(null);
            buttons[i].setFont(new Font("Times New Roman",Font.BOLD,40));
            buttons[i].setForeground(Color.lightGray);
            add(buttons[i]);
        }
    }
}

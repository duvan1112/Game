package views;

import presenters.Commands;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class JPMenuLeft extends JPanel {
    JButton[] buttons;
    public JPMenuLeft(ActionListener actionListener) {
        buttons = new JButton[5];
        initComponents(actionListener);
    }

    private void initComponents(ActionListener actionListener) {
        setBorder(new EmptyBorder(10,20,30,20));
        setLayout(new GridLayout(6,0,10,30));
        setBackground(Color.BLACK);

        JLabel tittle = new JLabel("Infinity Gems");
        tittle.setHorizontalAlignment(0);
        tittle.setForeground(Color.lightGray);
        tittle.setFont(new Font("Times New Roman",Font.BOLD,80));
        add(tittle);

        String[] text={"Juego Nuevo","Cargar Juego","Heroes","Música: On","Salir"};
        String[] commands={Commands.NEW_GAME.toString(),Commands.CONTINUE_GAME.toString(),Commands.HEROES.toString(),Commands.MUSIC.toString(),Commands.EXIT.toString()};
        for (int i=0;i<buttons.length;i++) {
            buttons[i] = new JButton(text[i]);
            buttons[i].setBackground(Color.decode("#b50000"));
            buttons[i].setFocusPainted(false);
            buttons[i].setBorder(null);
            buttons[i].setFont(new Font("Times New Roman",Font.BOLD,40));
            buttons[i].setForeground(Color.lightGray);
            buttons[i].addActionListener(actionListener);
            buttons[i].setActionCommand(commands[i]);
            add(buttons[i]);
        }
    }

    public boolean manageMusicBtn() {
        if (buttons[3].getText().equals("Música: On")) {
            buttons[3].setText("Música: Of");
            return false;
        }else{
            buttons[3].setText("Música: On");
            return true;
        }
    }
}
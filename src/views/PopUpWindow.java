package views;

import presenters.Commands;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class PopUpWindow extends JWindow {
    JButton[] buttons;

    public PopUpWindow(String text,Component component, ActionListener actionListener) {
        setSize(400,500);
        setLocationRelativeTo(component);
        setAlwaysOnTop(true);
        initComponents(text,actionListener);
    }

    private void initComponents(String text, ActionListener actionListener) {

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(4, 0, 10, 30));
        jPanel.setBorder(new EmptyBorder(10,10,10,10));
        jPanel.setBackground(Color.BLACK);

        JLabel tittle = new JLabel(text);
        tittle.setHorizontalAlignment(0);
        tittle.setForeground(Color.lightGray);
        tittle.setFont(new Font("Times New Roman", Font.BOLD, 50));
        jPanel.add(tittle);

        buttons = new JButton[3];
        String[] btnText = {"Menu", "Volver a Jugar", "Salir"};
        String[] commands = {Commands.MENU.toString(), Commands.NEW_GAME.toString(), Commands.EXIT.toString()};
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton(btnText[i]);
            buttons[i].setBackground(Color.decode("#b50000"));
            buttons[i].setFocusPainted(false);
            buttons[i].setBorder(null);
            buttons[i].setFont(new Font("Times New Roman", Font.BOLD, 40));
            buttons[i].setForeground(Color.lightGray);
            buttons[i].addActionListener(actionListener);
            buttons[i].setActionCommand(commands[i]);
            jPanel.add(buttons[i]);
        }
        add(jPanel);
    }
}

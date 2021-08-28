package views;

import presenters.Commands;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class JDHeroes extends JDialog {

    private JButton[] buttons;

    public JDHeroes(ActionListener actionListener){
        buttons = new JButton[4];
        initComponents(actionListener);
    }

    private void initComponents(ActionListener actionListener) {
        setSize(450,500);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(10,10,10,10));
        panel.setLayout(new GridLayout(4,0,10,10));
        panel.setBackground(Color.BLACK);
        String[] labelBtn= {"Iron-Man","Black Widow","Odin","Spider-man"};
        String[] commands={Commands.IRONMAN.toString(),Commands.BLACKWIDOW.toString(),Commands.ODIN.toString(),Commands.SPIDERMAN.toString()};

        for (int i=0;i<buttons.length;i++) {
            buttons[i] = new JButton(labelBtn[i]);
            buttons[i].setBackground(Color.decode("#b50000"));
            buttons[i].setFocusPainted(false);
            buttons[i].setBorder(null);
            buttons[i].setFont(new Font("Times New Roman", Font.BOLD, 40));
            buttons[i].setForeground(Color.lightGray);
            buttons[i].addActionListener(actionListener);
            buttons[i].setActionCommand(commands[i]);
            panel.add(buttons[i]);
        }
        add(panel);
    }

}

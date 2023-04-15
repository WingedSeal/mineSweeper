package com.wingedseal.minesweeper.gui.menupage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class StartButton extends JButton {
    private static final int DEFAULT_FONT_SIZE = 50;
    private int fontSize = DEFAULT_FONT_SIZE;
    StartButton() {
        super("PLAY");
        setFocusable(false);
        setBackground(Color.DARK_GRAY);
        setForeground(Color.WHITE);
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                fontSize = (int) (1.2 * DEFAULT_FONT_SIZE);
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                fontSize = DEFAULT_FONT_SIZE;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setFont(new Font("Times New Roman", Font.BOLD, fontSize));
    }
}

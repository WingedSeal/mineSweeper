package com.wingedseal.minesweeper.gui.gamepage;

import javax.swing.*;
import java.awt.*;

public class NumberDisplay extends JLabel {
    private static final int FONT_SIZE = 50;
    private static final Font FONT = new Font("Times New Roman", Font.BOLD, FONT_SIZE);

    NumberDisplay(int startNumber) {
        super(toCustomString(startNumber));
        setOpaque(true);
        setBackground(Color.BLACK);
        setFont(FONT);
        setForeground(Color.RED);
        setHorizontalAlignment(CENTER);
    }

    void setNumber(int number) {
        setText(toCustomString(number));
    }

    private static String toCustomString(int number) {
        return String.format("%03d", number < 0 ? number % 100 : number % 1000);
    }
}

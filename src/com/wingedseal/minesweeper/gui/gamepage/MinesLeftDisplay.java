package com.wingedseal.minesweeper.gui.gamepage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static com.wingedseal.minesweeper.utils.NumberToCustomString.toCustomString;

public class MinesLeftDisplay extends JLabel {
    MinesLeftDisplay(Font font) {
        super(toCustomString(0));
        setOpaque(true);
        setBackground(Color.BLACK);
        setFont(font);
        setForeground(Color.RED);
        setHorizontalAlignment(CENTER);
    }
}

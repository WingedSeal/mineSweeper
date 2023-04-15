package com.wingedseal.minesweeper.gui.menupage;

import javax.swing.*;
import java.awt.*;

public class TitleLabel extends JLabel {
    private static final int FONT_SIZE = 40;
    TitleLabel(String title) {
        super(title);
        setFont(new Font("Times New Roman", Font.BOLD, FONT_SIZE));
        setForeground(Color.DARK_GRAY);
        setHorizontalAlignment(CENTER);
    }
}

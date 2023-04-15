package com.wingedseal.minesweeper.gui.menupage;

import com.wingedseal.minesweeper.board.Difficulty;

import javax.swing.*;
import java.awt.*;

public class DifficultySelector extends JComboBox<Difficulty> {
    private static final int FONT_SIZE = 30;
    DifficultySelector() {
        super(Difficulty.values());
        setFocusable(false);
        setBackground(Color.WHITE);
        setFont(new Font("Times New Roman", Font.PLAIN, FONT_SIZE));
        setSelectedItem(Difficulty.NORMAL);
    }
}
package com.wingedseal.minesweeper.gui.gamepage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class StatsPanel extends JPanel {
    MinesLeftDisplay minesLeftDisplay = new MinesLeftDisplay();
    EmojiButton emojiButton = new EmojiButton();
    TimerDisplay timerDisplay = new TimerDisplay();
    private static final int PADDING_SIZE_X = 20;
    private static final int PADDING_SIZE_Y = 5;

    StatsPanel() {
        setLayout(new GridLayout(1, 5));
        setBorder(new EmptyBorder(PADDING_SIZE_Y, PADDING_SIZE_X, PADDING_SIZE_Y, PADDING_SIZE_X));
        JPanel emojiDisplay = new JPanel();
        emojiDisplay.add(emojiButton);
        add(minesLeftDisplay);
        add(new JPanel());
        add(emojiDisplay);
        add(new JPanel());
        add(timerDisplay);
    }
}
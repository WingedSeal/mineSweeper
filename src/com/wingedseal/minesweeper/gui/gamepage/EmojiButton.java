package com.wingedseal.minesweeper.gui.gamepage;

import javax.swing.*;
import java.awt.*;

import static com.wingedseal.minesweeper.gui.gamepage.IconDraw.*;


public class EmojiButton extends JButton {
    private EmojiState emojiState = EmojiState.IDLE;

    EmojiButton() {
        setHorizontalAlignment(CENTER);
    }

    public EmojiState getEmojiState() {
        return emojiState;
    }

    public void setEmojiState(EmojiState emojiState) {
        this.emojiState = emojiState;
        repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension dimension = getParent().getSize();
        int size = (int) (Math.min(dimension.getWidth(), dimension.getHeight()) * 0.8);
        dimension.setSize(size, size);
        return dimension;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        setBackground(Color.LIGHT_GRAY);
        switch (emojiState) {
            case IDLE -> drawIdleEmoji(g, getWidth(), getHeight());
            case NORMAL_CELL_FOUND -> drawNormalCellFoundEmoji(g, getWidth(), getHeight());
            case LOSE -> drawLoseEmoji(g, getWidth(), getHeight());
            case WIN -> drawWinEmoji(g, getWidth(), getHeight());
        }
    }
}

enum EmojiState {
    IDLE,
    NORMAL_CELL_FOUND,
    LOSE,
    WIN
}
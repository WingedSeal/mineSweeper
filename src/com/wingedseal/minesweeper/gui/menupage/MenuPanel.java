package com.wingedseal.minesweeper.gui.menupage;

import com.wingedseal.minesweeper.board.Difficulty;
import com.wingedseal.minesweeper.gui.gamepage.IconDraw;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MenuPanel extends JPanel {
    private final StartButton startButton = new StartButton();
    private final DifficultySelector difficultySelector = new DifficultySelector();
    private final SizeSelector sizeXSelector = new SizeSelector("Width:  ");
    private final SizeSelector sizeYSelector = new SizeSelector("Height: ");

    private static final int PADDING_SIZE_X = 10;
    private static final int PADDING_SIZE_Y = 30;

    public MenuPanel(String title) {
        setBorder(new EmptyBorder(PADDING_SIZE_Y, PADDING_SIZE_X, PADDING_SIZE_Y, PADDING_SIZE_X));
        setLayout(new GridLayout(6, 1, 0, 5));
        add(new TitleLabel(title));
        add(new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                IconDraw.drawBomb(g, getWidth(), getHeight());
            }
        });
        add(difficultySelector);
        add(sizeXSelector);
        add(sizeYSelector);
        add(startButton);
    }

    public StartButton getStartButton() {
        return startButton;
    }

    public Settings getSettings() {
        return new Settings(
                (Difficulty) difficultySelector.getSelectedItem(),
                sizeXSelector.getValue(),
                sizeYSelector.getValue()
        );
    }
}

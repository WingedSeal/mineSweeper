package com.wingedseal.minesweeper.gui;

import com.wingedseal.minesweeper.gui.gamepage.GamePanel;
import com.wingedseal.minesweeper.gui.menupage.MenuPanel;
import com.wingedseal.minesweeper.gui.menupage.Settings;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private final MenuPanel menuPanel;
    private GamePanel gamePanel;
    private final CardLayout cardLayout = new CardLayout();

    private final static String MENU = "Menu";
    private final static String GAME = "Game";

    MainPanel(String tittle) {
        setLayout(cardLayout);
        menuPanel = new MenuPanel(tittle);
        menuPanel.getStartButton().addActionListener(startEvent -> {
            Settings settings = menuPanel.getSettings();
            gamePanel = new GamePanel(settings.sizeX, settings.sizeY, settings.difficulty);
            gamePanel.getEmojiButton().addActionListener(emojiEvent ->
                    cardLayout.show(this, MENU)
            );
            add(gamePanel, GAME);
            cardLayout.show(this, GAME);
        });
        add(menuPanel, MENU);
    }
}
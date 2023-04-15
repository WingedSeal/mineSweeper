package com.wingedseal.minesweeper.gui;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class App extends Window {}
class Window extends JFrame {
    private static final String TITLE = "mineSweeper";
    private static final String ICON_PATH = "assets/WingedSeal64x64.png";

    public Window() {
        super(TITLE + " (WingedSeal Edition)");
        add(new MainPanel(TITLE));
        final URL resource = getClass().getResource(ICON_PATH);
        if (resource != null) {
            setIconImage((new ImageIcon(resource)).getImage());
        }
        setSize(600, 600);
        setMinimumSize(new Dimension(450, 400));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

}
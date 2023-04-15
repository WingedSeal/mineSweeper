package com.wingedseal.minesweeper.gui.menupage;

import com.wingedseal.minesweeper.board.Difficulty;

public class Settings {

    public final Difficulty difficulty;
    public final int sizeX;
    public final int sizeY;

    Settings(Difficulty difficulty, int sizeX, int sizeY) {
        this.difficulty = difficulty;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }
}

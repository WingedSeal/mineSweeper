package com.wingedseal.minesweeper.board;

import com.wingedseal.minesweeper.gui.gamepage.CellButton;

public class Cell {

    private boolean isMine;
    private final int posX;
    private final int posY;

    private int neighborMineCount;

    public CellButton button = null;

    public Cell(boolean isMine, int posX, int posY, int neighborMineCount) {
        this.isMine = isMine;
        this.posX = posX;
        this.posY = posY;
        this.neighborMineCount = neighborMineCount;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "isMine=" + isMine +
                ", posX=" + posX +
                ", posY=" + posY +
                ", neighborMineCount=" + neighborMineCount +
                '}';
    }

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        isMine = mine;
    }

    public int getNeighborMineCount() {
        return neighborMineCount;
    }

    public void decrementNeighborMineCount() {
        --neighborMineCount;
    }


    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }
}

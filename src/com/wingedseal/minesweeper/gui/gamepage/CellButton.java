package com.wingedseal.minesweeper.gui.gamepage;

import com.wingedseal.minesweeper.board.Cell;

import javax.swing.*;
import java.awt.*;

import static com.wingedseal.minesweeper.gui.gamepage.IconDraw.*;

public class CellButton extends JPanel {

    private CellState cellState = CellState.NORMAL;
    public Cell cell;

    public CellButton(Cell cell) {
        this.cell = cell;
    }

    public void toggleFlag() {
        if (cellState == CellState.FLAGGED) {
            cellState = CellState.NORMAL;
        } else {
            cellState = CellState.FLAGGED;
        }
        repaint();
    }

    public CellState getCellState() {
        return cellState;
    }

    public void setCellState(CellState cellState) {
        this.cellState = cellState;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        switch (cellState) {
            case NORMAL -> {
                setBackground(Color.LIGHT_GRAY);
                drawNormalCell(g, getWidth(), getHeight());
            }
            case FLAGGED -> {
                setBackground(Color.LIGHT_GRAY);
                drawNormalCell(g, getWidth(), getHeight());
                drawFlag(g, getWidth(), getHeight());
            }
            case REVEALED -> {
                setBackground(Color.LIGHT_GRAY);
                drawEmptyCell(g, getWidth(), getHeight());
                drawNumber(g, getWidth(), getHeight(), Integer.toString(cell.getNeighborMineCount()));
            }
            case MINE_EXPLODED -> {
                setBackground(Color.LIGHT_GRAY);
                drawEmptyCell(g, getWidth(), getHeight());
                drawBomb(g, getWidth(), getHeight());
            }
            case THIS_MINE_EXPLODED -> {
                setBackground(Color.RED);
                drawEmptyCell(g, getWidth(), getHeight());
                drawBomb(g, getWidth(), getHeight());
            }
        }
    }
}

enum CellState {
    MINE_EXPLODED, THIS_MINE_EXPLODED, FLAGGED, REVEALED, NORMAL
}

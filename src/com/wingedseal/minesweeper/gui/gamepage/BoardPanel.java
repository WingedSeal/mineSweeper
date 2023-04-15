package com.wingedseal.minesweeper.gui.gamepage;

import com.wingedseal.minesweeper.board.Board;
import com.wingedseal.minesweeper.board.Cell;
import com.wingedseal.minesweeper.board.Difficulty;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class BoardPanel extends JPanel {
    final Board board;

    public BoardPanel(int sizeX, int sizeY, Difficulty difficulty, CellClickListener listener) {
        board = new Board(sizeX, sizeY, difficulty);
        setLayout(new GridLayout(sizeY, sizeX));
        // The order of the buttons MATTERS, for loop must be in the exact order, "for y" then "for x"
        for (int y = 0; y < sizeY; ++y) {
            for (int x = 0; x < sizeX; ++x) {
                Cell cell = board.getCell(x, y);
                CellButton button = new CellButton(cell);
                cell.button = button;
                button.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                        switch (e.getButton()) {
                            case MouseEvent.BUTTON1 -> listener.onLeftClick(button);
                            case MouseEvent.BUTTON3 -> listener.onRightClick(button);
                        }
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                    }
                });
                add(button);
            }
        }
    }
}

interface CellClickListener {
    void onLeftClick(CellButton button);

    void onRightClick(CellButton button);
}
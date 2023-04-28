package com.wingedseal.minesweeper.gui.gamepage;

import com.wingedseal.minesweeper.board.Cell;
import com.wingedseal.minesweeper.board.Difficulty;
import com.wingedseal.minesweeper.utils.Offset;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static com.wingedseal.minesweeper.utils.Offset.*;

public class GamePanel extends JPanel implements CellClickListener {
    private int estimatedMinesLeft;
    private int revealedCount = 0;
    private final BoardPanel boardPanel;
    private final StatsPanel statsPanel;
    private GameState gameState = GameState.IDLE;

    private static final int PADDING_SIZE = 5;

    public GamePanel(int sizeX, int sizeY, Difficulty difficulty) {
        setBorder(new EmptyBorder(PADDING_SIZE, PADDING_SIZE, PADDING_SIZE, PADDING_SIZE));
        boardPanel = new BoardPanel(sizeX, sizeY, difficulty, this);
        statsPanel = new StatsPanel();
        setLayout(new BorderLayout());
        add(statsPanel, BorderLayout.NORTH);
        add(boardPanel, BorderLayout.CENTER);
    }

    private void updateMinesLeft() {
        statsPanel.minesLeftDisplay.setNumber(estimatedMinesLeft);
    }

    public EmojiButton getEmojiButton() {
        return statsPanel.emojiButton;
    }

    @Override
    public void onRightClick(CellButton button) {
        if (gameState != GameState.PLAYING) return;
        switch (button.getCellState()) {
            case NORMAL -> {
                button.toggleFlag();
                --estimatedMinesLeft;
                updateMinesLeft();
            }
            case FLAGGED -> {
                button.toggleFlag();
                ++estimatedMinesLeft;
                updateMinesLeft();
            }
        }
    }

    /**
     * Makes sure the amount of neighbor mines when user
     * open the board is less than the limit by removing
     * mines around it.
     *
     * @param posX        x position of the mine
     * @param posY        y position of the mine
     * @param exceedCount how many mines exceed the maximum
     */
    private void ensureLimitNeighBorOpening(int posX, int posY, int exceedCount) {
        for (Offset offset : get8NeighborOffsets()) {
            final int x = posX + offset.deltaX();
            final int y = posY + offset.deltaY();
            Cell cell = boardPanel.board.getCell(x, y);
            if (cell == null) continue;
            if (!cell.isMine()) continue;
            boardPanel.board.removeMine(x, y);
            if (--exceedCount <= 0) return;
            long b = 10L;
        }
    }

    @Override
    public void onLeftClick(CellButton button) {
        if (gameState == GameState.END) return;
        if (gameState == GameState.IDLE) {
            final int posX = button.cell.getPosX();
            final int posY = button.cell.getPosY();
            if (button.cell.isMine()) {
                boardPanel.board.removeMine(posX, posY);
            }
            if (button.cell.getNeighborMineCount() > boardPanel.board.getDifficulty().getMaxNeighBorOpening()) {
                ensureLimitNeighBorOpening(
                        posX, posY,
                        button.cell.getNeighborMineCount() - boardPanel.board.getDifficulty().getMaxNeighBorOpening()
                );
            }
            gameState = GameState.PLAYING;
            estimatedMinesLeft = boardPanel.board.getNumberOfMines();
            updateMinesLeft();
            statsPanel.timerDisplay.startTimer();
        }

        if (button.getCellState() != CellState.NORMAL) return;
        if (button.cell.isMine()) {
            button.setCellState(CellState.THIS_MINE_EXPLODED);
            for (int x = 0; x < boardPanel.board.getSizeX(); ++x) {
                for (int y = 0; y < boardPanel.board.getSizeY(); ++y) {
                    revealMine(boardPanel.board.getCell(x, y));
                }
            }
            repaint();
            gameLose();
            return;
        }
        revealNormalCells(button.cell.getPosX(), button.cell.getPosY(), true);
        repaint();
        if (!(revealedCount >= boardPanel.board.getSizeX() * boardPanel.board.getSizeY() - boardPanel.board.getNumberOfMines()))
            return;
        gameWin();
    }

    /**
     * Reveal a mine in the cell
     *
     * @param cell the cell to reveal
     */
    private void revealMine(Cell cell) {
        if (cell == null) return;
        if (!cell.isMine()) return;
        if (cell.button.getCellState() == CellState.THIS_MINE_EXPLODED) return;
        cell.button.setCellState(CellState.MINE_EXPLODED);
    }


    /**
     * Reveal normal cells (without mine)
     *
     * @param x position x of the cell
     * @param y position y of the cell
     */
    private void revealNormalCells(int x, int y) {
        revealNormalCells(x, y, false);
    }

    /**
     * Reveal normal cells (without mine)
     *
     * @param x        position x of the cell
     * @param y        position y of the cell
     * @param isByUser whether the cell is revealed by player or
     *                 did it spread from nearby cell reveal
     */
    private void revealNormalCells(int x, int y, boolean isByUser) {

        final Cell cell = boardPanel.board.getCell(x, y);
        if (cell == null) return;
        if (cell.isMine()) return;
        if (cell.button.getCellState() == CellState.REVEALED) return;

        if (isByUser) {
            final int delay = 200;
            getEmojiButton().setEmojiState(EmojiState.NORMAL_CELL_FOUND);
            Timer timer = new Timer(delay, e -> {
                if (getEmojiButton().getEmojiState() == EmojiState.NORMAL_CELL_FOUND)
                    getEmojiButton().setEmojiState(EmojiState.IDLE);
            });
            timer.setRepeats(false);
            timer.start();
        }
        cell.button.setCellState(CellState.REVEALED);
        ++revealedCount;


        if (!isByUser && cell.getNeighborMineCount() != 0) return;

        for (Offset offSet : get4AdjacentOffsets()) {
            revealNormalCells(x + offSet.deltaX(), y + offSet.deltaY());
        }

        if (cell.getNeighborMineCount() != 0) return;

        for (Offset offSet : get4CornerOffsets()) {
            revealNormalCells(x + offSet.deltaX(), y + offSet.deltaY());
        }
    }

    private void gameWin() {
        getEmojiButton().setEmojiState(EmojiState.WIN);
        gameEnd();
    }

    private void gameLose() {
        getEmojiButton().setEmojiState(EmojiState.LOSE);
        gameEnd();
    }

    private void gameEnd() {
        estimatedMinesLeft = 0;
        updateMinesLeft();
        gameState = GameState.END;
        statsPanel.timerDisplay.stopTimer();
    }
}

enum GameState {
    IDLE, PLAYING, END
}
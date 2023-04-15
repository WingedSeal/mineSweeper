package com.wingedseal.minesweeper.board;

import com.wingedseal.minesweeper.utils.Offset;

import static com.wingedseal.minesweeper.utils.Offset.get8NeighborOffsets;

public class Board {
    private final Cell[][] cells;
    private final int sizeX;
    private final int sizeY;
    private final Difficulty difficulty;
    private int numberOfMines;

    public Board(int sizeX, int sizeY, Difficulty difficulty) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.difficulty = difficulty;
        numberOfMines = difficulty.getNumberOfMines(sizeX * sizeY);
        cells = new Cell[sizeY][sizeX];
        GenerateGrid generateGrid = new GenerateGrid(sizeX, sizeY, numberOfMines, difficulty.getMaxNeighBorMines());
        int[][] grid = generateGrid.generateMines();
        numberOfMines = generateGrid.getNumberOfMines();
        for (int x = 0; x < sizeX; ++x) {
            for (int y = 0; y < sizeY; ++y) {
                cells[y][x] = new Cell(grid[y][x] < 0, x, y, GenerateGrid.calculateNeighborMineCount(grid[y][x]));
            }
        }
    }

    /**
     * Removes mine from cell at the position.
     * Doesn't throw an error if the cell is not found.
     *
     * @param x position x of the mine
     * @param y position x of the mine
     * @throws IllegalArgumentException if the cell at the position is not a mine
     */
    public void removeMine(int x, int y) {
        Cell cell = getCell(x, y);
        if (cell == null) return;
        if (!cell.isMine())
            throw new IllegalArgumentException("Cell at position x=" + x + ", y=" + y + " is not a mine.");
        cell.setMine(false);
        for (Offset offSet : get8NeighborOffsets()) {
            Cell neighborCell = getCell(x + offSet.deltaX(), y + offSet.deltaY());
            if (neighborCell == null) continue;
            neighborCell.decrementNeighborMineCount();
        }
        --numberOfMines;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }


    public Cell getCell(int x, int y) {
        if (Offset.isNotInRange(x, y, sizeX, sizeY)) return null;
        return cells[y][x];
    }


    public int getNumberOfMines() {
        return numberOfMines;
    }
}

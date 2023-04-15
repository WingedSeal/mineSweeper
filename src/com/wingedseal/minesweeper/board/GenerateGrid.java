package com.wingedseal.minesweeper.board;

import com.wingedseal.minesweeper.utils.Offset;

import java.util.Random;

public class GenerateGrid {
    private static final Random random = new Random();
    private final int[][] grid;
    private final int sizeX;
    private final int sizeY;
    private int numberOfMines;
    private final int maxNeighBorMines;

    /**
     * This number can be any number below -8, and it is
     * used to mark a grid as a mine while maintaining the
     * counting of the neighbor mines.
     **/
    private static final int NEIGHBOR_MINE_COUNT_COUNTER = 10;


    public GenerateGrid(int sizeX, int sizeY, int numberOfMines, int maxNeighBorMines) {
        grid = new int[sizeY][sizeX];
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.numberOfMines = numberOfMines;
        this.maxNeighBorMines = maxNeighBorMines;
    }

    /**
     * Calculates how many neighbor mines there are
     * using number inside the mine's grid.
     *
     * @param mineNumber the number inside the mine's grid
     */
    public static int calculateNeighborMineCount(int mineNumber) {
        return mineNumber >= 0 ? mineNumber : mineNumber + NEIGHBOR_MINE_COUNT_COUNTER;
    }

    /**
     * Whether the mine on this position has
     * more neighbor mine than the difficulty allows.
     *
     * @param mineX position x of the mine
     * @param mineY position y of the mine
     */
    private boolean hasNeighborMinesMoreThanMax(int mineX, int mineY) {
        return calculateNeighborMineCount(grid[mineY][mineX]) > maxNeighBorMines;
    }

    /**
     * Whether this position should deny the placement of the mine.
     *
     * @param mineX position x of the mine
     * @param mineY position y of the mine
     */
    private boolean shouldReRandom(int mineX, int mineY) {
        if (grid[mineY][mineX] < 0) return true;
        for (Offset offset : Offset.get4AdjacentOffsets()) {
            final int x = mineX + offset.deltaX();
            final int y = mineY + offset.deltaY();
            if (Offset.isNotInRange(x, y, sizeX, sizeY)) continue;
            if (hasNeighborMinesMoreThanMax(x, y)) return true;
        }
        return false;
    }

    /**
     * Generates mines in form of grids that contains the
     * number which can be used to calculate neighbor
     * mines count.
     *
     * @see #calculateNeighborMineCount
     */
    public int[][] generateMines() {
        for (int i = 0; i < numberOfMines; ++i) {
            int mineX;
            int mineY;
            final int MAX_ATTEMPT = sizeX * sizeY * 2;
            int attempt = 0;
            do {
                ++attempt;
                mineX = random.nextInt(sizeX);
                mineY = random.nextInt(sizeY);
            } while (shouldReRandom(mineX, mineY) && attempt < MAX_ATTEMPT);
            if (attempt >= MAX_ATTEMPT) {
                numberOfMines = i;
                break;
            }

            for (Offset offset : Offset.get8NeighborOffsets()) {
                final int x = mineX + offset.deltaX();
                final int y = mineY + offset.deltaY();
                if (Offset.isNotInRange(x, y, sizeX, sizeY)) continue;
                ++grid[y][x];
            }
            grid[mineY][mineX] -= NEIGHBOR_MINE_COUNT_COUNTER;

        }
        return grid;
    }

    public int getNumberOfMines() {
        return numberOfMines;
    }
}
